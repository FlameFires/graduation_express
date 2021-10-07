package com.express.mapper.dto;

import com.express.mapper.entity.TakeOrder;
import lombok.Data;

@Data
public class TakeOrderDetailDto extends TakeOrder {
    private String pointName;
    private String goodsName;
    private String dTypeName;
}
