package com.zss.shirodemo.mapper;

import com.zss.shirodemo.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author ZSS
 * @date 2019/9/25 15:32
 * @description user mapper
 */
public interface UserMapper {

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return User
     */
    User findByUsername(@Param("username") String username);

}
