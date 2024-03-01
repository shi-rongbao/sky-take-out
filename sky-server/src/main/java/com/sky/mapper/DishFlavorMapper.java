package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.DishFlavor;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
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

    /**
     * 根据dishId删除菜品关联的口味信息
     * @param dishId 被要删除口味相关联的菜品id
     */
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * 根据dishIds批量删除口味信息
     * @param dishIds 被要删除口味相关联的菜品ids
     */
    void deleteByDishIds(List<Long> dishIds);
}
