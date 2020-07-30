<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
		<head>
				<meta charset="UTF-8">
				<title>bootstrapTest</title>
				<%
					pageContext.setAttribute("APP_PATH", request.getContextPath());
				%>
				<!-- 引入jQuery -->
				<!-- 引入样式 -->
				<script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
				<link href="${APP_PATH }/static/css/bootstrap.min.css" rel="stylesheet">
				<script src="${APP_PATH }/static/js/bootstrap.min.js"></script>
		</head>
		<body>
					
			
				<<!-- 搭建显示页面 -->
				<div class="container">
						<!-- 标题 -->
						<div class="row">
								<div class="col-md-12">
										<h1>SSM-CRUD</h1>
								</div>
						</div>
						<!-- 按钮 -->
						<div class="row">
							<div class="col-md-4 col-md-offset-8">
									<button class="btn btn-success" id="emp_add_model_btn">新增</button>
									<button class="btn btn-danger" id="emp_delete_all_btn">删除</button>
							</div>
						</div>
						
						<!-- 员工添加模态框 -->
						<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
							  <div class="modal-dialog" role="document">
								    <div class="modal-content">
									      <div class="modal-header">
										        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										        <h4 class="modal-title" id="myModalLabel">员工添加</h4>
									      </div>
									      <div class="modal-body">
									        		<form class="form-horizontal">
															  <div class="form-group">
																	    <label class="col-sm-2 control-label">姓名</label>
																	    <div class="col-sm-10">
																	      		<input type="text" class="form-control" name="empName" id="empName_add_input" placeholder="请输入员工名称">
																	      		<span class="help-block"></span>
																	    </div>
															  </div>
															  <div class="form-group">
																	    <label class="col-sm-2 control-label">email</label>
																	    <div class="col-sm-10">
																	      		<input type="text" class="form-control" name="email" id="email_add_input" placeholder="123456@123.com">
																	      		<span class="help-block"></span>
																	    </div>
															  </div>
															  <div class="form-group">
															  			 <label class="col-sm-3 control-label">
																		 		   <input type="radio" name="gender" id="gender1_add_input" value="M" checked>
																		 		   男
																		  </label>
																		  <label class="col-sm-2 control-label">
																			 		 <input type="radio" name="gender" id="gender2_add_input" value="F">
																			 		 女
																		  </label>
															  </div>
															  
															  <div class="form-group">
																	    <label class="col-sm-2 control-label">部门名称</label>
																	    <div class="col-sm-4">
																	    		<!-- 部门提交 -->
																	      		<select class="form-control" name="dId" id="dept_add_select">
																					  
																				</select>
																	    </div>
															  </div>
															  <input type="reset" style="display:none;"/>
													</form>
									      </div>
										      <div class="modal-footer">
											        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
											        <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
										      </div>
								    </div>
							  </div>
						</div>
		
						<!-- 员工修改模态框 -->
						<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
							  <div class="modal-dialog" role="document">
								    <div class="modal-content">
									      <div class="modal-header">
										        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										        <h4 class="modal-title">员工修改</h4>
									      </div>
									      <div class="modal-body">
									        		<form class="form-horizontal">
															  <div class="form-group">
																	    <label class="col-sm-2 control-label">姓名</label>
																	    <div class="col-sm-10">
																	      		<p class="form-control-static" id="empName_update_static"></p>
																	    </div>
															  </div>
															  <div class="form-group">
																	    <label class="col-sm-2 control-label">email</label>
																	    <div class="col-sm-10">
																	      		<input type="text" class="form-control" name="email" id="email_update_input" placeholder="123456@123.com">
																	      		<span class="help-block"></span>
																	    </div>
															  </div>
															  <div class="form-group">
															  			 <label class="col-sm-3 control-label">
																		 		   <input type="radio" name="gender" id="gender1_update_input" value="M" checked>
																		 		   男
																		  </label>
																		  <label class="col-sm-2 control-label">
																			 		 <input type="radio" name="gender" id="gender2_update_input" value="F">
																			 		 女
																		  </label>
															  </div>
															  
															  <div class="form-group">
																	    <label class="col-sm-2 control-label">部门名称</label>
																	    <div class="col-sm-4">
																	    		<!-- 部门提交 -->
																	      		<select class="form-control" name="dId" id="dept_update_select">
																	      		
																				</select>
																	    </div>
															  </div>
															  <input type="reset" style="display:none;"/>
													</form>
									      </div>
										      <div class="modal-footer">
											        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
											        <button type="button" class="btn btn-primary" id="emp_update_btn">更新</button>
										      </div>
								    </div>
							  </div>
						</div>
						
						
						<!-- 表格数据 -->
						<div class="row">
								<div class="col-md-12">
										<table class="table table-hover" id="emps_table">
												<thead>
														<tr>
																<th>
																		<input type="checkbox" id="check_all"/>
																</th>
																<th>员工ID</th>
																<th>姓名</th>
																<th>性别</th>
																<th>email</th>
																<th>部门名称</th>
																<th>操作</th>
														</tr>
												</thead>
												<tbody>
												
												</tbody>
										</table>
								</div>
						</div>
						<!-- 分页信息 -->
						<div class="row">
								<!-- 分页文字信息 -->
								<div class="col-md-6 " id="page_info_area">
									
								</div>
								<!-- 分页条信息 -->
								<div class="col-md-6" id="page_nav_area">
										 
								</div>
						</div>
				</div>
				<script type="text/javascript">
				
						var totalRecord,currentPage;	// 保存分页总记录数
						// 1、页面加载完成以后，直接发送一个ajax请求，要到分页数据
						$(function(){
								// 去分页的第一页
								to_page(3)
						});
						
						function to_page(pn){
								$.ajax({
										url:"${APP_PATH}/list",
										data:"pn="+pn,
										type:"get",
										success:function(result){
												// console.log(result);
												// 1、解析并显示员工数据
												build_emps_table(result);
												// 2、解析显示分页信息
												build_page_info(result);
												// 3、解析显示分页条数据
												build_page_nav(result);
												
										}
								});
						}
						
						function build_emps_table(result){
							// 清空table表格
							$("#emps_table tbody").empty(); 
							
							var emps = result.extend.pageInfo.list;
							$.each(emps,function(index,item){
									var checkBoxTd = $("<td><input type='checkBox' class='check_item'/></td>");
									var empIdTd = $("<td></td>").append(item.empId);
									var empNameTd = $("<td></td>").append(item.empName);
									var empGenderTd = $("<td></td>").append(item.gender=='M'?"男":"女");
									var empEmailTd = $("<td></td>").append(item.email);
									var deptNameTd = $("<td></td>").append("deptName");
									/* 添加两个操作按钮 */
									var editBtn = $("<button></button>").addClass("btn btn-success btn-sm edit-btn")
																											   .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
																											   .append("编辑");
									// 为编辑按钮添加一个自定义的属性,来表示当前员工的id
									editBtn.attr("edit-id",item.empId);
									
									var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm delete-btn")
																										     .append($("<span></span>").addClass("glyphicon glyphicon-trash"))
																										     .append("删除");
									// 为删除按钮添加一个自定义的属性，来表示当前员工的id
									delBtn.attr("delete-id",item.empId);
									
									var btnTd = $("<td></td>").append(editBtn).append(delBtn);
									// append方法执行完成以后返回原来的元素
									$("<tr></tr>")
														   		 .append(checkBoxTd)
																 .append(empIdTd)
														     	 .append(empNameTd)
																 .append(empGenderTd)
																 .append(empEmailTd)
																  .append(deptNameTd)
																  .append(btnTd)
																  .appendTo("#emps_table tbody");
							});
						}
						
						// 解析显示分页记录信息
						function build_page_info(result){
								// 有则清空
								$("#page_info_area").empty();
							
								var pageinfo = result.extend.pageInfo;
								$("#page_info_area").append(" 当前"+pageinfo.pageNum+"页,总共"+pageinfo.pages+"页,共"+pageinfo.total+"条记录");
								totalRecord = pageinfo.total;
								currentPage = pageinfo.pageNum;
						}
						
						// 解析显示分页条
						function build_page_nav(result){
							
								// 有则清空
								$("#page_nav_area").empty();
								
								var ul = $("<ul></ul>").addClass("pagination");
								
								// 第一页
								var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
								
								// 前一页
								var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
								
								// 判断时候还有上一页，没有则disable
								if(result.extend.pageInfo.hasPreviousPage == false){
										firstPageLi.addClass("disable");
										prePageLi.addClass("disable");
								}else{
										// 跳转第一页点击事件								
										firstPageLi.click(function(){
											to_page(1);
										});
										// 跳转上一页点击事件
										prePageLi.click(function(){
											to_page(result.extend.pageInfo.pageNum - 1);
										});
								}''
								
								// 下一页
								var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
								
								// 最后一页
								var LastPageLi = $("<li></li>").append($("<a></a>").append("尾页").attr("href","#"));
								
								// 判断时候还有下一页，没有则disable
								if(result.extend.pageInfo.hasNextPage == false){
										nextPageLi.addClass("disable");
										LastPageLi.addClass("disable");
								}else{
										// 跳转下一页点击事件
										nextPageLi.click(function(){
											to_page(result.extend.pageInfo.pageNum + 1);
										});
										// 跳转最后一页点击事件
										LastPageLi.click(function(){
											to_page(result.extend.pageInfo.pages);
										});
								}
								
								
								ul.append(firstPageLi).append(prePageLi);
								
								$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
										var numLi =  $("<li></li>").append($("<a></a>").append(item));
										if(result.extend.pageInfo.pageNum == item){
											numLi.addClass("active");
										}
										numLi.click(function(){
												to_page(item);
										})
										ul.append(numLi);
								})
								// 添加下一页和尾页的提示
								ul.append(nextPageLi).append(LastPageLi);
								var navEle = $("<nav></nav>").append(ul);
								navEle.appendTo("#page_nav_area");
						}
						
						// 点击新增按钮
						$("#emp_add_model_btn").click(function(){
							
								// 清楚表单数据（表单 重置）
								 clearForm();
								
								// 发送ajax请求，查出部门信息，显示再下拉列表中
								getDepts("#empAddModal select");
								
								// 弹出模态框
								$("#empAddModal").modal({
										backdrop:"static"
								});
						});
						
						// 重置表单内容,样式
						function clearForm(){
							// 提取表单元素
							$("input[type]").trigger("click");// 出发隐藏的reset按钮
							
							// 重置表单样式
							$("#empAddModal").find("*").removeClass("has-error has-success");
							$("#empAddModal").find(".help-block").text("");
						}
						
						// 查出所有的部门信息，并显示在下拉列表中
						function getDepts(ele){
							$(ele).empty();
								$.ajax({
										url:"${APP_PATH}/depts",
										type:"get",
										success:function(result){
												// console.log(result);
												//显示部门信息再下拉列表中
												// $("#empAddModel select").append("")
												$.each(result.extend.depts,function(){
														var optionEle = $("<option></option>").append(this.deptName).attr("value",this.deptId);
														optionEle.appendTo(ele);
												});
										}
								});
						}
						
						// 检验表单数据方法
						function validate_add_form(){
								// 1、拿到要校验的数据，使用正则表达式
								
								// 校验邮箱
								var empName = $("#empName_add_input").val();
								var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;	// 允许a-zA-Z0-9，6-16位，汉字二到五位
						 		// alert(regName.test(empName));
								if(!regName.test(empName)){
									// alert("用户名可以是2-5位中文或者6-16位英文和数字的组合!");
									// 先清空之前的样式
									show_validate_msg("#empName_add_input","error","用户名可以是2-5位中文或者6-16位英文和数字的组合!");
									return false;
								}else{
									show_validate_msg("#empName_add_input","success","");
								}
								
								// 校验邮箱
								var email = $("#email_add_input").val();
								var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
								if(!regEmail.test(email)){
									// alert("邮箱格式不正确");
									show_validate_msg("#email_add_input","error","邮箱格式不正确");
									return false;
								}else{
									show_validate_msg("#email_add_input","success","");
								}
								return true;
						};
						
						// 显示校验信息
						function show_validate_msg(ele,status,msg){
							// 清楚当前元素的校验状态
								$(ele).parent().removeClass("has-success has-error");
								$(ele).next("sqan").text("");
								if("success" == status){
									$(ele).parent().addClass("has-success");
									$(ele).next("span").text(msg);
								}else if("error" == status){
									$(ele).parent().addClass("has-error");
									$(ele).next("span").text(msg);
								}
						}
						
						// 
						$("#empName_add_input").change(function(){
								// 发送ajax请求，验证用户名是否重复
								var empName = this.value;
								$.ajax({
										url:"${APP_PATH}/checkUser",
										data:"empName="+empName,
										type:"POST",
										success:function(result){
											if(result.code == 404){
												show_validate_msg("#empName_add_input","error",result.extend.va_msg);
												$("#emp_save_btn").attr("ajax-va","error");
											}else{
												show_validate_msg("#empName_add_input","success","用户名可用");
												$("#emp_save_btn").attr("ajax-va","success");
											}
										}
								});
						});
						
						// 点击保存，保存员工
						$("#emp_save_btn").click(function(){
							
								// 1、将模态框中填写的表单数据提交给服务器中进行保存
								// 2、先对要提交给服务器的数据进行校验
								// validate_add_form();
								if(!validate_add_form()){
									return false;
								}
								// 3、判断校验是否成功，成功了则继续往下走，不成功则提示并退出
								if($(this).attr("ajax-va")=="error"){
									return false;
								}
								
								// 4、发送ajax请求，保存员工
								//$("#empAddModel form").serialize();
								 $.ajax({
										url:"${APP_PATH}/emp",
										type:"POST",
										data:$("#empAddModal form").serialize(),
										success:function(result){
												 // alert("添加成功！！！");
												 
												 if(result.code == 200){
														// 员工保存成功
														// 1、关闭模态框
														$("#empAddModal").modal("hide");												
														// 2、来到最后一页，显示刚在保存的数据
														// 发送ajax请求，显示最后一页数据
														to_page(totalRecord);
												 }else{
													 // 显示失败信息
													 // console.log();
													 // 哪个字段的错误信息就显示哪个字段if
													 
													 if(undefined != result.extend.errorFields.email){
														 // 显示邮箱的错误信息
														 show_validate_msg("#email_add_input","error",result.extend.errorFields.email);
													 }
													 if(undefined != result.extend.errorFields.email){
														 	//显示员工名字的错误信息
														 show_validate_msg("#empName_add_input","error",result.extend.errorFields.empName);
													 }	 
												 }
										}
								}); 
						});
						
						// 问题：因为按钮创建之前就绑定了click，所以是绑定不了
						// 解决：1、可以在创建按钮的时候绑定	1、绑定单机事件
						$(document).on("click",".edit-btn",function(){
								// 1、点击编辑弹出模态框，并显示部门列表
								
								// 2、查出员工信息，显示员工信息
							 getDepts("#empUpdateModal select");
								//查询员工信息
								getEmp($(this).attr("edit-id"));
							// 弹出模态框
							// 把员工id传递给模态框的更新按钮
							$("#emp_update_btn").attr("edit-id",$(this).attr("edit-id"));
							
							$("#empUpdateModal").modal({
									backdrop:"static"
							});
						});
						
						// 查询员工信息并显示
						function getEmp(id){
								$.ajax({
										url:"${APP_PATH}/emp/"+id,
										type:"GET",
										success:function(result){
												// console.log(result);
												var empData = result.extend.emp;
												$("#empName_update_static").text(empData.empName);
												$("#email_update_input").val(empData.email);
												$("#empUpdateModal input[name=gender]").val([empData.gender]);
												$("#empUpdateModal select").val([empData.dId]);
										}
								});
						}
						
						// 点击更心，更新员工信息
						$("#emp_update_btn").click(function(){
								// 验证邮箱是否合法
								// 校验邮箱
								var email = $("#email_update_input").val();
								var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
								if(!regEmail.test(email)){
									// alert("邮箱格式不正确");
									show_validate_msg("#email_update_input","error","邮箱格式不正确");
									return false;
								}else{
									show_validate_msg("#email_update_input","success","");
								}
								
								// 发送ajax请求，保存用户更新数据
								$.ajax({
										url:"${APP_PATH}/emp/"+$(this).attr("edit-id"),
										type:"PUT",
										data:$("#empUpdateModal form").serialize(),
										success:function(result){
											alert(result.msg);
											// 员工信息更新成功
											// 1、关闭模态框
											$("#empUpdateModal").modal("hide");
											// 回到本页面
											to_page(currentPage);
										}
								});
						});
						
						// 实现单个删除
						$(document).on("click",".delete-btn",function(){
								// 1、弹出确认删除对话框
								 // alert($(this).parents("tr").find("td:eq(1)").text());
								var empName = $(this).parents("tr").find("td:eq(2)").text();
								
								var empId = $(this).attr("delete-id");
								
								if(confirm("确认删除【"+empName+"】吗？")){
										// 确认，返送ajax删除请求
										$.ajax({
												url:"${APP_PATH}/emp/"+empId,
												type:"DELETE",
												success:function(result){
														// alert(result.msg);
														to_page(currentPage);
												}
												
										});
								}
						});
						
						// 完成全选/全部选按钮
						$("#check_all").click(function(){
								// attr获取checked是undefined
								// 原生dom属性，attr获取自定义属性的值
								// 使用prop读取和修改dom原生属性的值
								// alert($(this).prop("checked"));
								$(".check_item").prop("checked",$(this).prop("checked"));
								
							
						});
						
						// check_item
						$(document).on("click",".check_item",function(){
								// 判断当前选中的元素是否为五个
								// alert($(".check_item:checked").length);
								// 判断选中的个数和显示的记录总条数是否一致，返回boolean值
								var flag = $(".check_item:checked").length == $(".check_item").length;
								
								$("#check_all").prop("checked",flag);
						});
						
						// 点击全部删除，就批量删除
						$("#emp_delete_all_btn").click(function(){
								// $(".check_item:checked")
								var empNames = "";
								var del_idStr = "";
								$.each( $(".check_item:checked"),function(){
										empNames +=  $(this).parents("tr").find("td:eq(2)").text()+",";
										// 组装员工id字符串
										del_idStr += $(this).parents("tr").find("td:eq(1)").text()+"-";
								});
								
								// 去除empNames多以的逗号
								empNames = empNames.substring(0,empNames.length-1);
								del_idStr = del_idStr.substring(0,del_idStr.length-1);
								if(confirm("确认删除【"+empNames+"】吗？")){
									// 确认，返送ajax批量删除请求
									$.ajax({
											url:"${APP_PATH}/emp/"+del_idStr,
											type:"DELETE",
											success:function(result){
													alert(result.msg);
													// to_page(currentPage);
											}
									});
							}
						});
				</script>
		</body>
</html>