<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<title>首页</title>
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
		<div class="mui-container-fluid">
			<div class="mui--text-subhead">
				实现一个真实的、基于SSH架构的电信网络学院（TNA）系统<br /> 
				增量式开发，循序渐进完成项目<br />
				掌握基于SSH架构的Web应用程序的编程和调试技巧<br />
			</div>
			<br />
			<div class="mui-panel">
				<p>
					主要涉及以下主要知识点：<br />
					<ul>
						<li>基于SSH框架的Model 2架构的实现</li>
						<li>使用Struts2框架OGNL、验证与资源 、拦截器等</li>
						<li>HttpSession会话管理</li>
						<li>可复用表示组件的实际运用</li>
						<li>Spring框架的Ioc与AOP特征</li>
						<li>使用Hibernate框架访问MySQL数据库</li>
						<li>三大框架的有机整合</li>
					</ul>
				</p>
			</div>
		</div>
	</div>

</body>
</html>