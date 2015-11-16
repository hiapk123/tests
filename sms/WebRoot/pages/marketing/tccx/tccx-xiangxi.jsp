<%@ page language="java" import="java.util.*,org.uestc.util.PageObject,com.uestc.bean.*"
	
	pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<base href="<%=basePath%>">

<title>套餐促销</title>



<script>




</script>

</head>

<body>

<div class="panel panel-default">
		<div class="panel-body">
			<div style="float: left; width: 15%;">
				<h4>套餐促销</h4>
				
			</div>
				
			<div id="mbt_tccx_head" style="float: right; width: 85%;">			
				<a class="btn btn-primary" onclick="backlist();" href="javascript:void(0)" style="float: right;">返回列表</a>
			</div>
		</div>
	</div>
	<!-- div start -->
	<%
		String type = (String)request.getAttribute("function");
		String enable = "disabled='disabled'";
		String readable = "readonly='readonly'";
		String function = (String)request.getAttribute("function");
		String m_th = "30%";
		String d_th = "40%";
		String h_th = "30%";
		if(function.equals("gengxin")||function.equals("copy")){
			enable = "";	
			m_th = "30%";
			d_th = "40%";
		   h_th = "20%";
			//readable="";
		}
		String d_type = (String)request.getAttribute("p_type");
		String t1 ="",t2="",t3="";
		if(d_type!=null&&!d_type.equals("null")){
			if(d_type.equals("1")||d_type.equals("3")||d_type.equals("5")||d_type.equals("7")){
				t1="checked = 'checked' ";
			}
			if(d_type.equals("2")||d_type.equals("3")||d_type.equals("6")||d_type.equals("7")){
				t2="checked = 'checked' ";
			}
			if(d_type.equals("4")||d_type.equals("5")||d_type.equals("6")||d_type.equals("7")){
				t3="checked = 'checked' ";
			}
		}
		String  aname = (String)request.getAttribute("p_name");
		 
		if(type.equals("copy")){
			aname +="(复制)";
		}
	%>
 <div class="panel panel-default">
 			<div id="mbt_tccx_content_head" class="panel-heading">
 						<div class="panel-heading" id="mbt_tccx_content_head">
 						活动名称：<input type="text" <%=enable %> value='<%=aname %>' id="mbt_tccx_active_name">
 						开始时间:<input  type="text"  <%=enable %> value='<%=request.getAttribute("p_start_time") %>' <%=readable %> id="date_start" class="form_datetime"/>
 						结束时间：<input type="text" <%=enable %> value='<%=request.getAttribute("p_end_time") %>'   <%=readable %> id="date_stop"class="form_datetime"/ >
 						 可用于： <label class="checkbox-inline">
 						 				<input type="checkbox" value="shitidian" <%=t1 %> <%=enable %> id="optionsRadios1" name="optionsRadiosinline"> 实体店
 						 			</label>
 						  <label class="checkbox-inline">
 						  		<input type="checkbox" value="wangdian"  <%=t2 %> <%=enable %> id="optionsRadios2" name="optionsRadiosinline"> 网店</label>
 						  <label class="checkbox-inline">
 						  		<input type="checkbox" value="huiyuanzhuanxiang" <%=t3 %> <%=enable %> id="optionsRadios3" name="optionsRadiosinline"> 会员专享
 						  </label>
 						 </div>
			</div> 
			<div class="panel-body">
				<div id="mbt_tccx_panel" class="col-sm-12 col-lg-12">
				  		<table class= "col-sm-12 col-lg-12 table tbd" border=1>
				  			<thead class="trBack ">
				  			<tr>
				  			<th width='<%=m_th %>'>商品名</th>
				  			<th width='<%=d_th %>'>套餐名称</th>
				  			<th width='<%=h_th %>'>套餐价</th>
				  			
			
					  		 <%if(function.equals("gengxin")||function.equals("copy")){
					  			 out.println("<th width=\"10%\">操作</th>");
					  		 }%>		
				  			</tr>
				  			</thead>
				  			<tbody id="tccx_daitianjiashangpinxianshi">
				  				<%
				  					
				  					 int k = 1;
				  					List <MarketingTccxBean> mlist = (List<MarketingTccxBean>)request.getAttribute("activelist"); 				  				
				  					for(Iterator<MarketingTccxBean> it = mlist.iterator();it.hasNext();){
				  						MarketingTccxBean m = it.next();
				  						List<String> gm_names = m.getName_list();
				  						
				  						List<String> gm_counts = m.getCount_list();
				  				
				  						
				  						out.println("<tr id = \"trlist_"+k+"\" class='tdbody' value=\""+k+"\">");
				  						
				  						
				  						out.println("<td id = \"glist_"+k+"\">");
				  						for(Iterator<String> it_gns= gm_names.iterator(), it_gcs = gm_counts.iterator();it_gns.hasNext();){
				  							String g_name = it_gns.next();
				  							String g_count = it_gcs.next();
				  							out.println("<div>"+g_name+" X "+g_count+"</div>");
				  						}
				  						//添加隐藏控件
				  						out.println("<input type='hidden' id=\"glistgoods_"+k+"\" class=\"gbarcode\" value=\""+m.getBarcodes()+"\" />");
				  						out.println("<input type='hidden' id=\"glistcounts_"+k+"\" class=\"gcounts\" value=\""+m.getCounts()+"\" />");
				  						if(function.equals("gengxin")||function.equals("copy"))
				  							out.println("<a href=\"javascript:void(0)\" onclick=\"addgoods($(this));\">添加商品</a>");		
				  						out.println("</td>");
				  						
				  						out.println("<td id=\"tdminp_"+k+"\" >  <input "+enable+" value=\""+m.getPackage_name()+"\" id=\"minput_"+k+"\"></td>");
				  						out.println("<td id=\"tddinp_"+k+"'\"> <input "+enable+" value=\""+m.getP_price()+"\" id=\"dinput_"+k+"\"></td>");
				  						if(function.equals("gengxin")||function.equals("copy")){
				  							out.println("<td id=\"shanchu_"+k+"\"><a href=\"javascript:void(0)\" onclick=\"delGoodsInList('trlist_"+k+"');\">删除</a></td>");
				  						}
				  					
				
				  						out.println("</tr>");
				  						k++;
				  						
				  					}   
				  					
				  				
				  				%>
				  			</tbody>
				  		
				  		</table>
				  		<%
				  			if(type.equals("gengxin")){
  								out.println("<a onclick=\"addgoods('gengxin');\" href=\"javascript:void(0)\">点此打开商品搜索</a>");
  					  			out.println("<div class=\"span7 text-center\"><a style=\"float:center;\" href=\"javascript:void(0)\" onclick=\"saveGoodsToActive('gengxin');\" class=\"text-center	 btn btn-info btn-sm\">保存返回列表</a></div>");
  							}
				  		if(type.equals("copy")){
							out.println("<a onclick=\"addgoods('gengxin');\" href=\"javascript:void(0)\">点此打开商品搜索</a>");
				  			out.println("<div class=\"span7 text-center\"><a style=\"float:center;\" href=\"javascript:void(0)\" onclick=\"saveGoodsToActive('copy');\" class=\"text-center	 btn btn-info btn-sm\">保存返回列表</a></div>");
						}
				  		%>
				  		
			   </div>
	
			</div> 
		
</div>
 
</body>
</html>
 