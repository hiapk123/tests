<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*,org.uestc.util.DateFormatUtils;"%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%String currentPage=request.getAttribute("currentPage").toString(); 
String s_id=request.getAttribute("s_id").toString();
String status=request.getAttribute("status").toString();
%>
<script>
	$(function() {
		$("li a").click(function() {
			var which = $(this).text();
			var s_id=$("#s_id").val();
			var status=$("#status").val();
			if (which === "首页") {
				which="first";
			} else if (which == "上一页") {
				which="prev";
			} else if (which === "下一页") {
				which="next";
			} else if (which === "尾页") {
				which="last";
			} 
			$("#fenye").empty();
			var currentPage=<%=currentPage%>;
		
			$.post("<%=basePath%>huoliu", {
				"which" : which,
				"s_id" : s_id,
				"m" : "findjs",
				"currentPage" : currentPage,
				"status" :status,
			}, function(data) {
				$("#fenye").html(data);
			}, "html");
		});
	});
        
</script>
 
<head>
<style>
table .ziti td{
font-size:15px;
WORD-WRAP: break-word;

}

</style>


</head>
<div id="fenye" style="width:1200px;height:auto">
<table class="table table-bordered table-condensed table-hover table-striped ">
		<thead>
			<tr>
			    <th><input class="checkAll" type="checkbox"/></th>
				<th>序号</th>
				<th>操作</th>
				<th>货流单号</th>
				<th>下单时间</th>
				<th>货单类型</th>
				
				<th>进货门店</th>
				<th>状态</th>
				<th>货流量</th>
				<th>总价</th>
				<th>预付款</th>
				<th>备注</th>
				
			</tr>
			<%
			
        List <Object[]> list=(List<Object[]>) request.getAttribute("list");
        String yanse[]={"success","danger","active","warning","info"};
        if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {%>
         	<tr class="<%= yanse[i%5]%>">
				<td><input class="subBox" type="checkbox"/></td>
				<td>序号</td>
				<td><button class="btn btn-primary"  onclick="getdetail(this)"  value="<%=list.get(i)[0]%>">详细</button></td>
				
				  
				<%
					for (int j = 1; j<10; j++) {
				%>
				<td> <%=list.get(i)[j]%> </td>
				<%
					}
				%>
				
			<input type="hidden" class="ss_detail"  value="<%=list.get(i)[0]%>">
			<input type="hidden" class="ss_id"  value="<%=list.get(i)[10]%>">
			
			<input type="hidden" class="ss_sid_in"  value="<%=list.get(i)[4]%>">
			<input type="hidden" class="ss_wl_serial"  value="<%=list.get(i)[1]%>">
			<input type="hidden" class="ss_wl_date"  value="<%=list.get(i)[2]%>">
			</tr>
			<%
				}
				}
			%>
			
		</thead>
		<tbody>
		
		
	</tbody>


	</table>
<ul class="pagination" id="page">
	<page:htmlPage  pageNo="${currentPage}"
		url=""
		totalSum="${totalSize }" showPage="10" pageSize="10" />
</ul>


</div>
<input type="hidden" id="s_id" value="<%=s_id%>" />
<input type="hidden" id="status" value="<%=status%>" />