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
<script src="mui/js/mui.min.js"></script>
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<title>注册</title>
</head>
<body class="colored-primary">

	<div class="mui--appbar-height"></div>
	<div class="login-dialog mui-container">
		<div class="mui-panel">
			<form class="mui-form" action="Register.do">
				<div>
					<div>
						<div class="mui-textfield mui-textfield">
							<input type="text" name="username"> <label>用户名</label>
						</div>
						<div class="mui-textfield mui-textfield">
							<input type="password" name="password"> <label id="l_password">密码</label>
						</div>
						<div class="mui-textfield mui-textfield">
							<input type="password" name="password_again" id="password_again"> <label id="l_password_again">再次输入密码</label>
						</div>
						<div class="mui-textfield mui-textfield">
							<input type="text" name="name"> <label>真实姓名</label>
						</div>
						<div class="mui-textfield mui-textfield">
							<input type="text" name="id_no" id="id_no"> <label id="l_id_no">身份证号</label>
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
	$("#password_again").blur(function() {
		if ($("#password").val() != $("#password_again").val()) {
			$("#l_password_again").html("输入密码不一致");
			$("#l_password_again").css("color", "#F44336");
			$("#password_again").css("border-color", "#F44336");
		} else {
			$("#l_password_again").css("color", 'initial');
			$("#password_again").css("border-color", 'initial');
		}
	});
	
// 	$("#id_no").blur(function() {
// 		if (!id_no_reg.test(form.id_no.value)) {
// 			document.getElementById("l_id_no").innerHTML = "身份证号不合法";
// 			document.getElementById("l_id_no").style.color = "#F44336";
// 			document.getElementById("id_no").style.borderColor="#F44336";
// 			valid = valid && false;
// 		} else {
// 			document.getElementById("l_id_no").innerHTML = "身份证号";
// 			document.getElementById("l_id_no").style.color = "#F44336";
// 			document.getElementById("id_no").style.borderColor="#F44336";
// 			valid = valid && true;
// 		}
// 	})
	
// 	function check(form) {
// 		var id_no_reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
// 		var valid = true;
// 		if (form.password.value != form.password_again.value) {
// 			document.getElementById("l_password_again").innerHTML = "输入密码不一致";
// 			document.getElementById("l_password_again").style.color = "#F44336";
// 			document.getElementById("password_again").style.borderColor="#F44336";
// 			valid = valid && false;
// 		} else {
// 			document.getElementById("l_password_again").innerHTML = "再次输入密码";
// 			document.getElementById("l_password_again").style.color = "#F44336";
// 			document.getElementById("password_again").style.borderColor="#F44336";
// 			valid = valid && true;
// 		}
		
// 		else {
// 			return true;
// 		}
// 		return false;
// 	}
	</script>

</body>
</html>