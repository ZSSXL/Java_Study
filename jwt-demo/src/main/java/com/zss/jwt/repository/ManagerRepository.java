package com.zss.jwt.repository;

import com.zss.jwt.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/7 10:54
 * @desc Manager - 持久化
 */
@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {

    /**
     * 管理员登录
     *
     * @param managerName     管理员名称
     * @param managerPassword 管理员密码
     * @return Manager
     */
    Manager findByManagerNameAndManagerPassword(String managerName, String managerPassword);

    /**
     * 通过用户名查询管理员信息
     *
     * @param managerName 用户名
     * @return Manager
     */
    Manager findByManagerName(String managerName);
}
