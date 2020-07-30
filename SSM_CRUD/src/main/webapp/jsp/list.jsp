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
				<script src="${APP_PATH }/static/css/bootstrap.min.css"></script>
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
									<button class="btn btn-success">新增</button>
									<button class="btn btn-danger">删除</button>
							</div>
						</div>
						<!-- 表格数据 -->
						<div class="row">
								<div class="col-md-12">
										<table class="table table-hover">
												<tr>
														<th>员工ID</th>
														<th>姓名</th>
														<th>性别</th>
														<th>email</th>
														<th>部门名称</th>
														<th>操作</th>
												</tr>
												<c:forEach items="${pageInfo.list }" var="emp">
														<tr>
																<td>${emp.empId }</td>
																<td>${emp.empName }</td>
																<td>${emp.gender=="M"?"男":"女" }</td>
																<td>${emp.email }</td>
																<td>${emp.department.deptName}</td>
																<td>
																		<button class="btn btn-success btn-sm">
																				<span  class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
																				编辑
																		</button>
																		<button class="btn btn-danger btn-sm">
																				<span  class="glyphicon glyphicon-trash" aria-hidden="true"></span>
																				删除
																		</button>
																</td>
														</tr>
												</c:forEach>
										</table>
								</div>
						</div>
						<!-- 分页信息 -->
						<div class="row">
								<!-- 分页文字信息 -->
								<div class="col-md-6 ">
									 当前${pageInfo.pageNum }页,总共${pageInfo.pages }页,共${pageInfo.total }条记录
								</div>
								<!-- 分页条信息 -->
								<div class="col-md-6">
										<nav aria-label="Page navigation">
											  <ul class="pagination">
											  		<li><a href="${APP_PATH }/?pn=1">首页</a></li>
											  		<c:if test="${pageInfo.hasPreviousPage }">
											  				<li>
															      <a href="${APP_PATH }/?pn=${pageInfo.pageNum - 1}" aria-label="Previous">
															   	     <span aria-hidden="true">&laquo;</span>
															      </a>
														    </li>
											  		</c:if>
												    <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
															<c:if test="${page_Num == pageInfo.pageNum }">
																		<li class="active"><a href="#">${page_Num }</a></li>
															</c:if>
															<c:if test="${page_Num != pageInfo.pageNum }">
																		<li><a href="${APP_PATH }/?pn=${page_Num }">${page_Num }</a></li>
															</c:if>
												    </c:forEach>
												    <c:if test="${pageInfo.hasNextPage }">
												    		 <li>
															      <a href="${APP_PATH }/?pn=${pageInfo.pageNum + 1}" aria-label="Next">
															  	      <span aria-hidden="true">&raquo;</span>
															      </a>
														    </li>
												    </c:if>
												    <li><a href="${APP_PATH }/?pn=${pageInfo.pages}">尾页</a></li>
											  </ul>
										</nav>
								</div>
						</div>
				</div>
		</body>
</html>