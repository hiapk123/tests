package org.uestc.daoImp;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.uestc.dao.ZBTJDao;
import org.uestc.util.PageBean;
import org.uestc.util.PageConstants;

import com.uestc.bean.Sale;
import com.uestc.bean.Store;
import com.uestc.bean.Users;

public class ZBTJDaoImp implements ZBTJDao {

	@Override
	public PageBean<Sale> findByCombination(String storeName, String beginTime, String endTime, String condition,
			Long uId, int pc) throws SQLException, ParseException {
		
		if (!beginTime.equals("")) {
			beginTime = StrToDate(beginTime);
		}
		if (!endTime.equals("")) {
			endTime = StrToDate(endTime);
		}
		
		
		int ps = PageConstants.SALE_PAGE_SIZE;

		String sql = "";
		Number number = null;
		int tr = 0;
		
		if (storeName.equals("全部门店")) {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 1 1 1
					sql = "select count(DISTINCT(sa_serial_num)) from sale where store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), uId);
					tr = number.intValue();
				} else {
					// 1 1 0
					sql = "select count(DISTINCT(sa_serial_num)) from sale where sa_date<=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), endTime, uId);
					tr = number.intValue();
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "select count(DISTINCT(sa_serial_num)) from sale where sa_date>=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, uId);
					tr = number.intValue();
				} else {
					// 1 0 0
					sql = "select count(DISTINCT(sa_serial_num)) from sale where sa_date>=? and sa_date<=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, endTime, uId);
					tr = number.intValue();
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "select count(DISTINCT(sa_serial_num)) from sale where store_id=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), uId);
					tr = number.intValue();
				} else {
					// 0 1 0
					sql = "select count(DISTINCT(sa_serial_num)) from sale where store_id=? and sa_date<=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), endTime, uId);
					tr = number.intValue();
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "select count(DISTINCT(sa_serial_num)) from sale where store_id=? and sa_date>=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime, uId);
					tr = number.intValue();
				} else {
					// 0 0 0
					sql = "select count(DISTINCT(sa_serial_num)) from sale where store_id=? and sa_date>=? and sa_date<=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime, endTime, uId);
					tr = number.intValue();
				}
			}
		}
		
		List<Object[]> list = null;
		
		if (storeName.equals("全部门店")) {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 1 1 1
					sql = "SELECT store_id,count(sa_serial_num),sum(sa_real_price),sum(sa_profit) FROM sale WHERE store_id in(select s_id from store where u_id=?) GROUP BY sa_serial_num limit ?,?;";
					list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
				} else {
					// 1 1 0
					sql = "SELECT store_id,count(sa_serial_num),sum(sa_real_price),sum(sa_profit) FROM sale WHERE sa_date<=? and store_id in(select s_id from store where u_id=?) GROUP BY sa_serial_num limit ?,?;";
					list = qr.query(sql, new ArrayListHandler(), endTime, uId, (pc - 1) * ps, ps);
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "SELECT store_id,count(sa_serial_num),sum(sa_real_price),sum(sa_profit) FROM sale WHERE sa_date>=? and store_id in(select s_id from store where u_id=?) GROUP BY sa_serial_num limit ?,?;";
					list = qr.query(sql, new ArrayListHandler(), beginTime, uId, (pc - 1) * ps, ps);
				} else {
					// 1 0 0
					sql = "SELECT store_id,count(sa_serial_num),sum(sa_real_price),sum(sa_profit) FROM sale WHERE sa_date>=? and sa_date<=? and store_id in(select s_id from store where u_id=?) GROUP BY sa_serial_num limit ?,?;";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId, (pc - 1) * ps, ps);
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "SELECT store_id,count(sa_serial_num),sum(sa_real_price),sum(sa_profit) FROM sale WHERE store_id=? and store_id in(select s_id from store where u_id=?) GROUP BY sa_serial_num limit ?,?;";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), uId, (pc - 1) * ps, ps);
				} else {
					// 0 1 0
					sql = "SELECT store_id,count(sa_serial_num),sum(sa_real_price),sum(sa_profit) FROM sale WHERE store_id=? and sa_date<=? and store_id in(select s_id from store where u_id=?) GROUP BY sa_serial_num limit ?,?;";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), endTime, uId, (pc - 1) * ps, ps);
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "SELECT store_id,count(sa_serial_num),sum(sa_real_price),sum(sa_profit) FROM sale WHERE store_id=? and sa_date>=? and store_id in(select s_id from store where u_id=?) GROUP BY sa_serial_num limit ?,?;";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime, uId, (pc - 1) * ps, ps);
				} else {
					// 0 0 0
					sql = "SELECT store_id,count(sa_serial_num),sum(sa_real_price),sum(sa_profit) FROM sale WHERE store_id=? and sa_date>=? and sa_date<=? and store_id in(select s_id from store where u_id=?) GROUP BY sa_serial_num limit ?,?;";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime, endTime, uId, (pc - 1) * ps, ps);
				}
			}
		}
		
