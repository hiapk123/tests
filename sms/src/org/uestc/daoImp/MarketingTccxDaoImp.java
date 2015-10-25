package org.uestc.daoImp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.uestc.dao.MarketingTccxDao;
import org.uestc.util.SqlHelper;

import com.uestc.bean.MarketingFunction;
import com.uestc.bean.MarketingHgcxBean;
import com.uestc.bean.MarketingInit;
import com.uestc.bean.MarketingTccxBean;

public class MarketingTccxDaoImp implements MarketingTccxDao {

	@Override
	public ArrayList<MarketingInit> getBeanList(String sql) {
		// TODO Auto-generated method stub
		ArrayList<MarketingInit> list = new ArrayList<MarketingInit>();
		ArrayList<Map> mlist = SqlHelper.findAll(sql);
		for(Iterator<Map> it = mlist.iterator(); it.hasNext();){
			Map m  = it.next();
			MarketingInit mk = new MarketingInit();
			mk.setActiveId(m.get("p_id")+"");
			mk.setD_start_date((String)m.get("p_start_date"));
			mk.setD_stop_date((String)m.get("p_end_date"));
			mk.setName((String)m.get("p_name"));
			String type = "";
			String ctype = m.get("p_type")+"";
			
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
		return null;
	}

	@Override
	public ArrayList<MarketingTccxBean> getXGBeanList(String sql) {
		// TODO Auto-generated method stub
	   ArrayList<MarketingTccxBean> list = new ArrayList<MarketingTccxBean>();
		ArrayList<Map> mlist = SqlHelper.findAll(sql);
		for(Iterator<Map> it = mlist.iterator(); it.hasNext();){
			Map m  = it.next();
			MarketingTccxBean mk = new MarketingTccxBean();
			mk.setName(m.get("p_name")+"");
			mk.setStart_time(m.get("p_start_date")+"");
			mk.setEnd_time(m.get("p_end_date")+"");
			mk.setP_price(m.get("p_price")+"");
			mk.setBarcodes(""+m.get("p_goods_list"));
			mk.setCounts(""+m.get("p_count_list"));
			mk.setPackage_name(""+m.get("package_name"));
			
			String type = ""+m.get("p_type");
			
			
			mk.setType(type);
			String bar_Str = m.get("p_goods_list")+"";
			String cnt_Str = m.get("p_count_list")+"";
			List<String> bar_list = getGoodNamesByBarCode(bar_Str);
			List<String> cnt_list = Arrays.asList(cnt_Str.split(","));
			mk.setName_list(bar_list);
			mk.setCount_list(cnt_list);
			list.add(mk);
		}
		return list;
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
