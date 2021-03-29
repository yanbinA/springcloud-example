package com.example.orderservice.controller;

import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Depp
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("insert")
    public boolean insert(@RequestBody Order order) {
        orderService.placeOrder(order.getUserId(), order.getCommodityCode(), order.getCount());
        return true;
    }

    @RequestMapping("rollback")
    public boolean rollback(@RequestBody Order order) {
        orderService.placeOrder(order.getUserId(), order.getCommodityCode(), order.getCount());
        return true;
    }
}
