package org.uestc.dao;

import java.util.ArrayList;

import com.uestc.bean.MarketingMefxBean;

public interface MarketingMefxDao extends MarketingDao{
	 public ArrayList<MarketingMefxBean> getXGBeanList(String sql_getActive);
}
