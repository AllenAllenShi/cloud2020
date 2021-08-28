package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.OrderDao;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;
    @Override
    @GlobalTransactional(name = "fsp_tx_group",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("开始创建订单start");

        orderDao.create(order);
        log.info("减库存start");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("减库存end");
        log.info("扣款start");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("扣款end");

        log.info("该状态start");
        orderDao.update(order.getUserId(),0);
        log.info("改状态end");
        log.info("开始创建订单end");
    }
}
