package com.wizontech.kct.common;

public class Enviroment {
	
	//Header 확인용 data 값들. 
	//아이프레임으로 불러올 경우 Header 를 삭제하고 , 직접적으로 페이지로 접근할 경우 Header를 삭제하지  않음.
	//현재 사용은 되고 있으나 , Header를 삭제하는 경우는 없음. 추후에 필요할 수도 있기 때문에 개발 후 기능 자체는 보류.
	public static final String NO_HEADER_SO_CODE = "tp";
	
	public static final String PARAM_NAME_CUST_NO = "so_cust_no";
	public static final String PARAM_NAME_SO_CODE = "so_code";
	public static final String PARAM_NAME_USER_MDN = "mdn";
	public static final String PARAM_NAME_USER_NAME = "name";
	public static final String PARAM_NAME_IS_SERVICE = "service_yn";

}
