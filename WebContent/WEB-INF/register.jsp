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
			<form class="mui-form" id="register-form" action="Register.do" method="post">
				<div>
					<div class="mui--text-headline">注册</div>
					<div>
						<div class="mui-textfield">
							<input type="text" name="UNAME" id="uname"> <label>用户名</label>
						</div>
						<div class="mui-textfield">
							<input type="password" name="PW" id="pw"> <label id="l_pw">密码</label>
						</div>
						<div class="mui-textfield">
							<input type="password" name="PW2" id="pw2"> <label id="l_pw2">确认密码</label>
						</div>
						<div class="mui-textfield">
							<input type="text" name="NAME" id="name"> <label>真实姓名</label>
						</div>
						<div class="mui-textfield">
							<input type="text" name="ID" id="id_no"> <label id="l_id_no">身份证号</label>
						</div>
						<div class="mui-textfield">
							<input type="tel" name="UPHONE" id="phone"> <label>联系电话</label>
						</div>
					</div>
					<div class="mui--text-caption wrong" id="error-info" style="display:none"></div>
					<div>
					<span class="mui--pull-right">	
					<button class="mui-btn mui-btn--flat mui-btn--primary" type="reset"
						value="重置">重置</button>
					<button class="mui-btn mui-btn--primary" type="submit" value="注册">注册</button>
					</span>
					<div class="mui--clearfix"></div>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">		
		var valid = true;
		function checkPasswords() {
			if ($("#pw").val().length = 0) {
				$("#l_pw").html("密码不能为空");
				$("#l_pw").addClass("wrong");
				$("#pw").addClass("wrong");
	 			valid = valid && false;
			} else if ($("#pw").val().length = 0) {
				$("#l_pw").addClass("wrong");
				$("#pw").addClass("wrong");
	 			valid = valid && false;
			} else if ($("#pw").val() != $("#pw2").val()) {
 				//alert($("#pw").val() +"!="+ $("#pw2").val());
				$("#l_pw2").html("输入密码不一致");
				$("#l_pw2").addClass("wrong");
				$("#pw2").addClass("wrong");
	 			valid = valid && false;
			} else {
 				// alert($("#pw").val() +"=="+ $("#pw2").val());
				$("#l_pw2").html("确认密码");
				$("#l_pw2").removeClass("wrong");
				$("#pw2").removeClass("wrong");
	 			valid = valid && true;
			}
		}

		$("#pw").blur(function() {
			if (!$("#pw").val() == "") {
				checkPasswords();
			}
		});
		$("#pw2").blur(checkPasswords);
		
		</script>
	
	<script type="text/javascript">
		$("#register-form").submit(function(event) {
		      event.preventDefault();
		      var $form = $( this ),
		          url = $form.attr( 'action' );
		      var posting = $.post( url, { UNAME: $('#uname').val(), PW: $('#pw').val(), PW2: $("#pw2").val(),
		    	  NAME: $('#name').val(), ID: $('#id_no').val(), UPHONE: $('#phone').val()} );
		      posting.done(function( data ) {
		    	  if (data == "true") {
		    		  alert("注册成功。");
		    		  window.location="login.jsp";
		    	  } else if (data == "not same pw") {
		    		  showError("输入密码不一致。")
		    		  checkPasswords();
		    	  } else if (data == "Username already existed") {
		    		  showError("用户名已存在。")
		    	  } else if (data == "id error") {
		    		  showError("身份证不合法。")
		    	  } else if (data == "empty info"){
		    		  showError("信息不完整，请补充。");
		    	  } else {
		    		  alert("注册失败。")
		    	  }
		      });
		});
	</script>
	<script type="text/javascript">
		function showError(data) {
			$('#error-info').html(data);
			$('#error-info').slideDown(200);
		}
	</script>
</body>
</html>