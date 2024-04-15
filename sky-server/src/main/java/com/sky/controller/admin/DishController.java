package com.sky.controller.admin;

import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageResult res = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(res);
    }

}
