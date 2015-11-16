package org.uestc.daoImp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.uestc.dao.MarketingDao;
import org.uestc.dao.MarketingDzcxDao;
import org.uestc.util.SqlHelper;

import com.uestc.bean.MarketingDzcxBean;
import com.uestc.bean.MarketingFunction;
import com.uestc.bean.MarketingInit;

public class MaDzcxDaoImp  implements MarketingDzcxDao  {

	
	
	@SuppressWarnings("unused")
	public ArrayList<MarketingInit> getBeanList(String sql) {
		// TODO Auto-generated method stub
		ArrayList<MarketingInit> list = new ArrayList<MarketingInit>();
		ArrayList<Map> mlist = SqlHelper.findAll(sql);
		for(Iterator<Map> it = mlist.iterator(); it.hasNext();){
			Map m  = it.next();
			MarketingInit mk = new MarketingInit();
			mk.setActiveId(m.get("e_id")+"");
			mk.setD_start_date((String)m.get("e_start_date"));
			mk.setD_stop_date((String)m.get("e_end_date"));
			mk.setName((String)m.get("e_name"));
			String type = "";
			String ctype = m.get("e_type")+"";
			
			if(ctype.equals("1")||ctype.equals("3")||ctype.equals("5")||ctype.equals("7")){
				type += "实体店 ";
			}
			if(ctype.equals("2")||ctype.equals("3")||ctype.equals("6")||ctype.equals("7")){
				type += "网店 ";
			}
			if(ctype.equals("4")||ctype.equals("6")||ctype.equals("5")||ctype.equals("7")){
				type += "会员专属";
			}
			String coupons = "";
			String c = m.get("coupons_id")+"";
			if(c.equals("null")){
				coupons  = "立即捆绑";
			}else{
				String getcouname = "select c_name from coupons where c_id = "+c;
				ArrayList<Map> clist = SqlHelper.findAll(getcouname);
				if(clist.size()!=0){
					coupons = (String)clist.get(0).get("c_name");
				}
			}
			mk.setCoupons(coupons);
			mk.setType(type);
			list.add(mk);
		}
		return list;
	}

	@Override
	public ArrayList<MarketingFunction> getMarketFunctionBeanList(String sql) {
		// TODO Auto-generated method stub
		ArrayList<MarketingFunction> list = new ArrayList<MarketingFunction>();
		ArrayList<Map> mlist = SqlHelper.findAll(sql);
		for(Iterator<Map> it = mlist.iterator(); it.hasNext();){
			Map m  = it.next();
			MarketingFunction mk = new MarketingFunction();
			mk.setActiveId(m.get("e_id")+"");
			mk.setD_start_date((String)m.get("e_start_date"));
			mk.setD_stop_date((String)m.get("e_end_date"));
			mk.setName((String)m.get("e_name"));
			String type = "";
			String ctype = m.get("e_type")+"";
			mk.setD_type(ctype);
			if(ctype.equals("1")||ctype.equals("3")||ctype.equals("5")||ctype.equals("7")){
				type += "实体店 ";
			}
			if(ctype.equals("2")||ctype.equals("3")||ctype.equals("6")||ctype.equals("7")){
				type += "网店 ";
			}
			if(ctype.equals("4")||ctype.equals("6")||ctype.equals("5")||ctype.equals("7")){
				type += "会员专属";
			}
			String coupons = "";
			String c = m.get("coupons_id")+"";
			if(c.equals("null")){
				coupons  = "立即捆绑";
			}else{
				String getcouname = "select c_name from coupons where c_id = "+c;
				ArrayList<Map> clist = SqlHelper.findAll(getcouname);
				if(clist.size()!=0){
					coupons = (String)clist.get(0).get("c_name");
				}
			}
			mk.setCoupons(coupons);
			mk.setType(type);
			mk.setDiscount((String)m.get("d_discount"));
			mk.setG_id((String) m.get("g_id"));
			String sql_getgood = "select * from goods where g_barcode = "+m.get("g_id");
			ArrayList<Map> glist = SqlHelper.findAll(sql_getgood);
			if(glist.size()!=0){
				Map g = glist.get(0);
				String sql_cate = "select c_name from category where c_id ="+g.get("c_id");
				
				ArrayList<Map> clist = SqlHelper.findAll(sql_cate);
				if(clist.size()!=0){
					Map ca = clist.get(0);
					mk.setC_name((String)ca.get("c_name"));
				}else {
					mk.setC_name("");
				}
				mk.setG_price((String)g.get("g_sale_price"));
				mk.setG_name((String)g.get("g_name"));
			}
			
			
			list.add(mk);
		}
		return list;
	}
	public ArrayList<MarketingDzcxBean> getXGBeanList(String sql_getActive) {
		// TODO Auto-generated method stub
		ArrayList <MarketingDzcxBean> mb = new ArrayList<MarketingDzcxBean>();
		ArrayList<Map> mlist=  SqlHelper.findAll(sql_getActive);
		for(Iterator<Map> it = mlist.iterator(); it.hasNext();){
			Map m = it.next();
			MarketingDzcxBean   mBean = new MarketingDzcxBean();
			
			String buylist = (String)m.get("e_buy_list");
			String buycount = (String)m.get("e_buy_count");
			String dList = (String)m.get("e_giving");
			String dcount = (String)m.get("e_giving_count");
			String active_name = (String)m.get("e_name");
			String start_time = (String)m.get("e_start_date");
			String end_time = (String)m.get("e_end_date");
			String type = m.get("e_type")+"";
		    
			
			ArrayList<String> gd_names = getGoodNamesByBarCode(dList);
			ArrayList<String> gm_names = getGoodNamesByBarCode(buylist);
			List<String> gm_counts = Arrays.asList(buycount.split(","));
			List<String> gd_counts = Arrays.asList(dcount.split(","));
			
			mBean.setGd_ids(dList);
			mBean.setGd_cnt(dcount);
			mBean.setGm_ids(buylist);
			mBean.setGm_cnt(buycount);
			mBean.setGm_names(gm_names);
			mBean.setGm_counts(gm_counts);
			mBean.setGd_name(gd_names);
			mBean.setGd_counts(gd_counts);
			mBean.setActive_name(active_name);
			mBean.setStart_time(start_time);
			mBean.setEnd_time(end_time);
			mBean.setType(type);
			
			mb.add(mBean);
		}
		
		return mb;
	}
	
	private ArrayList<String> getGoodNamesByBarCode(String ids){
		//ArrayList<String> names = new ArrayList<String>();
		List<String> gm_ids = Arrays.asList(ids.split(","));
		ArrayList<String> gm_names = new ArrayList<String>();
		for(Iterator<String> gmit = gm_ids.iterator(); gmit.hasNext();){
			String g_id = gmit.next();
			String sql_gm_name = "select g_name from goods where g_barcode  = "+g_id;
			
			ArrayList<Map> g_map = SqlHelper.findAll(sql_gm_name);
			if(g_map.size()!=0){
				gm_names.add((String)g_map.get(0).get("g_name"));
			}
			
		}
		return gm_names;
	}


}
