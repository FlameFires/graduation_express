package com.express.mapper.dto;

import com.express.mapper.entity.SendOrder;
import lombok.Data;

@Data
public class SendOrderDetailDto extends SendOrder {

    private String dPointName;
    private String goodsName;
    private String dTypeName;
}
