package com.sky.service;

import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/3/1 23:30
 * @description:
 */
public interface DishService {

    /**
     * 新增菜品和对应的口味
     * @param dishDTO 菜品所封装成的对象
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO 菜品分页查询所需要的参数
     * @return 返回PageResult
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 菜品批量删除
     * @param ids 要删除的菜品的id  List集合存储
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据菜品id来查询对应的菜品和口味
     * @param id 要查询的菜品
     * @return 返回查询到的数据并封装成对应的VO对象
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * 修改菜品数据
     * @param dishDTO 要修改的菜品的数据
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * 体检查询菜品和口味
     * @param dish 要查询的菜品信息
     * @return 返回菜品口味信息
     */
    List<DishVO> listWithFlavor(Dish dish);

    /**
     * 根据categoryId查询菜品
     * @param categoryId 分类id
     * @return 返回该分类下的所有菜品对象
     */
    List<Dish> list(Long categoryId);

    /**
     * 设置菜品的起售停售
     * @param status 状态码
     * @param id 当前菜品id
     */
    void startOrStop(Integer status, Long id);
}
