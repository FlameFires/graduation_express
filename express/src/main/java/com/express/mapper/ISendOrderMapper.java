package com.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.express.mapper.dto.SendOrderDto;
import com.express.mapper.entity.SendOrder;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ISendOrderMapper extends BaseMapper<SendOrder> {

    @Select(value = "SELECT * FROM send_order a LEFT JOIN take_time b on a.takeTimeId = b.id left join delivery_point c on a.deliveryPointId=c.id left join delivery_type d on a.deliveryTypeId=d.id where a.id = #{id}")
    SendOrderDto getSendOrderGoDto(Integer id);
}
