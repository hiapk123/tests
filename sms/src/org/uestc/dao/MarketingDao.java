package org.uestc.dao;

import java.util.ArrayList;

import com.uestc.bean.MarketingFunction;
import com.uestc.bean.MarketingInit;

public interface MarketingDao {
   public ArrayList<MarketingInit> getBeanList(String sql);
   public ArrayList<MarketingFunction> getMarketFunctionBeanList(String sql);
}