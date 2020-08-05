package com.zss.lambda;

import com.zss.lambda.model.User;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 14:07
 * @desc Lambda表达式学习
 */
public class Program4 {

    public static void main(String[] args) {
        // TreeSet<User> treeSet = new TreeSet<>(Comparator.comparingInt(User::getAge))
         TreeSet<User> treeSet = new TreeSet<>(((o1, o2) -> {
             if(o1.getAge() >= o2.getAge()){
                 return -1;
             } else {
                 return 1;
             }
         }));

        treeSet.add(new User("小明", "123456", 13));
        treeSet.add(new User("小王", "123456", 14));
        treeSet.add(new User("小强", "123456", 14));
        treeSet.add(new User("小刘", "123456", 12));
        treeSet.add(new User("小白", "123456", 18));
        treeSet.add(new User("小梅", "123456", 10));

        System.out.println(treeSet);
    }
}
