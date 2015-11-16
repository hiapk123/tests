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
<title>订单审核</title>
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

<script type="text/javascript">
	
	$(function() {
	});
	
	/* 预览页面 */
	function show(bno) {
		$.ajax({
			url : "/sms/AuditOrderServlet",
			data : {
				method : "getBookingDetailByBNo",
				bno : bno
			},
			type : "POST",
			dataType : "json",
			asyn : false,
			cache : false,
			success : function(result) {
				
				if (result.length > 0) { // 该订单有数据
					var detailHtml = "";
					for (var i = 0; i < result.length; i++) {
						detailHtml += "<tr>";
						detailHtml += "<td>" + (i+1) + "</td>";
						detailHtml += "<td>" + result[i].barcode + "</td>";
						detailHtml += "<td>" + result[i].gName + "</td>";
						detailHtml += "<td>" + result[i].gNum + "</td>";
						detailHtml += "<td>" + result[i].price + "</td>";
						detailHtml += "<td>" + (result[i].gNum * result[i].price) + "</td>";
						detailHtml += "<td>" + result[i].gInfo + "</td>";
						detailHtml += "<td><a class=\"btn btn-info\" href=\"#\"> <i	class=\"glyphicon glyphicon-edit icon-white\"></i>编辑</a></td>";
						detailHtml += "</tr>";
					}
					$("#bookingDetail").html(detailHtml);
				} else { // 该订单没有数据
					$("#bookingDetail").html("");
					$("#tip").html("<center><span>该订单没有数据！</span></center>");
				}
				
			}
		});
	}
	
	/* 编辑页面 */
	function edit(bno) {
		
		/**
		 *	设置 编辑页面表单提交请求的URL(带bno参数) 
		*/
		var action = "<c:url value='/AuditOrderServlet?method=updateBookingByBNo&bno=" + bno + "'/>";
		$("#updateForm").attr("action", action);
		//alert("action: " + $("#updateForm").attr("action"));
		
		$.ajax({
			url : "/sms/AuditOrderServlet",
			data : {
				method : "getBookingDetailByBNo",
				bno : bno
			},
			type : "POST",
			dataType : "json",
			asyn : false,
			cache : false,
			success : function(result) {
				
				if (result.length > 0) { // 该订单有数据
					var goodsNameHtml = "<option disabled>选择要编辑的商品</option>";
					for (var i = 0; i < result.length; i++) {
						goodsNameHtml += "<option value=" + i + ">" + result[i].gName + "</option>";
					}
					$("#goodsName").html(goodsNameHtml); // 填充商品名称下拉框内容
					
					// 默认表单输入框加载第一件商品的信息
					$("#barcode").val(result[0].barcode);
					$("#gName").val(result[0].gName);
					$("#quantity").val(result[0].gNum);
					$("#price").val(result[0].price);
					$("#description").val(result[0].gInfo);
					$("#gIndex").val("0");
					
					// 当商品名称下拉框的值发生改变，对应设置表单的值
					$("#goodsName").change(function(){
						var index = $("#goodsName").val();
						//alert("index: " + index);
						
						$("#barcode").val(result[index].barcode);
						$("#gName").val(result[index].gName);
						$("#quantity").val(result[index].gNum);
						$("#price").val(result[index].price);
						$("#description").val(result[index].gInfo);
						$("#gIndex").val(index);
						//alert("gIndex: " + $("#gIndex").val());
					});
				} else { // 该订单没有数据,清空表单所有的值
					$("#goodsName").html("<option disabled>选择要编辑的商品</option>");
					$("#barcode").val("");
					$("#gName").val("");
					$("#quantity").val("");
					$("#price").val("");
					$("#description").val("");
				}
				
			}
		});
	}
</script>
</head>

