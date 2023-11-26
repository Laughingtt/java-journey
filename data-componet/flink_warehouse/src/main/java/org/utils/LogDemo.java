package org.utils;

import org.apache.log4j.Logger;

public class LogDemo {
    private static  final Logger LOGGER = Logger.getLogger(LogDemo.class);


    public static void main(String[] args) {

        System.out.printf("hello\n");
        // 记录debug级别的信息
        LOGGER.debug("This is debug message.");
        // 记录info级别的信息
        LOGGER.info("This is info message.");
        // 记录warn级别的信息
        LOGGER.info("This is warn message.");
        // 记录error级别的信息
        LOGGER.error("This is error message.");
    }
}
