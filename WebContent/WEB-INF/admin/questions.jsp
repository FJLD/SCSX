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
	
	<button class="mui-btn mui-btn--small mui-btn--primary mui-btn--fab bottom-fab">+</button>
	
	<div class="content-wrapper">
		<div class="mui-container-fluid">
				<div class="mui-panel question-manage">
					<div>
						<ol id="items">
						</ol>
					</div>
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
				    			"<li class='mui--text-dark mui--text-body1 question-item' id='" + item.QNO + "'>"
				    				+ "<div><span class='mui--pull-left'>"
				    				+ "<div class='mui--text-caption'># " + item.QNO + ". 答案 "+ item.ANSString + "</div>"
					            	+ "<p class='bank'>" + item.BANK + "</p>"
					            	+ "<b>A. </b>" + "<label class='opt1'>" + item.OPTION1 + "</label><br/>"
					            	+ "<b>B. </b>" + "<label class='opt2'>" + item.OPTION2 + "</label><br/>"
					            	+ "<b>C. </b>" + "<label class='opt3'>" + item.OPTION3 + "</label><br/>"
					            	+ "<b>D. </b>" + "<label class='opt4'>" + item.OPTION4 + "</label><br/>"
					            	+ "</span> <span class='mui--pull-right'>"
					            	+ "<button class='mui-btn mui-btn--flat mui-btn--primary' id='edit-button" + item.QNO + "' onclick='return edit(this)' "
					            	+ "style='display:none'>" + "编辑" + "</button><br/>"
					            	+ "</span><div class='mui--clearfix'></div></div>"
					            + "</li>"
				    	   );
					       $(".question-item#" + item.QNO).hover(function(){
					    	   	$(".question-item#" + item.QNO).addClass("selected");
					    	    $("#edit-button" + item.QNO).show();
					    	    },function(){
					    	    $(".question-item#" + item.QNO).removeClass("selected");
					    	    $("#edit-button" + item.QNO).hide();
					    	});
				       })
			       }
			    }
			);
		}
	</script>
	
	<script>
	var editingElement;
	
	   function edit(element) {
		   if (editingElement != null) stopEdit(editingElement);
		   editingElement = element;
		   var li = $(element).parent().parent().parent();
		   //alert(li.find(".ans").html());
		   li.after(
				   "<li class='mui--text-dark mui--text-body1 question-edit selected' style='display:none'>"
				   + "<div class='question-edit'>"
					+ "<div class='mui-textfield'>"
						+ "<textarea name='BANK' placeholder='题面'>" + li.find(".bank").text() + "</textarea>"
					+ "</div>"
					+ "<div class='choice-edit'><input class='check' type='checkbox' name='ans' value='A'/>"
					+ "<div class='mui-textfield'>"
						+ "<input type='text' name='OPT1' value=" + li.find(".opt1").text() + " placeholder='选项 A'>"
					+ "</div></div>"
					+ "<div class='choice-edit'><input class='check' type='checkbox' name='ans' value='B'/>"
					+ "<div class='mui-textfield'>"
						+ "<input type='text' name='OPT2' value=" + li.find(".opt2").text() + " placeholder='选项 B'>"
					+ "</div></div>"
					+ "<div class='choice-edit'><input class='check' type='checkbox' name='ans' value='C'/>"
					+ "<div class='mui-textfield'>"
						+ "<input type='text' name='OPT3' value=" + li.find(".opt3").text() + " placeholder='选项 C'>"
					+ "</div class='choice-edit'></div>"
					+ "<div  class='choice-edit'><input class='check' type='checkbox' name='ans' value='D'/>"
					+ "<div class='mui-textfield'>"
						+ "<input type='text' name='OPT4' value=" + li.find(".opt4").text() + " placeholder='选项 D'>"
					+ "</div class='choice-edit'></div>"
					+ "<div><div>"
						+ "<span class='mui--pull-right'>"
						+ "<button class='mui-btn mui-btn--flat mui-btn--primary' onclick='return stopEdit(editingElement)'>取消</button>"
						+ "<button class='mui-btn mui-btn--primary' onclick='return save(editingElement)'>保存</button></span>"
						+ "<div class='mui--clearfix'></div>"
					+ "</div></div>"
					+ "</li>");
		   li.hide();
		   $(".question-edit").slideDown(200);
			return false;
		};
		
		function stopEdit(element) {
			var li = $(element).parent().parent().parent();
			li.show();
			li.next().remove();
			editingElement = null;
			return false;
		};
		
		function save(element) {
			var li = $(element).parent().parent().parent();
			var editingli = li.next();
			var qno = li.attr('id');
			var bank = editingli.find('textarea[name="BANK"]').val();
			var option1 = editingli.find('input[name="OPT1"]').val();
			var option2 = editingli.find('input[name="OPT2"]').val();
			var option3 = editingli.find('input[name="OPT3"]').val();
			var option4 = editingli.find('input[name="OPT4"]').val();      
		    var ans = "";
		    editingli.find(':checkbox:checked').each(function(i){
	          ans = ans + $(this).val();
	        });
			//alert(qno + ", " + bank + ", " + option1 + ", " + option2 + ", " + option3 + ", " + option4 + ", " + ans);
			$.post( "updateQuestion.do", { 'QNO' : qno, 'BANK' : bank, 
				'OPTION1' : option1, 'OPTION2' : option2, 'OPTION3' : option3, 'OPTION4' : option4, 'ans': ans} );
			location.reload();
		}
	</script>


</body>
</html>