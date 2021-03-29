package com.example.storageservice.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.storageservice.entity.Storage;
import com.example.storageservice.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Depp
 */
@Service
public class StorageService {
    @Autowired
    private StorageMapper storageMapper;

    @Transactional(rollbackFor = Exception.class)
    public void deduct(String commodityCode, Integer count) {
        QueryWrapper<Storage> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new Storage().setCommodityCode(commodityCode));
        Storage storage = this.storageMapper.selectOne(wrapper);
        storage.setCount(storage.getCount() - count);
        storageMapper.updateById(storage);
        if ("callback".equals(commodityCode)) {
            throw new RuntimeException("ERROR : deduct fail...");
        }
    }

}
