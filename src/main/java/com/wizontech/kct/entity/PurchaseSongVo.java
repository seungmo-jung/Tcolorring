package com.wizontech.kct.entity;

public class PurchaseSongVo {
	
	private String song_id;
	private String song_title;
	private String singer_name;
	private String vcode;
	private String song_part;
	private String longplay_yn;
	private String ph_longplay_yn;
	private String ph_song_part;
	
	public String getSong_id() {
		return song_id;
	}
	public void setSong_id(String song_id) {
		this.song_id = song_id;
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
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	public String getSong_part() {
		return song_part;
	}
	public void setSong_part(String song_part) {
		this.song_part = song_part;
	}
	public String getLongplay_yn() {
		return longplay_yn;
	}
	public void setLongplay_yn(String longplay_yn) {
		this.longplay_yn = longplay_yn;
	}
	public String getPh_longplay_yn() {
		return ph_longplay_yn;
	}
	public void setPh_longplay_yn(String ph_longplay_yn) {
		this.ph_longplay_yn = ph_longplay_yn;
	}
	public String getPh_song_part() {
		return ph_song_part;
	}
	public void setPh_song_part(String ph_song_part) {
		this.ph_song_part = ph_song_part;
	}
}
