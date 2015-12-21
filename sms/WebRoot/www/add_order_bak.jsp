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
<script type="text/javascript">
	$(function(){
		$.get('<%=basePath %>initTree?m=initS',null,function(stores){
			for(var i=0;i<stores.length;i++){
				$('#store').empty();
				$('#store').append("<option value=0 selected>请选择店铺</option>");
				$('#store').append("<option value=\'"+stores[i].id+"\'>"+stores[i].name+"</option>");
			}
		},"json");

		$('#store').change(function(){
			var store=$(this).val();
			$.get('<%=basePath%>addOrder?m=initTable',{'store':store,'pageNo':1},function(data){
					$('#content').html(data);
			},'html');
		});

		
	});
</script>
<style type="text/css">
a:HOVER {
	cursor: pointer;
}
</style>

</HEAD>

<BODY>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">总览</h3>
		</div>
		<div class="panel-body">
			<form class="form-inline" role="form">
				<div class="form-group">
					<select id='store'>
						<optgroup label="请选择店铺">
							<option>1</option>
							<option>1</option>
							<option>1</option>
						</optgroup>
					</select>
				</div>
				<label class="checkbox-inline"> <input type="radio"
					name="optionsRadiosinline" id="optionsRadios3" value="option1"
					checked> 未审核
				</label> <label class="checkbox-inline"> <input type="radio"
					name="optionsRadiosinline" id="optionsRadios4" value="option2">
					通过
				</label> <label class="checkbox-inline"> <input type="radio"
					name="optionsRadiosinline" id="optionsRadios3" value="option1">
					不通过
				</label>&nbsp;&nbsp;
				<div class="form-group">
					<label class="sr-only" for="name">名称</label> <input type="text"
						class="form-control" id="name" placeholder="请输入订单号">
				</div>

				<input type="button" class="btn btn-default" value="查找" />
			</form>

		</div>
		
		<div id='content'>
		
		</div>
		
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
					<table class="table">
					<tr>
						<th>名称</th>
						<th>数量</th>
						<th>总计</th>
					</tr>
					<c:forEach begin="1" end="10" varStatus="status">
						<tr>
							<td>
								商品${status.index }
							</td>
							<td>
								${status.index }
							</td>
							<td>
								${status.index }
							</td>
						</tr>
					</c:forEach>
						<tr>
							<td colspan="2" align="right">总计</td>
							<td>1000元</td>
						</tr>
					</table>
					
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