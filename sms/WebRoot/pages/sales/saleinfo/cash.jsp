<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//request.getRequestDispatcher(action).forward(request, response);
%>
<link href="<%=basePath %>pages/sales/saleinfo/th.css" rel="stylesheet">

<table class="table table-bordered">
	<thead>
		<tr>
			<th>时间</th>
			<th>收银员</th>
			<th>金额</th>
			<th>类型</th>
			<th>备注</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
		</tr>
	</tbody>
</table>