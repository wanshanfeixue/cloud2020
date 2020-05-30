package com.edian.springcloud.controller;

import com.edian.springcloud.entities.CommonResult;
import com.edian.springcloud.entities.Payment;
import com.edian.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description: PaymentController 类（或接口）是
 * @Author: wu
 * @Date: 2020/4/16 13:42
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        log.info("开始插入数据");
        int result = paymentService.create(payment);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort" + serverPort, result);
        } else {
            return new CommonResult(400, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") long id) {
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort" + serverPort, payment);
        } else {
            return new CommonResult(400, "没有对应的记录，id为：" + id, null);
        }
    }

    @GetMapping("/payment/getBalancer")
    public String getPaymentBalancer() {
        return serverPort;
    }
}
