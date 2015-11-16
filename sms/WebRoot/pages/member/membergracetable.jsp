<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@page import="java.util.List"%>
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
<td><a class="shabiaoqian" id="<%=osj[0]%>" onclick="shmemgraceedit(<%=osj[0]%>);" >编辑</a></td>
<td><%=osj[1]%></td>
<td><%=osj[2]%>折</td>
<td><%=osj[4]%>积分提升一级</td>
</tr>
<%
	}
	
}

%>