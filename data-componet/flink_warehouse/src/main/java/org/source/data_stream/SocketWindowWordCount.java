package org.source.data_stream;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * 滑动窗口的计算
 *
 * 通过socket模拟产生单词数据 flink对其进行统计计数
 * 实现时间窗口：
 *              每隔1秒统计前两秒的数据
 */
public class SocketWindowWordCount {
    public static void main(String[] args) throws Exception{
        //定义端口号，通过cli接收
        int port;
        try{
            ParameterTool parameterTool = ParameterTool.fromArgs(args);
            port = parameterTool.getInt("port");
        }catch(Exception e){
            System.err.println("No port Set, use default port---java");
            port = 9000;
        }

        //获取运行时环境，必须要
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //绑定Source,通过master的nc -l 900 产生单词
        String hostname = "127.0.0.1";
        String delimiter = "\n";
        //连接socket 绑定数据源
        DataStreamSource<String> socketWord = env.socketTextStream(hostname, port, delimiter);

        DataStream<WordWithCount> windowcounts = socketWord.flatMap(new FlatMapFunction<String, WordWithCount>() {
                    public void flatMap(String value, Collector<WordWithCount> out) throws Exception {
                        String[] splits = value.split("\\s");
                        for (String word : splits) {
                            out.collect(new WordWithCount(word, 1));
                        }
                    }
                }).keyBy("word")
                .timeWindow(Time.seconds(2), Time.seconds(1))
                //.sum("count");//这里求聚合 可以用reduce和sum两种方式
                .reduce(new ReduceFunction<WordWithCount>() {
                    public WordWithCount reduce(WordWithCount a, WordWithCount b) throws Exception {
                        return new WordWithCount(a.word, a.count + b.count);
                    }
                });
        windowcounts.print().setParallelism(1);
        env.execute("socketWindow");
    }

    public static class  WordWithCount{
        public String word;
        public int count;
        //无参的构造函数
        public WordWithCount(){

        }
        //有参的构造函数
        public WordWithCount(String word, int count){
            this.count = count;
            this.word = word;
        }

        @Override
        public String toString() {
            return "WordWithCount{" +
                    "word='" + word + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}