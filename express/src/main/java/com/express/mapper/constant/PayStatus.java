package com.express.mapper.constant;

/**
 * 支付状态
 */
public enum PayStatus {
    Ok("已支付"),
    No("未支付");

    private String val;

    PayStatus(String val) {
        this.val = val;
    }

    public String getVal() {
        return this.val;
    }
}
