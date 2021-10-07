package com.express.mapper.entity;

import lombok.*;

// 订单使用了优惠卷,订单与优惠卷的中间表
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderUseCoupon extends BaseEntity {
    private Integer orderId;
    private Integer couponId;
    // 是取件 true 还是寄件 false
    private boolean isTake;
}
