package com.zss.lettuce.data;

import com.zss.lettuce.BaseTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/11 14:26
 * @desc SpringBoot Data Redis - 测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataRedisTest extends BaseTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void getTest() {
        String country = redisTemplate.opsForValue().get("country");
        System.out.println("Get Result : " + country);
    }
}
