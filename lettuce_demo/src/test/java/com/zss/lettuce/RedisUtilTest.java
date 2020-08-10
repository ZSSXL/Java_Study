package com.zss.lettuce;

import com.zss.lettuce.util.RedisUtil;
import org.junit.Test;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/10 14:37
 * @desc RedisUtil - 测试
 */
public class RedisUtilTest extends BaseTest {

    String key = "local";
    String value = "shanghai";
    Long ex = 10L;

    @Test
    public void setTest() {
        String set = RedisUtil.set(key, value);
        System.out.println("Set Result : " + set);
    }

    @Test
    public void getTest() {
        String getResult = RedisUtil.get(key);
        System.out.println("Get Result : " + getResult);
    }

    @Test
    public void setExTest() {
        String set = RedisUtil.set(key, value, ex);
        System.out.println("Set Ex Result : " + set);
    }

    @Test
    public void delTest() {
        Long del = RedisUtil.del(key);
        System.out.println("Del Result : " + del);
    }
}
