package com.zss.repository;

import com.zss.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/14 9:50
 * @desc user 持久化
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
