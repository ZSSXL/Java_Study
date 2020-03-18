package com.zss.sftpdemo.common;

/**
 * 枚举类
 */

public enum ResponseCode {

    /**
     * 成功
     */
    SUCCESS(200, "SUCCESS"),
    /**
     * 失败
     */
    ERROR(404, "ERROR"),
    /**
     * 需要登录
     */
    NEED_LOGIN(401, "NEED_LOGIN"),
    /**
     * 参数错误
     */
    PARAM_ERROR(402, "PARAM_ERROR"),
    /**
     * 密码相同
     */
    SAME_PASSWORD(100, "SAME_PASSWORD");

    private final int code;
    private final String desc;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
