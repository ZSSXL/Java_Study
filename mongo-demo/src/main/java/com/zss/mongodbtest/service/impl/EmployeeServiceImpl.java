package com.zss.mongodbtest.service.impl;

import com.zss.mongodbtest.model.entity.Employee;
import com.zss.mongodbtest.repository.EmployeeRepository;
import com.zss.mongodbtest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/1 10:31
 * @desc Employee 服务层方法实现
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Boolean insertNewEmployee(Employee emp) {
        System.out.println("Employee : [" + emp + "]");
        Employee insert = employeeRepository.insert(emp);
        System.out.println("Response : " + insert);
        return true;
    }

    @Override
    public Boolean insertManyEmployee(List<Employee> employeeList) {
        List<Employee> insert = employeeRepository.insert(employeeList);
        for (Employee emp : insert) {
            System.out.println("[" + emp + "]");
        }
        return true;
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }
}
