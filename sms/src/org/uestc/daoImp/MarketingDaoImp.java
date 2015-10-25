package org.uestc.daoImp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.uestc.dao.MarketingDao;
import org.uestc.util.SqlHelper;

import com.uestc.bean.MarketingFunction;
import com.uestc.bean.MarketingInit;

public class MarketingDaoImp implements MarketingDao {

	
	
	@SuppressWarnings("unused")
	@Override
	public ArrayList<MarketingInit> getBeanList(String sql) {
		// TODO Auto-generated method stub
		ArrayList<MarketingInit> list = new ArrayList<MarketingInit>();
		ArrayList<Map> mlist = SqlHelper.findAll(sql);
		for(Iterator<Map> it = mlist.iterator(); it.hasNext();){
			Map m  = it.next();
			MarketingInit mk = new MarketingInit();
			mk.setActiveId(m.get("d_id")+"");
			mk.setD_start_date((String)m.get("d_start_date"));
			mk.setD_stop_date((String)m.get("d_end_date"));
			mk.setName((String)m.get("d_name"));
			String type = "";
			String ctype = m.get("d_type")+"";
			
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
			mk.setActiveId(m.get("d_id")+"");
			mk.setD_start_date((String)m.get("d_start_date"));
			mk.setD_stop_date((String)m.get("d_end_date"));
			mk.setName((String)m.get("d_name"));
			String type = "";
			String ctype = m.get("d_type")+"";
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

}
