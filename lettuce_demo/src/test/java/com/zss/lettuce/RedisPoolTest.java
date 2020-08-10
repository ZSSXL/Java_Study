package com.zss.lettuce;

import com.zss.lettuce.config.RedisPool;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Properties;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/10 10:38
 * @desc redis连接池 - 测试
 */
@SuppressWarnings("unused")
public class RedisPoolTest extends BaseTest {

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
     * 连接池的使用 - test
     */
    @Test
    public void useConnectPoolTest() {
        // 创建连接信息
        RedisURI redisUri = RedisURI.builder()
                .withHost(HOST)
                .withPort(PORT)
                .withDatabase(DATABASE)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient redisClient = RedisClient.create(redisUri);
        StatefulRedisConnection<String, String> connect = redisClient.connect();

        GenericObjectPoolConfig<StatefulRedisConnection<String, String>> poolConfig =
                new GenericObjectPoolConfig<>();
        // 对象池中最大的空闲对象个数。默认值是8。
        poolConfig.setMaxIdle(10);
        // 对象池中最小的空闲对象个数。默认值是0。
        poolConfig.setMinIdle(0);
        // 对象池中管理的最多对象个数。默认值是8。
        poolConfig.setMaxTotal(10);
        // 在获取对象的时候检查有效性, 默认false
        poolConfig.setTestOnBorrow(true);
        // 在归还对象的时候检查有效性, 默认false
        poolConfig.setTestOnReturn(false);
        // 在创建对象时检测对象是否有效，默认值是false。
        poolConfig.setTestOnCreate(true);
        // 当连接池资源耗尽时，等待时间，超出则抛异常，默认为-1即永不超时, ms
        poolConfig.setMaxWaitMillis(10000L);
        try (GenericObjectPool<StatefulRedisConnection<String, String>> genericObjectPool =
                     ConnectionPoolSupport.createGenericObjectPool(redisClient::connect, poolConfig)) {
            StatefulRedisConnection<String, String> connection = genericObjectPool.borrowObject();
            RedisCommands<String, String> sync = connect.sync();
            String name = sync.get("name");
            System.out.println("Get Result : " + name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.close();
        }
    }

    /**
     * 从连接池中获取连接
     *
     * @return Redis连接实例
     */
    private StatefulRedisConnection<String, String> getRedisConnect() {
        // 创建连接信息
        RedisURI redisUri = RedisURI.builder()
                .withHost(HOST)
                .withPort(PORT)
                .withDatabase(DATABASE)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient redisClient = RedisClient.create(redisUri);
        GenericObjectPoolConfig<StatefulRedisConnection<String, String>> poolConfig =
                new GenericObjectPoolConfig<>();
        // 对象池中最大的空闲对象个数。默认值是8。
        poolConfig.setMaxIdle(10);
        // 对象池中最小的空闲对象个数。默认值是0。
        poolConfig.setMinIdle(0);
        // 对象池中管理的最多对象个数。默认值是8。
        poolConfig.setMaxTotal(10);
        // 在获取对象的时候检查有效性, 默认false
        poolConfig.setTestOnBorrow(true);
        // 在归还对象的时候检查有效性, 默认false
        poolConfig.setTestOnReturn(false);
        // 在创建对象时检测对象是否有效，默认值是false。
        poolConfig.setTestOnCreate(true);
        // 当连接池资源耗尽时，等待时间，超出则抛异常，默认为-1即永不超时, ms
        poolConfig.setMaxWaitMillis(10000L);

        GenericObjectPool<StatefulRedisConnection<String, String>> genericObjectPool =
                ConnectionPoolSupport.createGenericObjectPool(redisClient::connect, poolConfig);
        try {
            return genericObjectPool.borrowObject();
        } catch (Exception e) {
            System.out.println("Redis Connection Pool Exception : " + e.getMessage());
            return null;
        }
    }

    /**
     * 从Redis连接池中获取并Set - 测试
     */
    @Test
    public void setFromPoolTest() {
        StatefulRedisConnection<String, String> redisConnect = getRedisConnect();
        if (redisConnect == null) {
            System.out.println("获取连接失败");
        } else {
            RedisCommands<String, String> sync = redisConnect.sync();
            SetArgs setArgs = SetArgs.Builder.nx().ex(20);
            String set = sync.set("local", "shanghai", setArgs);
            System.out.println("Set Result : " + set);
            redisConnect.close();
        }
    }

    @Test
    public void getFromPoolTest() {
        StatefulRedisConnection<String, String> redisConnect = getRedisConnect();
        if (redisConnect == null) {
            System.out.println("获取连接失败");
        } else {
            RedisCommands<String, String> sync = redisConnect.sync();
            String local = sync.get("local");
            System.out.println("Get Result : " + local);
            // Get Result : shanghai Or null
            redisConnect.close();
        }
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
