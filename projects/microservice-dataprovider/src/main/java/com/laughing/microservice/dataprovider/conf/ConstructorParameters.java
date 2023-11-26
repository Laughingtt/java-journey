package com.laughing.microservice.dataprovider.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;

@Slf4j
public class ConstructorParameters {

    private String serviceKey;
    private String sm4Key;
    private String version;

    public ConstructorParameters(Environment env) {
        this.version = env.getProperty(CryptoConfig.version);

        this.serviceKey = env.getProperty(CryptoConfig.serviceKey);
        this.sm4Key = env.getProperty(CryptoConfig.sm4Key);

        log.info("serviceKey :" + serviceKey);
        log.info("sm4Key :" + sm4Key);
        log.info("version :" + version);
    }


    public final void construct_key(Environment env) {

        this.version = env.getProperty(CryptoConfig.version);

        this.serviceKey = env.getProperty(CryptoConfig.serviceKey);
        this.sm4Key = env.getProperty(CryptoConfig.sm4Key);

        log.info("serviceKey :" + serviceKey);
        log.info("sm4Key :" + sm4Key);
        log.info("version :" + version);

    }

    public String getServiceKey() {
        return serviceKey;
    }

    public String getSm4Key() {
        return sm4Key;
    }

    public String getVersion() {
        return version;
    }
}
