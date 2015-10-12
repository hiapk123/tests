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
<title>趋势分析</title>
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
				action="<c:url value='/QSFXServlet?method=findByCombination' />"
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
					<!-- data-date-format="yyyy-mm-dd HH:mm:ss" -->
					<div class="input-group date form_datetime col-xs-3" data-date="" data-link-field="dtp_input1">
                    <input name="beginTime" class="form-control" size="16" type="text" value="${beginTime }" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
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
						<select class="form-control" name="hp_condition">
							<option <c:if test="${condition eq '按小时'}">selected</c:if>>按小时</option>
							<option <c:if test="${condition eq '按天'}">selected</c:if>>按天</option>
							<option <c:if test="${condition eq '按月'}">selected</c:if>>按月</option>
						</select>
					</div>

					<div class="col-xs-2">
						<button type="submit" class="btn btn-primary">统计分析</button>
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
		<table id="hp_qsfx" class="table table-bordered table-hover">
			<thead>
				<!--  style="font-weight: 900" -->
				<tr>
					<th>序号</th>
					<th>时间</th>
					<th>销售单数</th>
					<th>实收金额</th>
					<th>利润</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pb.beanList }" var="sale" varStatus="status">
					<tr>
						<td>${status.index + 1 }</td>
						<td>${sale.saDate }</td>
						<td>${sale.saGoodsNum }</td>
						<td>${sale.saRealPrice }</td>
						<td>${sale.saProfit }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div style="float: left; width: 100%; text-align: center;">
		<c:choose>
			<c:when test="${pb.tp eq 0}">
			未找到销售记录！
		</c:when>
			<c:otherwise>
				<%@include file="/pager_zbtj.jsp"%> <!-- 和占比统计的分页页面共用，因为参数名完全一样 -->
			</c:otherwise>
		</c:choose>
	</div>


	<script type="text/javascript">
		$('.form_datetime').datetimepicker({
			format: 'yyyy-mm-dd hh:ii',
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