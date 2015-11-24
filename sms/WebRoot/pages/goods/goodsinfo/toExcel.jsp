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
String as = "商品资料导出表.xls";
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
				<th>分类</th>
				<th>商品条码</th>
				<th>库存量</th>
				<th>进货价</th>
				<th>销售价</th>
				<th>批发价</th>
				<th>会员价</th>
				<th>积分商品</th>
				<th>会员折扣</th>
				<th>库存上限</th>
				<th>库存下限</th>
				<th>供货商</th>
				<th>生产日期</th>
				<th>保质期</th>
				<th>拼音码</th>
				<th>自定义1</th>
				<th>自定义2</th>
				<th>自定义3</th>
				<th>自定义4</th>
				<th>商品状态</th>
				<th>商品描述</th>
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
					for (int j = 0; j <22; j++) {
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