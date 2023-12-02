package com.laughing.microservice.dataprovider.conf;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class SetConf {
    /**
     *
     */
    @Value("${crypto-config.serviceKey}")
    private String serviceKey;

    /**
     *
     */
    @Value("${crypto-config.sm4Key}")
    private String sm4Key;


}
