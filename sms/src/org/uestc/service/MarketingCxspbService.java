package org.uestc.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MarketingCxspbService  {
	public  String  profenye(HttpServletRequest req,HttpServletResponse res);
	public String getPageInit(HttpServletRequest req,HttpServletResponse res);
	public String operateProgress(HttpServletRequest req ,HttpServletResponse rep) throws IOException;
	public String progressTCType(String tctype,HttpServletRequest req);
	
}
