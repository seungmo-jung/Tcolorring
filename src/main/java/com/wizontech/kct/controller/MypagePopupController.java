package com.wizontech.kct.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wizontech.kct.entity.CallerListVo;
import com.wizontech.kct.entity.CommonSongInfoVo;
import com.wizontech.kct.entity.PopupVo;
import com.wizontech.kct.entity.PurchaseHistListVo;
import com.wizontech.kct.entity.PurchaseSongVo;
import com.wizontech.kct.entity.TimeListVo;
import com.wizontech.kct.service.MypagePopupService;
import com.wizontech.kct.util.Json;
import com.wizontech.kct.util.StringUtil;

@RequestMapping(value="/mypage/popup")
@Controller
public class MypagePopupController {
	
	private static final Logger logger = LoggerFactory.getLogger(MypagePopupController.class);
	
	@Autowired
	private MypagePopupService mypagePopupService;
	
	//구매 기록 리스트 불러오기.
	@RequestMapping(value="/purchasesong")
	public void getSongList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String so_cust_no = (String) request.getSession().getAttribute("so_cust_no");
		
		List<PurchaseSongVo> list = mypagePopupService.getSong(so_cust_no);
		
		String resultStr = Json.stringify(list);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().println("{\"pageData\":"+resultStr+"}");
	}
	
	//발신자별 리스트 팝업.
	@RequestMapping(value = "/caller_list_popup")
	public ModelAndView getCallerListPopup(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("/mypage/popup/callerList");
	}
	
	//듣기 플레이어 팝업.
	@RequestMapping(value = "/listen_player_popup")
	public ModelAndView getListenPlayerPopup(PurchaseHistListVo purchaseHistListVo, HttpServletRequest request, HttpServletResponse response) throws IOException{
		ModelAndView mav = new ModelAndView("/mypage/popup/ListenPlayer");
		
		PopupVo pv = mypagePopupService.getListenSongInf(purchaseHistListVo.getSong_id());
		
		mav.addObject("song_id", new String(purchaseHistListVo.getSong_id()));
		mav.addObject("song_part", new String(purchaseHistListVo.getSong_part()));
		mav.addObject("song_title", pv.getSong_title());
		mav.addObject("singer_name", pv.getSinger_name());
		mav.addObject("c_longplay_yn", URLDecoder.decode(purchaseHistListVo.getC_longplay_yn(), "UTF-8"));
		mav.addObject("c_song_part", URLDecoder.decode(purchaseHistListVo.getC_song_part(), "UTF-8"));
		//mav.addObject("c_song_part", new String(purchaseHistListVo.getC_song_part().getBytes("8859_1"), "UTF-8"));
		
		return mav;
	}
	
	//발신자별 리스트를 불러옴.
	@RequestMapping(value = "/caller_list")
	public void getCallerList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String mdn = (String) request.getSession().getAttribute("mdn");
		
		List<CallerListVo> list = mypagePopupService.getCallerList(mdn);
		
		String resultStr = Json.stringify(list);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().println("{\"pageData\":"+resultStr+"}");
	}
	
	//발신자별 설정 팝업창.
//	@RequestMapping(value = "/caller_set_popup")
//	public ModelAndView getCallerSetPopup(HttpServletRequest request, HttpServletResponse response){
//		
//		return new ModelAndView("/mypage/popup/callerSet");
//	}
	
	//발신자별 수정 팝업창.
