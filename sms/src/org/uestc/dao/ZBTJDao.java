package org.uestc.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.uestc.util.JdbcUtils;
import org.uestc.util.PageBean;

import com.uestc.bean.Sale;

public interface ZBTJDao {

	public QueryRunner qr = new QueryRunner(JdbcUtils.getInstance().getDataSource());

	PageBean<Sale> findAllSalesByUid(Long uId, int pc) throws SQLException;
}
