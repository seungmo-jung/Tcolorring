package com.wizontech.kct.entity;

import java.util.List;
import java.util.Map;

public class SearchVo extends PageVo{
	//검색용 vo
	private int type;
	private String query;
	private int sortType;
	private List<Map> list;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public int getSortType() {
		return sortType;
	}
	public void setSortType(int sortType) {
		this.sortType = sortType;
	}
	public List<Map> getList() {
		return list;
	}
	public void setList(List<Map> list) {
		this.list = list;
	}
}
