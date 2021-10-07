package com.express.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.express.common.DateHelper;
import com.express.mapper.ISendOrderMapper;
import com.express.mapper.constant.OrderStatus;
import com.express.mapper.entity.SendOrder;
import com.express.service.ISendOrderService;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SendOrderServiceImpl extends OrderServiceImpl<SendOrder> implements ISendOrderService {
    private final ISendOrderMapper sendOrderMapper;

    public SendOrderServiceImpl(ISendOrderMapper sendOrderMapper) {
        super(sendOrderMapper);
        this.sendOrderMapper = sendOrderMapper;
        setName("sendOrder");
    }

    @Override
    public List<SendOrder> query(Integer pageNo, Integer pageSize, QueryWrapper queryWrapper) {
        var page = new Page(pageNo, pageSize);
        var list = sendOrderMapper.selectPage(page, queryWrapper).getRecords();
        return list;
    }

    @Override
    public Integer cancelOrderById(Integer orderId) {
        var ord = new SendOrder();
        ord.setId(orderId);
        ord.setOrderStatus(OrderStatus.Canceled.getVal());
        var ord2 = new SendOrder();
        ord2.setId(orderId);
        var wrapper = new QueryWrapper<SendOrder>(ord2);
        return sendOrderMapper.update(ord, wrapper);
    }

    @Override
    public Map<String, Integer> getOrderTypeNum(Integer customerId) {
        return null;
    }

    @Override
    @Select(value = "SELECT * FROM send_order a LEFT JOIN take_time b on a.takeTimeId = b.id left join delivery_point c on a.deliveryPointId=c.id left join delivery_type d on a.deliveryTypeId=d.id where a.id = #{id}")
    public SendOrder getObject(Integer id) {
        return super.getObject(id);
    }


    @Override
    public List<?> getCanAcceOrder(Integer courierId, String key, String date) {

        Timestamp ts = DateHelper.strToStamp(date);
        var wrapper = new QueryWrapper<SendOrder>().lambda();
        wrapper.ne(SendOrder::getOrderStatus, "已取消");
        if (ts != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(ts);
            c.add(Calendar.DATE, 1);
            ts = new Timestamp(c.getTimeInMillis());
            wrapper.lt(SendOrder::getCreateDate, ts);
        }
        if (key != null && key.strip().length() > 0) { // 手机号，订单号
            wrapper.and(i -> i.like(SendOrder::getId, key).or().like(SendOrder::getSenderPhoneNum, key).or().like(SendOrder::getReceiverPhoneNum, key));
        }
        wrapper.eq(SendOrder::getPayStatus, "已支付");
        wrapper.and(i -> i.eq(SendOrder::getCourierId, "0").or().eq(SendOrder::getCourierId, courierId));
        wrapper.orderByDesc(SendOrder::getCreateDate);
        return sendOrderMapper.selectList(wrapper);
    }


    @Override
    public Integer recOrderById(Integer orderId, Integer courierId) {
        SendOrder order = getObject(orderId);
        order.setCourierId(courierId);
        order.setOrderStatus(OrderStatus.Ongoing.getVal());

        var mod = new SendOrder();
        mod.setId(orderId);
        return sendOrderMapper.update(order, new QueryWrapper<>(mod));
    }
}
