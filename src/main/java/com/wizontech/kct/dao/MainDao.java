package com.wizontech.kct.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wizontech.kct.entity.MainVo;

@Repository
public class MainDao {
	
	@Autowired
	@Resource(name="sqlSessionToktokring")
	private SqlSession sqlSession;
	
	public List<Map> getAlbum(MainVo mainListVo) {
		return sqlSession.selectList("mainMapper.getAlbum", mainListVo);
	}
	
	public List<Map> getSong(MainVo mainListVo) {
		return sqlSession.selectList("mainMapper.getSong", mainListVo);
	}
	
//	public int insert(String sqlid, Map pmap) {
//		return sqlSession.insert(sqlid, pmap);
//	}
//	
//	public int update(String sqlid, Map pmap) {
//		return sqlSession.update(sqlid, pmap);
//	}
//	
//	public int delete(String sqlid, Map pmap) {
//		return sqlSession.delete(sqlid, pmap);
//	}
//	
//	public Object selectOne(String sqlid, Map pmap) {
//		return sqlSession.selectOne(sqlid, pmap);
//	}
//	
//	public List selectList(String sqlid, Map pmap) {
//		return sqlSession.selectList(sqlid, pmap);
//	}

}
