package com.zss.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zss.pojo.Department;
import com.zss.pojo.Message;
import com.zss.service.DepartmentService;

/**
 * 处理和部门有关的请求
 * @author 12711
 *
 */

@Controller
public class DeptarmentController {
	
	@Autowired
	private DepartmentService departmentServiceImpl;
	
	@RequestMapping("/depts")
	@ResponseBody
	public Message getDepts() {
		// 查出所有部门信息
		List<Department> depts = departmentServiceImpl.getDepts();
		return Message.success().add("depts", depts);
	}
}
