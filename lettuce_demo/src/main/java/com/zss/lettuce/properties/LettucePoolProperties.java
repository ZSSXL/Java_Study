package com.zss.lettuce.properties;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/11 14:56
 * @desc Lettuce连接池 - 属性
 */
@SuppressWarnings("unused")
public class LettucePoolProperties {

    private Integer maxIdle;
    private Integer minIdle;
    private Integer maxTotal;
    private Long maxWaitMills;
    private Boolean testOnBorrow;
    private Boolean testOnReturn;
    private Boolean testOnCreate;

    // ==================== Getter & Setter ==================== //


    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Long getMaxWaitMills() {
        return maxWaitMills;
    }

    public void setMaxWaitMills(Long maxWaitMills) {
        this.maxWaitMills = maxWaitMills;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public Boolean getTestOnCreate() {
        return testOnCreate;
    }

    public void setTestOnCreate(Boolean testOnCreate) {
        this.testOnCreate = testOnCreate;
    }

    // ==================== ToString ==================== //

    @Override
    public String toString() {
        return "LettucePoolProperties{" +
                "maxIdle=" + maxIdle +
                ", minIdle=" + minIdle +
                ", maxTotal=" + maxTotal +
                ", maxWaitMills=" + maxWaitMills +
                ", testOnBorrow=" + testOnBorrow +
                ", testOnReturn=" + testOnReturn +
                ", testOnCreate=" + testOnCreate +
                '}';
    }
}
