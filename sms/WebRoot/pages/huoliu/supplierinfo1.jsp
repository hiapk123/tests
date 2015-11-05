<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div style="width: 100%;height:400px;">
		<table class="table table-bordered table-condensed table-hover table-striped ">
		<thead>
			<tr class="info">
				<th>操作</th>
				<th>编号</th>
				<th>名称</th>
				<th>联系人</th>
				<th>电话</th>
				<th>邮箱</th>
				<th>授权状态</th>
			</tr>
		</thead>
		<tbody>
		<%
			List<Object[]> supplier = (List<Object[]>) request.getAttribute("list");

			String s_id = request.getParameter("s_id").toString();
			String yanse[]={"success","danger","active","warning","info"};
			if (supplier != null && supplier.size() > 0) {
				for (int i = 0; i < supplier.size(); i++) {
		%>
		<tr class="<%= yanse[i%5]%>">
			<td> <a  onclick="editsupplier(this)" data-toggle="modal" 
   data-target="#myModal2">编辑</a></td>
				<input type="hidden" class="su_id" value="<%=supplier.get(i)[6]%>">	
					<%
			
				for (int j = 0; j <= 5; j++) {
			%>
			<td><%=supplier.get(i)[j]%></td>
			<%
				}
			%>
		</tr>
		<%
			}
			}
		%>
	</tbody>

	</table>
		</div>