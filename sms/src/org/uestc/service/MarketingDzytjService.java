package org.uestc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MarketingDzytjService {
	public String getPageInit(HttpServletRequest req,HttpServletResponse res);
	public String operateProgress(HttpServletRequest req ,HttpServletResponse rep);
	public String progressTCType(String tctype,HttpServletRequest req);
	public String fysearch(HttpServletRequest req);
}
