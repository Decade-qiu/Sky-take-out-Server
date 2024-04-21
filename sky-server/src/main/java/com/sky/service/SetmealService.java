package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SetmealService {
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    void save(SetmealDTO setmealDTO);

    Setmeal getById(Long id);

    @Transactional
    void update(SetmealDTO setmealDTO);

    @Transactional
    void deleteBatch(List<Long> ids);

    void updateStatus(Long id, Integer status);

    List<Setmeal> list(Setmeal setmeal);

    List<DishItemVO> getDishItemById(Long id);
}
