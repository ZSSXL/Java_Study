package com.zss.lettuce;

import com.zss.lettuce.config.RedisPool;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.RedisCodec;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.masterreplica.MasterReplica;
import io.lettuce.core.masterreplica.StatefulRedisMasterReplicaConnection;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/10 16:22
 * @desc Redis主从配置 - 测试
 */
public class MasterSlaveTest extends BaseTest {

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
     * 获取连接 - 方法一
     *
     * @return commands
     */
    private StatefulRedisMasterReplicaConnection<String, String> getRedis() {
        // 创建连接信息
        RedisURI redisUri = RedisURI.builder()
                .withHost(HOST)
                .withPort(PORT)
                .withDatabase(DATABASE)
                .build();

        RedisClient redisClient = RedisClient.create(redisUri);
        RedisCodec<String, String> codec = StringCodec.UTF8;
        StatefulRedisMasterReplicaConnection<String, String> connect =
                MasterReplica.connect(redisClient, codec, redisUri);
        // 只从从节点读取数据
        connect.setReadFrom(ReadFrom.REPLICA);
        return connect;
    }

    /**
     * 获取连接 - 方法一
     *
     * @return commands
     */
    private StatefulRedisMasterReplicaConnection<String, String> getRedisV2() {
        // 创建连接信息
        List<RedisURI> uris = new ArrayList<>();
        uris.add(RedisURI.Builder.redis("127.0.0.1", 6379).build());
        uris.add(RedisURI.Builder.redis("127.0.0.1", 6378).build());
        uris.add(RedisURI.Builder.redis("127.0.0.1", 6377).build());

        RedisClient redisClient = RedisClient.create();
        RedisCodec<String, String> codec = StringCodec.UTF8;
        StatefulRedisMasterReplicaConnection<String, String> connect =
                MasterReplica.connect(redisClient, codec, uris);
        // 只从从节点读取数据
        connect.setReadFrom(ReadFrom.REPLICA);
        return connect;
    }

    @Test
    public void setTest() {
        StatefulRedisMasterReplicaConnection<String, String> redis = getRedis();
        RedisCommands<String, String> sync = redis.sync();
        String set = sync.set("master", "slave");
        System.out.println("Set : " + set);
        redis.close();
    }

    @Test
    public void delTest() {
        StatefulRedisMasterReplicaConnection<String, String> redis = getRedis();
        RedisCommands<String, String> sync = redis.sync();
        Long master = sync.del("master");
        System.out.println("Del Result : " + master);
    }

    @Test
    public void setTestV2() {
        StatefulRedisMasterReplicaConnection<String, String> redisV2 = getRedisV2();
        RedisCommands<String, String> sync = redisV2.sync();
        String set = sync.set("master", "slave");
        System.out.println("Set Result V2 : " + set);
        redisV2.close();
    }

    @Test
    public void delTestV2() {
        StatefulRedisMasterReplicaConnection<String, String> redisV2 = getRedisV2();
        RedisCommands<String, String> sync = redisV2.sync();
        Long master = sync.del("master");
        System.out.println("Del Result V2 : " + master);
    }


}
