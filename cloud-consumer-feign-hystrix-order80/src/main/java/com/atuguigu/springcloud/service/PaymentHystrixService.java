package com.atuguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT")
public interface PaymentHystrixService {
    @GetMapping("/payment/hystix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable Integer id);

    @GetMapping("/payment/hystix/timeout/{id}")
    public String PaymentInfo_TImeOut(@PathVariable Integer id);
}
