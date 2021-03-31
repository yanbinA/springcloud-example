package com.example.tccorderservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.tccorderservice.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Depp
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {
}
