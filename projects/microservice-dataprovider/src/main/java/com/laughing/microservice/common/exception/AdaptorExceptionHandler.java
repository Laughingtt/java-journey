package com.laughing.microservice.common.exception;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.laughing.microservice.common.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 * adaptor 异常处理类
 *
 * @author laughing
 **/
@Slf4j
@Primary
@RestControllerAdvice
public class AdaptorExceptionHandler {

    /////////////  FAIL /////////////////

    /**
     * 拦截捕捉自定义异常 MyException.class
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public JSONObject bizExceptionHandler(BizException ex) {
        log.warn("捕获 BizException:", ex.getMessage());
        JSONObject fail = CommonResponse.error(ex.getMessage());
        return fail;
    }

    /**
     * 捕获spring mvc 参数校验引发的校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ValidationException.class)
    public JSONObject validationExceptionHandler(ValidationException ex) {
        log.info("捕获 ValidationException:{}", ex.getMessage());
        JSONObject fail = CommonResponse.UN_SUPPORT_REQUEST_METHOD();
        return fail;
    }


    @ExceptionHandler(value = BindException.class)
    public JSONObject bindExceptionHandler(BindException ex) {

        StringBuilder msg = new StringBuilder();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError allError : allErrors) {
            msg.append(allError.getDefaultMessage()).append("\n");
        }
        log.info("捕获 BindException:{}. trace[{}]", msg, ex.getMessage());

        JSONObject fail = CommonResponse.INTERFACE_PARAMETER_BAND_EXCEPTION();
        return fail;
    }


    @ExceptionHandler(value = NoHandlerFoundException.class)
    public JSONObject noHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
        log.warn("捕获 NoHandlerFoundException:{}", ex.getMessage());
        JSONObject fail = CommonResponse.INTERFACE_NOT_EXIST(ex.getRequestURL());
        return fail;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public JSONObject illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        log.warn("捕获 IllegalArgumentException:{}", ex.getMessage());
        JSONObject fail = CommonResponse.INTERFACE_PARAM_UNMATCHABLE();
        return fail;
    }

    @ExceptionHandler(value = ParseException.class)
    public JSONObject parseExceptionHandler(ParseException ex) {
        log.warn("捕获 ParseException:{}", ex);
        JSONObject fail = CommonResponse.INTERFACE_DATA_PARSING_EXCEPTION();
        return fail;
    }

    @ExceptionHandler(value = MissingRequestValueException.class)
    public JSONObject missingRequestValueExceptionHandler(MissingRequestValueException ex) {
        log.warn("捕获 MissingRequestValueException:{}", ex);
        JSONObject fail = CommonResponse.INTERFACE_PARAM_UNMATCHABLE();
        return fail;
    }

    /**
     * 请求格式不匹配
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public JSONObject httpRequestMethodNotSupportedExceptionExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.error("捕获 HttpRequestMethodNotSupportedException:{}", ex);
        JSONObject fail = CommonResponse.UN_SUPPORT_REQUEST_METHOD();
        return fail;
    }


    ////////////////// ERROR ////////////////////

    @ExceptionHandler(value = NullPointerException.class)
    public JSONObject nullPointerExceptionHandler(NullPointerException ex) {
        log.warn("捕获 NullPointerException:{}", ex);
        JSONObject error = CommonResponse.error("参数为空异常：" + ex.getMessage());
        return error;
    }

    @ExceptionHandler(value = SQLException.class)
    public JSONObject sqlExceptionHandler(SQLException ex) {
        log.error("捕获 SQLException:{}", ex);
        JSONObject error = CommonResponse.error("系统发生SQL异常,请联系管理员");
        return error;
    }


    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public JSONObject errorHandler(Exception ex) {
        log.error("捕获Exception:", ex);
        JSONObject error = CommonResponse.error("系统发生异常,请联系管理员");
        return error;
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public JSONObject httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        log.error("捕获HttpMessageNotReadableException:{}", ex);
        JSONObject error = CommonResponse.INTERFACE_PARAM_CHECK_FAILED();
        return error;
    }

    @ExceptionHandler(value = JSONException.class)
    public JSONObject JSONExceptionHandler(JSONException ex) {
        log.error("捕获 JSONException:{}", ex);
        JSONObject error = CommonResponse.INTERFACE_DATA_PARSING_EXCEPTION();
        return error;
    }


}
