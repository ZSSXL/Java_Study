package com.zss.springsecurity.common.response;

/**
 * @author ZSS
 * @desc 枚举类
 */
public enum ResponseCode {

    /**
     * 成功
     */
    SUCCESS(0, "SUCCESS"),
    /**
     * 失败
     */
    ERROR(1, "ERROR"),
    /**
     * 权限失败
     */
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT"),
    /**
     * 未登录
     */
    NEED_LOGIN(10, "NEED_LOGIN"),

    /**
     * 参数错误
     */
    PARAMETER_ERROR(11, "PARAMETER_ERROR");

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
