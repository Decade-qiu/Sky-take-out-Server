package com.sky.controller.admin;


import com.github.pagehelper.PageHelper;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
@Api(tags = "分类")
@Slf4j
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping
    @ApiOperation("新增分类")
    public Result addCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("设置状态")
    public Result setStatus(@RequestParam Long id, @PathVariable Integer status) {
        categoryService.setStatus(id, status);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("删除分类")
    public Result deleteCategory(@RequestParam Long id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }

    @PutMapping
    @ApiOperation("更新分类")
    public Result updateCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("根据类型查询分类")
    public Result list(@RequestParam String type) {
        return Result.success(categoryService.getByType(type));
    }
}
