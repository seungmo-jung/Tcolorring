package com.wizontech.kct.entity;

public class PurchaseVo {
	// 구매할때 사용.
	private String so_cust_no;
	private String mdn;
	private String song_id;
	private String vcode;
	private String longplay_yn;
	private String song_part;
	private String sub_code;
	private String price;
	
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
	public String getSong_id() {
		return song_id;
	}
	public void setSong_id(String song_id) {
		this.song_id = song_id;
	}
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
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
	public String getSub_code() {
		return sub_code;
	}
	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
