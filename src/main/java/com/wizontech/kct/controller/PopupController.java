package com.wizontech.kct.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

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

import com.wizontech.kct.entity.PlayerVo;
import com.wizontech.kct.entity.PopupVo;
import com.wizontech.kct.entity.PurchaseVo;
import com.wizontech.kct.entity.VcodeVo;
import com.wizontech.kct.service.PopupService;
import com.wizontech.kct.util.Json;
import com.wizontech.kct.util.StringUtil;

@RequestMapping(value="/popup")
@Controller
public class PopupController {
	
	private static final Logger logger = LoggerFactory.getLogger(PopupController.class);
	
	@Autowired
	private PopupService popupService;
	
	@Autowired
	private MessageSourceAccessor messageAccessor;
	
	//구매 팝업창.
	@RequestMapping(value = "/purchasepopup")
	@ResponseBody
	public ModelAndView getPurchasePopup(PopupVo popupVo,HttpServletResponse response) throws IOException {
		popupVo.setVcode_list(popupService.setVcodeList(popupVo.getSong_id()));
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("song_id", popupVo.getSong_id());
		mav.addObject("song_title", popupVo.getVcode_list().get(0).getSong_title());
		mav.addObject("singer_name", popupVo.getVcode_list().get(0).getSinger_name());
		mav.addObject("vcode_list", popupVo.getVcode_list());
		
		mav.setViewName("/popup/purchasePopup");
		return mav;
	}
	
	//플레이어 팝업창.
	@RequestMapping(value = "/playerpopup")
	@ResponseBody
	public ModelAndView listenPopup(PopupVo popupVo,HttpServletResponse response) throws IOException {
		popupVo.setVcode_list(popupService.setVcodeList(popupVo.getSong_id()));
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("song_id", popupVo.getSong_id());
		mav.addObject("song_title", popupVo.getVcode_list().get(0).getSong_title());
		mav.addObject("singer_name", popupVo.getVcode_list().get(0).getSinger_name());
		mav.addObject("vcode_list", popupVo.getVcode_list());
		
		
		mav.setViewName("/popup/playerPopup");
		return mav;
		
	}
	
	//음악 불러오기.
	@RequestMapping(value = "/getplayer")
	@ResponseBody
	public void getPlayer(PlayerVo playerVo, HttpServletRequest request, HttpServletResponse response) throws IOException{
		String mdn = (String) request.getSession().getAttribute("mdn");
		
		//playerVo.setSong_id("4889316");
		
		playerVo.setMdn(mdn);
		
		String resultStr = StringUtil.returnListenUrl(playerVo);
		
        response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(resultStr);
	}
	
	//구매진행.
	@RequestMapping(value = "/purchase")
	@ResponseBody
	public HashMap<String, String>  setPurchase(PurchaseVo purchaseData,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String so_cust_no = (String) request.getSession().getAttribute("so_cust_no");
		String mdn = (String) request.getSession().getAttribute("mdn");
		
		purchaseData.setMdn(mdn);
		purchaseData.setSo_cust_no(so_cust_no);
		
		String resultCode = popupService.setPurchase(purchaseData);
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		if(resultCode.equals("1000")){
			map.put("code", "1000");
			map.put("msg", "성공적으로 구매하셨습니다. 설정 페이지로 이동합니다.");
		}else if(resultCode.equals("2000")){
			map.put("code", "2000");
			map.put("msg", "이미 구매하신 곡입니다.");
		}else if(resultCode.equals("0000")){
			map.put("code", "0000");
			map.put("msg", "구매에 실패하셨습니다.");
		}
		return map;
	}
}
