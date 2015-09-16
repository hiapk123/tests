package org.uestc.daoImp;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.uestc.dao.RJJLDao;
import org.uestc.util.PageBean;
import org.uestc.util.PageConstants;
import org.uestc.util.SqlHelper;

import com.uestc.bean.Employee;
import com.uestc.bean.Sale;
import com.uestc.bean.Store;

public class RJJLDaoImp implements RJJLDao {

	@Override
	public PageBean<Sale> findAllSalesByUid(Long uId, int pc) throws SQLException {
		int ps = PageConstants.SALE_PAGE_SIZE;

		String sql = "SELECT COUNT(DISTINCT(sa_saler_id)) FROM sale WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?)";
		Number number = qr.query(sql, new ScalarHandler(), uId);
		int tr = number.intValue();

//		sql = "SELECT tt.sa_date,tt.sa_saler_id,tt.sum_price,t1.sum_price,t2.sum_price,t3.sum_price"
//+" FROM (SELECT t.sa_date,t.sa_saler_id,SUM(t.sa_real_price) sum_price FROM sale t WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY sa_saler_id  LIMIT ?,?) tt"
//+" LEFT JOIN (SELECT t.sa_saler_id, SUM(t.sa_real_price) sum_price FROM sale t WHERE t.sa_type = 1 GROUP BY sa_saler_id) t1 ON t1.sa_saler_id = tt.sa_saler_id"
//+" LEFT JOIN (SELECT t.sa_saler_id, SUM(t.sa_real_price) sum_price FROM sale t WHERE t.sa_type = 2 GROUP BY sa_saler_id) t2 ON t2.sa_saler_id = tt.sa_saler_id"
//+" LEFT JOIN (SELECT t.sa_saler_id, SUM(t.sa_real_price) sum_price FROM sale t WHERE t.sa_type = 3 GROUP BY sa_saler_id) t3 ON t3.sa_saler_id = tt.sa_saler_id";
		
		// 一次性查询到开始时间、结束时间、收银员和收银总额，（现金，银联卡，在线后续单独查询之后拼接起来）
		sql = "SELECT MIN(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')),MAX(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')), sa_saler_id, SUM(sa_real_price) FROM sale WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY sa_saler_id LIMIT ?,?";
		
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			Employee employee = new Employee();
			sale.setSaDate(obj[0].toString());
			sale.setSaSerialNum(obj[1].toString()); //将其看作“结束时间”
			employee.setEmpName(findEmpNameByEmpId(Long.valueOf(obj[2].toString())));
			sale.setEmployee(employee); // 收银员
			sale.setSaRealPrice(obj[3].toString()); // 将其看作“收银总额”
			
			sale.setSaGoodsPrice(Double.toString(getCashMoney(Long.valueOf(obj[2].toString()), uId))); // 将其看作“现金”
			sale.setSaProfit(Double.toString(getCupCardMoney(Long.valueOf(obj[2].toString()), uId))); // 将其看作“银联卡”
			sale.setSaGoodsNum(Double.toString(getOnlineMoney(Long.valueOf(obj[2].toString()), uId))); // 在线  将其看作“在线”
			
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
	public PageBean<Sale> findByCombination(String storeName, String beginTime, String endTime, Long uId, int pc)
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
					sql = "SELECT COUNT(DISTINCT(sa_saler_id)) FROM sale WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), uId);
					tr = number.intValue();
				} else {
					// 1 1 0
					sql = "SELECT COUNT(DISTINCT(sa_saler_id)) FROM sale WHERE sa_date<=? and store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), endTime, uId);
					tr = number.intValue();
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "SELECT COUNT(DISTINCT(sa_saler_id)) FROM sale WHERE sa_date>=? and store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, uId);
					tr = number.intValue();
				} else {
					// 1 0 0
					sql = "SELECT COUNT(DISTINCT(sa_saler_id)) FROM sale WHERE sa_date>=? and sa_date<=? and store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, endTime, uId);
					tr = number.intValue();
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "SELECT COUNT(DISTINCT(sa_saler_id)) FROM sale WHERE store_id=? and store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), uId);
					tr = number.intValue();
				} else {
					// 0 1 0
					sql = "SELECT COUNT(DISTINCT(sa_saler_id)) FROM sale WHERE store_id=? and sa_date<=? and store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), endTime, uId);
					tr = number.intValue();
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "SELECT COUNT(DISTINCT(sa_saler_id)) FROM sale WHERE store_id=? and sa_date>=? and store_id IN (SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime, uId);
					tr = number.intValue();
				} else {
					// 0 0 0
					sql = "SELECT COUNT(DISTINCT(sa_saler_id)) FROM sale WHERE store_id=? and sa_date>=? and sa_date<=? and store_id IN (SELECT s_id FROM store WHERE u_id=?)";
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
					sql = "SELECT MIN(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')),MAX(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')), sa_saler_id, SUM(sa_real_price) FROM sale WHERE store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY sa_saler_id LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
				} else {
					// 1 1 0
					sql = "SELECT MIN(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')),MAX(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')), sa_saler_id, SUM(sa_real_price) FROM sale WHERE sa_date<=? and store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY sa_saler_id LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), endTime, uId, (pc - 1) * ps, ps);
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "SELECT MIN(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')),MAX(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')), sa_saler_id, SUM(sa_real_price) FROM sale WHERE sa_date>=? and store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY sa_saler_id LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), beginTime, uId, (pc - 1) * ps, ps);
				} else {
					// 1 0 0
					sql = "SELECT MIN(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')),MAX(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')), sa_saler_id, SUM(sa_real_price) FROM sale WHERE sa_date>=? and sa_date<=? and store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY sa_saler_id LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId, (pc - 1) * ps, ps);
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "SELECT MIN(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')),MAX(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')), sa_saler_id, SUM(sa_real_price) FROM sale WHERE store_id=? and store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY sa_saler_id LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), uId, (pc - 1) * ps,
							ps);
				} else {
					// 0 1 0
					sql = "SELECT MIN(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')),MAX(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')), sa_saler_id, SUM(sa_real_price) FROM sale WHERE store_id=? and sa_date<=? and store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY sa_saler_id LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), endTime, uId,
							(pc - 1) * ps, ps);
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "SELECT MIN(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')),MAX(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')), sa_saler_id, SUM(sa_real_price) FROM sale WHERE store_id=? and sa_date>=? and store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY sa_saler_id LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime, uId,
							(pc - 1) * ps, ps);
				} else {
					// 0 0 0
					sql = "SELECT MIN(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')),MAX(FROM_UNIXTIME(sa_date/1000,'%Y-%m-%d %h:%i:%s')), sa_saler_id, SUM(sa_real_price) FROM sale WHERE store_id=? and sa_date>=? and sa_date<=? and store_id IN (SELECT s_id FROM store WHERE u_id=?) GROUP BY sa_saler_id LIMIT ?,?";
					list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime, endTime,
							uId, (pc - 1) * ps, ps);
				}
			}
		}

		List<Sale> saleList = new ArrayList<Sale>();
		for (Object[] obj : list) {
			Sale sale = new Sale();
			Employee employee = new Employee();
			sale.setSaDate(obj[0].toString());
			sale.setSaSerialNum(obj[1].toString()); //将其看作“结束时间”
			employee.setEmpName(findEmpNameByEmpId(Long.valueOf(obj[2].toString())));
			sale.setEmployee(employee); // 收银员
			sale.setSaRealPrice(obj[3].toString()); // 将其看作“收银总额”
			
			if (storeName.equals("全部门店")) {
				sale.setSaGoodsPrice(Double.toString(getCashMoney(Long.valueOf(obj[2].toString()), uId))); // 将其看作“现金”
				sale.setSaProfit(Double.toString(getCupCardMoney(Long.valueOf(obj[2].toString()), uId))); // 将其看作“银联卡”
				sale.setSaGoodsNum(Double.toString(getOnlineMoney(Long.valueOf(obj[2].toString()), uId))); // 在线  将其看作“在线”
			} else {
				sale.setSaGoodsPrice(Double.toString(getCashMoney1(Long.valueOf(obj[2].toString()), uId, findStoreIdByStoreName(storeName)))); // 将其看作“现金”
				sale.setSaProfit(Double.toString(getCupCardMoney1(Long.valueOf(obj[2].toString()), uId, findStoreIdByStoreName(storeName)))); // 将其看作“银联卡”
				sale.setSaGoodsNum(Double.toString(getOnlineMoney1(Long.valueOf(obj[2].toString()), uId, findStoreIdByStoreName(storeName)))); // 在线  将其看作“在线”
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
	
	private double getMoney(Long saler_id, String sa_type, Long uId) throws SQLException {
		String sql = "select sum(t.sa_real_price) from sale t where t.sa_saler_id=? and t.sa_type=? AND store_id IN (SELECT s_id FROM store WHERE u_id=?) ";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), saler_id, sa_type, uId);
		if (list.size() == 1) {
			if (list.get(0)[0] != null) {
				double sum = (double) list.get(0)[0];
				return Double.valueOf(sum);
			}
		}
		return 0;
	}
	// (增加门店id条件)
	private double getMoney1(Long saler_id, String sa_type, Long uId, Long store_id) throws SQLException {
		String sql = "select sum(t.sa_real_price) from sale t where t.sa_saler_id=? and t.sa_type=?  AND store_id IN (SELECT s_id FROM store WHERE u_id=?) AND store_id=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), saler_id, sa_type, uId, store_id);
		if (list.size() == 1) {
			if (list.get(0)[0] != null) {
				double sum = (double) list.get(0)[0];
				return Double.valueOf(sum);
			}
		}
		return 0;
	}
	
	// 现金
	private double getCashMoney(Long saler_id, Long uId) throws SQLException {
		return getMoney(saler_id, "1", uId);
	}
	// 银联卡
	private double getCupCardMoney(Long saler_id, Long uId) throws SQLException {
		return getMoney(saler_id, "2", uId);
	}
	// 在线
	private double getOnlineMoney(Long saler_id, Long uId) throws SQLException {
		return getMoney(saler_id, "3", uId);
	}
	// 现金1(增加门店id条件)
	private double getCashMoney1(Long saler_id, Long uId, Long store_id) throws SQLException {
		return getMoney1(saler_id, "1", uId, store_id);
	}
	// 银联卡1(增加门店id条件)
	private double getCupCardMoney1(Long saler_id, Long uId, Long store_id) throws SQLException {
		return getMoney1(saler_id, "2", uId, store_id);
	}
	// 在线1(增加门店id条件)
	private double getOnlineMoney1(Long saler_id, Long uId, Long store_id) throws SQLException {
		return getMoney1(saler_id, "3", uId, store_id);
	}
	
	private String findEmpNameByEmpId(Long empId) throws SQLException {
		String sql = "select emp_name from employee where emp_id=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), empId);
		if (list.size() > 0) {
			Object[] obj = list.get(0);
			return obj[0].toString();
		}
		return null;
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
}
