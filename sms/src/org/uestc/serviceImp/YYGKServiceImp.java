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

	@Override
	public SalesSummaryBean getAllSalesSummary() {
		try {
			return yygkDao.getAllSalesSummary();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public SalesSummaryBean getAllSalesSummaryByCombination(String storeName, String beginTime, String endTime,
			String condition) {
		try {
			return yygkDao.getAllSalesSummaryByCombination(storeName, beginTime, endTime);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<JiaoJieBan_HP> findAllShiftingDutyRecordByCombination(String storeName, String beginTime,
			String endTime, int pc) {
		try {
			return yygkDao.findAllShiftingDutyRecordByCombination(storeName, beginTime, endTime, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public PageBean<XJSZBean> findAllCashDetailsByCombination(String storeName, String beginTime, String endTime,
			int pc) {
		try {
			return yygkDao.findAllCashDetailsByCombination(storeName, beginTime, endTime, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

