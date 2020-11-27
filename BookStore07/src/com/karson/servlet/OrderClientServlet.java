package com.karson.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.karson.bean.Cart;
import com.karson.bean.Constants;
import com.karson.bean.Order;
import com.karson.bean.User;
import com.karson.service.OrderService;
import com.karson.service.impl.OrderServiceImpl;
import com.karson.utils.WebUtils;

/**
 * Servlet implementation class OrderClientServlet
 */
public class OrderClientServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	OrderService orderService = new OrderServiceImpl();

	protected void checkout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.验证用户是否登录
		HttpSession session = request.getSession();

		// 获取已经登陆的用户
		User loginUser = WebUtils.getLoginUser(request);

		if (loginUser != null) {
			// 2.登录，则直接结账
			Cart cart = WebUtils.getCart(request);
			// 结算，返回订单号
			String orderId = orderService.checkout(cart, loginUser);
			session.setAttribute("orderId", orderId);
			response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");

		} else {
			// 3.没登录直接返回登录页面
			request.setAttribute("msg", "此操作需要登陆，请先登录");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
			;
		}

	}

	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loginUser = WebUtils.getLoginUser(request);
		List<Order> list = orderService.getMyOrder(loginUser.getId());
		// 在域中放置所有订单
		request.setAttribute("orders", list);
		request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
	}

	protected void received(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		// 确认收货
		orderService.updateStatus(orderId, Constants.DELIVERED + "");
		String referer = request.getHeader("referer");
		System.out.println(referer);
		response.sendRedirect(referer);
	}

}
