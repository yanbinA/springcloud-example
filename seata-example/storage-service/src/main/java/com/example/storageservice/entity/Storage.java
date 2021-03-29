package com.example.storageservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Primary;

/**
 * @author Depp
 */
@Data
@Accessors(chain = true)
@TableName("storage_tbl")
public class Storage {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String commodityCode;
    private Integer count;
}
