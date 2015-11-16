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

import org.uestc.dao.MarketingDiscDao;
import org.uestc.dao.MarketingTccxDao;
import org.uestc.daoImp.MarketingDiscDaoImp;
import org.uestc.daoImp.MarketingTccxDaoImp;
import org.uestc.service.MarketingTccxService;
import org.uestc.util.SqlHelper;

import com.uestc.bean.MarketingInit;
import com.uestc.bean.MarketingTccxBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class MarketingTccxServiceImp implements MarketingTccxService {
	
	private  String  s_id  = "1"; 
	MarketingTccxDao mdao =  new MarketingTccxDaoImp();
	MarketingDiscDao md = new MarketingDiscDaoImp();
	String active_type = "套餐促销";
	@Override
	public String getPageInit(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time=format.format(date); 
		String sql = "select * from package where  p_end_date >= \'"+time+"\' and s_del = 1 ";
		
		sql += " group by p_name,p_start_date,p_end_date order by p_start_date desc";
		System.out.println("查询活动："+sql);
		//sql查询活动，dao查询活动相关商品信息
		
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
		String d_name  = (String)req.getParameter("p_name");
		String d_start_time  = (String)req.getParameter("p_start_date");
		String sql = "select * from package  where p_name = \'"+d_name +"\' and p_start_date = \'"+d_start_time+"\' and s_del =1 ";
		System.out.println("详细或修改活动查询："+sql);
		ArrayList<MarketingTccxBean> mlist  =  mdao.getXGBeanList(sql);
		req.setAttribute("activelist", mlist);
		req.setAttribute("p_name", d_name);
		req.setAttribute("p_start_time", mlist.get(0).getStart_time());
		req.setAttribute("p_end_time", mlist.get(0).getEnd_time());
		req.setAttribute("p_type",mlist.get(0).getType());
		req.setAttribute("function", type);
	}

	
	private void progressShanchu(HttpServletRequest req ,HttpServletResponse resp){
		String d_name  = (String)req.getParameter("p_name");
		String d_start_time  = (String)req.getParameter("p_start_date");
		String sql = "delete from package where p_name = \'"+d_name+"\' and p_start_date = \'"+d_start_time+"\' and s_del = 1 " ;
		System.out.println("删除:"+sql);
		String p[]={};
		SqlHelper.executeUpdate(sql,p);
		md.deleteActive(active_type, d_name, d_start_time);
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
		//sb.append("<option value=\"0\">--全部分类--</option>");
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
		String tinfo = req.getParameter("tinfo");
		
		String e_name = req.getParameter("name");
		String start_time = req.getParameter("start_time");
		String stop_time = req.getParameter("stop_time");
		String type = req.getParameter("type");
		String hname = req.getParameter("hname");
		String hstart_time = req.getParameter("hstart_time");
		String cid = null;
		if(!(req.getParameter("savetype")!=null&&req.getParameter("savetype").equals("copy")))
		if(!hname.equals("")&&!hstart_time.equals("")){
			String getcid = "select coupons_id from package where p_name = \'"+hname+"\' and p_start_date = \'"+hstart_time+"\' ";
			ArrayList<Map> clist = SqlHelper.findAll(getcid);
			if(clist!=null&&clist.size()!=0){
				cid = ""+clist.get(0).get("coupons_id");
			}
			String sql  = "delete from package where  p_name = \'"+hname+"\' and p_start_date = \'"+hstart_time+"\'";
			String p[] = {};
			SqlHelper.executeUpdate(sql, p);
			md.deleteActive(active_type, hname, hstart_time);
		}
		
		List per = Arrays.asList(tinfo.split("_"));
		for(Iterator<String> it = per.iterator();it.hasNext();){
			 String p = it.next();
			 String package_name = p.split("MIPT")[1];
			 String package_price=p.split("BIPT")[1];
			 String barcodes = p.split("MID")[1];
			 String counts = p.split("MCN")[1];
			
	
			 String sql = "insert into package (coupons_id,p_name,p_start_date,p_end_date,p_type,p_goods_list,p_count_list,p_price,s_id,s_del,package_name)";
			 sql +="values("+cid+",'"+e_name+"','"+start_time+"','"+stop_time+"','"+type+"','"+barcodes+"','"+counts+"','"+package_price+"',"+s_id+",1,'"+package_name+"')";
			 String par[]={};
			 System.out.println("save goods to tccx_active:");
			 System.out.println(sql);
			 System.out.println(par);
			 SqlHelper.executeUpdate(sql, par);
		}
		md.insertActive(active_type, e_name, start_time);
		sb.append("success");
		return sb;

	}
	@Override
	public String fysearch(HttpServletRequest req) {
		// TODO Auto-generated method stub
		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();
		String keywords = req.getParameter("keywords");
		String c_id = req.getParameter("c_id");
		String g_barcode = (String)req.getParameter("alreadybarcode");
		List  gbarcode  = Arrays.asList(g_barcode.split(","));
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
			String g_code = (String)g.get("g_barcode");
			if(gbarcode.contains(g_code+"")){
				flag ="isSelect=\"yes\"";
				chek = "disabled=\"disabled\" checked=\"checked\"";
				class1 = "1";
				continue;
			}
			String cateName = "";
			sb.append("<tr id=\"tccx_tr_"+g.get("g_barcode")+"\"><td "+flag+" width=\"5%\"><input  class=\"tccx_single_checkbox"+class1+"\" "+chek+" type = \"checkbox\"  value = \""+g.get("g_barcode")+"\"/></td>");
			String getCate_sql = "select * from category where c_id = "+g.get("c_id");
			ArrayList<Map> cList = SqlHelper.findAll(getCate_sql);
			
			sb.append("<td width=\"20%\">"+(String)g.get("g_name")+"</td>");
			if(cList!=null&&cList.size()!=0){
				cateName = (String)cList.get(0).get("c_name");
			}
			sb.append("<td width=\"15%\">"+cateName+"</td>");
			sb.append("<td width=\"15%\">"+g.get("g_sale_price")+"</td>"); 
			sb.append("<td width=\"15%\">"+g.get("g_pur_price")+"</td>"); 
			sb.append("<td width=\"25%\"><input id=\"count_"+g.get("g_barcode")+"\" style=\"width:80%;float: right;\" value=\"1\" /></td>");
		}
		json.put("pageCount", itmscount);
		json.put("table", sb.toString());
		array.add(json);
		return array.toString();
	}

	
}
