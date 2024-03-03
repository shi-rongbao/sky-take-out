package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShiRongbao
 * @create 2024/3/3 22:09
 * @description:
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        // 调用微信接口服务，获得当前微信用户的openid
        String openid = getOpenId(userLoginDTO.getCode());
        // 判断openid是否为空，如果为空表示登录失败，抛出业务异常
        if (openid == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }
        // 判断当前用户是否为新用户  调用mapper，单表查询
        User user = userMapper.getByOpenid(openid);
        // 如果是新用户，自动完成注册
        if (user == null) {
            user = User.builder().openid(openid).createTime(LocalDateTime.now()).build();
            // 将该用户存入数据库中
            userMapper.insert(user);
        }
        // 返回这个用户对象
        return user;
    }

    /**
     * 调用微信接口服务，获取微信用户的openid
     * @param code 每次登录时的动态code
     * @return 当前用户的openid
     */
    private String getOpenId(String code) {
        Map<String, String> map = new HashMap<>();
        // 传递的四个参数
        map.put("appid", weChatProperties.getAppid());
        map.put("secret", weChatProperties.getSecret());
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        // 使用工具类   HttpClient发送GET请求
        String json = HttpClientUtil.doGet(WX_LOGIN, map);
        // 将结果转换为JSON格式
        JSONObject jsonObject = JSON.parseObject(json);
        // 返回需要的openid
        return jsonObject.getString("openid");
    }
}
