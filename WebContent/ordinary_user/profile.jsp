<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<jsp:include page="./header.jsp"></jsp:include>

<form>
	<div>
		<div>
			<p> 用户名 <input/> </p>
			<p> 密码 <input/> </p>
			<p> 密码确认 <input/> </p>
			<p> 真实姓名 <input/> </p>
			<p> 身份证号 <input/> </p>
			<p> 联系电话 <input/> </p>
		</div>
	<input type="reset" value="重置" >
	<input type="submit" value="注册" >
	</div>
</form>

</body>
</html>