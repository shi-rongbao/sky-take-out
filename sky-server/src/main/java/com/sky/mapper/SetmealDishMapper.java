package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/3/2 1:15
 * @description:
 */
@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id来查询套餐id
     * @param dishIds 要查询的所有菜品id
     * @return 返回查询到的所有套餐id
     */
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);
}
