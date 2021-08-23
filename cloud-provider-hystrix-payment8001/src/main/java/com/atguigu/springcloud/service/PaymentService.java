package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String PaymentInfo_OK(Integer id){
        return " 线程池： " + Thread . currentThread (). getName ()+ "paymentInfo_OK,id ：  " + id + " \t " + " 哈哈哈 ";
    }
    @HystrixCommand(fallbackMethod = "PaymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String PaymentInfo_TimeOut(Integer id)
    {
        try
        {
            TimeUnit.MILLISECONDS.sleep(3000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return " 线程池： " + Thread.currentThread().getName ()+ "paymentInfo_Timeout,id：" + id + " \t " + " 哈哈哈 ";
    }


    public String PaymentInfo_TimeOutHandler(Integer id){
        return " 线程池： " + Thread.currentThread().getName ()+ "8001系统繁忙，请稍后再试,id：" + id + " \t " + " o(╥﹏╥)o";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//一个统计窗口期内处理的请求数量达到这个阈值，才会进行是否熔断的判断
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//打开断路器后，到尝试处理请求并决定继续打开或关闭断路器的睡眠时间
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60") //失败率达到多久后的跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("**********id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号： " + serialNumber;
    }

    private String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数，请稍后再试，o(╥﹏╥)o id: " + id;
    }
}
