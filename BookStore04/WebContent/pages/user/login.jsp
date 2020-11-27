<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>尚硅谷会员登录页面</title>
<%@include file="/include/base.jsp"%>
</head>
<body>
	<div id="login_header">
		<img class="logo_img" alt="" src="static/img/logo.gif">
	</div>

	<div class="login_banner">

		<div id="l_content">
			<span class="login_word">欢迎登录</span>
		</div>

		<div id="content">
			<div class="login_form">
				<div class="login_box">
					<div class="tit">
						<h1>尚硅谷会员</h1>
						<a href="pages/user/regist.jsp">立即注册</a>
					</div>
					<div class="msg_cont">
						<b></b> <span class="errorMsg"> ${msg==null?"请输入用户名和密码":msg }
						</span>
					</div>
					<div class="form">
						<form action="UserServlet" method="post">
							<input type="hidden" name="method" value="login"> <label>用户名称：</label>
							<input class="itxt" type="text" placeholder="请输入用户名"
								autocomplete="off" tabindex="1" name="username"
								value="${param.username }" /> <br /> <br /> <label>用户密码：</label>
							<input class="itxt" type="password" placeholder="请输入密码"
								autocomplete="off" tabindex="1" name="password" /> <br /> <br />
							<input type="submit" value="登录" id="sub_btn" />
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- 引入页脚 -->
	<%@include file="/include/pagefoot.jsp"%>
</body>
</html>