package com.springcloud.service.impl;

import com.springcloud.dao.OrderDao;
import com.springcloud.domain.Order;
import com.springcloud.service.AccountService;
import com.springcloud.service.OrderService;
import com.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("--创建订单--");
        orderDao.create(order);
        log.info("订单微服务开始调用库存  做库存删减");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("------->order-service中扣减库存结束");
        log.info("订单微服务开始调用账户  做账户金额删减");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("------->order-service中扣减账户余额结束");

        /*修改订单状态 1代表已完成*/
        log.info("--修改订单开始--");
        orderDao.update(order.getUserId(), 0);
        log.info("--修改订单结束--");

        log.info("下单结束");
    }

}
