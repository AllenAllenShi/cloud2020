package com.atuguigu.springcloud.controller;

import com.atuguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable("id") Integer id){
        return paymentHystrixService.PaymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "PaymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand
    public String PaymentInfo_TImeOut(@PathVariable("id") Integer id){
        int i=10/0;
        return paymentHystrixService.PaymentInfo_TImeOut(id);
    }

    public String PaymentInfo_TimeOutHandler(Integer id){
        return " 线程池： " + Thread.currentThread().getName ()+ "我是消费者80，对方支付系统繁忙或者自己运行出错，请10秒钟后再试,id：" + id + " \t " + " o(╥﹏╥)o";
    }

    //下面是全局的降级方法（兜底方法)
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试，o(╥﹏╥)o";
    }
}
