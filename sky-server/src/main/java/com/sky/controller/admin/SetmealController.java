package com.sky.controller.admin;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController("adminSetmealController")
@RequestMapping("/admin/setmeal")
@Api(tags = "套餐管理")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    @GetMapping("/page")
    @ApiOperation("分页查询套餐")
    public Result page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping()
    @ApiOperation("新增套餐")
    @CacheEvict(cacheNames = "setmeal", key = "#setmealDTO.categoryId")
    public Result save(@RequestBody SetmealDTO setmealDTO) {
        setmealService.save(setmealDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<Setmeal> getById(@PathVariable Long id) {
        Setmeal setmeal = setmealService.getById(id);
        return Result.success(setmeal);
    }

    @PutMapping()
    @ApiOperation("更新套餐")
    @CacheEvict(cacheNames = "setmeal", allEntries = true)
    public Result update(@RequestBody SetmealDTO setmealDTO) {
        setmealService.update(setmealDTO);
        return Result.success();
    }

    @DeleteMapping()
    @ApiOperation("删除套餐")
    @CacheEvict(cacheNames = "setmeal", allEntries = true)
    public Result delete(@RequestParam String ids) {
        String[] idArr = ids.split(",");
        List<Long> idsList = new ArrayList<>();
        for (String id : idArr) {
            idsList.add(Long.parseLong(id));
        }
        setmealService.deleteBatch(idsList);
        return Result.success();
    }

    // @DeleteMapping()
    // @ApiOperation("删除套餐")
    // public Result delete(@RequestParam List<Long> ids) {
    //     setmealService.deleteBatch(idsList);
    //     return Result.success();
    // }

    @PostMapping("/status/{status}")
    @ApiOperation("更新套餐状态")
    @CacheEvict(cacheNames = "setmeal", allEntries = true)
    public Result updateStatus(@RequestParam Long id, @PathVariable Integer status) {
        setmealService.updateStatus(id, status);
        return Result.success();
    }
}
