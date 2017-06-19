<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>电信网络学院</title>
	<link rel="stylesheet" href="../css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/site.css">
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		    	<span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		   	</button>
		    <a class="navbar-brand" href="index.jsp">首页</a>
		</div>
		<div class="collapse navbar-collapse">
	    	<ul class="nav navbar-nav">
	        	<li {% if slug == '个人信息' %}class="active"{% endif %}>
	            	<a href="/">个人信息</a>
	            </li>
	            <li {% if slug == '参加考试' %}class="active"{% endif %}>
	                <a href="{% url 'page' 'pricing' %}">参加考试</a>
	            </li>
	            <li {% if slug == '考试记录' %}class="active"{% endif %}>
	               	<a href="record.jsp">考试记录</a>
	            </li>
	        </ul>
	        <ul class="nav navbar-nav navbar-right">
	            <li {% if slug == '退出' %}class="active"{% endif %}>
	                <a href="../login.jsp">退出</a>
	            </li>
	        </ul>
	    </div>
	</div>
</div>
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</body>
</html>