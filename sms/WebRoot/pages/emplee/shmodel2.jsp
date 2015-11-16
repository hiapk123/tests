<%@page import="org.apache.el.lang.ELSupport"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>

 <%@page import="org.uestc.serviceImp.MemInformServiceImp"%>       	
	
<%
List<Object[]> showemperformation=(List<Object[]>)request.getAttribute("shempperformthetable");
if(showemperformation!=null&&showemperformation.size()!=0)
{
	int node=0;
	float sums=0;
	String shshouyinyuanname="";
	String shclxin="";
	for(Object[] obj:showemperformation)
	{
		node++;
		float sx=Float.parseFloat(obj[4].toString());
		float sy=Float.parseFloat(obj[5].toString());
		sums=sx*sy;	
		//将obj【2】的id号码转化成收银员的姓名
		int shkid=Integer.parseInt(obj[2].toString());
		String shswichsql="select emp_name from employee where emp_id="+shkid;
		List<Object[]> empname=(List<Object[]>)new MemInformServiceImp().normalfinad(shswichsql);
		Object[] shobj2=empname.get(0);
		shshouyinyuanname=shobj2[0].toString();//收银员名字
		if("1".equals(obj[8].toString()))
		{
			shclxin="现金支付";
		}
		else if("2".equals(obj[8].toString()))
		{
			shclxin="银联卡支付";
		}
		else if("3".equals(obj[8].toString()))
		{
			shclxin="在线支付";
		}
		else
		{
			shclxin="混合支付";
		}
		
%>
<tr>
<td><%=node %></td>
<td><%=obj[1] %></td>
<td><%=shshouyinyuanname %></td>
<td><%=obj[3] %></td>
<td><%=obj[4] %></td>
<td><%=obj[5] %></td>
<td><%=sums %></td>
<td><%=obj[6] %></td>
<td><%=obj[7] %></td>
<td><%=shclxin %></td>
</tr>	
<%
	}
}

%>
