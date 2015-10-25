package org.uestc.dao;

import java.util.ArrayList;

import com.uestc.bean.MarketingHgcxBean;

public interface MarketingHgcxDao extends MarketingDao{
	 public ArrayList<MarketingHgcxBean> getXGBeanList(String sql_getActive);
}
