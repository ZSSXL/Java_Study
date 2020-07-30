package com.zss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zss.mapper.DepartmentMapper;
import com.zss.pojo.Department;
import com.zss.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public List<Department> getDepts() {
		return departmentMapper.selectByExample(null);
	}

}
