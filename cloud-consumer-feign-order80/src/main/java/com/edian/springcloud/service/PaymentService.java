package com.edian.springcloud.service;

import com.edian.springcloud.entities.CommonResult;
import com.edian.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-payment-service")
public interface PaymentService {

    @GetMapping(value = "/payment/getPaymentById/{id}")
     CommonResult<Payment> getPaymentById(@PathVariable("id") long id);

    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout();
}
