package org.uestc.daoImp;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.uestc.dao.YYGKDao;

import com.uestc.bean.SalesSummaryBean;
import com.uestc.bean.Store;

public class YYGKDaoImp implements YYGKDao {

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
