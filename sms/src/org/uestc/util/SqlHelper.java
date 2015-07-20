package org.uestc.util;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

public final class SqlHelper {

	public static List<Object[]> find(String sql,Object ...params) {
		List<Object[]> list=null;
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getInstance().getDataSource());
		    list = runner.query(sql, new ArrayListHandler(),params);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
}