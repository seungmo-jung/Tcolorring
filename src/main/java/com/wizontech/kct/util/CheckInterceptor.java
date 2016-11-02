package com.wizontech.kct.util;

import javax.servlet.http.HttpSession;

public class CheckInterceptor {
	
	
	public static boolean connectToMainCheck(HttpSession session){
		//데이터가 하나라도 없을 경우 False 를 반환 , 모두 데이터가 존재할 경우 true 를 반환.
		if(session.getAttribute("so_cust_no") == null || session.getAttribute("so_cust_no").equals("")
				|| session.getAttribute("mdn") == null || session.getAttribute("mdn").equals("")
				|| session.getAttribute("name") == null || session.getAttribute("name").equals("")
				|| session.getAttribute("so_code") == null || session.getAttribute("so_code").equals("")
				|| session.getAttribute("service_yn") == null || session.getAttribute("service_yn").equals("")){
			return false;
		} else{
			return true;
		}
	}
	/*
	 * 
	public static int connectToMainCheck(HttpSession session){
		int resultCode = 0;
		
		// 0이 아무 데이터도 없을 경우 , 1이 데이터가 모두 빈 값이 아닐 경우 , 2 so_code만 들어온 경우.
		if(session.getAttribute("so_cust_no") == null || session.getAttribute("so_cust_no").equals("")
				|| session.getAttribute("mdn") == null || session.getAttribute("mdn").equals("")
				|| session.getAttribute("name") == null || session.getAttribute("name").equals("")){
			
			if(session.getAttribute("so_code") == null || session.getAttribute("so_code").equals("")){
				resultCode = 0;
			}else{
				resultCode =2;
			}
			
			return resultCode;
		} else{
			resultCode = 1;
			
			return resultCode;
		}
	}
		 */
	
	public static boolean connectToService(HttpSession session){
		// Service_yn 값 여부 체크 값이 없을 경우나 N 일 경우 False 를 반화나.
		if(session.getAttribute("service_yn") == null || session.getAttribute("service_yn").equals("N") || session.getAttribute("service_yn").equals("")){
			return false;
		}else{
			return true;
		}
	}
}
