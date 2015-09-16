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
			sale.setSaDate(obj[0].toString());
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
			sale.setSaDate(obj[0].toString());
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
			sale.setSaDate(obj[0].toString());
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
			sale.setSaDate(obj[0].toString());
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
