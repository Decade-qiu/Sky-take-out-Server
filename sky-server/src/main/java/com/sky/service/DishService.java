package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    void save(DishDTO dish);

    void deleteBatch(List<Long> ids);

    DishVO getById(Long id);

    void updateWithFlavor(DishDTO dishDTO);

    List<Dish> selectByCategoryId(Long categoryId);

    void updateStatus(Long id, Integer status);

    List<DishVO> listWithFlavor(Dish dish);
}
