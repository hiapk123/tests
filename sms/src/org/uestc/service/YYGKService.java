package org.uestc.service;

import org.uestc.dao.YYGKDao;
import org.uestc.daoImp.YYGKDaoImp;
import org.uestc.util.PageBean;

import com.uestc.bean.JiaoJieBan_HP;
import com.uestc.bean.SalesSummaryBean;
import com.uestc.bean.XJSZBean;

public interface YYGKService {

	public YYGKDao yygkDao = new YYGKDaoImp();
	
	SalesSummaryBean getSalesSummary(Long uId);

	SalesSummaryBean getSalesSummaryByCombination(String storeName, String beginTime, String endTime, Long uId);

	PageBean<JiaoJieBan_HP> findShiftingDutyRecordByCombination(String storeName, String beginTime, String endTime,
			Long uId, int pc);

	PageBean<XJSZBean> findCashDetailsByCombination(String storeName, String beginTime, String endTime, Long uId,
			int pc);
}
