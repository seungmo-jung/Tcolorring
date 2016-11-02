package com.wizontech.kct.entity;

public class CommonSongInfoVo {
	private String mdn;
	private String caller_mdn;
	private String original_caller_mdn;
	private int s_time;
	private int e_time;
	private int original_s_time;
	private int original_e_time;
	private String song_id;
	private String longplay_yn;
	private String song_part;
	private String vcode;
	
	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	public String getCaller_mdn() {
		return caller_mdn;
	}
	public void setCaller_mdn(String caller_mdn) {
		this.caller_mdn = caller_mdn;
	}
	public String getOriginal_caller_mdn() {
		return original_caller_mdn;
	}
	public void setOriginal_caller_mdn(String original_caller_mdn) {
		this.original_caller_mdn = original_caller_mdn;
	}
	public int getS_time() {
		return s_time;
	}
	public void setS_time(int s_time) {
		this.s_time = s_time;
	}
	public int getE_time() {
		return e_time;
	}
	public void setE_time(int e_time) {
		this.e_time = e_time;
	}
	public int getOriginal_s_time() {
		return original_s_time;
	}
	public void setOriginal_s_time(int original_s_time) {
		this.original_s_time = original_s_time;
	}
	public int getOriginal_e_time() {
		return original_e_time;
	}
	public void setOriginal_e_time(int original_e_time) {
		this.original_e_time = original_e_time;
	}
	public String getSong_id() {
		return song_id;
	}
	public void setSong_id(String song_id) {
		this.song_id = song_id;
	}
	public String getLongplay_yn() {
		return longplay_yn;
	}
	public void setLongplay_yn(String longplay_yn) {
		this.longplay_yn = longplay_yn;
	}
	public String getSong_part() {
		return song_part;
	}
	public void setSong_part(String song_part) {
		this.song_part = song_part;
	}
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
}
