package com.example.tccstorageservice.action;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * @author Depp
 */
@LocalTCC
public interface TccActionStorage {
    @TwoPhaseBusinessAction(name = "storage-reduce", commitMethod = "commit", rollbackMethod = "rollback")
    boolean reduce(BusinessActionContext context, @BusinessActionContextParameter(paramName = "storageId") Integer storageId,
                          @BusinessActionContextParameter(paramName = "count") Integer count);

    boolean commit(BusinessActionContext context);

    boolean rollback(BusinessActionContext context);
}
