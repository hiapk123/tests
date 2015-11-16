<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//request.getRequestDispatcher(action).forward(request, response);
%>
<link href="<%=basePath%>pages/sales/saleinfo/th.css" rel="stylesheet">
<script>
	$(function() {
		$('.pagination').click(function(){
			alert($("#page").val());
		});
	});
</script>


<div class="panel panel-default">
	<div class="panel-body">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>操作 <span id="openAndClose">显示/隐藏</span></th>
					<th>流水号</th>
					<th>日期</th>
					<th>类型</th>
					<th>收银员</th>
					<th>会员</th>
					<th>商品数量</th>
					<th>商品原价</th>
					<th>实收金额</th>
					<th>利润</th>
					<th>导购员</th>
				</tr>
			</thead>
			<tbody>
			<%for(int i=0;i<10;i++){ %>
				<tr>
					<td><span class="openAndClose">明细</span><span class="print">打印</span></td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
				</tr>
				<tr class="table-item">
					<td colspan="3" rowspan="3">0</td>
					<td colspan="3">0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
				</tr>
				<tr class="table-item">
					<td colspan="3">0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
				</tr>
				<tr class="table-item">
					<td colspan="3">0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
				</tr>
				<%} %>
				
			</tbody>
		</table>
		<input type="hidden" id="page" value="20"/>
		<ul class="pagination">
			<page:htmlPage pageNo="20" url="www.baidu.com" totalSum="980" showPage="10" pageSize="10"/>
		</ul>
		<%-- <page:htmlPage pageNo="${pageNo}" url="your_url" totalSum="${totalSum}" showPage="10" pageSize="12"/> --%>
	</div>
</div>