<body>
	<div class="row">
		<div class="box col-md-12">
		<form action="<c:url value='/AuditOrderServlet?method=findByCombination' />" method="post">
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
			</select> <select name="status" data-rel="chosen" class="btn btn-default">
				<option disabled>按状态查看</option>
				<option <c:if test="${status eq '待审核'}">selected</c:if>>待审核</option>
				<option <c:if test="${status eq '审核中'}">selected</c:if>>审核中</option>
				<option <c:if test="${status eq '已审核通过'}">selected</c:if>>已审核通过</option>
				<option <c:if test="${status eq '审核未通过'}">selected</c:if>>审核未通过</option>
				<option <c:if test="${status eq '待发货'}">selected</c:if>>待发货</option>
				<option <c:if test="${status eq '已发货'}">selected</c:if>>已发货</option>
				<option <c:if test="${status eq '已收货'}">selected</c:if>>已收货</option>
				
			</select>

			<button type="submit" class="btn btn-primary">查找订单</button>
		</form>
		</div>
		<div class="box-content">
			<table
				class="table table-striped table-bordered bootstrap-datatable datatable responsive">
				<thead>
					<tr>
						<th>订单编号</th>
						<th>订货门店</th>
						<th>日期</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${pb.beanList }" var="booking" varStatus="status">
					<tr>
						<td id="bnoTd">${booking.BNo }</td>
						<td class="center">${booking.store.SName }</td>
						<td class="center">${booking.BDate }</td>
						<td class="center"><span
							class="label-success label label-default">${booking.BStatus }</span></td>
						<td class="center">
							<a onclick="javascript:show('${booking.BNo }');" id="preview" class="btn btn-success btn-setting" data-toggle="modal" data-target="#myModal"
							href="#"> 
								<i class="glyphicon glyphicon-zoom-in icon-white"></i> 预览
							</a> 
							<c:choose>
							<c:when test="${booking.BStatus eq '待审核'}">
								<a onclick="javascript:edit('${booking.BNo }');" class="btn btn-info" href="#" data-toggle="modal" data-target="#myModal2"> 
									<i class="glyphicon glyphicon-edit icon-white"></i> 编辑
								</a> 
							</c:when>
							<c:otherwise>
								<a class="btn" href="#" disabled> <!-- class="btn btn-primary" -->
									<i class="glyphicon glyphicon-edit icon-white"></i> 编辑
								</a>
							</c:otherwise>
							</c:choose>
							<c:choose>  
							<c:when test="${booking.BStatus eq '待审核' or booking.BStatus eq '审核中' or booking.BStatus eq '审核未通过'}">
								<a class="btn btn-danger" href="<c:url value='/AuditOrderServlet?method=delete&bno=${booking.BNo }'/>" onclick="return window.confirm('确定删除该订单信息吗？')"> 
									<i class="glyphicon glyphicon-trash icon-white"></i> 删除
								</a> 
							</c:when>
							<c:otherwise>
								<a disabled class="btn" href="<c:url value='/AuditOrderServlet?method=delete&bno=${booking.BNo }'/>" onclick="return window.confirm('确定删除该订单信息吗？')"> 
									<i class="glyphicon glyphicon-trash icon-white"></i> 删除
								</a>
							</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
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
								<th>操作</th>
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
	<!--编辑对话框  -->
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">

		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h3>订单编辑</h3>
				</div>
				<div class="modal-body">
					<select id="goodsName" data-rel="chosen" class="btn btn-default" style="margin-bottom: 20px;">
					</select>
					<form id="updateForm" action="<c:url value=''/>" method="post">
					<input type="hidden" name="gIndex" value="" id="gIndex">
					<input id="submitInput" type="submit" value="保存订单" style="display: none;"> 
					<table
						class="table table-striped table-bordered bootstrap-datatable datatable responsive">
						<tbody>

							<tr>
								<td>商品条码</td>
								<td><input name="barcode" type="text" class="form-control"
									id="barcode" readonly="readonly"></td>
							</tr>
							<tr>
								<td>商品名称</td>
								<td><input name="gName" type="text" class="form-control"
									id="gName" readonly="readonly"></td>
							</tr>
							<tr>
								<td>进货数量</td>
								<td><input name="quantity" type="text" class="form-control"
									id="quantity" value=""></td>
							</tr>
							<tr>
								<td>进货价格</td>
								<td><input name="price" type="text" class="form-control"
									id="price" value="" readonly="readonly"></td>
							</tr>
							<tr>
								<td>备注</td>
								<td><input name="description" type="text" class="form-control"
									id="description" value=""></td>
							</tr>
						</tbody>
					</table>
					<select name="bookingStatus" id="bookingStatus" data-rel="chosen" class="btn btn-default" style="margin-bottom: 20px;">
						<option <c:if test="${status eq '待审核'}">selected</c:if>>待审核</option>
						<option <c:if test="${status eq '审核中'}">selected</c:if>>审核中</option>
						<option <c:if test="${status eq '已审核通过'}">selected</c:if>>已审核通过</option>
<%-- 						<option selected="selected" <c:if test="${status eq '已审核通过'}">selected</c:if>>已审核通过</option> --%>
						<option <c:if test="${status eq '审核未通过'}">selected</c:if>>审核未通过</option>
						<option <c:if test="${status eq '待发货'}">selected</c:if>>待发货</option>
						<option <c:if test="${status eq '已发货'}">selected</c:if>>已发货</option>
						<option <c:if test="${status eq '已收货'}">selected</c:if>>已收货</option>
					</select>
					</form>

				</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-default" data-dismiss="modal">关闭对话框</a>
					<a href="#" class="btn btn-primary" data-dismiss="modal" onclick="$('#submitInput').click();">保存订单 </a>
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

