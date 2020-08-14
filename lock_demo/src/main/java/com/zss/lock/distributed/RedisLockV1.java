package com.zss.lock.distributed;

import com.zss.lock.config.Const;
import com.zss.lock.util.FunctionUtil;
import com.zss.lock.util.RedisUtil;
import com.zss.lock.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/12 17:10
 * @desc redis分布式锁 - 第一版本
 */
@Slf4j
@SuppressWarnings("unused")
@Component
@EnableScheduling
public class RedisLockV1 {

    private final RedisUtil redisUtil;

    @Autowired
    public RedisLockV1(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    private final int flag = 3;

    /**
     * 分布式锁第一版本
     */
    public void redisLockV1() {
        for (int i = 0; i < flag; i++) {
            // 调用setNx
            Boolean result = redisUtil.setNx(Const.KEY, Const.VALUE);
            // 判断是否成功
            if (result != null && result) {
                // 执行业务逻辑
                FunctionUtil.runBusiness();
                // 释放锁
                releaseLock();
            } else {
                // 不成功则获取锁失败
                log.warn("没有获得分布式锁：[{}]", Const.KEY);
            }
        }
    }

    /**
     * Redis分布式锁 - 将过期释放锁的权限交给Redis
     */
    public void redisLockV2() {
        for (int i = 0; i < flag; i ++){
            // 调用SetNx
            Boolean result = redisUtil.setNx(Const.KEY, Const.VALUE);
            if (result != null && result) {
                // 给锁添加有效时间 ： 当前时间 + 有效期
                Boolean expire = redisUtil.expire(Const.KEY, Const.EXPIRE_TIME);
                if (expire != null && expire) {
                    // 执行业务逻辑
                    FunctionUtil.runBusiness();
                    // 释放锁
                    releaseLock();
                } else {
                    // 释放锁
                    releaseLock();
                    // 不成功则获取锁失败
                    log.warn("没有获得分布式锁：[{}]", Const.KEY);
                }
            } else {
                // 不成功则获取锁失败
                log.warn("没有获得分布式锁：[{}]", Const.KEY);
            }
        }
    }

    /**
     * Redis分布式锁 - 将过期释放锁的权限交给Redis
     * 调用setNxEx方法
     */
    public void redisLockV2Plus() {
        for (int i = 0; i < flag; i ++){
            String result = redisUtil.setNxEx(Const.KEY, Const.VALUE, Const.EXPIRE_TIME);
            if (result != null && StringUtils.isEmpty(result)) {
                // 执行业务逻辑
                FunctionUtil.runBusiness();
                // 释放锁
                releaseLock();
            } else {
                // 不成功则获取锁失败
                log.warn("没有获得分布式锁：[{}]", Const.KEY);
            }
        }
    }

    /**
     * redis分布式锁 - 将释放过期锁的权限交给其他服务器
     */
    public void redisLockV3() {
        for (int i = 0; i < flag; i ++){
            Boolean result = redisUtil.setNx(Const.KEY,
                    String.valueOf(TimeUtil.getCurrentTimestamp() + Const.EXPIRE_TIME));
            if (result != null && result) {
                // 获取到锁，执行业务逻辑
                FunctionUtil.runBusiness();
                // 释放锁
                releaseLock();
            } else {
                // 未获取到锁，判断有效期是否过期
                String timeout = redisUtil.get(Const.KEY);
                // 如果timeout不为空且获取到的timeout小于当前的时间戳
                if (timeout != null && Long.parseLong(timeout) < TimeUtil.getCurrentTimestamp() + Const.EXPIRE_TIME) {
                    // 执行业务逻辑
                    FunctionUtil.runBusiness();
                    // 释放锁
                    releaseLock();
                } else {
                    log.warn("没有获得分布式锁：[{}]", Const.KEY);
                }
            }
        }
    }

    /**
     * redis分布式锁 - 将释放过期锁的权限交给其他服务器
     * 使用getSet方法
     */
    public void redisLockV3Plus() {
        for (int i = 0; i < flag; i ++){
            Boolean result = redisUtil.setNx(Const.KEY,
                    String.valueOf(TimeUtil.getCurrentTimestamp() + Const.EXPIRE_TIME));
            if (result != null && result) {
                // 获取到锁，执行业务逻辑
                FunctionUtil.runBusiness();
                // 释放锁
                releaseLock();
            } else {
                // 未获取到锁，判断有效期是否过期
                String timeout = redisUtil.get(Const.KEY);
                // 如果timeout不为空且获取到的timeout小于当前的时间戳
                if (timeout != null && Long.parseLong(timeout) < TimeUtil.getCurrentTimestamp() + Const.EXPIRE_TIME) {
                    String getSetValue = redisUtil.getSet(Const.KEY,
                            String.valueOf(TimeUtil.getCurrentTimestamp() + Const.EXPIRE_TIME));
                    // 如果getSetValue为空 -> 说明锁已经被释放了，可以获得锁
                    // 或者如果getSetValue等于之前获得的timeout -> 说明在这之间没有其他服务器去改变这个value，也可以获得锁
                    if (StringUtils.isEmpty(getSetValue) || StringUtils.equals(timeout, getSetValue)) {
                        // 执行业务逻辑
                        FunctionUtil.runBusiness();
                        // 释放锁
                        releaseLock();
                    } else {
                        log.warn("没有获得分布式锁：[{}]", Const.KEY);
                    }
                } else {
                    log.warn("没有获得分布式锁：[{}]", Const.KEY);
                }
            }
        }
    }

    // ====================== 内部私有方法 ====================== //

    /**
     * 释放分布式锁
     */
    private void releaseLock() {
        Long del = redisUtil.del(Const.KEY);
        if (del != null && del == 1) {
            log.info("锁[{}]释放成功...", Const.KEY);
        } else {
            log.warn("锁[{}]释放失败...", Const.KEY);
        }
    }
}
