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
<title>注册</title>
</head>
<body>

	<div class="mui--appbar-height"></div>
	<div class="mui-container">
		<div class="mui-panel">
			<form>
				<div>
					<div>
						<div class="mui-textfield mui-textfield--float-label">
							<input type="text"> <label>用户名</label>
						</div>
						<div class="mui-textfield mui-textfield--float-label">
							<input type="password"> <label>密码</label>
						</div>
						<div class="mui-textfield mui-textfield--float-label">
							<input type="password"> <label>再次输入密码</label>
						</div>
						<div class="mui-textfield mui-textfield--float-label">
							<input type="text"> <label>真实姓名</label>
						</div>
						<div class="mui-textfield mui-textfield--float-label">
							<input type="text"> <label>身份证号</label>
						</div>
						<div class="mui-textfield mui-textfield--float-label">
							<input type="text"> <label>联系电话</label>
						</div>
					</div>
					<button class="mui-btn mui-btn--flat mui-btn--primary" type="reset"
						value="重置">重置</button>
					<button class="mui-btn mui-btn--primary" type="submit" value="注册">注册</button>
				</div>
			</form>
		</div>
	</div>

</body>
</html>