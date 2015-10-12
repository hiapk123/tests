package org.uestc.service;

import org.uestc.dao.YYGKDao;
import org.uestc.daoImp.YYGKDaoImp;

import com.uestc.bean.SalesSummaryBean;

public interface YYGKService {

	public YYGKDao yygkDao = new YYGKDaoImp();
	
	SalesSummaryBean getSalesSummary(Long uId);

	SalesSummaryBean getSalesSummaryByCombination(String storeName, String beginTime, String endTime, Long uId);
}
