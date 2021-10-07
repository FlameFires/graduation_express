package com.express.mapper.entity;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SendOrder extends BaseEntity {
    private Integer customerId;
    private Integer courierId;
    private String senderName;
    private String senderPhoneNum;
    private String receiverName;
    private String receiverPhoneNum;
    /**
     * 取件地址
     */
    private String takeAddress;
    /**
     * 寄送地址
     */
    private String sendAddress;
    /**
     * 订单状态:进行中/已取消/已完成
     */
    private String orderStatus;
    /**
     * 支付状态:已支付/未支付
     */
    private String payStatus;
    /**
     * 物品状态:已取件/未取件/已送达 如果不是已送达那么就是未送达
     */
    private String goodsStatus;
    /**
     * 物品类型
     */
    private Integer goodsTypeId;
    /**
     * 取件时间 12:00~13:30
     */
    private String takeTimes;
    /**
     * 快递类型id
     */
    private Integer deliveryTypeId;
    /**
     * 快递点
     */
    private Integer deliveryPointId;

}
