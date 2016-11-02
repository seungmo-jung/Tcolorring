package com.wizontech.kct.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.wizontech.kct.entity.MainVo;
import com.wizontech.kct.entity.UserVo;
import com.wizontech.kct.service.MainService;
import com.wizontech.kct.util.CheckUtil;
import com.wizontech.kct.util.Json;
import com.wizontech.kct.util.TempUtil;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private MainService mainService;
	
	@Autowired
	private MessageSourceAccessor messageAccessor;
	
	@RequestMapping(value="/main")
    public ModelAndView getMain(HttpServletRequest request, HttpServletResponse response){
		//main 페이지로 이동.
        ModelAndView mav = new ModelAndView("main/main");
        
       mav.addObject("isHeaderHas", CheckUtil.isHeaderHas(TempUtil.getSoCode(request)));
        return mav;
    }
	
	@RequestMapping(value="/main/album")
	public void newAlbum(@ModelAttribute MainVo mainVo, HttpServletResponse response) throws IOException {
		//메인 페이지에서 최신 앨범 목록을 불러오는 메소드.
		
		mainVo.setList(mainService.getAlbum(mainVo));
		
		String resultStr = Json.stringify(mainVo);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().println("{\"pageData\":"+resultStr+"}");
	}
	
	@RequestMapping(value="/main/song")
	public void topSong(@ModelAttribute MainVo mainVo, HttpServletResponse response) throws IOException {
		//메인 페이지에서 음악 목록을 불러오는 메소드.
		mainVo.setList(mainService.getSong(mainVo));
		
        String resultStr = Json.stringify(mainVo);
        response.setContentType("application/json; charset=UTF-8");
		response.getWriter().println("{\"pageData\":"+resultStr+"}");
	}
	
	
//	@ExceptionHandler(Exception.class)
//	public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response){
//		//에러 페이지 전환용이였으나 사용되지 않음.
//		ModelAndView mav = new ModelAndView("/common/errorPage");
//		mav.addObject("isHeaderHas", CheckUtil.isHeaderHas(TempUtil.getSoCode(request)));
//		
//		return mav;
//	}
	
	@RequestMapping(value="/",  method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView  createSession(@ModelAttribute UserVo user, HttpServletRequest request, HttpServletResponse response){
		//세션 생성 메소드.
		String returnUrl = "/main.do";
		
		String strCurrentUrl = request.getScheme() + "://" + request.getServerName();
		
		if(strCurrentUrl.equals("https://tplring.com") || strCurrentUrl.equals("https://tplring.com/")){
			//www가 안붙었을 경우 www.tplring.com 으로 처리.
			returnUrl = "https://www.tplring.com/main.do";
			
			return new ModelAndView("redirect:"+ returnUrl);
		}else {
			returnUrl = mainService.createSession(user, request);
			response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\"");
				
			return new ModelAndView("redirect:"+ returnUrl);
		}
	}
}
