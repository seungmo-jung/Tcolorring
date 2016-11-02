package com.wizontech.kct.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizontech.kct.dao.SearchDao;
import com.wizontech.kct.entity.SearchVo;
import com.wizontech.kct.util.PageUtil;

@Service
public class SearchService {

	@Autowired 
	private SearchDao searchDao;
	
	//검색 목록을 가져옴. Type 별로 곡명인지 , 가수명인지, 앨범명인이 구분.
	@Transactional(value="transactionManagerToktokring")
	public List<Map> getList(SearchVo searchVo) {
		PageUtil.getPageGeneralMap(searchVo);
		if(searchVo.getType() == 0) {
			return searchDao.getSong(searchVo);
		} else if(searchVo.getType() == 1) {
			return searchDao.getSinger(searchVo);
		} else {
			return searchDao.getAlbum(searchVo);
		}
	}
	
	//페이징 처리를 위해 또 각각 최대 갯수를 카운트함.
	@Transactional(value="transactionManagerToktokring")
	public int totalCnt(SearchVo searchVo) {
		if(searchVo.getType() == 0) {
			return searchDao.totalCntSong(searchVo);
		} else if(searchVo.getType() == 1) {
			return searchDao.totalCntSinger(searchVo);
		} else {
			return searchDao.totalCntAlbum(searchVo);
		}
	}
}
