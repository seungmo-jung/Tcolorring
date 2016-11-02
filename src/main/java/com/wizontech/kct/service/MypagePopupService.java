package com.wizontech.kct.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wizontech.kct.dao.MypagePopupDao;
import com.wizontech.kct.entity.CallerListVo;
import com.wizontech.kct.entity.CommonSongInfoVo;
import com.wizontech.kct.entity.PopupVo;
import com.wizontech.kct.entity.PurchaseSongVo;
import com.wizontech.kct.entity.TimeCheckVo;
import com.wizontech.kct.entity.TimeListVo;

@Service
public class MypagePopupService {
	
	@Autowired
	private MypagePopupDao mypagePopupDao;
	
	private Logger logger = LoggerFactory.getLogger(MypagePopupService.class);
	
	//마이 페이지 팝업창 구매 곡 목록 가져오기.
	@Transactional(value="transactionManagerToktokring")
	public List<PurchaseSongVo> getSong(String so_cust_no) {
		return mypagePopupDao.getSongList(so_cust_no);			
	}
	
	//마이 페이지 팝업 듣기 곡 정보 가져오기.
	public PopupVo getListenSongInf(String song_id){
		return mypagePopupDao.getListenSongInf(song_id);
	}

	//마이 페이지 발신자별 목록 불러오기.
	@Transactional(value="transactionManagerToktokring")
	public List<CallerListVo> getCallerList(String mdn){
		return mypagePopupDao.getCallerList(mdn);
	}
	
	//마이 페이지 발신자별 추가.
	@Transactional(value="transactionManagerToktokring")
	public String insertCaller(CommonSongInfoVo commonSongInfoVo){
		String resultCode = "0000";
		
		logger.info("[ "+ commonSongInfoVo.getMdn() +" , "+ commonSongInfoVo.getCaller_mdn() +" ] CallerSong ("+ commonSongInfoVo.getSong_id() + " , " + commonSongInfoVo.getLongplay_yn()  + " , " + commonSongInfoVo.getSong_part() +") Set Try");
		
		try{
			mypagePopupDao.insertCaller(commonSongInfoVo);
			resultCode = "1000";
			logger.info("[ "+ commonSongInfoVo.getMdn() +" , "+ commonSongInfoVo.getCaller_mdn() +" ] CallerSong Set Insert Success");
		}catch(Exception e){		
			resultCode = "0000";
			logger.error("[ "+ commonSongInfoVo.getMdn() +" , "+ commonSongInfoVo.getCaller_mdn() +" ] CallerSong Set DB Insert Error");
			logger.error("[ "+ commonSongInfoVo.getMdn() +" , "+ commonSongInfoVo.getCaller_mdn() +" ] CallerSong Set Insert Faild");
			e.printStackTrace();
		}
		return resultCode;
	}
	
//	@Transactional(value="transactionManagerToktokring")
//	public String updateCaller(CommonSongInfoVo commonSongInfoVo){
//		String resultCode = "0000";
//		try{
//			mypagePopupDao.updateCaller(commonSongInfoVo);
//			resultCode = "1000";
//		}catch(Exception e){		
//			resultCode = "0000";
//			logger.error("Error");
//			e.printStackTrace();
//		}
//		return resultCode;
//	}
	
	//마이 페이지 발신자별 삭제.
	@Transactional(value="transactionManagerToktokring")
	public String deleteCaller(CommonSongInfoVo commonSongInfoVo){
		String resultCode = "0000";
		logger.info("[ "+ commonSongInfoVo.getMdn() +" , "+ commonSongInfoVo.getCaller_mdn() +" ] CallerSong Delete Try");
		try{
			mypagePopupDao.deleteCaller(commonSongInfoVo);
			resultCode = "1000";
			logger.info("[ "+ commonSongInfoVo.getMdn() +" , "+ commonSongInfoVo.getCaller_mdn() +" ] CallerSong Delete Success");
		}catch(Exception e){
			resultCode = "0000";
			logger.error("[ "+ commonSongInfoVo.getMdn() +" ] CallerSong DB Delete Error");
			logger.error("[ "+ commonSongInfoVo.getMdn() +" ] CallerSong Delete Faild");
			logger.error("Error");
			e.printStackTrace();
		}
		return resultCode;
	}
	
	// 마이 페이지 설정된 시간 목록 불러오기.
	@Transactional(value="transactionManagerToktokring")
	public List<TimeListVo> getTimeList(String mdn){
		return mypagePopupDao.getTimeList(mdn);
	}
	
