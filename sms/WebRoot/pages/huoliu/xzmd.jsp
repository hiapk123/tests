<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*,org.uestc.util.DateFormatUtils"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>


</head>
<body>


	<%String s_id=request.getParameter("s_id");
	String s_name=request.getParameter("s_name");
	%>
	<label>门店:<%=s_name %></label>
		<table style="width: 1200px; height: 30px; table-layout: fixed;"
			border="1"; class="table-bordered table-condensed table-hover table-striped ">
			<thead>
				<tr>
					
					<th >序号</th>
					<th>删除</th>
					<th>商品名称</th>
					<th>条码</th>
					<th>库存</th>
					<th>供货商</th>
					<th>进货量</th>
					<th>进货价(元)</th>
					<th>单位</th>
					<th>分类</th>
					<th>小计(元)</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
			</table>
  
</body>
</html>