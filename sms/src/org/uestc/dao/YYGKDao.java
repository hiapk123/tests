package org.uestc.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.uestc.util.JdbcUtils;
import org.uestc.util.PageBean;

import com.uestc.bean.JiaoJieBan_HP;
import com.uestc.bean.SalesSummaryBean;
import com.uestc.bean.XJSZBean;

public interface YYGKDao {

	public QueryRunner qr = new QueryRunner(JdbcUtils.getInstance().getDataSource());
	
	SalesSummaryBean getSalesSummary(Long uId) throws SQLException;

	SalesSummaryBean getSalesSummaryByCombination(String storeName, String beginTime, String endTime, Long uId) throws SQLException;

	PageBean<JiaoJieBan_HP> findShiftingDutyRecordByCombination(String storeName, String beginTime, String endTime,
			Long uId, int pc) throws SQLException;

	PageBean<XJSZBean> findCashDetailsByCombination(String storeName, String beginTime, String endTime, Long uId,
			int pc) throws SQLException;
	
}
