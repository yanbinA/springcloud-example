package com.example.tccorderservice.action.impl;

import com.example.tccorderservice.action.TccActionOrder;
import com.example.tccorderservice.entity.Order;
import com.example.tccorderservice.mapper.OrderMapper;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author Depp
 */
@Slf4j
@Component
public class TccActionOrderImpl implements TccActionOrder {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    //@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean placeOrder(BusinessActionContext context, String userId, String commodityCode, Integer count) {
        Order order = new Order().setUserId(userId)
                .setCommodityCode(commodityCode)
                .setCount(count)
                .setMoney(BigDecimal.TEN);
        orderMapper.insert(order);
        log.info("xid = {} >>> order = {}", RootContext.getXID(), order);
        //无效的， 只执行try方法之前已经吧BusinessActionContext的属性做了序列化存储，
        // try方法改变BusinessActionContext的值是无效的
        // 调用commit和rollback时反序列化得到BusinessActionContext
        context.getActionContext().put("id", order.getId());
        if ("rollback".equals(commodityCode)) {
            throw new RuntimeException();
        }
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext context) {
        log.debug("xid = {} commit", RootContext.getXID());
        log.debug("context param = {}", context.getActionContext());
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext context) {
        log.debug("xid = {} rollback", RootContext.getXID());
        log.debug("context param = {}", context.getActionContext());
        String xid = context.getXid();
        if (StringUtils.isBlank(xid)) {
            return true;
        }
        return true;
    }
}
