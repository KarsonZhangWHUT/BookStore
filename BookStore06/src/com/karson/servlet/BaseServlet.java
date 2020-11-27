package com.karson.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String method = request.getParameter("method");
		/*
		 * if ("regist".equals(method)) { regist(request, response); } else if
		 * ("login".equals(method)) { login(request, response); }
		 */
		try {
			Method method2 = this.getClass().getDeclaredMethod(method, HttpServletRequest.class,
					HttpServletResponse.class);
			// 保证此方法时可访问的
			method2.setAccessible(true);
			// 执行此方法
			method2.invoke(this, request, response);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
