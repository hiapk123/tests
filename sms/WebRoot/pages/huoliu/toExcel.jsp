<%@page import="org.apache.tomcat.util.net.SecureNio2Channel.ApplicationBufferHandler"%>
<%@page import="org.apache.catalina.connector.Request"%>
<%@ page language="java" import="java.sql.*,java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
String as = "供货商资料导出表.xls";
String fileName = as;


fileName = new String(as.getBytes("utf8"), "ISO_8859_1");

response.setHeader("Content-disposition", "attachment; filename="+fileName);
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>Insert title here</title>
</head>

<body>
   
	<table style="width:3000px; height:30px;  table-layout:fixed;" border="1" ;>
		<thead>
			<tr>
				<th>供货商编号</th>
				<th>供货商名称</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>邮箱</th>
				<th>状态</th>
				<th>配送费返点</th>
				<th>固定返利点</th>
				<th>地址</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>

			<%
				
			List <Object[]> suppliers = (List<Object[]>) request.getAttribute("supplierList");

				String s_id = request.getParameter("s_id").toString();
				
				if (suppliers != null && suppliers.size() > 0) {
					for (int i = 0; i < suppliers.size(); i++) {%>
			<tr>
				
				<%
					for (int j = 0; j <10; j++) {
				%>
				<td><%=suppliers.get(i)[j]%> </td>
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
</body>
</html>