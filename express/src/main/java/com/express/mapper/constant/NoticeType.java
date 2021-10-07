package com.express.mapper.constant;

public enum NoticeType {
    Notic("公告"),
    Active("活动");

    private String val;

    NoticeType(String val) {
        this.val = val;
    }

    public String getVal() {
        return this.val;
    }
}
