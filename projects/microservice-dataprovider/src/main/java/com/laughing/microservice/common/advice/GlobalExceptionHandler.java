package com.laughing.microservice.common.advice;

import com.alibaba.fastjson.JSONException;
import com.laughing.microservice.common.exception.BizException;
import com.laughing.microservice.common.exception.Sm4DecryptException;
import com.laughing.microservice.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
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
 * 全局异常处理类
 *
 * @author laughing
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /////////////  FAIL /////////////////

    /**
     * 拦截捕捉自定义异常 MyException.class
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public ApiResponse bizExceptionHandler(BizException ex) {
        log.warn("捕获 BizException:", ex);
        ApiResponse fail = ApiResponse.error(ex);
        return fail;
    }

    /**
     * 捕获spring mvc 参数校验引发的校验异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ValidationException.class)
    public ApiResponse validationExceptionHandler(ValidationException ex) {
        log.info("捕获 ValidationException:{}", ex.getMessage());
        ApiResponse fail = ApiResponse.UN_SUPPORT_REQUEST_METHOD();
        return fail;
    }


    @ExceptionHandler(value = BindException.class)
    public ApiResponse bindExceptionHandler(BindException ex) {

        StringBuilder msg = new StringBuilder();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError allError : allErrors) {
            msg.append(allError.getDefaultMessage()).append("\n");
        }
        log.info("捕获 BindException:{}. trace[{}]", msg, ex.getMessage());

        ApiResponse fail = ApiResponse.INTERFACE_PARAMETER_BAND_EXCEPTION();
        return fail;
    }


    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ApiResponse noHandlerFoundExceptionHandler(NoHandlerFoundException ex) {
        log.warn("捕获 NoHandlerFoundException:{}", ex.getMessage());
        ApiResponse fail = ApiResponse.INTERFACE_NOT_EXIST(ex.getRequestURL());
        return fail;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ApiResponse illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        log.warn("捕获 IllegalArgumentException:{}", ex.getMessage());
        ApiResponse fail = ApiResponse.INTERFACE_PARAM_UNMATCHABLE();
        return fail;
    }

    @ExceptionHandler(value = ParseException.class)
    public ApiResponse parseExceptionHandler(ParseException ex) {
        log.warn("捕获 ParseException:{}", ex);
        ApiResponse fail = ApiResponse.INTERFACE_DATA_PARSING_EXCEPTION();
        return fail;
    }

    @ExceptionHandler(value = MissingRequestValueException.class)
    public ApiResponse missingRequestValueExceptionHandler(MissingRequestValueException ex) {
        log.warn("捕获 MissingRequestValueException:{}", ex);
        ApiResponse fail = ApiResponse.INTERFACE_PARAM_UNMATCHABLE();
        return fail;
    }

    /**
     * 请求格式不匹配
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ApiResponse httpRequestMethodNotSupportedExceptionExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        log.error("捕获 HttpRequestMethodNotSupportedException:{}", ex);
        ApiResponse fail = ApiResponse.UN_SUPPORT_REQUEST_METHOD();
        return fail;
    }


    ////////////////// ERROR ////////////////////

    @ExceptionHandler(value = NullPointerException.class)
    public ApiResponse nullPointerExceptionHandler(NullPointerException ex) {
        log.warn("捕获 NullPointerException:{}", ex);
        ApiResponse error = ApiResponse.error("参数为空异常：" + ex.getMessage());
        return error;
    }

    @ExceptionHandler(value = SQLException.class)
    public ApiResponse sqlExceptionHandler(SQLException ex) {
        log.error("捕获 SQLException:{}", ex);
        ApiResponse error = ApiResponse.error("系统发生SQL异常,请联系管理员");
        return error;
    }


    /**
     * 全局异常捕捉处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ApiResponse errorHandler(Exception ex) {
        log.error("捕获Exception:", ex);
        ApiResponse error = ApiResponse.error("系统发生异常,请联系管理员");
        return error;
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ApiResponse httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex) {
        log.error("捕获HttpMessageNotReadableException:{}", ex);
        ApiResponse error = ApiResponse.INTERFACE_PARAM_CHECK_FAILED();
        return error;
    }

    @ExceptionHandler(value = JSONException.class)
    public ApiResponse JSONExceptionHandler(JSONException ex) {
        log.error("捕获 JSONException:{}", ex);
        ApiResponse error = ApiResponse.INTERFACE_DATA_PARSING_EXCEPTION();
        return error;
    }


    @ExceptionHandler(Sm4DecryptException.class)
    public ApiResponse handleCustomException(Sm4DecryptException ex) {
        log.error("捕获 Sm4DecryptException:{}", ex);
        ApiResponse error = ApiResponse.INTERFACE_DECODE_FAILED();
        return error;
    }

//    @ExceptionHandler(value = CommunicationsException.class)
//    public APIResponse errorCommunicationsExceptionHandler(CommunicationsException ex) {
//        log.error("捕获 CommunicationsException:", ex);
//        APIResponse error = APIResponse.error("系统发生通信异常,请联系管理员");
//        return error;
//    }


}
