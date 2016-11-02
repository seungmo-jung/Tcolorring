package com.wizontech.kct.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wizontech.kct.entity.CommonSongInfoVo;
import com.wizontech.kct.entity.PurchaseHistListVo;
import com.wizontech.kct.entity.PurchaseHistVo;

@Repository
public class MypageDao {
	
	@Autowired
	@Resource(name="sqlSessionToktokring")
	private SqlSession sqlSession;
	
	public List<PurchaseHistListVo> getPurchase(PurchaseHistVo purchaseHistVo){
		return sqlSession.selectList("mypageMapper.getPurchase", purchaseHistVo);
	}
	
	public int totalCntPurchase(PurchaseHistVo purchaseHistVo){
		return sqlSession.selectOne("mypageMapper.totalCntPurchase", purchaseHistVo);
	}

	public void deleteDefaultSong(String mdn){
		sqlSession.update("mypageMapper.deleteDefaultSong", mdn);
	}
	public void insertDefaultSong(CommonSongInfoVo commonSongInfoVo){
		sqlSession.insert("mypageMapper.insertDefaultSong", commonSongInfoVo);
	}
	
	public void updateDefaultSong(CommonSongInfoVo commonSongInfoVo) {
		sqlSession.update("mypageMapper.updateDefaultSong", commonSongInfoVo);
	}
	
	public int checkDefaultSong(String mdn){
		return sqlSession.selectOne("mypageMapper.checkDefaultSong", mdn);
	}
}
