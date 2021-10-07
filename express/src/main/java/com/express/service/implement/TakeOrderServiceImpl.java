package com.express.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.express.common.DateHelper;
import com.express.common.MybatisplusHelper;
import com.express.mapper.ITakeOrderMapper;
import com.express.mapper.constant.OrderStatus;
import com.express.mapper.entity.SendOrder;
import com.express.mapper.entity.TakeOrder;
import com.express.service.ITakeOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class TakeOrderServiceImpl extends OrderServiceImpl<TakeOrder> implements ITakeOrderService {
    private final ITakeOrderMapper takeOrderMapper;

    public TakeOrderServiceImpl(ITakeOrderMapper takeOrderMapper) {
        super(takeOrderMapper);
        this.takeOrderMapper = takeOrderMapper;
        setName("takeOrder");
    }

//    @Override
//    public TakeOrder getObject(Integer id) {
//        return takeOrderMapper.selectById(id);
//    }
//
//    @Override
//    public Integer delObject(Integer id) {
//        return takeOrderMapper.deleteById(id);
//    }
//
//    @Override
//    public Integer updObject(TakeOrder model) {
//        return takeOrderMapper.update(model, null);
//    }
//
//    @Override
//    public Integer addObject(TakeOrder model) {
//        return takeOrderMapper.insert(model);
//    }

    @Override
    public List<TakeOrder> query(Integer pageNo, Integer pageSize, QueryWrapper queryWrapper) {
        return takeOrderMapper.selectPage(MybatisplusHelper.getPageInstance(pageNo, pageSize), queryWrapper).getRecords();
    }

    @Override
    public Integer cancelOrderById(Integer orderId) {
        var obj = getObject(orderId);
        if (obj == null) return 0;

        obj.setOrderStatus(OrderStatus.Canceled.getVal());
        var nObj = TakeOrder.builder().build();
        nObj.setId(orderId);
        return takeOrderMapper.update(obj, MybatisplusHelper.getWrapperInstance(nObj));
    }

    @Override
    public Integer insertOrder(TakeOrder order) {
        return takeOrderMapper.insert(order);
    }

    @Override
    public Integer updateOrder(TakeOrder order) {
        return takeOrderMapper.updateById(order);
    }

    @Override
    public Map<String, Integer> getCustomerOrderTypeNum(Integer customerId) {
        Map<String, Integer> maps = new HashMap<>();
        return null;
    }

    @Override
    public List<?> getCanAcceOrder(Integer courierId, String key, String date){

        Timestamp ts = DateHelper.strToStamp(date);
        var wrapper = new QueryWrapper<TakeOrder>().lambda();
        wrapper.ne(TakeOrder::getOrderStatus, "已取消");
        if (ts != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(ts);
            c.add(Calendar.DATE, 1);
            ts = new Timestamp(c.getTimeInMillis());
            wrapper.lt(TakeOrder::getCreateDate, ts);
        }
        if (key != null && key.strip().length() > 0) { // 手机号，订单号
            wrapper.and(i -> i.like(TakeOrder::getId, key).or().like(TakeOrder::getPhoneNum, key));
        }
        wrapper.eq(TakeOrder::getPayStatus, "已支付");
        wrapper.and(i -> i.eq(TakeOrder::getCourierId, "0").or().eq(TakeOrder::getCourierId, courierId));
        wrapper.orderByDesc(TakeOrder::getCreateDate);
        return takeOrderMapper.selectList(wrapper);
    }

    @Override
    public Integer recOrderById(Integer orderId, Integer courierId) {
        var order = getObject(orderId);
        order.setCourierId(courierId);
        order.setOrderStatus(OrderStatus.Ongoing.getVal());

        var mod = new TakeOrder();
        mod.setId(orderId);
        return takeOrderMapper.update(order,new QueryWrapper<>(mod));
    }
}
