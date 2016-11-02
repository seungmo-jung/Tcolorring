package com.wizontech.kct.entity;

public class PlayerVo {
	private String song_id;
	private String mdn;
	private String song_title;
	private String singer_name;
	private String typeCode = "02"; // 01 벨소리 , 02 컬러링
	private String song_part;
	private String window = "Y";
	
	public String getSong_id() {
		return song_id;
	}
	public void setSong_id(String song_id) {
		this.song_id = song_id;
	}
	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
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
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getSong_part() {
		return song_part;
	}
	public void setSong_part(String song_part) {
		this.song_part = song_part;
	}
	public String getWindow() {
		return window;
	}
	public void setWindow(String window) {
		this.window = window;
	}
}