	//마이 페이지 시간 설정.
	@Transactional(value="transactionManagerToktokring")
	public String insertTime(CommonSongInfoVo commonSongInfoVo){
		String resultCode = "0000";
		boolean checkCode = true;
		
		logger.info("[ "+ commonSongInfoVo.getMdn() +" , Set START time : "+ commonSongInfoVo.getS_time() +" , Set END time : " + commonSongInfoVo.getE_time() +" ] TimeSong ("+ commonSongInfoVo.getSong_id() + " , " + commonSongInfoVo.getLongplay_yn()  + " , " + commonSongInfoVo.getSong_part() +") Set Try");
		
		List<TimeCheckVo> ckTimeList = mypagePopupDao.getCheckTimeList(commonSongInfoVo.getMdn());
		
		for(int i=0; i<ckTimeList.size(); i++){ // 저장되어있는 시간 체크 진행.
			int s_time = ckTimeList.get(i).getS_time();
			int e_time = ckTimeList.get(i).getE_time();
			
			logger.info("Stored START time : " + s_time + ", Stored END time" + e_time);
			logger.info("ㄴs_time : " + s_time + ", e_time : " + e_time);
			
//			if(s_time > e_time){
//				logger.info("Stored START time is more than stored END time");
//			}
			
			if(s_time != commonSongInfoVo.getS_time()){ // 시작 시간 중복 체크. (시작 시간이 중복될 수 없음.)
				logger.info("[Time Song Set] Stored START time and Set START time is not overlap : Pass (stored s_time != set s_time)");
				//저장된 시작 시간과 끝 시간을 비교하여 하루가 넘어가는지 , 넘어가지 않는지로 나누어 처리를한다.
				if(s_time > e_time){
					// 저장된 시작 시간 , 저장된 끝 시간 크기 체크.
					//저장된 시작시간이 저장된 끝시간보다 클 경우 하루가 넘어가는 경우가 생김.
					logger.info("[Time Song Set] Stored START time is bigger than Stored END time : Pass (stored s_time > stored e_time)");
					 if(commonSongInfoVo.getS_time() < s_time  && commonSongInfoVo.getS_time() >= e_time){
						 logger.info("[Time Song Set] Set START Time is OK : Pass [e_time, set start time, s_time]( " + e_time + " <= " + commonSongInfoVo.getS_time() + " < " + s_time +" )");
						 // 하루가 넘어가는 경우 저장될 시작 시간은 저장된 시작 시간보다 작고 , 저장될 끝시간보다는 크거나 같아야한다.
						 // 예를 들어 저장된 시작 시간이 7시부터 5시라고 한다면 설정 가능 시간 대는 5시부터 7시이다. 
						 // 저장된 끝시간인 5시로 설정 할 수 있지만 , 저장된 시작 시간으로는 설정 하지 못하므로 7시는 설정할 수 없다.
						 // 5(저장된 끝 시간) <= 저장될 시작 시간 < 7(저장된 시작 시간)
						 if(commonSongInfoVo.getE_time() <= s_time && commonSongInfoVo.getE_time() > e_time){
							 logger.info("[Time Song Set] Set END Time is OK : Pass ( " + e_time + " < " + commonSongInfoVo.getE_time() + " <= " + s_time +" )");
							 // 이 조건을 통과할 경우 다음으로는 끝시간을 검사를 진행한다.
							 // 끝시간의 경우도 마찬가지이지만 시작 시간과는 반대로 끝시간을 저장된 시작 시간 7시로 설정할 수 있지만 , 저장된 끝시간인 5시로는 설정할 수 없다.
							 // 5(저장된 끝 시간) < 저장될 끝 시간 <= 7 (저장된 시작 시간)
							 if(commonSongInfoVo.getS_time() < commonSongInfoVo.getE_time()){
								 logger.info("[Time Song Set] Set START Time is smaller than Set END time : Pass ( " + commonSongInfoVo.getS_time() + " < " + commonSongInfoVo.getE_time() + " )");
								 // 하지만 위의 방법대로 진행하면 설정을 시작을 6시로 , 끝을 5시로 진행하면 캐치를 하지 못하여 하루가 넘어가는 중복이 발생한다.
								 // 이 경우 만약 저장된 시작 시간이 저장된 끝시간보다 클 경우 또 다른 하루가 넘어가는 설정은 하지 못하므로 무조건 저장될 시작 시간은 저장될 끝시간보다 작아야한다.
								 checkCode = true;
							 }else{
								 logger.info("[Time Song Set] Set START Time is smaller than Set END time is wrong : Faild ( " + commonSongInfoVo.getS_time() + " < " + commonSongInfoVo.getE_time() + " )");
								 //저장될 시작 시간이 , 저장될 끝 시간보다 클 수 있지만 이미 하루가 넘어가는 시간대 설정이 되어 있으므로 또 진행하게 된다면 중복되어 실패처리.
								checkCode = false;
								break; 
							 }
						 }else{ 
							 //저장될 끝 시간이 저장된 시작 시간보다 클 경우 중복이 발생하고 , 혹 저장된 끝시간보다 작을 경우에도 중복이 발생하므로 실패처리
							 // ex ) 5(저장된 끝 시간) < 저장될 끝 시간 <= 7 (저장된 시작 시간) 으로 처리가 되어야 하는데 , 저장될 시간이 8시나 9시로 끝 시간이 설정되면 중복 처리가되고 .. 
							 // 혹은 하루가 넘어가게 설정이 되어있는데 3시나 4시로 잡혀 있게 되면 또 중복이 되므로 실패처리를 한다.
							 logger.info("[Time Song Set] Set END Time is wrong : Faild ( " + e_time + " < " + commonSongInfoVo.getE_time() + " <= " + s_time +" )");
							 
							 checkCode = false;
							 break; 
						 }
					 }else{
						 // 저장될 시작 시간이 저장될 끝시간과 , 저장된 시작 시간 사이에 없으므로 하루가 넘어가는 설정이 이미 잡혀있으므로 중복이되어 실패 처리를 한다.
						 logger.info("[Time Song Set] Set START Time is wrong : Faild ( " + e_time + " <= " + commonSongInfoVo.getS_time() + " < " + s_time +" )");
						checkCode = false;
						break; 
					 }
				} else if(s_time < e_time){ // 저장 시작 시간 < 저장 끝 시간.
					// 이제 만약 저장된 끝 시간이 저장된 시작 시간보다 클 경우이다.
					// 이 경우 시작 시간은 , 저장된 시작 시간보다 큰지 적은지로 나뉘어지게 된다.
					logger.info("[Time Song Set] Stored START time is smaller than Stored END time : Pass (stored s_time < stored e_time)");
					if(s_time < commonSongInfoVo.getS_time()){ // 저장 시작 시간 < 저장될 시작 시간.
						logger.info("[Time Song Set] Stored START Time is smaller than Set START time : Pass (stored s_time < set s_time)");
						if(commonSongInfoVo.getS_time() >= e_time){
							logger.info("[Time Song Set] Set START Time is OK : Pass [set Start Time, e_time]( " + commonSongInfoVo.getS_time() +  ">= "+ e_time + " )");
							// 저장된 시작 시간보다 저장될 시작 시간이 더 클 경우 , 저장될 시작 시간은 저장 된 끝난 시간보다 같거나 그보다 더 커야한다. (저장된 시작 시간보다 이미 크다는걸 앎)
							// 예를 들어 저장된 시작 시간이 6시 , 끝 시간이 10시라고 했을 경우 ..  저장될 시작 시간은 10시 보다 크거나 같아야한다. 
							if(commonSongInfoVo.getE_time() > e_time || commonSongInfoVo.getE_time() <= s_time){
								logger.info("[Time Song Set] Set END Time is OK : Pass [Set End Time, e_time, Set End Time, s_time ( "+ commonSongInfoVo.getE_time() + " > " + e_time + " or " + commonSongInfoVo.getE_time() + "<= " + s_time + " )");
								// 저장될 시작 시간이 10시보다 클 경우지만 , 저장될 끝 시간은 10시보다 더 클 수도 있고 , 저장된 시작 시간인 6시보다 작을 수도 있다. (작을 경우는 하루가 넘어가는 경우) 
								if(commonSongInfoVo.getS_time() < commonSongInfoVo.getE_time()){
									 logger.info("[Time Song Set] Set START Time is smaller than Set END time : Pass [Set Start time, Set END time] ( " + commonSongInfoVo.getS_time() + " < " + commonSongInfoVo.getE_time() + " )");
									//위의 조건들을 충족 할 경우 저장될 시작 시간이 저장될 끝 시간 보다 작다면 모두 성공 케이스이다.
									checkCode = true;
								} else{
									 logger.info("[Time Song Set] Set START Time is smaller than Set END time : Pass  [Set Start time, Set END time] ( " + commonSongInfoVo.getS_time() + " < " + commonSongInfoVo.getE_time() + " )");
									if(commonSongInfoVo.getE_time() <= s_time){
										logger.info("[Time Song Set] Set END Time Check OK : Pass [Set START Time, s_time] ( " + commonSongInfoVo.getE_time() + "<= " + s_time + " )");
										//만약 저장될 시작 시간이 저장될 끝시간보다 큰 경우가 생긴다면 , 이는 하루가 넘어가는 경우이다.
										// 저장될 끝시간이 저장된 시작 시간보다 작다면 하루가 넘어간 경우의 케이스로 중복되지 않아 성공하게 된다.
										checkCode = true;
									} else{
										logger.info("[Time Song Set] Set END Time is wrong : Faild [Set START Time, s_time] ( " + commonSongInfoVo.getE_time() + " <= " + s_time + " )");
										// 만약 저장된 시작 시간보다 크다면 이는 실패 처리이다.
										checkCode = false;
										break;
									}
								}
							} else {
								logger.info("[Time Song Set] Set END Time is wrong : Faild");
								// 저장될 끝시간이 저장된 끝시간보다 작거나 , 저장된 시작 시간보다 크거나 같다면 중복되는 경우이므로 실패처리이다.
								checkCode = false;
								break;
							}
						}else{
							logger.info("[Time Song Set] Set START Time is wrong : Faild [set Start Time, e_time]( " + commonSongInfoVo.getS_time() +  " >=  "+ e_time + " )");
							// 저장될 시작 시간이 저장된 끝시간보다 작다면 중복되는 경우이므로 실패 처리이다.
							checkCode = false;
							break;
						}
					}else if(s_time > commonSongInfoVo.getS_time()){//  저장된 시작 시간 > 저장될 시작 시간.
						//저장된 시작 시간이 , 저장될 시작 시간보다 클 경우.
						logger.info("[Time Song Set] Stored START Time is bigger than Set START time : Pass (stored s_time > set s_time)");
						if(s_time >= commonSongInfoVo.getE_time()){ // 시작 시간 >= 저장될 끝시간
							// 이 경우에는 저장될 끝시간 또한 저장된 시작 시간보다 무조건 작아야한다. (아닐 경우 중복 발생)
							if(commonSongInfoVo.getS_time() < commonSongInfoVo.getE_time()){
								// 시작 시간과 끝시간 모두 저장된 시작 시간보다 작아야 하므로 , 당연히 저장될 시작 시간은 저장될 끝시간 보다 작아야 중복이 발생하지 않는다.
								logger.info("[Time Song Set] Set START Time is smaller than Set END time : Pass ( " + commonSongInfoVo.getS_time() + " < " + commonSongInfoVo.getE_time() + " )");
								checkCode = true;
							} else {
								// 저장될 시작 시간이 저장될 끝시간보다 클 경우 시간대 중복이 발생하여 실패처리.
								logger.info("[Time Song Set] Set START Time is smaller than Set END time is wrong : Faild ( " + commonSongInfoVo.getS_time() + " < " + commonSongInfoVo.getE_time() + " )");
								checkCode = false;
								break;
							}
						}
					} else {
						// 저장될 시작시간이 저장된 시작 시간과 같을 경우. (실패처리)
						logger.info("[Time Song Set] Stored START Time is Same Set START TIME : Faild");
						checkCode = false;
						break;
					}
				} else{ // 저장된 시작 시간과 끝 시간이 서로 크지도 않고 작지도 않을 경우 (같을 경우).
					logger.info("[Time Song Set] Set Start Time is Same Set End Time : Faild");
					checkCode = false;
					break;
				}
			} else {
				// 저장될 시작 시간이 저장된 시작 시간과 같으므로 실패처리.
				logger.info("[Time Song Set] Set Start Time is Same Stored Start Time : Faild");
				checkCode = false;
				break;
			}
		}
		
		if(checkCode == true){
			try{
				mypagePopupDao.insertTime(commonSongInfoVo);
				logger.info("[Time Song Set] Success");
				resultCode = "1000";
			}catch (Exception e) {
				logger.info("[Time Song Set] DB error");
				resultCode = "0000";
				e.printStackTrace();
			}
		}else {
			logger.info("[Time Song Set] Faild");
			resultCode = "0000";
		}
		return resultCode;
	}
	
