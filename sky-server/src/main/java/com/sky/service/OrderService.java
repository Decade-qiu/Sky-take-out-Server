package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

public interface OrderService {
    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);

    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    void paySuccess(String outTradeNo);

    PageResult pageQueryUser(OrdersPageQueryDTO ordersPageQueryDTO);

    OrderVO getById(Long id);

    void userCancelById(Long id) throws Exception;

    void repetition(Long id);

    void reminder(Long id);

    OrderStatisticsVO statistics();

    void confirm(Long id);

    void rejection(OrdersRejectionDTO ordersRejectionDTO);

    void delivery(Long id);

    void complete(Long id);

    void cancel(OrdersCancelDTO ordersCancelDTO);
}
