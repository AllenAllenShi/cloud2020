package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
    @GetMapping("/payment/hystix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystix/timeout/{id}")
    public String PaymentInfo_TImeOut(@PathVariable("id") Integer id);
}
