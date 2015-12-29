package org.uestc.daoImp;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.uestc.dao.QSFXDao;
import org.uestc.util.PageBean;
import org.uestc.util.PageConstants;

import com.uestc.bean.Sale;
import com.uestc.bean.Store;

public class QSFXDaoImp implements QSFXDao {

	@Override
	public PageBean<Sale> findAllSalesByUid(Long uId, int pc) throws SQLException {
		int ps = PageConstants.SALE_PAGE_SIZE;

		String sql = "SELECT COUNT(DISTINCT(HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id in(select s_id from store where u_id=?)";
		Number number = qr.query(sql, new ScalarHandler(), uId);
		int tr = number.intValue();

		sql = "SELECT HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS hourdate ,count(sa_serial_num),sum(sa_real_price),sum(sa_profit) FROM sale WHERE store_id in(select s_id from store where u_id=?) GROUP BY hourdate limit ?,?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			if (obj[0] != null) {
				sale.setSaDate(obj[0].toString());
			}
			if (obj[1] != null) {
				sale.setSaGoodsNum(obj[1].toString()); // 将其看作“销售单数”
			}
			if (obj[2] != null) {
				sale.setSaRealPrice(obj[2].toString());
			}
			if (obj[3] != null) {
				sale.setSaProfit(obj[3].toString());
			}
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
	public PageBean<Sale> findByCombination(String storeName, String beginTime, String endTime, String condition,
			Long uId, int pc) throws SQLException, ParseException {
		if (condition.equals("按小时")) {
			return findByHour(storeName, beginTime, endTime, uId, pc);
		}
		if (condition.equals("按天")) {
			return findByDay(storeName, beginTime, endTime, uId, pc);
		}
		if (condition.equals("按月")) {
			return findByMonth(storeName, beginTime, endTime, uId, pc);
		}
		return null;
	}
	
