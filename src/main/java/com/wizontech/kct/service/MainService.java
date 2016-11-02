package com.wizontech.kct.service;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizontech.kct.dao.MainDao;
import com.wizontech.kct.entity.MainVo;
import com.wizontech.kct.entity.UserVo;
import com.wizontech.kct.util.CheckUtil;

@Service
public class MainService {
	
	private static final Logger logger = LoggerFactory.getLogger(MainService.class);
	
	@Autowired
	private MainDao mainDao;
	
//	@Value("#{configs['music.file.dir']}") 
//	private String musicFileDir;
	
	
	//메인 페이지 앨범 데이터 가져요기.
	@Transactional(value="transactionManagerToktokring")
	public List<Map> getAlbum(MainVo mainVo) {
		return mainDao.getAlbum(mainVo);
	}
	
	//메인 페이지 곡 데이터 가져오기.
	@Transactional(value="transactionManagerToktokring")
	public List<Map> getSong(MainVo mainVo) {
		return mainDao.getSong(mainVo);
	}
	
	public String createSession(UserVo user, HttpServletRequest request){
		// 세션 생성.
		String resultUrl = "https://www.tplusmobile.com/u7101.do";
		
		logger.info("Reqest user data");
		logger.info("CCBS_SEQ (post) : " + user.getCCBS_SEQ());
		logger.info("MEM_CELLNUM (post) : " + user.getMEM_CELLNUM());
		logger.info("MEM_NAME (post) : " + user.getMEM_NAME());
		logger.info("SO_CODE (post) : " + user.getSO_CODE());
		logger.info("SERVICE_YN (post) : " + user.getSERVICE_YN());
		
		if(CheckUtil.userDataCheck(user) && CheckUtil.isSocde(user.getSO_CODE())){
			//userData 체크 및 so_code 체크 진행.
			
			logger.info("Session Create " + user.getCCBS_SEQ() + ", " + user.getMEM_CELLNUM());
			HttpSession session = request.getSession(false);
			
			if(session != null){
				session.invalidate();
			}
			
			session = request.getSession(true);
			try {
				session.setAttribute("so_cust_no", user.getCCBS_SEQ());
				session.setAttribute("mdn", user.getMEM_CELLNUM());
				session.setAttribute("name", URLDecoder.decode(user.getMEM_NAME(), "UTF-8"));
				//new String(user.getMEM_NAME().getBytes("8859_1"), "UTF-8")
				session.setAttribute("so_code", user.getSO_CODE());
				session.setAttribute("service_yn", user.getSERVICE_YN());
				
				//SO 설정 진행.
				if(CheckUtil.isKct(user.getSO_CODE())){
					session.setAttribute("so", "KCT");
				}else if(CheckUtil.isTbroad(user.getSO_CODE())){
					session.setAttribute("so", "TBRD");
				}else{
					session.setAttribute("so", "ETC");
				}
				
				resultUrl = "/main.do";
			} catch (Exception e) {
				logger.error("Session Create Error" + user.getCCBS_SEQ() + "," + user.getMEM_CELLNUM() + "," + user.getSERVICE_YN());
				e.printStackTrace();
			}
			
//			logger.debug("CCBS_SEQ (post) : " + user.getCCBS_SEQ() + ", so_cust_no(session) : " + session.getAttribute("so_cust_no"));
//			logger.debug("MEM_CELLNUM (post) : " + user.getMEM_CELLNUM() + ", mdn(session) : " + session.getAttribute("mdn"));
//			logger.debug("MEM_NAME (post) : " + user.getMEM_NAME() + ", name(session) : " + session.getAttribute("name"));
//			logger.debug("SO_CODE (post) : " + user.getSO_CODE() + ", so_code(session) : " + session.getAttribute("so_code"));
//			logger.debug("SERVICE_YN (post) : " + user.getSERVICE_YN() + ", service_yn(session) : " + session.getAttribute("service_yn"));
			
			logger.info("Create session data");
			logger.info("so_cust_no(session) : " + session.getAttribute("so_cust_no"));
			logger.info("mdn(session) : " + session.getAttribute("mdn"));
			logger.info("name(session) : " + session.getAttribute("name"));
			logger.info("so_code(session) : " + session.getAttribute("so_code"));
			logger.info("service_yn(session) : " + session.getAttribute("service_yn"));
			logger.info("Create session success");
			
			return resultUrl;
		}else{
			//if(request.getSession().getAttribute("so_cust_no") == null || request.getSession().getAttribute("so_cust_no").equals("")){
			if(CheckUtil.isKct(user.getSO_CODE())){
				logger.info("Create session faild");
				logger.info("Return tplusmobile login page" + user.getSO_CODE());
				resultUrl = "https://www.tplusmobile.com/u7101.do";
			}else if(CheckUtil.isTbroad(user.getSO_CODE())){
				logger.info("Create session faild");
				logger.info("Return tbroad login page" + user.getSO_CODE());
				resultUrl = "https://www.tbroad.com/members/login/login.jsp";
			}else{
				logger.info("Create session faild");
				logger.info("Return else login page" + user.getSO_CODE());
				resultUrl = "https://www.tplusmobile.com/u7101.do";
			}
			//} else{
				//logger.info("Have a User Session" + request.getSession().getAttribute("so_cust_no"));
				//resultUrl = "/main.do";
			//}
			return resultUrl;
		}
	}
	
}
