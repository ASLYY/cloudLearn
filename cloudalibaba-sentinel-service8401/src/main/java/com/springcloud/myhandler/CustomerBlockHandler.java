package com.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult handleException(BlockException exception) {
        return new CommonResult(4444,"自定义流处理-----1");
    }

    public static CommonResult handleException2(BlockException exception) {
        return new CommonResult(4444,"自定义流处理-----2");
    }
}
