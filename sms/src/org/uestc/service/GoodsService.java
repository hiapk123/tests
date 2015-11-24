package org.uestc.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uestc.bean.Category;
import com.uestc.bean.CommissionRule;
import com.uestc.bean.IntegralRule;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;
import com.uestc.bean.Vip;

public interface GoodsService {

	public List<Object[]> findStoreByUserID(int uid);

	
	
	public List<Object[]> goodssearch(int sid, int currentPage,String key, String c_name,String g_del);
   public List<Object[]> toExcel(int s_id);
   public void addgood(String s_id,String s_name, String g_name, String g_del, String g_stock_num, String g_sale_price,
			String g_pur_price, String c_name, String g_barcode, String g_pm, String g_stock_max, String g_trade_price, 
			String g_prod_date, String zdy1, String zdy3, String s_name2, String g_stock_min, String vip_id,
			String g_vip_price, String g_giq, String zdy2, String zdy4, String g_qd_min, String g_cl_min,
			String g_stock_nor, String g_flag2, String g_best, String g_sale_nor, String g_info,
			String g_img_path, String g_integral,String c_id, String unit_id, String g_unit, String g_howmuch);
  
   public void deletegood(int g_id);
   public void fuzhi(int s_id1,int s_id2);
   public void kuaisuluru(int s_id,String g_barcode,String g_name,String c_name,String g_pur_price,String g_sale_price,String g_stock_num,String c_id,String s_name);

 
   public int getTotalSize(int store,String key,String c_name,String g_del);

List<Object[]> upsort(int sid,int currentPage, String sorted,String key,String c_name,String g_del);
List<Object[]> downsort(int sid,int currentPage, String sorted,String key,String c_name,String g_del);



public void editgood(String s_name, String g_name, String g_del, String g_stock_num, String g_sale_price,
		String g_pur_price, String c_name, String g_barcode, String g_pm, String g_stock_max, String g_trade_price,
		String g_prod_date, String zdy1, String zdy3, String su_name, String g_stock_min, String vip_id,
		String g_vip_price, String g_giq, String zdy2, String zdy4, String g_qd_min, String g_cl_min,
		String g_stock_nor, String g_flag, String g_best, String g_sale_nor, String g_info, String g_img_path,
		int g_id, String g_integral, String c_id,String g_unit,String g_howmuch,String unit_id);










}
