package org.uestc.service;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface AnalyseGoodsService {
	
	public List<Object[]> findGoodsByPage(int currentPage,int pageSize,int store);
	//根据条件查询:店铺，分类，条形码，日期,ording排序的依据
	public List<Object[]> findGoodsByCondition(int currentPage,int pageSize,int store,int category,String num,String startDate,String endDate,int ordering);
	
	public List<Object[]> findStoreByUserId(int uid);
	
	public List<Object[]> findByCategoryId(int pid,int store);
	
	public int getCount(int store,int category,String num,String startDate,String endDate);
}
