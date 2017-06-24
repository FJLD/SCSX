<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>
	
	<div class="mui--appbar-height"></div>
	<div class="mui-appbar mui--appbar-line-height">
		<div class="content-wrapper" id="title">
			<div class="mui-container">
				<h1>用户列表</h1>
			</div>
		</div>
	</div>

	<div class="content-wrapper">
		<div class="mui--appbar-height"></div>
		<div class="mui-container-fluid">
			<div class="mui-panel">
				<table class="mui-table">
					<thead>
						<tr>
							<th>编号</th>
							<th>用户名</th>
							<th>真实姓名</th>
							<th>身份证号</th>
							<th>联系电话</th>
						</tr>
					</thead>
					<tbody id="items">
					</tbody>
				</table>
			</div>
			<div>
				<span class="mui--pull-left"><button class="mui-btn mui-btn--primary" id="prev" onclick="prevPage()">上一页</button></span>
				<span class="mui--pull-right"><button class="mui-btn mui-btn--primary" id="next" onclick="nextPage()">下一页</button></span>
			<div class="mui--clearfix"></div>
			</div>
		</div>
	</div>
	
		<script type="text/javascript">
		var page = 1;
		
		$(document).ready(function() {
			if (page == 1) {
				$("button#prev").hide();
			}
			getPageData();
		});
		
		function prevPage() {
			if (page > 1) {
				page--;
				getPageData();
			} else {
				alert("已经到达第一页。");
			}
		}
		
		function nextPage() {
			page++;
			getPageData();
		}
		
		function getPageData() {
			 $.get("./getAllUsers.do",
			    {uno: 6, page: page},
			    function(data) {
			       alert('page content: ' + data);
			       var obj = JSON.parse(data);
			       //alert(obj[0].RESULT);
			       //alert("obj.length = " + obj.length);
			       if (obj.length == 0) {
			    	   $("button#next").hide();
			    	   if (page == 1) {
 			    		   $(".content-wrapper .mui-panel").html("<div class='mui--text-center mui--text-body1'>暂无用户</div>");
			    		   $(".mui-table").hide();
			    	   } else {
			    		   alert("已经到达最后一页。");
			    	   }
			       } else {
				       obj.forEach(function(item, index) {
				    	   $("#items").append("<tr><td>" + item.UNO + "</td>"
				    			   + "<td>" + item.UNAME + "</td>"
				    			   + "<td>" + item.NAME + "</td>"
				    			   + "<td>" + item.ID + "</td>"
				    			   + "<td>" + item.UPHONE + "</td></tr>")
				       })
			       }
			    }
			);
		}
		
	</script>

</body>
</html>