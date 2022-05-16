package com.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentHystrix_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + ",paymentHystrix_OK，id: " + id + "\t O(∩_∩)O";
    }

    public String paymentHystrix_TimeOut(Integer id) {
        int timeout = 2;
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        return "线程池：" + Thread.currentThread().getName() + ",paymentHystrix_TimeOut，id: " + id + "\t" + " /(ㄒoㄒ)/~~,耗时(s):"  + timeout;
    }

}
