package com.example.tccorderservice.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author Depp
 */
public interface OrderService {
    void placeOrder(String userId, String commodityCode, Integer count);
}
