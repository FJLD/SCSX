<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录</title>
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/site.css">
</head>
<body>
<div class="container">
<div class="row">
<form class="form-signup col-md-6 col-md-offset-3" role="form">
	<div class="form-group">
	<input type="text" class="form-control" placeholder="用户名" required="" autocomplete="off" >
	</div>
	<div class="form-group">
	<input type="password" class="form-control" placeholder="密码" required="" autocomplete="off" >
	</div>
	<div class="form-group">
	验证码：<input type="text" name="veriCode"/>
	</div>
	<div class="form-group">
	<button class="btn btn-lg btn-primary" type="submit">登录</button>
	<button class="btn btn-lg btn-primary" type="reset">重置</button>
	<button class="btn btn-lg btn-primary" onclick="location.href='register.jsp'">注册</button>
	</div>
</form>
</div>
</div>
</body>
</html>