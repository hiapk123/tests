package org.uestc.dao;

import java.sql.SQLException;
import java.text.ParseException;

import org.apache.commons.dbutils.QueryRunner;
import org.uestc.util.JdbcUtils;
import org.uestc.util.PageBean;

import com.uestc.bean.SPXSBean;
import com.uestc.bean.Sale;

public interface SPXSDao {
	QueryRunner qr = new QueryRunner(JdbcUtils.getInstance().getDataSource());
	
	PageBean<SPXSBean> findAllByUid(Long uId, int pc) throws SQLException;
	
	PageBean<SPXSBean> findByCombination(String storeName, String beginTime, String endTime, String condition, Long uId, int pc) throws SQLException, ParseException;
}
