package com.express.service;

import com.express.mapper.entity.DeliveryPoint;
import com.express.mapper.entity.DeliveryType;
import com.express.mapper.entity.TakeTime;

import java.util.List;

public interface IOrderService<T> extends IBaseService<T> {

    List<?> getList(String type);

    Integer addTakeTime(TakeTime entity);

    Integer addPoint(DeliveryPoint entity);

    Integer addDeliveryType(DeliveryType entity);

    Integer delTakeTime(Integer id);

    Integer delPoint(Integer id);

    Integer delDeliveryType(Integer id);

    Integer updateTakeTime(TakeTime entity);

    Integer updatePoint(DeliveryPoint entity);

    Integer updateDeliveryType(DeliveryType entity);

    boolean isExist(String type, String name);

    List<?> getOrderByAdmin(String date,String key);
}
