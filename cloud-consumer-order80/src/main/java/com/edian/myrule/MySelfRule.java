package com.edian.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
* @Description: MySelfRule 类（或接口）是自定义负载均衡路由规则
* @Author: wu
* @Date: 2020/4/28 11:16
*/
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        //负载均衡定义为随机
        return new RandomRule();
    }
}
