package com.wizontech.kct.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizontech.kct.dao.MypageDao;
import com.wizontech.kct.entity.CommonSongInfoVo;
import com.wizontech.kct.entity.PurchaseHistListVo;
import com.wizontech.kct.entity.PurchaseHistVo;
import com.wizontech.kct.util.PageUtil;

@Service
public class MypageService { 

	@Autowired
	private MypageDao mypageDao;
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	//마이 페이지 구매 기록 가져오기.
	@Transactional(value="transactionManagerToktokring")
	public List<PurchaseHistListVo> getPurchase(PurchaseHistVo purchaseHistVo){
		PageUtil.getPageGeneralMap(purchaseHistVo);
		return mypageDao.getPurchase(purchaseHistVo);
	}
	
	//마이 페이지 구매 총 카운트수 가져오기.(페이징 처리를 위해)
	@Transactional(value="transactionManagerToktokring")
	public int totalCntPurchase(PurchaseHistVo purchaseHistVo){
		return mypageDao.totalCntPurchase(purchaseHistVo);
	}
	
	//마이 페이지 기본곡 삭제.
	@Transactional(value="transactionManagerToktokring")
	public String deleteDefaultSong(String mdn){
		String resultCode = "0000";
		
		logger.info("[ "+ mdn +" ] DefaultSong Delete Try");
		
		try{
			mypageDao.deleteDefaultSong(mdn);
			logger.info("[ "+ mdn +" ] DefaultSong Delete Success");
			resultCode = "1000";
		}catch(Exception e){
			resultCode = "0000";
			logger.error("[ "+ mdn +" ] DefaultSong Delete DB Error");
			logger.error("[ "+ mdn +" ] DefaultSong Delete Faild");
			e.printStackTrace();
		}
		
		return resultCode;
	}
	
	// 마이페이지 기본곡 설정.
	@Transactional(value="transactionManagerToktokring")
	public String setDefaultSong(CommonSongInfoVo commonSongInfoVo) {
		int songCheck = mypageDao.checkDefaultSong(commonSongInfoVo.getMdn());
		String resultCode = "0000";
		logger.info("[ "+ commonSongInfoVo.getMdn() +" ] DefaultSong("+ commonSongInfoVo.getSong_id() + " , " + commonSongInfoVo.getLongplay_yn()  + " , " + commonSongInfoVo.getSong_part() +") Set Try");
		//없을 경우 추가.
		if(songCheck == 0){
			try{
				mypageDao.insertDefaultSong(commonSongInfoVo);
				logger.info("[ "+ commonSongInfoVo.getMdn() +" ] DefaultSong Set Insert Success");
				resultCode = "1000";
			}catch(Exception e){
				resultCode = "0000";
				logger.error("[ "+ commonSongInfoVo.getMdn() +" ] DefaultSong Set DB Insert Error");
				logger.error("[ "+ commonSongInfoVo.getMdn() +" ] DefaultSong Set Insert Faild");
				e.printStackTrace();
			}
		}else{
			//있을 경우 업데이트.
			try{
				mypageDao.updateDefaultSong(commonSongInfoVo);
				logger.info("[ "+ commonSongInfoVo.getMdn() +" ] DefaultSong Set Update Success");
				resultCode = "1000";
			}catch(Exception e){		
				resultCode = "0000";
				logger.error("[ "+ commonSongInfoVo.getMdn() +" ] DefaultSong Set DB Update Error");
				logger.error("[ "+ commonSongInfoVo.getMdn() +" ] DefaultSong Set U Faild");
				e.printStackTrace();
			}
		}
		
		return resultCode;
	}
}
