package com.atuguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String PaymentInfo_OK(Integer id) {
        return "PaymentFallbackService fall back- PaymentInfo_OK";
    }

    @Override
    public String PaymentInfo_TImeOut(Integer id) {
        return "PaymentFallbackService fall back- PaymentInfo_TImeOut";
    }
}
