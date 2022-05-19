package com.springcloud.service.impl;

import com.springcloud.service.PaymentHystrixService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentHsytrix_OK(Integer id) {
        return "PaymentFallbackService fallback -- paymentHsytrix_OK";
    }

    @Override
    public String paymentHsytrix_TimeOut(Integer id) {
        return "PaymentFallbackService fallback -- paymentHsytrix_TimeOut";
    }
}
