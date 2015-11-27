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

<title>第二件打折</title>



<script>




</script>

</head>

<body>

<div class="panel panel-default">
		<div class="panel-body">
			<div style="float: left; width: 15%;">
				<h4>第二件打折</h4>
				
			</div>
				
			<div id="mbt_dejdz_head" style="float: right; width: 85%;">			
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
		if(function.equals("gengxin")||function.equals("copy")){
			enable = "";	
			//readable="";
		}
		String d_type = (String)request.getAttribute("d_type");
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
		String  aname = (String)request.getAttribute("d_name");
		 
		if(type.equals("copy")){
			aname +="(复制)";
		}
	%>
 <div class="panel panel-default">
 			<div id="mbt_dejdz_content_head" class="panel-heading">
 						<div class="panel-heading" id="mbt_dejdz_content_head">
 						活动名称：<input type="text" <%=enable %> value='<%=aname %>' id="mbt_dejdz_active_name">
 						开始时间:<input  type="text"  <%=enable %> value='<%=request.getAttribute("d_start_time") %>' <%=readable %> id="date_start" class="form_datetime"/>
 						结束时间：<input type="text" <%=enable %> value='<%=request.getAttribute("d_end_time") %>'   <%=readable %> id="date_stop"class="form_datetime"/ >
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
				<div id="mbt_dejdz_panel" class="col-sm-12 col-lg-12">
				  		<table class= "col-sm-12 col-lg-12 table tbd">
				  			<thead class="trBack ">
				  			<tr>
				  			<th >活动名称</th>
				  			<th>分类</th>
				  			<th >原价</th>
				  			<th >折扣</th>
				  		 <%if(type.equals("gengxin")||type.equals("copy")){
				  			 out.println("<th>操作<th>");
				  		 }%>		
				  			</tr>
				  			</thead>
				  			<tbody id='dejdz_daitianjiashangpinxianshi'>
				  				<%
				  					
				  					
				  					List <MarketingFunction> mlist = (List<MarketingFunction>)request.getAttribute("activelist"); 				  				
				  					for(Iterator<MarketingFunction> it = mlist.iterator();it.hasNext();){
				  						MarketingFunction m = it.next();
				  						out.println("<tr id = \"list_"+m.getG_id()+"\" value=\""+m.getG_id()+"\"><td>"+m.getG_name()+"</td>");
				  						out.println("<td>"+m.getC_name()+"</td>");
				  						out.println("<td>"+m.getG_price()+"</td>");
				  						if(type.equals("xiangxi")){
				  							out.println("<td> <input type = \"text\" disabled=\"disabled\" value = \""+m.getDiscount()+"\"/></td>");
				  						}else if (type.equals("gengxin")||type.equals("copy")){
				  							//out.println("<td>"+m.getCoupons()+"</td>");
				  							out.println("<td> <input class='cfnum' onblur=\"disinput("+m.getG_id()+");\"  type = \"text\"  value = \""+m.getDiscount()+"\"/></td>");
				  							out.println("<td><a  onclick=\"delGoodsInList("+m.getG_id()+");\" href=\"javascript:void(0)\" >删除</a></td>");
				  						}
				  						out.println("</tr>");
				  						
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

 