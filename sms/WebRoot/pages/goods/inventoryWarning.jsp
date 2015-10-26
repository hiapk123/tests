<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>库存预警页面</title>

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
	
<!-- 打印控件资源（前面日期控件引入了jquery.min.js，此处不用引入jquery-1.4.4.min.js，一样可以使用，可能只有1.9.1版本的jquery不能用） -->
<script type="text/javascript"
	src="<c:url value='/js/jquery.jqprint-0.3.js'/>"></script>
<script type="text/javascript">
	function print() {
		$("#printDiv").jqprint();
	}
</script>
</head>

<body>

	<div class="panel panel-default">
		<div class="panel-footer">
			<form
				action="<c:url value='/InventoryWarningServlet?method=findByCombination' />"
				method="post">
				<div class="row">
					<!-- <div class="col-xs-2">
						<h4>库存预警</h4>
					</div> -->
					<div class="col-xs-2">
						<select class="form-control" name="hp_store">
							<option>全部门店</option>
<!-- 							<option value="-1">全部门店</option> -->
							<c:forEach items="${storeList }" var="store">
								<option <c:if test="${storeName eq store.SName}">selected</c:if>>${store.SName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-xs-2">
						<select class="form-control" name="hp_category">
							<option>全部分类</option>
<!-- 							<option value="-1">全部分类</option> -->
							<c:forEach items="${categoryList }" var="category">
								<option
									<c:if test="${categoryName eq category.CName}">selected</c:if>>${category.CName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-xs-2">
						<select class="form-control" name="hp_supplier">
							<option>全部供货商</option>
<!-- 							<option value="-1">全部供货商</option> -->
							<c:forEach items="${supplierList }" var="supplier">
								<option
									<c:if test="${supplierName eq supplier.suName}">selected</c:if>>${supplier.suName }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-xs-2">
						<select class="form-control" name="hp_inventoryStatus">
							<option <c:if test="${inventoryStatus eq '库存不足'}">selected</c:if>>库存不足</option>
							<option <c:if test="${inventoryStatus eq '库存过剩'}">selected</c:if>>库存过剩</option>
							<option <c:if test="${inventoryStatus eq '过期预警'}">selected</c:if>>过期预警</option>
						</select>
					</div>
					<div class="col-xs-1">
						<button type="submit" class="btn btn-primary">查询</button>
						<!-- <input type="submit" class="btn btn-primary">查询</input> -->
					</div>
					<div class="col-xs-1">
						<button type="button" class="btn btn-primary" onclick="print()">打印</button>
					</div>
					<!-- <div class="col-xs-1">
						<button type="button" class="btn btn-primary" id="hp_exportBtn">导出</button>
						导出货单
					</div> -->
				</div>
			</form>
		</div>
	</div>

	<div class="panel panel-default" id="printDiv">
		<!-- Default panel contents -->
		<!-- <div class="panel-heading">Panel heading</div> -->
		<!-- <div class="panel-body">
			<p>...</p>
		</div> -->

		<!-- Table -->
		<table id="hp_kcyj" class="table table-bordered table-hover">
			<thead> <!--  style="font-weight: 900" -->
				<tr>
					<th>序号</th>
					<th>商品名称</th>
					<th>所属门店</th>
					<th>商品分类</th>
					<th>供货商</th>
					<th>条码</th>
					<th>库存量</th>
					<th>库存上限</th>
					<th>库存下限</th>
					<th>到期日期</th>
					<th>剩余有效天数</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pb.beanList }" var="goods" varStatus="status">
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
<%-- 				<c:forEach items="${goodsList }" var="goods" varStatus="status">
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
				</c:forEach> --%>

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

	<div style="float: left; width: 100%; text-align: center;">
	<!-- 底部分页div -->
	<%-- <%@include file="/pager.jsp" %> --%>
	
	<c:choose>
		<c:when test="${pb.tp eq 0}">
			未找到符合条件商品！
		</c:when>
		<c:otherwise>
			<%@include file="/pager.jsp" %>
		</c:otherwise>
	</c:choose>
	</div>

	<script type="text/javascript">
		$(function() {
			$("#hp_exportBtn").click(function() {
				$("#hp_kcyj").tableExport({
					type : 'excel',
					escape : 'false'
				});
				alert('我在底部被点击了！');
			});
		});
	</script>

</body>
</html>
