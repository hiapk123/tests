package org.uestc.serviceImp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.uestc.dao.MarketingDiscDao;
import org.uestc.daoImp.MarketingDiscDaoImp;
import org.uestc.service.MarketingDzytjServicebk;
import org.uestc.util.SqlHelper;
@SuppressWarnings("rawtypes")
public class MarketingDzytjServiceImpbk implements MarketingDzytjServicebk {

	MarketingDiscDao md = new MarketingDiscDaoImp();
	private int s_id = 1;
	 public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	/**
	  * 获取数据库数据  工具
	  * @param sql
	  * @return
	  */
		private  ArrayList<Map>  getDiscountList(String sql){
		ArrayList<Map> results = new ArrayList<>();
		results = SqlHelper.findAll(sql);
		return results;
		
	}
	
	/**
	 * 活动列表初始化，没有作分页处理  必须考虑  活动类型/店铺名称/是否有效   店铺名称暂未考虑
	 * 根据活动开始时间，活动开始时间，删除标志，活动类型，店铺ID，查询活动数据
	 * sql =  "select *,count(distinct d_name,d_start_date) from discount where s_id = ? and s_del = 1 and active_type = 0 group by d_name";
	 */
	@Override
	public String getPageInit() {
		// TODO Auto-generated method stub
		StringBuffer sb=  new StringBuffer();

		sb.append("<table class=\"col-sm-12 col-lg-12 table\">");
		sb.append("<thead >");
		sb.append("<tr class=\"trBack\">");
		sb.append("<th>活动名称</th>");
		sb.append("<th>开始时间</th>");
		sb.append("<th>结束时间</th>");
		sb.append("<th>适用于</th>");
		sb.append("<th>捆绑优惠券</th>");
		sb.append("<th>操作</th>");
		sb.append("</tr>");
		sb.append("</thead>");
		sb.append("<tbody class=\"tbd\"> ");
		String sql =  "select *,count(distinct d_name,d_start_date) from discount where s_id = "+s_id+" and  s_del = 1 and active_type = 0 group by d_name order by d_start_date desc";
		ArrayList<Map> disList = getDiscountList(sql);
		
	 		for (Iterator<Map> itr =disList.iterator();itr.hasNext();){
	 			
	 		  sb.append("<tr>" );
	 		  	Map m =	itr.next();
	 		  	String d_name = (String)m.get("d_name");
	 		  	String d_start_date =(String)m.get("d_start_date");
	 		   sb.append("<td>"+ d_name);
	 		   sb.append("</td><td>"+ d_start_date);
	 		   sb.append("</td><td>"+ m.get("d_end_date"));
	 		   int d_type = (int)m.get("d_type");
	 		   System.out.println("d_type   =  = = = = =    "+d_type);
	 		   String type ="";
	 		   int nu = (int)m.get("d_type");// m.get("d_type");
	 		   switch(nu){
	 		   case 1:  type = "实体店";break;
	 		   case 2:	type = "网店";break;
	 		   case 3:  type = "实体店 网店";
	 		   case 4:	type = "会员专享";break;
	 		   case 5:	type = "实体店 会员专享";break;
	 		   case 6:	type = "网店 会员专享";break;
	 		   case 7:	type = "实体店 网店 会员专享";break;
	 		   }
	 		   sb.append("</td><td>"+type);
	 		   
				sb.append("<td><a href=\"javascript:void(0)\" onclick=\"addCouponsToDiscount("+m.get("d_id")+");\">立即捆绑</a></td>");
				sb.append("<td  > <a id=\"dzytj_xiangxi"+m.get("d_id")+"\" href=\"javascript:void(0)\" d_name=\""+d_name+"\" d_start_date=\""+d_start_date+"\"  onclick=\"addactive(1,"+m.get("d_id")+");\" >详细</a>");
				sb.append("| <a   href=\"javascript:void(0)\" d_name=\""+d_name+"\" d_start_date=\""+d_start_date+"\"   onclick=\"addactive(2,"+m.get("d_id")+");\">修改</a>");
				sb.append("| <a  d_name=\""+d_name+"\" d_start_date=\""+d_start_date+"\"  id=\"dzytj_delactive_"+m.get("d_id")+"\"  onclick=\"delactive("+m.get("d_id")+");\"  href=\"javascript:void(0)\">删除</a></td>");
				sb.append("</tr>");
	 		   sb.append("</td></tr>");
	 		   m.get("d_id");
		}
	 	/*	sb.append("<tr>");
			sb.append("<td>开业大放送</td>");
			sb.append("<td>2015.07.11</td>");
			sb.append("<td>2015.07.21</td>");
			sb.append("<td>实体店 网店</td>");
			sb.append("<td><a href=\"javascript:void(0)\">立即捆绑</a></td>");
			sb.append("<td><a href=\"javascript:void(0)\">详细</a> | <a href=\"javascript:void(0)\">修改</a> | <a href=\"javascript:void(0)\">删除</a></td>");
			sb.append("</tr>");*/
		
		
		
		sb.append("</tbody>");
		sb.append("</table>");
		

		return sb.toString();
	}

