package com.express.mapper.entity;

import lombok.*;


@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TakeOrder extends BaseEntity {
    /*
    下单人员
     */
    private Integer customerId;
    /*
    接单人员
     */
    private Integer courierId;
    /**
     * 取件逆臣
     */
    private String takerName;
    /*
    手机号码
     */
    private String phoneNum;
    /*
    取件地址
     */
    private String address;
    /*
    取件码
     */
    private String takeCode;
    /*
    订单状态:进行中/已取消/已完成
     */
    private String orderStatus;
    /*
    支付状态:已支付/未支付
     */
    private String payStatus;
    /*
    物品状态:已取件/未取件/已送达 如果不是已送达那么就是未送达
     */
    private String goodsStatus;

    private Integer deliveryPointId;
    private Integer deliveryTypeId;
}
