package com.sky.service;

import com.sky.dto.DishDTO;
import org.springframework.stereotype.Service;

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
}
