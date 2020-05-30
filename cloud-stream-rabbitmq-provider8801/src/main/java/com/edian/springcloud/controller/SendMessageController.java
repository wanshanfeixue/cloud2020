package com.edian.springcloud.controller;

import com.edian.springcloud.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SendMessageController {

    @Resource
    private MessageService provider;

    @GetMapping(value = "/sendMessage")
    public String send(){
        return provider.send();
    }
}
