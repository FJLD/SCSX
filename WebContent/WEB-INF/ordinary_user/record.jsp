<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="mui/js/mui.min.js"></script>
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
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
					<tbody id="records">
						<tr>
							<td>1</td>
							<td>试卷1</td>
							<td>2017.06.08 09:00</td>
							<td>92</td>
						</tr>
						<tr>
							<td>2</td>
							<td>试卷2</td>
							<td>2017.06.08 09:00</td>
							<td>92</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<span class="mui--pull-left"></span>
				<span class="mui--pull-right"><button class="mui-btn mui-btn--primary" onclick="nextPage()">下一页</button></span>
				<div class="mui--clearfix"></div>
			</div>
			
		</div>
	</div>
	
	<script type="text/javascript">
		var page = 0;
		
		function nextPage() {
			page++;
			getPageData();
		}
		
		function getPageData() {
			 $.get(
			    "./getExamRecords.do",
			    {uno: 6, page: page},
			    function(data) {
			       //alert('page content: ' + data);
			       var obj = JSON.parse(data);
			       //alert(obj[0].RESULT);
			       obj.forEach(function(item, index) {
			    	   $("#records").append("<tr><td>" + item.EXAMNO + "</td>"
			    			   + "<td>" + item.PNO + "</td>"
			    			   + "<td>" + item.TIME + "</td>"
			    			   + "<td>" + item.RESULT + "</td></tr>")
			       })
			    }
			);
		}
		
	</script>
	
</body>
</html>