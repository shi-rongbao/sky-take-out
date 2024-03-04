package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import java.util.List;

public interface SetmealService {

    /**
     * 新增套餐方法
     * @param setmealDTO 要新增的封装好的套餐对象，包括了菜品信息
     */
    void saveWithDish(SetmealDTO setmealDTO);


    /**
     * 条件查询
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据id查询菜品选项
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);

    /**
     * 套餐分页查询方法
     * @param setmealPageQueryDTO 封装的分页查询信息，包括页码，一页有多少，分类id等等
     * @return 返回PageResult
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 根据id集合批量删除套餐信息
     * @param ids 要批量删除的id
     */
    void deleteBatch(List<Long> ids);

    /**
     * 根据id查询套餐
     * @param id 要查询的套餐id
     * @return 封装好的套餐与菜品对象
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * 修改套餐方法
     * @param setmealDTO 要修改的数据
     */
    void update(SetmealDTO setmealDTO);

    /**
     * 根据路径参数与套餐id修改套餐状态
     * @param status 路径参数，套餐状态
     * @param id 套餐id
     */
    void startOrStop(Integer status, Long id);
}
