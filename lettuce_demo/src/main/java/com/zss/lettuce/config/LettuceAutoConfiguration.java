package com.zss.lettuce.config;

import com.zss.lettuce.properties.LettucePoolProperties;
import com.zss.lettuce.properties.LettuceProperties;
import com.zss.lettuce.properties.LettuceReplicaProperties;
import com.zss.lettuce.properties.LettuceSingleProperties;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.masterreplica.MasterReplica;
import io.lettuce.core.masterreplica.StatefulRedisMasterReplicaConnection;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/11 10:44
 * @desc Lettuce自动配置
 */
@Configuration
@ConditionalOnClass
@EnableConfigurationProperties(LettuceProperties.class)
public class LettuceAutoConfiguration {

    private final LettuceProperties lettuceProperties;

    public LettuceAutoConfiguration(LettuceProperties lettuceProperties) {
        this.lettuceProperties = lettuceProperties;
    }

    @Bean(destroyMethod = "shutdown")
    public ClientResources clientResources() {
        return DefaultClientResources.create();
    }

    // ========================== Single配置 Start ========================== //

    @Bean
    @ConditionalOnProperty(name = "lettuce.single.host")
    public RedisURI singleRedisUri() {
        LettuceSingleProperties single = lettuceProperties.getSingle();
        return RedisURI.builder()
                .withHost(single.getHost())
                .withPort(single.getPort())
                .withDatabase(single.getDatabase())
                .withTimeout(Duration.of(single.getTimeout(), ChronoUnit.SECONDS))
                .build();
    }

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnProperty(name = "lettuce.single.host")
    public RedisClient singleRedisClient(ClientResources clientResources,
                                         @Qualifier("singleRedisUri") RedisURI singleRedisUri) {
        return RedisClient.create(clientResources, singleRedisUri);
    }

    /*
    /**
     * 简单的实现
    @Bean(destroyMethod = "close")
    @ConditionalOnProperty(name = "lettuce.single.host")
    public StatefulRedisConnection<String, String> singleRedisConnection(
            @Qualifier("singleRedisClient") RedisClient singleRedisClient) {
        return singleRedisClient.connect();
    }*/

    /**
     * Redis连接池配置
     *
     * @param singleRedisClient redis客户端实例
     * @return 连接池实例
     * 问题：本来想@Bean(destroyMethod = "returnObject")
     * -----但是由于returnObject(T)这个方法需要带参，所有不行
     */
    @Bean
    @ConditionalOnProperty(name = "lettuce.single.host")
    public GenericObjectPool<StatefulRedisConnection<String, String>> singleRedisPool(
            @Qualifier("singleRedisClient") RedisClient singleRedisClient) {
        LettucePoolProperties pool = lettuceProperties.getPool();
        GenericObjectPoolConfig<StatefulRedisConnection<String, String>> poolConfig =
                new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(pool.getMaxIdle());
        poolConfig.setMinIdle(pool.getMinIdle());
        poolConfig.setMaxTotal(pool.getMaxTotal());
        poolConfig.setTestOnBorrow(pool.getTestOnBorrow());
        poolConfig.setTestOnReturn(pool.getTestOnReturn());
        poolConfig.setTestOnCreate(pool.getTestOnCreate());
        poolConfig.setMaxWaitMillis(pool.getMaxWaitMills());
        return ConnectionPoolSupport
                .createGenericObjectPool(singleRedisClient::connect, poolConfig);
    }

    @Bean(destroyMethod = "close")
    @ConditionalOnProperty(name = "lettuce.single.host")
    public StatefulRedisConnection<String, String> singleRedisConnection(
            @Qualifier("singleRedisPool") GenericObjectPool<StatefulRedisConnection<String, String>> singleRedisPool) {
        try {
            return singleRedisPool.borrowObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // ========================== Single配置 End ========================== //

    // ========================== Replica配置 Start ========================== //

    @Bean
    @ConditionalOnProperty(name = "lettuce.replica.host")
    public RedisURI replicaRedisUri() {
        LettuceReplicaProperties replica = lettuceProperties.getReplica();
        return RedisURI.builder()
                .withHost(replica.getHost())
                .withPort(replica.getPort())
                .withDatabase(replica.getDatabase())
                .withTimeout(Duration.of(replica.getTimeout(), ChronoUnit.SECONDS))
                .build();
    }

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnProperty(name = "lettuce.replica.host")
    public RedisClient replicaRedisClient(ClientResources clientResources,
                                          @Qualifier("replicaRedisUri") RedisURI replicaRedisUri) {
        return RedisClient.create(clientResources, replicaRedisUri);
    }

    @Bean(destroyMethod = "close")
    @ConditionalOnProperty(name = "lettuce.replica.host")
    public StatefulRedisMasterReplicaConnection<String, String> replicaRedisConnection(
            @Qualifier("replicaRedisClient") RedisClient replicaRedisClient,
            @Qualifier("replicaRedisUri") RedisURI replicaRedisUri) {
        return MasterReplica.connect(replicaRedisClient, StringCodec.UTF8, replicaRedisUri);
    }
    // ========================== Replica配置 end ========================== //


    // ========================== Sentinel配置 Start ========================== //
    // todo 后面仔细学一下哨兵模式和集群操作
   /* @Bean
    @ConditionalOnProperty(name = "lettuce.sentinel.host")
    public RedisURI sentinelRedisUri() {
        LettuceSentinelProperties sentinel = lettuceProperties.getSentinel();
        return RedisURI.builder()
                .withDatabase(sentinel.getDatabase())
                .withSentinel(sentinel.getHost(), sentinel.getPort())
                .withSentinelMasterId(sentinel.getMasterId())
                .withTimeout(Duration.of(sentinel.getTimeout(), ChronoUnit.SECONDS))
                .build();
    }

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnProperty(name = "lettuce.sentinel.host")
    public RedisClient sentinelRedisClient(ClientResources resources,
                                           @Qualifier("sentinelRedisUri") RedisURI sentinelRedisUri) {
        return RedisClient.create(resources, sentinelRedisUri);
    }

    @Bean(destroyMethod = "close")
    @ConditionalOnProperty(name = "lettuce.sentinel.host")
    public StatefulRedisMasterReplicaConnection<String, String> sentinelRedisConnection(
            @Qualifier("sentinelRedisUri") RedisURI sentinelRedisUri,
            @Qualifier("sentinelRedisClient") RedisClient sentinelRedisClient) {
        return MasterReplica.connect(sentinelRedisClient, StringCodec.UTF8, sentinelRedisUri);
    }*/
    // ========================== Sentinel配置 end ========================== //

    // ========================== Cluster配置 Start ========================== //

    /*@Bean
    @ConditionalOnProperty(name = "lettuce.cluster.host")
    public RedisURI clusterRedisUri() {
        LettuceClusterProperties cluster = lettuceProperties.getCluster();
        return RedisURI.builder()
                .withHost(cluster.getHost())
                .withPort(cluster.getPort())
                .withDatabase(cluster.getDatabase())
                .build();
    }

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnProperty(name = "lettuce.cluster.host")
    public RedisClusterClient redisClusterClient(ClientResources clientResources, @Qualifier("clusterRedisUri") RedisURI redisUri) {
        return RedisClusterClient.create(clientResources, redisUri);
    }

    @Bean(destroyMethod = "close")
    @ConditionalOnProperty(name = "lettuce.cluster.host")
    public StatefulRedisClusterConnection<String, String> clusterConnection(RedisClusterClient clusterClient) {
        return clusterClient.connect();
    }*/
    // ========================== Cluster3配置 end ========================== //

}
