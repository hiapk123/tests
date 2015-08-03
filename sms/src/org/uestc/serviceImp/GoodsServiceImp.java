package org.uestc.serviceImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.uestc.service.GoodsService;


import org.uestc.util.JdbcUtils;
import org.uestc.util.SqlHelper;

import com.uestc.bean.Category;
import com.uestc.bean.CommissionRule;
import com.uestc.bean.IntegralRule;
import com.uestc.bean.ShiftChange;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;
import com.uestc.bean.Vip;

//import sun.security.krb5.internal.CredentialsUtil;

public class GoodsServiceImp implements GoodsService {

	private QueryRunner queryRunner;

	public GoodsServiceImp() {
		queryRunner = new QueryRunner(JdbcUtils.getInstance().getDataSource());
	}

	@Override
	public List<Object[]> findStoreByUserID(int uid) {
		String sql = "select s_id,s_name from store where u_id=?";
		List<Object[]> list = SqlHelper.find(sql, new Object[] { uid });
		return list;
	}

	@Override
	public List<Object[]> goodssearch(int sid,int currentPage) {
		String sql="SELECT g_name,s_name,c_id,g_id,c_name from goods where s_id=? and g_del=1 limit ?,10";
		List<Object[]> list=SqlHelper.find(sql, sid,currentPage);
		return list;
	}

	
	
	
}
