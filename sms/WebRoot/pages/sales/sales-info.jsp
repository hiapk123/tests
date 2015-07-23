<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE>
<html>
<head>
<title>营业情况</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">

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

<link href="<%=basePath%>css/copy/jquery-ui-timepicker.css" rel='stylesheet'>
<link href="<%=basePath%>css/copy/jquery.mCustomScrollbar.min.css" rel='stylesheet'>
<link href="<%=basePath%>css/copy/main.css" rel='stylesheet'>
<!-- jQuery -->
<script src="<%=basePath%>bower_components/jquery/jquery.min.js"></script>
<script src="<%=basePath%>js/copy/jquery-ui.min.js"></script>
<script src="<%=basePath%>js/copy/jquery-ui-timepicker-addon.js"></script>
<script src="<%=basePath%>js/copy/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="<%=basePath%>js/copy/jquery.resizableFixedHeader.js"></script>
<script src="<%=basePath%>js/copy/pospal.js"></script>

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon" href="img/favicon.ico">
<style type="text/css">
.top {
	width: 100%;
	text-align: right;
}

select {
	width: 200px;
}

.content {
	width: 100%;
}
</style>
<script type="text/javascript">
	$(function(){
		
		$("#search").click(function(){
			var store=$("#store").val();
			var number=$("#category").val();
			var start=$("#ui-timePicker-begin-158008").val();
			var end=$("#ui-timePicker-end-76244").val();
			//alert(store+""+number+""+start+""+end);
			if(store==null||start==''||end==''){
				alert("请重新选择店铺！");
				return;
			}
			//var json={"store":store,"catagory":number,"start":start,"end":end};
			$("#xiaoshoudiv").empty();
			if(number==0){
				
				$.post("<%=basePath%>sales",{"m":"xiaoshou","store":store,"catagory":number,"start":start,"end":end},function(data){
					$("#xiaoshoudiv").append(data);
				},"html");
			}else if(number==1){
				$.post("<%=basePath%>sales",{"m":"jiaoban","store":store,"catagory":number,"start":start,"end":end},function(data){
					$("#xiaoshoudiv").append(data);
				},"html");
			}else if(number==2){
				$.post("<%=basePath%>sales", {
					"m" : "cash",
					"store" : store,
					"catagory" : number,
					"start" : start,
					"end" : end
				}, function(data) {
					$("#xiaoshoudiv").append(data);
				}, "html");
			}

		});
		
		$('#btnExport').click(function(){
			alert("Hello");
		});

	});
</script>
</head>
<body>

	<div class="conditionNav">
	
		<div class="conditionLeft textBtn">
            <div class="btnBlue" id="btnExport">导出</div>
        </div>
		<select id="store"
			class="singleSelector">
			<option value="-1" selected="selected" disabled="disabled">选择店铺</option>

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

		</select> <select id="category" class="singleSelector">
			<option value="0" selected="selected">销售汇总分析</option>
			<option value="1">交接班记录</option>
			<option value="2">现金收支明细</option>
		</select>
		<div id="dateTimeRangeBox" class="dateTimeRangeBox">
			<input id="ui-timePicker-begin-158008" type="text"
				class="timeInput hasDatepicker" value="2015.07.22 00:00" size="16"><span>-</span>
			<input
				id="ui-timePicker-end-76244" type="text"
				class="timeInput hasDatepicker" value="2015.07.22 23:59" size="16">
		</div>
		<!-- <input placeholder="2015-07-20" type="text" id='start'> 
		<input placeholder="2018-07-20" type="text" id='end'>  -->
		<input type="button" value="查询" id="search" class="submitBtn" />
	</div>
	<div class="content">
		<!--start  -->
		<div id="xiaoshoudiv"></div>
		<!-- end -->
	</div>
	<div id="ui-datepicker-div" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all"></div>
</body>
</html>