<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">

     
 #close{ display: block;  position: absolute;  top: 0%;  left: 0%;  width: 100%;  height: 100%;  background-color: white;  z-index:1001;  -moz-opacity: 0.7;  opacity:1;  filter: alpha(opacity=100);} 
</style>
<base href="<%=basePath%>">
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content=",keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<SCRIPT LANGUAGE="javascript">
<!--

function tuihuo()
{
	var id=$("#store").val();
	var name=$("#store :selected").text();
window.open ("<%=basePath%>huoliu?m=tuihuo&s_id="+id+"&s_name="+name,'newwindow','height=500,width=800,top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
}
-->
</SCRIPT>



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
<script type="text/javascript">
function guanbi1(){
	
	$("#detail").empty();
	
}
 function getdetail(node){
	
	
	var list=$(node).val();
		//alert(list);
	$.post("<%=basePath%>huoliu", {
			"m" : "detail",
			"list":list,
		}, function(data) {
			$("#detail").append(data);
		}, "html");
	
} 
$(function(){
	
		$("#jinhuo").click(function(){
			$("#hlgldiv").empty();
			$.post("<%=basePath%>huoliu", {
				"m" : "jinhuo",
			}, function(data) {
				$("#hlgldiv").append(data);
			}, "html");
			
		});
		$("#diaohuo").click(function(){
			$("#hlgldiv").empty();
			$.post("<%=basePath%>huoliu", {
				"m" : "diaohuo",
			}, function(data) {
				$("#hlgldiv").append(data);
			}, "html");
			
		});
		$("#search").click(function(){
			var s_id=$("#store").val();
			var type=$("#category").val();
			
			if(store==null){
				alert("请重新选择店铺！");
				return;
			}
			$("#findhl").empty();
			$.post("<%=basePath%>huoliu", {
				"m" : "findhl",
				"s_id" : s_id,
				"type" :type,
				
			}, function(data) {
				$("#findhl").append(data);
			}, "html");
			
		});
		
		
		
	});
</script>
</head>

<body>
<div id="hlgldiv">

	<button type="button" class="btn btn-success" name="submit"
		id="jinhuo">进货</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" name="submit"
		id="diaohuo">调货</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" name="submit"
		onclick="tuihuo()">退货给供货商</button>
	<div style="float: right;">
	<select id="store" class="singleSelector">
		<option value="-1" selected="selected" disabled="disabled">全部店铺</option>

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

	<select id="category">
	    <option value="-1" selected="selected" disabled="disabled">全部货单</option>
		<option value="进货单" >门店进货单</option>
		<option value="1">门店调货</option>
		<option value="2">门店退货</option>
	</select>

	
		<input class="input-medium search-query" type="text" float:right /> <input
			type="button" value="查询" id="search" class="submitBtn" />
		
	</div>
	
	<div data-spy="scroll"
		style="width: 100%; overflow: auto; position: relative;"
		data-offset="10">
 
<div id="findhl">
		
		<table style="width:1200px; height:30px;  table-layout:fixed;" border="1" ;>
		<thead>
			<tr>
			    <th><input id="checkAll" type="checkbox"/></th>
				<th>序号</th>
				<th>操作</th>
				<th>货流单号</th>
				<th>下单时间</th>
				<th>货单类型</th>
				<th>出货门店</th>
				<th>进货门店</th>
				<th>状态</th>
				<th>货流量</th>
				<th>总价</th>
				<th>预付款</th>
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
	</div>

  
</body>

</html>
