package com.wizontech.kct.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizontech.kct.dao.SingerDao;
import com.wizontech.kct.entity.SingerVo;
import com.wizontech.kct.util.PageUtil;

@Service
public class SingerService {

	@Autowired 
	private SingerDao singerDao;
	
	//가수 상세페이지로 곡 목록과 앨범 목록을 가져옴.
	@Transactional(value="transactionManagerToktokring")
	public List<Map> getList(SingerVo singerVo) {
		PageUtil.getPageGeneralMap(singerVo);
		if(singerVo.getType() == 0) {
			return singerDao.getSong(singerVo);			
		} else {
			return singerDao.getAlbum(singerVo);
		}
	}
	
	//페이징 처리를 위해 곡이나 앨범의 전체 개수를 가져옴.
	@Transactional(value="transactionManagerToktokring")
	public int totalCnt(SingerVo singerVo) {
		if(singerVo.getType() == 0) {
			return singerDao.totalCntSong(singerVo);
		} else {
			return singerDao.totalCntAlbum(singerVo);
		}
	}
}
