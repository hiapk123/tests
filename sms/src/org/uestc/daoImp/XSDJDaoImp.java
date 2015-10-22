package org.uestc.daoImp;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.uestc.dao.XSDJDao;
import org.uestc.util.PageBean;
import org.uestc.util.PageConstants;

import com.uestc.bean.SPXSBean;
import com.uestc.bean.Store;
import com.uestc.bean.XSDJBean;

public class XSDJDaoImp implements XSDJDao {

	@Override
	public PageBean<XSDJBean> findAllByUid(Long uId, int pc) throws SQLException {
		int ps = PageConstants.SALE_PAGE_SIZE;

		String sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
		Number number = qr.query(sql, new ScalarHandler(), uId);
		int tr = number.intValue();

		sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
		List<XSDJBean> xsdjBeanList = new ArrayList<XSDJBean>();
		for (Object[] obj : list) {
			XSDJBean xsdjBean = new XSDJBean();

			if (obj[0] != null) {
				xsdjBean.setSaSeriNum(obj[0].toString());
			}
			if (obj[1] != null) {
				xsdjBean.setSaDate(msecToDateTimeStr(obj[1].toString()));
			}
			if (obj[2] != null) {
				xsdjBean.setSaType(numericToChinese(obj[2].toString()));
			}
			if (obj[3] != null) {
				xsdjBean.setSaCashier(findEmpNameByEmpId(Long.valueOf(obj[3].toString())));
			}
			if (obj[4] != null) {
				xsdjBean.setSaVipName(findVipNameByVipId(Long.valueOf(obj[4].toString())));
			}
			if (obj[5] != null) {
				xsdjBean.setSaGoodsQuantity(obj[5].toString());
			}
			if (obj[6] != null) {
				xsdjBean.setgPurPrice(obj[6].toString());
			}
			if (obj[7] != null) {
				xsdjBean.setSaRealPrice(obj[7].toString());
			}
			if (obj[8] != null) {
				xsdjBean.setSaProfit(obj[8].toString());
			}
			xsdjBeanList.add(xsdjBean);
			
		}

		PageBean<XSDJBean> pb = new PageBean<XSDJBean>();
		pb.setBeanList(xsdjBeanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	@Override
	public PageBean<XSDJBean> findByCombination(String storeName, String receiptType, String beginTime, String endTime,
			String seriNum, Long uId, int pc) throws SQLException, ParseException {
		if (!beginTime.equals("")) {
			beginTime = StrToDate(beginTime);
		}
		if (!endTime.equals("")) {
			endTime = StrToDate(endTime);
		}

		if (receiptType.equals("有效单据")) {
			receiptType = "1";
		} else if (receiptType.equals("无效单据")) {
			receiptType = "0";
		} else if (receiptType.equals("会员单据")) {
			receiptType = "2";
		}

		int ps = PageConstants.SALE_PAGE_SIZE;

		String sql = "";
		Number number = null;
		int tr = 0;

		if (storeName.equals("全部门店")) {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					if (seriNum.equals("")) {
						// 1 1 1 1
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), uId);
						}
						tr = number.intValue();
					} else {
						// 1 1 1 0
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), seriNum, receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), seriNum, uId);
						}
						tr = number.intValue();
					}
				} else {
					if (seriNum.equals("")) {
						// 1 1 0 1
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.sa_date<=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), endTime, receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), endTime, uId);
						}
						tr = number.intValue();
					} else {
						// 1 1 0 0
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.sa_date<=? AND sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), endTime, seriNum, receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.sa_date<=? AND sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), endTime, seriNum, uId);
						}
						tr = number.intValue();
					}
				}
			} else {
				if (endTime.equals("")) {
					if (seriNum.equals("")) {
						// 1 0 1 1
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.sa_date>=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), beginTime, receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.sa_date>=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), beginTime, uId);
						}
						tr = number.intValue();
					} else {
						// 1 0 1 0
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.sa_date>=? AND sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), beginTime, seriNum, receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.sa_date>=? AND sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), beginTime, seriNum, uId);
						}
						tr = number.intValue();
					}
				} else {
					if (seriNum.equals("")) {
						// 1 0 0 1
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.sa_date>=? AND sa.sa_date<=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), beginTime, endTime, receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.sa_date>=? AND sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), beginTime, endTime, uId);
						}
						tr = number.intValue();
					} else {
						// 1 0 0 0
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.sa_date>=? AND sa.sa_date<=? AND sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), beginTime, endTime, seriNum, receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.sa_date>=? AND sa.sa_date<=? AND sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), beginTime, endTime, seriNum, uId);
						}
						tr = number.intValue();
					}
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					if (seriNum.equals("")) {
						// 0 1 1 1
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), receiptType,
								uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), uId);
						}
						tr = number.intValue();
					} else {
						// 0 1 1 0
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), seriNum,
								receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), seriNum,
									uId);
						}
						tr = number.intValue();
					}
				} else {
					if (seriNum.equals("")) {
						// 0 1 0 1
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date<=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), endTime,
								receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), endTime,
									uId);
						}
						tr = number.intValue();
					} else {
						// 0 1 0 0
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date<=? AND sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), endTime, seriNum,
								receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date<=? AND sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), endTime,
									seriNum, uId);
						}
						tr = number.intValue();
					}
				}
			} else {
				if (endTime.equals("")) {
					if (seriNum.equals("")) {
						// 0 0 1 1
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime,
								receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime,
									uId);
						}
						tr = number.intValue();
					} else {
						// 0 0 1 0
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime,
								seriNum, receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime,
									seriNum, uId);
						}
						tr = number.intValue();
					}
				} else {
					if (seriNum.equals("")) {
						// 0 0 0 1
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime,
								endTime, receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime,
									endTime, uId);
						}
						tr = number.intValue();
					} else {
						// 0 0 0 0
						sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
						number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime,
								endTime, seriNum, receiptType, uId);
						if (receiptType.equals("2")) {
							sql = "SELECT COUNT(*) FROM goods g,sale sa WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?)";
							number = qr.query(sql, new ScalarHandler(), findStoreIdByStoreName(storeName), beginTime,
									endTime, seriNum, uId);
						}
						tr = number.intValue();
					}
				}
			}
		}

		List<Object[]> list = null;

		if (storeName.equals("全部门店")) {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					if (seriNum.equals("")) {
						// 1 1 1 1
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), receiptType, uId, (pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
						}
					} else {
						// 1 1 1 0
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), seriNum, receiptType, uId, (pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), seriNum, uId, (pc - 1) * ps, ps);
						}
					}
				} else {
					if (seriNum.equals("")) {
						// 1 1 0 1
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.sa_date<=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), endTime, receiptType, uId, (pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), endTime, uId, (pc - 1) * ps, ps);
						}
					} else {
						// 1 1 0 0
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.sa_date<=? AND sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), endTime, seriNum, receiptType, uId, (pc - 1) * ps,
								ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.sa_date<=? AND sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), endTime, seriNum, uId, (pc - 1) * ps, ps);
						}
					}
				}
			} else {
				if (endTime.equals("")) {
					if (seriNum.equals("")) {
						// 1 0 1 1
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.sa_date>=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), beginTime, receiptType, uId, (pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.sa_date>=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), beginTime, uId, (pc - 1) * ps, ps);
						}
					} else {
						// 1 0 1 0
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.sa_date>=? AND sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), beginTime, seriNum, receiptType, uId,
								(pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.sa_date>=? AND sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), beginTime, seriNum, uId, (pc - 1) * ps, ps);
						}
					}
				} else {
					if (seriNum.equals("")) {
						// 1 0 0 1
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.sa_date>=? AND sa.sa_date<=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, receiptType, uId,
								(pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.sa_date>=? AND sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId, (pc - 1) * ps, ps);
						}
					} else {
						// 1 0 0 0
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.sa_date>=? AND sa.sa_date<=? AND sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, seriNum, receiptType, uId,
								(pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.sa_date>=? AND sa.sa_date<=? AND sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, seriNum, uId,
									(pc - 1) * ps, ps);
						}
					}
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					if (seriNum.equals("")) {
						// 0 1 1 1
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), receiptType,
								uId, (pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), uId,
									(pc - 1) * ps, ps);
						}
					} else {
						// 0 1 1 0
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), seriNum,
								receiptType, uId, (pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), seriNum,
									uId, (pc - 1) * ps, ps);
						}
					}
				} else {
					if (seriNum.equals("")) {
						// 0 1 0 1
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.sa_date<=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), endTime,
								receiptType, uId, (pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), endTime,
									uId, (pc - 1) * ps, ps);
						}
					} else {
						// 0 1 0 0
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.sa_date<=? AND sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), endTime,
								seriNum, receiptType, uId, (pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.sa_date<=? AND sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), endTime,
									seriNum, uId, (pc - 1) * ps, ps);
						}
					}
				}
			} else {
				if (endTime.equals("")) {
					if (seriNum.equals("")) {
						// 0 0 1 1
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.sa_date>=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime,
								receiptType, uId, (pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.sa_date>=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime,
									uId, (pc - 1) * ps, ps);
						}
					} else {
						// 0 0 1 0
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime,
								seriNum, receiptType, uId, (pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime,
									seriNum, uId, (pc - 1) * ps, ps);
						}
					}
				} else {
					if (seriNum.equals("")) {
						// 0 0 0 1
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime,
								endTime, receiptType, uId, (pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime,
									endTime, uId, (pc - 1) * ps, ps);
						}
					} else {
						// 0 0 0 0
						sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND sa.sa_serial_num=? AND sa.s_del=? AND g.g_barcode=sa.g_barcode AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
						list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime,
								endTime, seriNum, receiptType, uId, (pc - 1) * ps, ps);
						if (receiptType.equals("2")) {
							sql = "SELECT sa.sa_serial_num,sa.sa_date,sa.sa_type,sa.sa_saler_id,sa.sa_buyer_id,sa.sa_goods_num,g.g_pur_price,sa.sa_real_price,sa.sa_profit FROM sale sa, goods g WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND sa.sa_serial_num=? AND g.g_barcode=sa.g_barcode AND sa.sa_buyer_id in (select v_id from vip where v_id <> 10000) AND store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
							list = qr.query(sql, new ArrayListHandler(), findStoreIdByStoreName(storeName), beginTime,
									endTime, seriNum, uId, (pc - 1) * ps, ps);
						}
					}
				}
			}
		}

		List<XSDJBean> xsdjBeanList = new ArrayList<XSDJBean>();
		for (Object[] obj : list) {
			XSDJBean xsdjBean = new XSDJBean();


			if (obj[0] != null) {
				xsdjBean.setSaSeriNum(obj[0].toString());
			}
			if (obj[1] != null) {
				xsdjBean.setSaDate(msecToDateTimeStr(obj[1].toString()));
			}
			if (obj[2] != null) {
				xsdjBean.setSaType(numericToChinese(obj[2].toString()));
			}
			if (obj[3] != null) {
				xsdjBean.setSaCashier(findEmpNameByEmpId(Long.valueOf(obj[3].toString())));
			}
			if (obj[4] != null) {
				xsdjBean.setSaVipName(findVipNameByVipId(Long.valueOf(obj[4].toString())));
			}
			if (obj[5] != null) {
				xsdjBean.setSaGoodsQuantity(obj[5].toString());
			}
			if (obj[6] != null) {
				xsdjBean.setgPurPrice(obj[6].toString());
			}
			if (obj[7] != null) {
				xsdjBean.setSaRealPrice(obj[7].toString());
			}
			if (obj[8] != null) {
				xsdjBean.setSaProfit(obj[8].toString());
			}
			
			xsdjBeanList.add(xsdjBean);
		}

		PageBean<XSDJBean> pb = new PageBean<XSDJBean>();
		pb.setBeanList(xsdjBeanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);

		return pb;
	}

	private String msecToDateTimeStr(String msecStr) {
		Long msecLong = Long.valueOf(msecStr);
		Date dat = new Date(msecLong);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dat);
		SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = format.format(gc.getTime());
		return str;
	}

	private String numericToChinese(String value) {
		if (value.equals("1")) {
			return "现金";
		} else if (value.equals("2")) {
			return "银联卡";
		} else if (value.equals("3")) {
			return "在线";
		}
		return "";
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

	private String findVipNameByVipId(Long vipId) throws SQLException {
		String sql = "select vip_name from vip where v_id=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), vipId);
		if (list.size() > 0) {
			Object[] obj = list.get(0);
			return obj[0].toString();
		}
		return null;
	}

	/*
	 * yyyy-MM-dd HH:mm:ss格式的字符串转换为毫秒数的时间字符串
	 */
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

