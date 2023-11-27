package com.laughing.microservice.common.response;

import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

/**
 * 为 adaptor 定制的response对象
 *
 * @author linld
 */
public class CommonResponse {
    public static JSONObject successJsonObject() {

        JSONObject jsonObject = new JSONObject(true);

        jsonObject.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.SUCCESS.getStatus());
        jsonObject.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.SUCCESS.getMsg());

        return jsonObject;
    }

    public static JSONObject successJsonObject(JSONObject jsonObject) {

        if (Objects.isNull(jsonObject)) {
            jsonObject = new JSONObject(true);
        }

        jsonObject.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.SUCCESS.getStatus());
        jsonObject.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.SUCCESS.getMsg());

        return jsonObject;
    }

    public static JSONObject _successJsonObject() {

        JSONObject jsonObject = new JSONObject(true);

        jsonObject.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode._SUCCESS_.getStatus());
        jsonObject.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode._SUCCESS_.getMsg());

        return jsonObject;
    }

    public static JSONObject _successJsonObject(JSONObject jsonObject) {

        if (Objects.isNull(jsonObject)) {
            jsonObject = new JSONObject(true);
        }

        jsonObject.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode._SUCCESS_.getStatus());
        jsonObject.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode._SUCCESS_.getMsg());

        return jsonObject;
    }

    /**
     * 系统异常
     */
    public static JSONObject error() {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.SYS_ERROR.getStatus());
        jsonObject.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.SYS_ERROR.getMsg());

        return jsonObject;
    }

    public static JSONObject error(String message) {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.SYS_ERROR.getStatus());
        jsonObject.put(CommonString.COMMON_RESPONSE_MSG, message);

        return jsonObject;
    }


//////////////////// 5xxx ///////////////////////////

    public static JSONObject INTERFACE_ERROR() {
        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.INTERFACE_ERROR.getStatus());
        jsonObject.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.INTERFACE_ERROR.getMsg());

        return jsonObject;
    }

    public static JSONObject INTERFACE_INACCESSIBLE() {
        JSONObject rb = new JSONObject(true);
        rb.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.INTERFACE_INACCESSIBLE.getStatus());
        rb.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.INTERFACE_INACCESSIBLE.getMsg());
        return rb;
    }

    public static JSONObject INTERFACE_UNAVAILABLE() {
        JSONObject rb = new JSONObject(true);
        rb.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.INTERFACE_UNAVAILABLE.getStatus());
        rb.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.INTERFACE_UNAVAILABLE.getMsg());
        return rb;
    }

    public static JSONObject INTERFACE_NOT_EXIST(String msg) {
        JSONObject rb = new JSONObject(true);
        rb.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.INTERFACE_NOT_EXIST.getStatus());
        rb.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.INTERFACE_NOT_EXIST.getMsg() + ":" + msg);
        return rb;
    }


    public static JSONObject INTERFACE_DECODE_FAILED() {
        JSONObject rb = new JSONObject(true);
        rb.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.INTERFACE_DECODE_FAILED.getStatus());
        rb.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.INTERFACE_DECODE_FAILED.getMsg());
        return rb;
    }

    public static JSONObject INTERFACE_PARAM_UNMATCHABLE() {
        JSONObject rb = new JSONObject(true);
        rb.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.INTERFACE_PARAM_UNMATCHABLE.getStatus());
        rb.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.INTERFACE_PARAM_UNMATCHABLE.getMsg());
        return rb;
    }

    public static JSONObject INTERFACE_PARAM_CHECK_FAILED() {
        JSONObject rb = new JSONObject(true);
        rb.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.INTERFACE_PARAM_CHECK_FAILED.getStatus());
        rb.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.INTERFACE_PARAM_CHECK_FAILED.getMsg());
        return rb;
    }

    public static JSONObject INTERFACE_PARAM_TIMESTAMP_ERROR() {
        JSONObject rb = new JSONObject(true);
        rb.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.INTERFACE_PARAM_TIMESTAMP_ERROR.getStatus());
        rb.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.INTERFACE_PARAM_TIMESTAMP_ERROR.getMsg());
        return rb;
    }

    public static JSONObject INTERFACE_DATA_PARSING_EXCEPTION() {
        JSONObject rb = new JSONObject(true);
        rb.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.INTERFACE_DATA_PARSING_EXCEPTION.getStatus());
        rb.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.INTERFACE_DATA_PARSING_EXCEPTION.getMsg());
        return rb;
    }

    public static JSONObject INTERFACE_AUTHENTICATION_FAILED() {
        JSONObject rb = new JSONObject(true);
        rb.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.INTERFACE_AUTHENTICATION_FAILED.getStatus());
        rb.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.INTERFACE_AUTHENTICATION_FAILED.getMsg());
        return rb;
    }

    public static JSONObject INTERFACE_PARAMETER_BAND_EXCEPTION() {
        JSONObject rb = new JSONObject(true);
        rb.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.INTERFACE_PARAMETER_BAND_EXCEPTION.getStatus());
        rb.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.INTERFACE_PARAMETER_BAND_EXCEPTION.getMsg());
        return rb;
    }

    public static JSONObject UN_SUPPORT_REQUEST_METHOD() {
        JSONObject rb = new JSONObject(true);
        rb.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.UN_SUPPORT_REQUEST_METHOD.getStatus());
        rb.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.UN_SUPPORT_REQUEST_METHOD.getMsg());
        return rb;
    }

    public static JSONObject DATA_SEARCH_FAILED() {
        JSONObject rb = new JSONObject(true);
        rb.put(CommonString.COMMON_RESPONSE_CODE, WebResultCode.DATA_SEARCH_FAILED.getStatus());
        rb.put(CommonString.COMMON_RESPONSE_MSG, WebResultCode.DATA_SEARCH_FAILED.getMsg());
        return rb;
    }


}
