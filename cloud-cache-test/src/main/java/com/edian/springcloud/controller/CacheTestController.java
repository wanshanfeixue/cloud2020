package com.edian.springcloud.controller;

import com.edian.springcloud.service.CacheTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CacheTestController {

    @Resource
    private CacheTestService cacheTestService;

    @GetMapping("/get/{id}")
    public String test(@PathVariable("id") String id){
        return cacheTestService.get(id);
    }

}
