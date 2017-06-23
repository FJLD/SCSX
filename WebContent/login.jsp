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

	<script>
	function login() { 
		// jquery 表单提交 
		/* $("#login-form").ajaxSubmit(function(message) { 
			document.getElementById("notFound").innerHTML=message;
		}); 
		return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转  */
		$.post("Login.do", {username: "hello", 
			password: "fd", 
			power: "用户"}, function (callback){
			alert(callback);
		})
		
	} 
	</script>

	<div class="mui--appbar-height"></div>
	<div class="login-dialog">
	<div class="mui-container">
		<div class="mui-panel">
			<form id="login-form" action="Login.do" method="post" >
				<div>
					<div class="mui-textfield mui-textfield--float-label">
						<input type="text" name="username"> <label>用户名</label>
					</div>
					<div class="mui-textfield mui-textfield--float-label">
						<input type="password" name="password"> <label>密码</label>
					</div>
				</div>
				<div>
					<input type="radio" name="power" value="用户" checked="checked" /><label for="user">学生用户</label> 
					<input type="radio" name="power" value="管理员" /><label for="user">管理员</label> 
				</div>
				<div id="notFound">
				</div>
				<div>
					<button class="mui-btn mui-btn--flat mui-btn--primary" type="button"
						value="register" onclick="window.location.href='goToRegisterPage.do'">注册</button>
					<button class="mui-btn mui-btn--flat mui-btn--primary" type="reset"
						value="reset">重置</button>
					<button class="mui-btn mui-btn--primary" type="submit">确认</button>
				</div>
		        <br />
			</form>
		</div>
		</div>
	</div>
	<script src="https://cdn.bootcss.com/jquery/3.2.1/core.js"></script>
</body>
</html>