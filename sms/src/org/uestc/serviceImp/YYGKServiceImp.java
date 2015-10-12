package org.uestc.serviceImp;

import java.sql.SQLException;

import org.uestc.service.YYGKService;

import com.uestc.bean.SalesSummaryBean;

public class YYGKServiceImp implements YYGKService {

	@Override
	public SalesSummaryBean getSalesSummary(Long uId) {
		try {
			return yygkDao.getSalesSummary(uId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public SalesSummaryBean getSalesSummaryByCombination(String storeName, String beginTime, String endTime, Long uId) {
		try {
			return yygkDao.getSalesSummaryByCombination(storeName, beginTime, endTime, uId);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
