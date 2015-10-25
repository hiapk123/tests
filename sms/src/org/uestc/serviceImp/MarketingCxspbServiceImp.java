package org.uestc.serviceImp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.dao.MarketingDiscDao;
import org.uestc.daoImp.MarketingDiscDaoImp;
import org.uestc.service.MarketingCxspbService;
import org.uestc.util.SqlHelper;

import com.uestc.bean.MarketingDiscBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MarketingCxspbServiceImp implements MarketingCxspbService {

	private String s_id = "1";
	
	MarketingDiscDao md = new MarketingDiscDaoImp();
	
	public String getPageInit(HttpServletRequest req, HttpServletResponse res) {

		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time=format.format(date); 
		// TODO Auto-generated method stub
		
		String sql = "select count(*) from disc_goods where d_start_date <= '"+time+"' and d_end_date >= '"+time+"'order by d_end_date ";
		ArrayList<Map> amap = SqlHelper.findAll(sql);
		if(amap==null||amap.size()==0){
			req.setAttribute("pageCount", 0);
		}else{
			req.setAttribute("pageCount", amap.get(0).get("count(*)")+"");
		}
		System.out.println("pagecount ："+sql);

		return null;
	}
	@Override
	public String profenye(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
		int pageSize = Integer.parseInt(req.getParameter("pageSize"));
		int par1 = (pageIndex -1)* pageSize;
		
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time=format.format(date); 
		String sql = "select * from disc_goods where d_start_date <= '"+time+"' and d_end_date >= '"+time+"'  order by d_end_date limit "+par1+","+pageSize;
		System.out.println("disc_goods fenye : "+sql);
		JSONArray array = new JSONArray();
		List<MarketingDiscBean> mlist = new ArrayList<MarketingDiscBean>();
		mlist = md.getMarketingDiscBeanBySQL(sql);
		for(Iterator<MarketingDiscBean> mdb =mlist.iterator();mdb.hasNext();){
			MarketingDiscBean m = mdb.next();
			JSONObject json = new  JSONObject();
			json.put("name",m.getD_goodname_lsit()+"");
			json.put("barcode", m.getD_goodbar_list());
			json.put("activeName", m.getD_active_name());
			json.put("activeType", m.getD_active_type());
			json.put("startTime", m.getD_start_date());
			json.put("endTime", m.getD_end_date());
			json.put("price", m.getD_saleprice_list());
			String disc = m.getD_disprice_list();
			if("null".equals(disc))
				disc="-";
			json.put("discPirce", disc);
			json.put("type", m.getD_type());
			array.add(json);
		}
		
		return array.toString();
	}
	@Override
	public String operateProgress(HttpServletRequest req, HttpServletResponse rep) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String progressTCType(String tctype, HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}



}