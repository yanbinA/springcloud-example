package com.example.tccorderservice.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author Depp
 */
@LocalTCC
public interface TccActionOrder {
    /**
     * 定义order-insert两阶段提交
     * `@TwoPhaseBusinessAction` 该注解放在`@LocalTCC`的try方法上
     * name TCC bean name, must be unique
     * commitMethod 指定二阶段的确认方法
     * rollbackMethod 指定二阶段的取消方法
     * `@BusinessActionContextParameter` 将参数传递到二阶段
     * @param userId userId
     * @param commodityCode commodityCode
     * @param count count
     */
    @TwoPhaseBusinessAction(name = "order-insert", commitMethod = "commit", rollbackMethod = "rollback")
    boolean placeOrder(BusinessActionContext context, @BusinessActionContextParameter(paramName = "userId") String userId, @BusinessActionContextParameter(paramName = "commodityCode") String commodityCode,
                    @BusinessActionContextParameter(paramName = "count") Integer count);

    /**
     * TCC 二阶段确认方法
     * 方法名可自定义， 但要与try方法上commitMethod一致
     * @param context   try方法传递的参数
     */
    boolean commit(BusinessActionContext context);

    /**
     * TCC 二阶段取消方法
     * @param context   try方法传递的参数
     */
    boolean rollback(BusinessActionContext context);
}