//		String sql = "select count(DISTINCT(sa_serial_num)) from sale where store_id in(select s_id from store where u_id=?)";
//		Number number = qr.query(sql, new ScalarHandler(), uId);
//		int tr = number.intValue();
//
//		sql = "SELECT store_id,count(sa_serial_num),sum(sa_real_price),sum(sa_profit) FROM sale WHERE store_id in(select s_id from store where u_id=?) GROUP BY sa_serial_num limit ?,?;";
//		List<Object[]> list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
		
		
		
		
		
		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			sale.setStore(findStoreByStoreId(Long.valueOf(obj[0].toString())));
			sale.setSaGoodsNum(obj[1].toString()); // 将其看作“销售单数”
			sale.setSaRealPrice(obj[2].toString());
			sale.setSaProfit(obj[3].toString());
			saleList.add(sale);
		}

		PageBean<Sale> pb = new PageBean<Sale>();
		pb.setBeanList(saleList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	@Override
	public PageBean<Sale> findAllSalesByUid(Long uId, int pc) throws SQLException {
		int ps = PageConstants.SALE_PAGE_SIZE;

		String sql = "select count(DISTINCT(sa_serial_num)) from sale where store_id in(select s_id from store where u_id=?)";
		// String sql = "select count(*) from sale where store_id in(select s_id
		// from store where u_id=?) GROUP BY sa_serial_num";
		Number number = qr.query(sql, new ScalarHandler(), uId);
		int tr = number.intValue();

		sql = "SELECT store_id,count(sa_serial_num),sum(sa_real_price),sum(sa_profit) FROM sale WHERE store_id in(select s_id from store where u_id=?) GROUP BY sa_serial_num limit ?,?;";
		// sql = "SELECT store_id,count(sa_serial_num),sa_real_price,sa_profit
		// FROM sale WHERE store_id in(select s_id from store where u_id=?)
		// limit ?,?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			sale.setStore(findStoreByStoreId(Long.valueOf(obj[0].toString())));
			sale.setSaGoodsNum(obj[1].toString()); // 将其看作“销售单数”
			sale.setSaRealPrice(obj[2].toString());
			sale.setSaProfit(obj[3].toString());
			saleList.add(sale);
		}

		PageBean<Sale> pb = new PageBean<Sale>();
		pb.setBeanList(saleList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	private Store findStoreByStoreId(Long id) throws SQLException {
		String sql = "select s_name from store where s_id=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), id);
		if (list.size() > 0) {
			Store store = new Store();
			Object[] obj = list.get(0);
			store.setSName(obj[0].toString());
			return store;
		}
		return null;
	}
	private Long findStoreIdByStoreName(String storeName) throws SQLException {
		String sql = "select s_id from store where s_name=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), storeName);
		if (list.size() > 0) {
			Store store = new Store();
			Object[] obj = list.get(0);
			return Long.valueOf(obj[0].toString());
		}
		return null;
	}
	
	private String dateToString(String in) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date date = format.parse(in);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return Long.toString(cal.getTimeInMillis());
	}
	
	private String StrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "" + date.getTime();
	}

}
