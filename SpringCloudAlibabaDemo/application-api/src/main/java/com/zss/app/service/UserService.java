package com.zss.app.service;

import com.zss.app.entity.User;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/12/3 17:12
 * @desc 服务层接口
 */
public interface UserService {

    /**
     * 创建用户
     *
     * @param user 用户对象
     * @return User
     */
    User createUser(User user);

    /**
     * 获取所有用户
     *
     * @return All
     */
    List<User> getAllUsers();
}
