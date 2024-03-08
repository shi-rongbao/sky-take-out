package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @author ShiRongbao
 * @create 2024/3/3 22:23
 * @description:
 */
@Mapper
public interface UserMapper {

    /**
     * 根据openid查询是否有改用户
     * @param openid openid
     * @return 返回查到的用户对象
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * 将用户信息存入数据库中
     * @param user 要存入的用户信息
     */
    void insert(User user);

    @Select("select * from user where id = #{id}")
    User getById(Long userId);


    /**
     * 根据动态条件统计用户数量
     * @param map 动态条件
     * @return 返回用户数量
     */
    Integer countByMap(Map map);
}
