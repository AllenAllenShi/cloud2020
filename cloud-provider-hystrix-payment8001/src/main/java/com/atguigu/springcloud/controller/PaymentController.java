package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable Integer id){

        String result = paymentService.PaymentInfo_OK(id);
        log.info("**********result: " + result);
        return result;
    }

    @GetMapping("/payment/hystix/timeout/{id}")
    public String PaymentInfo_TImeOut(@PathVariable Integer id){

        String result = paymentService.PaymentInfo_TimeOut(id);
        log.info("**********result: " + result);
        return result;
    }
}