	/**
	 * 活动添加表格的分类处理 仅分类
	 */
	@Override
	public String getAddPage(String type,String d_name,String d_start_date) {
		// TODO Auto-generated method stub
		StringBuffer sb =new StringBuffer();
		if(type!=null)
		switch (type) {
		case "add":
			 sb = getAddPage_typeAdd();
			break;
		case "1":
			sb = getAddPage_typeSearch(d_name,d_start_date);
			break;
		case "2":
			sb = getAddPage_typeUpdate(d_name,d_start_date);
			break;	

		default:
			break;
		}
		
		return sb.toString();
	}
	/**
	 * 添加 按钮 添加界面表格 无需任何信息
	 * @return
	 */
	private StringBuffer getAddPage_typeAdd(){
		StringBuffer sb = new StringBuffer();
		sb.append("<table class=\"col-sm-12 col-lg-12 table\">");
		sb.append("<thead >");
		sb.append("<tr class=\"trBack\">");
		sb.append("<th>商品名</th>");
		sb.append("<th>分类</th>");
		sb.append("<th>原价</th>");
		sb.append("<th>折扣</th>");
		sb.append("<th>删除</th>");
				
		sb.append("</tr>");
		sb.append("</thead>");
		sb.append("<tbody class=\"tbd\" id=\"dzytj_daitianjiashangpinxianshi\">");
		sb.append("</tbody>");
		sb.append("</table>");
		sb.append("<tr ><td style=\"text-align: left;\"><a onclick=\"addgoods(1);\" href=\"javascript:void(0)\">点此打开商品搜索</a></td></tr>");
		
		return sb;
	}
	/**
	 * 更新活动商品信息  更新信息 考虑 活动类型/店铺名称/是否有效 
	 * @return
	 */
	private StringBuffer getAddPage_typeUpdate(String d_name, String d_start_date){
		StringBuffer sb = new StringBuffer();
		sb.append("<table class=\"col-sm-12 col-lg-12 table\">");
		sb.append("<thead >");
		sb.append("<tr class=\"trBack\">");
		sb.append("<th>商品名</th>");
		sb.append("<th>分类</th>");
		sb.append("<th>原价</th>");
		sb.append("<th>折扣</th>");
		sb.append("<th>删除</th>");
				
		sb.append("</tr>");
		sb.append("</thead>");
		
		
		sb.append(getGoodsListStr("gengxin", d_name, d_start_date));
		
/*		sb.append("<tbody class=\"tbd\"> ");
		sb.append("<tr>");
		sb.append("<td>伊犁牛奶</td>");
		sb.append("<td>定价</td>");
		sb.append("<td>5.5</td>");
		sb.append("<td><input type=\"text\" value=100 /></td>");
		sb.append("<td><a href=\"javascript:void(0)\">删除</a></td>");
		sb.append("</tr> ");
		
		
			
		sb.append("</tbody>");*/
		
		sb.append("</table>");
		sb.append("<tr ><td style=\"text-align: left;\"><a onclick=\"addgoods(2);\" href=\"javascript:void(0)\">点此打开商品搜索</a></td></tr>");
		return sb;
	}
	/**
	 * 获取活动商品列表  详细信息 考虑 活动类型/店铺名称/是否有效
	 * @return
	 */
	private StringBuffer getAddPage_typeSearch(String d_name, String d_start_date){
		
		StringBuffer sb = new StringBuffer();
		sb.append("<table class=\"col-sm-12 col-lg-12 table\">");
		sb.append("<thead >");
		sb.append("<tr class=\"trBack\">");
		sb.append("<th>商品名</th>");
		sb.append("<th>分类</th>");
		sb.append("<th>原价</th>");
		sb.append("<th>折扣</th>");
		
				
		sb.append("</tr>");
		sb.append("</thead>");
		sb.append(getGoodsListStr("xiangxi", d_name, d_start_date));
/*		sb.append("<tbody class=\"tbd\"> ");
		sb.append("<tr>");
		sb.append("<td>伊犁牛奶</td>");
		sb.append("<td>定价</td>");
		sb.append("<td>5.5</td>");
		sb.append("<td><input type=\"text\" value=100 /></td>");
		sb.append("<td><a href=\"javascript:void(0)\">删除</a></td>");
		sb.append("</tr> ");
		
		
			
		sb.append("</tbody>");*/
		
		sb.append("</table>");
		
		return sb;
	}
	/**
	 * 添加按钮 添加界面头部 无需任何操作
	 * (non-Javadoc)
	 * @see org.uestc.service.MarketingDzytjServicebk#getAddHead()
	 */
	@Override
	public String getAddHead() {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		
		/*	sb.append("<div class=\"panel-heading\">");		
			sb.append(" 活动名称：<input id=\"mbt_dzytj_active_name\" type=\"text\" />开始时间:<input type=\"text\" value=\"开始时间\"/>");
			sb.append("结束时间：<input type=\"text\" value=\"结束时间\"  /> 可用于： ");
			sb.append(" <label class=\"checkbox-inline\">");
			sb.append("<input type=\"radio\" name=\"optionsRadiosinline\" id=\"optionsRadios1\" ");
			sb.append("value=\"shitidian\" checked> 实体店");
			sb.append("</label>");
			sb.append(" <label class=\"checkbox-inline\">");
			sb.append("<input type=\"radio\" name=\"optionsRadiosinline\" id=\"optionsRadios2\" ");
			sb.append("value=\"wangdian\"> 网店");
			sb.append("</label>");
			sb.append("<label class=\"checkbox-inline\">");
			sb.append("<input type=\"radio\" name=\"optionsRadiosinline\" id=\"optionsRadios3\" ");
			sb.append("value=\"huiyuanzhuanxiang\"> 会员专享");
			sb.append("</label>");	   
			sb.append("</div> ");*/
			
			
			sb.append("活动名称：<input id=\"mbt_dzytj_active_name\" type=\"text\" />");
			sb.append("开始时间:<input  id=\"date_start\" value=\"2015-08-20 21:05\"  type=\"text\" class=\"form_datetime\"/>");
			sb.append("结束时间：<input id=\"date_stop\" value=\"2015-08-15 21:05\" type=\"text\"   class=\"form_datetime\" /> 可用于： ");
			sb.append("<label class=\"checkbox-inline\">");
			sb.append("<input type=\"checkbox\" name=\"optionsRadiosinline\" id=\"optionsRadios1\" ");
			sb.append("value=\"shitidian\" checked> 实体店");
			sb.append("</label>");
			sb.append(" <label class=\"checkbox-inline\">");
			sb.append("<input type=\"checkbox\" name=\"optionsRadiosinline\" id=\"optionsRadios2\" ");
			sb.append("value=\"wangdian\"> 网店");
			sb.append("</label>");
			sb.append("<label class=\"checkbox-inline\">");
			sb.append("<input type=\"checkbox\" name=\"optionsRadiosinline\" id=\"optionsRadios3\""); 
			sb.append("value=\"huiyuanzhuanxiang\"> 会员专享");
			sb.append("</label>");
		return sb.toString();
	}
	
