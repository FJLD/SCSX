<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>考试记录</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>
	
	<div class="mui--appbar-height"></div>

	<div class="mui-appbar mui--appbar-line-height">
		<div class="content-wrapper" id="title">
			<div class="mui-container">
				<h1>考试记录</h1>
			</div>
		</div>
	</div>

	<div class="content-wrapper">
		<div class="mui-container-fluid">
			<div class="mui-panel">
				<table class="mui-table">
					<thead>
						<tr>
							<th>编号</th>
							<th>试卷</th>
							<th>考试时间</th>
							<th>分数</th>
						</tr>
					</thead>
					<tbody id="items">
					</tbody>
				</table>
			</div>
			<div>
				<span class="mui--pull-right"><button class="mui-btn mui-btn--raised mui-btn--primary" id="next" onclick="nextPage()">加载更多</button></span>
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
			 $.get("./getExamRecords.do",
			    {page: page},
			    function(data) {
			       //alert('page content: ' + data);
			       var obj = JSON.parse(data);
			       //alert(obj[0].RESULT);
			       //alert("obj.length = " + obj.length);
			       if (obj.length == 0) {
			    	   $("button#next").hide();
			    	   if (page == 1) {
 			    		   $(".content-wrapper .mui-panel").html("<div class='mui--text-center mui--text-body1'>暂无考试记录</div>");
			    		   $(".mui-table").hide();
			    	   } else {
			    		   alert("没有更多记录。");
			    	   }
			       } else {
				       obj.forEach(function(item, index) {
				    	   $("#items").append("<tr><td>" + item.EXAMNO + "</td>"
				    			   + "<td>" + item.PNO + "</td>"
				    			   + "<td>" + item.TIME + "</td>"
				    			   + "<td>" + item.RESULT + "</td></tr>")
				       })
			       }
			    }
			);
		}
		
	</script>
	
</body>
</html>