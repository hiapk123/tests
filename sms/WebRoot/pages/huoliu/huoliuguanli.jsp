

<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<base href="<%=basePath%>">
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content=",keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=basePath%>bower_components/jquery/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="<%=basePath%>js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.zh-CN.js"
	charset="utf-8"></script>
<script type="text/javascript">

function trimStr(str){return str.replace(/(^\s*)|(\s*$)/g,"");}
function ruku(node){
	var l_detail=$(node).parent().parent().find(".l_detail").val();
	var l_id=$(node).parent().parent().find(".l_id").val();
	var s_id_out=$(node).parent().parent().find(".s_id_out").val();
	var s_id_in=$(node).parent().parent().find(".s_id_in").val();
	var l_serial_num=$(node).parent().parent().find(".l_serial_num").val();
	var l_date=$(node).parent().parent().find(".l_date").val();
	var tr=$(node).parent().parent();

	var td = tr.find("td");
   var zhuangtai=td.eq(8).text();
    zhuangtai=trimStr(zhuangtai);
   
    if(zhuangtai=="已完成进货"){
		alert("该货单已完成进货!");
	} 
	if(zhuangtai=="待审核"){
		alert("该货单还未被审核!");
	} 
	if(zhuangtai=="待同意"){
		alert("该货单还未被同意退货!");
	} 
	if((zhuangtai=="已审核")||(zhuangtai=="已同意")){
		alert("hahah");
	   
	        $("#hlgldiv").empty();
		$.post("<%=basePath%>huoliu", {
			"m" : "ruku",
			"s_id_in":s_id_in,
			"s_id_out":s_id_out,
			"l_id":l_id,
			"l_detail":l_detail,
			"l_serial_num":l_serial_num,
			"l_date":l_date,
		}, function(data) {
			$("#hlgldiv").append(data);
		}, "html");
		
		
		
	}
		
	
	
}

 function getdetail(node){
	
	
	var list=$(node).val();
	$("#motai5").empty();
	$.post("<%=basePath%>huoliu", {
			"m" : "detail",
			"list":list,
		}, function(data) {
			$("#motai5").append(data);
		}, "html");
	
} 

	
function jinhuo(){
			$("#hlgldiv").empty();
			$.post("<%=basePath%>huoliu", {
				"m" : "jinhuo",
			}, function(data) {
				$("#hlgldiv").append(data);
			}, "html");
			
}
function diaohuo(){
			$("#hlgldiv").empty();
			$.post("<%=basePath%>huoliu", {
				"m" : "diaohuo",
			}, function(data) {
				$("#hlgldiv").append(data);
			}, "html");
			
}
		
function tuihuo(){
			$("#hlgldiv").empty();
			$.post("<%=basePath%>huoliu", {
				"m" : "tuihuo",
			}, function(data) {
				$("#hlgldiv").append(data);
			}, "html");
			
}
function search1(){
	
			var s_id=$("#store").val();
			var s_name=$("#store :selected").text();
			var type=$("#category").val();
			var currentPage=null;
			if(store==null){
				alert("请重新选择店铺！");
				return;
			}
			$("#findhl").empty();
			$.post("<%=basePath%>huoliu", {
				"m" : "findhl",
				"s_id" : s_id,
				"s_name":s_name,
				"type" :type,
				"currentPage":currentPage,
			}, function(data) {
				$("#findhl").append(data);
			}, "html");
			
}
		
		
		
	
</script>
</head>

<body>
<div id="hlgldiv">

	<button type="button" class="btn btn-success" name="submit"
		onclick="jinhuo()">进货</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" name="submit"
		onclick="diaohuo()">调货</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" name="submit"
		onclick="tuihuo()">退货给供货商</button>
	<div style="float: right;">
	<select id="store" class="singleSelector">
		<option value="" selected="selected" >全部店铺</option>

		<%
			List<Object[]> list = (List<Object[]>) request.getAttribute("storeList");
			if (list != null && list.size() != 0) {
				for (Object[] obj : list) {
		%>
		<option value='<%=obj[0]%>'><%=obj[1]%></option>
		<%
			}
			}
		%>

	</select>

	<select id="category">
	    <option value="全部货单" selected="selected" >全部货单</option>
		<option value="进货单" >门店进货单</option>
		<option value="调拨出货单">调拨出货单</option>
		<option value="调拨进货单">调拨进货单</option>
		<option value="退货单">门店退货单</option>
	</select>

	
		<input class="input-medium search-query" type="text" float:right /> 
		<button type="button" class="btn btn-success" name="submit"
		onclick="search1()">查询</button>
	</div>
	
	<div data-spy="scroll"
		style="width: 100%; height: 500px; overflow: auto; position: relative;"
		data-offset="10">
 
