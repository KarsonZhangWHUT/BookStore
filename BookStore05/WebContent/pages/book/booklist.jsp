<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@include file="/include/base.jsp"%>

</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">网上书城</span>
		<!-- 这里是操作菜单 -->
		<%@include file="/include/userinfo.jsp"%>
	</div>
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/BookClientServlet?method=pageByPrice" method="post">
					价格：<input id="min" type="text" name="min" value="${param.min }"> 元 - <input
						id="max" type="text" name="max" value="${param.max }"> 元 <input
						type="submit" value="查询" />
				</form>
			</div>
			<div style="text-align: center">
				<span>您的购物车中有3件商品</span>
				<div>
					您刚刚将<span style="color: red">时间简史</span>加入到了购物车中
				</div>
			</div>


			<c:forEach items="${requestScope.page.pageData }" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.imgPath }" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span> <span class="sp2">${book.title }</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span> <span class="sp2">${book.author }</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span> <span class="sp2">￥${book.price }</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span> <span class="sp2">${book.sales }</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span> <span class="sp2">${book.stock }</span>
						</div>
						<div class="book_add">
							<button>加入购物车</button>
						</div>
					</div>
				</div>


			</c:forEach>

		</div>

		<%@include file="/include/page.jsp"%>
	</div>

	<!-- 引入页脚 -->
	<%@include file="/include/pagefoot.jsp"%>
</body>
</html>