package com.zss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zss.mapper.EmployeeMapper;
import com.zss.pojo.Employee;
import com.zss.pojo.EmployeeExample;
import com.zss.pojo.EmployeeExample.Criteria;
import com.zss.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
	}

	@Override
	public int saveEmp(Employee employee) {
		int index = employeeMapper.insertSelective(employee);
		return index;
	}

	@Override
	public int existByName(String empName) {
		return  employeeMapper.exist(empName);
	}

	@Override
	public Employee selectById(int id) {
		return employeeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateBySelective(Employee employee) {
		return employeeMapper.updateByPrimaryKeySelective(employee);
	}

	@Override
	public int deleteById(int id) {
		return employeeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteBatch(List<Integer> ids) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpIdIn(ids);
		return employeeMapper.deleteByExample(example);
	}

}
