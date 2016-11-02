package com.wizontech.kct.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizontech.kct.dao.AlbumDao;
import com.wizontech.kct.entity.AlbumVo;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumDao albumDao;
	
	
	@Transactional(value="transactionManagerToktokring")
	public Map getInfo(AlbumVo albumVo) {
		//ablum id 를 통해 album 상세 정보를 조회.
		return albumDao.getInfo(albumVo);
	}
	
	@Transactional(value="transactionManagerToktokring")
	public List<Map> getSong(AlbumVo albumVo) {
		//album id 를 통해 album 수록곡 정보들을 조회. 
		return albumDao.getSong(albumVo);			
	}
	
}
