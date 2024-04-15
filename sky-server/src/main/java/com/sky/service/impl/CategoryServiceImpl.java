package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.StatusConstant;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());

        Page<Category> res = categoryMapper.pageQuery(categoryPageQueryDTO.getName(), categoryPageQueryDTO.getType());

        return new PageResult(res.getTotal(), res.getResult());

    }

    @Override
    public void addCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setType(categoryDTO.getType());
        category.setSort(categoryDTO.getSort());
        category.setStatus(StatusConstant.ENABLE);
        // LocalDateTime now = LocalDateTime.now();
        // category.setCreateTime(now);
        // category.setUpdateTime(now);
        // category.setCreateUser(BaseContext.getCurrentId());
        // category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.insert(category);
    }

    @Override
    public void setStatus(Long id, Integer status) {
        Category category = new Category();
        category.setId(id);
        category.setStatus(status);
        // category.setUpdateTime(LocalDateTime.now());
        // category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.update(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryMapper.deleteById(id);
    }

    @Override
    public void updateCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setType(categoryDTO.getType());
        category.setSort(categoryDTO.getSort());
        // category.setUpdateTime(LocalDateTime.now());
        // category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.update(category);
    }

    @Override
    public List<Category> getByType(String id) {
        return categoryMapper.selectById(id);
    }
}
