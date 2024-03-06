package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ShiRongbao
 * @create 2024/3/6 13:13
 * @description:
 */
@Mapper
public interface OrderMapper {
    /**
     * 根据封装的订单数据往订单表中插入订单数据
     * @param orders 封装的订单数据
     */
    void insert(Orders orders);
}
