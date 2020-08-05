package com.zss.lambda.functional;

import com.zss.lambda.model.User;

import java.util.function.*;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 14:31
 * @desc 系统内置的一些函数式接口
 */
public class FunctionalInterface {

    public static void main(String[] args) {
        // 系统内置的一些函数式接口
        // Predicate<T>     :   参数T     返回值 boolean
        // Consumer<T>      :   参数T     返回值 void
        // Function<T, R>   :   参数T     返回值R
        // Supplier<T>      :   参数无    返回值T
        // UnaryOperator    :   参数T     返回值T
        // BinaryOperator<T>    :   参数T, T     返回值T
        // BiFunction<T, U, R>  :   参数T, U     返回值R
        // BiPredicate<T, U>    :   参数T, U     返回值boolean
        // BiConsumer<T, U>     :   参数T, U     返回值void

    }
}
