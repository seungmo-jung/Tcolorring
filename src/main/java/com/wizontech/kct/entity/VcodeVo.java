package com.wizontech.kct.entity;

public class VcodeVo {
	private String song_title;
	private String singer_name;
	private String longplay_yn;
	private String song_part;
	private String vcode;
	private String song_price;
	private String sub_code; 
	
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
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	public String getSong_price() {
		return song_price;
	}
	public String getSub_code() {
		return sub_code;
	}
	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}
	public void setSong_price(String song_price) {
		this.song_price = song_price;
	}
}
