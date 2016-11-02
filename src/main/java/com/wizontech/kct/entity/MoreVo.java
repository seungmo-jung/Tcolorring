package com.wizontech.kct.entity;

import java.util.List;
import java.util.Map;

public class MoreVo extends PageVo{

	private int type;
	private String genre;
	private List<Map> list;
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public List<Map> getList() {
		return list;
	}
	public void setList(List<Map> list) {
		this.list = list;
	}
}
