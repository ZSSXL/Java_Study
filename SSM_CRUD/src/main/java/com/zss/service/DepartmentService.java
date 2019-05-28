package com.zss.service;

import java.util.List;

import com.zss.pojo.Department;

public interface DepartmentService {
	
	/**
	 * 获取所有部门信息
	 * @return
	 */
	public List<Department> getDepts();
}
