<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<title>讨论区…</title>
</head>
<body>

	<jsp:include page="../ordinary_user/header.jsp"></jsp:include>

	<div class="mui--appbar-height"></div>

	<div class="mui-appbar mui--appbar-line-height">
		<div class="content-wrapper" id="title">
			<div class="mui-container">
				<h1>请选择对应试卷的讨论区…</h1>
			</div>
		</div>
	</div>

	<div class="content-wrapper">
		<div class="mui-container-fluid">
			<div class="mui-panel">
				<form action="goToDiscussion.do">
					<div id="paper_choices" style="display:none"></div>
					<div id="info" style="display:none">
						<div class="mui-divider"></div>
						<div class="mui--text-body1" id="choice_info"></div>
					</div>
					<div>
						<span class="mui--pull-right">
							<button class="mui-btn mui-btn--primary" id="start" type="submit"
								value="确定" disabled>确定</button>
						</span>
						<div class="mui--clearfix"></div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript" charset="UTF-8">
		$(document).ready(function() {
			getPageData();
		});

		function getPageData() {
			$.get("./getAllPapers.do",
				function(data) {
					var obj = JSON.parse(data);
					if (obj.length == 0) {
						$(".content-wrapper .mui-panel")
								.html("<div class='mui--text-center mui--text-body1'>暂无试卷</div>");
						$(".mui-table").hide();
					} else {
						obj.forEach(function(item, index) {
							$("#paper_choices").append(
								"<div class='mui-radio inline'><label>"
										+ "<input type='radio' name='paper' value='" + item.PNO + "' + id ='" + index + "'/>"
										+ item.PNAME
										+ "</label></div>");
						})
					}
					$("#paper_choices").slideDown(200);
					$('input[type=radio][name=paper]').change(function() {
				        $("#choice_info").html("已选择" + obj[this.id].PNAME + "，您确定要查看该试卷的讨论。");
				        $("#info").slideDown(200);
				        $("#start").prop('disabled', false);
				    });
				});
		}
	</script>

</body>
</html>