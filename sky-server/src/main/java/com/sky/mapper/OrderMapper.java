package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import com.sky.vo.OrderStatisticsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {

    void insert(Orders order);

    @Select("select * from orders where number = #{orderNumber} and user_id= #{userId}")
    Orders getByNumberAndUserId(String orderNumber, Long userId);

    void update(Orders orders);

    @Select("select * from orders where id = #{id}")
    Orders getById(Long id);

    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    @Select("select * from orders where status = #{pendingPayment} and order_time < #{time}")
    List<Orders> getByStatusAndOvertime(Integer pendingPayment, LocalDateTime time);
    @Select("select count(id) from orders where status = #{status}")
    Integer statistics(Integer st);
}
