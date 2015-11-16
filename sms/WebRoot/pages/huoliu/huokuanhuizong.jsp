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




</head>

<body>
	<div class="row">
		<div class="box col-md-12">
			
			<font size="5.8"><span class="label-success label label-default">从</span></font>
			<select data-rel="chosen" class="btn btn-default">
				<option disabled>开始时间</option>
				<option>2015-10-05<option>
			</select> 
			<font size="5.8"><span class="label-success label label-default">到</span></font>
			<select data-rel="chosen" class="btn btn-default">
				<option disabled>结束时间</option>
				<option>2015-11-05</option>
			</select>

			<button type="submit" class="btn btn-success">查询</button>
		</div>
		<div class="box-content">
			<table
				class="table table-striped table-bordered bootstrap-datatable datatable responsive">
				<thead>
					<tr>
						<th>序号</th>
						<th>商品条码</th>
						<th>商品名称</th>
						<th>商品进价</th>
						<th>商品售价</th>
						<th>累计出货</th>
						<th>累计进货</th>
						<th>利润</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach begin="1" end="10" step="1" varStatus="status">
					<tr>
						<td class="center">${status.index }</td>
						<td class="center">626262612155121${status.index }</td>
						<td class="center">矿泉水${status.index }</td>
						<td class="center">${status.index*status.index }</td>
						<td class="center">${status.index*status.index+10 }</td>
						<td class="center">${status.index*10 }</td>
						<td class="center">${status.index*15 }</td>
						<td class="center">${status.index*status.index*status.index*10-5*status.index }</td>
						<td class="center">有所为</td>
						<td class="center">
						<a class="btn btn-danger" href="#"> <i
								class="glyphicon glyphicon-trash icon-white"></i> 删除
						</a></td>
					</tr>
				</c:forEach>
					<tr>
						<td colspan="3"></td>
						<td>汇总</td>
						<td	colspan="3">10009209元</td>
						
					    <td>2000.9元</td>
					    <td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

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
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach begin="1" end="10" step="1" varStatus="status">
								<tr>
									<td>${status.index}</td>
									<td>201511051114${status.index }</td>
									<td>商品${status.index }</td>
									<td>${status.index*status.index }</td>
									<td>${status.index*status.index }</td>
									<td>${status.index*status.index*status.index*status.index }</td>
									<td><a class="btn btn-info" href="#"> <i
											class="glyphicon glyphicon-edit icon-white"></i> 编辑
									</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-default" data-dismiss="modal">关闭对话框</a>
					<a href="#" class="btn btn-primary" data-dismiss="modal">保存订单 </a>
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
					<select data-rel="chosen" class="btn btn-default" style="margin-bottom: 20px;">
						<option disabled>选择要编辑的商品</option>
						<option value="2015222222">水杯</option>
						<option value="2015222222">洗衣粉</option>
						<option value="2015222222">拖把</option>
						<option value="2015222222">毛巾</option>
					</select>
					
					<table
						class="table table-striped table-bordered bootstrap-datatable datatable responsive">
						<tbody>

							<tr>
								<td>商品条码</td>
								<td><input type="text" class="form-control"
									id="inputSuccess1" disabled="disabled"></td>
							</tr>
							<tr>
								<td>商品名称</td>
								<td><input type="text" class="form-control"
									id="inputSuccess1" disabled="disabled"></td>
							</tr>
							<tr>
								<td>进货数量</td>
								<td><input type="text" class="form-control"
									id="inputSuccess1" value="10"></td>
							</tr>
							<tr>
								<td>进货价格</td>
								<td><input type="text" class="form-control"
									id="inputSuccess1" value="30.6"></td>
							</tr>
							<tr>
								<td>备注</td>
								<td><input type="text" class="form-control"
									id="inputSuccess1" value="库存不足"></td>
							</tr>
						</tbody>
					</table>

				</div>
				<div class="modal-footer">
					<a href="#" class="btn btn-default" data-dismiss="modal">关闭对话框</a>
					<a href="#" class="btn btn-primary" data-dismiss="modal">保存订单 </a>
				</div>
			</div>
		</div>
	</div>
	<div align="center">
		<input type="hidden" id="page" value="20" />
		<ul class="pagination">
			<page:htmlPage pageNo="20" url="index.jsp" totalSum="980"
				showPage="10" pageSize="10" />
		</ul>
	</div>

</body>
</html>

