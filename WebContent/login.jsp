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
<script src="https://cdn.bootcss.com/jquery/3.2.1/core.js"></script>
<title>登录</title>
</head>
<body class="colored-primary">
<%
     String username = "";
	 String password = "";
     Cookie[] cookies = request.getCookies();
     for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
         if ("username".equals(cookies[i].getName())) {
             username = cookies[i].getValue();
         } else if("password".equals(cookies[i].getName())){
        	 password = "";
         }
     }
	String msg = (String)request.getAttribute("msg");
	if(msg!=null){
		out.print(msg);
	}
%>
	<div class="mui--appbar-height"></div>
	<div class="login-dialog mui-container">
		<div class="mui-panel">
			<form id="login-form" action="DoLogin.do" method="post" >
				<div>
					<div class="mui-textfield mui-textfield-">
						<input type="text" name="UNAME" value=<%=username %> > <label>用户名</label>
					</div>
					<div class="mui-textfield mui-textfield">
						<input type="password" name="PW" value=<%=password %> > <label>密码</label>
					</div>
					验证码:<input type="text" name="code">
					<img src="./codeServlet.do" onclick="changeCode()"/><br/>
					<a href="javascript:changeCode()" >看不清换一张</a><br/>
				</div>
				<div>
					<div class="mui-radio inline">
						<label><input type="radio" name="POWER" value="用户" checked="checked" />学生用户</label> 
					</div>
					<div class="mui-radio inline">
						<label><input type="radio" name="POWER" value="管理员" />管理员</label> 
					</div>
				</div>
				<div id="notFound">
				</div>
				<div>
					<button class="mui-btn mui-btn--flat mui-btn--primary" type="button"
						value="register" onclick="window.location.href='goToRegisterPage.do'">注册</button>
					<button class="mui-btn mui-btn--flat mui-btn--primary" type="reset"
						value="reset">重置</button>
					<button class="mui-btn mui-btn--primary" type="submit">确认</button>
				</div>
		        <br />
			</form>
		</div>
	</div>
	<script type="text/javascript">
	function login() { 
		// jquery 表单提交 
		/* $("#login-form").ajaxSubmit(function(message) { 
			document.getElementById("notFound").innerHTML=message;
		}); 
		return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转  */
		$.post("Login.do", {username: "hello", 
			password: "fd", 
			power: "用户"}, function (callback){
			alert(callback);
		})
	} 
	function changeCode(){
		//得到图片元素
		var img = document.getElementsByTagName("img")[0];
		img.src = "./codeServlet.do?time="+new Date().getTime();
	}
	</script>
</body>
</html>