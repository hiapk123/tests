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
				<th></th>
				<th>概况</th>
				<th>现金支付</th>
				<th>银联卡支付</th>
				<th>网银支付</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>商品销售</td>
				<td>销售额：${xiaoshou}，利润：${lirun }，单数：${xiaoshoucount }</td>
				<td class="center">${cash }</td>
				<td class="center">${bank }</td>
				<td class="center">${online }</td>
			</tr>
			<tr>
				<td>会员卡充值</td>
				<td>充值：${vipin }，赠送：${vipout }，单数：${chongzhicount }</td>
				<td class="center">${vipCash }</td>
				<td class="center">${vipBank }</td>
				<td class="center">${vipOnline }</td>
			</tr>
			<tr>
				<td>现金收支</td>
				<td class="center">收入：${shouru }，支出：${zhichu }</td>
				<td class="center">-</td>
				<td class="center">-</td>
				<td class="center">-</td>
			</tr>

			<tr>
				<td>总计</td>
				<td class="center">${lirun+shouru-zhichu+vipin-vipout }</td>
				<td class="center">--</td>
				<td class="center">0</td>
				<td class="center">0</td>
			</tr>
		</tbody>
	</table>
