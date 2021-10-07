package com.express.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.express.common.DateHelper;
import com.express.mapper.IDeliveryPointMapper;
import com.express.mapper.IDeliveryTypeMapper;
import com.express.mapper.ISendOrderMapper;
import com.express.mapper.ITakeTimeMapper;
import com.express.mapper.entity.*;
import com.express.service.IOrderService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class OrderServiceImpl<T> extends BaseServerImpl<T> implements IOrderService<T> {

    @Autowired
    private IDeliveryTypeMapper deliveryTypeMapper;
    @Autowired
    private IDeliveryPointMapper deliveryPointMapper;
    @Autowired
    private ITakeTimeMapper takeTimeMapper;

    @Getter
    @Setter
    private String name;

    OrderServiceImpl(BaseMapper mapper) {
        super(mapper);
    }


    @Override
    public List<?> getList(String type) {
        if (type.equals("tt")) {
            return takeTimeMapper.selectList(null);
        }
        if (type.equals("dt")) {
            return deliveryTypeMapper.selectList(null);
        }
        if (type.equals("dp")) {
            return deliveryPointMapper.selectList(null);
        }
        return null;
    }

    @Override
    public Integer addTakeTime(TakeTime entity) {
        return takeTimeMapper.insert(entity);
    }

    @Override
    public Integer addPoint(DeliveryPoint entity) {
        return deliveryPointMapper.insert(entity);
    }

    @Override
    public Integer addDeliveryType(DeliveryType entity) {
        return deliveryTypeMapper.insert(entity);
    }

    @Override
    public Integer delTakeTime(Integer id) {
        return takeTimeMapper.deleteById(id);
    }

    @Override
    public Integer delPoint(Integer id) {
        return deliveryPointMapper.deleteById(id);
    }

    @Override
    public Integer delDeliveryType(Integer id) {
        return deliveryTypeMapper.deleteById(id);
    }

    @Override
    public Integer updateTakeTime(TakeTime entity) {
        return takeTimeMapper.updateById(entity);
    }

    @Override
    public Integer updatePoint(DeliveryPoint entity) {
        return deliveryPointMapper.updateById(entity);
    }

    @Override
    public Integer updateDeliveryType(DeliveryType entity) {
        return deliveryTypeMapper.updateById(entity);
    }

    @Override
    public boolean isExist(String type, String name) {
        var num = false;
        if (type.equals("tt")) {
            var mod = TakeTime.builder().name(name).build();
            num = takeTimeMapper.selectOne(new QueryWrapper(mod)) != null;
        }
        if (type.equals("dt")) {
            var mod = DeliveryType.builder().name(name).build();
            num = deliveryTypeMapper.selectOne(new QueryWrapper(mod)) != null;
        }
        if (type.equals("dp")) {
            var mod = DeliveryPoint.builder().name(name).build();
            num = deliveryPointMapper.selectOne(new QueryWrapper(mod)) != null;
        }
        return num;
    }

    @Override
    public List<?> getOrderByAdmin(String date, String key) {
        Date dateobj = DateHelper.strToDate(date);

        boolean isSendOrder = getName().equals("sendOrder") ? true : false;

        if(isSendOrder){
            var wrapper = new QueryWrapper<SendOrder>().lambda() ;
            if (dateobj != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateobj);
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                calendar.add(calendar.DATE, 1);
                String curDate = s.format(calendar.getTime());  //当前日期
                wrapper.lt(SendOrder::getCreateDate,curDate);
            }
            if (key != null && key.strip().length() > 0) { // 手机号，订单号
                wrapper.and(i -> i.like(SendOrder::getId, key).or().like(SendOrder::getSenderPhoneNum, key).or().like(SendOrder::getReceiverPhoneNum, key));
            }
            wrapper.orderByDesc(SendOrder::getCreateDate);
            return baseMapper.selectList(wrapper);
        }else {
            var wrapper = new QueryWrapper<TakeOrder>().lambda();
            if (dateobj != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateobj);
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                calendar.add(calendar.DATE, 1);
                String curDate = s.format(calendar.getTime());  //当前日期
                wrapper.lt(TakeOrder::getCreateDate,curDate);
            }
            if (key != null && key.strip().length() > 0) { // 手机号，订单号
                wrapper.and(i -> i.like(TakeOrder::getId, key).or().like(TakeOrder::getPhoneNum, key));
            }
            wrapper.orderByDesc(TakeOrder::getCreateDate);
            return baseMapper.selectList(wrapper);
        }
    }
}
