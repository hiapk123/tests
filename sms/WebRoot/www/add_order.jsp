<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<HTML>
<HEAD>
<TITLE></TITLE>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="www/css/bootstrap.min.css">
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script src="www/js/bootstrap.min.js"></script>
<style type="text/css">
a:HOVER {
	cursor: pointer;
}
</style>

<script type="text/javascript">
	$(function(){
		$('#show').click(function(){$('#good_select').toggle(1000);});
	});
</script>

</HEAD>

<BODY>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">已加入的商品</h3>
		</div>
		<div class="panel-body">
			<input type="button" class="btn btn-default" value="显示/隐藏" id='show'/>
			<div id='content'></div>
		</div>
	</div>
	<div id='good_select'>
		<jsp:include page="basic.jsp"></jsp:include>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">订单编号：123456</h4>
				</div>
				<div class="modal-body">
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary">提交更改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</BODY>
</HTML>