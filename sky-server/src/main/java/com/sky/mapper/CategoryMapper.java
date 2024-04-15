package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {

    Page<Category> pageQuery(String name, Integer type);

    @Insert(
            "insert into category(name, type, sort, status, create_time, update_time, create_user, update_user) " +
                    "values(#{name}, #{type}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})"
    )
    void insert(Category category);

    void update(Category category);

    @Delete(
            "delete from category where id = #{id}"
    )
    void deleteById(Long id);
}
