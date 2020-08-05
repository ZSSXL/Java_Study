package com.zss.lambda.impl;

import com.zss.lambda.interfaces.IComparator;

import java.math.BigDecimal;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 10:41
 * @desc 比较器
 */
public class Comparator implements IComparator {

    @Override
    public BigDecimal plus(String arg1, String arg2) {
        BigDecimal de = new BigDecimal(arg1);
        return de.add(new BigDecimal(arg2));
    }

    @Override
    public Integer subtract(Integer arg1, Integer arg2) {
        return arg1 - arg2;
    }
}
