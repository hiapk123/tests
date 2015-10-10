<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<base href="<%=basePath%>">
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content=",keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!-- The styles -->
<link id="bs-css" href="<%=basePath%>css/bootstrap-cerulean.min.css"
	rel="stylesheet">

<link href="<%=basePath%>css/charisma-app.css" rel="stylesheet">
<link
	href='<%=basePath%>bower_components/fullcalendar/dist/fullcalendar.css'
	rel='stylesheet'>
<link
	href='<%=basePath%>bower_components/fullcalendar/dist/fullcalendar.print.css'
	rel='stylesheet' media='print'>
<link href='<%=basePath%>bower_components/chosen/chosen.min.css'
	rel='stylesheet'>
<link
	href='<%=basePath%>bower_components/colorbox/example3/colorbox.css'
	rel='stylesheet'>
<link
	href='<%=basePath%>bower_components/responsive-tables/responsive-tables.css'
	rel='stylesheet'>
<link
	href='<%=basePath%>bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>
<link href='<%=basePath%>css/jquery.noty.css' rel='stylesheet'>
<link href='<%=basePath%>css/noty_theme_default.css' rel='stylesheet'>
<link href='<%=basePath%>css/elfinder.min.css' rel='stylesheet'>
<link href='<%=basePath%>css/elfinder.theme.css' rel='stylesheet'>
<link href='<%=basePath%>css/jquery.iphone.toggle.css' rel='stylesheet'>
<link href='<%=basePath%>css/uploadify.css' rel='stylesheet'>
<link href='<%=basePath%>css/animate.min.css' rel='stylesheet'>
<link href="<%=basePath%>css/bootstrap-datetimepicker.css"
	rel="stylesheet">
<link href="<%=basePath%>css/jquery.dataTables.min.css" rel="stylesheet">

<!-- jQuery -->
<script src="<%=basePath%>bower_components/jquery/jquery.min.js"></script>

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico">

<!-- external javascript -->

<script
	src="<%=basePath%>bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="<%=basePath%>js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='<%=basePath%>bower_components/moment/min/moment.min.js'></script>
<script
	src='<%=basePath%>bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='<%=basePath%>js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="<%=basePath%>bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script
	src="<%=basePath%>bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="<%=basePath%>js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script
	src="<%=basePath%>bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script
	src="<%=basePath%>bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="<%=basePath%>js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="<%=basePath%>js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="<%=basePath%>js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="<%=basePath%>js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="<%=basePath%>js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="<%=basePath%>js/charisma.js"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.zh-CN.js"
	charset="utf-8"></script>
<style type="text/css">
table thead tr th{
background: #D5E9ED;
}
 </style>
</head>
<script type="text/javascript">
function querenjiesuan1(){
	var danshu=$("#danshu").val();
	var numOfGoods=$("#numOfGoods").val();
	var price=$("#price").val();
	var ss_pre_price=$("#ss_pre_price").val();
	var su_gd_return=$("#su_gd_return").val();
	var su_ps_return=$("#su_ps_return").val();
	
	$.post("<%=basePath%>huoliu", {
		"m" : "qrjs1",
		"danshu":danshu,
		"numOfGoods":numOfGoods,
		"price":price,
		"supplier":supplier,
	}, function(data) {
		$("#qrjs").append(data);
	}, "html");
	
	
}
function querenjiesuan()
{
	var danshu=$("#findjs").find("input:checkbox[class='subBox']:checked").length ;
	var tr=$("#findjs").find("input:checkbox[class='subBox']:checked").parent().parent();
	var td = tr.find("td");
	
	var supplier=$("#supplier").val();
	for(var i=0,numOfGoods=0;i<td.length/12;i++){
		numOfGoods=numOfGoods+parseInt(td.eq(8+12*i).text());
	}
	for(var i=0,price=0;i<td.length/12;i++){
		price=price+parseInt(td.eq(9+12*i).text());
	}
	$("#qrjs").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "qrjs",
		"danshu":danshu,
		"numOfGoods":numOfGoods,
		"price":price,
		"supplier":supplier,
	}, function(data) {
		$("#qrjs").append(data);
	}, "html");
}
function querenduizhang1()
{   var tr=$("#findjs").find("input:checkbox[class='subBox']:checked").parent().parent();
	var list_ss_id=tr.find(".ss_id");
	var list="";
	for(var i=0;i<list_ss_id.length;i++){
		list=list+list_ss_id.eq(i).val()+" ";
	}
	alert(list);
	$("#qrdz").empty();
	$("#ghsjs").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "qrdz1",
		"list":list,
	}, function(data) {
		$("#ghsjs").append(data);
	}, "html");
}
function querenduizhang()
{    
	var danshu=$("#findjs").find("input:checkbox[class='subBox']:checked").length ;
	var tr=$("#findjs").find("input:checkbox[class='subBox']:checked").parent().parent();
	var td = tr.find("td");
	alert(danshu);
	for(var i=0,numOfGoods=0;i<td.length/12;i++){
		numOfGoods=numOfGoods+parseInt(td.eq(8+12*i).text());
	}
	for(var i=0,price=0;i<td.length/12;i++){
		price=price+parseInt(td.eq(9+12*i).text());
	}
	$("#qrdz").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "QRDZ",
		"danshu":danshu,
		"numOfGoods":numOfGoods,
		"price":price,
	}, function(data) {
		$("#qrdz").append(data);
	}, "html");
}
function guanbi1(){
	
	$("#detail").empty();
	
}

