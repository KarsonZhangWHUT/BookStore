<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单管理</title>
<%@include file="/include/base.jsp"%>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">订单管理系统</span>
		<!-- 这里是操作菜单 -->
		<%@include file="/include/bookmanager.jsp"%>
	</div>

	<div id="main">
		<table>
			<tr>
				<td>订单号</td>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>

			</tr>

			<c:forEach items="${requestScope.orders }" var="order">
				<tr>
					<td>${order.orderId }</td>
					<td>${order.createDate }</td>
					<td>${order.totalMoney }</td>
					<td><a href="#">查看详情</a></td>
					<td>
						<c:choose>
							<c:when test="${order.status==0 }"><a href="manager/OrderManagerServlet?method=deliver&orderId=${order.orderId }">点击发货</a></c:when>
						</c:choose>
						<c:choose>
							<c:when test="${order.status==1 }">等待收货</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${order.status==2 }">交易完成</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<!-- 引入页脚 -->
	<%@include file="/include/pagefoot.jsp"%>
</body>
</html>