	/*
	 * 按小时
	 */
	private PageBean<Sale> findByHour(String storeName, String beginTime, String endTime, Long uId, int pc)
			throws SQLException, ParseException {

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
					sql = "SELECT COUNT(DISTINCT(HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), uId);
					tr = number.intValue();
				} else {
					// 1 1 0
					sql = "SELECT COUNT(DISTINCT(HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), endTime, uId);
					tr = number.intValue();
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "SELECT COUNT(DISTINCT(HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE sa_date>=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, uId);
					tr = number.intValue();
				} else {
					// 1 0 0
					sql = "SELECT COUNT(DISTINCT(HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE sa_date>=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, endTime, uId);
					tr = number.intValue();
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "SELECT COUNT(DISTINCT(HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), uId);
					tr = number.intValue();
				} else {
					// 0 1 0
					sql = "SELECT COUNT(DISTINCT(HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), endTime, uId);
					tr = number.intValue();
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "SELECT COUNT(DISTINCT(HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id=? AND sa_date>=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime, uId);
					tr = number.intValue();
				} else {
					// 0 0 0
					sql = "SELECT COUNT(DISTINCT(HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id=? AND sa_date>=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime, endTime,
							uId);
					tr = number.intValue();
				}
			}
		}

		List<Object[]> list = null;

		if (storeName.equals("全部门店")) {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 1 1 1
					sql = "SELECT HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS hourdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY hourdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
				} else {
					// 1 1 0
					sql = "SELECT HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS hourdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY hourdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), endTime, uId, (pc - 1) * ps, ps);
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "SELECT HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS hourdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE sa_date>=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY hourdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), beginTime, uId, (pc - 1) * ps, ps);
				} else {
					// 1 0 0
					sql = "SELECT HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS hourdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE sa_date>=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY hourdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId, (pc - 1) * ps, ps);
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "SELECT HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS hourdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY hourdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), uId, (pc - 1) * ps,
							ps);
				} else {
					// 0 1 0
					sql = "SELECT HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS hourdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY hourdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), endTime, uId,
							(pc - 1) * ps, ps);
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "SELECT HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS hourdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id=? AND sa_date>=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY hourdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime, uId,
							(pc - 1) * ps, ps);
				} else {
					// 0 0 0
					sql = "SELECT HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS hourdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id=? AND sa_date>=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY hourdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime, endTime,
							uId, (pc - 1) * ps, ps);
				}
			}
		}

		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			if (obj[0] != null) {
				sale.setSaDate(obj[0].toString());
			}
			if (obj[1] != null) {
				sale.setSaGoodsNum(obj[1].toString()); // 将其看作“销售单数”
			}
			if (obj[2] != null) {
				sale.setSaRealPrice(obj[2].toString());
			}
			if (obj[3] != null) {
				sale.setSaProfit(obj[3].toString());
			}
			saleList.add(sale);
		}

		PageBean<Sale> pb = new PageBean<Sale>();
		pb.setBeanList(saleList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;

	}
	private PageBean<Sale> findAllByHour(String storeName, String beginTime, String endTime, int pc)
			throws SQLException, ParseException {
		
		if (!beginTime.equals("")) {
			beginTime = StrToDate(beginTime);
		}
		if (!endTime.equals("")) {
			endTime = StrToDate(endTime);
		}
		
		int ps = PageConstants.SALE_PAGE_SIZE;
		
		StringBuilder cntSql = new StringBuilder("SELECT COUNT(DISTINCT(HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale");
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		StringBuilder selectSql = new StringBuilder("SELECT HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS hourdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale");
		List<Object> params = new ArrayList<Object>();
		
		if (!storeName.equals("全部门店")) {
			whereSql.append(" and store_id=?");
			params.add(findStoreIdByStoreName(storeName));
		}
		if (beginTime != null && !beginTime.trim().isEmpty()) {
			whereSql.append(" and sa_date>=?");
			params.add(beginTime);
		}
		if (endTime != null && !endTime.trim().isEmpty()) {
			whereSql.append(" and sa_date<=?");
			params.add(endTime);
		}
		
		int tr = 0;
		Number number = (Number) qr.query(cntSql.toString()+whereSql.toString(), new ScalarHandler(), params.toArray());
		tr = number.intValue();
		System.out.println("符合条件的记录条数: " + tr);
		System.out.println("符合条件的记录条数sql: " + cntSql.toString()+whereSql.toString());
		
		String sql = selectSql.toString()+whereSql.toString() + " GROUP BY hourdate LIMIT ?,?";
		params.add((pc-1)*ps);
		params.add(ps);
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), params.toArray());
		System.out.println("查询语句sql: " + sql);
		
