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



//System.out.println(currentPage);
String method=application.getAttribute("method").toString();
if(!method.equals("findByPage")){
String sorted=application.getAttribute("sorted").toString();
}
%>

<script>

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
			$("#tableContent").empty();
			var currentPage=<%=currentPage%>;
		
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
				$("#tableContent").html(data);
			}, "html");
		});

		
</script>
<div  style="width:3000px;height:auto">

	<table class="table table-bordered table-condensed table-hover table-striped ">
		<thead>
			<tr class="danger">
				<th>操作</th>
				<th>商品名称</th>
				<th>所属门店</th>
				<th>商品条码</th>
				<th>库存量<button onclick="UP(this)" class=" btn btn-success btn-xs" value="g_stock_num">&uarr;</button><button onclick="DOWN(this)" class=" btn btn-success btn-xs" value="g_stock_num">&darr;</button></th>
				<th>进货价<button onclick="up(this)" class=" btn btn-success btn-xs" value="g_pur_price">&uarr;</button><button onclick="down(this)" class=" btn btn-success btn-xs" value="g_pur_price">&darr;</button></th>
				<th>销售价</th>
				<th>批发价</th>
				<th>分类</th>
				<th>单位</th>
				<th>单位数量</th>
				<th>库存下限</th>
				<th>库存上限</th>
				<th>生产日期</th>
				<th>保质期 ↑ ↓</th>
				<th>拼音码</th>
				<th>供货商</th>
				<th>是否锁定</th>
				<th>会员折扣</th>
				<th>会员价</th>  
				<th>自定义1</th>
				<th>自定义2</th>
				<th>自定义3</th>
				
				<th>自定义4</th>
				<th>最小起订量</th>
				<th>最低陈列量</th>
				<th>库存合理值</th>
				<th>畅销量</th>
				<th>正常销售量</th>
				
				
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
			<tr class="<%=yanse[i%5]%>">
				<td>
					<a   href="javascript:del()">删除</a>		
				<a   href="javascript:edit()">编辑</a>
				<input type="hidden" class="list" value="<%=list%>">
				<%
				
					for (int j = 1; j <9; j++) {
						if(String.valueOf(goods.get(i)[j]).equals("null")) goods.get(i)[j]="";
				%>
				
				<td><%=goods.get(i)[j]%> </td>
				<%
					}
				%>
				<%
				if(String.valueOf(goods.get(i)[32]).equals("null")) goods.get(i)[32]="";
				if(String.valueOf(goods.get(i)[33]).equals("null")) goods.get(i)[33]="";
				%>
				<td><%=goods.get(i)[32]%> </td>
				<td><%=goods.get(i)[33]%> </td>
				<%
				
					for (int j = 9; j <15; j++) {
						if(String.valueOf(goods.get(i)[j]).equals("null")) goods.get(i)[j]="";
				%>
				
				<td><%=goods.get(i)[j]%> </td>
				<%
					}
				%>
				<%
				
				if(String.valueOf(goods.get(i)[15]).equals("0")){
					goods.get(i)[15]="否";
				}else{
					goods.get(i)[15]="是";
				}
					
				%>
				<td><%=goods.get(i)[15]%> </td>
				
				<%
				
				if(String.valueOf(goods.get(i)[16]).equals("0")){
					goods.get(i)[16]="是";
				}else{
					goods.get(i)[16]="否";
				}
					
				%>
				<td><%=goods.get(i)[16]%> </td>
				<%
				
					for (int j = 17; j <27; j++) {
						
						if(String.valueOf(goods.get(i)[j]).equals("null")) goods.get(i)[j]="";
				%>
				
				<td><%=goods.get(i)[j]%> </td>
				<%
					}
				%>
			</tr>
			<%
				}
				}
			%>
		</tbody>
	</table>
	</div>
<input type="hidden" id="method" value="${method}" />
<input type="hidden" id="sorted" value="${sorted}" />

<ul class="pagination" id="page" style="position: absolute; bottom: 0px;">
	<page:htmlPage  pageNo="${currentPage}"
		url=""
		totalSum="${totalSize }" showPage="10" pageSize="10" />
</ul>