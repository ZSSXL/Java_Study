package com.zss.lock.distributed;

import com.zss.lock.config.Const;
import com.zss.lock.util.FunctionUtil;
import com.zss.lock.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
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

    /**
     * 分布式锁第一版本
     * 每隔十秒执行一次
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void redisLockV1() {
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
            log.warn("没有获得分布式锁：{}", Const.KEY);
        }
    }

    /**
     * 释放分布式锁
     */
    private void releaseLock() {
        Long del = redisUtil.del(Const.KEY);
        if (del != null && del == 1) {
            log.info("{}锁释放成功...", Const.KEY);
        } else {
            log.info("{}锁释放失败...", Const.KEY);
        }
    }
}
