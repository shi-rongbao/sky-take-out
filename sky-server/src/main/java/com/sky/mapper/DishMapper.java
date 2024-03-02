package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 插入菜品数据
     * @param dish 封装好的菜品对象
     */
    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 根据id查询菜品信息
     * @param id 要查询的菜品id
     * @return 返回封装好的菜品对象
     */
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 根据菜品id删除菜品信息
     * @param id 要删除的菜品id
     */
    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据ids批量删除菜品信息
     * @param ids 要批量删除的菜品的id
     */
    void deleteByIds(List<Long> ids);

    /**
     * 菜品分页查询
     * @param dishPageQueryDTO 菜品分页查询所需要的参数
     * @return 返回Page集合
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 将传入的dish对象修改为新的
     * @param dish 要修改为的dish数据
     */
    @AutoFill(OperationType.UPDATE)  // 自动填充公共字段 UPDATE
    void update(Dish dish);
}
