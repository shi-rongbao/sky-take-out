package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

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

    /**
     * 查看购物车方法
     * @return 返回购物车集合
     */
    List<ShoppingCart> showShoppingCart();

    /**
     * 清空当前账户下购物车所有数据
     */
    void cleanShoppingCart();

}
