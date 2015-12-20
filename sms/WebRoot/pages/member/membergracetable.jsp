<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@page import="java.util.List"%>

<table class="table table-striped table-bordered bootstrap-datatable datatable responsive dataTable" name="numgettable">
		<thred>
			<tr>
				<th><font size="3" style="text-align: center;">序号</font></th>
				<th><font size="3" style="text-align: center;">操作</font></th>
				<th><font size="3" style="text-align: center;">等级名称</font></th>
				<th><font size="3" style="text-align: center;">优惠折扣</font></th>
				<th><font size="3" style="text-align: center;">自动提升规则</font></th>
			</tr>
		</thred>
<tbody>
<%
List<Object[]> memgraceaccept=(List<Object[]>)request.getAttribute("shmemgrace");
if(memgraceaccept!=null&&memgraceaccept.size()!=0)
{
	int sssk=0;
	for(Object[] osj:memgraceaccept)
	{
		sssk++;
	
%>
<tr>
<td><%=sssk %></td>
<td><a class="shabiaoqian" id="<%=osj[0]%>" onclick="shmemgraceedit(<%=osj[0]%>);" ><font size="3" style="text-align: center;">编辑</font></a></td>
<td><font size="3" style="text-align: center;"><%=osj[1]%></font></td>
<td><font size="3" style="text-align: center;"><%=osj[2]%>折</font></td>
<td><font size="3" style="text-align: center;"><%=osj[4]%>积分提升一级</font></td>
</tr>
<%
	}
	
}

%>
</tbody>
</table>