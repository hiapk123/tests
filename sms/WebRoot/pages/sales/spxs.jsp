<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品销售统计</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/pager.css'/>" />
<!-- 日期控件css资源 -->
<link href="<c:url value='/datetimepicker/css/bootstrap.min.css'/>"
	rel="stylesheet" media="screen">
<link
	href="<c:url value='/datetimepicker/css/bootstrap-datetimepicker.min.css'/>"
	rel="stylesheet" media="screen">

<!-- 日期控件js资源 -->
<script type="text/javascript"
	src="<c:url value='/datetimepicker/jquery/jquery-1.8.3.min.js'/>"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<c:url value='/datetimepicker/js/bootstrap.min.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/datetimepicker/js/bootstrap-datetimepicker.js'/>"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="<c:url value='/datetimepicker/js/bootstrap-datetimepicker.zh-CN.js'/>"
	charset="UTF-8"></script>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-footer">
			<form
				action="<c:url value='/SPXSServlet?method=findByCombination' />"
				method="post">
				<div class="row">
					<!-- <div class="col-md-2">
						<h4>商品销售统计</h4>
					</div> -->

					<div class="col-md-2">
						<select class="form-control" name="hp_store">
							<option>全部门店</option>
							<c:forEach items="${storeList }" var="store">
								<option <c:if test="${storeName eq store.SName}">selected</c:if>>${store.SName }</option>
							</c:forEach>
						</select>
					</div>
					<!-- data-date-format="yyyy-mm-dd HH:mm:ss" -->
					<div class="input-group date form_datetime col-md-3" data-date=""
						data-date-format="yyyy-mm-dd HH:mm:ss"
						data-link-field="dtp_input1">
						<input name="beginTime" class="form-control" size="16" type="text"
							value="${beginTime }" readonly> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-remove"></span></span> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-th"></span></span>
					</div>
					<input type="hidden" id="dtp_input1" value="" />

					<div class="input-group date form_datetime col-md-3" data-date=""
						data-date-format="yyyy-mm-dd HH:mm:ss"
						data-link-field="dtp_input1">
						<input name="endTime" class="form-control" size="16" type="text"
							value="${endTime }" readonly> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-remove"></span></span> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-th"></span></span>
					</div>
					<input type="hidden" id="dtp_input1" value="" />

					<div class="col-md-2">
						<input type="text" class="form-control" placeholder="商品条码/名称"
							name="gBarcodeOrGName" id="gBarcodeOrGName" value="${condition }">
					</div>

					<div class="col-md-2">
						<button type="submit" class="btn btn-primary">查询</button>
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
		<table id="hp_zbtj" class="table table-bordered table-hover">
			<thead>
				<!--  style="font-weight: 900" -->
				<tr>
					<th>序号</th>
					<th>商品名称</th>
					<th>商品条码</th>
					<th>商品分类</th>
					<th>现有库存</th>
					<th>销售数量</th>
					<th>商品总价</th>
					<th>实收金额</th>
					<th>利润</th>
				</tr>
			</thead>
			<tbody>
				<!-- <tr>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
					<td>1</td>
				</tr> -->
				<c:forEach items="${pb.beanList }" var="spxs" varStatus="status">
					<tr>
						<td>${status.index + 1 }</td>
						<td>${spxs.gName }</td>
						<td>${spxs.gBarcode }</td>
						<td>${spxs.cName }</td>
						<td>${spxs.gStockNum }</td>
						<td>${spxs.saleQuantity }</td>
						<td>${spxs.gTotalPrice }</td>
						<td>${spxs.saRealAmount }</td>
						<td>${spxs.saProfit }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div style="float: left; width: 100%; text-align: center;">
		<c:choose>
			<c:when test="${pb.tp eq 0}">
			未找到符合条件的记录！
		</c:when>
			<c:otherwise>
				<%@include file="/pager_spxs.jsp"%>
			</c:otherwise>
		</c:choose>
	</div>


	<script type="text/javascript">
		$('.form_datetime').datetimepicker({
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			showMeridian : 1
		});
	</script>

</body>
</html>