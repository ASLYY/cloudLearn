package com.springcloud.controller;

import com.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystirxController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String paymentHsytrix_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentHsytrix_OK(id);
        return result;
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    public String paymentHsytrix_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentHsytrix_TimeOut(id);
        return result;
    }


}
