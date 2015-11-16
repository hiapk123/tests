package org.uestc.dao;

import java.util.List;

import com.uestc.bean.MarketingDiscBean;

public interface MarketingDiscDao {
	public String insertActive(String type,String name,String start_time);
	public List<MarketingDiscBean> getMarketingDiscBeanBySQL(String sql);
	public String deleteActive(String type,String name,String start_tim);
	
}
