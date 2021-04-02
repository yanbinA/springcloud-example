package com.example.tccstorageservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.tccstorageservice.action.TccActionStorage;
import com.example.tccstorageservice.entity.Storage;
import com.example.tccstorageservice.mapper.StorageMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Depp
 */
@Service
public class StorageService {
    @Autowired
    private TccActionStorage tccActionStorage;
    @Autowired
    private StorageMapper storageMapper;

    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void deduct(String commodityCode, Integer count) {
        QueryWrapper<Storage> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Storage().setCommodityCode(commodityCode));
        Storage storage = this.storageMapper.selectOne(wrapper);
        tccActionStorage.reduce(null, storage.getId(), count);
        if ("callback".equals(commodityCode)) {
            throw new RuntimeException("ERROR : deduct fail...");
        }
    }

}
