package com.wizontech.kct.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wizontech.kct.entity.PurchaseVo;
import com.wizontech.kct.entity.VcodeVo;

@Repository
public class PopupDao {
	
	@Autowired
	@Resource(name="sqlSessionToktokring")
	private SqlSession sqlSession;
	
	public List<VcodeVo> getVcodeList(String song_id){
		return sqlSession.selectList("popupMapper.getVcodeList", song_id);
	}
	
	public void insertPurchaseHist(PurchaseVo purhcaseData){
		sqlSession.insert("popupMapper.setPurchaseHistory", purhcaseData);
	}
	
	public void insertUserPurchase(PurchaseVo purchaseData){
		sqlSession.insert("popupMapper.setUserPurchase", purchaseData);
	}
	
	public int checkPurchaseHist(PurchaseVo purchaseData){
		return sqlSession.selectOne("popupMapper.checkPurchase", purchaseData);
	}
	
	public void updateSongBuyCnt(PurchaseVo purchaseData){
		sqlSession.update("popupMapper.updateSongCnt", purchaseData);
	}
}
