<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="mui/css/mui.min.css" rel="stylesheet" type="text/css" />
<link href="mui/css/mui.min.css" rel="stylesheet" type="text/css" />
<link href="css/content-wrapper.css" rel="stylesheet" type="text/css" />
<link href="css/header.css" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<title>讨论区</title>
</head>
<body>

	<jsp:include page="../ordinary_user/header.jsp"></jsp:include>
	
	<div class="mui--appbar-height"></div>

	<div class="mui-appbar mui--appbar-line-height">
		<div class="content-wrapper" id="title">
			<div class="mui-container">
				<h1>讨论区</h1>
			</div>
		</div>
	</div>

	<div class="content-wrapper">
		<div class="mui-container-fluid">
			<div class="mui-panel">
				<table class="mui-table">
					<thead>
						<tr>
							<th>头像</th>
							<th>姓名</th>
							<th>时间</th>
							<th>话</th>
						</tr>
					</thead>
					<tbody id="items" style="display:none">
					</tbody>
				</table>
			</div>
			<div>
				<span class="mui--pull-right"><button class="mui-btn mui-btn--primary" id="next" onclick="nextPage()">加载更多</button></span>
				<div class="mui--clearfix"></div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		var page = 1;
		
		$(document).ready(function() {
			getPageData();
		});
		
		function nextPage() {
			page++;
			getPageData();
		}
		
		function getPageData() {
			 $.get("./getDiscussion.do",
			    {page: page},
			    function(data) {
			       var obj = JSON.parse(data);
			       if (obj.length == 0) {
			    	   $("button#next").hide();
			    	   if (page == 1) {
 			    		   $(".content-wrapper .mui-panel").html("<div class='mui--text-center mui--text-body1'>抢沙发</div>");
			    		   $(".mui-table").hide();
			    	   } else {
			    		   alert("没有更多记录。");
			    	   }
			       } else {
				       obj.forEach(function(item, index) {
				    	   $("#items").append("<tr><td>" + "<img src="+item.HEADIMAGE+" class='avatar'>"+ "</td>"
				    			   + "<td>" + item.UNAME + "</td>"
				    			   + "<td>"  + item.TIME + "</td>"
				    			   + "<td>" + item.DATA + "</td></tr>")
				       })
				       $("#items").slideDown("slow");
			       }
			    }
			);
		}
		
	</script>
	
</body>
</html>