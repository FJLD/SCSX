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
<link href="css/content-wrapper.css" rel="stylesheet" type="text/css" />
<script src="mui/js/mui.min.js"></script>
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/core.js"></script>
<title>注册</title>
</head>
<body class="colored-primary">

	<div class="mui--appbar-height"></div>
	<div class="login-dialog mui-container">
		<div class="mui-panel">
			<form class="mui-form" action="Register.do" method="post">
				<div>
					<div>
						<div class="mui-textfield">
							<input type="text" name="UNAME"> <label>用户名</label>
						</div>
						<div class="mui-textfield">
							<input type="password" name="PW" id="pw"> <label id="l_pw">密码</label>
						</div>
						<div class="mui-textfield">
							<input type="password" id="pw2"> <label id="l_pw2">再次输入密码</label>
						</div>
						<div class="mui-textfield">
							<input type="text" name="NAME"> <label>真实姓名</label>
						</div>
						<div class="mui-textfield">
							<input type="text" name="ID" id="id_no"> <label id="l_id_no">身份证号</label>
						</div>
						<div class="mui-textfield">
							<input type="tel" name="UPHONE"> <label>联系电话</label>
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
		var id_no_reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		var valid = true;
		
		$("#pw2").blur(function() {
			if ($("#pw").val() != $("#pw2").val()) {
 				alert($("#pw").val() +"!="+ $("#pw2").val());
				$("#l_pw2").html("输入密码不一致");
				$("#l_pw2").addClass("wrong");
				$("#pw2").addClass("wrong");
	 			valid = valid && false;
			} else {
 				// alert($("#pw").val() +"=="+ $("#pw2").val());
				$("#l_pw2").html("再次输入密码");
				$("#l_pw2").removeClass("wrong");
				$("#pw2").removeClass("wrong");
	 			valid = valid && true;
			}
		});
		
// 		$("#id_no").blur(function() {
// 			if (!id_no_reg.test($("#id_no").val()) {
// 				$("#l_id_no").html("身份证号不合法");
// 				$("#l_id_no").addClass("wrong");
// 				$("#id_no").addClass("wrong");
// 	// 			valid = valid && false;
// 			} else {
// 				$("#l_id_no").html("身份证号");
// 				$("#l_id_no").removeClass("wrong");
// 				$("#id_no").removeClass("wrong");
// 	// 			valid = valid && true;
// 			}
// 		});
	</script>
	
</body>
</html>