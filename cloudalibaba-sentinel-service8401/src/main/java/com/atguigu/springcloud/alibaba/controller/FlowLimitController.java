package com.atguigu.springcloud.alibaba.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
public class FlowLimitController
{
    @GetMapping("/testA")
    public String testA() {

        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {

        return "------testB";
    }



}


