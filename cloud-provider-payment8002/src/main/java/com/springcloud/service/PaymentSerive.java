package com.springcloud.service;

import com.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentSerive {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
