package com.zss.lettuce.v2;

import com.zss.lettuce.BaseTest;
import com.zss.lettuce.config.RedisPoolV2;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/11 16:04
 * @desc Redis连接池V2 - 测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisPoolV2Test extends BaseTest {

    @Autowired
    private RedisPoolV2 redisPoolV2;

    @Test
    public void getTest() {
        StatefulRedisConnection<String, String> redis = redisPoolV2.getRedis();
        if (redis != null) {
            redisPoolV2.getPoolMessage();
            RedisCommands<String, String> sync = redis.sync();
            String local = sync.get("local");
            System.out.println("Get Result : " + local);
            redisPoolV2.returnRedis(redis);
            redisPoolV2.getPoolMessage();
        }
    }
}
