package com.express.mapper.constant;

/**
 * 寄件订单状态
 */
public enum OrderStatus {
    Ongoing("进行中"),
    Canceled("已取消"),
    Completed("已完成");

    private String val;

    OrderStatus(String val) {
        this.val = val;
    }

    public String getVal() {
        return this.val;
    }
}
