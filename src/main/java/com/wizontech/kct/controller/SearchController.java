package com.wizontech.kct.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wizontech.kct.common.Enviroment;
import com.wizontech.kct.entity.SearchVo;
import com.wizontech.kct.service.SearchService;
import com.wizontech.kct.util.CheckUtil;
import com.wizontech.kct.util.Json;
import com.wizontech.kct.util.TempUtil;

@RequestMapping(value="/search")
@Controller
public class SearchController {
	private Logger log = LoggerFactory.getLogger(SearchController.class);
	
	@Autowired
	private SearchService searchService;
	
	//검색 페이지로 이동.
	@RequestMapping
	public ModelAndView search(@RequestParam String type, @RequestParam String query, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView("/search/searchList");
		
		mv.addObject("type", type);
		mv.addObject("query", URLDecoder.decode(query, "UTF-8"));
		mv.addObject("isHeaderHas", CheckUtil.isHeaderHas(TempUtil.getSoCode(request)));
		return mv;
	}
	
	//검색 목록 불러오기.
	@RequestMapping(value="/list")
	public void searchData(SearchVo searchVo, HttpServletResponse response) throws IOException {
		//한글 decoding
		searchVo.setQuery(URLDecoder.decode(searchVo.getQuery(), "UTF-8"));
		//searchVo.setQuery(new String(searchVo.getQuery().getBytes("8859_1"), "UTF-8"));
		int totalCnt = 0;
		
		List<Map> list = searchService.getList(searchVo);
		
		totalCnt = searchService.totalCnt(searchVo);
		
		log.debug(totalCnt+"");
		
		searchVo.setList(list);
		searchVo.setTotalCnt(totalCnt);
		
		String resultStr = Json.stringify(searchVo);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().println("{\"pageData\":"+resultStr+"}");
	}
}
