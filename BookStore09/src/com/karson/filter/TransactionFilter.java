package com.karson.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karson.utils.JDBCUtils;

/**
 * Servlet Filter implementation class TransactionFilter
 */
public class TransactionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// 请求方形之前
//		long id = Thread.currentThread().getId();
//		System.out.println("filter放行前的线程号：" + id);
		// 拿到当前线程的连接
		Connection connection = JDBCUtils.getConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			chain.doFilter(request, response);
			// 没有异常。提交事务
			connection.commit();
		} catch (Exception e) {
			System.out.println("filter收到异常了：" + e.getMessage());

			// 回滚事务
			try {
				JDBCUtils.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			HttpServletResponse resp = (HttpServletResponse) response;
			HttpServletRequest req = (HttpServletRequest) request;
			resp.sendRedirect(req.getContextPath() + "/pages/exception.jsp");

		}

		// 关闭连接
		JDBCUtils.releaseConnection(connection);

	}

}
