<%@ page language="java" pageEncoding="utf-8" import="java.util.*,org.uestc.util.DateFormatUtils"
	contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<script type="text/javascript">
<!--
	$(function(){
		$('li a').click(function(){
			var which = $(this).text()
			if (which === "首页") {
				which="first";
			} else if (which == "上一页") {
				which="prev";
			} else if (which === "下一页") {
				which="next";
			} else if (which === "尾页") {
				which="last";
			} 
			alert(which);
		});
	});
//-->
</script>

<table class="table">
	<tr>
		<th>订单编号</th>
		<th>所属店铺</th>
		
		<th>生成日期</th>
		<th>状态（未审核、通过、不通过）</th>
		<th>详情</th>
		<th>操作（过期的可以删除）</th>
	</tr>
	<% 
		List<Map> list=(List<Map>)request.getAttribute("list");
			for(int i=0;i<list.size();i++){ %>
				<tr>
					<td><%=list.get(i).get("b_no") %></td>
					<td><%=list.get(i).get("s_name") %></td>
					
					<td><%=DateFormatUtils.LongTimeToDate(Long.parseLong(list.get(i).get("b_date").toString())) %></td>
					<td><%=list.get(i).get("b_status") %></td>
					<td><a data-toggle="modal"
						data-target="#myModal">显示详细</a>
					</td>
					<td><a>删除</a></td>
				</tr>
			<%} %>
</table>

<input type="hidden" id="page" value="${pageNo }" />
<ul class="pagination">
	<page:htmlPage pageNo="${pageNo }" url="#" totalSum="${totalSum }" showPage="5"
		pageSize="10" />
</ul>