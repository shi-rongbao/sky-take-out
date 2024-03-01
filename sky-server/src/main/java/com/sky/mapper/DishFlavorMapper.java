package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/3/2 0:09
 * @description:
 */
@Mapper
public interface DishFlavorMapper {

    /**
     * 批量插入菜品口味数据方法
     * @param flavors 装有所有口味的List集合
     */
    void insertBatch(List<DishFlavor> flavors);
}
