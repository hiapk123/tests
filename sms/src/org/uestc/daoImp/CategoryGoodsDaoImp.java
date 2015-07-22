package org.uestc.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.uestc.dao.CategoryGoodsDao;
import org.uestc.util.JdbcUtils;

public class CategoryGoodsDaoImp implements CategoryGoodsDao {

	@Override
	public List<Object[]> find(String sql, Object... params) {
		// TODO Auto-generated method stub
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
