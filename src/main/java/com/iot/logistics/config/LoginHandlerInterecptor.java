package com.iot.logistics.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginHandlerInterecptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object attribute = request.getSession().getAttribute("sessionUser");
		if(attribute == null) {
			request.getRequestDispatcher("/login.html").forward(request, response);
//			response.sendRedirect("/login.html");
			return false;
		}else {
			return true;
		}
	}
}
