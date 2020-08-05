package com.zss.lambda;

import com.zss.lambda.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 13:55
 * @desc Lambda表达式学习
 */
public class Program3 {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        userList.add(new User("小明", "123456", 13));
        userList.add(new User("小王", "123456", 14));
        userList.add(new User("小强", "123456", 15));
        userList.add(new User("小刘", "123456", 12));
        userList.add(new User("小白", "123456", 18));
        userList.add(new User("小梅", "123456", 10));
        userList.sort((o1, o2) -> o2.getAge() - o1.getAge());
        System.out.println(userList);
    }
}