<div id="findhl">
		
		<table class="table table-bordered table-condensed table-hover table-striped">
		<thead>
			<tr>
			    <th><input id="checkAll" type="checkbox"/></th>
				<th>序号</th>
				<th>操作</th>
				<th>货流单号</th>
				<th>下单时间</th>
				<th>货单类型</th>
				<th>出货门店</th>
				<th>进货门店</th>
				<th>状态</th>
				<th>货流量</th>
				<th>总价</th>
				<th>预付款</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		
		
	</tbody>

	</table>
		</div>
		
</div>
  
	</div>

 
</body>


</html>
<!-- 模态框  详细（Modal） -->
<div class="modal fade" id="myModal5" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button>
         </div>
            
      <!-- 模态框本质内容 --> 
      <div id="motai5">
   </div>    
     <!-- 模态框本质内容 -->   
         <div class="modal-footer">
           
           
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<!-- 模态框  ruku jh（Modal） -->
<div class="modal fade" id="myModaljh" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close"  data-dismiss="modal" aria-hidden="true">&times; </button>
			</div>

			<!-- 模态框本质内容 -->
			<div id="motaijh">

				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">预付款:</label>
						<div class="col-sm-6">
							<input id="paidMoney" class="pay form-control" value=""
								maxlength="10" type="text">
						</div>
						<label for="firstname" class="col-sm-1 control-label">元</label>
					</div>


				</form>
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">备注:</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="txt_remarks" value="">
						</div>
					</div>
				</form>
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<div class="col-sm-3"></div>
						<div class="col-sm-6">
							<button type="button" class="btn btn-success"
								style="width: 150px" data-dismiss="modal" onclick="Inforukujh()">通知收银端入库</button>
						</div>
					</div>
				</form>



			</div>

			<!-- 模态框本质内容 -->
			<div class="modal-footer">
			
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>

<!-- 模态框  ruku dh（Modal） -->
<div class="modal fade" id="myModaldh" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
			</div>

			<!-- 模态框本质内容 -->
			<div id="motaidh">

				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">预付款:</label>
						<div class="col-sm-6">
							<input id="paidMoney2" class="pay form-control" value=""
								maxlength="10" type="text">
						</div>
						<label for="firstname" class="col-sm-1 control-label">元</label>
					</div>


				</form>
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">备注:</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="txt_remarks2" value="">
						</div>
					</div>
				</form>
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<div class="col-sm-3"></div>
						<div class="col-sm-6">
							<button type="button" class="btn btn-success"
								style="width: 150px" data-dismiss="modal" onclick="Inforukudh()">通知收银端入库</button>
						</div>
					</div>
				</form>



			</div>

			<!-- 模态框本质内容 -->
			<div class="modal-footer"></div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>

<!-- 模态框  ruku th（Modal） -->
<div class="modal fade" id="myModalth" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
			</div>

			<!-- 模态框本质内容 -->
			<div id="motaith">

				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">预付款:</label>
						<div class="col-sm-6">
							<input id="paidMoney" class="pay form-control" value=""
								maxlength="10" type="text">
						</div>
						<label for="firstname" class="col-sm-1 control-label">元</label>
					</div>


				</form>
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">备注:</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="txt_remarks3" value="">
						</div>
					</div>
				</form>
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<div class="col-sm-3"></div>
						<div class="col-sm-6">
							<button type="button" class="btn btn-success"
								style="width: 150px" data-dismiss="modal" onclick="Inforukuth()">通知收银端入库</button>
						</div>
					</div>
				</form>



			</div>

			<!-- 模态框本质内容 -->
			<div class="modal-footer"></div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>