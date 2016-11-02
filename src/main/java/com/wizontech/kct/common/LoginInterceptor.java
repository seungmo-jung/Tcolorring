package com.wizontech.kct.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wizontech.kct.util.CheckInterceptor;

@SessionAttributes("user")
public class LoginInterceptor extends HandlerInterceptorAdapter {
	//로그인 인터셉터이다.
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//로그인 페이지로 접근을 시도할 경우 아래의 검사를 먼저 진행한다.
		
		logger.debug("LoginInterceptor.preHandle process");
		logger.debug("CheckInterceptor connecToMainCheck (request.getSession) : " + CheckInterceptor.connectToMainCheck(request.getSession()) );
		
		if(CheckInterceptor.connectToMainCheck(request.getSession())){
			// 데이터가 존재하므로 페이지 접근 허용.
			return super.preHandle(request, response, handler);
		}else {
			response.sendRedirect("https://www.tplusmobile.com/u7101.do");
			return false;
		}
//		return super.preHandle(request, response, handler); 58.151
	}
}
