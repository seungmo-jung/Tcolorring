package com.wizontech.kct.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wizontech.kct.entity.CallerListVo;
import com.wizontech.kct.entity.CommonSongInfoVo;
import com.wizontech.kct.entity.PopupVo;
import com.wizontech.kct.entity.PurchaseSongVo;
import com.wizontech.kct.entity.TimeCheckVo;
import com.wizontech.kct.entity.TimeListVo;

@Repository
public class MypagePopupDao {
	
	@Autowired
	@Resource(name="sqlSessionToktokring")
	private SqlSession sqlSession;
	
	public List<PurchaseSongVo> getSongList(String so_cust_no) {
		return sqlSession.selectList("mypagePopupMapper.getSongList", so_cust_no);
	}
	
	public PopupVo getListenSongInf(String song_id){
		return sqlSession.selectOne("mypagePopupMapper.getListenSongInf", song_id);
	}
	
	
	public List<TimeListVo> getTimeList(String mdn){
		return sqlSession.selectList("mypagePopupMapper.getTimeList", mdn);
	}
	
	//getCheckTimeList
	public List<TimeCheckVo> getCheckTimeList(String mdn){
		return sqlSession.selectList("mypagePopupMapper.getTimeCheckList", mdn);
	}

	public List<CallerListVo> getCallerList(String mdn){
		return sqlSession.selectList("mypagePopupMapper.getCallerList", mdn);
	}
	
	public Map getSong(int songId) {
		return sqlSession.selectOne("mypagePopupMapper.getSong", songId);
	}
	
	public void insertCaller(CommonSongInfoVo commonSongInfoVo){
		sqlSession.insert("mypagePopupMapper.insertCaller", commonSongInfoVo);
	}
	
	public void insertTime(CommonSongInfoVo commonSongInfoVo){
		sqlSession.insert("mypagePopupMapper.insertTime", commonSongInfoVo);
	}
	
	public void updateCaller(CommonSongInfoVo commonSongInfoVo){
		sqlSession.insert("mypagePopupMapper.updateCaller", commonSongInfoVo);
	}
	
	public void updateTime(CommonSongInfoVo commonSongInfoVo){
		sqlSession.update("mypagePopupMapper.updateTime", commonSongInfoVo);
	}
	
	
	public void deleteTime(CommonSongInfoVo commonSongInfoVo){
		sqlSession.delete("mypagePopupMapper.deleteTime", commonSongInfoVo);
	}
	public void deleteCaller(CommonSongInfoVo commonSongInfoVo){
		sqlSession.delete("mypagePopupMapper.deleteCaller", commonSongInfoVo);
	}
}
