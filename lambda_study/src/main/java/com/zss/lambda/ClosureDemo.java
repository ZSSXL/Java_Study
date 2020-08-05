package com.zss.lambda;

import java.util.function.Supplier;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 14:51
 * @desc 闭包
 */
public class ClosureDemo {

    public static void main(String[] args) {
        Integer integer = getNumber().get();
        System.out.println(integer);

    }

    private static Supplier<Integer> getNumber(){
        int num = 10;
        return () -> num;
    }
}
