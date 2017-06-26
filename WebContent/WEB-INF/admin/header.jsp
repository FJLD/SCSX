<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="mui/css/mui.min.css" rel="stylesheet" type="text/css" />
<link href="mui/css/mui.min.css" rel="stylesheet" type="text/css" />
<link href="css/content-wrapper.css" rel="stylesheet" type="text/css" />
<link href="css/header.css" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="mui/js/mui.min.js"></script>
<script src="js/header.js"></script>
</head>

<div id="sidedrawer" class="mui--no-user-select">
	<div class="mui-divider"></div>
	<ul>
		<li><a href="goToAdminIndex.do">首页</a></li>
		<li><a href="ListUsers.do">用户列表</a></li>
		<li><a href="AllRecords.do">考试记录</a></li>
		<li><a href="QuestionsManager.do">题库管理</a></li>
	</ul>
	<div id="bottom">
	<a href="./login.jsp"><i class="material-icons">exit_to_app</i></a>
	</div>
</div>

<header id="header">
<div class="mui-appbar mui--appbar-line-height">
	<div class="mui-container-fluid">
		<a
			class="sidedrawer-toggle mui--visible-xs-inline-block mui--visible-sm-inline-block js-show-sidedrawer">☰</a>
		<a
			class="sidedrawer-toggle mui--hidden-xs mui--hidden-sm js-hide-sidedrawer">☰</a>
	</div>
</div>
</header>

</html>