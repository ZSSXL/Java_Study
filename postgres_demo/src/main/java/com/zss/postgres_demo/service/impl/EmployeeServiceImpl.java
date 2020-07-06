package com.zss.postgres_demo.service.impl;

import com.zss.postgres_demo.entity.Employee;
import com.zss.postgres_demo.repostitory.EmployeeRepository;
import com.zss.postgres_demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/6 13:58
 * @desc employee 服务层接口方法实现
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Boolean addEmployee(Employee employee) {
        Employee save = employeeRepository.save(employee);
        System.out.println("Save Employee : [" + save + "]");
        return true;
    }

    @Override
    public Boolean addManyEmployees(List<Employee> employeeList) {
        List<Employee> employees = employeeRepository.saveAll(employeeList);
        for (Employee emp : employees) {
            System.out.println("Many Employee : [" + emp + "]");
        }
        return true;
    }

    @Override
    public List<Employee> selectAllEmployees() {
        return employeeRepository.findAll();
    }
}
