package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/3/6 13:14
 * @description:
 */
@Mapper
public interface OrderDetailMapper {
    /**
     * 批量插入订单详情表
     * @param orderDetails 订单详情集合
     */
    void insertBatch(List<OrderDetail> orderDetails);
}
