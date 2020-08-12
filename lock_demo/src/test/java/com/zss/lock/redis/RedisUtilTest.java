package com.zss.lock.redis;

import com.zss.lock.BaseTest;
import com.zss.lock.util.RedisUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/12 14:51
 * @desc redis工具类 - 测试
 */
public class RedisUtilTest extends BaseTest {

    @Autowired
    private RedisUtil redisUtil;

    private final String key = "hello";
    private final String value = "world";

    @Test
    public void setTest() {
        String set = redisUtil.set(key, value);
        System.out.println("Set Result : " + set);
    }

    @Test
    public void getTest() {
        String value = redisUtil.get(key);
        System.out.println("Get Value : " + value);
    }

    @Test
    public void setExTest() {
        Long expire = 10L;
        String result = redisUtil.setEx(key, value, expire);
        System.out.println("SetEx Result : " + result);
    }

    @Test
    public void setNxTest() {
        Boolean result = redisUtil.setNx(key, value);
        System.out.println("SetNx : " + result);
    }

    /**
     * getSet：如果redis中存在这个key，那么会返回该key对应的值，并重新设置value
     * 如果不存在这个key, 那么返回的值为null，且依然set该key和value
     */
    @Test
    public void getSetTest() {
        String getSet = "The world is not enough";
        String set = redisUtil.getSet(key, getSet);
        System.out.println("GetSet Result : " + set);
    }

    @Test
    public void delTest() {
        Long del = redisUtil.del(key);
        System.out.println("Del Result : " + del);
    }
}
