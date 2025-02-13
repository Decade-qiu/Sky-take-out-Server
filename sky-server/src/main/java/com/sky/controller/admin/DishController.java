package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController("adminDishController")
@RequestMapping("/admin/dish")
@Api(tags = "菜品")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    private void cleanCache(String pattern){
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }


    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageResult res = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(res);
    }

    @PostMapping()
    @ApiOperation("新增")
    public Result save(@RequestBody DishDTO dishDTO) {
        dishService.save(dishDTO);
        cleanCache("dish_" + dishDTO.getCategoryId());
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("菜品批量删除")
    public Result delete(@RequestParam List<Long> ids) {
        dishService.deleteBatch(ids);//后绪步骤实现
        cleanCache("dish_*");
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询")
    public Result getById(@PathVariable Long id) {
        DishVO dishVO = dishService.getById(id);//后绪步骤实现
        return Result.success(dishVO);
    }

    @PutMapping
    @ApiOperation("修改菜品")
    public Result update(@RequestBody DishDTO dishDTO) {
        dishService.updateWithFlavor(dishDTO);
        cleanCache("dish_*");
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("根据分类id查询")
    public Result selectByCategoryId(Long categoryId) {
        List<Dish> dishes = dishService.selectByCategoryId(categoryId);
        return Result.success(dishes);
    }

    @PostMapping("/status/{status}")
    @ApiOperation("修改菜品状态")
    public Result updateStatus(@RequestParam Long id, @PathVariable Integer status) {
        dishService.updateStatus(id, status);
        cleanCache("dish_*");
        return Result.success();
    }

}
