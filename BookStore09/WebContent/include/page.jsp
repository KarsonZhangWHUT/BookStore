<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(function() {
		$("#goToPage").click(function() {
			//1.获取到用户输入的值
			var pn = $("#pn_input").val();
			//2.发送新的分页请求
			window.location.href("${requestScope.page.url}&pn=" + pn);
		});
	});
</script>


<div id="page_nav">
	<a href="${requestScope.page.url }&pn=1">首页</a>
	<c:if test="${requestScope.page.hasPre }">
		<a href="${requestScope.page.url }&pn=${requestScope.page.pageNo-1 }">上一页</a>
	</c:if>

	<!-- 总页码在5页以内，指定begin和end的值 -->
	<c:if test="${requestScope.page.totalPage<=5 }">
		<c:set var="begin" value="1" scope="page"></c:set>
		<c:set var="end" value="${requestScope.page.totalPage }" scope="page"></c:set>
	</c:if>


	<!-- 总页码在5页以上,才使用连续显示5页的逻辑 -->
	<c:if test="${requestScope.page.totalPage>5 }">
		<!-- 当前页码小于3 -->
		<c:if test="${requestScope.page.pageNo<=3 }">
			<c:set var="begin" value="1" scope="page"></c:set>
			<c:set var="end" value="5" scope="page"></c:set>
		</c:if>
		<!-- 当前页码大于3 -->
		<c:if test="${requestScope.page.pageNo>3 }">
			<c:set var="begin" value="${requestScope.page.pageNo-2 }"
				scope="page"></c:set>
			<c:set var="end" value="${requestScope.page.pageNo+2 }" scope="page"></c:set>
		</c:if>
		<c:if
			test="${requestScope.page.pageNo+2>=requestScope.page.totalPage }">
			<c:set var="begin" value="${requestScope.page.totalPage-4 }"
				scope="page"></c:set>
			<c:set var="end" value="${requestScope.page.totalPage }" scope="page"></c:set>
		</c:if>
	</c:if>

	<c:forEach begin="${pageScope.begin }" end="${pageScope.end }"
		var="pnum">
		<!-- 当前页码不加链接 -->
		<c:if test="${pnum==requestScope.page.pageNo }">
			<span style="color: blue">【${requestScope.page.pageNo }】</span>
		</c:if>
		<!-- 不是当前页面，要加链接 -->
		<c:if test="${pnum!=requestScope.page.pageNo }">
			<a href="${requestScope.page.url }&pn=${pnum }">${pnum }</a>
		</c:if>

	</c:forEach>
	<c:if test="${requestScope.page.hasNext }">
		<a href="${requestScope.page.url }&pn=${requestScope.page.pageNo+1 }">下一页</a>
	</c:if>
	<a href="${requestScope.page.url }&pn=${requestScope.page.totalPage }">末页</a>
	共${requestScope.page.totalPage }页，${requestScope.page.totalCount }条记录
	到第 <input value="${requestScope.page.pageNo }" name="pn" id="pn_input" />
	页 <input type="button" value="确定" id="goToPage">
</div>