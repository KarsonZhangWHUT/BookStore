<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
<%@include file="/include/base.jsp"%>
<style type="text/css">
h1 {
	text-align: center;
	margin-top: 200px;
}
</style>
</head>
<body>

	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">我的订单</span>
		<!-- 这里是操作菜单 -->
		<%@include file="/include/userinfo.jsp"%>
	</div>

	<div id="main">
		<c:if test="${!empty requestScope.orders }">
			<table>
				<tr>
					<td>订单号</td>
					<td>日期</td>
					<td>金额</td>
					<td>状态</td>
					<td>详情</td>
				</tr>
				<c:forEach items="${requestScope.orders }" var="order">
					<tr>
						<td>${order.orderId }</td>
						<td>${order.createDate }</td>
						<td>${order. totalMoney}</td>
						<td><c:choose>
								<c:when test="${order.status==0 }">未发货</c:when>
								<c:when test="${order.status==1 }">
									<a
										href="client/OrderClientServlet?method=received&orderId=${order.orderId }">确认收货</a>
								</c:when>
								<c:when test="${order.status==2 }">交易完成</c:when>
							</c:choose></td>
						<td><a href="#">查看详情</a></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<c:if test="${empty requestScope.orders }">
			<h1 style="color: red;">
				没有对应的订单，<a style="color: blue;" href="index.jsp">赶紧去购买吧！</a>
			</h1>
		</c:if>

	</div>

	<!-- 引入页脚 -->
	<%@include file="/include/pagefoot.jsp"%>
</body>
</html>