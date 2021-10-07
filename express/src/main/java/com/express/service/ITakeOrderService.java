package com.express.service;

import com.express.mapper.entity.TakeOrder;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ITakeOrderService extends IBaseService<TakeOrder>, IPageService<TakeOrder>, IOrderService<TakeOrder> {
    // 取消订单
    Integer cancelOrderById(Integer orderId);

    // 下单 insert
    Integer insertOrder(TakeOrder order);

    // 更改订单信息 update
    Integer updateOrder(TakeOrder order);

    // 获取用户各个订单类型的数量
    Map<String, Integer> getCustomerOrderTypeNum(Integer customerId);


    // Courier

    /**
     * 获取已接受的订单
     *
     * @param pageNo
     * @param pageSize
     * @param courierId 已接受的订单（不等于0）
     * @return
     */
    List<?> getCanAcceOrder(Integer courierId, String key, String date);

    // 送单员接单
    Integer recOrderById(Integer orderId, Integer courierId);

}
