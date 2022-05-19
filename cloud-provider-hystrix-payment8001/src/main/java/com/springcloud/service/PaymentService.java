package com.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    public String paymentHystrix_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + ",paymentHystrix_OK，id: " + id + "\t O(∩_∩)O";
    }

    @HystrixCommand(fallbackMethod = "paymentHystrix_TimeOut_Handler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")})
    public String paymentHystrix_TimeOut(Integer id) {
        int timeout = 500;
//        int a = 10 / 0;
        try { TimeUnit.MILLISECONDS.sleep(timeout); } catch (InterruptedException e) { e.printStackTrace(); }
        return "线程池：" + Thread.currentThread().getName() + ",paymentHystrix_TimeOut，id: " + id + "\t" + " O(∩_∩)O~~,耗时(s):"  + timeout;
//        return "线程池：" + Thread.currentThread().getName() + ",paymentHystrix_TimeOut，id: " + id + "\t" + " O(∩_∩)O";
    }

    public String paymentHystrix_TimeOut_Handler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + ",paymentHystrix_TimeOut_Handler，id: " + id + "\t" + " ┭┮﹏┭┮" ;
    }

//    ===========服务熔断===========
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//触发熔断的最小请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//ms,时间窗口期 如果超过了10才慢慢开始恢复 首先会进行半开放 尝试请求 如果请求依旧是失败的 断路器打开
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少百分比后熔断
//          上述参数：指在特定窗口期内，至少达到10次调用，并且失败率达到60%才会熔断
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }

}
