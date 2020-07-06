package com.zss.postgres_demo.repostitory;

import com.zss.postgres_demo.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/6 13:21
 * @desc Enterprise持久化
 */
@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, String> {
}
