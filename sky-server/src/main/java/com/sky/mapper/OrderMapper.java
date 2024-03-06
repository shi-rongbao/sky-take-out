package com.sky.mapper;

import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 根据订单号查询订单
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);
}
