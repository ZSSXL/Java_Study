package com.zss.lambda;

import com.zss.lambda.interfaces.IUser;
import com.zss.lambda.interfaces.IUser2;
import com.zss.lambda.model.User;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 13:42
 * @desc Program2
 */
public class Program2 {

    public static void main(String[] args) {
        IUser iUser = () -> new User();
        // 构造方法的引用
        IUser iUser1 = User::new;
        iUser.userBuilder();
        iUser1.userBuilder();

        IUser2 iUser2 = User::new;
        User user = iUser2.userBuilder("xiaoming", "123456", 23);
        System.out.println("User : " + user);
    }
}
