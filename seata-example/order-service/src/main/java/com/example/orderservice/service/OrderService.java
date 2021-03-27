package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.mapper.OrderMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author Depp
 */
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Transactional(rollbackFor = Exception.class)
    @GlobalTransactional
    public void placeOrder(String userId, String commodityCode, Integer count) {
        Order order = new Order()
                .setUserId(userId)
                .setCommodityCode(commodityCode)
                .setCount(count)
                .setMoney(BigDecimal.valueOf(10));
        orderMapper.insert(order);
    }
}
