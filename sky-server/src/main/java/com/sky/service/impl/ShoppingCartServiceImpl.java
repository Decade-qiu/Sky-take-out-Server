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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    @Transactional
    public void add(ShoppingCartDTO shoppingCartDTO) {
        Long dishId = shoppingCartDTO.getDishId();
        Long setmealId = shoppingCartDTO.getSetmealId();
        String dishFlavor = shoppingCartDTO.getDishFlavor();

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(BaseContext.getCurrentId())
                .dishId(dishId)
                .setmealId(setmealId)
                .dishFlavor(dishFlavor)
                .build();

        List<ShoppingCart> has = shoppingCartMapper.list(shoppingCart);

        if (has != null && !has.isEmpty()) {
            ShoppingCart exist = has.get(0);
            exist.setNumber(exist.getNumber() + 1);
            shoppingCartMapper.update(exist);
        } else {
            if (dishId != null) {
                Dish cur = dishMapper.selectById(dishId);
                shoppingCart.setCreateTime(LocalDateTime.now());
                shoppingCart.setName(cur.getName());
                shoppingCart.setAmount(cur.getPrice());
                shoppingCart.setImage(cur.getImage());
            } else {
                Setmeal one = setmealMapper.getById(setmealId);
                shoppingCart.setCreateTime(LocalDateTime.now());
                shoppingCart.setName(one.getName());
                shoppingCart.setAmount(one.getPrice());
                shoppingCart.setImage(one.getImage());
            }
            shoppingCart.setNumber(1);
            shoppingCartMapper.insert(shoppingCart);
        }
    }

    @Override
    public List<ShoppingCart> list() {
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(BaseContext.getCurrentId())
                .build();
        return shoppingCartMapper.list(shoppingCart);
    }

    @Override
    @Transactional
    public void delete(ShoppingCartDTO shoppingCartDTO) {
        Long dishId = shoppingCartDTO.getDishId();
        Long setmealId = shoppingCartDTO.getSetmealId();
        String dishFlavor = shoppingCartDTO.getDishFlavor();

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(BaseContext.getCurrentId())
                .dishId(dishId)
                .setmealId(setmealId)
                .dishFlavor(dishFlavor)
                .build();

        List<ShoppingCart> has = shoppingCartMapper.list(shoppingCart);

        if (has != null && !has.isEmpty()) {
            ShoppingCart exist = has.get(0);
            exist.setNumber(exist.getNumber() - 1);
            if (exist.getNumber() == 0) {
                shoppingCartMapper.delete(exist.getId());
            } else {
                shoppingCartMapper.update(exist);
            }
        }else{
            throw new RuntimeException("购物车中无此商品");
        }
    }

    @Override
    public void clean() {
        shoppingCartMapper.deleteAllByUserId(BaseContext.getCurrentId());
    }

}
