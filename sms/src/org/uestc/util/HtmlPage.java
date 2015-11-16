package org.uestc.util;

public class HtmlPage extends Page {
	public HtmlPage(VariablePage variablePage) {
		super(variablePage);
	}

	public String pagination() {
		String printNo = "";
		// 如果已分页；并且页数小于等于要显示的页数
		if (variablePage.totalPage > 1 && variablePage.totalPage <= variablePage.showPageNum) {
			return displayAll();
			// 如果分页数：大于显示的页码数
		} else if (variablePage.totalPage > 1 && variablePage.totalPage > variablePage.showPageNum) {
			if (variablePage.pageNo == 1) {// 当前页等于第一页
				return fromFirstPagePrint();
			} else if (variablePage.pageNo == variablePage.totalPage) {// 当前页等于最后一页
				return fromLastPagePrint();
			} else {// 如果当前页：即不是首页也不是尾页
				if (variablePage.showPageNum % 2 == 0) {// 可以平分页码
					int print$No = variablePage.showPageNum / 2;
					if (variablePage.pageNo >= print$No) {
						int index$No = variablePage.pageNo - print$No;
						if (variablePage.pageNo + print$No >= variablePage.totalPage) {
							return fromLastPagePrint();
						} else {
							if (index$No == 0)
								index$No = 1;
							for (int i = index$No; i < (variablePage.showPageNum + index$No); i++) {
								if (i == variablePage.pageNo) {// 如果是当前页:不添加连接URL
									printNo +="<li><a>"+ (i + variablePage.split)+"</a></li>";
								} else {
									printNo += (buildA(variablePage, i) + variablePage.split);
								}
							}
						}
					} else {
						return fromFirstPagePrint();
					}
				} else {// 打印页数不是偶数时：
					int print$No = variablePage.showPageNum / 2 + 1;
					if (variablePage.pageNo >= print$No && variablePage.pageNo + print$No < variablePage.totalPage) {
						int index$No = variablePage.pageNo - print$No + 1;
						for (int i = index$No; i < variablePage.showPageNum + index$No; i++) {
							if (i == variablePage.pageNo) {// 如果是当前页:不添加连接URL
								printNo +="<li><a>"+ (i + variablePage.split)+"</a></li>";
							} else {
								printNo += (buildA(variablePage, i) + variablePage.split);
							}
						}
					} else if (variablePage.pageNo <= print$No) {// 从第一页开始
						return fromFirstPagePrint();
					} else {
						return fromLastPagePrint();
					}
				}
			}
			return (printNo);
		} else {
			return "1";
		}
	}

	public String getBackpageNum() {
		if (variablePage.pageNo <= 1) {
			return buildSpan("上一页", variablePage);
		} else {
			return buildA("上一页", (variablePage.url + (variablePage.pageNo - 1)));
		}
	}

	public String getNextpageNum() {
		if (variablePage.pageNo >= variablePage.totalPage) {
			return buildSpan("下一页", variablePage);
		} else {
			return buildA("下一页", variablePage.url + (variablePage.pageNo + 1));
		}
	}

	public String buildSpan(String text, VariablePage variablePage) {
		return "<span style=\"color:gray;\">" + text + "</span>";
	}

	public String buildA(String text, String url) {
		return "<li><a href=\"" + url + "\">" + text + "</a></li>";
	}

	public String buildA(VariablePage variablePage, int num) {
		return ("<li><a href=\"" + variablePage.url + num + "\">" + num + "</a></li>");
	}
}