package com.example.tccorderservice.service.impl;

import com.example.tccorderservice.action.TccActionOrder;
import com.example.tccorderservice.entity.Order;
import com.example.tccorderservice.mapper.OrderMapper;
import com.example.tccorderservice.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author Depp
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TccActionOrder tccActionOrder;

    @Override
    @GlobalTransactional
    public void placeOrder(String userId, String commodityCode, Integer count) {
        tccActionOrder.placeOrder(null, userId, commodityCode, count);
        if ("rollback".equals(commodityCode)) {
            throw new RuntimeException();
        }
    }
}
