package com.aiden.cj.constant;

/**
 * @author Aiden
 * @version 1.0
 * @description 优惠卷状态
 * @date 2021-5-22 10:12:34
 */
public enum CouponStatus {
    UNUSED(0, "UNUSED", "未使用"),
    USED(1, "USED", "已使用,已核销"),
    EXPIRE(2, "EXPIRE", "过期");

    private int code;
    private String value;
    private String description;

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    CouponStatus(int code, String value, String description) {
        this.code = code;
        this.value = value;
        this.description = description;
    }
}
