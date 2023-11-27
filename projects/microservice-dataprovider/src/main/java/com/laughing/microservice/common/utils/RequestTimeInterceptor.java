package com.laughing.microservice.common.utils;

import com.laughing.microservice.crypto.utils.Md5Crypto;
import com.laughing.microservice.dataprovider.entity.ReqLogInfo;
import com.laughing.microservice.dataprovider.service.ReqLogInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Slf4j
public class RequestTimeInterceptor implements HandlerInterceptor {

    private final ReqLogInfoService ReqLogInfoService;

    private static final ThreadLocal<Instant> startTime = new ThreadLocal<>();

    public RequestTimeInterceptor(ReqLogInfoService ReqLogInfoService) {
        this.ReqLogInfoService = ReqLogInfoService;
    }

    public static String getUuid() {
        return Md5Crypto.encode(UUID.randomUUID().toString());
    }

    public static String getUuid2() {
        return GetFastUuid.getTransId();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        startTime.set(Instant.now());
        request.setAttribute("ReqInfoUuid", getUuid2());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // 这里可以处理请求完成后的逻辑
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Instant _startTime = startTime.get();
        Instant _endTime = Instant.now();
        long ReqTotalTime = Duration.between(_startTime, _endTime).toMillis();
        ;
        log.info("======请求耗时======[{}]ms", ReqTotalTime);

        Object transId = request.getAttribute("ReqInfoUuid");
        String reqPath = request.getRequestURI();
        Object _ReqQueryStart = request.getAttribute("ReqQueryStart");
        Object _ReqQueryEnd = request.getAttribute("ReqQueryEnd");


        ReqLogInfo reqLogInfo = new ReqLogInfo();
        reqLogInfo.setTrans_id((String) transId);
        reqLogInfo.setReq_path(reqPath);
        reqLogInfo.setReq_start_tsp(_startTime);
        reqLogInfo.setReq_end_tsp(_endTime);
        reqLogInfo.setQuery_start_tsp((Instant) _ReqQueryStart);
        reqLogInfo.setQuery_end_tsp((Instant) _ReqQueryEnd);
        reqLogInfo.setReq_time(String.valueOf(ReqTotalTime));
        reqLogInfo.setInfo("");

        // 同步插入
        //        ReqLogInfoService.insertInfoAsync(reqLogInfo);

        //        Instant t0 = Instant.now();
        // 异步插入
        ReqLogInfoService.insertInfoAsync(reqLogInfo).handle((result, exception) -> {
            if (exception != null) {
                // 处理异常
                log.info(exception.getMessage());
            } else {
                // 异步方法执行成功
                log.info("Async Insert request completed successfully.");
            }
            return null;
        });
        //        Instant t1 = Instant.now();
        //        log.info("insert time is :{}",Duration.between(t0, t1).toMillis());


    }
}
