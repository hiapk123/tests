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


<!-- 分页头部引用 -->
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page" %>
<table class="table table-bordered" >
<thead>
	<tr>
		<th>开始时间</th>
		<th>结束时间</th>
		<th>员工编号</th>
		<th>员工姓名</th>
		<th>收银单数</th>
		<th>收银总额</th>
		<th>现金数量</th>
		<th>刷卡数量</th>
		
	</tr>
</thead>
	<tbody>
	<% 
	List<Object[]> showloglist=(List<Object[]>)request.getAttribute("longlist3");
	if(showloglist!=null&showloglist.size()!=0)
	{
		for(Object[] obj:showloglist)
		{
			

	%>
	<tr>
		<td><%=obj[0] %></td>	
		<td><%=obj[1] %></td>	
		<td><%=obj[2] %></td>	
		<td><%=obj[3] %></td>	
		<td><%=obj[4] %></td>	
		<td><%=obj[5] %></td>	
		<td><%=obj[6] %></td>	
		<td><%=obj[7] %></td>	
		
	</tr>
<% 

	}
}

%>
	</tbody>

</table>