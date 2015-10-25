package org.uestc.daoImp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.uestc.dao.MarketingMefxDao;
import org.uestc.util.SqlHelper;

import com.uestc.bean.MarketingFunction;
import com.uestc.bean.MarketingInit;
import com.uestc.bean.MarketingMefxBean;
import com.uestc.bean.MarketingTccxBean;

public class MarketingMefxDaoImp implements MarketingMefxDao {

	@Override
	public ArrayList<MarketingInit> getBeanList(String sql) {
		// TODO Auto-generated method stub
		ArrayList<MarketingInit> list = new ArrayList<MarketingInit>();
		ArrayList<Map> mlist = SqlHelper.findAll(sql);
		for(Iterator<Map> it = mlist.iterator(); it.hasNext();){
			Map m  = it.next();
			MarketingInit mk = new MarketingInit();
			mk.setActiveId(m.get("c_id")+"");
			mk.setD_start_date((String)m.get("c_start_date"));
			mk.setD_stop_date((String)m.get("c_end_date"));
			mk.setName((String)m.get("c_name"));
			mk.setC_num(""+m.get("c_num"));
			mk.setBarcode(""+m.get("c_no"));
			String type = "-";
			String ctype = m.get("c_only_vip")+"";
			
			if(ctype.equals("1")){
				type = "会员专属";
			}
			
			String coupons = "";
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
	public ArrayList<MarketingMefxBean> getXGBeanList(String sql) {
		// TODO Auto-generated method stub
	   ArrayList<MarketingMefxBean> list = new ArrayList<MarketingMefxBean>();
		ArrayList<Map> mlist = SqlHelper.findAll(sql);
		for(Iterator<Map> it = mlist.iterator(); it.hasNext();){
			Map m  = it.next();
			MarketingMefxBean mk = new MarketingMefxBean();
			mk.setActive_id(""+m.get("c_id"));
			mk.setName(m.get("c_name")+"");
			mk.setStart_time(m.get("c_start_date")+"");
			mk.setEnd_time(m.get("c_end_date")+"");
			mk.setManes(""+m.get("c_meet_price"));
			mk.setBacks(""+m.get("c_money"));
			mk.setC_num(""+m.get("c_num"));
			String type = ""+m.get("c_only_vip");
			
			
			mk.setType(type);
			String bar_Str = m.get("c_meet_price")+"";
			String cnt_Str = m.get("c_money")+"";
			List<String> bar_list = Arrays.asList(bar_Str.split(","));
			List<String> cnt_list = Arrays.asList(cnt_Str.split(","));
			mk.setMane_list(bar_list);
			mk.setBack_list(cnt_list);
			list.add(mk);
		}
		return list;
	}
	

}
