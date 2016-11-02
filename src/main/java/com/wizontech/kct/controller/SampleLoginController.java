//package com.wizontech.kct.controller;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.support.MessageSourceAccessor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.wizontech.kct.service.MainService;
//
//@RequestMapping(value="/sampleLogin")
//@Controller
//public class SampleLoginController {
//	
//	private static final Logger logger = LoggerFactory.getLogger(SampleLoginController.class);
//	
//	@Autowired
//	private MainService mainService;
//	
//	@Autowired
//	private MessageSourceAccessor messageAccessor;
//	
//	@RequestMapping
//    public ModelAndView getSampleLogin(HttpServletRequest request, HttpServletResponse response) {
//        ModelAndView mav = new ModelAndView("main/sampleLogin");
//        
//        return mav;
//    }
//
//	@RequestMapping(value="/noUseService")
//	public ModelAndView noUseService(HttpServletRequest request, HttpServletResponse response){
//	       ModelAndView mav = new ModelAndView("common/noUseService");
//	        
//	        return mav;
//	}
//}
