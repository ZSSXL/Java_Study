package com.zss.postgres_demo.service;

import com.zss.postgres_demo.entity.Employee;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/6 13:57
 * @desc employee 服务层接口
 */
public interface EmployeeService {

    /**
     * 添加员工
     *
     * @param employee 员工实体
     * @return Boolean
     */
    Boolean addEmployee(Employee employee);

    /**
     * 批量添加员工
     *
     * @param employeeList 多个员工实体
     * @return Boolean
     */
    Boolean addManyEmployees(List<Employee> employeeList);

    /**
     * 查询所有员工
     *
     * @return List<Employee>
     */
    List<Employee> selectAllEmployees();
}
