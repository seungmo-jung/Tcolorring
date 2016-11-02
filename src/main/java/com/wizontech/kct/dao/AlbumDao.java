package com.wizontech.kct.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wizontech.kct.entity.AlbumVo;

@Repository
public class AlbumDao {
	
	@Autowired
	@Resource(name="sqlSessionToktokring")
	private SqlSession sqlSession;
	
	public Map getInfo(AlbumVo pmap) {
		//ablum id 를 통해 album 상세 정보를 조회.
		return sqlSession.selectOne("albumMapper.getInfo", pmap);
	}
	
	public List<Map> getSong(AlbumVo pmap) {
		//album id 를 통해 album 수록곡 정보들을 조회. 
		return sqlSession.selectList("albumMapper.getSong", pmap);
	}
}
 