package com.wizontech.kct.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wizontech.kct.entity.SearchVo;

@Repository
public class SearchDao {
	
	@Autowired
	@Resource(name="sqlSessionToktokring")
	private SqlSession sqlSession;

	public List<Map> getSong(SearchVo pmap) {
		return sqlSession.selectList("searchMapper.getSong", pmap);
	}
	
	public List<Map> getSinger(SearchVo pmap) {
		return sqlSession.selectList("searchMapper.getSinger", pmap);
	}
	
	public List<Map> getAlbum(SearchVo pmap) {
		return sqlSession.selectList("searchMapper.getAlbum", pmap);
	}
	
	public int totalCntSong(SearchVo pmap) {
		return sqlSession.selectOne("searchMapper.totalCntSong", pmap);
	}
	
	public int totalCntSinger(SearchVo pmap) {
		return sqlSession.selectOne("searchMapper.totalCntSinger", pmap);
	}
	
	public int totalCntAlbum(SearchVo pmap) {
		return sqlSession.selectOne("searchMapper.totalCntAlbum", pmap);
	}
}
