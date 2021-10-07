package com.express.service;

import com.express.mapper.entity.SendOrder;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface ISendOrderService extends IBaseService<SendOrder>, IPageService<SendOrder>, IOrderService<SendOrder> {
    // 取消订单
    Integer cancelOrderById(Integer orderId);

    // 下单 insert

    // 更改订单信息 update

    /**
     * 获取个订单类型的数量
     *
     * @param customerId
     * @return
     */
    Map<String, Integer> getOrderTypeNum(Integer customerId);


    //region Courier


    /**
     * 获取可接受的订单
     * @param courierId
     * @param isRec
     * @param date
     * @return
     */
    List<?> getCanAcceOrder(Integer courierId, String key, String date);

    // 送单员接单
    Integer recOrderById(Integer orderId, Integer courierId);
    //endregion

}
