package com.edian.springcloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class CacheTestService {
    public Map<String, String> enties = new HashMap<>();

    @Resource
    private CacheManager cacheManager;

    public CacheTestService() {
        enties.put("id", "cache1");
    }

    @Cacheable(cacheNames = "test")
    public String get(String id) {
        // 记录数据产生的时间，用于测试对比
        long time = new Date().getTime();
        // 打印使用到的cacheManager
        log.error("The cacheManager is：" + cacheManager);
        // 当数据不是从cache里面获取时，打印日志
        log.error("Get value by id=" + id + ", The time is " + time);
        return "Get value by id=" + id + ",the value is：" + enties.get("id");
    }

    @Scheduled(fixedRate = 60 * 1000)
    @CachePut(cacheNames = "test")
    public void time() {
        log.warn("开始更新");
        enties.put("id", "cache2");
        log.warn("更新结束" + enties.get("id"));
    }

}
