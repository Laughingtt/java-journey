package com.laughing.crypto_parse_bridge.cryptoparsebridge.flow;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.laughing.crypto_parse_bridge.cryptoparsebridge.protol.CryptoParse;
import com.laughing.crypto_parse_bridge.cryptoparsebridge.utils.LogRegistration;
import com.laughing.crypto_parse_bridge.cryptoparsebridge.conf.ConstructorParameters;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class RequestUrl {

    Logger logger = LogRegistration.get_logger();

    public JSONObject request_url(ConstructorParameters constructorParameters, String StringEntityBody) throws IOException {
        // 创建连接
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 请求url
        HttpPost httpPost = new HttpPost(constructorParameters.getApi_url());
        // 请求参数，设置为utf-8
        StringEntity stringEntity = new StringEntity(StringEntityBody, "utf-8");
        // 设置Content类型
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        // 发送post请求得到结果response
        HttpResponse response = httpClient.execute(httpPost);
        String resp = EntityUtils.toString(response.getEntity());
        logger.info("平台响应结果:" + resp);

        return JSONObject.parseObject(resp, JSONObject.class);
    }

    public final String constitute_body(String serviceParamJson, CryptoParse crypto_parse) throws Exception {
        String body = crypto_parse.aes_encrypt(serviceParamJson);

        // 封装请求参数
        Map<String, Object> plateParam = new HashMap<String, Object>();
        plateParam.put("body", body);
        plateParam.put("header", null);
        plateParam.put("query", null);
        plateParam.put("secureId", crypto_parse.getSecureId());
        logger.info("plateParam is :" + plateParam);

        return JSON.toJSONString(plateParam);
    }
}
