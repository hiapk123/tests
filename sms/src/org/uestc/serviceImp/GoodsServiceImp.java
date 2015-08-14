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
	public List<Object[]> goodssearch(int sid, int currentPage) {
		String sql = "SELECT g_name,s_name,c_id,g_id,c_name,s_id from goods where s_id=? and g_del=1 limit ?,10";
		List<Object[]> list = SqlHelper.find(sql, sid, currentPage);
		return list;
	}

	@Override
	public void addgood(int s_id, String s_name, String g_name, int g_flag, String g_stock_num, String g_sale_price,
			String g_pur_price, String c_name, String g_barcode) {
		// TODO Auto-generated method stub
		String sql = "insert into goods(s_id,s_name,g_name,g_flag,g_stock_num,g_sale_price,g_pur_price, c_name,g_barcode,g_del) value(?,?,?,?,?,?,?,?,?,1)";
		String g_flag0 = String.valueOf(g_flag);
		SqlHelper.executeUpdate(sql, new String[] { s_id + "", s_name, g_name, g_flag0, g_stock_num, g_sale_price,
				g_pur_price, c_name, g_barcode });

	}

	@Override
	public void editgood(int s_id, String s_name, String g_name, String g_stock_num, String g_sale_price,
			String g_pur_price, String c_name, String g_barcode, int g_id) {
		// TODO Auto-generated method stub
		String sql = "update goods set s_id=?,s_name=?, g_name=?,g_stock_num=?,g_sale_price=?, g_pur_price=?,c_name=?,c_name=?  where g_id=?";

		SqlHelper.executeUpdate(sql, new String[] { s_id + "", s_name, g_name, g_stock_num, g_sale_price, g_pur_price,
				c_name, g_barcode, g_id + "" });
	}

	@Override
	public void deletegood(int g_id) {
		// TODO Auto-generated method stub
		String sql = "delete from goods where g_id=?";
		SqlHelper.executeUpdate(sql, new String[] { g_id + "" });
	}

	@Override
	public int getTotalSize(int store) {
		String sql = "select count(g_id) from goods where g_del=1 and g_flag=1 and s_id=?";
		List<Object[]> list = SqlHelper.find(sql, store);
		if (null != list && list.size() == 1) {
			return Integer.valueOf(list.get(0)[0]+"");
		}
		return 0;
	}

}
