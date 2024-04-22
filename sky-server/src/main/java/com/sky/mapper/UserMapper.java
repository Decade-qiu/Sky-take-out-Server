package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.sky.entity.User;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    @Select("select * from user where id = #{id}")
    User getById(Long id);

    void insert(User user);
}
