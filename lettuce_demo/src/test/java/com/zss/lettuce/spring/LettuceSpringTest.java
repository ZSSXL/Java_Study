package com.zss.lettuce.spring;

import com.zss.lettuce.BaseTest;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.masterreplica.StatefulRedisMasterReplicaConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/11 13:39
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LettuceSpringTest extends BaseTest {

    @Autowired
    @Qualifier("singleRedisConnection")
    private StatefulRedisConnection<String, String> singleConnection;

    @Autowired
    @Qualifier("replicaRedisConnection")
    private StatefulRedisMasterReplicaConnection<String, String> replicaConnection;

    @Test
    public void singleSetTest() {
        RedisCommands<String, String> sync = singleConnection.sync();
        String set = sync.set("local", "ganzhou");
        System.out.println("Set Result : " + set);
    }

    @Test
    public void replicaSetTest() {
        RedisCommands<String, String> sync = replicaConnection.sync();
        String set = sync.set("country", "China");
        System.out.println("Replica Set Result : " + set);
    }

    @Test
    public void replicaGetTest() {
        RedisCommands<String, String> sync = replicaConnection.sync();
        String country = sync.get("country");
        System.out.println("Replica Get Result : " + country);
    }
}
