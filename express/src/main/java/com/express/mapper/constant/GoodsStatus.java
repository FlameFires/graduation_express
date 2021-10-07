package com.express.mapper.constant;

public enum GoodsStatus {
    Ongoing("寄件中"),
    Waiting("待取件"),
    Taked("已取件");

    private String val;

    GoodsStatus(String val) {
        this.val = val;
    }

    public String getVal() {
        return this.val;
    }
}
