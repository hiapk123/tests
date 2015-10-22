<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">


<title>添加商品</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">


<link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>

<link
	href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>

<link href='css/elfinder.min.css' rel='stylesheet'>

<link href='css/animate.min.css' rel='stylesheet'>

<script src="bower_components/bootstrap/dist/css/bootstrap.min.css"></script>
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="bower_components/jquery/jquery.min.js"></script>
<SCRIPT LANGUAGE="javascript">
<!--

function help()
{
window.open ('pages/goods/goodsinfo/help.jsp','newwindow','height=500,width=800,top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
}
-->
</SCRIPT>
</head>
<%
String s_id=request.getParameter("s_id");
String s_name=request.getParameter("s_name");
%>
<body>
	<span class="label label-default" style="padding:10px">商品快速录入</span>  &nbsp;&nbsp;
	<a href="pages/goods/goods-info.jsp"><input type="button" class="btn btn-success" value="返回"></input></a>
	<button type="button" class="btn btn-success" name="submit" onclick="help()">使用帮助</button>
	<label>录入门店<%=s_name %></label>
	<form action="<%=basePath%>goods?m=kuaisu1"  method="post">
	<table style="width:1200px; height:30px;  table-layout:fixed;" border="1" ;>
		<thead>
			<tr>
				<th>行号</th>
				<th>商品条码</th>
				<th>商品名称</th>
				<th>商品分类</th>
				<th>进货价</th>
				<th>销售价</th>
				<th>库存量</th>
			</tr>
		</thead>
		<tbody>
				<td>1</td>
				<td><input type="text" name="g_barcode" style="width:100px"></td>
				<td><input type="text" name="g_name" style="width:100px"></td>
				<td><input type="text" name="c_name" style="width:100px"></td>
				<td><input type="text" name="g_pur_price" style="width:100px"></td>
				<td><input type="text" name="g_sale_price" style="width:100px"></td>
				<td><input type="text" name="g_stock_num" style="width:100px"></td>
				
		</tbody>
	</table>
	<input type="hidden" name="s_id" value="<%=s_id%>">
	<button type="submit" i class="btn btn-success" name="submit" >保存</button>
	<button type="reset" i class="btn btn-success" name="reset" >取消</button>
	</form>
	
</body>
</html>

