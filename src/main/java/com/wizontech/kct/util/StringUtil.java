package com.wizontech.kct.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wizontech.kct.common.ServiceInterceptor;
import com.wizontech.kct.entity.CommonSongInfoVo;
import com.wizontech.kct.entity.PlayerVo;

public class StringUtil {
	private static final Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);
	
	//반환.
	public static String returnListenUrl(PlayerVo playerVo){
		String listenUrl= "http://mcd.nate.com/cg/moWebPreHear.xcgi?H=%s&S=%s&MID=%s&SECTION=%s&W=%s";
		//String listenUrl= "http://222.235.208.183/cg/moWebPreHear.xcgi?H=%s&S=%s&MID=%s&SECTION=%s&W=%s";
		String resultUrl = "";
		
		listenUrl = String.format(listenUrl, playerVo.getMdn(), playerVo.getTypeCode(), playerVo.getSong_id(), playerVo.getSong_part(), playerVo.getWindow());
		
		logger.info("request listen url : "+ listenUrl);
		URL url;
		try {
			url = new URL(listenUrl);
			BufferedReader bf;
			
			bf = new BufferedReader(new InputStreamReader(url.openStream()));
			
			resultUrl = bf.readLine();
			
			logger.info("result listen url : "+ resultUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultUrl;
	}
	
	//Song data 파싱.
	public static CommonSongInfoVo parseSongSetVo(String data){
		CommonSongInfoVo resultVo = new CommonSongInfoVo();
		
		String[] dataArray = data.split(",");

		
		resultVo.setSong_id(dataArray[0]);
		resultVo.setLongplay_yn(dataArray[1]);
		resultVo.setSong_part(dataArray[2]);
		resultVo.setVcode(dataArray[3]);
		
		return resultVo;
	}
	
	public static boolean isValueHas(String value) {
		return (value != null && !"".equals(value) && !"null".equals(value));
	}
}