function getdetail(node){
	
	
	var list=$(node).val();
		//alert(list);
	$.post("<%=basePath%>huoliu", {
			"m" : "ghsdetail",
			"list":list,
		}, function(data) {
			$("#detail").append(data);
		}, "html");
	
} 
$(function(){

$("#search").click(function(){
	var s_id=$("#store").val();
	var status=$("#category").val();
	var supplier=$("#supplier").val();
	var currentPage=null;
	if(store==null){
		alert("请重新选择店铺！");
		return;
	}
	$("#findjs").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "findjs",
		"s_id" : s_id,
		"status" :status,
		"supplier" :supplier,
		"currentPage":currentPage,
	}, function(data) {
		$("#findjs").append(data);
	}, "html");
	
});
});

</script>
<body>
<div id="ghsjsdiv">

	<button type="button" class="btn btn-success" name="submit"
		id="jsjl">结算记录</button>
	&nbsp;&nbsp;
	
	<div style="float: right;">
	<select id="store" class="singleSelector">
		<option value="" selected="selected" >全部门店</option>

		<%
			List<Object[]> list = (List<Object[]>) request.getAttribute("storeList");
			if (list != null && list.size() != 0) {
				for (Object[] obj : list) {
		%>
		<option value='<%=obj[0]%>'><%=obj[1]%></option>
		<%
			}
			}
		%>

	</select>
<select id="supplier" class="singleSelector">
		<option value="" selected="selected" >全部供货商</option>

		<%
			List<Object[]> supplierList = (List<Object[]>) request.getAttribute("supplierList");
			if (supplierList != null && supplierList.size() != 0) {
				for (Object[] obj : supplierList) {
		%>
		<option value='<%=obj[0]%>'><%=obj[1]%></option>
		<%
			}
			}
		%>

	</select>
	<select id="category">
	    <option value="全部状态" selected="selected" >全部状态</option>
		<option value="未成功货单" >未成功货单</option>
		<option value="待对账货单">待对账货单</option>
		<option value="待结算货单">待结算货单</option>
		<option value="已结算货单">已结算货单</option>
	</select>

	
		<input class="input-medium search-query" type="text" float:right /> <input
			type="button"  class="btn btn-warning" value="  查     询     " id="search" class="submitBtn" />
		
	</div>
	
	<div data-spy="scroll"
		style="width: 100%; overflow: auto; position: relative;"
		data-offset="10">
 
<div id="findjs">
		
		<table style="width:1200px; height:30px;  table-layout:fixed;" border="1" ;>
		<thead>
			<tr>
			    <th><input id="checkAll" type="checkbox"/></th>
				<th>序号</th>
				<th>操作</th>
				<th>供货商</th>
				<th>门店</th>
				<th>货单号</th>
				<th>下单时间</th>
				<th>货单类型</th>
				
				<th>货流量</th>
				<th>总价</th>
				<th>预付款</th>
				<th>状态</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		
		
	</tbody>

	</table>
		</div>
		
</div>
  <div id="detail">
 
 </div>
	

  <div id="ly" style="position: absolute; bottom: 1400px;">
	
	<button type="button" class="btn btn-primary btn-lg" name="submit"
			onclick="querenduizhang()" style="margin: 0 0 0 950px" >确认对账</button>
	<button type="button" class="btn btn-success btn-lg" name="submit"
			onclick="querenjiesuan()" style="margin: 0 0 0 50px" >确认结算</button>	
	</div>
 <div id="detail">
 
 </div>
<div id="qrdz"  >
	
</div>
<div id="qrjs"  >
	
</div>
</div>
</body>

</html>
