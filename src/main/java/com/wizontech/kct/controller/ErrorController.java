package com.wizontech.kct.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wizontech.kct.service.MainService;

@Controller
public class ErrorController {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private MessageSourceAccessor messageAccessor;
	
//	@RequestMapping(value="/error")
//    public ModelAndView getMain(HttpServletRequest request, HttpServletResponse response) {
//		//현재 하는 역할 x. 원래 목적은 403, 404, 500 error 가 발생할 경우 errorpage로 넘기는 역할을 하려 했으나 현재 뷰단에서 바로 처리중.
//        ModelAndView mav = new ModelAndView("/common/errorPage");
//        
//        logger.debug("403, 404 , 500 error");
//        
//        return mav;
//    }
}
