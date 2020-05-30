package com.edian.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//配置feign的日志级别
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
        //请求和响应的头信息，请求和响应的正文及元数据
        return Logger.Level.FULL;
    }
}
