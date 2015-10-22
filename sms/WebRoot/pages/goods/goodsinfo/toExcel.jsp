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
String as = "我明白了.xls";
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
				
				<th>商品名称</th>
				<th>门店名称</th>
				<th>商品条码</th>
				
			</tr>
		</thead>
		<tbody>

			<%
				
			List <Object[]> goods = (List<Object[]>) request.getAttribute("goodsList");

				String s_id = request.getParameter("s_id").toString();
				
				if (goods != null && goods.size() > 0) {
					for (int i = 0; i < goods.size(); i++) {%>
			<tr>
				
				<%
					for (int j = 0; j <= 2; j++) {
				%>
				<td><%=goods.get(i)[j]%> </td>
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