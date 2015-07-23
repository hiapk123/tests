package org.uestc.util;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.uestc.bean.Users;

public final class SqlHelper {

	public static List<Object[]> find(String sql,Object ...params) {
		List<Object[]> list=null;
		QueryRunner runner=null;
		try {
			runner= new QueryRunner(JdbcUtils.getInstance().getDataSource());
		    list = runner.query(sql, new ArrayListHandler(),params);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			runner=null;
		}
		return list;
	}
	
	

	
	
	public static <T> List<T> findBean(Class<T> clazz,String sql,Object ...params) {	
		
		List<T> list=null;
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getInstance().getDataSource());
		    list = runner.query(sql, new BeanListHandler<T>(clazz),params);
		    runner=null;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args) {
		//test
		
	}
}