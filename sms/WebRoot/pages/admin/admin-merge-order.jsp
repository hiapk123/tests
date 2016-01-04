<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>订单合并</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/pager.css'/>" />
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
<!-- jQuery -->
<script src="<%=basePath%>bower_components/jquery/jquery.min.js"></script>

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="<%=basePath%>http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<script
	src="<%=basePath%>bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->

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

<!-- 打印控件资源（前面日期控件引入了jquery.min.js，此处不用引入jquery-1.4.4.min.js，一样可以使用，可能只有1.9.1版本的jquery不能用） -->
<script type="text/javascript"
	src="<c:url value='/js/jquery-1.4.4.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery.jqprint-0.3.js'/>"></script>
<script type="text/javascript">

	/* 合并订单页面 */
	function merge() {
		
		var bnos = new Array();
		var ckd = $(":checkbox[name=checkboxBtn][checked=true]");
		ckd.each(function() {
			bnos.push(($(this).val()));
		});
		//alert("ajax传递传递参数之前： " + bnos);
		
		$.ajax({
			url : "/sms/MergeOrderServlet",
			data : {
				method : "getBookingDetailByBNos",
				bnos : bnos+"" // 将数组转换成字符串传递到Servlet中，不然接收的字符串为null
			},
			type : "POST",
			dataType : "json",
			asyn : false,
			cache : false,
			success : function(result) {
				//alert("合并success");
				if (result.length > 0) { // 该合并订单有数据
					var detailHtml = "";
					$("#mergeTip").html("");
					for (var i = 0; i < result.length; i++) {
						detailHtml += "<tr>";
						detailHtml += "<td>" + (i+1) + "</td>";
						detailHtml += "<td>" + result[i].barcode + "</td>";
						detailHtml += "<td>" + result[i].gName + "</td>";
						detailHtml += "<td>" + result[i].gNum + "</td>";
						detailHtml += "<td>" + result[i].price + "</td>";
						detailHtml += "<td>" + (result[i].gNum * result[i].price).toFixed(1) + "</td>";
						detailHtml += "<td>" + result[i].gInfo + "</td>";
						detailHtml += "</tr>";
					}
					$("#mergeDetail").html(detailHtml);
					$("#exportDiv").jqprint();
				} else { // 该订单没有数据
					$("#mergeDetail").html("");
					$("#mergeTip").html("<center><span>该合并订单没有数据！</span></center>");
				}
				
			}
		});
	}



	/**
		打印相关
	*/
	function print() {
		$("#printDiv").jqprint();
	}
	function exportBooking() {
		$("#exportDiv").jqprint();
	}
</script>

<!-- 处理复选框的js片段 -->
<script type="text/javascript">
	$(document).ready(function() {
		$("#selectAll").click(function() {
			var bool = $("#selectAll").attr("checked");
			$(":checkbox[name=checkboxBtn]").attr("checked", bool);
		});
		
		$(":checkbox[name=checkboxBtn]").click(function(){
			var all = $(":checkbox[name=checkboxBtn]").length;
			var select = $(":checkbox[name=checkboxBtn][checked=true]").length;
			if (all == select) {
				$("#selectAll").attr("checked", true);
			} else if (select == 0) {
				$("#selectAll").attr("checked", false);
			} else {
				$("#selectAll").attr("checked", false);
			}
		});
	});
</script>
</head>

<body>
	<div class="row">
		<div class="box col-md-12">
		<form action="<c:url value='/MergeOrderServlet?method=findByCombination' />" method="post">
			<div class="form-group has-success col-md-4">
				<input name="bookingNo" type="text" class="form-control" id="inputSuccess1"
					 placeholder="请输入要查找的订单号：" value="${bookingNo }">
			</div>

			<select name="date" data-rel="chosen" class="btn btn-default">
				<option disabled>按时间查看</option>
				<c:forEach items="${dateList }" var="d">
					<option <c:if test="${date eq d}">selected</c:if>>${d }</option>
				</c:forEach>
			</select> <select name="storeName" data-rel="chosen" class="btn btn-default">
				<option disabled>按门店查看</option>
				<c:forEach items="${storeList }" var="store">
					<option <c:if test="${storeName eq store.SName}">selected</c:if>>${store.SName }</option>
				</c:forEach>
			</select>

			<button type="submit" class="btn btn-primary">查找订单</button>
			<!-- <button type="button" class="btn btn-primary" onclick="print()">打印</button> -->
			<a onclick="merge()" id="mergeOrder" class="btn btn-success btn-setting" data-toggle="modal" data-target="#myModal3"
				href="#"> 
				<i class="glyphicon glyphicon-zoom-in icon-white"></i>合并订单并打印
			</a>
		</form>
		</div>
		<div class="box-content" id="printDiv">
			<table
				class="table table-striped table-bordered bootstrap-datatable datatable responsive">
				<thead>
					<tr>
						<th><input type="checkbox" id="selectAll" name="selectAll" checked="checked"><label for="selectAll">&nbsp;全选</label></th>
						<th>订单编号</th>
						<th>订货门店</th>
						<th>日期</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pb.beanList }" var="order" varStatus="status">
					<tr>
						
						<td>
							<input value="${order.bNo }" type="checkbox" name="checkboxBtn" checked="checked">
						</td>
						
						<td id="bnoTd">${order.bNo }</td>
						<td class="center">${order.store.SName }</td>
						<td class="center">${order.bDate }</td>
						<td class="center">
							<a onclick="javascript:show('${order.bNo }');" id="preview" class="btn btn-success btn-setting" data-toggle="modal" data-target="#myModal"
							href="#"> 
								<i class="glyphicon glyphicon-zoom-in icon-white"></i> 预览
							</a> 
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<!-- 合并订单模态框 -->
	<div class="modal fade" id="myModal33" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">

		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3>合并订单明细</h3>
					<div align="right"> 
						<button type="button" class="btn btn-primary" onclick="exportBooking()">导出</button>
					</div> 
				</div>
				<div class="modal-body" id="exportDiv">
					<table
						class="table table-striped table-bordered bootstrap-datatable datatable responsive">
						<thead>
							<tr>
								<th>序号</th>
								<th>商品条码</th>
								<th>商品名称</th>
								<th>商品数量</th>
								<th>进货价</th>
								<th>小计</th>
								<th>备注</th>
							</tr>
						</thead>
						<tbody id="mergeDetail">
						</tbody>
					</table>
					<div id="mergeTip"></div>

				</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-default" data-dismiss="modal">关闭对话框</a>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 预览模态框 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">

		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3>订单明细</h3>
				</div>
				<div class="modal-body">
					<table
						class="table table-striped table-bordered bootstrap-datatable datatable responsive">
						<thead>
							<tr>
								<th>序号</th>
								<th>商品条码</th>
								<th>商品名称</th>
								<th>商品数量</th>
								<th>进货价</th>
								<th>小计</th>
								<th>备注</th>
								<!-- <th>操作</th> -->
							</tr>
						</thead>
						<tbody id="bookingDetail">
						</tbody>
					</table>
					<div id="tip"></div>

				</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-default" data-dismiss="modal">关闭对话框</a>
					<!-- <a href="#" class="btn btn-primary" data-dismiss="modal">保存订单 </a> -->
				</div>
			</div>
		</div>
	</div>
	
	<div style="float: left; width: 100%; text-align: center;">
		<c:choose>
			<c:when test="${pb.tp eq 0}">
			未找到符合条件的记录！
		</c:when>
			<c:otherwise>
				<%@include file="/pager_admin.jsp"%>
			</c:otherwise>
		</c:choose>
	</div>
	
</body>
</html>

