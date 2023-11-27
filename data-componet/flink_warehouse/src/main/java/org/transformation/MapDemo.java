package org.transformation;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MapDemo {
    private static final Logger log = LoggerFactory.getLogger(MapDemo.class);


    public static void main(String[] args) throws Exception {
        int port = 9000;
        log.info("hello flink {},{}", "127.0.0.1", port);
        // 环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        // 数据源
        DataStreamSource<String> streamSource = env.socketTextStream("localhost", port, "\n");

        // 转化
        SingleOutputStreamOperator<String> streamOperator = streamSource.map(new MapOperator());

        // sink
        streamOperator.print();

        // 启动
        env.execute();
    }
}

class MapOperator implements MapFunction<String, String> {

    Logger log = LoggerFactory.getLogger(MapFunction.class);


    @Override
    public String map(String s) throws Exception {
        // 转化过程，把字符串转化为大写
//        log.info(s);
        log.info("This message is : {}", s);
        return s.toUpperCase();
    }
}