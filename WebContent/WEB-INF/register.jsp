<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="mui/css/mui.min.css" rel="stylesheet" type="text/css" />
<link href="mui/css/mui.min.css" rel="stylesheet" type="text/css" />
<link href="css/login-dialog.css" rel="stylesheet" type="text/css" />
<script src="mui/js/mui.min.js"></script>
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<title>注册</title>
</head>
<body>

	<div class="mui--appbar-height"></div>
	<div class="login-dialog mui-container">
		<div class="mui-panel">
			<form class="mui-form" action="login.jsp">
				<div>
					<div>
						<div class="mui-textfield mui-textfield">
							<input type="text" name="username"> <label>用户名</label>
						</div>
						<div class="mui-textfield mui-textfield">
							<input type="password" name="password"> <label id="l_password">密码</label>
						</div>
						<div class="mui-textfield mui-textfield">
							<input type="password" name="password_again"> <label id="l_password_again">再次输入密码</label>
						</div>
						<div class="mui-textfield mui-textfield">
							<input type="text" name="name"> <label>真实姓名</label>
						</div>
						<div class="mui-textfield mui-textfield">
							<input type="text" name="id_no"> <label>身份证号</label>
						</div>
						<div class="mui-textfield mui-textfield">
							<input type="tel" name="phone"> <label>联系电话</label>
						</div>
					</div>
					<button class="mui-btn mui-btn--flat mui-btn--primary" type="reset"
						value="重置">重置</button>
					<button class="mui-btn mui-btn--primary" type="submit" value="注册" onclick="return check(this.form)">注册</button>
				</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
	function check(form) {
		if (form.password.value != form.password_again.value) {
			document.getElementById("l_password_again").innerHTML = "输入密码不一致";
		}
		else if (form.password.) {
			
		}
		else {
			return true;
		}
		return false;
	}
	</script>

</body>
</html>