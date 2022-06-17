package com.springcloud.service;

import com.springcloud.domain.CommonResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface AccountService {

    @RequestMapping(value = "/account/decrease")
    CommonResult decrease(@RequestParam("userId") Long userId
            , @RequestParam("money") BigDecimal money);
}
