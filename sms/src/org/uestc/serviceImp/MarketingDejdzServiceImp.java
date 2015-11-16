package org.uestc.serviceImp;

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

import org.uestc.dao.MarketingDao;
import org.uestc.dao.MarketingDiscDao;
import org.uestc.daoImp.MarketingDaoImp;
import org.uestc.daoImp.MarketingDiscDaoImp;
import org.uestc.service.MarketingDejdzService;
import org.uestc.util.SqlHelper;

import com.uestc.bean.MarketingFunction;
import com.uestc.bean.MarketingInit;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class MarketingDejdzServiceImp implements MarketingDejdzService {
	
	private  String  s_id  = "1"; 
	MarketingDao mdao =  new MarketingDaoImp();
	MarketingDiscDao md = new MarketingDiscDaoImp();
	@Override
	public String getPageInit(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		Date date = new Date();
		DateFormat formate  = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String time = formate.format(date);
		String name = (String)req.getAttribute("d_name");
		String start_time = (String)req.getAttribute("d_start_date");
		
		String sql = "select * from discount where  d_end_date >= \'"+time+"\' and active_type =  1  and s_del = 1 ";
		sql += " group by d_name,d_start_date,d_end_date order by d_start_date desc";
		System.out.println("查询活动："+sql);
		ArrayList<MarketingInit> mint = mdao.getBeanList(sql);
		req.setAttribute("activelist", mint);
		return null;
	}
	@Override
	public String operateProgress(HttpServletRequest req, HttpServletResponse resp ){
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
		case "copy":
			progressXG(req, resp,"copy");
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
		String d_name  = (String)req.getParameter("d_name");
		String d_start_time  = (String)req.getParameter("d_start_date");
		String sql = "select * from discount where d_name = \'"+d_name +"\' and d_start_date = \'"+d_start_time+"\' and s_del =1 and active_type = 1 ";
		System.out.println("progressXG："+sql);
		ArrayList<MarketingFunction> mlist  =  mdao.getMarketFunctionBeanList(sql);
		req.setAttribute("activelist", mlist);
		req.setAttribute("d_name", d_name);
		req.setAttribute("d_start_time", mlist.get(0).getD_start_date());
		req.setAttribute("d_end_time", mlist.get(0).getD_stop_date());
		req.setAttribute("d_type",mlist.get(0).getD_type());
		req.setAttribute("function", type);
	}

	
	private void progressShanchu(HttpServletRequest req ,HttpServletResponse resp){
		String d_name  = (String)req.getParameter("d_name");
		String d_start_time  = (String)req.getParameter("d_start_date");
		String sql = "delete from discount where d_name = \'"+d_name+"\' and d_start_date = \'"+d_start_time+"\' and s_del = 1 and active_type = 1 ";
		String p[]={};
		String sql_disc = "delete from disc_goods where d_active_type = '第二件打折' and d_active_name = \'"+d_name+"\' and d_start_date = \'"+d_start_time+"\'";
		SqlHelper.executeUpdate(sql_disc, p);
		SqlHelper.executeUpdate(sql,p);
	}
	
	
	
	public String progressTCType(String tctype,HttpServletRequest req) {
		StringBuffer sb = new StringBuffer();
		switch (tctype) {
		case "getSelect":
			sb = getSelectOption(s_id);
			break;
		case "saveGoods":
			sb = saveGoodsToActive( req);
			break;
		case "delactive":
			sb= delactive(req);
			break;
		default:
			break;
		}
		return sb.toString();
	}
	
	private StringBuffer getSelectOption(String s_id){
		JSONArray array = new JSONArray();
		StringBuffer sb = new StringBuffer();
		String sql = "select *,count( distinct c_name) from category  group by c_name";
		ArrayList<Map> sList = SqlHelper.findAll(sql);
		for(Iterator<Map> m = sList.iterator();m.hasNext();){
			Map mm=	m.next();
			JSONObject jsonObject  = new JSONObject();
			jsonObject.put("c_id", mm.get("c_id"));
			jsonObject.put("c_name", mm.get("c_name"));
			//sb.append("<option value = \""+mm.get("c_id")+"\" >"+mm.get("c_name")+"</option>");
			array.add(jsonObject);
		}
		sb.append(array.toString());
		return sb;
	}

	private StringBuffer saveGoodsToActive(HttpServletRequest req){
		StringBuffer sb = new StringBuffer();
		String g_ids = req.getParameter("g_ids");
		String discounts = req.getParameter("discount");
		String name = req.getParameter("name");
		String start_time = req.getParameter("start_time");
		String stop_time = req.getParameter("stop_time");
		String type = req.getParameter("type");
		String hname = req.getParameter("hname");
		String hstart_time = req.getParameter("hstart_time");
		String cid = null;
		if(!(req.getParameter("savetype")!=null&&req.getParameter("savetype").equals("copy")))
		if(!hname.equals("")&&!hstart_time.equals("")){
			String getcid = "select coupons_id from discount where d_name = \'"+hname+"\' and d_start_date = \'"+hstart_time+"\' and active_type =  1";
			ArrayList<Map> clist = SqlHelper.findAll(getcid);
			if(clist!=null&&clist.size()!=0){
				cid = ""+clist.get(0).get("coupons_id");
			}
			String sql  = "delete from discount where  d_name = \'"+hname+"\' and d_start_date = \'"+hstart_time+"\' and active_type =  1";
			String p[] = {};
			String sql_disc = "delete from disc_goods where d_active_type = '第二件打折' and d_active_name = \'"+hname+"\' and d_start_date = \'"+hstart_time+"\'";
			SqlHelper.executeUpdate(sql_disc, p);
			SqlHelper.executeUpdate(sql, p);
		}
		List  gids  = Arrays.asList(g_ids.split(","));
		List  dis = Arrays.asList(discounts.split(","));
		String sql = "";
		Iterator di = dis.iterator();
		
		for(Iterator it = gids.iterator();it.hasNext();){
			String id = (String)it.next();
			String d = (String)di.next();
			//String sql_getbarcode = "select g_barcode from goods where g_id = "+id;
			//ArrayList<Map> barcode = SqlHelper.findAll(sql_getbarcode);
			//if(barcode.size()!=0){
				String code = id;
			
				String par[] = {cid,name,start_time,stop_time,type,code,d,1+"",1+"",s_id+""};
				sql = "insert into discount (coupons_id,d_name,d_start_date,d_end_date,d_type,g_id,d_discount,s_del,active_type,s_id)";	
				sql += "values ("+cid+",'"+name+"','"+start_time+"','"+stop_time+"','"+type+"','"+code+"','"+d+"','"+1+"','"+1+"',s_id)";
				System.out.println("save goods to dejdz_active:");
				System.out.println(sql);
			//	System.out.println(Arrays.asList(par));
				SqlHelper.executeUpdate(sql, null);
			
			//}
		}
		md.insertActive("第二件打折", name, start_time);
	
		sb.append("success");
		return sb;
	}

	private StringBuffer delactive(HttpServletRequest req){
		StringBuffer sb =new StringBuffer();
		String d_name = req.getParameter("d_name");
		String d_start_time = req.getParameter("d_start_time");
		String sql  = "delete from discount where active_type = 1 and  d_name = \'"+d_name+"\' and d_start_date = \'"+d_start_time+"\'";
		
		String p[] = {};
		String sql_disc = "delete from disc_goods where d_active_type = '第二件打折' and d_active_name = \'"+d_name+"\' and d_start_date = \'"+d_start_time+"\'";
		SqlHelper.executeUpdate(sql_disc, p);
		SqlHelper.executeUpdate(sql, p);
		sb.append("shanchu");
		return sb;
	}
	@Override
	public String fysearch(HttpServletRequest req) {
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		String keywords = req.getParameter("keywords");
		String c_id = req.getParameter("c_id");
		String g_ids = (String)req.getParameter("alreadygid");
		List  gids  = Arrays.asList(g_ids.split(","));
		int pageSize = Integer.parseInt(req.getParameter("pageSize"));
		int pageIndex= Integer.parseInt(req.getParameter("pageIndex"));
		int count = (pageIndex-1)*pageSize;
		
		StringBuffer sb = new StringBuffer();
		String sql = "select count(*) from goods where   g_id > 0 ";
		String sql_sel = "select * from goods where   g_id > 0 ";
		if(!c_id.equals("0")){
			sql +="and  c_id = "+c_id ; 
			sql_sel +="and  c_id = "+c_id ; 
		}
		if(!keywords.equals("商品名/条形码")){
			sql += " and ( g_name like '%"+keywords+"%' or g_barcode like '%"+keywords+"%')";
			sql_sel += " and ( g_name like '%"+keywords+"%' or g_barcode like '%"+keywords+"%')";
		}
		sql_sel += " limit "+count+","+pageSize;
		System.out.println("商品搜索sql："+sql_sel);
		System.out.println("商品总数sql："+sql);
		String itmscount = "";
		
		ArrayList<Map> amap  = SqlHelper.findAll(sql);
		if(amap==null||amap.size()==0){
			itmscount = "0";
		}else{
			itmscount = amap.get(0).get("count(*)")+"";
		}
		
		ArrayList<Map> gList =SqlHelper.findAll(sql_sel);
		for(Iterator<Map> m = gList.iterator();m.hasNext();){
			Map g =  m.next();
			String flag = "isSelect=\"no\"";
			String chek = "";
			String class1="";
			String g_id = (String)g.get("g_barcode");
			if(gids.contains(g_id+"")){
				flag ="isSelect=\"yes\"";
				chek = "disabled=\"disabled\" checked=\"checked\"";
				class1 = "1";
			}
			String cateName = "";
			sb.append("<tr id=\"dejdz_tr_"+g.get("g_barcode")+"\"><td "+flag+" width=\"10%\"><input  class=\"dejdz_single_checkbox"+class1+"\" "+chek+" type = \"checkbox\"  value = \""+g.get("g_barcode")+"\"/></td>");
			String getCate_sql = "select * from category where c_id = "+g.get("c_id");
			ArrayList<Map> cList = SqlHelper.findAll(getCate_sql);
			
			sb.append("<td width=\"30%\">"+(String)g.get("g_name")+"</td>");
			if(cList!=null&&cList.size()!=0){
				cateName = (String)cList.get(0).get("c_name");
			}
			sb.append("<td width=\"20%\">"+cateName+"</td>");  //分类名称
			sb.append("<td width=\"20%\">"+g.get("g_sale_price")+"</td>"); //原价
			sb.append("<td width=\"20%\">"+g.get("g_pur_price")+"</td>"); 
		}
		json.put("pageCount", itmscount);
		json.put("table", sb.toString());
		array.add(json);
		return array.toString();
	}

}
