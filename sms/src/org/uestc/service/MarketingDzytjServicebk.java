package org.uestc.service;

import javax.servlet.http.HttpServletRequest;

public interface MarketingDzytjServicebk {
	String getPageInit();
	String getAddPage(String type,String d_name, String d_start_date);
	String getAddHead();
	String getXiangxiHead(String id);
	String getGengxinHead(String id);
	String getGoodsListStr(String type,String d_name,String d_start_date);
	String progressTCType(String tctype,HttpServletRequest req);
	
}
