<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请选择试卷…</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>
	
	<div class="mui--appbar-height"></div>

	<div class="mui-appbar mui--appbar-line-height">
		<div class="content-wrapper" id="title">
			<div class="mui-container">
				<h1>请选择试卷…</h1>
			</div>
		</div>
	</div>

	<div class="content-wrapper">
		<div class="mui-container-fluid">
			<div class="mui-panel">
				<form>
					<div class="mui-radio inline">
						<label><input type="radio" name="paper" value="p1"/>试卷 1</label> 
					</div>
					<div class="mui-radio inline">
						<label><input type="radio" name="paper" value="p2" />试卷 2</label> 
					</div>
					<button class="mui-btn mui-btn--primary" type="submit" value="交卷">开始考试</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>