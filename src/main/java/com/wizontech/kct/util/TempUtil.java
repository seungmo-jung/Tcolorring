package com.wizontech.kct.util;

import javax.servlet.http.HttpServletRequest;

import com.wizontech.kct.common.Enviroment;

public class TempUtil {
	//so_code 가져오기.
	public static String getSoCode(HttpServletRequest request) {
		String so_code = String.valueOf(request.getSession().getAttribute(Enviroment.PARAM_NAME_SO_CODE));
		String result = "";
		if(StringUtil.isValueHas(so_code)) {
			result = so_code;
		} 
		return result;
	}
}
