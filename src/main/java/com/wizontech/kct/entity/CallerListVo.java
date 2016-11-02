package com.wizontech.kct.entity;

public class CallerListVo {
	private String mdn;
	private String caller_mdn;
	private String song_title;
	private String singer_name;
	private String longplay_yn;
	private String song_part;
	
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
	public String getSong_title() {
		return song_title;
	}
	public void setSong_title(String song_title) {
		this.song_title = song_title;
	}
	public String getSinger_name() {
		return singer_name;
	}
	public void setSinger_name(String singer_name) {
		this.singer_name = singer_name;
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
}
