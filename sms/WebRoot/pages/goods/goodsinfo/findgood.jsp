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
    String method=application.getAttribute("method").toString();
   
    
    
%>


<script>
	$(function() {
		$('.pagination').click(function() {
			//alert(method);
		});
		$("li a").click(function() {
			
			
			var m=$("#method").val();
			alert("滴天髓"+m);
			var sorted=$("#sorted").val();
			var which = $(this).text();
			var storeID=$("#storeID").val();
			if (which === "首页") {
				which="first";
			} else if (which == "上一页") {
				which="prev";
			} else if (which === "下一页") {
				which="next";
			} else if (which === "尾页") {
				which="last";
			} else {
			}
			$("#tableContent").empty();
			var pageNo=$("#page").val()
			$.post("<%=basePath%>goods", {
				"which" : which,
				"store" : storeID,
				"m" : m,
				"sorted":sorted,
				"currentPage" : pageNo
			}, function(data) {
				$("#tableContent").html(data);
			}, "html");
		});
		$("#up-g_stock_num").click(function(){
			var which = $(this).text();
			var storeID=$("#storeID").val();
			var sorted=$("#up-g_stock_num").val();
			if (which === "首页") {
				which="first";
			} else if (which == "上一页") {
				which="prev";
			} else if (which === "下一页") {
				which="next";
			} else if (which === "尾页") {
				which="last";
			} else if(which=="↑") {
				which="first";
			} 
			$("#tableContent").empty();
			var pageNo=$("#page").val()
			$.post("<%=basePath%>goods", {
				"m" : "upsort",
				"which" : which,
				"store" : storeID,
				"sorted" : sorted,
				"currentPage" : pageNo
			}, function(data) {
				$("#tableContent").append(data);
			}, "html");
		});
         $("#down-g_stock_num").click(function(){
        	var sorted=$("#down-g_stock_num").val();
			var which = $(this).text();
			var storeID=$("#storeID").val();
			if (which === "首页") {
				which="first";
			} else if (which == "上一页") {
				which="prev";
			} else if (which === "下一页") {
				which="next";
			} else if (which === "尾页") {
				which="last";
			}  else if(which=="↓") {
				which="first";
			}
			$("#tableContent").empty();
			var pageNo=$("#page").val()
			$.post("<%=basePath%>goods", {
				"m" : "downsort",
				"which" : which,
				"store" : storeID,
				"sorted" : sorted,
				"currentPage" : pageNo,
			}, function(data) {
				$("#tableContent").append(data);
			}, "html");
		});
	});
</script>
<script>
	$(function() {
		
		$("#up").click(function(){
			var which = $(this).text();
			var storeID=$("#storeID").val();
			var sorted=$("#up").val();
			if (which === "首页") {
				which="first";
			} else if (which == "上一页") {
				which="prev";
			} else if (which === "下一页") {
				which="next";
			} else if (which === "尾页") {
				which="last";
			} else if(which=="↑") {
				which="first";
			} 
			$("#tableContent").empty();
			var pageNo=$("#page").val()
			$.post("<%=basePath%>goods", {
				"m" : "upsort",
				"which" : which,
				"store" : storeID,
				"sorted" : sorted,
				"currentPage" : pageNo
			}, function(data) {
				$("#tableContent").append(data);
			}, "html");
		});
         $("#down").click(function(){
        	var sorted=$("#down").val();
			var which = $(this).text();
			var storeID=$("#storeID").val();
			if (which === "首页") {
				which="first";
			} else if (which == "上一页") {
				which="prev";
			} else if (which === "下一页") {
				which="next";
			} else if (which === "尾页") {
				which="last";
			}  else if(which=="↓") {
				which="first";
			}
			$("#tableContent").empty();
			var pageNo=$("#page").val()
			$.post("<%=basePath%>goods", {
				"m" : "downsort",
				"which" : which,
				"store" : storeID,
				"sorted" : sorted,
				"currentPage" : pageNo,
			}, function(data) {
				$("#tableContent").append(data);
			}, "html");
		});
	});
</script>
<div id="tableContent">
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
				<th>操作</th>
				<th>商品名称</th>
				<th>所属门店</th>
				<th>销售价</th>
				<th>批发价</th>
				<th>库存量<button id="up-g_stock_num" class="btn btn-success btn-xs" value="g_stock_num">&uarr;</button>&nbsp;&nbsp;<button id="down-g_stock_num" class="btn btn-success btn-xs" value="g_stock_num">&darr;</button></th>
				<th>进货价<button id="up" class="btn btn-success btn-xs" value="g_pur_price">&uarr;</button>&nbsp;&nbsp;<button id="down" class="btn btn-success btn-xs" value="g_pur_price">&darr;</button></th>
				<th>会员价</th>  
				<th>分类</th>
				<th>条码</th>
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

				String storeID = request.getParameter("store").toString();
				
				if (goods != null && goods.size() > 0) {
					for (int i = 0; i < goods.size(); i++) {%>
			<tr>
				<td><a
					href="<%=basePath%>goods?m=deleteGood&g_id=<%=goods.get(i)[3]%>">
						删除 </a> <a
					href="<%=basePath%>goods?m=editGood&g_id=<%=goods.get(i)[3]%>&s_name=<%=goods.get(i)[1]%>&g_name=<%=goods.get(i)[0]%>&s_id=<%=storeID%>">编辑</a>
					<a href="#"> 图片 </a></td>
				<%
					for (int j = 0; j <= 5; j++) {
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
	<input type="hidden" id="page" value="${currentPage }" />
</div>
<input type="hidden" id="storeID" value="<%=storeID%>" />
<input type="hidden" id="sorted" value="${sorted }" />
<ul class="pagination" id="page">
	<page:htmlPage pageNo="${currentPage }"
		url=""
		totalSum="${totalPage }" showPage="10" pageSize="10" />
</ul>


