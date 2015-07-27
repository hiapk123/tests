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
<title>销售单据</title>
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

<link href="<%=basePath%>css/copy/jquery-ui-timepicker.css"
	rel='stylesheet'>
<link href="<%=basePath%>css/copy/jquery.mCustomScrollbar.min.css"
	rel='stylesheet'>
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
			var start=$("#start").val();
			var end=$("#end").val();
			//alert(store+""+number+""+start+""+end);
			if(store==null||start==''||end==''){
				alert("请重新选择店铺！");
				return;
			}
			//var json={"store":store,"catagory":number,"start":start,"end":end};
			$("#xiaoshoudiv").empty();
			
			$.post("<%=basePath%>sales", {
				"m" : "showdetails",
				"store" : store,
				"catagory" : number,
				"start" : start,
				"end" : end
			}, function(data) {
				$("#xiaoshoudiv").append(data);
			}, "html");

		});

		//明细按钮

	});
</script>
</head>
<body>

	<div>
		
		<form class="form-inline">
		<span><input type="checkbox"> 显示明细</span>
			<div class="form-group">
				<select id="store" class="form-control">
					<option value="-1" selected="selected"  disabled="disabled">选择店铺</option>
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
			</div>
			<div class="form-group">
				<select id="category" class="form-control">
					<option value="0" selected="selected">有效单据</option>
					<option value="1">作废单据</option>
				</select> 
			</div>
			<div class="form-group">
			<input id="start"  class="form-control" type="text" value="2015-07-26 00:00:00" size="16">
			<input id="end" class="form-control"  type="text" value="2015-07-26 23:59:00" size="16">
			<input type="text" class="form-control" autocomplete="off" placeholder="流水号" id="flownum">
			</div>
			
			<input type="button" value="查询" id="search" class="btn btn-default"/>
		</form>
		
		
		
	</div>
	<div class="content">
		<!--start  -->
		<div id="xiaoshoudiv"></div>
		<!-- end -->
	</div>

</body>
</html>