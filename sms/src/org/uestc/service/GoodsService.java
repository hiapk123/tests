package org.uestc.service;

import java.util.List;

import com.uestc.bean.Category;
import com.uestc.bean.CommissionRule;
import com.uestc.bean.IntegralRule;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;
import com.uestc.bean.Vip;

public interface GoodsService {

	public List<Object[]> findStoreByUserID(int uid);

	public List<Object[]> goodssearch(int sid, int currentPage);

   public void addgood(int s_id,String s_name, String g_name, int g_flag, String g_stock_num, String g_sale_price,
			String g_pur_price, String c_name, String g_barcode);
   public void editgood(int s_id,String s_name, String g_name, String g_stock_num, String g_sale_price,
			String g_pur_price, String c_name, String g_barcode,int g_id);
   public void deletegood(int g_id);
   
   public int getTotalSize(int store);

}
