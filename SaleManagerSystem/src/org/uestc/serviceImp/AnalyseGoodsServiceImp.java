package org.uestc.serviceImp;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.uestc.dao.AnalyseGoodsDao;
import org.uestc.daoImp.AnalyseGoodsDaoImp;
import org.uestc.service.AnalyseGoodsService;

public class AnalyseGoodsServiceImp implements AnalyseGoodsService {
	private AnalyseGoodsDao goodDao = new AnalyseGoodsDaoImp();

	@Override
	public List<Object[]> findGoodsByPage(int currentPage, int pageSize,
			int store) {
		// 商品信息分析
		String sql = "select t1.*,t2.*,t4.*,t6.* from goods t1 INNER JOIN sale t2 INNER JOIN (select t3.* from restock t3 where t3.r_flag=0)t4 INNER JOIN (SELECT t5.* FROM restock t5 WHERE t5.r_flag=1)t6 WHERE t1.g_id=t2.g_id and t1.g_id=t4.g_id and t1.g_id=t6.g_id where t1.g_del=1 and t1.g_flag=1 and t1.s_id=? limit ?,?";
		Object[] params = new Object[] { store, currentPage, pageSize };
		return goodDao.find(sql, params);// 调用dao;
	}

	@Override
	public List<Object[]> findStoreByUserId(int uid) {

		String sql = "SELECT t1.s_id,t1.s_name FROM store t1 WHERE t1.s_del=1 and t1.s_flag=1 and t1.u_id=?";
		return goodDao.find(sql, uid);
	}

	@Override
	public List<Object[]> findByCategoryId(int pid,int store) {
		String sql = "select * from category where c_parent_id=? and s_id=?";
		return goodDao.find(sql, pid,store);
	}

	@Override
	public List<Object[]> findGoodsByCondition(int currentPage, int pageSize,
			int store, int category, String num, String startDate,
			String endDate, int ordering) {
		// 注日期为毫秒数
		boolean isNull = true;
		String sql = "select t1.*,t2.*,t1.g_sale_price-t1.g_pur_price as chajia from goods t1,sale t2 where t1.g_id=t2.g_id and t1.s_id=? and t1.c_id=? and t1.g_del=1 and t1.g_flag=1 and t2.sa_date between ? and ?";
		if (!"".equals(num) && null != num) {
			sql += " and t1.g_barcode=?";
			isNull = false;
		}
		switch (ordering) {
		// 1-按照商品名降序
		case 1:
			sql += " order by t1.g_name desc";
			break;
		// 2-按照商品名升序
		case 2:
			sql += " order by t1.g_name asc";
			break;
		case 3:
			// 3-按照条形码降序
			sql += " order by t1.g_barcode desc";
			break;
		case 4:
			sql += " order by t1.g_barcode asc";
			break;
		// 5-按照库存量降序
		case 5:
			sql += " order by t1.g_stock_num desc";
			break;
		case 6:
			sql += " order by t1.g_stock_num asc";
			break;
		// 售价降序
		case 7:
			sql += " order by t1.g_sale_price desc";
			break;
		case 8:
			sql += " order by t1.g_sale_price asc";
			break;
		// 成本价
		case 9:
			sql += " order by t1.g_pur_price desc";
			break;
		case 10:
			sql += " order by t1.g_pur_price asc";
			break;
		// 差价
		case 11:
			sql += " order by chajia desc";
			break;
		case 12:
			sql += " order by chajia asc";
			break;
		default:
			break;
		}
		sql += " limit ?,?";
		if (isNull)
			return goodDao.find(sql, store,category, startDate, endDate, currentPage,
					pageSize);
		else
			return goodDao.find(sql, store,category, startDate, endDate, num,
					currentPage, pageSize);
	}

	@Override
	public int getCount(int store, int category, String num, String startDate,
			String endDate) {
		boolean isNull = true;
		String sql = "select t1.g_id from goods t1,sale t2 where t1.g_id=t2.g_id and t1.s_id=? and t1.c_id=? and t1.g_del=1 and t1.g_flag=1 and t2.sa_date between ? and ?";
		if (!"".equals(num) && null != num) {
			sql += " and t1.g_barcode=?";
			isNull = false;
		}
		
		try {
			if (isNull)
				return goodDao.find(sql, store,category, startDate, endDate).size();
			else
				return goodDao.find(sql, store,category, startDate, endDate, num).size();
		} catch (Exception e) {
			
			e.printStackTrace();
			return 0;
		}
	}
}
