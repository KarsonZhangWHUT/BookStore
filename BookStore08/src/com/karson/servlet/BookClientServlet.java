package com.karson.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karson.bean.Book;
import com.karson.bean.Page;
import com.karson.service.BookService;
import com.karson.service.impl.BookServiceImpl;

/**
 * Servlet implementation class BookClientServlet
 */
public class BookClientServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	BookService bookService = new BookServiceImpl();

	protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 显示第一页数据
		String pn = request.getParameter("pn");
		Page<Book> page = bookService.getPage(pn, "4");
		page.setUrl("client/BookClientServlet?method=page");
		// 将第一页数据在页面显示
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/book/booklist.jsp").forward(request, response);
	}

	protected void pageByPrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String minPrice = request.getParameter("min");
		String maxPrice = request.getParameter("max");

		String pn = request.getParameter("pn");
		

		Page<Book> pageByPrice = bookService.getPageByPrice(pn, "4", minPrice, maxPrice);
		pageByPrice.setUrl("client/BookClientServlet?method=pageByPrice&min=" + minPrice + "&max=" + maxPrice);

		request.setAttribute("page", pageByPrice);

		request.getRequestDispatcher("/pages/book/booklist.jsp").forward(request, response);

	}

}
