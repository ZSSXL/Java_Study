package com.zss.lambda;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 14:15
 * @desc Lambda表达式学习
 */
public class Program5 {

    public static void main(String[] args) {
        // 集合的遍历
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1,2,3,4,5,6,7,8,9,0);
        // 常见的遍历
        for (Integer i : list){
            System.out.println("Foreach : " + i);
        }
        // Lambda遍历
        // list.forEach(System.out::println)
        list.forEach((a) ->{
            System.out.println("A : " + a);
        });
    }
}
