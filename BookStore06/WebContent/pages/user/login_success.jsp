<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>尚硅谷会员注册页面</title>
<%@include file="/include/base.jsp"%>
<style type="text/css">
h1 {
	text-align: center;
	margin-top: 200px;
}

h1 a {
	color: red;
}
</style>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif">
		<!-- 这里是操作菜单 -->
		<%@include file="/include/userinfo.jsp"%>
	</div>

	<div id="main">

		<h1>
			欢迎回来 <a href="index.html">转到主页</a>
		</h1>

	</div>

	<!-- 引入页脚 -->
	<%@include file="/include/pagefoot.jsp"%>
</body>
</html>