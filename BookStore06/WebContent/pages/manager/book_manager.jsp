<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书管理</title>
<%@include file="/include/base.jsp"%>
<script type="text/javascript">
	$(function() {
		$(".delBtn").click(function() {
			//alert("删除");
			//this代表当前被点击的a标签内容
			var td = $(this).parent().parent().children(":first");
			if (!confirm("确认删除【" + td.text() + "】吗？")) {
				return false;
			}
		});
	});
</script>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">图书管理系统</span>
		<!-- 这里是操作菜单 -->
		<%@include file="/include/bookmanager.jsp"%>
	</div>

	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.pageData }" var="book">
				<!-- 每一本图书的相信信息 -->
				<tr>
					<td>${book.title }</td>
					<td>${book.price }</td>
					<td>${book.author }</td>
					<td>${book.sales }</td>
					<td>${book.stock }</td>
					<td><a
						href="manager/BookManagerServlet?method=getBook&id=${book.id }&pn=${requestScope.page.pageNo}">修改</a></td>
					<!-- 绑定点击事件 -->
					<td><a class="delBtn"
						href="manager/BookManagerServlet?method=delete&id=${book.id }&pn=${requestScope.page.pageNo}">删除</a></td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
			</tr>
		</table>
		<%@include file="/include/page.jsp"%>

	</div>

	<!-- 引入页脚 -->
	<%@include file="/include/pagefoot.jsp"%>
</body>
</html>