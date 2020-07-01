package com.zss.mongodbtest.service;

import com.zss.mongodbtest.model.entity.Employee;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/1 10:30
 * @desc Employee 服务层接口
 */
public interface EmployeeService {

    /**
     * 添加新员工
     *
     * @param emp 员工实体
     * @return Integer
     */
    Boolean insertNewEmployee(Employee emp);

    /**
     * 添加更多员工
     *
     * @param employeeList 多个员工
     * @return Boolean
     */
    Boolean insertManyEmployee(List<Employee> employeeList);

    /**
     * 获取所有员工信息
     *
     * @return List<Employee>
     */
    List<Employee> findAllEmployee();
}
