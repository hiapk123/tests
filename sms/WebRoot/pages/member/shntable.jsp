<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<table class="table table-bordered" name="numgettable">
<thead>
	<tr>
		<th>会员序号</th>
		<th>操作</th>
		<th>会员号</th>
		<th>姓名</th>
		<th>电话</th>
		<th>会员等级</th>
		<th>余额</th>
		<th>积分</th>
		<th>开卡门店</th>
		<th>开卡时间</th>
	</tr>
</thead>
<tbody>
<%
	List<Object[]> shlist=(List<Object[]>)request.getAttribute("nlist");
	int shnumxh=0;
	if(shlist!=null&&shlist.size()!=0)
	{
		for(Object[] al:shlist)
		{
			shnumxh++;
	
%>
		<tr>
		<td><%=shnumxh %></td>
		<td>
		<a onclick="shbianji();" id="shmedit">编辑</a>
		<!-- //设置一个隐藏按钮的值 -->
		<input id="shycan" type="hidden" value="<%=al[0]%>">
		</td>
		<td><%=al[1]%></td>
		<td><%=al[2]%></td>
		<td><%=al[3]%></td>
		<td><%=al[4]%></td>
		<td><%=al[5] %></td>
		<td><%=al[6] %></td>
		<td><%=al[7] %></td>
		<td><%=al[8] %></td>
		</tr>
<% 
	}
		}
%>	
</tbody>

</table>