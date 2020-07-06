package com.zss.postgres_demo;

import com.zss.postgres_demo.entity.Employee;
import com.zss.postgres_demo.service.EmployeeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoushs@dist.com.cn
 * @date 2020/7/6 14:08
 * @desc employee 测试
 */
public class EmployeeTest extends BaseTest {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 添加员工测试
     */
    @Test
    public void addEmployeeTest() {
        Employee zhangsan = Employee.builder()
                .employeeId("1001")
                .employeeName("zhangsan")
                .employeeAge("23")
                .employeePhone("132448573382")
                .build();
        Boolean result = employeeService.addEmployee(zhangsan);
        System.out.println("Insert Result : [" + result + "]");
    }

    /**
     * 批量添加员工测试
     */
    @Test
    public void addManyEmployeesTest() {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 2; i <= 10; i++) {
            employeeList.add(Employee.builder()
                    .employeeId("100" + i)
                    .employeeName("emp + " + i)
                    .employeePhone("134594993" + i)
                    .employeeAge("2" + i)
                    .build());
        }
        Boolean result = employeeService.addManyEmployees(employeeList);
        System.out.println("Insert Result : [" + result + "]");
    }

    /**
     * selectAll - 测试
     */
    @Test
    public void selectAllEmployeeTest() {
        List<Employee> employeeList = employeeService.selectAllEmployees();
        for (Employee emp : employeeList) {
            System.out.println("Select Many Emp : [" + emp + "]");
        }
    }
}
