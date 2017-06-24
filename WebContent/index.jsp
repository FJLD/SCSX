<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
    <button onclick="return httpGet()">点击跳转</button>
  </body>
  <form action="./test.do" method="post">
  	<input type="text" name="hello">
  	<input type="submit">
  </form>
  <h1>${hello12 }</h1>
  
  
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
  <script src="https://cdn.bootcss.com/jquery/3.2.1/core.js"></script>
  <script>
  function httpGet() {
	  $.get(
		    "./getExamRecords.do",
		    {uno: 6, page: 1},
		    function(data) {
		       alert('page content: ' + data);
		    }
		);
  }
  </script>
</html>
