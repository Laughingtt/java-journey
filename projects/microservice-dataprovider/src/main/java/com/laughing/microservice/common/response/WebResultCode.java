package com.laughing.microservice.common.response;

/**
 * web code返回值
 *
 * @author laughing
 **/
public enum WebResultCode {

    /**
     * 成功
     */
    SUCCESS(1000, "success"),
    /**
     * 成功
     */
    _SUCCESS_(1001, "未查得"),
    /**
     * 5100
     */
    INTERFACE_ERROR(5100, "接口处理异常"),
    INTERFACE_INACCESSIBLE(5101, "接口状态为不可用"),
    INTERFACE_UNAVAILABLE(5102, "暂未开通接口服务，请与客服联系"),
    INTERFACE_NOT_EXIST(5103, "接口不存在"),
    INTERFACE_DECODE_FAILED(5200, "参数解密失败"),
    INTERFACE_PARAM_UNMATCHABLE(5201, "参数不匹配"),
    INTERFACE_PARAM_CHECK_FAILED(5202, "参数校验异常"),
    INTERFACE_PARAM_TIMESTAMP_ERROR(5203, "timestamp异常"),
    INTERFACE_AUTHENTICATION_FAILED(5205, "身份验证失败"),
    INTERFACE_DATA_PARSING_EXCEPTION(5206, "数据解析异常"),
    INTERFACE_PARAMETER_BAND_EXCEPTION(5207, "参数绑定异常"),
    UN_SUPPORT_REQUEST_METHOD(5400, "请求格式不支持"),
    DATA_SEARCH_FAILED(5600, "数据查询异常"),

    /**
     * 系统异常
     */
    SYS_ERROR(9999, "系统内部错误");


    int status;
    String msg;

    WebResultCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
