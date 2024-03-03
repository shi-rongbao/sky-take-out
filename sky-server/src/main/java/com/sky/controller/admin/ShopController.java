package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author ShiRongbao
 * @create 2024/3/2 16:43
 * @description:
 */
@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关接口")
@Slf4j
public class ShopController {

    public static final String KEY = "SHOP_STATUS";


    @Autowired
    RedisTemplate redisTemplate;

    @PutMapping("/{status}")
    @ApiOperation("设置店铺的营业状态方法")
    public Result setStatus(@PathVariable Integer status) {
        log.info("设置店铺的营业状态：{}", status == 1 ? "营业中" : "打样中");
        redisTemplate.opsForValue().set(KEY,  status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation("获取当前店铺的营业状态")
    public Result<Integer> getStatus(){
        Integer status = (Integer) redisTemplate.opsForValue().get(KEY);
        log.info("获得当前status：{}", status == 1 ? "营业中" : "打样中");
        return Result.success(status);
    }
}

