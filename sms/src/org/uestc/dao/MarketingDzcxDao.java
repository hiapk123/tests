package org.uestc.dao;

import java.util.ArrayList;

import com.uestc.bean.MarketingDzcxBean;

public interface MarketingDzcxDao extends MarketingDao{
	//sql = select * from for_gift where e_name = '' and e_start_date = '';
   public ArrayList<MarketingDzcxBean> getXGBeanList(String sql_getActive);  
}