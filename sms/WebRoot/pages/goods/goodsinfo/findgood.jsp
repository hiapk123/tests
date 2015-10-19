<%@page import="org.apache.tomcat.util.net.SecureNio2Channel.ApplicationBufferHandler"%>
<%@page import="org.apache.catalina.connector.Request"%>
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
	
    String method=application.getAttribute("method").toString();
%>
<script>
	$(function() {
		$("li a").click(function() {
			
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
				"m" : m,
				"sorted":sorted,
				"currentPage" : currentPage,
			}, function(data) {
				$("#tableContent").html(data);
			}, "html");
		});
	});
        
</script>
<script>
function UP(node){
	 var s_id=$("#s_id").val();
	var sorted=$(node).val();
	$("#tableContent").empty();
	var currentPage=<%=currentPage%>;
	$.post("<%=basePath%>goods", {
		"m" : "upsort",
		"which" : "first",
		"s_id" : s_id,
		"sorted" : sorted,
		"currentPage" : currentPage,
	}, function(data) {
		$("#tableContent").append(data);
	}, "html");
}
function DOWN(node){
	var sorted=$(node).val();
	var s_id=$("#s_id").val();
	$("#tableContent").empty();
	var currentPage=<%=currentPage%>;
	$.post("<%=basePath%>goods", {
		"m" : "downsort",
		"which" : "first",
		"s_id" : s_id,
		"sorted" : sorted,
		"currentPage" : currentPage,
	}, function(data) {
		$("#tableContent").append(data);
	}, "html");
}
</script>
<script>
function up(node){
	var s_id=$("#s_id").val();
	var sorted=$(node).val();
	$("#tableContent").empty();
	var currentPage=<%=currentPage%>;
	
	$.post("<%=basePath%>goods", {
		"m" : "upsort",
		"which" : "first",
		"s_id" : s_id,
		"sorted" : sorted,
		"currentPage" : currentPage,
	}, function(data) {
		$("#tableContent").append(data);
	}, "html");
}
function down(node){
	var sorted=$(node).val();
	var s_id=$("#s_id").val();
	$("#tableContent").empty();
	var currentPage=<%=currentPage%>;
	$.post("<%=basePath%>goods", {
		"m" : "downsort",
		"which" : "first",
		"s_id" : s_id,
		"sorted" : sorted,
		"currentPage" : currentPage,
	}, function(data) {
		$("#tableContent").append(data);
	}, "html");
}
</script>



<div id="tableContent">

	<table style="width:3000px; height:30px;  table-layout:fixed;" border="1" ;>
		<thead>
			<tr>
				<th>操作</th>
				<th>商品名称</th>
				<th>所属门店</th>
				<th>商品条码</th>
				<th>销售价</th>
				<th>库存量<button onclick="UP(this)" class=" btn btn-success btn-xs" value="g_stock_num">&uarr;</button><button onclick="DOWN(this)" class=" btn btn-success btn-xs" value="g_stock_num">&darr;</button></th>
				<th>进货价<button onclick="up(this)" class=" btn btn-success btn-xs" value="g_pur_price">&uarr;</button><button onclick="down(this)" class=" btn btn-success btn-xs" value="g_pur_price">&darr;</button></th>
				<th>会员价</th>  
				<th>分类</th>
				<th>批发价</th>
				<th>会员折扣</th>
				<th>库存上限</th>
				<th>库存下限</th>
				<th>生产日期</th>
				<th>保质期 ↑ ↓</th>
				<th>拼音码</th>
				<th>供货商</th>
				<th>自定义1</th>
				<th>自定义2</th>
				<th>自定义3</th>
				<th>自定义4</th>
				<th>最小起订量</th>
				<th>最低陈列量</th>
				<th>畅销量</th>
				<th>正常销售量</th>
				<th>库存合理值</th>
				<th>是否锁定</th>
			</tr>
		</thead>
		<tbody>
			<%
			List<Object[]> goods = (List<Object[]>) request.getAttribute("goodsList");

				String s_id = request.getParameter("s_id").toString();
				
				if (goods != null && goods.size() > 0) {
					for (int i = 0; i < goods.size(); i++) {%>
			<tr>
				<td><a
					href="<%=basePath%>goods?m=deleteGood&g_id=<%=goods.get(i)[6]%>">
						删除 </a><br> <a
					href="<%=basePath%>goods?m=editGood&g_barcode=<%=goods.get(i)[11]%>&g_id=<%=goods.get(i)[0]%>&s_name=<%=goods.get(i)[3]%>&g_name=<%=goods.get(i)[1]%>&s_id=<%=s_id%>">编辑</a>
					<br><a href="#"> 图片 </a></td>
				<%
					for (int j = 1; j <= 26; j++) {
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
<input type="hidden" id="method" value="${method }" />
<input type="hidden" id="sorted" value="${sorted }" />
	
<ul class="pagination" id="page">
	<page:htmlPage  pageNo="${currentPage}"
		url=""
		totalSum="${totalSize }" showPage="10" pageSize="10" />
</ul>
</div>
<input type="hidden" id="s_id" value="<%=s_id%>" />





