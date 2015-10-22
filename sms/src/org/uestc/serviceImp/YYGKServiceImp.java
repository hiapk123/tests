package org.uestc.serviceImp;

import java.sql.SQLException;

import org.uestc.service.YYGKService;
import org.uestc.util.PageBean;

import com.uestc.bean.JiaoJieBan_HP;
import com.uestc.bean.SalesSummaryBean;
import com.uestc.bean.XJSZBean;

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

	@Override
	public PageBean<JiaoJieBan_HP> findShiftingDutyRecordByCombination(String storeName, String beginTime,
			String endTime, Long uId, int pc) {
		try {
			return yygkDao.findShiftingDutyRecordByCombination(storeName, beginTime, endTime, uId, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<XJSZBean> findCashDetailsByCombination(String storeName, String beginTime, String endTime, Long uId,
			int pc) {
		try {
			return yygkDao.findCashDetailsByCombination(storeName, beginTime, endTime, uId, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
