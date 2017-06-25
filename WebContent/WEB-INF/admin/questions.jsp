<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<title>题库管理</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>
	
	<div class="mui--appbar-height"></div>
	<div class="mui-appbar mui--appbar-line-height">
		<div class="content-wrapper" id="title">
			<div class="mui-container">
				<h1>题库管理</h1>
			</div>
		</div>
	</div>
	
	<div class="content-wrapper">
		<div class="mui-container-fluid">
			<form>
				<div class="mui-panel">
					<div>
						<ol id="items">
						</ol>
					</div>
				</div>
			<div>
				<span class="mui--pull-left"><button class="mui-btn mui-btn--primary" id="prev" onclick="prevPage()">上一页</button></span>
				<span class="mui--pull-right"><button class="mui-btn mui-btn--primary" id="next" onclick="nextPage()">下一页</button></span>
				<div class="mui--clearfix"></div>
			</div>
			</form>
		</div>
	</div>
	
		<script type="text/javascript">
		var page = 1;
		
		$("#items").load(function() {
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
			 $.get("./getAllQuestions.do",
			    {page: page},
			    function(data) {
			    	//alert(data);
			       var obj = JSON.parse(data);
			       if (obj.length == 0) {
			    	   $("button#next").hide();
			    	   if (page == 1) {
 			    		   $(".content-wrapper .mui-panel").html("<div class='mui--text-center mui--text-body1'>暂无考试记录</div>");
			    		   $(".mui-table").hide();
			    	   } else {
			    		   alert("已经到达最后一页。");
			    	   }
			       } else {
				       obj.forEach(function(item, index) {
				    	   $("#items").append(
				    			"<li class='mui--text-dark mui--text-body1 question-item'>"
					            	+ "<p>" + item.BANK + "</p>"
					            	+ "<button onclick='edit(this)'>" + "编辑" + "</button>"
					            	+ "<label>" + item.OPTION1 + "</label><br/>"
					            	+ "<label>" + item.OPTION2 + "</label><br/>"
					            	+ "<label>" + item.OPTION3 + "</label><br/>"
					            	+ "<label>" + item.OPTION4 + "</label><br/>"
					            + "</li>"
				    	   );
				       })
			       }
			    }
			);
		}
	</script>
	
	<script>
	   function edit(element) {
			$(element).hide();
		};
	</script>


</body>
</html>