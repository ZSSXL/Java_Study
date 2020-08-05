package com.zss.lambda.interfaces;

import java.math.BigDecimal;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 10:41
 * @desc
 */
public interface IComparator {

    /**
     * 两数相加
     *
     * @param arg1 第一个数
     * @param arg2 第二个数
     * @return 相加结果
     */
    BigDecimal plus(String arg1, String arg2);

    /**
     * 减法
     *
     * @param arg1 被减数
     * @param arg2 减数
     * @return 计算结果
     */
    Integer subtract(Integer arg1, Integer arg2);

    /**
     * 两数相乘
     *
     * @param arg1 乘数
     * @param arg2 乘数
     * @return 计算结果
     */
    default BigDecimal multiply(String arg1, String arg2) {
        BigDecimal bigDecimal = new BigDecimal(arg1);
        BigDecimal bigDecimal1 = new BigDecimal(arg2);
        return bigDecimal.multiply(bigDecimal1);
    }
}
