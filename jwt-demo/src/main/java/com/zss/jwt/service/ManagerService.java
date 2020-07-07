package com.zss.jwt.service;

import com.zss.jwt.entity.Manager;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/7 11:00
 * @desc manager 服务层接口
 */
public interface ManagerService {

    /**
     * 添加管理员
     *
     * @param manager 管理员实体
     * @return Boolean
     */
    Boolean addManagerService(Manager manager);

    /**
     * 查询管理员
     *
     * @return manager
     */
    Manager selectManagerByName(String username);

    /**
     * 用户名密码登录
     *
     * @param username 用户名
     * @param password 密码
     * @return Boolean
     */
    Boolean login(String username, String password);
}
