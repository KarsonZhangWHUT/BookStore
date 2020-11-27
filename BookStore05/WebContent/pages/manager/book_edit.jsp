<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑图书</title>
<%@include file="/include/base.jsp"%>
<style type="text/css">
h1 {
	text-align: center;
	margin-top: 200px;
}

h1 a {
	color: red;
}

input {
	text-align: center;
}
</style>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">编辑图书</span>
		<!-- 这里是操作菜单 -->
		<%@include file="/include/bookmanager.jsp"%>
	</div>

	<div id="main">
		<form action="manager/BookManagerServlet" method="post">
			<input type="hidden" name="method" value="update" /> <input
				type="hidden" name="id" value="${requestScope.aBook.id }" />
				<input type="hidden" name="pn" value="${param.pn }"/>
			<table>
				<tr>
					<td>名称</td>
					<td>价格</td>
					<td>作者</td>
					<td>销量</td>
					<td>库存</td>
					<td colspan="2">操作</td>
				</tr>
				<tr>
					<td><input name="title" type="text"
						value="	${requestScope.aBook.title }" /></td>
					<td><input name="price" type="text"
						value="${requestScope.aBook.price }" /></td>
					<td><input name="author" type="text"
						value="${requestScope.aBook.author }" /></td>
					<td><input name="sales" type="text"
						value="${requestScope.aBook.sales }" /></td>
					<td><input name="stock" type="text"
						value="${requestScope.aBook.stock }" /></td>
					<td><input type="submit" value="提交" /></td>
				</tr>
			</table>
		</form>


	</div>

	<!-- 引入页脚 -->
	<%@include file="/include/pagefoot.jsp"%>
</body>
</html>