	//마이 페이지 시간대별 설정 삭제.
	@Transactional(value="transactionManagerToktokring")
	public String deleteTime(CommonSongInfoVo commonSongInfoVo){
		String resultCode = "0000";
		try{
			mypagePopupDao.deleteTime(commonSongInfoVo);
			resultCode = "1000";
		}catch(Exception e){
			resultCode = "0000";
			logger.error("Error");
			e.printStackTrace();
		}
		return resultCode;
	}
	
//	@Transactional(value="transactionManagerToktokring")
//	public String updateTime(CommonSongInfoVo commonSongInfoVo){
//		String resultCode = "0000";
//		boolean checkCode = true;
//		
//		List<TimeCheckVo> ckTimeList = mypagePopupDao.getCheckTimeList(commonSongInfoVo.getMdn());
//		
//		for(int i=0; i<ckTimeList.size(); i++){ // 저장되어있는 시간 체크 진행.
//			int s_time = ckTimeList.get(i).getS_time();
//			int e_time = ckTimeList.get(i).getE_time();
//			
//			if(s_time != commonSongInfoVo.getOriginal_s_time() || e_time != commonSongInfoVo.getOriginal_e_time()){
//				if(s_time != commonSongInfoVo.getS_time()){ // 시작 시간 중복 체크.
//					logger.info("s_time != set s_time");
//					if(s_time > e_time){ // 저장되어 있는 시작 시간 , 끝 시간 크기 체크.
//						logger.info("s_time > e_time");
//						if(s_time < commonSongInfoVo.getS_time()){ // 저장 시작 시간 < 저장될 시작 시간.
//							checkCode = false;
//							break;
//						}else if(s_time > commonSongInfoVo.getS_time() && e_time <= commonSongInfoVo.getS_time()){ // 저장 시작 시간 > 저장될 시작 시간.
//							if(s_time < commonSongInfoVo.getE_time()){ // 저장 시작 시간 < 저장될 끝 시간.
//								checkCode = false;
//								break;
//							}else if(s_time >= commonSongInfoVo.getE_time()){ 
//								if(commonSongInfoVo.getS_time() > commonSongInfoVo.getE_time()){ // 저장될 시작 시간 , 끝 시간 체크.
//									checkCode = false;
//									break;
//								} else if (commonSongInfoVo.getS_time() < commonSongInfoVo.getE_time()){  // 저장될 시작 시간 , 끝 시간 체크.
//									checkCode = true;
//								}
//							}
//						}else {
//							checkCode = false;
//							break;
//						}
//					} else if (s_time < e_time){ // 저장 시작 시간 < 저장 끝 시간.
//						logger.info("s_time < e_time");
//						if(s_time < commonSongInfoVo.getS_time()){ // 저장 시작 시간 < 저장될 시작 시간.
//							if(e_time > commonSongInfoVo.getS_time()){ // 저장된 끝 시간 > 저장될 시작 시간.
//								checkCode = false;
//								break;
//							}else if(e_time <= commonSongInfoVo.getS_time()){ // 저장된 끝 시간 <= 저장될 시작 시간.
//								if(commonSongInfoVo.getE_time() > commonSongInfoVo.getS_time()){
//									checkCode = true;
//								}else{
//									checkCode = false;
//									break;
//								}
//							}
//						}else if(s_time > commonSongInfoVo.getS_time()){//  저장된 시작 시간 > 저장될 시작 시간.
//							if(s_time >= commonSongInfoVo.getE_time()){ // 시작 시간 >= 저장될 끝시간
//								if(commonSongInfoVo.getS_time() > commonSongInfoVo.getE_time()){ // 저장될 시작 시간 , 끝 시간 체크.
//									logger.info("set s_time > set e_time");
//									checkCode = false;
//									break;
//								}else if(commonSongInfoVo.getS_time() < commonSongInfoVo.getE_time()){ // 저장될 시작 시간 , 끝 시간 체크.
//									checkCode = true;
//								}
//							}else if(s_time < commonSongInfoVo.getE_time()){ // 시작 시간 < 저장될 끝 시간
//								checkCode = false;
//								break;
//							}
//						}
//					}
//				} else { // 시작시간이 중복 될경우.
//					checkCode = false;
//					break;
//				}
//			}
//		}
//		
//		if(checkCode == true){
//			mypagePopupDao.updateTime(commonSongInfoVo);
//			
//			resultCode = "1000";
//		}else {
//			
//			resultCode = "0000";
//		}
//		return resultCode;
//	}
	

}
