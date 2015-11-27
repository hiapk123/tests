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

<title>优惠券</title>



<script>




</script>

</head>

<body>

<div class="panel panel-default">
		<div class="panel-body">
			<div style="float: left; width: 15%;">
				<h4>优惠券</h4>
				
			</div>
				
			<div id="mbt_mefx_head" style="float: right; width: 85%;">			
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
		String m_th = "50%";
		String d_th = "50%";
		
		if(function.equals("gengxin")){
			enable = "";	
			m_th = "40%";
			d_th = "40%";
		  
			//readable="";
		}
		String d_type = (String)request.getAttribute("c_type");
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
	%>
 <div class="panel panel-default">
 			<div id="mbt_mefx_content_head" class="panel-heading">
 						<div class="panel-heading" id="mbt_mefx_content_head">
 						<input type = 'hidden' id = 'mbt_mefx_active_id' value ='<%=request.getAttribute("c_id") %>' />
 						优惠券名称：<input type="text" <%=enable %> value='<%=request.getAttribute("c_name") %>' id="mbt_mefx_active_name">
 						优惠券数量：<input class='cfIntNum' type="text"  <%=enable %>  value='<%=request.getAttribute("c_num") %>' id="mbt_mefx_cnum">
 						开始时段:<input  type="text"  <%=enable %> value='<%=request.getAttribute("c_start_time") %>' <%=readable %> id="date_start" class="form_datetime"/>
 						结束时段：<input type="text" <%=enable %> value='<%=request.getAttribute("c_end_time") %>'   <%=readable %> id="date_stop"class="form_datetime"/ >
 						 类型： <label class="checkbox-inline">
 						 				<input type="checkbox" value="shitidian" <%=t1 %> <%=enable %> id="optionsRadios1" name="optionsRadiosinline"> 会员专享
 						 			</label>
 						  
 						 </div>
			</div> 
			<div class="panel-body">
				<div id="mbt_mefx_panel" class="col-sm-12 col-lg-12">
				  		<table class= "col-sm-12 col-lg-12 table tbd" border=1>
				  			<thead class="trBack ">
				  			<tr>
				  			<th width='<%=m_th %>'>订单总额</th>
				  			<th width='<%=d_th %>'>优惠金额</th>
				  			
				  			
			
					  		 <%if(type.equals("gengxin")){
					  			 out.println("<th width=\"20%\">操作</th>");
					  		 }%>		
				  			</tr>
				  			</thead>
				  			<tbody id="mefx_daitianjiashangpinxianshi">
				  				<%
				  					
				  					 int k = 1;
				  					List <MarketingMefxBean> mlist = (List<MarketingMefxBean>)request.getAttribute("activelist"); 				  				
				  					for(Iterator<MarketingMefxBean> it = mlist.iterator();it.hasNext();){
				  						MarketingMefxBean m = it.next();
				  						List<String> gm_names = m.getMane_list();
				  						List<String> gm_counts = m.getBack_list();			
				  						for(Iterator<String> it_gns= gm_names.iterator(), it_gcs = gm_counts.iterator();it_gns.hasNext();){
				  							String g_name = it_gns.next();
				  							String g_count = it_gcs.next();
				  							out.println("<tr id = \"trlist_"+k+"\" class='tdbody' value=\""+k+"\">");
				  							out.println("<td class='mane' >  <input class='cfnum' "+enable+" type='text' value=\""+g_name+"\"  /></td>");
					  						out.println("<td class='back' >  <input class='cfnum' "+enable+" type='text' value=\""+g_count+"\" /></td>");
					  						if(type.equals("gengxin")){
					  							out.println("<td class='shanchu'><a href=\"javascript:void(0)\" onclick=\"delGoodsInList('trlist_"+k+"');\">删除</a></td>");
					  						}
					  						out.println("</tr>");
					  						k++;
				  						}				  						
				  					}   
				  					
				  				
				  				%>
				  			</tbody>
				  		
				  		</table>
				  		<%
				  			if(type.equals("gengxin")){
  								out.println("<a onclick=\"addgoods('gengxin');\" href=\"javascript:void(0)\">新增明细</a>");
  					  			out.println("<div class=\"span7 text-center\"><a style=\"float:center;\" href=\"javascript:void(0)\" onclick=\"saveGoodsToActive('gengxin');\" class=\"text-center	 btn btn-info btn-sm\">保存返回列表</a></div>");
  							}
				  		%>
				  		
			   </div>
	
			</div> 
		
</div>
 
</body>
</html>

 