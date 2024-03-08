package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

/**
 * @author ShiRongbao
 * @create 2024/3/6 13:08
 * @description:
 */
public interface OrderService {

    /**
     * 用户下单方法
     * @param ordersSubmitDTO 下单时提供的一些参数
     * @return 返回VO对象
     */
    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);


    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功，修改订单状态
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    /**
     * 根据参数查询历史订单
     * @param page 页数
     * @param pageSize 一页有几条数据
     * @param status 订单状态
     * @return 返回PageResult对象
     */
    PageResult pageQuery4User(Integer page, Integer pageSize, Integer status);

    /**
     * 根据订单id查询订单详情
     * @param id 订单id
     * @return 返回OrderVO对象
     */
    OrderVO details(Long id);

    /**
     * 根据用户id取消订单
     * @param id 用户id
     */
    void userCancelById(Long id) throws Exception;

    /**
     * 根据订单id再来一单
     * @param id 订单id
     */
    void repetition(Long id);

    /**
     * 根据参数分页查询订单
     * @param ordersPageQueryDTO 参数
     * @return 返回PageResult对象
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 查询各个状态的订单数量统计
     * @return 返回OrderStatisticsVO
     */
    OrderStatisticsVO statistics();

    /**
     * 商家接单，也就是把订单状态修改为已接单
     * @param ordersConfirmDTO 订单配置
     */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 商家拒单，把订单状态修改为拒单,如果用户已付款，需要退款
     * @param ordersRejectionDTO 订单拒绝配置
     */
    void rejection(OrdersRejectionDTO ordersRejectionDTO) throws Exception;

    /**
     * 商家取消订单
     * @param ordersCancelDTO 取消订单DTO
     */
    void cancel(OrdersCancelDTO ordersCancelDTO) throws Exception;

    /**
     * 派送订单，修改订单状态
     * @param id 订单id
     */
    void delivery(Long id);

    /**
     * 完成订单，修改订单状态
     * @param id 订单id
     */
    void complete(Long id);

    /**
     * 客户催单方法
     * @param id 订单id
     */
    void reminder(Long id);
}
