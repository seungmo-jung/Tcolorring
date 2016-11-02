package com.wizontech.kct.entity;

public class PageVo {

	private String start;
	private String limit;

	private String currentPage;
	private String rowPerPage;
	private String movePageNum;
	private String pagePerPage;
	private int totalCnt;
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getRowPerPage() {
		return rowPerPage;
	}
	public void setRowPerPage(String rowPerPage) {
		this.rowPerPage = rowPerPage;
	}
	public String getMovePageNum() {
		return movePageNum;
	}
	public void setMovePageNum(String movePageNum) {
		this.movePageNum = movePageNum;
	}
	public String getPagePerPage() {
		return pagePerPage;
	}
	public void setPagePerPage(String pagePerPage) {
		this.pagePerPage = pagePerPage;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	
	
}
