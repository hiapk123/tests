package org.uestc.serviceImp;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.dao.MarketingMefxDao;
import org.uestc.daoImp.MarketingMefxDaoImp;
import org.uestc.service.MarketingMefxService;
import org.uestc.util.SqlHelper;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.uestc.bean.MarketingInit;
import com.uestc.bean.MarketingMefxBean;



public class MarketingMefxServiceImp implements MarketingMefxService {
	
	private  String  s_id  = "1"; 
	MarketingMefxDao mdao =  new MarketingMefxDaoImp();
	@Override
	public String getPageInit(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub

		String sql = "select * from coupons  where   s_del = 1 ";
		sql += " order by c_id  desc";
		System.out.println("查询活动："+sql);
		Date date = new Date();
		DateFormat formate  = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String time = formate.format(date);
		//sql查询活动，dao查询活动相关商品信息
		removeOutdateCoupons(time);
		ArrayList<MarketingInit> mint = mdao.getBeanList(sql);
		req.setAttribute("activelist", mint);
		return null;
	}
	@Override
	public String operateProgress(HttpServletRequest req, HttpServletResponse resp ) throws IOException{
		// TODO Auto-generated method stub
		String active_type =(String)req.getParameter("active_type");//("active_type");
		System.out.println("actice_type:"+active_type);
	   if (active_type!=null)
		switch (active_type) {
		case "xiangxi":
			progressXG(req, resp,"xiangxi");
			break;
		case "gengxin":
			progressXG(req, resp,"gengxin");
			break;
		case "shanchu":
			progressShanchu(req, resp);
			break;
		default:
			break;
		}
		return active_type;
	}
	private void progressXG(HttpServletRequest req,HttpServletResponse resp,String type){
		String c_id  = (String)req.getParameter("c_id");
		//String d_start_time  = (String)req.getParameter("c_start_date");
		String sql = "select * from coupons  where c_id = \'"+c_id +"\' and s_del =1 ";
		System.out.println("详细或修改活动查询："+sql);
		ArrayList<MarketingMefxBean> mlist  =  mdao.getXGBeanList(sql);
		req.setAttribute("activelist", mlist);
		req.setAttribute("c_name", mlist.get(0).getName());
		req.setAttribute("c_id", mlist.get(0).getActive_id());
		req.setAttribute("c_start_time", mlist.get(0).getStart_time());
		req.setAttribute("c_end_time", mlist.get(0).getEnd_time());
		req.setAttribute("c_type",mlist.get(0).getType());
		req.setAttribute("c_num", mlist.get(0).getC_num());
		req.setAttribute("function", type);
	}

	
	private void progressShanchu(HttpServletRequest req ,HttpServletResponse resp) throws IOException{
		String c_id  = (String)req.getParameter("c_id");
		//String d_start_time  = (String)req.getParameter("c_start_date");
		String sql = "delete from coupons where c_id = \'"+c_id+"\' and s_del = 1 ";
		System.out.println("删除:"+sql);
		String p[]={};
		try {
			SqlHelper.executeUpdate(sql,p);
		}catch(Exception e){
			PrintWriter out = resp.getWriter();
			out.write("filed");
			out.flush();
			out.close();
		}
	}
	
	
	
	public String progressTCType(String tctype,HttpServletRequest req ,HttpServletResponse res) throws IOException {
		StringBuffer sb = new StringBuffer();
		switch (tctype) {	
		case "saveGoods":
			sb = saveGoodsToActive( req,res);
			break;
		
		default:
			break;
		}
		return sb.toString();
	}
	
	
	

	private StringBuffer saveGoodsToActive(HttpServletRequest req,HttpServletResponse res) throws IOException{
		StringBuffer sb = new StringBuffer();
		String tinfo = req.getParameter("tinfo");		
		String e_name = req.getParameter("name");
		String start_time = req.getParameter("start_time");
		String stop_time = req.getParameter("stop_time");
		String type = req.getParameter("type");
		String hc_id = req.getParameter("hc_id");
		String c_num = req.getParameter("c_num");
		if(hc_id!=null&&!hc_id.equals("")){
			String sql  = "delete from coupons where c_id= "+hc_id+" and s_del = 1";
			String p[] = {};
			try{
			SqlHelper.executeUpdate(sql, p);
			}catch(Exception e){
				PrintWriter out = res.getWriter();
				out.write("filed");
				out.flush();
				out.close();
				return sb;
			}
		}		
		List per = Arrays.asList(tinfo.split("_"));
		for(Iterator<String> it = per.iterator();it.hasNext();){
			 String p = it.next();
			 String mane = p.split("MIPT")[1];
			 String back=p.split("BIPT")[1];
			 String sql = "insert into coupons (c_only_vip,c_name,c_num,c_start_date,c_end_date,c_status,c_meet_price,c_money,s_del)";
			 sql +="values('"+type+"','"+e_name+"',"+c_num+",'"+start_time+"','"+stop_time+"','1','"+mane+"','"+back+"',"+1+")";
			 String par[]={};
			 System.out.println("save goods to yhq_active:");
			 System.out.println(sql);
			 System.out.println(par);
			 SqlHelper.executeUpdate(sql, par);
			 String sql_cid = "select max(c_id) from coupons";
			 ArrayList<Map> cidlist = SqlHelper.findAll(sql_cid);
			 String c_no = "yhq";
			 String c_id = "";
			 if(cidlist.size()!=0){
				 c_no+=cidlist.get(0).get("max(c_id)");
				 c_id =""+ cidlist.get(0).get("max(c_id)");
			 }
			 String sqlupdate = "update coupons set c_no = '"+c_no +"' where c_id =" +c_id ;
			 System.out.println("优惠券条码修改:"+sqlupdate);
			 SqlHelper.executeUpdate(sqlupdate, par);
		}
		sb.append("success");
		return sb;

	}
	@Override
	public String removeOutdateCoupons(String time) {
		// TODO Auto-generated method stub
		String sql1 = "update discount set coupons_id = null where d_end_date < '"+time+"'";
		String sql2 = "update swap  set coupons_id = null where s_end_date < '"+time+"'";
		String sql3 = "update for_gift set coupons_id = null where e_end_date < '"+time+"'";
		String sql4 = "update package set coupons_id = null where p_end_date < '"+time+"'";
		String sqls[]={sql1,sql2,sql3,sql4};
		for(String s:sqls){
			SqlHelper.executeUpdate(s, null);
		}
		return null;
	}

}
