package com.zss.lettuce.properties;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/11 10:42
 * @desc Lettuce哨兵 - 属性
 */
@SuppressWarnings("unused")
public class LettuceSentinelProperties extends LettuceSingleProperties {

    private String masterId;

    // ==================== Getter & Setter ==================== //

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    // ==================== Hash & Equals ==================== //

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    // ==================== ToString ==================== //

    @Override
    public String toString() {
        return "LettuceSentinelProperties{" +
                "masterId='" + masterId + '\'' +
                '}';
    }
}
