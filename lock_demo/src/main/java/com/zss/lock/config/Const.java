package com.zss.lock.config;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/12 17:15
 * @desc 常量类
 */
@SuppressWarnings("unused")
public class Const {

    /**
     * 分布式锁 - KEY
     */
    public static final String KEY = "distribute_lock_key";

    /**
     * 分布式锁 - VALUE
     */
    public static final String VALUE = "distribute_lock_value";

    /**
     * 有效期60秒，单位秒，
     */
    public static final Long EXPIRE_TIME_SECONDS = 60L;

    /**
     * 有效期60秒，单位毫秒
     */
    public static final Long EXPIRE_TIME_MILLIS = 60L * 1000L;
}
