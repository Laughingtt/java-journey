package com.laughing.microservice.common.utils;

import com.laughing.microservice.dataprovider.service.ReqLogInfoService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    private ReqLogInfoService ReqLogInfoService;

    public WebConfig(ReqLogInfoService ReqLogInfoService) {
        this.ReqLogInfoService = ReqLogInfoService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestTimeInterceptor(ReqLogInfoService));
    }
}