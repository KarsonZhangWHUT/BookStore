<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>尚硅谷会员注册页面</title>
<%@include file="/include/base.jsp"%>
<style type="text/css">
.login_form {
	height: 420px;
	margin-top: 25px;
}
</style>
<script type="text/javascript">
	$(function() {
		//2、点击图片更换验证码
		$("#codeImg").click(function() {
			//改变src,让浏览器知道是新请求
			var url = "code.jpg?t=" + Math.random();
			$(this).prop("src", url);

		});

		//	1、提交表单时，验证用户名、密码、邮箱是否符合的规定
		$("#sub_btn")
				.click(
						function() {//重写表单提交按钮的click()方法
							//	2、如果验证失败，交代失败原因
							//  2.1获取用户输入的所有值,这里根据id获取输入框内容
							var $username = $("#username").val();
							var $password = $("#password").val();
							var $repswd = $("#repwd").val();
							var $email = $("#email").val();
							var $code = $("#code").val();
							//  2.2确定用户名规则、密码规则、验证密码规则、邮箱规则、验证码规则
							//  2.3正则表达式：定义正确规则的表达式，用于验证字符串是否符合给定的表达式的规则
							//  2.4使用正则表达式的reg.test(username)方法验证username是否符合规则
							//  2.5定义表达式
							var usernameReg = /^[a-zA-Z0-9_-]{4,16}$/;
							var passwordReg = /^[a-z0-9_-]{6,18}$/;
							var emailReg = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
							if (!usernameReg.test($username)) {
								$(".errorMsg").text("用户名格式错误")
								//  return false阻止表单提交并不再往下验证其他的输入信息是否合法
								return false;
							}
							if (!passwordReg.test($password)) {
								$(".errorMsg").text("密码格式错误")
								//  return false阻止表单提交并不再往下验证其他的输入信息是否合法
								return false;
							}
							if ($repswd !== $password) {
								$(".errorMsg").text("两次输入密码不一致")
								//  return false阻止表单提交并不再往下验证其他的输入信息是否合法
								return false;
							}
							if (!emailReg.test($email)) {
								$(".errorMsg").text("邮箱格式错误")
								//  return false阻止表单提交并不再往下验证其他的输入信息是否合法
								return false;
							}
							if ($code === "") {
								$(".errorMsg").text("请输入验证码")
								return false;
							}
							//	3、验证成功，提交表单

						});

		$("#username").blur(
				function() {
					var username = $("#username").val();
					//用户已经输完用户名，向服务器发送请求，看用户名是否可用
					//验证用户名
					var usernameReg = /^[a-zA-Z0-9_-]{4,16}$/;
					if (usernameReg.test(username)) {
						//用户名验证成功，发请求
						$.get("UserServlet?method=checkUser&username="
								+ username, function(data) {
									$(".errorMsg").text(data)
						});

					}

				});

	});
</script>

</head>
<body>


	<div id="login_header">
		<img class="logo_img" alt="" src="static/img/logo.gif">
	</div>

	<div class="login_banner">

		<div id="l_content">
			<span class="login_word">欢迎注册</span>
		</div>

		<div id="content">
			<div class="login_form">
				<div class="login_box">
					<div class="tit">
						<h1>注册尚硅谷会员</h1>
						<span class="errorMsg"> ${msg==null?"请输入用户名":msg } </span>
					</div>
					<div class="form">
						<form action="UserServlet" method="post">
							<input type="hidden" name="method" value="regist"> <label>用户名称：</label>
							<input class="itxt" type="text" placeholder="请输入用户名"
								autocomplete="off" tabindex="1" name="username" id="username"
								value="${param.username }" /> <br /> <br /> <label>用户密码：</label>
							<input class="itxt" type="password" placeholder="请输入密码"
								autocomplete="off" tabindex="1" name="password" id="password" />
							<br /> <br /> <label>确认密码：</label> <input class="itxt"
								type="password" placeholder="确认密码" autocomplete="off"
								tabindex="1" name="repwd" id="repwd" /> <br /> <br /> <label>电子邮件：</label>
							<input class="itxt" type="text" placeholder="请输入邮箱地址"
								autocomplete="off" tabindex="1" name="email" id="email"
								value="${param.email }" /> <br /> <br /> <label>验证码：</label>
							<input class="itxt" type="text" style="width: 90px;" id="code"
								name="code" /> <img alt="" src="code.jpg"
								style="float: right; margin-right: 40px; width: 120px; height: 40px"
								id="codeImg"> <br /> <br /> <input type="submit"
								value="注册" id="sub_btn" />

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