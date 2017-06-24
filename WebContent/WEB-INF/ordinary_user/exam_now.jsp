<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<title>正在考试…</title>
</head>
<body>

	<jsp:include page="./header.jsp"></jsp:include>
	
	<div class="mui--appbar-height"></div>

	<div class="mui-appbar mui--appbar-line-height">
		<div class="content-wrapper" id="title">
			<div class="mui-container">
				<h1>正在考试…</h1>
			</div>
		</div>
	</div>

	<div class="content-wrapper">
		<div class="mui-container-fluid">
			<div class="mui-panel">
				<form>
					<ol>
						<li>
							<p>JDK 的构成中不包含以下哪个部分？</p> <input type="radio" name="u1" id="u1a" />
							<label for="u1a">Java 编程语言</label> <br /> <input type="radio"
							name="u1" id="u1b" /> <label for="u1b">工具及工具的 API</label> <br />
							<input type="radio" name="u1" id="u1c" /> <label for="u1c">工具及工具的
								API</label> <br /> <input type="radio" name="u1" id="u1d" /> <label
							for="u1d">工具及工具的 API</label> <br />
						</li>
						<li>
							<p>下列关于 JDK、JRE 和 JVM 的描述，哪项正确？</p> <input type="radio" name="u2"
							id="u2a" /> <label for="u2a">工具及工具的 API</label> <br /> <input
							type="radio" name="u2" id="u2b" /> <label for="u2b">工具及工具的
								API</label> <br /> <input type="radio" name="u2" id="u2c" /> <label
							for="u2c">工具及工具的 API</label> <br /> <input type="radio"
							name="u2" id="u2d" /> <label for="u2d">工具及工具的 API</label> <br />
						</li>
						
						<c:forEach items="${questions}" varStatus="i" var="item" >  
				            <li>
				            	<p>${item.BANK}</p>
				            	<label><input type="radio" name="${i+1}" value="a"/>${item.OPTION1 }</label>
				            	<label><input type="radio" name="${i+1}" value="a"/>${item.OPTION2 }</label>
				            	<label><input type="radio" name="${i+1}" value="a"/>${item.OPTION3 }</label>
				            	<label><input type="radio" name="${i+1}" value="a"/>${item.OPTION4 }</label>
				            </li> 
						</c:forEach>  
					</ol>
					<button class="mui-btn mui-btn--primary" type="submit" value="交卷">交卷</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>