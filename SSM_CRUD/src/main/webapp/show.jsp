<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
				<meta charset="UTF-8">
				<title>输出显示所有信息</title>
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
			<div class="container">
					<table class="table table-hover">
							<tr>
									<th>ID</th>
									<th>NAME</th>
									<th>PASSWORD</th>
							</tr>
							<c:forEach items="${users }" var="user">
									<tr>
											<td>${user.id }</td>
											<td>${user.uname }</td>
											<td>${user.pwd }</td>
									</tr>
							</c:forEach>
					</table>
			</div>
	</body>
</html>