<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="mui/js/mui.min.js"></script>
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
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
				<form action="goToExam.do">
					<div id="paper_choices">
					</div>
					<button class="mui-btn mui-btn--primary" type="submit" value="开始考试">开始考试</button>
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
			       alert(data);
			       var obj = JSON.parse(data);
			       //alert(obj[0].RESULT);
			       //alert("obj.length = " + obj.length);
			       if (obj.length == 0) {
			    	   $(".content-wrapper .mui-panel").html("<div class='mui--text-center mui--text-body1'>暂无试卷</div>");
		    		   $(".mui-table").hide();
			       } else {
				       obj.forEach(function(item, index) {
				    	   $("#paper_choices").append("<div class='mui-radio inline'><label>" 
				    			   + "<input type='radio' name='paper' value='"
				    			   + item.PNO + "' />"
				    			   + item.PNAME + "</label></div>");
				       })
			       }
			    }
			);
		}
	</script>

</body>
</html>