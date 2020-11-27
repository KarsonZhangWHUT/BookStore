package com.karson.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karson.bean.Book;
import com.karson.bean.Page;
import com.karson.service.BookService;
import com.karson.service.impl.BookServiceImpl;
import com.karson.utils.WebUtils;

/**
 * Servlet implementation class BookManagerServlet
 */
public class BookManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	BookService bookService = new BookServiceImpl();

	/**
	 * 显示分页数据
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 显示第一页数据
		String pn = request.getParameter("pn");
		Page<Book> page = bookService.getPage(pn, "4");
		page.setUrl("manager/BookManagerServlet?method=page");
		
		// 将第一页数据在页面显示
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);

	}

	//改为显示page
//	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// 查出所有的图书
//		List<Book> list = bookService.getAll();
//		// 图书查出来以后交给页面显示
//		request.setAttribute("list", list);
//		// 把数据交给页面，转发
//		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
//	}
//	在update里处理了
//	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		// 1、将提交的图书信息封装为book对象,表单的name和bean属性名一一对应
//		Book book = WebUtils.param2bean(request, new Book());
//		// 2、将图书保存到数据库
//		bookService.add(book);
//		// 保存成功，重回图书列表页面
//		response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=list");
//	}

//	protected void delete(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String pn = request.getParameter("pn");
//		Book book = WebUtils.param2bean(request, new Book());
//		bookService.delete(book);
//		// 删完以后回到列表显示
//		response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=page&pn="+pn);
//	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pn = request.getParameter("pn");
		
		String referer = request.getHeader("referer");
		
		Book book = WebUtils.param2bean(request, new Book());
		bookService.delete(book);
		// 删完以后回到列表显示
		response.sendRedirect(referer);
	}

	protected void getBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = WebUtils.param2bean(request, new Book());
		Book book2 = bookService.getOne(book);
		request.setAttribute("aBook", book2);
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}

//	protected void update(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String pn = request.getParameter("pn");
//		
//		// 封装图书信息
//		Book book = WebUtils.param2bean(request, new Book());
//		if (book.getId() == 0)
//			// 将图书保存到数据库
//			bookService.add(book);
//		else
//			// 修改图书
//			bookService.update(book);
//		// 返回列表页面
//		response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=page&pn="+pn);
//
//	}
//	
	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pn = request.getParameter("pn");
		String referer = request.getHeader("referer");
		
		
		// 封装图书信息
		Book book = WebUtils.param2bean(request, new Book());
		if (book.getId() == 0)
			// 将图书保存到数据库
			bookService.add(book);
		else
			// 修改图书
			bookService.update(book);
		// 返回列表页面
		response.sendRedirect(request.getContextPath() + "/manager/BookManagerServlet?method=page&pn="+pn);

	}

}
