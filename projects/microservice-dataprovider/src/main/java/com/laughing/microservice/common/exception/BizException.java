package com.laughing.microservice.common.exception;

import com.laughing.microservice.common.response.WebResultCode;

/**
 * 业务异常
 *
 * @author laughing
 **/
public class BizException extends RuntimeException {


    private static final long serialVersionUID = 7040571198809404505L;

    public BizException() {
        super();
    }

    public BizException(String message) {
        super(message);
        this.msg = message;
    }

    public BizException(WebResultCode webResultCode) {
        super(webResultCode.getMsg());
        this.status = webResultCode.getStatus();
        this.msg = webResultCode.getMsg();
    }

    public BizException(int status, String message) {
        super(message);
        this.status = status;
        this.msg = message;
    }

    private int status;
    private String msg;


    public BizException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
