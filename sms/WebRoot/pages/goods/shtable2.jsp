<%@page import="com.uestc.bean.batchgoods"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//request.getRequestDispatcher(action).forward(request, response);
%>

<table class="table table-bordered" id="pagetable">
<thead>
<tr>
	<th><input type="checkbox"></th>
	<th>序号</th>
	<th>商品名称</th>
	<th>条码</th>
	<th>商品分类</th>
	<th>会员优惠</th>
	<th>供货商</th>
	<th>状态</th>
	<th>积分</th>
	<th>提成规则</th>
</tr>
</thead>
<tbody>
<%
	List<batchgoods> showlist=(List<batchgoods>)request.getAttribute("shlist");
	if(showlist!=null&&showlist.size()!=0){
	for(batchgoods obj:showlist){
	
%>
<tr>
	<td><input type="checkbox"></td>
	<td><%=obj.getG_id() %></td>
	<td><%=obj.getG_name() %></td>
	<td><%=obj.getG_barcode() %></td>
	<td><%=obj.getC_id() %></td>
	<td><%=obj.getG_vip_price() %></td>
	<td><%=obj.getSu_name() %></td>
	<td><%=obj.getG_flag() %></td>
	<td><%=obj.getG_integral() %></td>
	<td><%=obj.getCome_name() %></td>
</tr>	
	<%
		}
	}
	%>
</tbody>
</table>

