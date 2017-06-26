<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>正在考试…</title>
</head>
<body onload="time_fun()">

	<jsp:include page="./header.jsp"></jsp:include>

	<div class="mui--appbar-height"></div>

	<div class="mui-appbar mui--appbar-line-height">
		<div class="content-wrapper" id="title">
			<div class="mui-container">
				<h1>正在考试…</h1>
			</div>
		</div>
	</div>

	<div class="content-wrapper">
		<div class="mui-container-fluid">
			<form action="markingPaper.do" method="post">
				<div class="mui-panel mui--no-user-select">
					<ol>
						<c:forEach items="${questions}" varStatus="i" var="item">
							<li class="mui--text-dark mui--text-body1 question-item">
								<p>${item.BANK}</p> <label><input type="radio"
									name="${item.QNO}" value="a" />${item.OPTION1 }</label><br /> <label><input
									type="radio" name="${item.QNO}" value="b" />${item.OPTION2 }</label><br />
								<label><input type="radio" name="${item.QNO}" value="c" />${item.OPTION3 }</label><br />
								<label><input type="radio" name="${item.QNO}" value="d" />${item.OPTION4 }</label><br />
							</li>
						</c:forEach>
					</ol>
				</div>
				<div>
					<span class="mui--pull-right">
						<button class="mui-btn mui-btn--raised mui-btn--primary"
							type="submit" value="交卷">交卷</button>
					</span>
					<div class="mui--clearfix"></div>
				</div>
			</form>
		</div>
	</div>

	<script>
		function two_char(n) {
			return n >= 10 ? n : "0" + n;
		}
		function time_fun() {
			var sec = 1800;
			setInterval(
					function() {
						sec--;
						var date = new Date(0, 0)
						date.setSeconds(sec);
						var h = date.getHours(), m = date.getMinutes(), s = date
								.getSeconds();
						document.getElementById("headline-more").innerText = two_char(h)
								+ ":" + two_char(m) + ":" + two_char(s);
					}, 1000);
		}
	</script>

</body>
</html>