package com.wizontech.kct.util;

import com.wizontech.kct.common.Enviroment;
import com.wizontech.kct.entity.UserVo;

public class CheckUtil {
	public static boolean isHeaderHas(String soCode) {
		//헤더 체크.
		boolean result = false;
		if(!Enviroment.NO_HEADER_SO_CODE.equals(soCode)) {
			result = true;
		}
		return result;
	}
	
	public static boolean isSocde(String soCode){
		//공통 so_code 값 체크. (kct + tbroad 합)
		boolean result = false;
		
		if(soCode.equals("1009") || soCode.equals("1100") || soCode.equals("1101") || soCode.equals("1102") || soCode.equals("1103") || soCode.equals("1106") || soCode.equals("1107") 
				|| soCode.equals("1303") || soCode.equals("1305") || soCode.equals("1306") || soCode.equals("1308") 
				|| soCode.equals("2200") || soCode.equals("2300") || soCode.equals("3200") || soCode.equals("3300") || soCode.equals("3400") || soCode.equals("3500") 
				|| soCode.equals("3700") ||soCode.equals("3800") || soCode.equals("3900") || soCode.equals("4200") || soCode.equals("6100") || soCode.equals("7100") 
				|| soCode.equals("7200") || soCode.equals("7400") || soCode.equals("7600") || soCode.equals("8100") || soCode.equals("8500") || soCode.equals("8600")
				|| soCode.equals("8700") || soCode.equals("8800") || soCode.equals("8850") || soCode.equals("8950")){
			result = true;
		}
		return result;
	}
	
	public static boolean isTbroad(String soCode){
		//TBRD so_code 값 체크.
		boolean result = false;
		if(soCode.equals("2200") || soCode.equals("2300") || soCode.equals("3200") || soCode.equals("3300") || soCode.equals("3400") || soCode.equals("3500") 
				|| soCode.equals("3700") ||soCode.equals("3800") || soCode.equals("3900") || soCode.equals("4200") || soCode.equals("6100") || soCode.equals("7100") 
				|| soCode.equals("7200") || soCode.equals("7400") || soCode.equals("7600") || soCode.equals("8100") || soCode.equals("8500") || soCode.equals("8600")
				|| soCode.equals("8700") || soCode.equals("8800") || soCode.equals("8850") || soCode.equals("8950")){
			result = true;
		}
		return result;
	}
	
	public static boolean isKct(String soCode){
		//KCT so_code 값 체크.
		boolean result = false;
		if(soCode.equals("1009") || soCode.equals("1100") || soCode.equals("1101") || soCode.equals("1102") || soCode.equals("1103") || soCode.equals("1106") || soCode.equals("1107") 
				|| soCode.equals("1303") || soCode.equals("1305") || soCode.equals("1306") || soCode.equals("1308")){
			result = true;
		}
		return result;
	}
	

	
	public static boolean userDataCheck(UserVo user){
		//ccbs_seq 널체크, 값 undefined 체크, 자리수 체크
		// 전화번호 널체크, 자리수 체크
		// 이름 널체크
		// so_code 널체크
		// 서비스 널체크
		boolean result = false;
		if(user.getCCBS_SEQ() != null && !user.getCCBS_SEQ().equals("") && !user.getCCBS_SEQ().equals("undefined") && ccbsSeqLengthCheck(user.getCCBS_SEQ())
			&& user.getMEM_CELLNUM() != null && !user.getMEM_CELLNUM().equals("") && cellNumLengthCheck(user.getMEM_CELLNUM())
			&& user.getMEM_NAME() != null && !user.getMEM_NAME().equals("")
			&& user.getSO_CODE() != null && !user.getSO_CODE().equals("")
			&& user.getSERVICE_YN() != null && !user.getSERVICE_YN().equals("")){
			result = true;
		}
		
		return result;
	}
	
	public static boolean ccbsSeqLengthCheck(String ccbsSeq){
		// 자리수 10자리 체크.
		boolean result = false;
		if(ccbsSeq.length() == 10){
			result  = true;
		}
		return result;
	}
	
	public static boolean cellNumLengthCheck(String cellNum){
		// 전화번호 10자리 11자리 체크.
		boolean result = false;
		if(cellNum.length() == 10 || cellNum.length() == 11){
			result  = true;
		}
		return result;
	}
}
