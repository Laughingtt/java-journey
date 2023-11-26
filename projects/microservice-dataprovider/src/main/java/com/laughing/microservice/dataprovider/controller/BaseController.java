package com.laughing.microservice.dataprovider.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.laughing.microservice.common.response.CommonResponse;
import com.laughing.microservice.dataprovider.conf.ConstructorParameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class BaseController {

    @Autowired
    public Environment env;

    private ConstructorParameters constructorParameters;

    public void init_key(Environment env) {
//        constructorParameters.construct_key(env);
        constructorParameters = new ConstructorParameters(env);
    }

    public String constructBody(String queryParamJson) throws Exception {

        String body = "";

        // 封装请求参数
        Map<String, Object> plateParam = new HashMap<>(8);
        plateParam.put("body", body);
        plateParam.put("header", null);
        plateParam.put("query", null);
        plateParam.put("secureId", null);

        log.info("requestBody is [{}]", plateParam);

        return JSON.toJSONString(plateParam);

    }

    public JSONObject getResponseData(JSONObject resjsonObject) {

        JSONObject successJsonObject = null;
        if (!resjsonObject.isEmpty()) {

            successJsonObject = CommonResponse.successJsonObject();

        } else {
            successJsonObject = CommonResponse.error();

        }
        successJsonObject.put("data", resjsonObject);
        successJsonObject.put("req_time", System.currentTimeMillis());

        return successJsonObject;
    }

    public JSONObject ReqExecutor(Object obj, HttpServletRequest request) {
        String transId = (String) request.getAttribute("ReqInfoUuid");

        //计时器
        Instant start = Instant.now();

        JSONObject queryParamJson = getQueryResult(obj);

        Instant end = Instant.now();
        long elapsed = Duration.between(start, end).toMillis();
        JSONObject result = getResponseData(queryParamJson);
        log.info("transId: {}获取响应结果：{}, 请求开始时间[{}], 请求结束时间[{}], 请求耗时[{}]ms", transId, result, start.toEpochMilli(), end.toEpochMilli(), elapsed);

        request.setAttribute("ReqQueryStart", start);
        request.setAttribute("ReqQueryEnd", end);

        return result;
    }

    public JSONObject getQueryResult(Object object) {
        return new JSONObject();
    }

    public static void main(String[] args) {
        log.info("BaseController run");
    }

}
