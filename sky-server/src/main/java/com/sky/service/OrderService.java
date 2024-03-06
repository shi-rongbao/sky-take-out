package com.sky.service;

import com.sky.dto.OrdersSubmitDTO;
import com.sky.vo.OrderSubmitVO;
import org.springframework.stereotype.Service;

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
}
