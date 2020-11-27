package com.karson.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.karson.bean.User;
import com.karson.service.UserService;
import com.karson.service.impl.UserServiceImpl;
import com.karson.utils.WebUtils;

/**
 * 处理和用户有关系的请求 抽取出BaseServlet后，UserServlet里面只需要编写响应的处理逻辑即可
 * 
 * 
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	UserService userService = new UserServiceImpl();

	protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		String email = request.getParameter("email");

		User user = WebUtils.param2bean(request, new User());
		boolean b = userService.regist(user);
		if (b) {
			// 注册成功,转发到注册成功页面
			request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
		} else {
			// 注册失败,重定向到注册页面
			request.setAttribute("msg", "用户已存在");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		
		User user2 = WebUtils.param2bean(request, new User());
		User user = userService.login(user2);
		if (user == null) {
			// 登陆失败，返回登陆页面，采用转发
			request.setAttribute("msg", "用户名密码错误");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);

		} else {
			// 登陆成功,重定向到成功页面
			response.sendRedirect(request.getContextPath() + "/pages/user/login_success.jsp");
		}
	}

}
