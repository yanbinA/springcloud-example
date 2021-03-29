package com.example.storageservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.storageservice.entity.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Depp
 */
@Mapper
@Repository
public interface StorageMapper extends BaseMapper<Storage> {
}
