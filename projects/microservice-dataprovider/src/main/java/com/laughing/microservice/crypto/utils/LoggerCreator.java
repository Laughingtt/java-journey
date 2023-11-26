package com.laughing.microservice.crypto.utils;

import com.alibaba.fastjson.JSONObject;

import java.time.Duration;
import java.time.Instant;

/**
 * 用来生成不同的内容
 *
 * @author linld
 */
public class LoggerCreator {

    public static String logReq(Instant start, String requestURI, String method, String ip, String headerStr, String param, String desensitizedBody, int status, String desensitizedResponse, Instant end, long elapsed, String idNoMd5) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("req_ip", ip);
        jsonObject.put("req_method", method);
        jsonObject.put("req_start_time", start.toEpochMilli());
        jsonObject.put("req_end_time", end.toEpochMilli());
        jsonObject.put("req_cost_time", elapsed);
        jsonObject.put("req_header", headerStr);
        jsonObject.put("req_url", requestURI);
        jsonObject.put("req_para", param);
        jsonObject.put("req_body", desensitizedBody);
        jsonObject.put("req_staus", status);
        jsonObject.put("req_return", desensitizedResponse);
        jsonObject.put("idno_md5", idNoMd5);

        String logStr = jsonObject.toJSONString();

        return logStr;
    }

    public static String logParamEnc(Instant start, Instant end) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("para_enc_start_time", start.toEpochMilli());
        jsonObject.put("para_enc_end_time", end.toEpochMilli());
        jsonObject.put("para_enc_cost_time", Duration.between(start, end).toMillis());

        return jsonObject.toJSONString();
    }

    /**
     * 生成加密日志
     * @param start 开始时间
     * @param end 结束时间
     * @param encType 加密类型
     * @return
     */
    public static String logReqEnc(Instant start, Instant end, String encType) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("req_enc_start_time", start.toEpochMilli());
        jsonObject.put("req_enc_end_time", end.toEpochMilli());
        jsonObject.put("req_enc_end_time", end.toEpochMilli());
        jsonObject.put("enc_type", encType);

        return jsonObject.toJSONString();
    }

    public static String logOthersReq(Instant start, Instant end, String resultBody, String traceId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("others_req_start_time", start.toEpochMilli());
        jsonObject.put("others_req_end_time", end.toEpochMilli());
        jsonObject.put("others_req_cost_time", Duration.between(start, end).toMillis());
        jsonObject.put("others_req_return", resultBody);
        jsonObject.put("others_req_traceid", traceId);

        return jsonObject.toJSONString();
    }

    public static String logOthersDec(Instant start, Instant end, String resultBody, String traceId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("others_dec_start_time", start.toEpochMilli());
        jsonObject.put("others_dec_end_time", end.toEpochMilli());
        jsonObject.put("others_dec_cost_time", Duration.between(start, end).toMillis());
        jsonObject.put("others_dec_return", resultBody);
        jsonObject.put("others_dec_traceid", traceId);

        return jsonObject.toJSONString();
    }

    public static String logExcept(Exception exception) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("except_info", exception.getMessage());

        return jsonObject.toJSONString();
    }
}
