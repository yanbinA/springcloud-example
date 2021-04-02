package com.example.tccstorageservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tccstorageservice.entity.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Depp
 */
@Mapper
@Repository
public interface StorageMapper extends BaseMapper<Storage> {
    @Update("update storage_tbl set count=count - #{count} where id = #{id}")
    void deductById(@Param("id") Integer id, @Param("count") Integer count);
}
