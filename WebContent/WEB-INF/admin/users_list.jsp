<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>
	
	<div class="mui--appbar-height"></div>
	<div class="mui-appbar mui--appbar-line-height">
		<div class="content-wrapper" id="title">
			<div class="mui-container">
				<h1>项目目标</h1>
			</div>
		</div>
	</div>

	<div class="content-wrapper">
		<div class="mui--appbar-height"></div>
		<div class="mui-container-fluid">
			<div class="mui-panel">
				<table class="mui-table">
					<thead>
						<tr>
							<th>编号</th>
							<th>用户名</th>
							<th>真实姓名</th>
							<th>身份证号</th>
							<th>联系电话</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>Alex</td>
							<td>张三</td>
							<td>33041234567898761X</td>
							<th>18812345677</th>
						</tr>
						<tr>
							<td>2</td>
							<td>Susan</td>
							<td>李四</td>
							<td>33041234567898762X</td>
							<th>18812345678</th>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</body>
</html>