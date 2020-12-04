package com.zss.app.repository;

import com.zss.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/12/3 17:11
 * @desc 持久化
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
