package com.qdu.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qdu.test.bean.Users;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;


public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 通过request对象获取session
		HttpSession session = request.getSession();
		// 判断session中是否有登录用户
		Users user = (Users) session.getAttribute("user");
		// 如果用户为空，则跳转到登录页面
		if (ObjectUtils.isEmpty(user)) {
			String contexPath = request.getContextPath();
			response.sendRedirect(contexPath + "/users/to_login");
			return false;
		}
		return true;
	}

}
