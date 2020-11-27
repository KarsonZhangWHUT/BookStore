package com.karson.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.code.kaptcha.Constants;
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

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	protected void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		String email = request.getParameter("email");

		// 获取用户输入的验证码，获取session中的验证码
		String code = request.getParameter("code");
		String sessionCode = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
		// 如果验证码一致则注册，否则回到注册界面并提示验证码错误
		if (!sessionCode.equalsIgnoreCase(code)) {
			// 验证码错误,重定向到注册页面
			request.setAttribute("msg", "验证码错误");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			return;
		}

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

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");

		User user2 = WebUtils.param2bean(request, new User());
		User user = userService.login(user2);
		// 将用户保存到session中
		// 获取session
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		if (user == null) {
			// 登陆失败，返回登陆页面，采用转发
			request.setAttribute("msg", "用户名密码错误");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);

		} else {
			// 登陆成功,重定向到成功页面
			response.sendRedirect(request.getContextPath() + "/pages/user/login_success.jsp");
		}
	}

	/**
	 * 用户登出操作
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		// 销毁session
		session.invalidate();
		// 点击登出后返回商城首页
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
//	protected void checkUser(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		//页面应该把用户名传过来  key是username
//		User user = WebUtils.getLoginUser(request);
//		userService.ch
//		
//	}

}
