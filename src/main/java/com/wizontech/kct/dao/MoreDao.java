package com.wizontech.kct.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wizontech.kct.entity.MoreVo;

@Repository
public class MoreDao {
	
	@Autowired
	@Resource(name="sqlSessionToktokring")
	private SqlSession sqlSession;
	
	public List<Map> getAlbum(MoreVo moreListVo) {
		return sqlSession.selectList("moreMapper.getAlbum", moreListVo);
	}
	
	public List<Map> getSong(MoreVo moreListVo) {
		return sqlSession.selectList("moreMapper.getSong", moreListVo);
	}
	
	public int totalCntAlbum(MoreVo moreListVo) {
		return sqlSession.selectOne("moreMapper.totalCntAlbum", moreListVo);
	}
	
	public int totalCntSong(MoreVo moreListVo) {
		return sqlSession.selectOne("moreMapper.totalCntSong", moreListVo);
	}
	
}
