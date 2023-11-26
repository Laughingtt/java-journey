package com.laughing.microservice.common.response;

import com.alibaba.fastjson.JSONObject;
import com.laughing.microservice.common.exception.BizException;
import lombok.Data;


/**
 * 返回值
 *
 * @author laughing
 **/
@Data
public class ApiResponse {
    private int Code;

    private String Message;

    private Object result;

    public ApiResponse() {
    }

    public ApiResponse(int Code, String Message) {
        this.Code = Code;
        this.Message = Message;
    }

    public static ApiResponse success() {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.SUCCESS.getStatus());
        rb.setMessage(WebResultCode.SUCCESS.getMsg());
        return rb;
    }

    public static ApiResponse success(Object data) {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.SUCCESS.getStatus());
        rb.setMessage(WebResultCode.SUCCESS.getMsg());
        rb.setResult(data);
        return rb;
    }

    public static ApiResponse success(JSONObject data) {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.SUCCESS.getStatus());
        rb.setMessage(WebResultCode.SUCCESS.getMsg());
        rb.setResult(data);
        return rb;
    }

    /**
     * 系统异常
     */
    public static ApiResponse error() {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.SYS_ERROR.getStatus());
        rb.setMessage(WebResultCode.SYS_ERROR.getMsg());
        return rb;
    }

    public static ApiResponse error(String message) {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.SYS_ERROR.getStatus());
        rb.setMessage(message);
        return rb;
    }

    public static ApiResponse error(BizException exception) {
        ApiResponse rb = new ApiResponse();
        rb.setCode(exception.getStatus());
        rb.setMessage(exception.getMsg());
        return rb;
    }


//////////////////// 5xxx ///////////////////////////

    public static ApiResponse INTERFACE_ERROR() {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.INTERFACE_ERROR.getStatus());
        rb.setMessage(WebResultCode.INTERFACE_ERROR.getMsg());
        return rb;
    }

    public static ApiResponse INTERFACE_INACCESSIBLE() {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.INTERFACE_INACCESSIBLE.getStatus());
        rb.setMessage(WebResultCode.INTERFACE_INACCESSIBLE.getMsg());
        return rb;
    }

    public static ApiResponse INTERFACE_UNAVAILABLE() {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.INTERFACE_UNAVAILABLE.getStatus());
        rb.setMessage(WebResultCode.INTERFACE_UNAVAILABLE.getMsg());
        return rb;
    }

    public static ApiResponse INTERFACE_NOT_EXIST(String Message) {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.INTERFACE_NOT_EXIST.getStatus());
        rb.setMessage(WebResultCode.INTERFACE_NOT_EXIST.getMsg() + ":" + Message);
        return rb;
    }


    public static ApiResponse INTERFACE_DECODE_FAILED() {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.INTERFACE_DECODE_FAILED.getStatus());
        rb.setMessage(WebResultCode.INTERFACE_DECODE_FAILED.getMsg());
        return rb;
    }

    public static ApiResponse INTERFACE_PARAM_UNMATCHABLE() {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.INTERFACE_PARAM_UNMATCHABLE.getStatus());
        rb.setMessage(WebResultCode.INTERFACE_PARAM_UNMATCHABLE.getMsg());
        return rb;
    }

    public static ApiResponse INTERFACE_PARAM_CHECK_FAILED() {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.INTERFACE_PARAM_CHECK_FAILED.getStatus());
        rb.setMessage(WebResultCode.INTERFACE_PARAM_CHECK_FAILED.getMsg());
        return rb;
    }

    public static ApiResponse INTERFACE_PARAM_TIMESTAMP_ERROR() {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.INTERFACE_PARAM_TIMESTAMP_ERROR.getStatus());
        rb.setMessage(WebResultCode.INTERFACE_PARAM_TIMESTAMP_ERROR.getMsg());
        return rb;
    }

    public static ApiResponse INTERFACE_DATA_PARSING_EXCEPTION() {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.INTERFACE_DATA_PARSING_EXCEPTION.getStatus());
        rb.setMessage(WebResultCode.INTERFACE_DATA_PARSING_EXCEPTION.getMsg());
        return rb;
    }

    public static ApiResponse INTERFACE_AUTHENTICATION_FAILED() {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.INTERFACE_AUTHENTICATION_FAILED.getStatus());
        rb.setMessage(WebResultCode.INTERFACE_AUTHENTICATION_FAILED.getMsg());
        return rb;
    }

    public static ApiResponse INTERFACE_PARAMETER_BAND_EXCEPTION() {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.INTERFACE_PARAMETER_BAND_EXCEPTION.getStatus());
        rb.setMessage(WebResultCode.INTERFACE_PARAMETER_BAND_EXCEPTION.getMsg());
        return rb;
    }

    public static ApiResponse UN_SUPPORT_REQUEST_METHOD() {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.UN_SUPPORT_REQUEST_METHOD.getStatus());
        rb.setMessage(WebResultCode.UN_SUPPORT_REQUEST_METHOD.getMsg());
        return rb;
    }

    public static ApiResponse DATA_SEARCH_FAILED() {
        ApiResponse rb = new ApiResponse();
        rb.setCode(WebResultCode.DATA_SEARCH_FAILED.getStatus());
        rb.setMessage(WebResultCode.DATA_SEARCH_FAILED.getMsg());
        return rb;
    }


    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
