package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

public interface CategoryService {

    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void addCategory(CategoryDTO categoryDTO);

    void setStatus(Long id, Integer status);

    void deleteCategory(Long id);

    void updateCategory(CategoryDTO categoryDTO);

    List<Category> getByType(String id);
}