	/**
	 * 更新按钮  更新页面头部  不需要考虑活动类型/店铺名称/是否有效
	 * sql = "select * from discount where s_del = 1 and active_type = 0 and  d_id = "+id;
	 */
	@Override
	public String getGengxinHead(String id) {
		// TODO Auto-generated method stub
		
		StringBuffer sb =new StringBuffer();
		String sql = "select * from discount where s_del = 1 and active_type = 0 and  d_id = "+id;
		String d1 ="";
		String d2 = "";
		String d3="";
		ArrayList<Map> disList = getDiscountList(sql);
		if(disList.size()!=0){
			sb.append("活动名称：<input id=\"mbt_dzytj_active_name\" type=\"text\" value=\""+disList.get(0).get("d_name")+"\"/>");
			sb.append("开始时间:<input  id=\"date_start\" value=\""+disList.get(0).get("d_start_date")+"\"  type=\"text\" class=\"form_datetime\" />");
			sb.append("结束时间：<input id=\"date_stop\" value=\""+disList.get(0).get("d_end_date")+"\" type=\"text\"   class=\"form_datetime\" /> 可用于： ");
			sb.append("<label class=\"checkbox-inline\">");
			int type = (int)disList.get(0).get("d_type");
			if(type==1||type==3||type==5||type==7){
				d1="checked=\"checked\"";
			}
			if(type==2||type==3||type==6||type==7){
				d2="checked=\"checked\"";
			}
			if(type==4||type==5||type==6||type==7){
				d3="checked=\"checked\"";
			}
			sb.append("<input type=\"checkbox\" name=\"optionsRadiosinline\" id=\"optionsRadios1\"  ");
			sb.append(d1);
			sb.append("value=\"shitidian\" checked> 实体店");
			sb.append("</label>");
			sb.append(" <label class=\"checkbox-inline\">");
			sb.append("<input type=\"checkbox\" name=\"optionsRadiosinline\" id=\"optionsRadios2\" ");
			sb.append(d2);
			sb.append("value=\"wangdian\"> 网店");
			sb.append("</label>");
			sb.append("<label class=\"checkbox-inline\">");
			sb.append("<input type=\"checkbox\" name=\"optionsRadiosinline\" id=\"optionsRadios3\" "); 
			sb.append(d3);
			sb.append("value=\"huiyuanzhuanxiang\"> 会员专享");
			sb.append("</label>");
		}
		return sb.toString();
	}
	/**
	 * 详细信息 详细页面头部 不需要考虑店铺/活动类型/是否有效
	 * String sql = "select * from discount where d_id = "+id;
	 */
	@Override
	public String getXiangxiHead(String id) {
		
		StringBuffer sb =new StringBuffer();
		String sql = "select * from discount where d_id = "+id;
		String d1 ="";
		String d2 = "";
		String d3="";
		ArrayList<Map> disList = getDiscountList(sql);
		if(disList.size()!=0){
			sb.append("活动名称：<input id=\"mbt_dzytj_active_name\" type=\"text\" value="+disList.get(0).get("d_name")+" disabled=\"disabled\" />");
			sb.append("开始时间:<input  id=\"date_start\" value=\""+disList.get(0).get("d_start_date")+"\"  type=\"text\" disabled=\"disabled\" readonly=\"readonly\"/>");
			sb.append("结束时间：<input id=\"date_stop\" value=\""+disList.get(0).get("d_end_date")+"\" type=\"text\"  disabled=\"disabled\" readonly=\"readonly\"  /> 可用于： ");
			sb.append("<label class=\"checkbox-inline\">");
			int type = (int)disList.get(0).get("d_type");
			if(type==1||type==3||type==5||type==7){
				d1="CHECKED=\"checked\"";
			}
			if(type==2||type==3||type==6||type==7){
				d2="CHECKED=\"checked\"";
			}
			if(type==4||type==5||type==6||type==7){
				d3="CHECKED=\"checked\"";
			}
			sb.append("<input type=\"checkbox\" name=\"optionsRadiosinline\" id=\"optionsRadios1\" disabled=\"disabled\" ");
			sb.append(d1);
			sb.append("value=\"shitidian\" checked> 实体店");
			sb.append("</label>");
			sb.append(" <label class=\"checkbox-inline\">");
			sb.append("<input type=\"checkbox\" name=\"optionsRadiosinline\" id=\"optionsRadios2\" disabled=\"disabled\"");
			sb.append(d2);
			sb.append("value=\"wangdian\"> 网店");
			sb.append("</label>");
			sb.append("<label class=\"checkbox-inline\">");
			sb.append("<input type=\"checkbox\" name=\"optionsRadiosinline\" id=\"optionsRadios3\" disabled=\"disabled\""); 
			sb.append(d3);
			sb.append("value=\"huiyuanzhuanxiang\"> 会员专享");
			sb.append("</label>");
		}
		return sb.toString();
		
	}
	
