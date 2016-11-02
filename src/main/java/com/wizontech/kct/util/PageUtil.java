package com.wizontech.kct.util;

import com.wizontech.kct.entity.PageVo;


public class PageUtil {
	
	private String currentPage;
	private String movePageNum;
	private String rowPerPage;
	private String pagePerPage;
	
	/**
	 * @return the currentPage
	 */
	public String getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the movePageNum
	 */
	public String getMovePageNum() {
		return movePageNum;
	}

	/**
	 * @param movePageNum the movePageNum to set
	 */
	public void setMovePageNum(String movePageNum) {
		this.movePageNum = movePageNum;
	}

	/**
	 * @return the rowPerPage
	 */
	public String getRowPerPage() {
		return rowPerPage;
	}

	/**
	 * @param rowPerPage the rowPerPage to set
	 */
	public void setRowPerPage(String rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	/**
	 * @return the pagePerPage
	 */
	public String getPagePerPage() {
		return pagePerPage;
	}

	/**
	 * @param pagePerPage the pagePerPage to set
	 */
	public void setPagePerPage(String pagePerPage) {
		this.pagePerPage = pagePerPage;
	}

	public static void getPageGeneralMap(PageVo pageVo){
		String currentPage = pageVo.getCurrentPage();
		String movePageNum = pageVo.getMovePageNum();
		String rowPerPage = pageVo.getRowPerPage();
		String start = "";

		int maxPageRowNum = 0;

		if( rowPerPage == null || rowPerPage.trim().length() ==  0 ){
			rowPerPage = "15";
		}
		if( currentPage == null || currentPage.trim().length() ==  0 ){
			currentPage = "1";
		}
		if (Integer.parseInt(currentPage) > 1) {
			start = String.valueOf(( ( Integer.parseInt(currentPage) - 1 )*Integer.parseInt(rowPerPage) ));
		} else {
			start = "0";
		}

        pageVo.setRowPerPage(rowPerPage);
        pageVo.setCurrentPage(currentPage);
        pageVo.setStart(start);
        pageVo.setPagePerPage(String.valueOf(maxPageRowNum));
	}
}