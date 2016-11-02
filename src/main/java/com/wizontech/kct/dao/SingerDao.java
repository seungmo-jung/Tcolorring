package com.wizontech.kct.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wizontech.kct.entity.SingerVo;

@Repository
public class SingerDao {
	
	@Autowired
	@Resource(name="sqlSessionToktokring")
	private SqlSession sqlSession;

	public List<Map> getSong(SingerVo singerVo) {
		return sqlSession.selectList("singerMapper.getSong", singerVo);
	}
	
	public List<Map> getAlbum(SingerVo singerVo) {
		return sqlSession.selectList("singerMapper.getAlbum", singerVo);
	}
	
	public int totalCntSong(SingerVo singerVo) {
		return sqlSession.selectOne("singerMapper.totalCntSong", singerVo);
	}
	
	public int totalCntAlbum(SingerVo singerVo) {
		return sqlSession.selectOne("singerMapper.totalCntAlbum", singerVo);
	}
	
}
