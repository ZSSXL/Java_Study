package com.zss.service;

import java.util.List;

import com.zss.pojo.Employee;

public interface EmployeeService {
	
	/**
	 * 获取所有员工信息
	 * @return
	 */
	public List<Employee> getAll();
	
	/**
	 * 保存员工信息
	 * @param emloyee
	 * @return
	 */
	public int saveEmp(Employee emloyee);
	
	/**
	 * 校验用户名是否重复
	 * @return
	 */
	public int existByName(String name);
	
	/**
	 * 通过员工id查询信息
	 * @param id
	 * @return
	 */
	public Employee selectById(int id);
	
	/**
	 * 有选择的更新员工信息
	 * @param employee
	 * @return
	 */
	public int updateBySelective(Employee employee);
	
	/**
	 * 根据id单个删除员工信息
	 * @param id
	 * @return
	 */
	public int deleteById(int id);
	
	/**
	 * 批量删除用户信息
	 * @param ids
	 * @return
	 */
	public int deleteBatch(List<Integer> ids);
}
