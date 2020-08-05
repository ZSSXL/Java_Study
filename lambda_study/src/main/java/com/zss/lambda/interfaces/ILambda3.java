package com.zss.lambda.interfaces;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 13:29
 * @desc 接口
 */
@FunctionalInterface
public interface ILambda3 {

    /**
     * 字符串追加
     *
     * @param arg1 参数一
     * @param arg2 参数二
     * @return 输出结果
     */
    String subString(String arg1, String arg2);
}
