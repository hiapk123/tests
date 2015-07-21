<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>库存预警页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
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

</head>

<body>

	<div class="panel panel-default">
		<div class="panel-footer">
			<form action="<c:url value='/InventoryWarningServlet?method=findByCriteria' />" method="post">
				<div class="row">
					<div class="col-md-2">
						<h4>库存预警</h4>
					</div>
					<div class="col-md-2">
						<select class="form-control" name="hp_store" >
							<option value="-1">全部门店</option>
							<c:forEach items="${storeList }" var="store">
								<option <c:if test="${storeName eq store.SName}">selected</c:if> >${store.SName }</option>
							</c:forEach>
						</select>
					</div>
					<!-- 				<div class="col-md-2">
					<select class="form-control"  placeholder=".col-md-2">
						<option>门店下拉1</option>
						<option>门店下拉2</option>
						<option>门店下拉3</option>
						<option>门店下拉4</option>
						<option>门店下拉5</option>
					</select>
				</div> -->
					<div class="col-md-2">
						<select class="form-control" name="hp_category">
							<option value="-1">全部分类</option>
							<c:forEach items="${categoryList }" var="category">
								<option <c:if test="${categoryName eq category.CName}">selected</c:if> >${category.CName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-2">
						<select class="form-control" name="hp_supplier">
							<option value="-1">全部供货商</option>
							<c:forEach items="${supplierList }" var="supplier">
								<option <c:if test="${supplierName eq supplier.suName}">selected</c:if> >${supplier.suName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-2">
						<select class="form-control" name="hp_inventoryStatus">
							<option <c:if test="${inventoryStatus eq '库存不足'}">selected</c:if> >库存不足</option>
							<option <c:if test="${inventoryStatus eq '库存过剩'}">selected</c:if> >库存过剩</option>
							<option <c:if test="${inventoryStatus eq '过期预警'}">selected</c:if> >过期预警</option>
						</select>
					</div>
					<div class="col-md-1">
						<button type="submit" class="btn btn-primary">查询</button>
						<!-- <input type="submit" class="btn btn-primary">查询</input> -->
					</div>
					<div class="col-md-1">
						<button type="button" class="btn btn-primary">导出</button>
						<!-- 导出货单 -->
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<!-- <div class="panel-heading">Panel heading</div> -->
		<!-- <div class="panel-body">
			<p>...</p>
		</div> -->

		<!-- Table -->
		<table class="table table-bordered table-hover">
			<thead style="font-weight: 900">
				<th>
				<td>商品名称</td>
				<td>所属门店</td>
				<td>商品分类</td>
				<td>供货商</td>
				<td>条码</td>
				<td>库存量</td>
				<td>库存上限</td>
				<td>库存下限</td>
				<td>到期日期</td>
				<td>剩余有效天数</td>
				</th>
			</thead>
			<tbody>
				<c:forEach items="${goodsList }" var="goods" varStatus="status">
					<tr>
						<td>${status.index + 1 }</td>
						<td>${goods.GName }</td>
						<td>${goods.SName }</td>
						<td>${goods.CName }</td>
						<td>${goods.suName }</td>
						<td>${goods.GBarcode }</td>
						<td>${goods.GStockNum }</td>
						<td>${goods.GStockMax }</td>
						<td>${goods.GStockMin }</td>
						<td>-</td>
						<td>-</td>
					</tr>
				</c:forEach>

				<!-- <tr>
					<td>2</td>
					<td>矿泉水</td>
					<td>王二小食品店</td>
					<td>散装</td>
					<td>赵小六</td>
					<td>1507140040105</td>
					<td>0</td>
					<td>100</td>
					<td>0</td>
					<td>-</td>
					<td>-</td>
				</tr>
				<tr>
					<td>3</td>
					<td>矿泉水</td>
					<td>王二小食品店</td>
					<td>散装</td>
					<td>赵小六</td>
					<td>1507140040105</td>
					<td>0</td>
					<td>100</td>
					<td>0</td>
					<td>-</td>
					<td>-</td>
				</tr> -->
			</tbody>
		</table>
	</div>

</body>
</html>
