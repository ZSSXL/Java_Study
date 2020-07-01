package com.zss.mongodbtest.repository;

import com.zss.mongodbtest.model.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/1 10:26
 * @desc employee 持久层
 */
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
