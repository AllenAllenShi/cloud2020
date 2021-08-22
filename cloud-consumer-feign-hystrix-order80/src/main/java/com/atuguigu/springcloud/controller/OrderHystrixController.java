package com.atuguigu.springcloud.controller;

import com.atuguigu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable Integer id){
        return paymentHystrixService.PaymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystix/timeout/{id}")
    public String PaymentInfo_TImeOut(@PathVariable Integer id){
        return paymentHystrixService.PaymentInfo_TImeOut(id);
    }
}
