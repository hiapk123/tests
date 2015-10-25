package org.uestc.dao;

import java.util.ArrayList;

import com.uestc.bean.MarketingHgcxBean;
import com.uestc.bean.MarketingTccxBean;

public interface MarketingTccxDao extends MarketingDao{
	 public ArrayList<MarketingTccxBean> getXGBeanList(String sql_getActive);
}
