package org.uestc.service;

import java.util.List;

import javax.servlet.http.HttpSession;

public interface AnalyseGoodsService {
	
	public List<Object[]> findGoodsByPage(int currentPage,int pageSize,int store);
	
	public List<Object[]> findStoreByUserId(int uid);
	
	public List<Object[]> findByCategoryId(int pid);
}
