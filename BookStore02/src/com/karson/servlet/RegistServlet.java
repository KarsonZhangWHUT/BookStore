package com.karson.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karson.bean.User;
import com.karson.service.UserService;
import com.karson.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RegistServlet
 */
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		System.out.println(username+password+email);
		boolean b = userService.regist(new User(username,password,email));
		if(b) {
			//注册成功,转发到注册成功页面
			request.getRequestDispatcher("/pages/user/regist_success.html").forward(request, response);
		}else {
			//注册失败,重定向到注册页面
			response.sendRedirect(request.getContextPath()+"/pages/user/regist.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
