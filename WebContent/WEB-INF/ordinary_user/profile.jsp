<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>

	<div class="mui--appbar-height"></div>

	<div class="mui-appbar mui--appbar-line-height">
		<div class="content-wrapper" id="title">
			<div class="mui-container">
				<h1>个人信息</h1>
			</div>
		</div>
	</div>

	<div class="content-wrapper">
		<div class="mui-container-fluid">
			<div class="mui-panel">
				<form id = "picForm" action="upload.do" method="post" enctype="multipart/form-data">
	  				<table width="500" cellspacing="0" cellpadding="0">
			  			<tr>
			  			<td width="72" id="name01"> </td>
			  			<td><img id='imgSize1ImgSrc' src=''   height="100" width="100" /></td>
			  			<td width="242"><input type="file" class="file" name="file" id="file" onchange='submitImgSize1Upload()'/></td>
			  			<td width="184" id="size01" class="size02"> </td>
			  			</tr>
	  				</table>
				</form>
			</div>
			<div class="mui-panel">
				<form id="user-info" action="updateUserInfo.do">
					<div>
						<div>
							<div class="mui-textfield">
								<input type="text" value=${user.UNAME } disabled> <label>用户名</label>
							</div>
							<div class="mui-textfield">
								<input type="password" name="PW" id="pw" value=${user.PW }> <label>密码</label>
							</div>
							<div class="mui-textfield">
								<input type="password" id="pw2"> <label>再次输入密码</label>
							</div>
							<div class="mui-textfield">
								<input type="text" value=${user.NAME } disabled> <label>真实姓名</label>
							</div>
							<div class="mui-textfield">
								<input type="text" value=${user.ID } disabled> <label>身份证号</label>
							</div>
							<div class="mui-textfield">
								<input type="text" name="UPHONE" id="phone" value=${user.UPHONE }> <label>联系电话</label>
							</div>
						</div>
						<div>
							<span class="mui--pull-right">
								<button class="mui-btn mui-btn--flat mui-btn--primary"
									type="reset" value="重置">重置</button>
								<button class="mui-btn mui-btn--primary" type="submit"
									value="保存">保存</button>
							</span>
							<div class="mui--clearfix"></div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script src="js/jquery.form.js"></script>
	<script>
		$('#myFormId').submit(function() {
		    // submit the form
		    $(this).ajaxSubmit(function(message) {
		    	alert(message);
		    });
		    // return false to prevent normal browser submit and page navigation
		    return false;
		});
	</script>
  	<script type="text/javascript">
  		$(document).ready(function(){
  			$("#file").change( function(){
	  			var filepath=$("input[name='file']").val();
	  			var extStart=filepath.lastIndexOf(".");
	  			var ext=filepath.substring(extStart,filepath.length).toUpperCase();
	  			if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
	  				alert("图片限于bmp,png,gif,jpeg,jpg格式");
	  				return false;
	  			}else{ 
	  				$("#name01").text(ext)
	  			}
	  			var file_size = 0;
	  			if ( $.browser.msie) {
	  				var img=new Image();
	  				img.src=filepath;
	  				while(true){
	  					if(img.fileSize > 0){
	  						if(img.fileSize>3*1024*1024){
	 		 					alert("图片不大于100MB。");
	  						}else{
	  							var num03 = img.fileSize/1024;
	  							num04 = num03.toFixed(2)
	  							$(".size02").text(num04+"KB");
	  						}
	  						break;
	  					}
	  				}
	  			} else {
	  				file_size = this.files[0].size;
	  				console.log(file_size/1024/1024 + " MB");
	  				var size = file_size / 1024;
	  				if(size > 10240){
	 	 				alert("上传的文件大小不能超过10M！");
	  				}else{
	  					var num01 = file_size/1024;
	  					num02 = num01.toFixed(2)
	  					$("#size01").text(num02 + " KB");
	  				}
	  			}
	  			return true;
  			});
  		});
  	</script>
  	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.form.js"></script>
  	<script type="text/javascript">
function submitImgSize1Upload(){
	var option={
			type:'POST',
			url:'upload.do',
			dataType:'text',
			data:{
				fileName : 'form01'
			},
			success:function(data){
				
				//把json格式的字符串转换成json对象
				var jsonObj = $.parseJSON(data);
				
				//返回服务器图片路径，把图片路径设置给img标签
				$("#imgSize1ImgSrc").attr("src",jsonObj.fullPath);
				//数据库保存相对路径
				$("#imgSize1").val(jsonObj.relativePath);
			}
			
		};
	
	$("#picForm").ajaxSubmit(option);
	
}
</script>
</body>
</html>