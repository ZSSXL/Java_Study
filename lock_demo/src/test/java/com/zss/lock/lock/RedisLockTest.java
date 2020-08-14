package com.zss.lock.lock;

import com.zss.lock.BaseTest;
import com.zss.lock.distributed.RedisLockV1;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/14 10:13
 * @desc redis分布式锁 - 测试
 */
public class RedisLockTest extends BaseTest {

    @Autowired
    private RedisLockV1 redisLockV1;

    /**
     * redisLockV1()
     * redisLockV2()
     * redisLockV2Plus()
     * redisLockV3()
     * redisLockV3Plus()
     */
    @Test
    public void redisLocKTest() {
        redisLockV1.redisLockV3PlusPlus();
    }
}
