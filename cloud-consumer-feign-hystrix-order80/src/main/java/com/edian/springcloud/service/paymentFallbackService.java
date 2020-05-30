package com.edian.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class paymentFallbackService implements PaymentService{
    @Override
    public String paymentInfo_Ok(Integer id) {
        return "paymentFallbackService fallback paymentInfo_Ok,(ㄒoㄒ)";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "paymentFallbackService fallback paymentInfo_TimeOut,(ㄒoㄒ)";
    }
}
