<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>

	<div class="mui--appbar-height"></div>

	<div class="mui-appbar mui--appbar-line-height">
		<div class="content-wrapper" id="title">
			<div class="mui-container">
				<h1>个人信息</h1>
			</div>
		</div>
	</div>

	<div class="content-wrapper">
		<div class="mui-container-fluid">
			<div class="mui-panel">
				<img id="avatar" class="mui-button" src="${headImage }" />
				<button class="mui-btn mui-btn--primary">浏览…</button>
			</div>
			<div class="mui-panel">
				<form id="user-info" action="updateUserInfo.do">
					<div>
						<div>
							<div class="mui-textfield">
								<input type="text" value=${user.UNAME } disabled> <label>用户名</label>
							</div>
							<div class="mui-textfield">
								<input type="password" name="PW" id="pw" value=${user.PW }> <label>密码</label>
							</div>
							<div class="mui-textfield">
								<input type="password" id="pw2"> <label>再次输入密码</label>
							</div>
							<div class="mui-textfield">
								<input type="text" value=${user.NAME } disabled> <label>真实姓名</label>
							</div>
							<div class="mui-textfield">
								<input type="text" value=${user.ID } disabled> <label>身份证号</label>
							</div>
							<div class="mui-textfield">
								<input type="text" name="UPHONE" id="phone" value=${user.UPHONE }> <label>联系电话</label>
							</div>
						</div>
						<div>
							<span class="mui--pull-right">
								<button class="mui-btn mui-btn--flat mui-btn--primary"
									type="reset" value="重置">重置</button>
								<button class="mui-btn mui-btn--primary" type="submit"
									value="保存">保存</button>
							</span>
							<div class="mui--clearfix"></div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script src="js/jquery.form.js"></script>
	<script>
		$('#myFormId').submit(function() {
		    // submit the form
		    $(this).ajaxSubmit(function(message) {
		    	alert(message);
		    });
		    // return false to prevent normal browser submit and page navigation
		    return false;
		});
	</script>

</body>
</html>