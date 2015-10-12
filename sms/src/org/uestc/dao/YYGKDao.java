package org.uestc.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.uestc.util.JdbcUtils;

import com.uestc.bean.SalesSummaryBean;

public interface YYGKDao {

	public QueryRunner qr = new QueryRunner(JdbcUtils.getInstance().getDataSource());
	
	SalesSummaryBean getSalesSummary(Long uId) throws SQLException;

	SalesSummaryBean getSalesSummaryByCombination(String storeName, String beginTime, String endTime, Long uId) throws SQLException;
	
}
