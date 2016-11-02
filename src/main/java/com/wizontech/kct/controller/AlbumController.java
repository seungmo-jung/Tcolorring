package com.wizontech.kct.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wizontech.kct.common.Enviroment;
import com.wizontech.kct.entity.AlbumVo;
import com.wizontech.kct.service.AlbumService;
import com.wizontech.kct.util.CheckUtil;
import com.wizontech.kct.util.Json;
import com.wizontech.kct.util.TempUtil;

@RequestMapping(value="/album")
@Controller
public class AlbumController {
	
	private Logger log = LoggerFactory.getLogger(AlbumController.class);
	
	@Autowired
	private AlbumService albumService;

	
	@RequestMapping
	public ModelAndView album(HttpServletRequest request, int album, HttpServletResponse response) {
		//앨범 상세페이지로 이동. 
		
		ModelAndView mv = new ModelAndView("/album/albumInfo");
		mv.addObject("album", album);
		mv.addObject("isHeaderHas", CheckUtil.isHeaderHas(TempUtil.getSoCode(request)));
		return mv;
	}
	
	@RequestMapping(value="/info")
	public void albumData(AlbumVo albumVo, HttpServletResponse response) throws IOException {
		//앨범 상세 정보와 , 수록 곡 정보들을 DB에 조회를 진행하여 데이터를 넘겨줌.
		
		albumVo.setList(albumService.getSong(albumVo));
		albumVo.setAlbum(albumService.getInfo(albumVo));
		
		String resultStr = Json.stringify(albumVo);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().println("{\"pageData\":"+resultStr+"}");
	}
}
