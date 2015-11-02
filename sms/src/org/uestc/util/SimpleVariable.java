package org.uestc.util;

public class SimpleVariable {
	/**
	 * 显示页码
	 */
	protected int showPageNum = 10;
	/**
	 * 连接URL
	 */
	protected String url = null;
	/**
	 * 当前页码
	 */
	protected int pageNo = 1;
	/**
	 * 总页码
	 */
	protected int totalPage = 1;
	/**
	 * 总条数
	 */
	protected int totalSum = 0;
	/**
	 * 每页显示条数
	 */
	protected int pageSize = 10;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(int totalSum) {
		this.totalSum = totalSum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getShowPageNum() {
		return showPageNum;
	}

	public void setShowPageNum(int showPageNum) {
		this.showPageNum = showPageNum;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
}