package com.sky.service;

import com.sky.dto.ShoppingCartDTO;

/**
 * @author ShiRongbao
 * @create 2024/3/5 22:04
 * @description:
 */
public interface ShoppingCartService {


    /**
     * 添加物品到购物车
     * @param shoppingCartDTO 要添加的物品数据
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

}
