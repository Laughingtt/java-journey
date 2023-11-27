package com.laughing.microservice.dataprovider;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author zhongjie
 * @date 2023/10/23
 */
@Slf4j
@ComponentScan("com.laughing.microservice")
@MapperScan("com.laughing.microservice.**.dao")
@SpringBootApplication
@EnableAsync
public class DataproviderApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(DataproviderApplication.class, args);
        } catch (Exception e) {
            log.error("Server启动失败,异常信息:{}", e.getMessage(), e);
        }
    }
}
