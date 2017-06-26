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
				<div class="mui-panel question-manage">
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
					            	+ "<p class='bank'>" + item.BANK + "</p>"
					            	+ "<label class='ans'>" + "答案 "+ item.ANS + "</label>"
					            	+ "<button onclick='return edit(this)' style='float:right'>" + "编辑" + "</button><br/>"
					            	+ "<label class='opt1'>" + item.OPTION1 + "</label><br/>"
					            	+ "<label class='opt2'>" + item.OPTION2 + "</label><br/>"
					            	+ "<label class='opt3'>" + item.OPTION3 + "</label><br/>"
					            	+ "<label class='opt4'>" + item.OPTION4 + "</label><br/>"
					            + "</li>"
				    	   );
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
		   var li = $(element).parent();
		   //alert(li.find(".ans").html());
		   li.after(
				   "<li class='mui--text-dark mui--text-body1 question-edit'>"
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
						+ "<button class='mui-btn mui-btn--flat mui-btn--primary' onclick='return stopEdit(editingElement)'>取消</button></span>"
						+ "<button class='mui-btn mui-btn--primary' onclick='return save(editingElement)'>保存</button></span>"
						+ "<div class='mui--clearfix'></div>"
					+ "</div></div>"
					+ "</li>");
		   li.hide();
			return false;
		};
		
		function stopEdit(element) {
			var li = $(element).parent();
			li.show();
			li.next().remove();
			editingElement = null;
			return false;
		};
		
		function save(element) {
			var li = $(element).parent();
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
			// alert(qno + ", " + bank + ", " + option1 + ", " + option2 + ", " + ans);
			$.post( "updateQuestion.do", { 'QNO' : qno, 'BANK' : bank, 
				'OPTION1' : option1, 'OPTION2' : option2, 'OPTION3' : option3, 'OPTION4' : option4, 'ans': ans} );
			return false;
		}
	</script>


</body>
</html>