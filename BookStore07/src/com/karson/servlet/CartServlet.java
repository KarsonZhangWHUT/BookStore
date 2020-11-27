package com.karson.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.karson.bean.Book;
import com.karson.bean.Cart;
import com.karson.service.BookService;
import com.karson.service.impl.BookServiceImpl;
import com.karson.utils.WebUtils;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BookService bookService = new BookServiceImpl();

	/**
	 * 将图书添加到购物车中
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Book book = WebUtils.param2bean(request, new Book());
		// 购物车整个内容在session中保存
		HttpSession session = request.getSession();
//		Cart cart = (Cart) session.getAttribute("cart");
//		if (cart == null) {
//			// 给session中放入购物车
//			cart = new Cart();
//			session.setAttribute("cart", cart);
//		}
		Cart cart = WebUtils.getCart(request);
		Book one = bookService.getOne(book);
		cart.addBook2Cart(one);
		session.setAttribute("title", one.getTitle());
		String referer = request.getHeader("referer");
		response.sendRedirect(referer);
	}

	// 根据用户传来的图书ID删除图书items
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		Cart cart = (Cart) session.getAttribute("cart");
		Cart cart = WebUtils.getCart(request);
		cart.deleteItem(request.getParameter("id"));
		// 返回cart.jsp
		String referer = request.getHeader("referer");
		response.sendRedirect(referer);
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取购物车
//		HttpSession session = request.getSession();
//		Cart cart = (Cart) session.getAttribute("cart");
		Cart cart = WebUtils.getCart(request);
		// 修改完成
		cart.updateCount(request.getParameter("id"), request.getParameter("count"));
		// 返回cart.jsp
		String referer = request.getHeader("referer");
		response.sendRedirect(referer);
	}

	protected void clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cart cart = WebUtils.getCart(request);
		cart.clear();
		// 返回cart.jsp
		String referer = request.getHeader("referer");
		response.sendRedirect(referer);
	}

}
