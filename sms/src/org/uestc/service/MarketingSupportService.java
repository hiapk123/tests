package org.uestc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MarketingSupportService {
	public String getCoupons();
	public String searchCoupons(String keywords);
	public String saveCouponsToActive(HttpServletRequest req,HttpServletResponse res);
}
