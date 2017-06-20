<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/login-dialog.css" rel="stylesheet" type="text/css" />
<link href="mui/css/mui.min.css" rel="stylesheet" type="text/css" />
<link href="mui/css/mui.min.css" rel="stylesheet" type="text/css" />
<script src="mui/js/mui.min.js"></script>
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<title>登录</title>
</head>
<body class="colored-primary">
	<div class="mui--appbar-height"></div>
	<div class="login-dialog">
	<div class="mui-container">
		<div class="mui-panel">
			<form action="/SCSX/MyServlet" method="post">
				<div>
					<div class="mui-textfield mui-textfield--float-label">
						<input type="text" name="username"> <label>用户名</label>
					</div>
					<div class="mui-textfield mui-textfield--float-label">
						<input type="password" name="password"> <label>密码</label>
					</div>
				</div>
				<div>
					<input type="radio" name="user" value="ordinary" checked="checked" /><label for="user">学生用户</label> 
					<input type="radio" name="user" value="admin" /><label for="user">管理员</label> 
				</div>
				<div>
					<button class="mui-btn mui-btn--flat mui-btn--primary" type="button"
						value="register" onclick="register.jsp">注册</button>
					<button class="mui-btn mui-btn--flat mui-btn--primary" type="reset"
						value="reset">重置</button>
					<button class="mui-btn mui-btn--primary" type="submit" 
						value="confirm">确认</button>
				</div>
			</form>
		</div>
		</div>
	</div>
</body>
</html>