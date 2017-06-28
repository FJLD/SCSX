<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="mui/css/mui.min.css" rel="stylesheet" type="text/css" />
<link href="mui/css/mui.min.css" rel="stylesheet" type="text/css" />
<link href="css/login-dialog.css" rel="stylesheet" type="text/css" />
<link href="css/content-wrapper.css" rel="stylesheet" type="text/css" />
<script src="mui/js/mui.min.js"></script>
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/core.js"></script>
<title>登录电信网络学院</title>
</head>
<body class="colored-primary">
	<%
		String username = "";
		String password = "";
		Cookie[] cookies = request.getCookies();
		for (int i = 0; cookies != null && i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
			if ("username".equals(cookies[i].getName())) {
				username = cookies[i].getValue();
			} else if ("password".equals(cookies[i].getName())) {
				password = "";
			}
		}
		String msg = (String) request.getAttribute("msg");
		if (msg != null) {
			out.print(msg);
		}
	%>
	<div class="mui--appbar-height"></div>
	<div class="login-dialog mui-container">
		<div class="mui-panel">
			<form id="login-form" action="DoLogin.do" method="post">
				<div>
					<div class="mui--text-headline">登录</div>
					<div class="mui-textfield">
						<input type="text" name="UNAME" value=<%=username%>> <label>用户名</label>
					</div>
					<div class="mui-textfield">
						<input type="password" name="PW" value=<%=password%>> <label>密码</label>
					</div>
					<div class="mui-textfield inline">
						<input type="text" name="code"> <label>验证码</label>
						<img src="./codeServlet.do" onclick="changeCode()" />
						<a href="javascript:changeCode()">看不清换一张</a>
					</div>
				</div>
				<div class="toggle">
					<div class="mui-radio inline">
						<label><input type="radio" name="POWER" value="用户"
							checked="checked" />学生用户</label>
					</div>
					<div class="mui-radio inline">
						<label><input type="radio" name="POWER" value="管理员" />管理员</label>
					</div>
				</div>
				<div class="mui--text-caption wrong">${message }</div>
				<div>
					<span class="mui--pull-left">
						<button class="mui-btn mui-btn--flat mui-btn--primary"
							type="button" value="register"
							onclick="window.location.href='goToRegisterPage.do'">注册</button>
					</span> 
					<span class="mui--pull-right">
						<button class="mui-btn mui-btn--flat mui-btn--primary"
							type="reset" value="reset">重置</button>
						<button class="mui-btn mui-btn--primary" type="submit">确认</button>
					</span>
					<div class="mui--clearfix"></div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>