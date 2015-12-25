package org.uestc.daoImp;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.uestc.dao.SPXSDao;
import org.uestc.util.PageBean;
import org.uestc.util.PageConstants;

import com.uestc.bean.SPXSBean;
import com.uestc.bean.Sale;
import com.uestc.bean.Store;

public class SPXSDaoImp implements SPXSDao {

	@Override
	public PageBean<SPXSBean> findAllByUid(Long uId, int pc) throws SQLException {
		int ps = PageConstants.SALE_PAGE_SIZE;

		String sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
		Number number = qr.query(sql, new ScalarHandler(), uId);
		int tr = number.intValue();

		sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
		List<SPXSBean> spxsBeanList = new ArrayList<SPXSBean>();
		for (Object[] obj : list) {
			SPXSBean spxsBean = new SPXSBean();
			if (obj[0] != null) {
				spxsBean.setgName(obj[0].toString());
			}
			if (obj[1] != null) {
				spxsBean.setgBarcode(obj[1].toString());
			}
			if (obj[2] != null) {
				spxsBean.setcName(obj[2].toString());
			}
			if (obj[3] != null) {
				spxsBean.setgStockNum(obj[3].toString());
			}
			if (obj[4] != null) {
				String quantity = obj[4].toString();
				int index = quantity.indexOf(".");
				if (index != -1) {
					quantity = quantity.substring(0, index);
				}
				spxsBean.setSaleQuantity(quantity);
			}
			if (obj[5] != null) {
				spxsBean.setgTotalPrice(obj[5].toString());
			}
			if (obj[6] != null) {
				spxsBean.setSaRealAmount(obj[6].toString());
			}
			if (obj[7] != null) {
				spxsBean.setSaProfit(obj[7].toString());
			}
			spxsBeanList.add(spxsBean);
		}

		PageBean<SPXSBean> pb = new PageBean<SPXSBean>();
		pb.setBeanList(spxsBeanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	@Override
	public PageBean<SPXSBean> findByCombination(String storeName, String beginTime, String endTime, String condition,
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
					if (condition.equals("")) {
						// 1 1 1 1
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), uId);
						tr = number.intValue();
					} else {
						// 1 1 1 0
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), condition, condition, uId);
						tr = number.intValue();
					}
				} else {
					if (condition.equals("")) {
						// 1 1 0 1
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), endTime, uId);
						tr = number.intValue();
					} else {
						// 1 1 0 0
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE sa.sa_date<=? AND (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), endTime, condition, condition, uId);
						tr = number.intValue();
					}
				}
			} else {
				if (endTime.equals("")) {
					if (condition.equals("")) {
						// 1 0 1 1
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE sa.sa_date>=? AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), beginTime, uId);
						tr = number.intValue();
					} else {
						// 1 0 1 0
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE sa.sa_date>=? AND (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), beginTime, condition, condition, uId);
						tr = number.intValue();
					}
				} else {
					if (condition.equals("")) {
						// 1 0 0 1
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE sa.sa_date>=? AND sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), beginTime, endTime, uId);
						tr = number.intValue();
					} else {
						// 1 0 0 0
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE sa.sa_date>=? AND sa.sa_date<=? AND (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), beginTime, endTime, condition, condition, uId);
						tr = number.intValue();
					}
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					if (condition.equals("")) {
						// 0 1 1 1
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE sa.store_id=? AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), uId);
						tr = number.intValue();
					} else {
						// 0 1 1 0
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE sa.store_id=? AND (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), condition,
								condition, uId);
						tr = number.intValue();
					}
				} else {
					if (condition.equals("")) {
						// 0 1 0 1
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), endTime, uId);
						tr = number.intValue();
					} else {
						// 0 1 0 0
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date<=? AND (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), endTime,
								condition, condition, uId);
						tr = number.intValue();
					}
				}
			} else {
				if (endTime.equals("")) {
					if (condition.equals("")) {
						// 0 0 1 1
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime, uId);
						tr = number.intValue();
					} else {
						// 0 0 1 0
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime,
								condition, condition, uId);
						tr = number.intValue();
					}
				} else {
					if (condition.equals("")) {
						// 0 0 0 1
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime,
								endTime, uId);
						tr = number.intValue();
					} else {
						// 0 0 0 0
						sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime,
								endTime, condition, condition, uId);
						tr = number.intValue();
					}
				}
			}
		}

		List<Object[]> list = null;

		if (storeName.equals("全部门店")) {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					if (condition.equals("")) {
						// 1 1 1 1
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
					} else {
						// 1 1 1 0
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), condition, condition, uId, (pc - 1) * ps, ps);
					}
				} else {
					if (condition.equals("")) {
						// 1 1 0 1
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), endTime, uId, (pc - 1) * ps, ps);
					} else {
						// 1 1 0 0
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE sa.sa_date<=? AND (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), endTime, condition, condition, uId, (pc - 1) * ps,
								ps);
					}
				}
			} else {
				if (endTime.equals("")) {
					if (condition.equals("")) {
						// 1 0 1 1
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE sa.sa_date>=? AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), beginTime, uId, (pc - 1) * ps, ps);
					} else {
						// 1 0 1 0
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE sa.sa_date>=? AND (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), beginTime, condition, condition, uId,
								(pc - 1) * ps, ps);
					}
				} else {
					if (condition.equals("")) {
						// 1 0 0 1
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE sa.sa_date>=? AND sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId, (pc - 1) * ps, ps);
					} else {
						// 1 0 0 0
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE sa.sa_date>=? AND sa.sa_date<=? AND (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, condition, condition, uId,
								(pc - 1) * ps, ps);
					}
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					if (condition.equals("")) {
						// 0 1 1 1
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE sa.store_id=? AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), uId,
								(pc - 1) * ps, ps);
					} else {
						// 0 1 1 0
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE sa.store_id=? AND (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), condition,
								condition, uId, (pc - 1) * ps, ps);
					}
				} else {
					if (condition.equals("")) {
						// 0 1 0 1
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), endTime, uId,
								(pc - 1) * ps, ps);
					} else {
						// 0 1 0 0
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date<=? AND (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), endTime,
								condition, condition, uId, (pc - 1) * ps, ps);
					}
				}
			} else {
				if (endTime.equals("")) {
					if (condition.equals("")) {
						// 0 0 1 1
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime, uId,
								(pc - 1) * ps, ps);
					} else {
						// 0 0 1 0
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime,
								condition, condition, uId, (pc - 1) * ps, ps);
					}
				} else {
					if (condition.equals("")) {
						// 0 0 0 1
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime,
								endTime, uId, (pc - 1) * ps, ps);
					} else {
						// 0 0 0 0
						sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND (g.g_name=? OR g.g_barcode=?) AND g.g_barcode=sa.g_barcode AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) GROUP BY g.g_barcode limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime,
								endTime, condition, condition, uId, (pc - 1) * ps, ps);
					}
				}
			}
		}

		List<SPXSBean> spxsBeanList = new ArrayList<SPXSBean>();
		for (Object[] obj : list) {
			SPXSBean spxsBean = new SPXSBean();
			if (obj[0] != null) {
				spxsBean.setgName(obj[0].toString());
			}
			if (obj[1] != null) {
				spxsBean.setgBarcode(obj[1].toString());
			}
			if (obj[2] != null) {
				spxsBean.setcName(obj[2].toString());
			}
			if (obj[3] != null) {
				spxsBean.setgStockNum(obj[3].toString());
			}
			if (obj[4] != null) {
				String quantity = obj[4].toString();
				int index = quantity.indexOf(".");
				if (index != -1) {
					quantity = quantity.substring(0, index);
				}
				spxsBean.setSaleQuantity(quantity);
			}
			if (obj[5] != null) {
				spxsBean.setgTotalPrice(obj[5].toString());
			}
			if (obj[6] != null) {
				spxsBean.setSaRealAmount(obj[6].toString());
			}
			if (obj[7] != null) {
				spxsBean.setSaProfit(obj[7].toString());
			}
			spxsBeanList.add(spxsBean);
		}

		PageBean<SPXSBean> pb = new PageBean<SPXSBean>();
		pb.setBeanList(spxsBeanList);
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
	public PageBean<SPXSBean> findAll(int pc) throws SQLException {
		int ps = PageConstants.SALE_PAGE_SIZE;

		String sql = "SELECT COUNT(DISTINCT(sa.g_barcode)) FROM goods g,sale sa WHERE g.g_barcode=sa.g_barcode";
		Number number = qr.query(sql, new ScalarHandler());
		int tr = number.intValue();

		sql = "SELECT g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) FROM goods g,sale sa WHERE g.g_barcode=sa.g_barcode GROUP BY g.g_barcode limit ?,?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), (pc - 1) * ps, ps);
		List<SPXSBean> spxsBeanList = new ArrayList<SPXSBean>();
		for (Object[] obj : list) {
			SPXSBean spxsBean = new SPXSBean();
			if (obj[0] != null) {
				spxsBean.setgName(obj[0].toString());
			}
			if (obj[1] != null) {
				spxsBean.setgBarcode(obj[1].toString());
			}
			if (obj[2] != null) {
				spxsBean.setcName(obj[2].toString());
			}
			if (obj[3] != null) {
				spxsBean.setgStockNum(obj[3].toString());
			}
			if (obj[4] != null) {
				String quantity = obj[4].toString();
				int index = quantity.indexOf(".");
				if (index != -1) {
					quantity = quantity.substring(0, index);
				}
				spxsBean.setSaleQuantity(quantity);
			}
			if (obj[5] != null) {
				spxsBean.setgTotalPrice(obj[5].toString());
			}
			if (obj[6] != null) {
				spxsBean.setSaRealAmount(obj[6].toString());
			}
			if (obj[7] != null) {
				spxsBean.setSaProfit(obj[7].toString());
			}
			spxsBeanList.add(spxsBean);
		}

		PageBean<SPXSBean> pb = new PageBean<SPXSBean>();
		pb.setBeanList(spxsBeanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	/**
	 * 管理员条件组合查询
	 */
	@Override
	public PageBean<SPXSBean> findAllByCombination(String storeName, String beginTime, String endTime, String condition,
			int pc) throws SQLException, ParseException {
		if (!beginTime.equals("")) {
			beginTime = StrToDate(beginTime);
		}
		if (!endTime.equals("")) {
			endTime = StrToDate(endTime);
		}
		System.out.println("dao    : " + storeName+", "+beginTime+", "+endTime+", "+condition);
		// 一个条件都没有选择： 		全部门店, , ,即："全部门店" "" "" "" 
		// 所有条件都选择： 			门店3, 1451565900000, 1452351300000, ssssss
		int ps = PageConstants.SALE_PAGE_SIZE;
		
		
		StringBuilder cntSql = new StringBuilder("select count(DISTINCT(sa.g_barcode)) from goods g,sale sa");
		StringBuilder whereSql = new StringBuilder(" where 1=1 and g.g_barcode=sa.g_barcode");
		StringBuilder selectSql = new StringBuilder("select g.g_name,g.g_barcode,g.c_name,g.g_stock_num,SUM(sa.sa_goods_num),SUM(sa.sa_goods_price),SUM(sa.sa_real_price),SUM(sa.sa_profit) from goods g,sale sa");
		List<Object> params = new ArrayList<Object>();
		
		if (!storeName.equals("全部门店")) {
			whereSql.append(" and sa.store_id=?");
			params.add(findStoreIdByStoreName(storeName));
		}
		if (beginTime != null && !beginTime.trim().isEmpty()) {
			whereSql.append(" and sa.sa_date>=?");
			params.add(beginTime);
		}
		if (endTime != null && !endTime.trim().isEmpty()) {
			whereSql.append(" and sa.sa_date<=?");
			params.add(endTime);
		}
		if (condition != null && !condition.trim().isEmpty()) {
			whereSql.append(" and (g.g_name like ? OR g.g_barcode like ?)");
			params.add("%" + condition + "%");
			params.add("%" + condition + "%");
		}
		
		int tr = 0;
		Number number = (Number) qr.query(cntSql.toString()+whereSql.toString(), new ScalarHandler(), params.toArray());
		tr = number.intValue();
		System.out.println("符合条件的记录条数: " + tr);
		System.out.println("符合条件的记录条数sql: " + cntSql.toString()+whereSql.toString());
		
		String sql = selectSql.toString()+whereSql.toString() + " group by g.g_barcode limit ?,?";
		params.add((pc-1)*ps);
		params.add(ps);
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), params.toArray());
		System.out.println("查询语句sql: " + sql);

		List<SPXSBean> spxsBeanList = new ArrayList<SPXSBean>();
		for (Object[] obj : list) {
			SPXSBean spxsBean = new SPXSBean();
			if (obj[0] != null) {
				spxsBean.setgName(obj[0].toString());
			}
			if (obj[1] != null) {
				spxsBean.setgBarcode(obj[1].toString());
			}
			if (obj[2] != null) {
				spxsBean.setcName(obj[2].toString());
			}
			if (obj[3] != null) {
				spxsBean.setgStockNum(obj[3].toString());
			}
			if (obj[4] != null) {
				String quantity = obj[4].toString();
				int index = quantity.indexOf(".");
				if (index != -1) {
					quantity = quantity.substring(0, index);
				}
				spxsBean.setSaleQuantity(quantity);
			}
			if (obj[5] != null) {
				spxsBean.setgTotalPrice(obj[5].toString());
			}
			if (obj[6] != null) {
				spxsBean.setSaRealAmount(obj[6].toString());
			}
			if (obj[7] != null) {
				spxsBean.setSaProfit(obj[7].toString());
			}
			spxsBeanList.add(spxsBean);
		}

		PageBean<SPXSBean> pb = new PageBean<SPXSBean>();
		pb.setBeanList(spxsBeanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}
}

