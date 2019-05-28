package com.zss.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zss.pojo.Department;
import com.zss.pojo.Employee;
import com.zss.pojo.Message;
import com.zss.service.DepartmentService;
import com.zss.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class ServiceTest {
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	@Autowired
	private DepartmentService departmentServiceImpl;
	
	@Test
	public void EmpTest() {
		List<Employee> all = employeeServiceImpl.getAll();
		if(all != null) {
			for (Employee emp : all) {
				System.out.println(emp);
			}	
		}else {
			System.out.println("There is no thing");
		}
		
		System.out.println("success");
	}
	
	@Test
	public void DeptTest() {
		List<Department> depts = departmentServiceImpl.getDepts();
		for (Department dept : depts) {
			System.out.println(dept);
		}
		System.out.println("success");

	}
	
	@Test
	public void InsertEmpTest() {
		Employee employee = new Employee(null,"王二麻子","M","123456@qq.com",2,null);
		int index = employeeServiceImpl.saveEmp(employee);
		if(index == 1) {
			System.out.println("保存成功");
		}else {
			System.out.println("保存失败");
		}
	}
	
	@Test
	public void SelectByNameTest() {
		int index = employeeServiceImpl.existByName("王二麻子");
		if(index == 0) {
			System.out.println("没有这个人");
		}else {
			System.out.println(index);
		}
	}
	
	@Test
	public void UpdateTest() {
		Employee employee = new Employee(501,"王二麻子","M","wangermazi@qq.com",5,null);
		int index = employeeServiceImpl.updateBySelective(employee);
		if(index == 1) {
			System.out.println("更新成功");
		}else {
			System.out.println("更新失败"); 
		}
	}
	
	@Test
	public void deleteEmpByIdTest() {
		int index = employeeServiceImpl.deleteById(508);
		if(index == 1) {
			System.out.println("删除成功");
		}else {
			System.out.println("删除失败");
		}
	}
	
}
