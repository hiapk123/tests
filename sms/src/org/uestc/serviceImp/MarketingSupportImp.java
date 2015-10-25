package org.uestc.serviceImp;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.MarketingSupportService;
import org.uestc.util.SqlHelper;

public class MarketingSupportImp implements MarketingSupportService {

	@Override
	public String getCoupons() {
		// TODO Auto-generated method stub
		StringBuffer sb  = new StringBuffer();
		sb.append("");
		String sql = "select * from coupons";
		ArrayList<Map> clist = SqlHelper.findAll(sql);
		if(clist!=null&&clist.size()!=0)
		for(Map m : clist){
			sb.append("<tr><td width=\"10%\"><div class = \"radio\"> <lable> <input class=\"radiosoption\" type = \"radio\" id = 'radio_"+m.get("c_id")+"' onclick=\"radioEvent('"+m.get("c_id")+"')\">");
			sb.append("</lable></div></td>");
			sb.append("<td width=\"30%\"> "+m.get("c_name")+"</td>");
			sb.append("<td width=\"20%\">"+m.get("c_num")+"</td>");
			sb.append("<td width=\"20%\">"+m.get("c_start_date")+"</td>");
			sb.append("<td width=\"20%\">"+m.get("c_end_date")+"</td>");
			sb.append("</tr>");
		}
		System.out.println("优惠券全部："+sql);
		return sb.toString();
		
	}

	@Override
	public String searchCoupons(String keywords) {
		// TODO Auto-generated method stub
		StringBuffer sb  = new StringBuffer();
		sb.append("");
		String sql = "select * from coupons where c_name like '%"+keywords+"%' or c_no like '%"+keywords+"%'";
		ArrayList<Map> clist = SqlHelper.findAll(sql);
		if(clist!=null&&clist.size()!=0)
		for(Map m : clist){
			sb.append("<tr><td width=\"10%\"><div class = \"radio\"> <lable> <input class=\"radiosoption\" type = \"radio\" id = 'radio_"+m.get("c_id")+"' onclick=\"radioEvent('"+m.get("c_id")+"')\">");
			sb.append("</lable></div></td>");
			sb.append("<td width=\"30%\"> "+m.get("c_name")+"</td>");
			sb.append("<td width=\"20%\">"+m.get("c_num")+"</td>");
			sb.append("<td width=\"20%\">"+m.get("c_start_date")+"</td>");
			sb.append("<td width=\"20%\">"+m.get("c_end_date")+"</td>");
			sb.append("</tr>");
		}
		System.out.println("优惠券搜索："+sql);
		return sb.toString();
	}

	@Override
	public String saveCouponsToActive(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		String table = req.getParameter("active");
		String acname = req.getParameter("activeName");
		String start = req.getParameter("start_time");
		String end = req.getParameter("end_time");
		String cid = req.getParameter("cid");
		if(cid.equals("")){
			cid="null";
		}
		String sql = "";
		if(table!=null){
			switch (table) {
			case "discount":
					sql = "update "+table +" set coupons_id = "+cid+" where d_name = '"+acname+"' and d_start_date = '"+start+"' and d_end_date = '"+end+"' and active_type = '"+req.getParameter("activeSub")+"'";
				break;
			case "swap":
				sql = "update "+table +" set coupons_id = "+cid+" where s_name = '"+acname+"' and s_start_date = '"+start+"' and s_end_date = '"+end+"'";
			break;
			case "package":
				sql = "update "+table +" set coupons_id = "+cid+" where p_name = '"+acname+"' and p_start_date = '"+start+"' and p_end_date = '"+end+"'";// and active_type = '"+req.getParameter("activeSub")+"'";
			break;
			case "for_gift":
				sql = "update "+table +" set coupons_id = "+cid+" where e_name = '"+acname+"' and e_start_date = '"+start+"' and e_end_date = '"+end+"'";// and active_type = '"+req.getParameter("activeSub")+"'";
			break;
			default:
				break;
			}
				
			
			
		}
		System.out.println("绑定优惠券："+sql);
		
		SqlHelper.executeUpdate(sql, null);
		return null;
	}

}
