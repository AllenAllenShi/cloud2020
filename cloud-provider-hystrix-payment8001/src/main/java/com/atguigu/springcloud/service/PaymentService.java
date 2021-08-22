package com.atguigu.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String PaymentInfo_OK(Integer id){
        return " 线程池： " + Thread . currentThread (). getName ()+ "paymentInfo_OK,id ：  " + id + " \t " + " 哈哈哈 ";
    }

    public String PaymentInfo_TimeOut(Integer id)
    {
        int timeNumber =3;
        try
        {
            TimeUnit.SECONDS.sleep(timeNumber);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return " 线 程池： " + Thread.currentThread().getName ()+ "paymentInfo_Timeout,id：" + id + " \t " + " 哈哈哈 ";
    }
}
