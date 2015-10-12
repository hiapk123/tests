<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*,com.uestc.bean.*"%>
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
			<th>开始时间</th>
			<th>结束时间</th>
			<th>收银员</th>
			<th>收银总额</th>
			<th>现金</th>
			<th>银联卡</th>
			<th>网银</th>
			<th>备用金</th>
		</tr>
	</thead>
	<tbody>
		<%
			List<ShiftChange> shlist = (List<ShiftChange>) request.getAttribute("shList");
			if (shlist != null && shlist.size() > 0) {
				for (ShiftChange shiftChange : shlist) {
		%>
		<tr>
			<td><%=shiftChange.shiftStartDate%></td>
			<td><%=shiftChange.shiftEndDate%></td>
			<td><%=shiftChange.empName%></td>
			<td><%=shiftChange.shiftTotalMoney%></td>
			<td><%=shiftChange.cash%></td>
			<td><%=shiftChange.bank%></td>
			<td><%=shiftChange.online%></td>
			<td><%=shiftChange.pettyCash%></td>
		</tr>
		<%
			}
			}
		%>

	</tbody>
</table>