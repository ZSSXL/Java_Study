package com.zss.lock.util;

import com.zss.lock.config.RedisPool;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/12 14:30
 * @desc Redis - 工具类
 */
@Component
@SuppressWarnings("unused")
public class RedisUtil {

    private final RedisPool redisPool;

    @Autowired
    public RedisUtil(RedisPool redisPool) {
        this.redisPool = redisPool;
    }

    /**
     * redis - Get
     *
     * @param key 键
     * @return 值
     */
    public String get(String key) {
        StatefulRedisConnection<String, String> redis = redisPool.getRedis();
        if (redis != null) {
            RedisCommands<String, String> sync = redis.sync();
            String value = sync.get(key);
            redisPool.returnRedis(redis);
            return value;
        } else {
            return "";
        }
    }

    /**
     * redis - Set
     *
     * @param key   键
     * @param value 值
     * @return set结果
     */
    public String set(String key, String value) {
        StatefulRedisConnection<String, String> redis = redisPool.getRedis();
        if (redis != null) {
            RedisCommands<String, String> sync = redis.sync();
            String set = sync.set(key, value);
            redisPool.returnRedis(redis);
            return set;
        } else {
            return "";
        }
    }

    /**
     * redis - del
     *
     * @param key 键
     * @return del结果 <=0为失败, 1为成功
     */
    public Long del(String key) {
        StatefulRedisConnection<String, String> redis = redisPool.getRedis();
        if (redis != null) {
            RedisCommands<String, String> sync = redis.sync();
            Long del = sync.del(key);
            redisPool.returnRedis(redis);
            return del;
        } else {
            return -1L;
        }
    }

    /**
     * redis - setEx
     *
     * @param key   键
     * @param value 值
     * @param ex    过期时间,单位秒（s）
     * @return setEx结果
     */
    public String setEx(String key, String value, Long ex) {
        StatefulRedisConnection<String, String> redis = redisPool.getRedis();
        if (redis != null) {
            RedisCommands<String, String> sync = redis.sync();
            String setEx = sync.setex(key, ex, value);
            redisPool.returnRedis(redis);
            return setEx;
        } else {
            return "";
        }
    }

    /**
     * redis - setNx
     *
     * @param key   键
     * @param value 值
     * @return setNx结果
     */
    public Boolean setNx(String key, String value) {
        StatefulRedisConnection<String, String> redis = redisPool.getRedis();
        if (redis != null) {
            RedisCommands<String, String> sync = redis.sync();
            Boolean setNx = sync.setnx(key, value);
            redisPool.returnRedis(redis);
            return setNx;
        } else {
            return Boolean.FALSE;
        }
    }

    /**
     * redis - getSet
     *
     * @param key   键
     * @param value 值
     * @return getSet结果
     */
    public String getSet(String key, String value) {
        StatefulRedisConnection<String, String> redis = redisPool.getRedis();
        if (redis != null) {
            RedisCommands<String, String> sync = redis.sync();
            String getSet = sync.getset(key, value);
            redisPool.returnRedis(redis);
            return getSet;
        } else {
            return "";
        }
    }
}
