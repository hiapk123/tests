package org.uestc.dao;

import java.sql.SQLException;
import java.text.ParseException;

import org.apache.commons.dbutils.QueryRunner;
import org.uestc.util.JdbcUtils;
import org.uestc.util.PageBean;

import com.uestc.bean.XSDJBean;

public interface XSDJDao {
	QueryRunner qr = new QueryRunner(JdbcUtils.getInstance().getDataSource());
	
	PageBean<XSDJBean> findAllByUid(Long uId, int pc) throws SQLException;
	
	PageBean<XSDJBean> findByCombination(String storeName,String receiptType, String beginTime, String endTime, String seriNum, Long uId, int pc) throws SQLException, ParseException;

	PageBean<XSDJBean> findAll(int pc) throws SQLException;

	PageBean<XSDJBean> findAllByCombination(String storeName, String receiptType, String beginTime, String endTime,
			String seriNum, int pc) throws SQLException, ParseException;
}
