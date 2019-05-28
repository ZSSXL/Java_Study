package com.zss.controller;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zss.pojo.Employee;
import com.zss.pojo.Message;
import com.zss.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeServiceImpl;
	
	
	/**
	 * 单个/批量二合一
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Message deleteEmpById(@PathVariable("ids") String ids) {
		int index = 0;
		// 批量删除
		if(ids.contains("-")) {
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			for (String str_id : str_ids) {
				del_ids.add(Integer.parseInt(str_id));
			}
			for (Integer idsss : del_ids) {
				// 执行多遍单个删除
				index = employeeServiceImpl.deleteById(idsss);
				if(index == 1) {
					return Message.success();
				}else {
					return Message.fail();
				}
			}
			return Message.success();
		}else {	// 单个删除
			index = employeeServiceImpl.deleteById(Integer.parseInt(ids));
			if(index == 1) {
				return Message.success();
			}else {
				return Message.fail();
			}
		}
	}
	
	
	/**
	 * 新增用户，并保存
	 * 1、支持JSR303校验
	 * 2、导入Hibernate-Validator
	 * @param emp
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Message saveEmp(@Valid Employee emp,BindingResult result) {
		if(result.hasErrors()) {
			// 校验失败，返回失败,在模态框中显示失败的错误信息
			Map<String,Object> map = new HashMap<>();
			
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError error : fieldErrors) {
				System.out.println("错误的字段名:"+error.getField());
				System.out.println("错误的信息："+error.getDefaultMessage());
				map.put(error.getField(),error.getDefaultMessage());
			}
			return Message.fail().add("errorFields", map);
		}else {
			int index = employeeServiceImpl.saveEmp(emp);
			 if(index == 1) {
				 return Message.success();
			 }
			return Message.fail();
		}
	}
	
	/**
	 * 校验用户是否存在可用
	 * @param empName
	 * @return
	 */
	@RequestMapping(value="/checkUser",method=RequestMethod.POST)
	@ResponseBody
	public Message userExist(@RequestParam("empName") String empName) {
		// 先判断用户名是否合法  // /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if(!empName.matches(regx)) {
			return Message.fail().add("va_msg", "用户名可以是2-5位中文或者6-16位英文和数字的组合!");
		}

		// 再进行数据库进行重复叫用
		int index = employeeServiceImpl.existByName(empName);
		if(index == 0) {
			return Message.success();
		}else {
			return Message.fail().add("va_msg", "用户名不可用");
		}
	}
	
	/**
	 * 根据员工id查询用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Message selectById(@PathVariable("id") int id) {
		Employee employee = employeeServiceImpl.selectById(id);
		return Message.success().add("emp",employee);
	}
	
	/**
	 * 
	 * 员工更新
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Message updateBySelective(Employee employee) {
		// System.out.println("将要更新的员工数据："+employee);
		int index = employeeServiceImpl.updateBySelective(employee);
		if(index == 1) {
			// System.out.println("更新成功");
			return Message.success();
		}else {
			// System.out.println("更新失败");
			return Message.fail();
		}
		
	}
	
	/**
	 * 导入json包
	 * 显示员工数据表格
	 * @param pn
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Message getEmpsJson(@RequestParam(value="pn",defaultValue="1") Integer pn) {
		// 在查询治安只需要调用，传入页码，以及每页的大小， pn:pageNumber
		PageHelper.startPage(pn,5);
		// startPage后面紧跟的这个查询就是一个分页
		List<Employee> emps = employeeServiceImpl.getAll();
				
		// 使用pageInfo对结果进行包装,只需要将pageInfo交给页面就行了
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo<Employee> page = new PageInfo<Employee>(emps,5);
		return Message.success().add("pageInfo", page);
	}
	
	//@RequestMapping("/")
	public String showEmps(@RequestParam(value="pn",defaultValue="1") Integer pn,Model model) {
		
		// 在查询治安只需要调用，传入页码，以及每页的大小， pn:pageNumber
		PageHelper.startPage(pn,5);
		// startPage后面紧跟的这个查询就是一个分页
		List<Employee> emps = employeeServiceImpl.getAll();
		
		// 使用pageInfo对结果进行包装,只需要将pageInfo交给页面就行了
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo<Employee> page = new PageInfo<Employee>(emps,5);
		model.addAttribute("pageInfo", page);
		
		return "list";
	}
}
