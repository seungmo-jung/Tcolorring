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
public class ServiceInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// Tplring 서비스를 사용 하기 위해 Session 이 생성되어있는지 검사.
		logger.debug("ServiceInterceptor.preHandle process");
		
		logger.debug("mainChecker - " + CheckInterceptor.connectToMainCheck(request.getSession()));
		logger.debug("ServiceChecker - " + CheckInterceptor.connectToService(request.getSession()));
		
		//if(CheckInterceptor.connectToMainCheck(request.getSession())){
		if(CheckInterceptor.connectToMainCheck(request.getSession())){
			//세션의 값들의 Null 체크 진행.
			if(CheckInterceptor.connectToService(request.getSession())){
				//세션의 Service_yn 검사를 진행 , Y일 경우 계속 진행 N일 경우 진행하지 않음.
				//티플링 자체 서비스를 가입하지 않았을 경우 사용하지 못하는 기능들이 있으 해당 서비스의 사용 여부 체크.
				return super.preHandle(request, response, handler);
			}else{
				response.sendRedirect("https://www.tplusmobile.com/");
				return false;
			}
		} else {
			response.sendRedirect("https://www.tplusmobile.com/");
			return false;
		}
//		return super.preHandle(request, response, handler);
	}
}