//	@RequestMapping(value = "/caller_edit_popup")
//	public ModelAndView getCallerEditPopup(@RequestParam String caller_mdn, HttpServletResponse response) throws IOException{
//		ModelAndView mav = new ModelAndView();
//		
//		mav.addObject("caller_mdn", new String(caller_mdn.getBytes("8859_1"), "UTF-8"));
//		
//		logger.info(caller_mdn);
//		
//		mav.setViewName("/mypage/popup/callerEdit");
//		return mav;
//	}
	
	//발신자별 설정 진행.
		@RequestMapping(value = "/caller_set")
		@ResponseBody
		public HashMap<String, String> setCaller(@RequestParam String song_inf,@RequestParam String caller_mdn, HttpServletRequest request, HttpServletResponse response){
			String mdn = (String) request.getSession().getAttribute("mdn");
			
			CommonSongInfoVo commonSongInfoVo = StringUtil.parseSongSetVo(song_inf);
			commonSongInfoVo.setCaller_mdn(caller_mdn);
			commonSongInfoVo.setMdn(mdn);
			
			String resultCode = mypagePopupService.insertCaller(commonSongInfoVo);
			
			HashMap<String, String> map = new HashMap<String, String>();

			if(resultCode.equals("1000")){
				map.put("code", "1000");
				map.put("msg", "성공적으로 설정하셨습니다.");
				logger.info(map.get("code"));
			}else if(resultCode.equals("0000")){
				map.put("code", "0000");
				map.put("msg", "설정에 실패하셨습니다.");
			}
			return map;
		}
		
		// 발신자별 수정 진행.
//		@RequestMapping(value = "/caller_edit")
//		@ResponseBody
//		public HashMap<String, String> editCaller(@RequestParam String song_inf, @RequestParam String caller_mdn, @RequestParam String original_caller_mdn, HttpServletRequest request, HttpServletResponse response){
//			String mdn = (String) request.getSession().getAttribute("mdn");
//			
//			CommonSongInfoVo commonSongInfoVo = StringUtil.parseSongSetVo(song_inf);
//			commonSongInfoVo.setMdn(mdn);
//			commonSongInfoVo.setCaller_mdn(caller_mdn);
//			commonSongInfoVo.setOriginal_caller_mdn(original_caller_mdn);
//			
//			String resultCode = mypagePopupService.updateCaller(commonSongInfoVo);
//			
//			HashMap<String, String> map = new HashMap<String, String>();
//
//			if(resultCode.equals("1000")){
//				map.put("code", "1000");
//				map.put("msg", "성공적으로 수정하셨습니다.");
//				logger.info(map.get("code"));
//			}else if(resultCode.equals("0000")){
//				map.put("code", "0000");
//				map.put("msg", "수정에 실패하셨습니다.");
//			}
//			return map;
//		}
		
		// 발신자별 삭제 진행.
		@RequestMapping(value = "/caller_delete")
		@ResponseBody
		public HashMap<String, String> delCaller(@RequestParam String caller_mdn, HttpServletRequest request, HttpServletResponse response){
			String mdn = (String) request.getSession().getAttribute("mdn");
			
			CommonSongInfoVo commonSongInfoVo = new CommonSongInfoVo();
			commonSongInfoVo.setMdn(mdn);
			commonSongInfoVo.setCaller_mdn(caller_mdn);
			
			String resultCode = mypagePopupService.deleteCaller(commonSongInfoVo);
			
			HashMap<String, String> map = new HashMap<String, String>();

			if(resultCode.equals("1000")){
				map.put("code", "1000");
				map.put("msg", "성공적으로 삭제하셨습니다.");
				logger.info(map.get("code"));
			}else if(resultCode.equals("0000")){
				map.put("code", "0000");
				map.put("msg", "삭제에 실패하셨습니다.");
			}
			return map;
		}
	
	//시간대별 리스트 팝업창.
	@RequestMapping(value = "/time_list_popup")
	public ModelAndView getTimeListPage(HttpServletRequest request, HttpServletResponse response){
		return new ModelAndView("/mypage/popup/timeList");
	}
	
	//시간대별 설정 팝업창.
//	@RequestMapping(value="/time_set_popup")
//	public ModelAndView getTimeSetPopup(HttpServletResponse response){
//		return new ModelAndView("mypage/popup/timeSet");
//	}
	
	//시간대별 수정 팝업창.
