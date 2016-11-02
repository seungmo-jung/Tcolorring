package com.wizontech.kct.entity;

import java.util.List;
import java.util.Map;

public class PurchaseHistVo extends PageVo{
	// 구매 고객의 so_cust_no 와 mdn 그리고 목록.
	private String so_cust_no;
	private String mdn;
	private List<PurchaseHistListVo> list;
	
	public String getSo_cust_no() {
		return so_cust_no;
	}
	public void setSo_cust_no(String so_cust_no) {
		this.so_cust_no = so_cust_no;
	}
	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	public List<PurchaseHistListVo> getList() {
		return list;
	}
	public void setList(List<PurchaseHistListVo> list) {
		this.list = list;
	}
}
