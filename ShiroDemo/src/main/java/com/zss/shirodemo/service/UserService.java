package com.zss.shirodemo.service;

import com.zss.shirodemo.pojo.User;

/**
 * @author ZSS
 * @date 2019/9/25 15:33
 * @description user service
 **/
public interface UserService {

    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return User
     */
    User getUserByUsername(String username);

}
