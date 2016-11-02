package com.wizontech.kct.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wizontech.kct.common.Enviroment;
import com.wizontech.kct.entity.SingerVo;
import com.wizontech.kct.service.SingerService;
import com.wizontech.kct.util.CheckUtil;
import com.wizontech.kct.util.Json;
import com.wizontech.kct.util.TempUtil;

@RequestMapping(value="/singer")
@Controller
public class SingerController {
	
	private Logger log = LoggerFactory.getLogger(SingerController.class);
	
	@Autowired
	private SingerService singerService;

	//가수 상세 페이지로 이동.
	@RequestMapping
	public ModelAndView singer(HttpServletRequest request, int singer, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/singer/singerList");
		mv.addObject("singer", singer);
		mv.addObject("isHeaderHas", CheckUtil.isHeaderHas(TempUtil.getSoCode(request)));
		return mv;
	}
	
	//가수의 곡 목록을 불러옴. (가수 상세 페이지라 함.)
	@RequestMapping(value="/list")
	public void singerData(SingerVo singerVo, HttpServletResponse response) throws IOException {
		int totalCnt = 0;
		
		List<Map> list = singerService.getList(singerVo);
		
		totalCnt = singerService.totalCnt(singerVo);
		log.debug(totalCnt+"");
		singerVo.setList(list);
		singerVo.setTotalCnt(totalCnt);
		
		String resultStr = Json.stringify(singerVo);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().println("{\"pageData\":"+resultStr+"}");
	}
}
