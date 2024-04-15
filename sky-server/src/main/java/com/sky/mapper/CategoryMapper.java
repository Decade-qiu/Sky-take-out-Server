package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface CategoryMapper {

    Page<Category> pageQuery(String name, Integer type);

    @Insert(
            "insert into category(name, type, sort, status, create_time, update_time, create_user, update_user) " +
                    "values(#{name}, #{type}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})"
    )
    @AutoFill(OperationType.INSERT)
    void insert(Category category);

    @AutoFill(OperationType.UPDATE)
    void update(Category category);

    @Delete(
            "delete from category where id = #{id}"
    )
    void deleteById(Long id);

    @Select(
            "select * from category where type = #{type}"
    )
    List<Category> selectById(String type);
}
