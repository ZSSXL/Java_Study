package com.zss.lambda;

import com.zss.lambda.impl.Comparator;
import com.zss.lambda.interfaces.IComparator;
import com.zss.lambda.interfaces.ILambda;
import com.zss.lambda.interfaces.ILambda2;
import com.zss.lambda.interfaces.ILambda3;

import java.math.BigDecimal;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 10:40
 * @desc lambda - 学习
 */
public class Program {

    public static void main(String[] args) {
        // 使用接口实现类
        Comparator comparator = new Comparator();
        System.out.println("Plus : " + comparator.plus("101", "201"));

        // 使用匿名内部类
        IComparator iComparator = new IComparator() {
            @Override
            public BigDecimal plus(String arg1, String arg2) {
                BigDecimal de = new BigDecimal(arg1);
                return de.add(new BigDecimal(arg2));
            }

            @Override
            public Integer subtract(Integer arg1, Integer arg2) {
                return arg1 - arg2;
            }
        };
        System.out.println("No Name Plus :" + iComparator.plus("1002", "1003"));

        // lambda 表达式
        ILambda lambda = Integer::sum;
        System.out.println("sub : " + lambda.sub(1, 2));

        ILambda2 lambda2 = (content) -> System.out.println("Content : " + content);
        lambda2.print("Content Test");

        ILambda3 lambda3 = new Program()::function;
        System.out.println(lambda3.subString("hello", "_world"));

    }

    private String function(String arg1, String arg2) {
        String str = arg1 + arg2;
        System.out.println("String : " + str);
        return str;
    }
}
