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
<link href="css/discussion.css" rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<title>讨论区</title>
</head>
<body>

	<jsp:include page="../ordinary_user/header.jsp"></jsp:include>
	
	<div class="mui--appbar-height"></div>

	<div class="mui-appbar mui--appbar-line-height">
		<div class="content-wrapper" id="title">
			<div class="mui-container">
				<h1>${PNAME}讨论区</h1>
			</div>
		</div>
	</div>

	<div class="content-wrapper">
		<div class="mui-container-fluid">
			<div class="mui-panel">
				<table class="mui-table">
					<tbody id="items" style="display:none">
					</tbody>
				</table>
			</div>
			<div>
				<span class="mui--pull-right"><button class="mui-btn mui-btn--primary" id="next" onclick="getPageData()">加载更多</button></span>
				<div class="mui--clearfix"></div>
			</div>
			<div class="mui--appbar-height"></div>
			<form id="comments-form" method="post" action="pushComment.do">
				<div class='mui-textfield mui-panel'>
					<textarea name="submitComments" id="commitText" rows="4" placeholder='发表评论…'></textarea>
				</div>
				<div>
					<span class="mui--pull-right">
						<button class="mui-btn mui-btn--primary" type="submit" id="comments_submit" value="发表">发表</button>
					</span>
					<div class="mui--clearfix"></div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		var page = 0;
		
		$(document).ready(function() {
			getPageData();
		});
				
		function getPageData() {
			 $.get("./getDiscussion.do",
			    {page: page},
			    function(data) {
			       var obj = JSON.parse(data);
			       if (obj.length == 0) {
			    	   $("button#next").hide();
			    	   if (page == 0) {
 			    		   //$(".content-wrapper .mui-panel").html("<div class='mui--text-center mui--text-body1'>抢沙发</div>");
			    		   //$(".mui-table").hide();
			    	   } else {
			    		   alert("没有更多记录。");
			    	   }
			       } else {     
			    	   obj.forEach(function(item, index) {
				    	   $("#items").append("<tr>"
				    	   		+ "<td class='post-user'>"
				    	   		+ "<img src="+item.HEADIMAGE+" class='small-avatar'>"
				    	   		+ "<div class='mui--text-caption post-username'>" + item.UNAME + "</div>"
				    	   		+ "</td>"
				    	   		+ "<td class='post-content'>"
				    	   		+ "<div class='mui--text-body1 post-content-data'>" + item.DATA + "</div>"
				    	   		+ "<div class='mui--text-caption post-content-time'>" + item.TIME + "</div>"
				    	   		+ "</td>"
				    	   		+ "</tr>")
				       })
				       $("#items").slideDown("slow");
			    	   page = obj.length + page;
			       }
			    }
			);
		}
		
	</script>
	<script type="text/javascript">
		$("#comments-form").submit(function(event) {
		      event.preventDefault();
		      var $form = $( this ),
		          url = $form.attr( 'action' );
		      var posting = $.post( url, { submitComments: $('#commitText').val() } );
		      posting.done(function( data ) {
		    	  if (data == "true") {
		    		  alert ("发表评论成功。");
		    		  $("button#next").show();
		    		  $('#commitText').val("");
		    	  } else {
		    		  alert ("发表评论失败。");
		    	  }
		      });
		});
	</script>
</body>
</html>