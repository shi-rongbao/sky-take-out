package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.ShoppingCart;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.mapper.ShoppingCartMapper;
import com.sky.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ShiRongbao
 * @create 2024/3/5 22:05
 * @description:
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public void addShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        // 判断当前加入到购物车的商品是否已经存在
        ShoppingCart shoppingCart = new ShoppingCart();
        BeanUtils.copyProperties(shoppingCartDTO, shoppingCart);
        // 每次登录时会校验token，token可以解析出来当前用户id，并放入ThreadLocal中，这里只需要获取就可以
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);
        // 以上将购物车基本信息已经设置好了
        List<ShoppingCart> list = shoppingCartMapper.list(shoppingCart);
        // 如果已经存在了，只需要将数量增加1
        if (list != null && list.size() > 0) {
            // 查到了购物车的数据，这里根据dish_id或者setmeal_id与user_id查询，集合中只可能出现0条数据或一条数据
            // 拿到当前购物车数据，并修改数量加一即可
            ShoppingCart cart = list.get(0);
            cart.setNumber(cart.getNumber() + 1);
            // 然后更新该数据到数据库中
            shoppingCartMapper.updateNumberById(cart);
        } else {
            // 如果不存在，需要插入一条购物车数据
            // 首先判断本次添加到购物车的是菜品还是套餐
            Long dishId = shoppingCartDTO.getDishId();
            if (dishId != null) {
                // 本次添加到购物车的是菜品
                Dish dish = dishMapper.getById(dishId);  // 从菜品表中查到当前菜品数据
                shoppingCart.setName(dish.getName());
                shoppingCart.setAmount(dish.getPrice());
                shoppingCart.setImage(dish.getImage());
            } else {
                // 本次添加到购物车的是套餐
                Long setmealId = shoppingCartDTO.getSetmealId();
                Setmeal setmeal = setmealMapper.getById(setmealId);  // 从套餐表中查到当前套餐数据
                shoppingCart.setName(setmeal.getName());
                shoppingCart.setAmount(setmeal.getPrice());
                shoppingCart.setImage(setmeal.getImage());
            }
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            // 调用insert方法将该购物车插入到数据库中
            shoppingCartMapper.insert(shoppingCart);
        }
    }

    @Override
    public List<ShoppingCart> showShoppingCart() {
        // 获取当前微信用户id
        Long userId = BaseContext.getCurrentId();
        ShoppingCart cart = ShoppingCart.builder().userId(userId).build();
        List<ShoppingCart> carts = shoppingCartMapper.list(cart);
        return carts;
    }

    @Override
    public void cleanShoppingCart() {
        // 获取当前微信用户id
        Long userId = BaseContext.getCurrentId();
        shoppingCartMapper.deleteByUserId(userId);
    }
}
