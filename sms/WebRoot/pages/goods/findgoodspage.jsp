<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*,org.uestc.util.DateFormatUtils"%>
	<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
String currentPage=request.getAttribute("currentPage").toString();
String key=request.getAttribute("key").toString();
String c_name=request.getAttribute("c_name").toString();

String g_del=request.getAttribute("g_del").toString();
String method=application.getAttribute("method").toString();

if(!method.equals("findByPage")){
String sorted=request.getAttribute("sorted").toString();
}
%>

 <script>
$(function(){
$("li a").click(function() {
	
	 var key=$("#key").val();
	 
	 var c_name=$("#fenlei").val();
	 
	 var g_del=$("#g_del").val();
	var m=$("#method").val();
	var sorted=$("#sorted").val();
	var which = $(this).text();
	var s_id=$("#s_id").val();
	if (which === "首页") {
		which="first";
	} else if (which == "上一页") {
		which="prev";
	} else if (which === "下一页") {
		which="next";
	} else if (which === "尾页") {
		which="last";
	} 
	var currentPage=$("#currentPage").val();
	
	$("#goodsinfodiv").empty();
	
	$.post("<%=basePath%>goods", {
		"which" : which,
		"s_id" : s_id,
		"key" : key,
		"c_name" : c_name,
		"g_del" : g_del,
		"m" : m,
		"sorted":sorted,
		"currentPage" : currentPage,
	}, function(data) {
		$("#goodsinfodiv").html(data);
	}, "html");
});
});
		
</script>


	<table class="table table-bordered table-condensed table-hover table-striped ">
		<thead>
			<tr class="default">
				<th>操作</th>
				<th>商品名称</th>
				<th>所属门店</th>
				<th>商品条码</th>
				<th>库存量<button onclick="UP(this)" class=" btn btn-success btn-xs" value="g_stock_num">&uarr;</button><button onclick="DOWN(this)" class=" btn btn-success btn-xs" value="g_stock_num">&darr;</button></th>
				<th>进货价<button onclick="up(this)" class=" btn btn-success btn-xs" value="g_pur_price">&uarr;</button><button onclick="down(this)" class=" btn btn-success btn-xs" value="g_pur_price">&darr;</button></th>
				<th>销售价</th>
				<th>批发价</th>
				<th>分类</th>
				<th>详细</th>
				
				
				
			</tr>
		</thead>
		<tbody>
			<%
			List<Object[]> goods = (List<Object[]>) request.getAttribute("goodsList");

				String s_id = request.getParameter("s_id").toString();
				String yanse[]={"success","danger","active","warning","info"};
				if (goods != null && goods.size() > 0) {
					for (int i = 0; i < goods.size(); i++) {
						String list="";   //如果此次用逗号会引起按钮不能触发的BUG
			               
	                	for (int j = 0; j <35; j++) {
	                		if(String.valueOf(goods.get(i)[j]).equals(""))
	                		{list=list+" ,";
	                		
	                		}else{
	                		list=list+String.valueOf(goods.get(i)[j])+",";
	                		}
	                }
					%>
				
					<input type="hidden" id="g_id" value="<%=goods.get(i)[0]%>">
			<tr class="default">
				<td>
				<a   onclick="del()">删除</a>		
				<a  data-toggle="modal" data-target="#myModal2" onclick="edit(this)">编辑</a>
				<input type="hidden" class="list" value="<%=list%>">
				<%
				
					for (int j = 1; j <9; j++) {
						if(String.valueOf(goods.get(i)[j]).equals("null")) goods.get(i)[j]="";
				%>
				
				<td><%=goods.get(i)[j]%> </td>
				
				<%
					}
				%>
				<td><a  data-toggle="modal" data-target="#myModal22" onclick="detail(this)">详细</a></td>
			</tr>
			<%
				}
				}
			%>
		</tbody>
	</table>
	<input type="hidden" id="s_id" value="<%=s_id%>" />
<input type="hidden" id="key" value="<%=key%>" />
<input type="hidden" id="fenlei" value="<%=c_name%>" />
<input type="hidden" id="g_del" value="<%=g_del%>" />
<input type="hidden" id="method" value="${method }" />
<input type="hidden" id="sorted" value="${sorted }" />
<input type="hidden" id="currentPage" value="<%=currentPage%>" />
<%-- <input type="hidden" id="method" value="${method}" />
<input type="hidden" id="sorted" value="${sorted}" />
<input type="hidden" id="currentPage" value="<%=currentPage%>" /> --%>
<ul class="pagination" id="page" style="position: absolute; bottom: 70px;">
	<page:htmlPage  pageNo="${currentPage}"
		url=""
		totalSum="${totalSize }" showPage="10" pageSize="10" />
</ul>