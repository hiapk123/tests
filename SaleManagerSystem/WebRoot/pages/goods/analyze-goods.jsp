<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>商品信息分析</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="商品,信息,分析">
<meta http-equiv="description" content="商品信息分析页面">

<script type="text/javascript">
$(function(){

			$.post('<%=basePath%>AnalyzeGoods',{"type":"All","m":"initStore"},function(data){
				var list=$('#zxt_dropdown-menu');
				list.empty();
				for(var i=0;i<data.length;i++){
					list.append("<li data-id="+data[i].sid+">"+data[i].sname+"</li>");
				}
				
			},"json");
		$('#select_store').click(function(){
			
		});
		
});
</script>

</head>

<body>
	<div class="panel panel-default">
		<div class="panel-body">
			<div style="float:left;width:15%;">
				<h4>商品信息分析</h4>
			</div>
			<div style="float:right;width:85%;">

				<div class="btn-group">
					<button id="select_store" type="button" class="btn btn-default dropdown-toggle"
						data-toggle="dropdown">
						选择店铺<span class="caret"></span>
						
					</button>
					<ul class="dropdown-menu" role="menu" id="zxt_dropdown-menu">
					</ul>
				</div>
				<div class="btn-group">
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">
						全部分类<span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu" >
						<li><a href="#">功能</a></li>
						<li><a href="#">另一个功能</a></li>
						<li><a href="#">其他</a></li>
						<li class="divider"></li>
						<li><a href="#">分离的链接</a></li>
					</ul>
				</div>
	
				<input type="text" value="" id="datetimepicker_analyze_1">至<input type="text"
					value="" id="datetimepicker_analyze_2"> <input type="text" value="条码/名称">
				<div class="btn-group">
					<button type="button" class="btn btn-primary dropdown-toggle">
						分析</button>
				</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div style="overflow: scroll;">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>品名</th>
						<th>条形码</th>
						<th>商品状态</th>
						<th>累计进货</th>
						<th>累计出货</th>
						<th>累计退货</th>
						<th>累计销售</th>
						<th>库存数量</th>
						<th>零售单价</th>
						<th>成本单价</th>
						<th>差价</th>
						<th>毛利润</th>
						<th>折让总额</th>
						<th>零售总额</th>
						<th>零售成本</th>
						<th>库存总额</th>
						<th>畅销系数</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Tanmay</td>
						<td>Bangalore</td>
						<td>560001</td>
						<td>Tanmay</td>
						<td>Bangalore</td>
						<td>560001</td>
						<td>Tanmay</td>
						<td>Bangalore</td>
						<td>560001</td>
						<td>Tanmay</td>
						<td>Bangalore</td>
						<td>560001</td>
						<td>Tanmay</td>
						<td>Bangalore</td>
						<td>560001</td>
						<td>Tanmay</td>
						<td>Bangalore</td>

					</tr>
				</tbody>
			</table>

			<div>
				<ul class="pagination pagination-lg" style="float: right;">
					<li><a href="#">&laquo;</a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">&raquo;</a></li>
				</ul>
			</div>



		</div>
	</div>
</body>
</html>

