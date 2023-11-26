package org.transformation;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FlatMapDemo {

    public static void main(String[] args) throws Exception {
        int port = 9000;

        // 环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 数据源
        DataStreamSource<String> streamSource = env.socketTextStream("localhost", port, "\n");

        // 转化
        SingleOutputStreamOperator<Character> streamOperator = streamSource.flatMap(new FlatMapOperator());

        // sink
        streamOperator.print();

        // 启动
        env.execute();
    }
}

class FlatMapOperator implements FlatMapFunction<String, Character> {

    Logger log = LoggerFactory.getLogger(MapFunction.class);

    @Override
    public void flatMap(String s, Collector<Character> collector) throws Exception {

        log.info("the message is :{}",s);
        // 转换为大写
        String upperStr = s.toUpperCase();

        // 拆分每一个字母并下发
        for (int i = 0; i < upperStr.length(); i++) {
            char c = upperStr.charAt(i);
            collector.collect(c);
        }
    }
}