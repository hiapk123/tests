<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>

<%
List<Object[]> shtables=(List<Object[]>)request.getAttribute("shlistd");
String shtatue="";
int shmun=0;
if(shtables!=null&&shtables.size()!=0)
{
	for(Object[] all:shtables)
	{
		shmun++;
		System.out.print("dasdasdasd"+all[0]);
		if(Integer.parseInt(all[5].toString())==0)
		{
			shtatue="启用";
		}
		else{
			shtatue="禁用";
			
		}
%>
<tr id="<%=all[0] %>">
<td ><%=shmun %></td>
<td><a id="<%=all[0] %>" class="shclassofname"  onclick="empleebjmodel(<%=all[0]%>);">编辑</a></td>
<td><%=all[1] %></td>
<td><%=all[2] %></td>
<td><%=all[3] %></td>
<td><%=all[4] %></td>
<td><%=shtatue %></td>
</tr>
<%
	}
}
%>
