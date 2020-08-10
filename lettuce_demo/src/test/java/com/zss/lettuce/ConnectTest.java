package com.zss.lettuce;

import com.zss.lettuce.config.RedisPool;
import io.lettuce.core.*;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/7 17:32
 * @desc Redis连接 - 测试
 */
@SuppressWarnings("unused")
public class ConnectTest extends BaseTest {

    /**
     * 地址
     */
    private static final String HOST;
    /**
     * 端口
     */
    private static final Integer PORT;
    /**
     * 数据库索引号
     */
    private static final Integer DATABASE;

    static {
        Properties properties = new Properties();
        InputStream resourceAsStream = RedisPool.class
                .getClassLoader()
                .getResourceAsStream("redis.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HOST = properties.getProperty("redis.host");
        PORT = Integer.valueOf(properties.getProperty("redis.port"));
        DATABASE = Integer.valueOf(properties.getProperty("redis.database"));
    }

    /**
     * 获取连接
     *
     * @return commands
     */
    private RedisClient getRedis() {
        // 创建连接信息
        RedisURI redisUri = RedisURI.builder()
                .withHost(HOST)
                .withPort(PORT)
                .withDatabase(DATABASE)
                .build();
        // 创建客户端
        return RedisClient.create(redisUri);
    }

    /**
     * 管理连接
     *
     * @param redisClient 连接实例
     */
    private void shotDownRedis(RedisClient redisClient) {
        redisClient.shutdown();
    }

    /**
     * (同步)set - test
     */
    @Test
    public void setTest() {
        RedisClient redisClient = getRedis();
        /*RedisCommands<String, String> commands = redisClient.connect().sync();
        String set = commands.set("name", "zss_test");*/
        RedisCommands<String, String> sync = redisClient.connect().sync();
        // 设置Set参数，nx:只在键不存在时，才对键进行设置操作 ex:过期时间
        SetArgs setArgs = SetArgs.Builder.nx().ex(10);
        String set = sync.set("name", "zss_test", setArgs);
        System.out.println("Set Result : [" + set + "]");
        // Set Result : [OK]
        shotDownRedis(redisClient);
    }

    /**
     * (同步)get - test
     */
    @Test
    public void getTest() {
        RedisClient redisClient = getRedis();
        RedisCommands<String, String> sync = redisClient.connect().sync();
        String name = sync.get("name");
        System.out.println("Name : [ " + name + " ]");
        // Name : [ zss_test ]
        shotDownRedis(redisClient);
    }

    /**
     * del - test
     */
    @Test
    public void delTest() {
        RedisClient redis = getRedis();
        RedisCommands<String, String> sync = redis.connect().sync();
        Long name = sync.del("name");
        System.out.println("Del Result : " + name);
        // Del Result : 1
        shotDownRedis(redis);
    }

    /**
     * (异步)set - test
     */
    @Test
    public void setAsyncTest() {
        RedisClient redis = getRedis();
        RedisAsyncCommands<String, String> async = redis.connect().async();
        RedisFuture<String> setAsync = async.set("name", "zss_async");
        setAsync.thenAccept(value -> System.out.println("Value : " + value));
        redis.shutdown();
        shotDownRedis(redis);
    }

    /**
     * (异步)get - test
     */
    @Test
    public void getAsyncTest() {
        RedisClient redis = getRedis();
        RedisAsyncCommands<String, String> async = redis.connect().async();
        RedisFuture<String> name = async.get("name");
        name.thenAccept(value -> System.out.println("Value : " + value));
        LettuceFutures.awaitAll(1, TimeUnit.MINUTES, name);
        // Value : zss_async
        shotDownRedis(redis);
    }


    // ============================= Getter方法 ============================= //

    public static String getHOST() {
        return HOST;
    }

    public static Integer getPORT() {
        return PORT;
    }

    public static Integer getDATABASE() {
        return DATABASE;
    }
}
