package com.karson.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karson.bean.Constants;
import com.karson.bean.Order;
import com.karson.service.OrderService;
import com.karson.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class OrderManagerServlet
 */
public class OrderManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderServiceImpl();

	/**
	 * 列出所有订单
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取所有订单
		List<Order> list = orderService.getAllOrder();
		// 保存到域中
		request.setAttribute("orders", list);
		// 转发到页面进行显示
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
	}

	/**
	 * 发货
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void deliver(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 从页面获取订单号，修改订单状态
		String orderId = request.getParameter("orderId");
		orderService.updateStatus(orderId, Constants.DELIVERING+"");
		//回到页面
		String referer = request.getHeader("referer");
		response.sendRedirect(referer);
	}

}
