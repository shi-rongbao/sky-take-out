package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据ordersPageQueryDTO查询分页数据
     * @param ordersPageQueryDTO ordersPageQueryDTO
     * @return 返回订单分页数据
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 根据订单id查询订单
     * @param id 订单id
     * @return 返回订单对象
     */
    @Select("select * from orders where id = #{id}")
    Orders getById(Long id);

    /**
     *
     * @param toBeConfirmed
     * @return
     */
    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer toBeConfirmed);

    /**
     * 查询orders表中，未付款且超过15分钟的订单集合
     * @param status 订单状态
     * @param orderTime 下单时间
     * @return 返回订单数据集合
     */
    @Select("select * from orders where status = #{status} and order_time = #{orderTime}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);

    /**
     * 根据动态条件统计营业额数据
     * @param map 动态条件
     * @return 返回营业额
     */
    Double sumByMap(Map<String, Object> map);


    /**
     * 根据动态条件查询订单数
     * @param map 动态条件
     * @return 返回订单数
     */
    Integer countByMap(Map<String, Object> map);


    /**
     * 统计指定时间区间内的销量前10
     * @param begin 开始日期
     * @param end 结束日期
     * @return 返回DTO对象
     */
    List<GoodsSalesDTO> getSalesTop10(LocalDateTime begin, LocalDateTime end);
}
