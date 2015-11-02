package org.uestc.util;

public class VariablePage extends SimpleVariable {
	protected String split = " ";
	protected String style = "class='page'";

	public VariablePage(int pageNo, int totalSum, int pageSize, String url) {
		pageNo = pageNo < 1 ? 1 : pageNo;
		super.pageNo = pageNo;
		this.totalSum = totalSum;
		this.url = url;
		this.pageSize = pageSize;
	}

	public VariablePage(int pageNum, int totalSum, String url) {
		pageNum = pageNum < 1 ? 1 : pageNum;
		super.pageNo = pageNum;
		this.totalSum = totalSum;
		this.url = url;
	}

	public String getSplit() {
		return split;
	}

	public void setSplit(String split) {
		this.split = split;
	}

}