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
import com.wizontech.kct.entity.MoreVo;
import com.wizontech.kct.service.MoreService;
import com.wizontech.kct.util.CheckUtil;
import com.wizontech.kct.util.Json;
import com.wizontech.kct.util.TempUtil;

@RequestMapping(value="/more")
@Controller
public class MoreController {
	
	private Logger log = LoggerFactory.getLogger(MoreController.class);
	
	@Autowired
	private MoreService moreService;
	
	
	@RequestMapping(value="/album")
	public ModelAndView more(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//더보기 페이지중 앨범 더보기 페이지로 이동.
		ModelAndView mv = new ModelAndView("more/albumMore");
		mv.addObject("isHeaderHas", CheckUtil.isHeaderHas(TempUtil.getSoCode(request)));
		return mv;
	}
	
	@RequestMapping(value="/album_data")
	public void moreAlbum(HttpServletRequest request, MoreVo moreVo, HttpServletResponse response) throws IOException {
		// 앨범 더보기 목록을 불러오는 메소드.
		int totalCnt = 0;
		
		List<Map> list = moreService.getAlbum(moreVo);
		
		totalCnt = moreService.totalCntAlbum(moreVo);
		moreVo.setList(list);
		moreVo.setTotalCnt(totalCnt);
		
		String resultStr = Json.stringify(moreVo);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().println("{\"pageData\":"+resultStr+"}");
	}
	
	
	@RequestMapping(value="/top")
	public ModelAndView topSongMore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//더보기 페이지중 인기곡 더보기 페이지로 이동..
		ModelAndView mv = new ModelAndView("more/topMore");
		mv.addObject("isHeaderHas", CheckUtil.isHeaderHas(TempUtil.getSoCode(request)));
		return mv;
	}
	
	@RequestMapping(value="/new")
	public ModelAndView newSongMore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//더보기 페이지중 최신곡 더보기 페이지로 이동.
		ModelAndView mv = new ModelAndView("more/newMore");
		mv.addObject("isHeaderHas", CheckUtil.isHeaderHas(TempUtil.getSoCode(request)));
		return mv;
	}

	@RequestMapping(value="/genre")
	public ModelAndView genreSongMore(HttpServletRequest request, String genre, HttpServletResponse response) throws IOException {
		//더보기 페이지중 장르별 더보기 페이지로 이동.
		ModelAndView mv = new ModelAndView("more/genreMore");
		mv.addObject("genre", genre);
		mv.addObject("isHeaderHas", CheckUtil.isHeaderHas(TempUtil.getSoCode(request)));
		return mv;
	}
	
	@RequestMapping(value="/song_data")
	public void genreSongList(MoreVo moreVo, HttpServletResponse response) throws IOException {
		// 타입 별로 곡 더보기 목록을 불러옴. (0일 경우, 1일 경우, 2일 경우)
		int totalCnt = 0;
		
		List<Map> list = moreService.getSong(moreVo);
		
		totalCnt = moreService.totalCntSong(moreVo);
		moreVo.setList(list);
		moreVo.setTotalCnt(totalCnt);
		
		String resultStr = Json.stringify(moreVo);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().println("{\"pageData\":"+resultStr+"}");
	}
}
