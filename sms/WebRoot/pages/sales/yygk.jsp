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
<title>营业概况</title>
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
<%-- <script type="text/javascript"
	src="<c:url value='/js/jquery-1.4.4.min.js'/>"></script> --%>
<script type="text/javascript"
	src="<c:url value='/js/jquery.jqprint-0.3.js'/>"></script>
<script type="text/javascript">
	function a() {
		$("#ddd").jqprint();
	}
</script>
</head>
<body>
<input type="button" onclick=" a()" value="打印" />
	<div class="panel panel-default">
		<div class="panel-footer">
			<form
				action="<c:url value='/YYGKServlet?method=findByCombination' />"
				method="post">
				<div class="row">

					<div class="col-xs-2">
						<select class="form-control" name="hp_store">
							<option>全部门店</option>
							<c:forEach items="${storeList }" var="store">
								<option <c:if test="${storeName eq store.SName}">selected</c:if>>${store.SName }</option>
							</c:forEach>
						</select>
					</div>

					<div class="col-xs-2">
						<select class="form-control" name="hp_condition">
							<option <c:if test="${condition eq '销售汇总分析'}">selected</c:if>>销售汇总分析</option>
							<option <c:if test="${condition eq '交接班记录'}">selected</c:if>>交接班记录</option>
							<option <c:if test="${condition eq '现金收支明细'}">selected</c:if>>现金收支明细</option>
						</select>
					</div>

					<div class="input-group date form_datetime col-xs-3" data-date=""
						data-link-field="dtp_input1">
						<input name="beginTime" class="form-control" size="16" type="text"
							value="${beginTime }" readonly> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-remove"></span></span> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-th"></span></span>
					</div>
					<input type="hidden" id="dtp_input1" value="" />

					<div class="input-group date form_datetime col-xs-3" data-date=""
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

					<div class="col-xs-2">
						<button type="submit" class="btn btn-primary">查询</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="panel panel-default" id="ddd">
		<!-- Default panel contents -->
		<!-- <div class="panel-heading">Panel heading</div> -->
		<!-- <div class="panel-body">
			<p>...</p>
		</div> -->

		<!-- Table -->
		<table id="hp_xsdj" class="table table-bordered table-hover">
			<thead>
			<c:if test="${empty condition}">
				<tr>
					<th></th>
					<th>概况</th>
					<th>现金支付</th>
					<th>银联卡支付</th>
					<th>在线支付</th>
				</tr>
			</c:if>
			<c:if test="${condition eq '销售汇总分析'}">
				<tr>
					<th></th>
					<th>概况</th>
					<th>现金支付</th>
					<th>银联卡支付</th>
					<th>在线支付</th>
				</tr>
			</c:if>
			<c:if test="${condition eq '交接班记录'}">
				<tr>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>收银员</th>
					<th>收银总额</th>
					<th>现金</th>
					<th>银联卡</th>
					<th>在线</th>
				</tr>
			</c:if>
			<c:if test="${condition eq '现金收支明细'}">
				<tr>
					<th>时间</th>
					<th>收银员</th>
					<th>支付</th>
					<th>找现</th>
					<th>应付</th>
					<th>类型</th>
				</tr>
			</c:if>
			</thead>
			<tbody>
			<c:if test="${empty condition}">
				<tr>
					<td>商品销售</td>
					<td>销售额：${ssb.salesTotalAmount }，利润：${ssb.salesTotalProfit }，单数：${ssb.salesCounts }</td>
					<td>${ssb.salesCash }</td>
					<td>${ssb.salesBank }</td>
					<td>${ssb.salesOnline }</td>
				</tr>
				<tr>
					<td>会员卡充值</td>
					<td>充值：${ssb.vipCharge }，赠送：${ssb.vipDonate }，单数：${ssb.vipCounts }</td>
					<td>${ssb.vipCash }</td>
					<td>${ssb.vipBank }</td>
					<td>${ssb.vipOnline }</td>
				</tr>
				<tr>
					<td><strong>总计</strong></td>
					<td>${ssb.summaryTotal }</td>
					<td>${ssb.cashTotal }</td>
					<td>${ssb.bankTotal }</td>
					<td>${ssb.onlineTotal }</td>
				</tr>
			</c:if>
			<c:if test="${condition eq '销售汇总分析'}">
				<tr>
					<td>商品销售</td>
					<td>销售额：${ssb.salesTotalAmount }，利润：${ssb.salesTotalProfit }，单数：${ssb.salesCounts }</td>
					<td>${ssb.salesCash }</td>
					<td>${ssb.salesBank }</td>
					<td>${ssb.salesOnline }</td>
				</tr>
				<tr>
					<td>会员卡充值</td>
					<td>充值：${ssb.vipCharge }，赠送：${ssb.vipDonate }，单数：${ssb.vipCounts }</td>
					<td>${ssb.vipCash }</td>
					<td>${ssb.vipBank }</td>
					<td>${ssb.vipOnline }</td>
				</tr>
				<tr>
					<td><strong>总计</strong></td>
					<td>${ssb.summaryTotal }</td>
					<td>${ssb.cashTotal }</td>
					<td>${ssb.bankTotal }</td>
					<td>${ssb.onlineTotal }</td>
				</tr>
			</c:if>
			<c:if test="${condition eq '交接班记录'}">
			<c:forEach items="${pb.beanList }" var="jjb" varStatus="status">
				<tr>
					<td>${jjb.startTime }</td>
					<td>${jjb.endTime }</td>
					<td>${jjb.employee.empName }</td>
					<td>${jjb.totalAll }</td>
					<td>${jjb.cashPay }</td>
					<td>${jjb.bankPay }</td>
					<td>${jjb.onlinePay }</td>
				</tr>
			</c:forEach>
			</c:if>
			<c:if test="${condition eq '现金收支明细'}">
				<c:forEach items="${pb.beanList }" var="xjsz" varStatus="status">
				<tr>
					<td>${xjsz.saDate }</td>
					<td>${xjsz.employee.empName }</td>
					<td>${xjsz.fCash }</td>
					<td>${xjsz.fZhaoXian }</td>
					<td>${xjsz.fYingFu }</td>
					<td>现金</td>
				</tr>
			</c:forEach>
			</c:if>
			</tbody>
		</table>
	</div>

	<div style="float: left; width: 100%; text-align: center;">
		<c:choose>
			<c:when test="${pb.tp eq 0}">
			未找到符合条件的记录！
		</c:when>
			<c:otherwise>
			<c:if test="${condition eq '交接班记录' or condition eq '现金收支明细'}">
				<%@include file="/pager_zbtj.jsp"%>
			</c:if>
			</c:otherwise>
		</c:choose>
	</div>

	<script type="text/javascript">
		$('.form_datetime').datetimepicker({
			format : 'yyyy-mm-dd hh:ii',
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