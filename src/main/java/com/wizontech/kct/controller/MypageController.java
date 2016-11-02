package com.wizontech.kct.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wizontech.kct.common.Enviroment;
import com.wizontech.kct.entity.CommonSongInfoVo;
import com.wizontech.kct.entity.PageVo;
import com.wizontech.kct.entity.PurchaseHistListVo;
import com.wizontech.kct.entity.PurchaseHistVo;
import com.wizontech.kct.service.MypageService;
import com.wizontech.kct.util.CheckUtil;
import com.wizontech.kct.util.Json;
import com.wizontech.kct.util.StringUtil;
import com.wizontech.kct.util.TempUtil;

@RequestMapping(value="/mypage")
@Controller
public class MypageController {
	
	@Autowired
	private MypageService mypageService;
	
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);
	
	@Autowired
	private MessageSourceAccessor messageAccessor;
	
	@RequestMapping(value = "/mypage")
	public ModelAndView getMypage(HttpServletRequest request, HttpServletResponse response){
		//마이 페이지로 이동.
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/mypage/mypage");
		mav.addObject("isHeaderHas", CheckUtil.isHeaderHas(TempUtil.getSoCode(request)));
		
		return mav;
	}
	
	@RequestMapping(value="/purchasehist")
	public void getPurchase(HttpServletRequest request, PurchaseHistVo purchaseHistVo, HttpServletResponse response) throws IOException {
		//유저의 구매 목록을 불러오는 메소드.
		int totalCnt = 0;
		purchaseHistVo.setSo_cust_no((String) request.getSession().getAttribute("so_cust_no"));
		purchaseHistVo.setMdn((String) request.getSession().getAttribute("mdn"));
		
		List<PurchaseHistListVo> purchaseData = mypageService.getPurchase(purchaseHistVo);
		totalCnt = mypageService.totalCntPurchase(purchaseHistVo);
		
		purchaseHistVo.setList(purchaseData);
		purchaseHistVo.setTotalCnt(totalCnt);
		
		String resultStr = Json.stringify(purchaseHistVo);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().println("{\"pageData\":"+resultStr+"}");
	}
	
	//기본곡 설정.
	@RequestMapping(value = "/default_song_set")
	@ResponseBody
	public HashMap<String, String> setDefaultSong(CommonSongInfoVo commonSongInfoVo,HttpServletRequest request ,HttpServletResponse response) throws IOException{
		commonSongInfoVo.setMdn((String) request.getSession().getAttribute("mdn"));
		
		String resultCode = mypageService.setDefaultSong(commonSongInfoVo);
		
		HashMap<String, String> map = new HashMap<String, String>();

		if(resultCode.equals("1000")){
			map.put("code", "1000");
			map.put("msg", "성공적으로 설정하셨습니다.");
		}else if(resultCode.equals("0000")){
			map.put("code", "0000");
			map.put("msg", "설정에 실패하셨습니다.");
		}
		return map;
	}
	
	//기본곡 삭제.
	@RequestMapping(value = "/default_song_del")
	@ResponseBody
	public HashMap<String, String> delDefaultSong(HttpServletRequest request ,HttpServletResponse response){
		String mdn = (String) request.getSession().getAttribute("mdn");
		
		String resultCode = mypageService.deleteDefaultSong(mdn);
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		if(resultCode.equals("1000")){
			map.put("code", "1000");
			map.put("msg", "기본음원으로 변경되었습니다.");
		}else if(resultCode.equals("0000")){
			map.put("code", "0000");
			map.put("msg", "기본음원 변경에 실패하셨습니다.");
		}
		return map;
	}
}
