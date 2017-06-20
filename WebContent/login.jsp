<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="mui/css/mui.min.css" rel="stylesheet" type="text/css" />
<link href="mui/css/mui.min.css" rel="stylesheet" type="text/css" />
<script src="mui/js/mui.min.js"></script>
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<title>登录</title>
</head>
<body>

	<div class="mui--appbar-height"></div>
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
				<input type="radio" name="user" id="ordinary" /><label for="user">学生用户</label> 
				<input type="radio" name="user" id="admin" /><label for="user">管理员</label> 
				<button class="mui-btn mui-btn--flat mui-btn--primary" type="button"
					value="注册" onclick="register.jsp">注册</button>
				<button class="mui-btn mui-btn--flat mui-btn--primary" type="reset"
					value="重置">重置</button>
				<button class="mui-btn mui-btn--primary" type="submit" 
					value="确认">确认</button>
			</form>
		</div>
	</div>

</body>
</html>