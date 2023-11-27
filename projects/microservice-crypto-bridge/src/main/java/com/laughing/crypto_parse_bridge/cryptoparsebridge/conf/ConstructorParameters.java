package com.laughing.crypto_parse_bridge.cryptoparsebridge.conf;

import org.springframework.core.env.Environment;

public class ConstructorParameters {

    private String serviceKey;
    private String sm4Key;
    private String secureId;
    private String api_url;
    private String version;
    private String realUser;
    private String scene;


    public final void construct_key(Environment env, String api_url_info, String server_info) {

        this.api_url = env.getProperty(api_url_info);
        this.version = env.getProperty( ShdataDistributionConf.version);
        this.realUser = env.getProperty(ShdataDistributionConf.realUser);
        this.scene = env.getProperty(ShdataDistributionConf.scene);

        this.serviceKey = env.getProperty(server_info + "." + ShdataDistributionConf.serviceKey);
        this.sm4Key = env.getProperty(server_info + "." + ShdataDistributionConf.sm4Key);
        this.secureId = env.getProperty(server_info + "." + ShdataDistributionConf.secureId);

        System.out.println("serviceKey :" + serviceKey);
        System.out.println("sm4Key :" + sm4Key);
        System.out.println("secureId :" + secureId);
        System.out.println("api_url :" + api_url);

    }

    public String getApi_url() {
        return api_url;
    }

    public String getSecureId() {
        return secureId;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public String getSm4Key() {
        return sm4Key;
    }

    public String getRealUser() {
        return realUser;
    }

    public String getScene() {
        return scene;
    }

    public String getVersion() {
        return version;
    }
}
