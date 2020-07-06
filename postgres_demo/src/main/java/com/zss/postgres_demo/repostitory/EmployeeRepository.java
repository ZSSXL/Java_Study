package com.zss.postgres_demo.repostitory;

import com.zss.postgres_demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/6 13:22
 * @desc employee 持久化
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
