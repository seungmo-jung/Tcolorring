package com.wizontech.kct.entity;

import java.util.List;
import java.util.Map;

public class SingerVo extends PageVo{
	//가수 상세 페이지에서 사용.
	private int type;
	private int singerId;
	private List<Map> list;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSingerId() {
		return singerId;
	}

	public void setSingerId(int singerId) {
		this.singerId = singerId;
	}

	public List<Map> getList() {
		return list;
	}

	public void setList(List<Map> list) {
		this.list = list;
	}
	
}
