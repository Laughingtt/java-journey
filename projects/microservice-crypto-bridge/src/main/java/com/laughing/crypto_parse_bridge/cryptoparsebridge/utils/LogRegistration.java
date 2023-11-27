package com.laughing.crypto_parse_bridge.cryptoparsebridge.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogRegistration {

    static final Logger logger = Logger.getLogger(LogRegistration.class);

    public static void main(String[] args) {
        //显示警告级别以上的信息
        logger.setLevel(Level.DEBUG);
        // debug level
        logger.debug("DEBUG(调试)");
        // info level
        logger.info("INFO(消息)");
        // warning level
        logger.warn("WARN(警告)");
        // error level
        logger.error("ERROR(错误)");
    }

    public static Logger get_logger() {

        return logger;
    }

    public static void setLevel(Level level) {
        logger.setLevel(level);
    }
}
