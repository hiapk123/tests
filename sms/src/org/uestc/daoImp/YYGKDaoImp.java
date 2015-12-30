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
import org.uestc.dao.YYGKDao;
import org.uestc.util.PageBean;
import org.uestc.util.PageConstants;

import com.uestc.bean.Employee;
import com.uestc.bean.JiaoJieBan_HP;
import com.uestc.bean.SPXSBean;
import com.uestc.bean.Sale;
import com.uestc.bean.SalesSummaryBean;
import com.uestc.bean.Store;
import com.uestc.bean.XJSZBean;

public class YYGKDaoImp implements YYGKDao {

	@Override
	public PageBean<XJSZBean> findCashDetailsByCombination(String storeName, String beginTime, String endTime, Long uId,
			int pc) throws SQLException {
		if (!beginTime.equals("")) {
			beginTime = StrToDate(beginTime);
			System.out.println(beginTime);
		}
		if (!endTime.equals("")) {
			endTime = StrToDate(endTime);
			System.out.println(endTime);
		}
		
		int ps = PageConstants.SALE_PAGE_SIZE;
		
		String sql = "";
		Number number = null;
		int tr = 0;
		Long storeId = findStoreIdByStoreName(storeName);
		
		if (storeName.equals("全部门店")) {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 1 1 1
					sql = "SELECT COUNT(*) FROM sale sa, flow f WHERE sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), uId);
					tr = number.intValue();
				} else {
					// 1 1 0
					sql = "SELECT COUNT(*) FROM sale sa, flow f WHERE sa.sa_date<=? AND sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), endTime, uId);
					tr = number.intValue();
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "SELECT COUNT(*) FROM sale sa, flow f WHERE sa.sa_date>=? AND sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, uId);
					tr = number.intValue();
				} else {
					// 1 0 0
					sql = "SELECT COUNT(*) FROM sale sa, flow f WHERE sa.sa_date>=? AND sa.sa_date<=? AND sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, endTime, uId);
					tr = number.intValue();
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "SELECT COUNT(*) FROM sale sa, flow f WHERE sa.store_id=? AND sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), storeId, uId);
					tr = number.intValue();
				} else {
					// 0 1 0
					sql = "SELECT COUNT(*) FROM sale sa, flow f WHERE sa.store_id=? AND sa.sa_date<=? AND sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), storeId, endTime, uId);
					tr = number.intValue();
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "SELECT COUNT(*) FROM sale sa, flow f WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), storeId, beginTime, uId);
					tr = number.intValue();
				} else {
					// 0 0 0
					sql = "SELECT COUNT(*) FROM sale sa, flow f WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), storeId, beginTime, endTime, uId);
					tr = number.intValue();
					System.out.println("0 0 0 : " + tr);
				}
			}
		}

		List<Object[]> list = null;

		if (storeName.equals("全部门店")) {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 1 1 1
					sql = "SELECT sa.sa_date, sa.sa_saler_id, f.cash, f.zhaoxian, f.yingfu FROM sale sa, flow f WHERE sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), uId, (pc - 1) * ps, ps);
				} else {
					// 1 1 0
					sql = "SELECT sa.sa_date, sa.sa_saler_id, f.cash, f.zhaoxian, f.yingfu FROM sale sa, flow f WHERE sa.sa_date<=? AND sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), endTime, uId, (pc - 1) * ps, ps);
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "SELECT sa.sa_date, sa.sa_saler_id, f.cash, f.zhaoxian, f.yingfu FROM sale sa, flow f WHERE sa.sa_date>=? AND sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), beginTime, uId, (pc - 1) * ps, ps);
				} else {
					// 1 0 0
					sql = "SELECT sa.sa_date, sa.sa_saler_id, f.cash, f.zhaoxian, f.yingfu FROM sale sa, flow f WHERE sa.sa_date>=? AND sa.sa_date<=? AND sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId, (pc - 1) * ps, ps);
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "SELECT sa.sa_date, sa.sa_saler_id, f.cash, f.zhaoxian, f.yingfu FROM sale sa, flow f WHERE sa.store_id=? AND sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), storeId, uId, (pc - 1) * ps, ps);
				} else {
					// 0 1 0
					sql = "SELECT sa.sa_date, sa.sa_saler_id, f.cash, f.zhaoxian, f.yingfu FROM sale sa, flow f WHERE sa.store_id=? AND sa.sa_date<=? AND sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), storeId, endTime, uId, (pc - 1) * ps, ps);
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "SELECT sa.sa_date, sa.sa_saler_id, f.cash, f.zhaoxian, f.yingfu FROM sale sa, flow f WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, uId, (pc - 1) * ps, ps);
				} else {
					// 0 0 0
					sql = "SELECT sa.sa_date, sa.sa_saler_id, f.cash, f.zhaoxian, f.yingfu FROM sale sa, flow f WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND sa.sa_serial_num = f.flows AND sa.store_id in(SELECT s_id FROM store WHERE u_id=?) limit ?,?";
					System.out.println("0 0 0 查询");
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, endTime, uId, (pc - 1) * ps, ps);
				}
			}
		}
		
		List<XJSZBean> xjszList = new ArrayList<XJSZBean>();
		for (Object[] obj : list) {
			XJSZBean xjsz = new XJSZBean();
			Employee employee = new Employee();
			if (obj[0] != null) {
				xjsz.setSaDate(msecToDateTimeStr(obj[0].toString()));
			}
			if (obj[1] != null) {
				employee.setEmpName(findEmpNameByEmpId(Long.valueOf(obj[1].toString())));
				xjsz.setEmployee(employee);
			}
			if (obj[2] != null) {
				xjsz.setfCash(obj[2].toString());
			}
			if (obj[3] != null) {
				xjsz.setfZhaoXian(obj[3].toString());
			}
			if (obj[4] != null) {
				xjsz.setfYingFu(obj[4].toString());
			}
			xjszList.add(xjsz);
		}
		
		PageBean<XJSZBean> pb = new PageBean<XJSZBean>();
		pb.setBeanList(xjszList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
		
	}
	
	@Override
	public PageBean<JiaoJieBan_HP> findShiftingDutyRecordByCombination(String storeName, String beginTime,
			String endTime, Long uId, int pc) throws SQLException {
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
		Long storeId = findStoreIdByStoreName(storeName);
		
		if (storeName.equals("全部门店")) {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 1 1 1
					sql = "SELECT COUNT(DISTINCT(id)) FROM jiaojieban WHERE saler_id IN (SELECT emp_id FROM users WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), uId);
					tr = number.intValue();
				} else {
					// 1 1 0
					sql = "SELECT COUNT(DISTINCT(id)) FROM jiaojieban WHERE end_time<=? and saler_id IN (SELECT emp_id FROM users WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), endTime,
							uId);
					tr = number.intValue();
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "SELECT COUNT(DISTINCT(id)) FROM jiaojieban WHERE start_time>=? and saler_id IN (SELECT emp_id FROM users WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime,
							uId);
					tr = number.intValue();
				} else {
					// 1 0 0
					sql = "SELECT COUNT(DISTINCT(id)) FROM jiaojieban WHERE start_time>=? and end_time<=? and saler_id IN (SELECT emp_id FROM users WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, endTime,
							uId);
					tr = number.intValue();
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "SELECT COUNT(DISTINCT(id)) FROM jiaojieban WHERE (SELECT store_id FROM employee WHERE emp_id=(SELECT emp_id FROM users WHERE u_id=?))=? and saler_id IN (SELECT emp_id FROM users WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), uId, storeId,
							uId);
					tr = number.intValue();
				} else {
					// 0 1 0
					sql = "SELECT COUNT(DISTINCT(id)) FROM jiaojieban WHERE (SELECT store_id FROM employee WHERE emp_id=(SELECT emp_id FROM users WHERE u_id=?))=? and end_time<=? and saler_id IN (SELECT emp_id FROM users WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), uId, storeId, endTime,
							uId);
					tr = number.intValue();
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "SELECT COUNT(DISTINCT(id)) FROM jiaojieban WHERE (SELECT store_id FROM employee WHERE emp_id=(SELECT emp_id FROM users WHERE u_id=?))=? and start_time>=? and saler_id IN (SELECT emp_id FROM users WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), uId, storeId, beginTime,
							uId);
					tr = number.intValue();
				} else {
					// 0 0 0
					sql = "SELECT COUNT(DISTINCT(id)) FROM jiaojieban WHERE (SELECT store_id FROM employee WHERE emp_id=(SELECT emp_id FROM users WHERE u_id=?))=? and start_time>=? and end_time<=? and saler_id IN (SELECT emp_id FROM users WHERE u_id=?)";
					number = qr.query(sql, new ScalarHandler(), uId, storeId, beginTime, endTime,
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
					sql = "SELECT start_time, end_time, saler_id, total_all, cash_pay, bank_pay, online_pay FROM jiaojieban WHERE saler_id IN (SELECT emp_id FROM users WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), 
							uId, (pc - 1) * ps, ps);
				} else {
					// 1 1 0
					sql = "SELECT start_time, end_time, saler_id, total_all, cash_pay, bank_pay, online_pay FROM jiaojieban WHERE end_time<=? and saler_id IN (SELECT emp_id FROM users WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), endTime,
							uId, (pc - 1) * ps, ps);
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "SELECT start_time, end_time, saler_id, total_all, cash_pay, bank_pay, online_pay FROM jiaojieban WHERE start_time>=? and saler_id IN (SELECT emp_id FROM users WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), beginTime,
							uId, (pc - 1) * ps, ps);
				} else {
					// 1 0 0
					sql = "SELECT start_time, end_time, saler_id, total_all, cash_pay, bank_pay, online_pay FROM jiaojieban WHERE start_time>=? and end_time<=? and saler_id IN (SELECT emp_id FROM users WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime,
							uId, (pc - 1) * ps, ps);
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "SELECT start_time, end_time, saler_id, total_all, cash_pay, bank_pay, online_pay FROM jiaojieban WHERE (SELECT store_id FROM employee WHERE emp_id=(SELECT emp_id FROM users WHERE u_id=?))=? and saler_id IN (SELECT emp_id FROM users WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), uId, storeId,
							uId, (pc - 1) * ps, ps);
				} else {
					// 0 1 0
					sql = "SELECT start_time, end_time, saler_id, total_all, cash_pay, bank_pay, online_pay FROM jiaojieban WHERE (SELECT store_id FROM employee WHERE emp_id=(SELECT emp_id FROM users WHERE u_id=?))=? and end_time<=? and saler_id IN (SELECT emp_id FROM users WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), uId, storeId, endTime,
							uId, (pc - 1) * ps, ps);
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "SELECT start_time, end_time, saler_id, total_all, cash_pay, bank_pay, online_pay FROM jiaojieban WHERE (SELECT store_id FROM employee WHERE emp_id=(SELECT emp_id FROM users WHERE u_id=?))=? and start_time>=? and saler_id IN (SELECT emp_id FROM users WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), uId, storeId, beginTime,
							uId, (pc - 1) * ps, ps);
				} else {
					// 0 0 0
					sql = "SELECT start_time, end_time, saler_id, total_all, cash_pay, bank_pay, online_pay FROM jiaojieban WHERE (SELECT store_id FROM employee WHERE emp_id=(SELECT emp_id FROM users WHERE u_id=?))=? and start_time>=? and end_time<=? and saler_id IN (SELECT emp_id FROM users WHERE u_id=?) limit ?,?";
					list = qr.query(sql, new ArrayListHandler(), uId, storeId, beginTime, endTime,
							uId, (pc - 1) * ps, ps);
				}
			}
		}
		
		List<JiaoJieBan_HP> jjbList = new ArrayList<JiaoJieBan_HP>();
		for (Object[] obj : list) {
			JiaoJieBan_HP jjb = new JiaoJieBan_HP();
			Employee employee = new Employee();
			if (obj[0] != null) {
				jjb.setStartTime(msecToDateTimeStr(obj[0].toString()));
			}
			if (obj[1] != null) {
				jjb.setEndTime(msecToDateTimeStr(obj[1].toString()));
			}
			if (obj[2] != null) {
				employee.setEmpName(findEmpNameByEmpId(Long.valueOf(obj[2].toString())));
				jjb.setEmployee(employee);
			}
			if (obj[3] != null) {
				jjb.setTotalAll(obj[3].toString());
			}
			if (obj[4] != null) {
				jjb.setCashPay(obj[4].toString());
			}
			if (obj[5] != null) {
				jjb.setBankPay(obj[5].toString());
			}
			if (obj[6] != null) {
				jjb.setOnlinePay(obj[6].toString());
			}
			jjbList.add(jjb);
		}
		
		PageBean<JiaoJieBan_HP> pb = new PageBean<JiaoJieBan_HP>();
		pb.setBeanList(jjbList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
	}
	
	@Override
	public SalesSummaryBean getSalesSummary(Long uId) throws SQLException {
		
		double salesTotalAmount = 0; // 销售总额 
		double salesTotalProfit = 0; // 销售总利润
		int salesCounts = 0; // 销售单数      
		double salesCash = 0; // 销售现金支付额     
		double salesBank = 0; // 销售银联卡支付额    
		double salesOnline = 0; // 销售在线支付额   
		double vipCharge = 0; // 会员充值        
		double vipDonate = 0;  // 会员赠送       
		int vipCounts = 0; // 会员单数        
		double vipCash = 0; // 会员现金支付额       
		double vipBank = 0; // 会员银联卡支付额      
		double vipOnline = 0; // 会员在线支付额     
		double cashTotal = 0; // 现金支付总额      
		double bankTotal = 0; // 银联卡支付总额     
		double onlineTotal = 0; // 现在支付总额    
		double summaryTotal = 0; // 销售加充值总额
		
		SalesSummaryBean ssb = new SalesSummaryBean();
		String sql = "";
		List<Object[]> list = null;
		Number number = null;
		
		/**
		 * 商品销售相关6个参数
		 */
		sql = "select count(DISTINCT(sa_serial_num)) from sale where store_id in(select s_id from store where u_id=?)";
		number = qr.query(sql, new ScalarHandler(), uId);
		salesCounts = number.intValue(); // 销售单数      
		
		sql = "select sum(sa_profit), SUM(sa_real_price) from sale where store_id in(select s_id from store where u_id=?)";
		list = qr.query(sql, new ArrayListHandler(), uId);
		if (list.get(0)[0] != null && list.get(0)[1] != null) {
			salesTotalProfit = Double.parseDouble(list.get(0)[0].toString());
			salesTotalAmount = Double.parseDouble(list.get(0)[1].toString());
			salesTotalAmount = Math.ceil(salesTotalAmount*100+.5)/100;
		}
		sql = "select SUM(sa_real_price) from sale where sa_type = 1 and store_id in(select s_id from store where u_id=?)";
		list = qr.query(sql, new ArrayListHandler(), uId);
		if (list.get(0)[0] != null) {
			salesCash = Double.parseDouble(list.get(0)[0].toString());
		}
		sql = "select SUM(sa_real_price) from sale where sa_type = 2 and store_id in(select s_id from store where u_id=?)";
		list = qr.query(sql, new ArrayListHandler(), uId);
		if (list.get(0)[0] != null) {
			salesBank = Double.parseDouble(list.get(0)[0].toString());
		}
		sql = "select SUM(sa_real_price) from sale where sa_type = 3 and store_id in(select s_id from store where u_id=?)";
		list = qr.query(sql, new ArrayListHandler(), uId);
		if (list.get(0)[0] != null) {
			salesOnline = Double.parseDouble(list.get(0)[0].toString());
		}
		/**
		 * 会员卡充值相关6个参数
		 */
		sql = "select count(DISTINCT(v_c_id)) from vip_consume_log where store_id in(select s_id from store where u_id=?)";
		number = qr.query(sql, new ScalarHandler(), uId);
		vipCounts = number.intValue(); // 会员卡单数      
		
		sql = "select sum(v_giving), SUM(v_payin_off) from vip_consume_log where store_id in(select s_id from store where u_id=?)";
		list = qr.query(sql, new ArrayListHandler(), uId);
		if (list.get(0)[0] != null && list.get(0)[1] != null) {
			vipDonate = Double.parseDouble(list.get(0)[0].toString());
			vipCharge = Double.parseDouble(list.get(0)[1].toString());
			vipCharge = Math.ceil(vipCharge*100+.5)/100;
		}
		sql = "select SUM(v_payin_off) from vip_consume_log where vtype = 1 and store_id in(select s_id from store where u_id=?)";
		list = qr.query(sql, new ArrayListHandler(), uId);
		if (list.get(0)[0] != null) {
			vipCash = Double.parseDouble(list.get(0)[0].toString());
		}
		sql = "select SUM(v_payin_off) from vip_consume_log where vtype = 2 and store_id in(select s_id from store where u_id=?)";
		list = qr.query(sql, new ArrayListHandler(), uId);
		if (list.get(0)[0] != null) {
			vipBank = Double.parseDouble(list.get(0)[0].toString());
		}
		sql = "select SUM(v_payin_off) from vip_consume_log where vtype = 3 and store_id in(select s_id from store where u_id=?)";
		list = qr.query(sql, new ArrayListHandler(), uId);
		if (list.get(0)[0] != null) {
			vipOnline = Double.parseDouble(list.get(0)[0].toString());
		}
		
		// 现金，银联卡，在线 小计3个参数
		cashTotal = salesCash + vipCash;
		bankTotal = salesBank + vipBank;
		onlineTotal = salesOnline +vipOnline;
		summaryTotal = salesTotalAmount + vipCharge;
		
		ssb.setSalesCounts(salesCounts); // 1
		ssb.setSalesTotalProfit(salesTotalProfit); // 2
		ssb.setSalesTotalAmount(salesTotalAmount); // 3
		ssb.setSalesCash(salesCash); // 4
		ssb.setSalesBank(salesBank); // 5
		ssb.setSalesOnline(salesOnline); // 6
		
		ssb.setVipCharge(vipCharge); // 7
		ssb.setVipDonate(vipDonate); // 8
		ssb.setVipCounts(vipCounts); // 9
		ssb.setVipCash(vipCash); // 10
		ssb.setVipBank(vipBank); // 11
		ssb.setVipOnline(vipOnline); // 12 
		
		ssb.setCashTotal(cashTotal); // 13
		ssb.setBankTotal(bankTotal); // 14
		ssb.setOnlineTotal(onlineTotal); // 15
		ssb.setSummaryTotal(summaryTotal); // 16
		
		return ssb;
	}

	@Override
	public SalesSummaryBean getSalesSummaryByCombination(String storeName, String beginTime, String endTime, Long uId) throws SQLException {
		double salesTotalAmount = 0; // 销售总额 
		double salesTotalProfit = 0; // 销售总利润
		int salesCounts = 0; // 销售单数      
		double salesCash = 0; // 销售现金支付额     
		double salesBank = 0; // 销售银联卡支付额    
		double salesOnline = 0; // 销售在线支付额   
		double vipCharge = 0; // 会员充值        
		double vipDonate = 0;  // 会员赠送       
		int vipCounts = 0; // 会员单数        
		double vipCash = 0; // 会员现金支付额       
		double vipBank = 0; // 会员银联卡支付额      
		double vipOnline = 0; // 会员在线支付额     
		double cashTotal = 0; // 现金支付总额      
		double bankTotal = 0; // 银联卡支付总额     
		double onlineTotal = 0; // 现在支付总额    
		double summaryTotal = 0; // 销售加充值总额
		
		SalesSummaryBean ssb = new SalesSummaryBean();
		String sql = "";
		List<Object[]> list = null;
		Number number = null;
		Long storeId = findStoreIdByStoreName(storeName);
		
		if (!beginTime.equals("")) {
			beginTime = StrToDate(beginTime);
		}
		if (!endTime.equals("")) {
			endTime = StrToDate(endTime);
		}
		
		/**
		 * 商品销售和会员卡充值相关12个参数
		 */
		if (storeName.equals("全部门店")) {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 1 1 1
					sql = "select count(DISTINCT(sa_serial_num)) from sale where store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), uId);
					salesCounts = number.intValue();
					
					sql = "select count(DISTINCT(v_c_id)) from vip_consume_log where store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), uId);
					vipCounts = number.intValue(); // 会员卡单数
				} else {
					// 1 1 0
					sql = "select count(DISTINCT(sa_serial_num)) from sale where sa_date<=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), endTime, uId);
					salesCounts = number.intValue();
					
					sql = "select count(DISTINCT(v_c_id)) from vip_consume_log where v_date<=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), endTime, uId);
					vipCounts = number.intValue(); // 会员卡单数
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "select count(DISTINCT(sa_serial_num)) from sale where sa_date>=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, uId);
					salesCounts = number.intValue();
					
					sql = "select count(DISTINCT(v_c_id)) from vip_consume_log where v_date>=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, uId);
					vipCounts = number.intValue(); // 会员卡单数
				} else {
					// 1 0 0
					sql = "select count(DISTINCT(sa_serial_num)) from sale where sa_date>=? and sa_date<=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, endTime, uId);
					salesCounts = number.intValue();
					
					sql = "select count(DISTINCT(v_c_id)) from vip_consume_log where v_date>=? and v_date<=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), beginTime, endTime, uId);
					vipCounts = number.intValue(); // 会员卡单数
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "select count(DISTINCT(sa_serial_num)) from sale where store_id=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), storeId, uId);
					salesCounts = number.intValue();
					
					sql = "select count(DISTINCT(v_c_id)) from vip_consume_log where store_id=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), storeId, uId);
					vipCounts = number.intValue(); // 会员卡单数
				} else {
					// 0 1 0
					sql = "select count(DISTINCT(sa_serial_num)) from sale where store_id=? and sa_date<=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), storeId, endTime, uId);
					salesCounts = number.intValue();
					
					sql = "select count(DISTINCT(v_c_id)) from vip_consume_log where store_id=? and v_date<=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), storeId, endTime, uId);
					vipCounts = number.intValue(); // 会员卡单数
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "select count(DISTINCT(sa_serial_num)) from sale where store_id=? and sa_date>=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), storeId, beginTime, uId);
					salesCounts = number.intValue();
					
					sql = "select count(DISTINCT(v_c_id)) from vip_consume_log where store_id=? and v_date>=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), storeId, beginTime, uId);
					vipCounts = number.intValue(); // 会员卡单数
				} else {
					// 0 0 0
					sql = "select count(DISTINCT(sa_serial_num)) from sale where store_id=? and sa_date>=? and sa_date<=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), storeId, beginTime, endTime,
							uId);
					salesCounts = number.intValue();
					
					sql = "select count(DISTINCT(v_c_id)) from vip_consume_log where store_id=? and v_date>=? and v_date<=? and store_id in(select s_id from store where u_id=?)";
					number = qr.query(sql, new ScalarHandler(), storeId, beginTime, endTime,
							uId);
					vipCounts = number.intValue(); // 会员卡单数
				}
			}
		}
		if (storeName.equals("全部门店")) {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 1 1 1
					sql = "select sum(sa_profit), SUM(sa_real_price) from sale where store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						salesTotalProfit = Double.parseDouble(list.get(0)[0].toString());
						salesTotalAmount = Double.parseDouble(list.get(0)[1].toString());
						salesTotalAmount = Math.ceil(salesTotalAmount*100+.5)/100;
					}
					sql = "select SUM(sa_real_price) from sale where sa_type = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), uId);
					if (list.get(0)[0] != null) {
						salesCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where sa_type = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), uId);
					if (list.get(0)[0] != null) {
						salesBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where sa_type = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), uId);
					if (list.get(0)[0] != null) {
						salesOnline = Double.parseDouble(list.get(0)[0].toString());
					}
					
					// --
					sql = "select sum(v_giving), SUM(v_payin_off) from vip_consume_log where store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						vipDonate = Double.parseDouble(list.get(0)[0].toString());
						vipCharge = Double.parseDouble(list.get(0)[1].toString());
						vipCharge = Math.ceil(vipCharge*100+.5)/100;
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where vtype = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), uId);
					if (list.get(0)[0] != null) {
						vipCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where vtype = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), uId);
					if (list.get(0)[0] != null) {
						vipBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where vtype = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), uId);
					if (list.get(0)[0] != null) {
						vipOnline = Double.parseDouble(list.get(0)[0].toString());
					}
				} else {
					// 1 1 0
					sql = "select sum(sa_profit), SUM(sa_real_price) from sale where sa_date<=? and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), endTime, uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						salesTotalProfit = Double.parseDouble(list.get(0)[0].toString());
						salesTotalAmount = Double.parseDouble(list.get(0)[1].toString());
						salesTotalAmount = Math.ceil(salesTotalAmount*100+.5)/100;
					}
					sql = "select SUM(sa_real_price) from sale where sa_date<=? and sa_type = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), endTime, uId);
					if (list.get(0)[0] != null) {
						salesCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where sa_date<=? and sa_type = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), endTime, uId);
					if (list.get(0)[0] != null) {
						salesBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where sa_date<=? and sa_type = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), endTime, uId);
					if (list.get(0)[0] != null) {
						salesOnline = Double.parseDouble(list.get(0)[0].toString());
					}

					// --
					sql = "select sum(v_giving), SUM(v_payin_off) from vip_consume_log where v_date<=? and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), endTime, uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						vipDonate = Double.parseDouble(list.get(0)[0].toString());
						vipCharge = Double.parseDouble(list.get(0)[1].toString());
						vipCharge = Math.ceil(vipCharge*100+.5)/100;
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where v_date<=? and vtype = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), endTime, uId);
					if (list.get(0)[0] != null) {
						vipCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where v_date<=? and vtype = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), endTime, uId);
					if (list.get(0)[0] != null) {
						vipBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where v_date<=? and vtype = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), endTime, uId);
					if (list.get(0)[0] != null) {
						vipOnline = Double.parseDouble(list.get(0)[0].toString());
					}
				}
			} else {
				if (endTime.equals("")) {
					// 1 0 1
					sql = "select sum(sa_profit), SUM(sa_real_price) from sale where sa_date>=? and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						salesTotalProfit = Double.parseDouble(list.get(0)[0].toString());
						salesTotalAmount = Double.parseDouble(list.get(0)[1].toString());
						salesTotalAmount = Math.ceil(salesTotalAmount*100+.5)/100;
					}
					sql = "select SUM(sa_real_price) from sale where sa_date>=? and sa_type = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, uId);
					if (list.get(0)[0] != null) {
						salesCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where sa_date>=? and sa_type = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, uId);
					if (list.get(0)[0] != null) {
						salesBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where sa_date>=? and sa_type = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, uId);
					if (list.get(0)[0] != null) {
						salesOnline = Double.parseDouble(list.get(0)[0].toString());
					}

					// --
					sql = "select sum(v_giving), SUM(v_payin_off) from vip_consume_log where v_date>=? and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						vipDonate = Double.parseDouble(list.get(0)[0].toString());
						vipCharge = Double.parseDouble(list.get(0)[1].toString());
						vipCharge = Math.ceil(vipCharge*100+.5)/100;
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where v_date>=? and vtype = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, uId);
					if (list.get(0)[0] != null) {
						vipCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where v_date>=? and vtype = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, uId);
					if (list.get(0)[0] != null) {
						vipBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where v_date>=? and vtype = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, uId);
					if (list.get(0)[0] != null) {
						vipOnline = Double.parseDouble(list.get(0)[0].toString());
					}
				} else {
					// 1 0 0
					sql = "select sum(sa_profit), SUM(sa_real_price) from sale where sa_date>=? and sa_date<=? and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						salesTotalProfit = Double.parseDouble(list.get(0)[0].toString());
						salesTotalAmount = Double.parseDouble(list.get(0)[1].toString());
						salesTotalAmount = Math.ceil(salesTotalAmount*100+.5)/100;
					}
					sql = "select SUM(sa_real_price) from sale where sa_date>=? and sa_date<=? and sa_type = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId);
					if (list.get(0)[0] != null) {
						salesCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where sa_date>=? and sa_date<=? and sa_type = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId);
					if (list.get(0)[0] != null) {
						salesBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where sa_date>=? and sa_date<=? and sa_type = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId);
					if (list.get(0)[0] != null) {
						salesOnline = Double.parseDouble(list.get(0)[0].toString());
					}

					// --
					sql = "select sum(v_giving), SUM(v_payin_off) from vip_consume_log where v_date>=? and v_date<=? and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						vipDonate = Double.parseDouble(list.get(0)[0].toString());
						vipCharge = Double.parseDouble(list.get(0)[1].toString());
						vipCharge = Math.ceil(vipCharge*100+.5)/100;
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where v_date>=? and v_date<=? and vtype = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId);
					if (list.get(0)[0] != null) {
						vipCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where v_date>=? and v_date<=? and vtype = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId);
					if (list.get(0)[0] != null) {
						vipBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where v_date>=? and v_date<=? and vtype = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), beginTime, endTime, uId);
					if (list.get(0)[0] != null) {
						vipOnline = Double.parseDouble(list.get(0)[0].toString());
					}
				}
			}
		} else {
			if (beginTime.equals("")) {
				if (endTime.equals("")) {
					// 0 1 1
					sql = "select sum(sa_profit), SUM(sa_real_price) from sale where store_id=? and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						salesTotalProfit = Double.parseDouble(list.get(0)[0].toString());
						salesTotalAmount = Double.parseDouble(list.get(0)[1].toString());
						salesTotalAmount = Math.ceil(salesTotalAmount*100+.5)/100;
					}
					sql = "select SUM(sa_real_price) from sale where store_id=? and sa_type = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, uId);
					if (list.get(0)[0] != null) {
						salesCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where store_id=? and sa_type = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, uId);
					if (list.get(0)[0] != null) {
						salesBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where store_id=? and sa_type = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, uId);
					if (list.get(0)[0] != null) {
						salesOnline = Double.parseDouble(list.get(0)[0].toString());
					}

					// --
					sql = "select sum(v_giving), SUM(v_payin_off) from vip_consume_log where store_id=? and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						vipDonate = Double.parseDouble(list.get(0)[0].toString());
						vipCharge = Double.parseDouble(list.get(0)[1].toString());
						vipCharge = Math.ceil(vipCharge*100+.5)/100;
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where store_id=? and vtype = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, uId);
					if (list.get(0)[0] != null) {
						vipCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where store_id=? and vtype = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, uId);
					if (list.get(0)[0] != null) {
						vipBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where store_id=? and vtype = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, uId);
					if (list.get(0)[0] != null) {
						vipOnline = Double.parseDouble(list.get(0)[0].toString());
					}
				} else {
					// 0 1 0
					sql = "select sum(sa_profit), SUM(sa_real_price) from sale where store_id=? and sa_date<=? and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, endTime, uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						salesTotalProfit = Double.parseDouble(list.get(0)[0].toString());
						salesTotalAmount = Double.parseDouble(list.get(0)[1].toString());
						salesTotalAmount = Math.ceil(salesTotalAmount*100+.5)/100;
					}
					sql = "select SUM(sa_real_price) from sale where store_id=? and sa_date<=? and sa_type = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, endTime, uId);
					if (list.get(0)[0] != null) {
						salesCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where store_id=? and sa_date<=? and sa_type = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, endTime, uId);
					if (list.get(0)[0] != null) {
						salesBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where store_id=? and sa_date<=? and sa_type = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, endTime, uId);
					if (list.get(0)[0] != null) {
						salesOnline = Double.parseDouble(list.get(0)[0].toString());
					}

					// --
					sql = "select sum(v_giving), SUM(v_payin_off) from vip_consume_log where store_id=? and v_date<=? and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, endTime, uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						vipDonate = Double.parseDouble(list.get(0)[0].toString());
						vipCharge = Double.parseDouble(list.get(0)[1].toString());
						vipCharge = Math.ceil(vipCharge*100+.5)/100;
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where store_id=? and v_date<=? and vtype = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, endTime, uId);
					if (list.get(0)[0] != null) {
						vipCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where store_id=? and v_date<=? and vtype = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, endTime, uId);
					if (list.get(0)[0] != null) {
						vipBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where store_id=? and v_date<=? and vtype = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, endTime, uId);
					if (list.get(0)[0] != null) {
						vipOnline = Double.parseDouble(list.get(0)[0].toString());
					}
				}
			} else {
				if (endTime.equals("")) {
					// 0 0 1
					sql = "select sum(sa_profit), SUM(sa_real_price) from sale where store_id=? and sa_date>=? and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						salesTotalProfit = Double.parseDouble(list.get(0)[0].toString());
						salesTotalAmount = Double.parseDouble(list.get(0)[1].toString());
						salesTotalAmount = Math.ceil(salesTotalAmount*100+.5)/100;
					}
					sql = "select SUM(sa_real_price) from sale where store_id=? and sa_date>=? and sa_type = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, uId);
					if (list.get(0)[0] != null) {
						salesCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where store_id=? and sa_date>=? and sa_type = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, uId);
					if (list.get(0)[0] != null) {
						salesBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where store_id=? and sa_date>=? and sa_type = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, uId);
					if (list.get(0)[0] != null) {
						salesOnline = Double.parseDouble(list.get(0)[0].toString());
					}

					// --
					sql = "select sum(v_giving), SUM(v_payin_off) from vip_consume_log where store_id=? and v_date>=? and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						vipDonate = Double.parseDouble(list.get(0)[0].toString());
						vipCharge = Double.parseDouble(list.get(0)[1].toString());
						vipCharge = Math.ceil(vipCharge*100+.5)/100;
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where store_id=? and v_date>=? and vtype = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, uId);
					if (list.get(0)[0] != null) {
						vipCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where store_id=? and v_date>=? and vtype = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, uId);
					if (list.get(0)[0] != null) {
						vipBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where store_id=? and v_date>=? and vtype = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, uId);
					if (list.get(0)[0] != null) {
						vipOnline = Double.parseDouble(list.get(0)[0].toString());
					}
				} else {
					// 0 0 0
					sql = "select sum(sa_profit), SUM(sa_real_price) from sale where store_id=? and sa_date>=? and sa_date<=? and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, endTime, uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						salesTotalProfit = Double.parseDouble(list.get(0)[0].toString());
						salesTotalAmount = Double.parseDouble(list.get(0)[1].toString());
						salesTotalAmount = Math.ceil(salesTotalAmount*100+.5)/100;
					}
					sql = "select SUM(sa_real_price) from sale where store_id=? and sa_date>=? and sa_date<=? and sa_type = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, endTime, uId);
					if (list.get(0)[0] != null) {
						salesCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where store_id=? and sa_date>=? and sa_date<=? and sa_type = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, endTime, uId);
					if (list.get(0)[0] != null) {
						salesBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(sa_real_price) from sale where store_id=? and sa_date>=? and sa_date<=? and sa_type = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, endTime, uId);
					if (list.get(0)[0] != null) {
						salesOnline = Double.parseDouble(list.get(0)[0].toString());
					}
					
					// --
					sql = "select sum(v_giving), SUM(v_payin_off) from vip_consume_log where store_id=? and v_date>=? and v_date<=? and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, endTime, uId);
					if (list.get(0)[0] != null && list.get(0)[1] != null) {
						vipDonate = Double.parseDouble(list.get(0)[0].toString());
						vipCharge = Double.parseDouble(list.get(0)[1].toString());
						vipCharge = Math.ceil(vipCharge*100+.5)/100;
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where store_id=? and v_date>=? and v_date<=? and vtype = 1 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, endTime, uId);
					if (list.get(0)[0] != null) {
						vipCash = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where store_id=? and v_date>=? and v_date<=? and vtype = 2 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, endTime, uId);
					if (list.get(0)[0] != null) {
						vipBank = Double.parseDouble(list.get(0)[0].toString());
					}
					sql = "select SUM(v_payin_off) from vip_consume_log where store_id=? and v_date>=? and v_date<=? and vtype = 3 and store_id in(select s_id from store where u_id=?)";
					list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, endTime, uId);
					if (list.get(0)[0] != null) {
						vipOnline = Double.parseDouble(list.get(0)[0].toString());
					}
				}
			}
		}
		
		// 现金，银联卡，在线 小计3个参数
		cashTotal = salesCash + vipCash;
		bankTotal = salesBank + vipBank;
		onlineTotal = salesOnline + vipOnline;
		summaryTotal = salesTotalAmount + vipCharge;

		ssb.setSalesCounts(salesCounts); // 1
		ssb.setSalesTotalProfit(salesTotalProfit); // 2
		ssb.setSalesTotalAmount(salesTotalAmount); // 3
		ssb.setSalesCash(salesCash); // 4
		ssb.setSalesBank(salesBank); // 5
		ssb.setSalesOnline(salesOnline); // 6

		ssb.setVipCharge(vipCharge); // 7
		ssb.setVipDonate(vipDonate); // 8
		ssb.setVipCounts(vipCounts); // 9
		ssb.setVipCash(vipCash); // 10
		ssb.setVipBank(vipBank); // 11
		ssb.setVipOnline(vipOnline); // 12

		ssb.setCashTotal(cashTotal); // 13
		ssb.setBankTotal(bankTotal); // 14
		ssb.setOnlineTotal(onlineTotal); // 15
		ssb.setSummaryTotal(summaryTotal); // 16

		return ssb;
	}

	/**
	* 字符串转换成日期毫秒数
	* @param str
	* @return String
	*/
	public static String StrToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (ParseException e) {
	    e.printStackTrace();
	   }
	   return ""+date.getTime();
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
	private String findEmpNameByEmpId(Long empId) throws SQLException {
		String sql = "select emp_name from employee where emp_id=?";
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), empId);
		if (list.size() > 0) {
			Object[] obj = list.get(0);
			return obj[0].toString();
		}
		return null;
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

	@Override
	public SalesSummaryBean getAllSalesSummary() throws SQLException {
		
		double salesTotalAmount = 0; // 销售总额 
		double salesTotalProfit = 0; // 销售总利润
		int salesCounts = 0; // 销售单数      
		double salesCash = 0; // 销售现金支付额     
		double salesBank = 0; // 销售银联卡支付额    
		double salesOnline = 0; // 销售在线支付额   
		double vipCharge = 0; // 会员充值        
		double vipDonate = 0;  // 会员赠送       
		int vipCounts = 0; // 会员单数        
		double vipCash = 0; // 会员现金支付额       
		double vipBank = 0; // 会员银联卡支付额      
		double vipOnline = 0; // 会员在线支付额     
		double cashTotal = 0; // 现金支付总额      
		double bankTotal = 0; // 银联卡支付总额     
		double onlineTotal = 0; // 现在支付总额    
		double summaryTotal = 0; // 销售加充值总额
		
		SalesSummaryBean ssb = new SalesSummaryBean();
		String sql = "";
		List<Object[]> list = null;
		Number number = null;
		
		/**
		 * 商品销售相关6个参数
		 */
		sql = "select count(DISTINCT(sa_serial_num)) from sale";
		number = qr.query(sql, new ScalarHandler());
		salesCounts = number.intValue(); // 销售单数      
		
		sql = "select sum(sa_profit), SUM(sa_real_price) from sale";
		list = qr.query(sql, new ArrayListHandler());
		if (list.get(0)[0] != null && list.get(0)[1] != null) {
			salesTotalProfit = Double.parseDouble(list.get(0)[0].toString());
			salesTotalAmount = Double.parseDouble(list.get(0)[1].toString());
			salesTotalAmount = Math.ceil(salesTotalAmount*100+.5)/100;
		}
		sql = "select SUM(sa_real_price) from sale where sa_type = 1";
		list = qr.query(sql, new ArrayListHandler());
		if (list.get(0)[0] != null) {
			salesCash = Double.parseDouble(list.get(0)[0].toString());
		}
		sql = "select SUM(sa_real_price) from sale where sa_type = 2";
		list = qr.query(sql, new ArrayListHandler());
		if (list.get(0)[0] != null) {
			salesBank = Double.parseDouble(list.get(0)[0].toString());
		}
		sql = "select SUM(sa_real_price) from sale where sa_type = 3";
		list = qr.query(sql, new ArrayListHandler());
		if (list.get(0)[0] != null) {
			salesOnline = Double.parseDouble(list.get(0)[0].toString());
		}
		/**
		 * 会员卡充值相关6个参数
		 */
		sql = "select count(DISTINCT(v_c_id)) from vip_consume_log";
		number = qr.query(sql, new ScalarHandler());
		vipCounts = number.intValue(); // 会员卡单数      
		
		sql = "select sum(v_giving), SUM(v_payin_off) from vip_consume_log";
		list = qr.query(sql, new ArrayListHandler());
		if (list.get(0)[0] != null && list.get(0)[1] != null) {
			vipDonate = Double.parseDouble(list.get(0)[0].toString());
			vipCharge = Double.parseDouble(list.get(0)[1].toString());
			vipCharge = Math.ceil(vipCharge*100+.5)/100;
		}
		sql = "select SUM(v_payin_off) from vip_consume_log where vtype = 1";
		list = qr.query(sql, new ArrayListHandler());
		if (list.get(0)[0] != null) {
			vipCash = Double.parseDouble(list.get(0)[0].toString());
		}
		sql = "select SUM(v_payin_off) from vip_consume_log where vtype = 2";
		list = qr.query(sql, new ArrayListHandler());
		if (list.get(0)[0] != null) {
			vipBank = Double.parseDouble(list.get(0)[0].toString());
		}
		sql = "select SUM(v_payin_off) from vip_consume_log where vtype = 3";
		list = qr.query(sql, new ArrayListHandler());
		if (list.get(0)[0] != null) {
			vipOnline = Double.parseDouble(list.get(0)[0].toString());
		}
		
		// 现金，银联卡，在线 小计3个参数
		cashTotal = salesCash + vipCash;
		bankTotal = salesBank + vipBank;
		onlineTotal = salesOnline +vipOnline;
		summaryTotal = salesTotalAmount + vipCharge;
		
		ssb.setSalesCounts(salesCounts); // 1
		ssb.setSalesTotalProfit(salesTotalProfit); // 2
		ssb.setSalesTotalAmount(salesTotalAmount); // 3
		ssb.setSalesCash(salesCash); // 4
		ssb.setSalesBank(salesBank); // 5
		ssb.setSalesOnline(salesOnline); // 6
		
		ssb.setVipCharge(vipCharge); // 7
		ssb.setVipDonate(vipDonate); // 8
		ssb.setVipCounts(vipCounts); // 9
		ssb.setVipCash(vipCash); // 10
		ssb.setVipBank(vipBank); // 11
		ssb.setVipOnline(vipOnline); // 12 
		
		ssb.setCashTotal(cashTotal); // 13
		ssb.setBankTotal(bankTotal); // 14
		ssb.setOnlineTotal(onlineTotal); // 15
		ssb.setSummaryTotal(summaryTotal); // 16
		
		return ssb;
	}

	@Override
	public SalesSummaryBean getAllSalesSummaryByCombination(String storeName, String beginTime, String endTime)
			throws SQLException {
		double salesTotalAmount = 0; // 销售总额 
		double salesTotalProfit = 0; // 销售总利润
		int salesCounts = 0; // 销售单数      
		double salesCash = 0; // 销售现金支付额     
		double salesBank = 0; // 销售银联卡支付额    
		double salesOnline = 0; // 销售在线支付额   
		double vipCharge = 0; // 会员充值        
		double vipDonate = 0;  // 会员赠送       
		int vipCounts = 0; // 会员单数        
		double vipCash = 0; // 会员现金支付额       
		double vipBank = 0; // 会员银联卡支付额      
		double vipOnline = 0; // 会员在线支付额     
		double cashTotal = 0; // 现金支付总额      
		double bankTotal = 0; // 银联卡支付总额     
		double onlineTotal = 0; // 现在支付总额    
		double summaryTotal = 0; // 销售加充值总额
		
		SalesSummaryBean ssb = new SalesSummaryBean();
		String sql = "";
		List<Object[]> list = null;
		Number number = null;
		Long storeId = findStoreIdByStoreName(storeName);
		
		if (!beginTime.equals("")) {
			beginTime = StrToDate(beginTime);
		}
		if (!endTime.equals("")) {
			endTime = StrToDate(endTime);
		}
		
		/**
		 * 商品销售和会员卡充值相关12个参数
		 */
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		StringBuilder vipWhereSql = new StringBuilder(" where 1=1");
		List<Object> params = new ArrayList<Object>();
		
		if (!storeName.equals("全部门店")) {
			whereSql.append(" and store_id=?");
			vipWhereSql.append(" and store_id=?");
			params.add(storeId);
		}
		if (beginTime != null && !beginTime.trim().isEmpty()) {
			whereSql.append(" and sa_date>=?");
			vipWhereSql.append(" and v_date>=?");
			params.add(beginTime);
		}
		if (endTime != null && !endTime.trim().isEmpty()) {
			whereSql.append(" and sa_date<=?");
			vipWhereSql.append(" and v_date<=?");
			params.add(endTime);
		}
		
		// 0 0 0
		sql = "select count(DISTINCT(sa_serial_num)) from sale" + whereSql.toString();
		number = qr.query(sql, new ScalarHandler(), params.toArray());
		salesCounts = number.intValue();
		
		sql = "select count(DISTINCT(v_c_id)) from vip_consume_log" + vipWhereSql.toString();
		number = qr.query(sql, new ScalarHandler(), params.toArray());
		vipCounts = number.intValue(); // 会员卡单数
		
		
		// 0 0 0
		sql = "select sum(sa_profit), SUM(sa_real_price) from sale" + whereSql.toString();
		list = qr.query(sql, new ArrayListHandler(), params.toArray());
		if (list.get(0)[0] != null && list.get(0)[1] != null) {
			salesTotalProfit = Double.parseDouble(list.get(0)[0].toString());
			salesTotalAmount = Double.parseDouble(list.get(0)[1].toString());
			salesTotalAmount = Math.ceil(salesTotalAmount*100+.5)/100;
		}
		sql = "select SUM(sa_real_price) from sale" + whereSql.toString() + " and sa_type = 1";
		list = qr.query(sql, new ArrayListHandler(), params.toArray());
		if (list.get(0)[0] != null) {
			salesCash = Double.parseDouble(list.get(0)[0].toString());
		}
		sql = "select SUM(sa_real_price) from sale" + whereSql.toString() + " and sa_type = 2";
		list = qr.query(sql, new ArrayListHandler(), params.toArray());
		if (list.get(0)[0] != null) {
			salesBank = Double.parseDouble(list.get(0)[0].toString());
		}
		sql = "select SUM(sa_real_price) from sale" + whereSql.toString() + " and sa_type = 3";
		list = qr.query(sql, new ArrayListHandler(), params.toArray());
		if (list.get(0)[0] != null) {
			salesOnline = Double.parseDouble(list.get(0)[0].toString());
		}
		
		// --
		sql = "select sum(v_giving), SUM(v_payin_off) from vip_consume_log" + vipWhereSql.toString();
		list = qr.query(sql, new ArrayListHandler(), params.toArray());
		if (list.get(0)[0] != null && list.get(0)[1] != null) {
			vipDonate = Double.parseDouble(list.get(0)[0].toString());
			vipCharge = Double.parseDouble(list.get(0)[1].toString());
			vipCharge = Math.ceil(vipCharge*100+.5)/100;
		}
		sql = "select SUM(v_payin_off) from vip_consume_log" + vipWhereSql.toString() + " and vtype = 1";
		list = qr.query(sql, new ArrayListHandler(), params.toArray());
		if (list.get(0)[0] != null) {
			vipCash = Double.parseDouble(list.get(0)[0].toString());
		}
		sql = "select SUM(v_payin_off) from vip_consume_log" + vipWhereSql.toString() + " and vtype = 2";
		list = qr.query(sql, new ArrayListHandler(), params.toArray());
		if (list.get(0)[0] != null) {
			vipBank = Double.parseDouble(list.get(0)[0].toString());
		}
		sql = "select SUM(v_payin_off) from vip_consume_log" + vipWhereSql.toString() + " and vtype = 3";
		list = qr.query(sql, new ArrayListHandler(), params.toArray());
		if (list.get(0)[0] != null) {
			vipOnline = Double.parseDouble(list.get(0)[0].toString());
		}
		
		// 现金，银联卡，在线 小计3个参数
		cashTotal = salesCash + vipCash;
		bankTotal = salesBank + vipBank;
		onlineTotal = salesOnline + vipOnline;
		summaryTotal = salesTotalAmount + vipCharge;

		ssb.setSalesCounts(salesCounts); // 1
		ssb.setSalesTotalProfit(salesTotalProfit); // 2
		ssb.setSalesTotalAmount(salesTotalAmount); // 3
		ssb.setSalesCash(salesCash); // 4
		ssb.setSalesBank(salesBank); // 5
		ssb.setSalesOnline(salesOnline); // 6

		ssb.setVipCharge(vipCharge); // 7
		ssb.setVipDonate(vipDonate); // 8
		ssb.setVipCounts(vipCounts); // 9
		ssb.setVipCash(vipCash); // 10
		ssb.setVipBank(vipBank); // 11
		ssb.setVipOnline(vipOnline); // 12

		ssb.setCashTotal(cashTotal); // 13
		ssb.setBankTotal(bankTotal); // 14
		ssb.setOnlineTotal(onlineTotal); // 15
		ssb.setSummaryTotal(summaryTotal); // 16

		return ssb;
	}

	@Override
	public PageBean<JiaoJieBan_HP> findAllShiftingDutyRecordByCombination(String storeName, String beginTime,
			String endTime, int pc) throws SQLException {
		if (!beginTime.equals("")) {
			beginTime = StrToDate(beginTime);
		}
		if (!endTime.equals("")) {
			endTime = StrToDate(endTime);
		}
		
		int ps = PageConstants.SALE_PAGE_SIZE;
		
		StringBuilder cntSql = new StringBuilder("SELECT COUNT(DISTINCT(id)) FROM jiaojieban");
		StringBuilder whereSql = new StringBuilder(" where 1=1");
		StringBuilder selectSql = new StringBuilder("SELECT start_time, end_time, saler_id, total_all, cash_pay, bank_pay, online_pay FROM jiaojieban");
		List<Object> params = new ArrayList<Object>();
		
		if (!storeName.equals("全部门店")) {
//			whereSql.append(" and store_id=?");
			whereSql.append(" and ? IN (SELECT store_id FROM employee)");
			
			params.add(findStoreIdByStoreName(storeName));
		}
		if (beginTime != null && !beginTime.trim().isEmpty()) {
			whereSql.append(" and start_time>=?");
			params.add(beginTime);
		}
		if (endTime != null && !endTime.trim().isEmpty()) {
			whereSql.append(" and end_time<=?");
			params.add(endTime);
		}
		
		int tr = 0;
		Number number = (Number) qr.query(cntSql.toString()+whereSql.toString(), new ScalarHandler(), params.toArray());
		tr = number.intValue();
		System.out.println("符合条件的记录条数: " + tr);
		System.out.println("符合条件的记录条数sql: " + cntSql.toString()+whereSql.toString());
		
		String sql = selectSql.toString()+whereSql.toString() + " limit ?,?";
		params.add((pc-1)*ps);
		params.add(ps);
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), params.toArray());
		System.out.println("查询语句sql: " + sql);
		
		List<JiaoJieBan_HP> jjbList = new ArrayList<JiaoJieBan_HP>();
		for (Object[] obj : list) {
			JiaoJieBan_HP jjb = new JiaoJieBan_HP();
			Employee employee = new Employee();
			if (obj[0] != null) {
				jjb.setStartTime(msecToDateTimeStr(obj[0].toString()));
			}
			if (obj[1] != null) {
				jjb.setEndTime(msecToDateTimeStr(obj[1].toString()));
			}
			if (obj[2] != null) {
				employee.setEmpName(findEmpNameByEmpId(Long.valueOf(obj[2].toString())));
				jjb.setEmployee(employee);
			}
			if (obj[3] != null) {
				jjb.setTotalAll(obj[3].toString());
			}
			if (obj[4] != null) {
				jjb.setCashPay(obj[4].toString());
			}
			if (obj[5] != null) {
				jjb.setBankPay(obj[5].toString());
			}
			if (obj[6] != null) {
				jjb.setOnlinePay(obj[6].toString());
			}
			jjbList.add(jjb);
		}
		
		PageBean<JiaoJieBan_HP> pb = new PageBean<JiaoJieBan_HP>();
		pb.setBeanList(jjbList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
	}

	@Override
	public PageBean<XJSZBean> findAllCashDetailsByCombination(String storeName, String beginTime, String endTime,
			int pc) throws SQLException {
		if (!beginTime.equals("")) {
			beginTime = StrToDate(beginTime);
			System.out.println(beginTime);
		}
		if (!endTime.equals("")) {
			endTime = StrToDate(endTime);
			System.out.println(endTime);
		}
		
		int ps = PageConstants.SALE_PAGE_SIZE;
		
		StringBuilder cntSql = new StringBuilder("SELECT COUNT(*) FROM sale sa, flow f");
		StringBuilder whereSql = new StringBuilder(" where 1=1 and sa.sa_serial_num = f.flows");
		StringBuilder selectSql = new StringBuilder("SELECT sa.sa_date, sa.sa_saler_id, f.cash, f.zhaoxian, f.yingfu FROM sale sa, flow f");
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
		
		int tr = 0;
		Number number = (Number) qr.query(cntSql.toString()+whereSql.toString(), new ScalarHandler(), params.toArray());
		tr = number.intValue();
		System.out.println("符合条件的记录条数: " + tr);
		System.out.println("符合条件的记录条数sql: " + cntSql.toString()+whereSql.toString());
		
		String sql = selectSql.toString()+whereSql.toString() + " limit ?,?";
		params.add((pc-1)*ps);
		params.add(ps);
		List<Object[]> list = qr.query(sql, new ArrayListHandler(), params.toArray());
		System.out.println("查询语句sql: " + sql);
		
//		// 0 0 0
//		sql = "SELECT COUNT(*) FROM sale sa, flow f WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND sa.sa_serial_num = f.flows";
//		number = qr.query(sql, new ScalarHandler(), storeId, beginTime, endTime);
//		tr = number.intValue();
//		System.out.println("0 0 0 : " + tr);
//
//		List<Object[]> list = null;
//
//		// 0 0 0
//		sql = "SELECT sa.sa_date, sa.sa_saler_id, f.cash, f.zhaoxian, f.yingfu FROM sale sa, flow f WHERE sa.store_id=? AND sa.sa_date>=? AND sa.sa_date<=? AND sa.sa_serial_num = f.flows limit ?,?";
//		System.out.println("0 0 0 查询");
//		list = qr.query(sql, new ArrayListHandler(), storeId, beginTime, endTime, (pc - 1) * ps, ps);
		
		List<XJSZBean> xjszList = new ArrayList<XJSZBean>();
		for (Object[] obj : list) {
			XJSZBean xjsz = new XJSZBean();
			Employee employee = new Employee();
			if (obj[0] != null) {
				xjsz.setSaDate(msecToDateTimeStr(obj[0].toString()));
			}
			if (obj[1] != null) {
				employee.setEmpName(findEmpNameByEmpId(Long.valueOf(obj[1].toString())));
				xjsz.setEmployee(employee);
			}
			if (obj[2] != null) {
				xjsz.setfCash(obj[2].toString());
			}
			if (obj[3] != null) {
				xjsz.setfZhaoXian(obj[3].toString());
			}
			if (obj[4] != null) {
				xjsz.setfYingFu(obj[4].toString());
			}
			xjszList.add(xjsz);
		}
		
		PageBean<XJSZBean> pb = new PageBean<XJSZBean>();
		pb.setBeanList(xjszList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		
		return pb;
	}
	
}
