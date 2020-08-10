package com.zss.lettuce.util;

import com.zss.lettuce.config.RedisPool;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/10 14:12
 * @desc Redis - 工具类
 */
@SuppressWarnings("unused")
public class RedisUtil {

    /**
     * redis - SET
     *
     * @param key   键
     * @param value 值
     * @return SET结果
     */
    public static String set(String key, String value) {
        StatefulRedisConnection<String, String> redis = RedisPool.getRedis();
        if (redis != null) {
            RedisPool.getPoolMessage();
            RedisCommands<String, String> sync = redis.sync();
            String set = sync.set(key, value);
            RedisPool.returnRedis(redis);
            RedisPool.getPoolMessage();
            return set;
        } else {
            return "";
        }
    }

    /**
     * redis - get
     *
     * @param key 键
     * @return 值
     */
    public static String get(String key) {
        StatefulRedisConnection<String, String> redis = RedisPool.getRedis();
        if (redis != null) {
            RedisCommands<String, String> sync = redis.sync();
            String get = sync.get(key);
            RedisPool.returnRedis(redis);
            return get;
        } else {
            return "";
        }
    }

    /**
     * redis - setEx
     *
     * @param key   键
     * @param value 值
     * @param ex    过期时间，单位秒
     * @return SET结果
     */
    public static String set(String key, String value, Long ex) {
        StatefulRedisConnection<String, String> redis = RedisPool.getRedis();
        if (redis != null) {
            RedisCommands<String, String> sync = redis.sync();
            SetArgs setArgs = SetArgs.Builder.ex(ex);
            String set = sync.set(key, value, setArgs);
            RedisPool.returnRedis(redis);
            return set;
        } else {
            return "";
        }
    }

    /**
     * redis - del
     *
     * @param key 键
     * @return 删除结果
     */
    public static Long del(String key) {
        StatefulRedisConnection<String, String> redis = RedisPool.getRedis();
        if (redis != null) {
            RedisCommands<String, String> sync = redis.sync();
            Long del = sync.del(key);
            RedisPool.returnRedis(redis);
            return del;
        } else {
            return 0L;
        }
    }
}
