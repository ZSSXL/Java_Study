package com.zss.lambda.interfaces;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 11:27
 * @desc 接口
 */
@FunctionalInterface
public interface ILambda {

    /**
     * 两数相加
     *
     * @param arg1 加数
     * @param arg2 家数
     * @return 计算结果
     */
    Integer sub(Integer arg1, Integer arg2);
}