//	@RequestMapping(value = "/time_edit_popup")
//	public ModelAndView getTimeEditPopup(@RequestParam int s_time, @RequestParam int e_time, HttpServletResponse response) throws IOException{
//		ModelAndView mav = new ModelAndView();
//		
//		mav.addObject("s_time", s_time);
//		mav.addObject("e_time", e_time);
//		
//		mav.setViewName("/mypage/popup/timeEdit");
//		return mav;
//	}
	
	//시간대별 리스트를 불러옴.
	@RequestMapping(value="/time_list")
	public void getTimeList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String mdn = (String) request.getSession().getAttribute("mdn");
		
		List<TimeListVo> list = mypagePopupService.getTimeList(mdn);
		
		String resultStr = Json.stringify(list);
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().println("{\"pageData\":"+resultStr+"}");
	}
	
	//시간대별 설정 삭제.
	@RequestMapping(value="/time_delete")
	@ResponseBody
	public HashMap<String, String> delTime(@RequestParam int s_time, HttpServletRequest request, HttpServletResponse response){
		String mdn = (String) request.getSession().getAttribute("mdn");
		
		CommonSongInfoVo commonSongInfoVo = new CommonSongInfoVo();
		
		commonSongInfoVo.setMdn(mdn);
		commonSongInfoVo.setS_time(s_time);

		String resultCode = mypagePopupService.deleteTime(commonSongInfoVo);
		
		HashMap<String, String> map = new HashMap<String, String>();

		if(resultCode.equals("1000")){
			map.put("code", "1000");
			map.put("msg", "성공적으로 삭제하셨습니다.");
			logger.info(map.get("code"));
		}else if(resultCode.equals("0000")){
			map.put("code", "0000");
			map.put("msg", "삭제에 실패하셨습니다.");
		}
		return map;
	}
	

	
	//시간대별 설정
	@RequestMapping(value="/time_set")
	@ResponseBody
	public HashMap<String, String> setTime(@RequestParam int s_time,@RequestParam int e_time, @RequestParam String song_inf, HttpServletRequest request, HttpServletResponse response){
		String mdn = (String) request.getSession().getAttribute("mdn");
		
		CommonSongInfoVo commonSongInfoVo = StringUtil.parseSongSetVo(song_inf);
		commonSongInfoVo.setMdn(mdn);
		commonSongInfoVo.setS_time(s_time);
		commonSongInfoVo.setE_time(e_time);
		
		logger.info("Set Start Time : " +  commonSongInfoVo.getS_time());
		logger.info("End Start Time : " + commonSongInfoVo.getE_time());
		
		String resultCode = mypagePopupService.insertTime(commonSongInfoVo);
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		if(resultCode.equals("1000")){
			map.put("code", "1000");
			map.put("msg", "성공적으로 설정하셨습니다.");
			logger.info(map.get("code"));
		}else if(resultCode.equals("0000")){
			map.put("code", "0000");
			map.put("msg", "설정에 실패하셨습니다.");
		}
		return map;
	}
	
	//시간 대별 수정.
//	@RequestMapping(value="/time_edit")
//	@ResponseBody
//	public HashMap<String, String> editTime(@RequestParam int s_time,@RequestParam int e_time, @RequestParam int original_s_time, @RequestParam int original_e_time, @RequestParam String song_inf, HttpServletRequest request, HttpServletResponse response){
//		String mdn = (String) request.getSession().getAttribute("mdn");
//		
//		CommonSongInfoVo commonSongInfoVo = StringUtil.parseSongSetVo(song_inf);
//		commonSongInfoVo.setMdn(mdn);
//		commonSongInfoVo.setS_time(s_time);
//		commonSongInfoVo.setE_time(e_time);
//		commonSongInfoVo.setOriginal_s_time(original_s_time);
//		commonSongInfoVo.setOriginal_e_time(original_e_time);
//		
//		String resultCode = mypagePopupService.updateTime(commonSongInfoVo);
//		
//		HashMap<String, String> map = new HashMap<String, String>();
//		
//		if(resultCode.equals("1000")){
//			map.put("code", "1000");
//			map.put("msg", "성공적으로 설정하셨습니다.");
//			logger.info(map.get("code"));
//		}else if(resultCode.equals("0000")){
//			map.put("code", "0000");
//			map.put("msg", "설정에 실패하셨습니다.");
//		}
//		return map;
//	}
	
}	
