package com.socman.view.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.socman.data.orm.Member;
import com.socman.data.service.SocietyDataService;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private SocietyDataService societyDataService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();

		Member member = (Member) session.getAttribute("member");
		if (member == null) {
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String loginId = auth.getName();
			member = societyDataService.findByLoginId(loginId);
			session.setAttribute("member", member);
		}

		return super.preHandle(request, response, handler);
	}
}