package com.zss.lambda.interfaces;

import com.zss.lambda.model.User;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 13:46
 * @desc 接口
 */
public interface IUser2 {

    /**
     * 构建User
     *
     * @param username 用户名
     * @param password 密码
     * @param age      年龄
     * @return User
     */
    User userBuilder(String username, String password, Integer age);
}