	/**
	 * 根据 活动ID/活动的类型/活动名称/活动开始时间   获取某一活动的   所有商品  应该不需考虑店铺ID 
	 * 根据 活动名s_id称，开始时间，删除标志，店铺ID，查询所有数据
	 */
	
	@Override
	public String getGoodsListStr(String type,String d_name, String d_start_date) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		String gengxin = "";
		
		if(!type.equals("gengxin")){
			gengxin="disabled = \"disabled\" readonly=\"true\"";
		}
		
		String sql_getList = "select * from discount where d_name = \'"+d_name +"\' and d_start_date = \'"+d_start_date+"\' and s_del = 1 and active_type = 0  ";
		System.out.println("获取活动的所有商品："+sql_getList);
		ArrayList<Map> goodlist= getDiscountList(sql_getList);
		sb.append("<tbody class=\"tbd\" id=\"dzytj_daitianjiashangpinxianshi\">");
		for(Iterator<Map> it = goodlist.iterator();it.hasNext();){
			Map Activegood =	it.next();
			//外键g_id 改为条码
		   ArrayList<Map>	 goodList = getDiscountList("select * from goods where g_barcode = "+Activegood.get("g_id") );
		   if(goodList.size()!=0){
			   
			Map good = goodList.get(0);   //商品信息（g_id,g_sale_price,c_id）
			   
			   sb.append("<tr id=\"list_"+good.get("g_id")+"\" value=\""+good.get("g_id")+"\">");
			   sb.append("<td>"+good.get("g_name")+"</td>"); //商品名
			   ArrayList<Map> cateList = getDiscountList("select * from category where c_id = "+good.get("c_id"));
			   if(cateList.size()!=0){
				   Map cate = cateList.get(0);
				   sb.append("<td>"+(String)cate.get("c_name")+"</td>");   //分类
			   }else {
				   sb.append("<td></td>");
			   }
			   sb.append("<td>"+good.get("g_sale_price")+"</td>");				//原价
			   sb.append("<td><input onblur=\"disinput("+good.get("g_id")+");\"  type=\"text\" "+gengxin +" value=\""+Activegood.get("d_discount")+"\" /></td>"); //折扣
			   if(type.equals("gengxin")){
				   sb.append("<td><a href=\"javascript:void(0)\" id=mbtdel"+Activegood.get("d_id")+" onclick=\"delGoodsInList("+good.get("g_id")+")\" >删除</a></td>");
			   }
			   
			   sb.append("</tr> ");		
		   }
		}
		sb.append("</tbody>");
		return sb.toString();
		
