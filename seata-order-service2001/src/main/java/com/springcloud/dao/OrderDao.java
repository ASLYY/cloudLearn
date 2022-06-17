package com.springcloud.dao;

import com.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface OrderDao{
    /**
     * 新建订单
     */
    void create(Order order);

    /**
     * 修改订单
     */

    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
