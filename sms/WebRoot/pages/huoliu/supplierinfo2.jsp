<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
String s_id=request.getAttribute("s_id").toString();

String s_name=request.getAttribute("s_name").toString();
String su_name=request.getAttribute("su_name").toString();
String su_phone=request.getAttribute("su_phone").toString();
String su_email=request.getAttribute("su_email").toString();
String su_contacter=request.getAttribute("su_contacter").toString();
String s_del=request.getAttribute("s_del").toString();
String su_ps_return=request.getAttribute("su_ps_return").toString();
String su_gd_return=request.getAttribute("su_gd_return").toString();
String su_number=request.getAttribute("su_number").toString();

String su_empower=request.getAttribute("su_empower").toString();
String su_address=request.getAttribute("su_address").toString();
String su_info=request.getAttribute("su_info").toString();


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
		<tr class="primary">
		<td><a>编辑</a></td>
		<%
			String arr[]={su_number,su_name,su_contacter,su_phone,su_email,su_empower};
			
				for (int i = 0; i < arr.length; i++) {
		%>
		
			<td> <%=arr[i] %></td>
		
		<%
			
			}
		%>
		</tr>
	</tbody>

	</table>
		</div>
		