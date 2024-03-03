package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;

/**
 * @author ShiRongbao
 * @create 2024/3/3 21:56
 * @description:
 */
public interface UserService {


    /**
     * 微信用户登录
     * @param userLoginDTO 向微信服务器发送请求调用微信登录API所要传的code
     * @return 返回封装好的User对象
     */
    User wxLogin(UserLoginDTO userLoginDTO);

}
