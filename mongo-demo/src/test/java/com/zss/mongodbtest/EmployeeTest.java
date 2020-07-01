package com.zss.mongodbtest;

import com.zss.mongodbtest.model.entity.Employee;
import com.zss.mongodbtest.service.EmployeeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/1 10:34
 * @desc Employee 测试
 */
public class EmployeeTest extends BaseTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void insertEmployeeTest() {
        Employee zhangsan = Employee.builder()
                .empId("1001")
                .empName("zhangsan")
                .empEmail("zhangsan@123.com")
                .empPhone("1234567789")
                .build();
        Boolean result = employeeService.insertNewEmployee(zhangsan);
        System.out.println("Insert result : " + result);
    }

    /**
     * 多个员工插入测试
     */
    @Test
    public void insertManyEmployeeTest() {
        List<Employee> employeeList = new ArrayList<>();
        for(int i = 2; i <= 10; i ++){
            employeeList.add(Employee.builder()
                    .empId("100" + i)
                    .empEmail("1234" + i + "@123.com")
                    .empPhone("123456" + i)
                    .empName("hh" + i)
                    .build());
        }
        Boolean result = employeeService.insertManyEmployee(employeeList);
        System.out.println("Insert result : " + result);
    }

    @Test
    public void findAllEmployeeTest() {
        List<Employee> allEmployee = employeeService.findAllEmployee();
        for (Employee employee : allEmployee) {
            System.out.println(employee);
        }
    }
}