//		String sql = "";
//		Number number = null;
//		int tr = 0;
//		
//		// 0 0 0
//		sql = "SELECT COUNT(DISTINCT(HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id=? AND sa_date>=? AND sa_date<=?";
//		number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime, endTime);
//		tr = number.intValue();
//		
//		List<Object[]> list = null;
//		
//		// 0 0 0
//		sql = "SELECT HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS hourdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id=? AND sa_date>=? AND sa_date<=? GROUP BY hourdate LIMIT ?,?";
//		list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime, endTime,
//				(pc - 1) * ps, ps);
		
		
		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			if (obj[0] != null) {
				sale.setSaDate(obj[0].toString());
			}
			if (obj[1] != null) {
				sale.setSaGoodsNum(obj[1].toString()); // 将其看作“销售单数”
			}
			if (obj[2] != null) {
				sale.setSaRealPrice(obj[2].toString());
			}
			if (obj[3] != null) {
				sale.setSaProfit(obj[3].toString());
			}
			saleList.add(sale);
		}
		
		PageBean<Sale> pb = new PageBean<Sale>();
		pb.setBeanList(saleList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
		
	}
	/*
	 * 按天
	 */
	private PageBean<Sale> findByDay(String storeName, String beginTime, String endTime, Long uId, int pc)
			throws SQLException, ParseException {
		
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
					sql = "SELECT COUNT(DISTINCT(DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), uId);
					tr = number.intValue();
				} else {
					// 1 1 0
					sql = "SELECT COUNT(DISTINCT(DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), endTime, uId);
					tr = number.intValue();
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "SELECT COUNT(DISTINCT(DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE sa_date>=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, uId);
					tr = number.intValue();
				} else {
					// 1 0 0
					sql = "SELECT COUNT(DISTINCT(DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE sa_date>=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, endTime, uId);
					tr = number.intValue();
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "SELECT COUNT(DISTINCT(DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), uId);
					tr = number.intValue();
				} else {
					// 0 1 0
					sql = "SELECT COUNT(DISTINCT(DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), endTime, uId);
					tr = number.intValue();
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "SELECT COUNT(DISTINCT(DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id=? AND sa_date>=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime, uId);
					tr = number.intValue();
				} else {
					// 0 0 0
					sql = "SELECT COUNT(DISTINCT(DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id=? AND sa_date>=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime, endTime,
							uId);
					tr = number.intValue();
				}
			}
		}
		
		List<Object[]> list = null;
		
		if (storeName.equals("全部门店")) {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 1 1 1
					sql = "SELECT DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS daydate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY daydate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
				} else {
					// 1 1 0
					sql = "SELECT DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS daydate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY daydate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), endTime, uId, (pc - 1) * ps, ps);
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "SELECT DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS daydate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE sa_date>=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY daydate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), beginTime, uId, (pc - 1) * ps, ps);
				} else {
					// 1 0 0
					sql = "SELECT DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS daydate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE sa_date>=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY daydate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId, (pc - 1) * ps, ps);
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "SELECT DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS daydate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY daydate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), uId, (pc - 1) * ps,
							ps);
				} else {
					// 0 1 0
					sql = "SELECT DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS daydate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY daydate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), endTime, uId,
							(pc - 1) * ps, ps);
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "SELECT DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS daydate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id=? AND sa_date>=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY daydate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime, uId,
							(pc - 1) * ps, ps);
				} else {
					// 0 0 0
					sql = "SELECT DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS daydate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id=? AND sa_date>=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY daydate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime, endTime,
							uId, (pc - 1) * ps, ps);
				}
			}
		}
		
		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			if (obj[0] != null) {
				sale.setSaDate(obj[0].toString());
			}
			if (obj[1] != null) {
				sale.setSaGoodsNum(obj[1].toString()); // 将其看作“销售单数”
			}
			if (obj[2] != null) {
				sale.setSaRealPrice(obj[2].toString());
			}
			if (obj[3] != null) {
				sale.setSaProfit(obj[3].toString());
			}
			saleList.add(sale);
		}
		
		PageBean<Sale> pb = new PageBean<Sale>();
		pb.setBeanList(saleList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
		
	}
	private PageBean<Sale> findAllByDay(String storeName, String beginTime, String endTime, int pc)
			throws SQLException, ParseException {
		
		if (!beginTime.equals("")) {
			beginTime = StrToDate(beginTime);
		}
		if (!endTime.equals("")) {
			endTime = StrToDate(endTime);
		}
		
		int ps = PageConstants.SALE_PAGE_SIZE;
		
		StringBuilder cntSql = new StringBuilder("SELECT COUNT(DISTINCT(DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale");
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		StringBuilder selectSql = new StringBuilder("SELECT DAY(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS daydate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale");
		List<Object> params = new ArrayList<Object>();
		
		if (!storeName.equals("全部门店")) {
			whereSql.append(" and store_id=?");
			params.add(findStoreIdByStoreName(storeName));
		}
		if (beginTime != null && !beginTime.trim().isEmpty()) {
			whereSql.append(" and sa_date>=?");
			params.add(beginTime);
		}
		if (endTime != null && !endTime.trim().isEmpty()) {
			whereSql.append(" and sa_date<=?");
			params.add(endTime);
		}
		
		int tr = 0;
		Number number = (Number) qr.query(cntSql.toString()+whereSql.toString(), new ScalarHandler(), params.toArray());
		tr = number.intValue();
		System.out.println("符合条件的记录条数: " + tr);
		System.out.println("符合条件的记录条数sql: " + cntSql.toString()+whereSql.toString());
		
		String sql = selectSql.toString()+whereSql.toString() + " GROUP BY daydate LIMIT ?,?";
		params.add((pc-1)*ps);
		params.add(ps);
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), params.toArray());
		System.out.println("查询语句sql: " + sql);
		
		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			if (obj[0] != null) {
				sale.setSaDate(obj[0].toString());
			}
			if (obj[1] != null) {
				sale.setSaGoodsNum(obj[1].toString()); // 将其看作“销售单数”
			}
			if (obj[2] != null) {
				sale.setSaRealPrice(obj[2].toString());
			}
			if (obj[3] != null) {
				sale.setSaProfit(obj[3].toString());
			}
			saleList.add(sale);
		}
		
		PageBean<Sale> pb = new PageBean<Sale>();
		pb.setBeanList(saleList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
		
	}
	/*
	 * 按月
	 */
	private PageBean<Sale> findByMonth(String storeName, String beginTime, String endTime, Long uId, int pc)
			throws SQLException, ParseException {
		
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
					sql = "SELECT COUNT(DISTINCT(MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), uId);
					tr = number.intValue();
				} else {
					// 1 1 0
					sql = "SELECT COUNT(DISTINCT(MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), endTime, uId);
					tr = number.intValue();
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "SELECT COUNT(DISTINCT(MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE sa_date>=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, uId);
					tr = number.intValue();
				} else {
					// 1 0 0
					sql = "SELECT COUNT(DISTINCT(MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE sa_date>=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, endTime, uId);
					tr = number.intValue();
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "SELECT COUNT(DISTINCT(MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), uId);
					tr = number.intValue();
				} else {
					// 0 1 0
					sql = "SELECT COUNT(DISTINCT(MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), endTime, uId);
					tr = number.intValue();
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "SELECT COUNT(DISTINCT(MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id=? AND sa_date>=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime, uId);
					tr = number.intValue();
				} else {
					// 0 0 0
					sql = "SELECT COUNT(DISTINCT(MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale WHERE store_id=? AND sa_date>=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime, endTime,
							uId);
					tr = number.intValue();
				}
			}
		}
		
		List<Object[]> list = null;
		
		if (storeName.equals("全部门店")) {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 1 1 1
					sql = "SELECT MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS monthdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY monthdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
				} else {
					// 1 1 0
					sql = "SELECT MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS monthdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY monthdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), endTime, uId, (pc - 1) * ps, ps);
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "SELECT MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS monthdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE sa_date>=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY monthdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), beginTime, uId, (pc - 1) * ps, ps);
				} else {
					// 1 0 0
					sql = "SELECT MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS monthdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE sa_date>=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY monthdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId, (pc - 1) * ps, ps);
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "SELECT MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS monthdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY monthdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), uId, (pc - 1) * ps,
							ps);
				} else {
					// 0 1 0
					sql = "SELECT MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS monthdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY monthdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), endTime, uId,
							(pc - 1) * ps, ps);
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "SELECT MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS monthdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id=? AND sa_date>=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY monthdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime, uId,
							(pc - 1) * ps, ps);
				} else {
					// 0 0 0
					sql = "SELECT MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS monthdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale WHERE store_id=? AND sa_date>=? AND sa_date<=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY monthdate LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime, endTime,
							uId, (pc - 1) * ps, ps);
				}
			}
		}
		
		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			if (obj[0] != null) {
				sale.setSaDate(obj[0].toString());
			}
			if (obj[1] != null) {
				sale.setSaGoodsNum(obj[1].toString()); // 将其看作“销售单数”
			}
			if (obj[2] != null) {
				sale.setSaRealPrice(obj[2].toString());
			}
			if (obj[3] != null) {
				sale.setSaProfit(obj[3].toString());
			}
			saleList.add(sale);
		}
		
		PageBean<Sale> pb = new PageBean<Sale>();
		pb.setBeanList(saleList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
		
	}
	private PageBean<Sale> findAllByMonth(String storeName, String beginTime, String endTime, int pc)
			throws SQLException, ParseException {
		
		if (!beginTime.equals("")) {
			beginTime = StrToDate(beginTime);
		}
		if (!endTime.equals("")) {
			endTime = StrToDate(endTime);
		}
		
		int ps = PageConstants.SALE_PAGE_SIZE;
		
		StringBuilder cntSql = new StringBuilder("SELECT COUNT(DISTINCT(MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale");
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		StringBuilder selectSql = new StringBuilder("SELECT MONTH(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS monthdate ,COUNT(sa_serial_num),SUM(sa_real_price),SUM(sa_profit) FROM sale");
		List<Object> params = new ArrayList<Object>();
		
		if (!storeName.equals("全部门店")) {
			whereSql.append(" and store_id=?");
			params.add(findStoreIdByStoreName(storeName));
		}
		if (beginTime != null && !beginTime.trim().isEmpty()) {
			whereSql.append(" and sa_date>=?");
			params.add(beginTime);
		}
		if (endTime != null && !endTime.trim().isEmpty()) {
			whereSql.append(" and sa_date<=?");
			params.add(endTime);
		}
		
		int tr = 0;
		Number number = (Number) qr.query(cntSql.toString()+whereSql.toString(), new ScalarHandler(), params.toArray());
		tr = number.intValue();
		System.out.println("符合条件的记录条数: " + tr);
		System.out.println("符合条件的记录条数sql: " + cntSql.toString()+whereSql.toString());
		
		String sql = selectSql.toString()+whereSql.toString() + " GROUP BY monthdate LIMIT ?,?";
		params.add((pc-1)*ps);
		params.add(ps);
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), params.toArray());
		System.out.println("查询语句sql: " + sql);
		
		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			if (obj[0] != null) {
				sale.setSaDate(obj[0].toString());
			}
			if (obj[1] != null) {
				sale.setSaGoodsNum(obj[1].toString()); // 将其看作“销售单数”
			}
			if (obj[2] != null) {
				sale.setSaRealPrice(obj[2].toString());
			}
			if (obj[3] != null) {
				sale.setSaProfit(obj[3].toString());
			}
			saleList.add(sale);
		}
		
		PageBean<Sale> pb = new PageBean<Sale>();
		pb.setBeanList(saleList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
		
	}
	
	private String StrToDate(String str) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "" + date.getTime();
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

	@Override
	public PageBean<Sale> findAll(int pc) throws SQLException {
		int ps = PageConstants.SALE_PAGE_SIZE;

		String sql = "SELECT COUNT(DISTINCT(HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')))) FROM sale";
		Number number = qr.query(sql, new ScalarHandler());
		int tr = number.intValue();

		sql = "SELECT HOUR(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')) AS hourdate ,count(sa_serial_num),sum(sa_real_price),sum(sa_profit) FROM sale GROUP BY hourdate limit ?,?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), (pc - 1) * ps, ps);
		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			if (obj[0] != null) {
				sale.setSaDate(obj[0].toString());
			}
			if (obj[1] != null) {
				sale.setSaGoodsNum(obj[1].toString()); // 将其看作“销售单数”
			}
			if (obj[2] != null) {
				sale.setSaRealPrice(obj[2].toString());
			}
			if (obj[3] != null) {
				sale.setSaProfit(obj[3].toString());
			}
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
	public PageBean<Sale> findAllByCombination(String storeName, String beginTime, String endTime, String condition,
			int pc) throws SQLException, ParseException {
		if (condition.equals("按小时")) {
			return findAllByHour(storeName, beginTime, endTime, pc);
		}
		if (condition.equals("按天")) {
			return findAllByDay(storeName, beginTime, endTime, pc);
		}
		if (condition.equals("按月")) {
			return findAllByMonth(storeName, beginTime, endTime, pc);
		}
		return null;
	}
}

