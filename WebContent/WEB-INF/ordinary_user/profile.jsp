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
			<div class="mui-panel" id="upload-avatar">
				<form id = "picForm" action="upload.do" method="post" enctype="multipart/form-data">
	  				<img id='imgSize1ImgSrc' class="avatar" src=''/>
			  		<input type="file" class="file" name="file" id="file" onchange='submitImgSize1Upload()' style="display:none"/>
			  		<label for="file" class="mui-btn mui-btn--primary mui--align-middle">
						浏览图片…</label> 
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
			<div>
				<div class="mui-panel">
				<form id="user-pw" action="updateUserPW.do">
							<div class="mui-textfield">
								<input type="password" name="PW" id="pw" value=${user.PW }> <label>密码</label>
							</div>
							<div class="mui-textfield">
								<input type="password" id="pw2" value=${user.PW }> <label>再次输入密码</label>
							</div>
							<div>
							<span class="mui--pull-right">
								<button class="mui-btn mui-btn--primary" type="submit"
									value="保存">更改密码</button>
							</span>
							<div class="mui--clearfix"></div>
						</div>
				</form>
				</div>
			</div>
		</div>
	</div>
	
  	<script type="text/javascript">
  		$(document).ready(function(){
  			$("#file").change( function(){
	  			var filepath=$("input[name='file']").val();
	  			var extStart=filepath.lastIndexOf(".");
	  			var ext=filepath.substring(extStart,filepath.length).toUpperCase();
	  			if(ext!=".BMP"&&ext!=".PNG"&&ext!=".GIF"&&ext!=".JPG"&&ext!=".JPEG"){
	  				alert("图片限于 bmp, jpeg, jpg, png, gif, 格式。");
	  				return false;
	  			}else{ 
	  				$("#name01").text(ext)
	  			}
	  			var file_size = 0;
	  			if ( $.browser.msie) {
	  				var img=new Image();
	  				img.src=filepath;
	  				while(true){
	  					if (img.fileSize > 0){
	  						if(img.fileSize>3*1024*1024){
	 		 					alert("图片不大于 100 MB。");
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
	 	 				alert("上传的文件大小不能超过 10 MB！");
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
						$(".avatar").attr("src",jsonObj.fullPath);
						//数据库保存相对路径
						$("#imgSize1").val(jsonObj.relativePath);
					}
					
				};
			$("#picForm").ajaxSubmit(option);
		}
	</script>
	<script type="text/javascript">
		$("#user-info").submit(function(event) {
		      event.preventDefault();
		      var $form = $( this ),
		          url = $form.attr( 'action' );
		      var posting = $.post( url, { UPHONE: $('#phone').val() } );
		      posting.done(function( data ) {
		    	  if (data == "true") {
		    		  alert ("个人信息更新成功。");
		    	  } else {
		    		  alert ("个人信息更新失败。")
		    	  }
		      });
		});
	</script>
		<script type="text/javascript">
		$("#user-pw").submit(function(event) {
		      event.preventDefault();
		      var $form = $( this ),
		          url = $form.attr( 'action' );
		      var posting = $.post( url, { PW: $('#pw').val() } );
		      posting.done(function( data ) {
		    	  if (data == "true") {
		    		  alert ("密码已修改。请重新登录。");
		    		  window.location="login.jsp";
		    	  } else {
		    		  alert ("密码修改失败。")
		    	  }
		      });
		});
	</script>
</body>
</html>