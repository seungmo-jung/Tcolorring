package com.wizontech.kct.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizontech.kct.dao.MoreDao;
import com.wizontech.kct.entity.MoreVo;
import com.wizontech.kct.util.PageUtil;

@Service
public class MoreService {
	
	private static final Logger logger = LoggerFactory.getLogger(MoreService.class);
	
	@Autowired
	private MoreDao moreDao;

	
	//더보기 페이지 앨범 데이터 가져오기.
	@Transactional(value="transactionManagerToktokring")
	public List<Map> getAlbum(MoreVo moreVo) {
		PageUtil.getPageGeneralMap(moreVo);
		return moreDao.getAlbum(moreVo);
	}
	
	//더보기 페이지 앨범 카운트. (페이징처리를 위해)
	@Transactional(value="transactionManagerToktokring")
	public int totalCntAlbum(MoreVo moreVo) {
		return moreDao.totalCntAlbum(moreVo);
	}
	
	//더보기 페이지 곡 데이터 가져오기.
	@Transactional(value="transactionManagerToktokring")
	public List<Map> getSong(MoreVo moreVo) {
		PageUtil.getPageGeneralMap(moreVo);
		return moreDao.getSong(moreVo);
	}
	
	//더보기 페이지 곡 카운트. (페이징 처리를 위해)
	@Transactional(value="transactionManagerToktokring")
	public int totalCntSong(MoreVo moreVo) {
		return moreDao.totalCntSong(moreVo);
	}
	
}
