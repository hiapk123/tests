<%@page import="org.w3c.dom.ls.LSInput"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*,org.uestc.util.DateFormatUtils"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//request.getRequestDispatcher(action).forward(request, response);
%>
<link href="<%=basePath%>pages/sales/saleinfo/th.css" rel="stylesheet">

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
		<%
			List<Object[]> cash = (List<Object[]>) request.getAttribute("cashList");
			if (cash != null && cash.size() > 0) {
				for (int i = 0; i < cash.size(); i++) {
		%>
		<tr>
			<td><%=DateFormatUtils.LongTimeToDate(Long.valueOf(cash.get(i)[0].toString())) %></td>
			<td><%=cash.get(i)[1] %></td>
			<td><%=cash.get(i)[2] %></td>
			<td><%=cash.get(i)[3] %></td>
			<td><%=cash.get(i)[4] %></td>
		</tr>
		<%
			}
			}
		%>

	</tbody>
</table>