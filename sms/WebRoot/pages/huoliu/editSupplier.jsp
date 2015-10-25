<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
<form action="<%=basePath%>huoliu?m=editSupplier2" method="post">
		<table style="width:1200px; height:30px;  table-layout:fixed;" border="1" ;>
		<thead>
			<tr>
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
		String su_id=request.getParameter("su_id");
		String su_number=request.getParameter("su_number");
		String su_name=request.getParameter("su_name");
		String su_contacter=request.getParameter("su_contacter");
		String su_phone=request.getParameter("su_phone");
		String su_email=request.getParameter("su_email");
		String su_empower=request.getParameter("su_empower");
		String s_id=request.getParameter("s_id");
		%>	
		<td><input type="hidden" name="s_id" value="<%=s_id%>"></td>
		<td><input type="hidden" name="su_id" value="<%=su_id%>"></td>
		<td><input type="text" name="su_number" value="<%=su_number%>"></td>
		<td><input type="text" name="su_name" value="<%=su_name%>"></td>
		<td><input type="text" name="su_contacter" value="<%=su_contacter%>"></td>
		<td><input type="text" name="su_phone" value="<%=su_phone%>"></td>
		<td><input type="text" name="su_email" value="<%=su_email%>"></td>
		<td><input type="text" name="su_empower" value="<%=su_empower%>"></td>
	</tbody>

	</table>
<button type="submit" class="btn btn-success center-block"name="submit"">保存</button>
	</form>
</body>
</html>