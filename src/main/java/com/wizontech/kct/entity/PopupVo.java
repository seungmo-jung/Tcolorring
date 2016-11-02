package com.wizontech.kct.entity;

import java.util.List;

public class PopupVo {
	private String song_id;
	private String song_title;
	private String singer_name;
	private List<VcodeVo> vcode_list;
	
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
	public List<VcodeVo> getVcode_list() {
		return vcode_list;
	}
	public void setVcode_list(List<VcodeVo> vcode_list) {
		this.vcode_list = vcode_list;
	}
}
