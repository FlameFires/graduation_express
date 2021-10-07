package com.express.mapper.entity;

import lombok.*;

import java.sql.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Coupon extends BaseEntity {
    private String title;
    // 管理员id
    private Integer issuerId;
    // 用户id
    private Integer customerId;
    private Integer conditionPrice;
    private String value;
    private Date validDate;
    private boolean isUse;
}
