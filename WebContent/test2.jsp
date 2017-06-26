<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  	<script src="jquery-1.7.1.min.js" type="text/javascript"></script>
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
  <title>无标题文档</title>
  </head>
  <body>
	<form id = "picForm" action="upload.do" method="post" enctype="multipart/form-data">
	  <table width="500" cellspacing="0" cellpadding="0">
		  <tr>
		  	<td width="72" id="name01"> </td>
		  	<td><img id='imgSize1ImgSrc' src='${picPath }${item.pic }'  height="100" width="100" /></td>
		  	<label>用户名：</label><input type="text" name="name"/><br/> <!-- onchange='submitImgSize1Upload()' -->
		  	<td width="242"><input type="file" class="file" name="file" id="file" /></td>
		  	<td width="184" id="size01" class="size02"> </td>
		  </tr>
	  </table>
	  <input type="submit" value="上传图片"/>
	</form>
  </body>
</html>
