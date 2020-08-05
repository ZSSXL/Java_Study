package com.zss.lambda.interfaces;

import com.zss.lambda.model.User;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/8/5 13:41
 * @desc 接口
 */
@FunctionalInterface
public interface IUser {

    /**
     * 构建User
     *
     * @return user
     */
    User userBuilder();
}
