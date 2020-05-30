package com.edian.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_Ok(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "方法：paymentInfo_Ok,id:" + id + "\t" + "成功";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "，方法：paymentInfo_TimeOut,id" + id + "\t" + "耗时:" + "（秒）";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "，8001系统繁忙或者运行错误！请稍后再试，(ㄒoㄒ)";
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启熔断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),//请求量阈值
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),//错误百分比，失败率达到多少后熔断
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "，调用成功，流水号为：" + serialNumber;
    }

    public String paymentCircuitBreakerFallback(Integer id) {
        return "id不能为负数，请稍后再试，/(ㄒoㄒ)/~~，id：" + id;
    }
}