		/*	sb.append("<tbody class=\"tbd\"> ");
		sb.append("<tr>");
		sb.append("<td>伊犁牛奶</td>");
		sb.append("<td>定价</td>");
		sb.append("<td>5.5</td>");
		sb.append("<td><input type=\"text\" value=100 /></td>");
		sb.append("<td><a href=\"javascript:void(0)\">删除</a></td>");
		sb.append("</tr> ");		
		sb.append("</tbody>");*/
		
	}


	/******************************************************************************
	 **************************阶段二，数据添加，修改，删除,考虑门店***********************
	 *****************************************************************************/
	
	/**
	 * 分类处理弹出层请求，数据库无关
	 * 
	 */
	
	@Override
	public String progressTCType(String tctype,HttpServletRequest req) {
		StringBuffer sb = new StringBuffer();
		switch (tctype) {
		case "getSelect":
			sb = getSelectOption(s_id);
			break;
		case "getSearchGoods":
			String keywords = req.getParameter("keywords");
			String c_id = req.getParameter("c_id");
			String g_ids = (String)req.getParameter("alreadygid");
			sb = getSearchGoods(keywords,c_id,s_id,g_ids);
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
	private StringBuffer getSearchGoods(String keywords ,String c_id,int s_id,String g_ids){
		List  gids  = Arrays.asList(g_ids.split(","));
		
		StringBuffer sb = new StringBuffer();
		String sql = "select * from goods where   s_id = "+s_id;
		if(!c_id.equals("0")){
			sql +="and  c_id = "+c_id ; 
		}
		if(!keywords.equals("商品名/条形码")){
			sql += " and ( g_name like '%"+keywords+"%' or g_barcode like '%"+keywords+"%')";
		}
		System.out.println("商品搜索sql："+sql);
		ArrayList<Map> gList = getDiscountList(sql);
		for(Iterator<Map> m = gList.iterator();m.hasNext();){
			Map g =  m.next();
			/*<th><input type="checkbox" /></th>
			<th>商品名称</th>
			<th>分类</th>
			<th>原价</th>
			<th>进货价</th>*/
			String flag = "isSelect=\"no\"";
			String chek = "";
			String class1="";
			Long g_id = (Long)g.get("g_id");
			if(gids.contains(g_id+"")){
				flag ="isSelect=\"yes\"";
				chek = "disabled=\"disabled\" checked=\"checked\"";
				class1 = "1";
			}
			String cateName = "";
			sb.append("<tr id=\"dzytj_tr_"+g.get("g_id")+"\"><td "+flag+" width=\"10%\"><input  class=\"dzytj_single_checkbox"+class1+"\" "+chek+" type = \"checkbox\"  value = \""+g.get("g_id")+"\"/></td>");
			String getCate_sql = "select * from category where c_id = "+g.get("c_id");
			ArrayList<Map> cList = getDiscountList(getCate_sql);
			
			sb.append("<td width=\"30%\">"+(String)g.get("g_name")+"</td>");
			if(cList!=null&&cList.size()!=0){
				cateName = (String)cList.get(0).get("c_name");
			}
			sb.append("<td width=\"20%\">"+cateName+"</td>");  //分类名称
			sb.append("<td width=\"20%\">"+g.get("g_sale_price")+"</td>"); //原价
			sb.append("<td width=\"20%\">"+g.get("g_pur_price")+"</td>"); 
			
		}
		return sb;
	}
	
	private StringBuffer getSelectOption(int s_id){
		StringBuffer sb = new StringBuffer();
		String sql = "select *,count( distinct c_name) from category where s_id = "+s_id+" group by c_name";
		ArrayList<Map> sList = getDiscountList(sql);
		sb.append("<option value=\"0\">--全部分类--</option>");
		for(Iterator<Map> m = sList.iterator();m.hasNext();){
			Map mm=	m.next();
			sb.append("<option value = \""+mm.get("c_id")+"\" >"+mm.get("c_name")+"</option>");
			
		}
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
		if(!hname.equals("")&&!hstart_time.equals("")){
			String sql  = "delete from discount where s_id= "+s_id+" and d_name = \'"+hname+"\' and d_start_date = \'"+hstart_time+"\' and active_type = 0";
			String sql_disc = "delete from disc_goods where d_active_type = '打折与特价' and d_active_name = \'"+hname+"\' and d_start_date = \'"+hstart_time+"\' ";
			String p[] = {};
			SqlHelper.executeUpdate(sql_disc, p);
			
			SqlHelper.executeUpdate(sql, p);
		}
		//String discount = req.getParameter("discount");
		//int itype = Integer.parseInt(type);
		List  gids  = Arrays.asList(g_ids.split(","));
		List  dis = Arrays.asList(discounts.split(","));
		String sql = "";
		Iterator di = dis.iterator();
		
		for(Iterator it = gids.iterator();it.hasNext();){
			String id = (String)it.next();
			String d = (String)di.next();
			String sql_getbarcode = "select g_barcode from goods where g_id = "+id;
			ArrayList<Map> barcode = getDiscountList(sql_getbarcode);
			if(barcode.size()!=0){
				String code = (String)barcode.get(0).get("g_barcode");
			
				String par[] = {name,start_time,stop_time,type,code,d,1+"",0+"",s_id+""};
				sql = "insert into discount (d_name,d_start_date,d_end_date,d_type,g_id,d_discount,s_del,active_type,s_id)";	
				sql += "values (?,?,?,?,?,?,?,?,?)";
				
				System.out.println("save goods to active:");
				System.out.println(sql);
				System.out.println(Arrays.asList(par));
				SqlHelper.executeUpdate(sql, par);
				
			
			}
		}
	
		md.insertActive("打折与特价", name, start_time);
		sb.append("success");
		return sb;
	}

	private StringBuffer delactive(HttpServletRequest req){
		StringBuffer sb =new StringBuffer();
		String d_name = req.getParameter("d_name");
		String d_start_time = req.getParameter("d_start_time");
		String sql  = "delete from discount where s_id = "+s_id+" and active_type = 0   and d_name = \'"+d_name+"\' and d_start_date = \'"+d_start_time+"\'";
		String p[] = {};
		SqlHelper.executeUpdate(sql, p);
		String sql_disc = "delete from disc_goods where d_active_type = '打折与特价' and d_active_name = \'"+d_name +"\' and d_start_date = \'"+d_start_time +"\' ";
		SqlHelper.executeUpdate(sql_disc, p);
		sb.append("success");
		return sb;
	}
}
