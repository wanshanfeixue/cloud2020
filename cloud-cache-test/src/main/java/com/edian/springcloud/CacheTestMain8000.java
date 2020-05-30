package com.edian.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
@EnableScheduling
public class CacheTestMain8000 {
    public static void main(String[] args) {
            SpringApplication.run(CacheTestMain8000.class,args);
        }
}
