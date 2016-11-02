package com.wizontech.kct.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizontech.kct.dao.PopupDao;
import com.wizontech.kct.entity.PurchaseVo;
import com.wizontech.kct.entity.VcodeVo;

@Service
public class PopupService {

	@Autowired
	private PopupDao popupDao;
	private final Logger logger = Logger.getLogger(this.getClass());

	// 미리듣기 및 구매 팝업창을 위해 Vcode List를 가져옴.
	@Transactional(value="transactionManagerToktokring")
	public List<VcodeVo> setVcodeList(String song_id){
		return popupDao.getVcodeList(song_id);
	}
	
	//구매 진행.
	@Transactional(value="transactionManagerToktokring")
	public String setPurchase(PurchaseVo purchaseData) {
		int histCheck = popupDao.checkPurchaseHist(purchaseData);
		String resultCode = "3000";
		logger.info("[ "+ purchaseData.getMdn() + " , " + purchaseData.getSo_cust_no() + " ] Song("+ purchaseData.getSong_id() +", "+ purchaseData.getLongplay_yn() +", "+ purchaseData.getSong_part() +") Purchase Try");
		
		if(histCheck == 0){
			//logger.info("곡을 추가합니다.");
			try{
				popupDao.insertPurchaseHist(purchaseData);
				popupDao.insertUserPurchase(purchaseData);
				popupDao.updateSongBuyCnt(purchaseData);
				logger.info("[ "+ purchaseData.getMdn() + " , " + purchaseData.getSo_cust_no() + " ] Song Purchase Success");
				resultCode = "1000";
			} catch(Exception e) {
				resultCode = "0000";
				logger.error("[ "+ purchaseData.getMdn() + " , " + purchaseData.getSo_cust_no() + " ] Song Purchase DB Error");
				logger.error("[ "+ purchaseData.getMdn() + " , " + purchaseData.getSo_cust_no() + " ] Song Purchase Faild");
				e.printStackTrace();
			}

		} else{
			resultCode = "2000";
			logger.info("[ "+ purchaseData.getMdn() + " , " + purchaseData.getSo_cust_no() + " ] Song("+ purchaseData.getSong_id() +", "+ purchaseData.getLongplay_yn() +", "+ purchaseData.getSong_part() +") Exist");
			//logger.info("이미 있는 곡입니다.");
		}
		return resultCode;
	}
}
