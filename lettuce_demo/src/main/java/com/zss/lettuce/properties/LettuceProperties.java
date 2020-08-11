package com.zss.lettuce.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/11 10:51
 * @desc Lettuce - 属性
 */
@SuppressWarnings("unused")
@ConfigurationProperties(prefix = "lettuce")
public class LettuceProperties {

    private LettuceSingleProperties single;
    private LettuceReplicaProperties replica;
    private LettuceSentinelProperties sentinel;
    private LettuceClusterProperties cluster;

    // ==================== Getter & Setter ==================== //


    public LettuceSingleProperties getSingle() {
        return single;
    }

    public void setSingle(LettuceSingleProperties single) {
        this.single = single;
    }

    public LettuceReplicaProperties getReplica() {
        return replica;
    }

    public void setReplica(LettuceReplicaProperties replica) {
        this.replica = replica;
    }

    public LettuceSentinelProperties getSentinel() {
        return sentinel;
    }

    public void setSentinel(LettuceSentinelProperties sentinel) {
        this.sentinel = sentinel;
    }

    public LettuceClusterProperties getCluster() {
        return cluster;
    }

    public void setCluster(LettuceClusterProperties cluster) {
        this.cluster = cluster;
    }

    // ==================== Hash & Equals ==================== //


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LettuceProperties that = (LettuceProperties) o;
        return Objects.equals(single, that.single) &&
                Objects.equals(replica, that.replica) &&
                Objects.equals(sentinel, that.sentinel) &&
                Objects.equals(cluster, that.cluster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(single, replica, sentinel, cluster);
    }

    // ==================== ToString ==================== //


    @Override
    public String toString() {
        return "LettuceProperties{" +
                "single=" + single +
                ", replica=" + replica +
                ", sentinel=" + sentinel +
                ", cluster=" + cluster +
                '}';
    }
}
