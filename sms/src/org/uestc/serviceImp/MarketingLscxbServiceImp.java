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
import org.uestc.service.MarketingLscxbService;
import org.uestc.util.SqlHelper;

import com.uestc.bean.MarketingDiscBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MarketingLscxbServiceImp implements MarketingLscxbService {

	private String s_id = "1";
	
	MarketingDiscDao md = new MarketingDiscDaoImp();
	
	public String getPageInit(HttpServletRequest req, HttpServletResponse res) {
		List<MarketingDiscBean> mlist = new ArrayList<MarketingDiscBean>();
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time=format.format(date); 
		String count_sql = "select count(*) from disc_goods where d_id in ( select min(d_id) from disc_goods where  d_end_date < '"+time+"' ";
			count_sql+=	" group by d_start_date,d_active_name,d_end_date) ";

		System.out.println("disc_goods count_sql :"+count_sql);
		ArrayList<Map> amap  = SqlHelper.findAll(count_sql);
		if(amap==null||amap.size()==0){
			req.setAttribute("pageCount", 0);
			
		}
		else{
			String count = amap.get(0).get("count(*)")+"";
			req.setAttribute("pageCount", count);
		}
		return null;
	}

	@Override
	public String operateProgress(HttpServletRequest req, HttpServletResponse rep) {
		// TODO Auto-generated method stub
		String type = ""+req.getParameter("type");
		String start_time  = ""+req.getParameter("start_time");
		String name  = ""+req.getParameter("name");
		md.deleteActive(type, name, start_time);
		return null;
	}

	@Override
	public String progressTCType(String tctype, HttpServletRequest req) {
		// TODO Auto-generated method stub
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
		String sql = "select *  from disc_goods where  d_end_date < '"+time+"' group by d_start_date,d_active_name,d_end_date order by d_end_date limit "+par1+","+pageSize;
		System.out.println("disc_goods fenye : "+sql);
		JSONArray array = new JSONArray();
		List<MarketingDiscBean> mlist = new ArrayList<MarketingDiscBean>();
		mlist = md.getMarketingDiscBeanBySQL(sql);
		for(Iterator<MarketingDiscBean> mdb =mlist.iterator();mdb.hasNext();){
			MarketingDiscBean m = mdb.next();
			JSONObject json = new  JSONObject();
			json.put("name",m.getD_active_name()+"");
			json.put("activeType", m.getD_active_type());
			json.put("startTime", m.getD_start_date());
			json.put("endTime", m.getD_end_date());
			json.put("type", m.getD_type());
			array.add(json);
		}
		
		return array.toString();
	}

}