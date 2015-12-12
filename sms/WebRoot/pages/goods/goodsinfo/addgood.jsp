<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*,org.uestc.util.*"%>



<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<style>
/* .success{
background:green;
}
.danger{
background:pink;
} */
</style>
<script>
<%-- function edit(){
    
    var list=$("#list").val();
    $("#goodsinfodiv").empty();
    $("#11").empty();
	$.post("<%=basePath%>goods", {
		"m" : "editGood",
		"list" : list,
		
	}, function(data) {
		$("#goodsinfodiv").append(data);
	}, "html");
} --%>
function edit1(){
	var s_id=$("#s_id").val();
    var list=$("#list").val();
  // $("#tableContent").empty();
    $("#motai2").empty();
	$.post("<%=basePath%>goods", {
		"m" : "editGood",
		"list" : list,
		"s_id" : s_id,
	}, function(data) {
		$("#motai2").append(data);
	}, "html");
}


function detail1(node){
	 var list=$("#list").val();
	var s_id=$("#s_id").val();
   // var list=$("#list").val();
  // $("#tableContent").empty();
    $("#motai22").empty();
	$.post("<%=basePath%>goods", {
		"m" : "detail",
		"list" : list,
		"s_id" : s_id,
	}, function(data) {
		$("#motai22").append(data);
	}, "html");
}
function del(){
	var s_id=$("#s_id").val();
	var g_id=$("#g_id").val();
	$("#goodsinfodiv").empty();

	$.post("<%=basePath%>goods", {
		"m" : "deleteGood",
		"g_id" : g_id,
		"s_id" :s_id,
		"currentPage":"1",
	}, function(data) {
		$("#goodsinfodiv").append(data);
	}, "html");
	}
</script>


</head>


<table  class="table table-bordered table-condensed table-hover table-striped ">
	<thead>
		<tr class="default">
			<th>操作</th>
				<th>商品名称</th>
				<th>所属门店</th>
				<th>商品条码</th>
				<th>库存量</th>
				<th>进货价</th>
				<th>销售价</th>
				<th>批发价</th>
				<th>分类</th>
				<th>详细</th>
				
		</tr>
	</thead>
	<tbody>

		<%          
		           //必填资料9
		            String s_id = request.getParameter("s_id");
					String s_name = request.getParameter("s_name");
		            String g_name = request.getParameter("g_name");
		            String g_del = request.getParameter("g_del");
		            String g_stock_num = request.getParameter("g_stock_num");
		            String g_sale_price = request.getParameter("g_sale_price");
		            String g_pur_price = request.getParameter("g_pur_price");
		            String c_name = request.getParameter("c_name");
		            String g_barcode = request.getParameter("g_barcode");
		          //扩展资料13
		             String g_pm = request.getParameter("g_pm");
					String su_name = request.getParameter("su_name");
		            String g_stock_max = request.getParameter("g_stock_max");
		            String g_stock_min = request.getParameter("g_stock_min");
		            String g_trade_price = request.getParameter("g_trade_price");
		            String vip_id = request.getParameter("vip_id");
		            String g_vip_price = request.getParameter("g_vip_price");
		            String g_prod_date = request.getParameter("g_prod_date");
		            String g_giq = request.getParameter("g_giq");
		            String zdy1 = request.getParameter("zdy1");
		            String zdy2 = request.getParameter("zdy2");
		            String zdy3 = request.getParameter("zdy3");
		            String zdy4 = request.getParameter("zdy4");
		          //报表参数6
		            String g_qd_min = request.getParameter("g_qd_min");
		            String g_cl_min = request.getParameter("g_cl_min");
		            String g_stock_nor = request.getParameter("g_stock_nor");
		            String g_flag = request.getParameter("g_flag");
		            String g_best = request.getParameter("g_best");
		            String g_sale_nor = request.getParameter("g_sale_nor");
		        	//商品描述
		    		String g_info=request.getParameter("g_info");
		    		//图片路径
		    		String g_img_path=request.getParameter("g_img_path");
		    		String g_integral=request.getParameter("g_integral");
		    		String c_id=request.getParameter("c_id");
		    		
		    		String g_unit=request.getParameter("g_unit");
		    		String g_howmuch=request.getAttribute("g_howmuch").toString();
		    		String unit_id=request.getAttribute("unit_id").toString();
		            String sql="select g_id from goods where s_id=? and g_barcode=?";
		    		List<Object[]> a = SqlHelper.find(sql, s_id,g_barcode);
		    		Number num = (Number) a.get(0)[0];  
		    	   int  Num = num.intValue();
		    		String g_id=String.valueOf(Num);
		    		String arr[]={g_id,g_name,s_name,g_barcode,g_stock_num,g_pur_price,g_sale_price, //7
		    				g_trade_price,c_name,g_stock_min,g_stock_max,g_prod_date,g_giq,       //6
		    				g_pm,su_name,g_flag,vip_id,g_vip_price,                    //5
		    				zdy1,zdy2,zdy3,zdy4,g_qd_min,g_cl_min,g_stock_nor,                //7
		    				g_best,g_sale_nor,g_del,g_info,g_img_path,g_integral,c_id,g_unit,g_howmuch,unit_id                            //5
		    		};
		    		
		    		String list="";   //如果此次用逗号会引起按钮不能触发的BUG
		               
                	for (int j = 0; j <35; j++) {
                		if(String.valueOf(arr[j]).equals(""))
                		{list=list+" ,";
                		
                		}else{
                		list=list+String.valueOf(arr[j])+",";
                		}
                }
			
				%>
				<input type="hidden" id="list" value="<%=list%>">
				<input type="hidden" id="s_id" value="<%=s_id%>">
				<input type="hidden" id="g_id" value="<%=arr[0]%>">
		<tr class="default">
			<td class="center"><a   href="javascript:del()">删除</a>	 
			<a  data-toggle="modal" data-target="#myModal2" onclick="edit1()">编辑</a>
<br>
			
			
			<%
			for(int i=1;i<9;i++){
				
			
			%>	
			<td><%=arr[i]%></td>		
			
			<%
			}
			%>
			
			<td><a  data-toggle="modal" data-target="#myModal22" onclick="detail1(this)">详细</a></td>
			
			
		
			
			
			
			
			
			
		</tr>
		

	</tbody>

</table>

<input type="hidden" id="page" value="20"/>
<%-- <ul class="pagination" id="page">
	<page:htmlPage pageNo="${currentPage }"
		url="/goods?m=findGoodByPage&currentPage=${currentPage }"
		totalSum="${totalPage }" showPage="10" pageSize="10" />
</ul> --%>

<%-- <a href="<%=basePath%>goods?m=goodsInfo">返回商品资料页面</a> --%>
