package com.laughing.microservice.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LogService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void writeToDatabase(String logMessage) {
        try {
            // 使用Lombok生成的日志记录器记录日志
            log.info("Writing to database: {}", logMessage);

            // 使用JdbcTemplate将日志信息写入到数据库
            String sql = "INSERT INTO log_table (log_message) VALUES (?)";
            jdbcTemplate.update(sql, logMessage);
        } catch (Exception e) {
            // 记录错误日志
            log.error("Error writing to database: {}", e.getMessage(), e);
        }
    }

    public void writeToLogTable(String Uuid, String ReqTotalTime, String ReqQueryTime) {
        try {
            // 使用Lombok生成的日志记录器记录日志
            log.info("Writing to database: {},{},{}", Uuid, ReqTotalTime, ReqQueryTime);

            // 使用JdbcTemplate将日志信息写入到数据库
            String sql = "INSERT INTO req_log_info (trans_id,req_time,query_time) VALUES (?,?,?)";
            jdbcTemplate.update(sql, Uuid, ReqTotalTime, ReqQueryTime);
        } catch (Exception e) {
            // 记录错误日志
            log.error("Error writing to database: {}", e.getMessage(), e);
        }
    }
}
