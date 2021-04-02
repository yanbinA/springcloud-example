package com.example.tccstorageservice.action.impl;

import com.example.tccstorageservice.action.TccActionStorage;
import com.example.tccstorageservice.mapper.StorageMapper;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * TCC设计
 * 1. 允许空回滚
 * 2. 防悬挂控制
 *      cancel比try先执行
 * 3. 幂等性
 *
 * @author Depp
 */
@Slf4j
@Component
public class TccActionStorageImpl implements TccActionStorage {
    @Autowired
    private StorageMapper storageMapper;
    private ConcurrentMap<String, String> result = new ConcurrentHashMap<>();

    @Override
    public boolean reduce(BusinessActionContext context, Integer storageId, Integer count) {
        String xid = context.getXid();
        if (result.containsKey(xid)) {
            return true;
        }
        result.put(xid, "R");
        storageMapper.deductById(storageId, count);
        log.info("xid = {}", xid);
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext context) {
        String xid = context.getXid();
        log.info("xid = {} commit", xid);
        result.put(xid, "T");
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext context) {
        String xid = context.getXid();
        if ("R".equals(result.get(xid))) {
            return true;
        }
        storageMapper.deductById((Integer) context.getActionContext("storageId"), - (Integer) context.getActionContext("count"));
        log.error("xid = {} rollback", xid);
        result.put(xid, "F");
        return true;
    }
}
