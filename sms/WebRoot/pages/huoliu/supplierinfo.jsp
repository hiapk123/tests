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
<script language="javascript" src="<%=basePath%>js/ajaxfileupload.js" > </script>
 <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=basePath%>bower_components/jquery/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>

	
<script type="text/javascript">

function editsupplier2(){
	$("#motai1").empty();
	 var s_id=$("#s_id").val();
	 var s_name=$("#s_name").val();
	 var su_id=$("#su_id").val();
	var su_name=$("#su_name").val();
	if(su_name=="") {alert("供货商名称不能为空"); return;}
	var su_phone=$("#su_phone").val();
	var su_email=$("#su_email").val();
	var su_contacter=$("#su_contacter").val();
	var s_del=$("#s_del").val();
	var su_ps_return=$("#su_ps_return").val();
	if(su_ps_return=="") su_ps_return=0;
	if(isNaN(su_ps_return)){alert("配送返费点请输入数字"); return;}
	var su_gd_return=$("#su_gd_return").val();
	if(su_gd_return=="") su_gd_return=0;
	if(isNaN(su_gd_return)){alert("固定返利点请输入数字"); return;}
	var su_empower=$("#su_empower").val();
	var su_address=$("#su_address").val();
	var su_info=$("#su_info").val();
	$("#supplierdiv").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "editSupplier2",
		"su_id" : su_id,
		"s_id" : s_id,
		"s_name" : s_name,
		"su_name" : su_name,
		"su_phone" : su_phone,
		"su_email" : su_email,
		"su_contacter":su_contacter,
		"s_del" : s_del,
		"su_ps_return" : su_ps_return,
		"su_gd_return" : su_gd_return,
		
		"su_empower" : su_empower,
		"su_address" : su_address,
		"su_info" : su_info,
	}, function(data) {
		$("#supplierdiv").append(data);
	}, "html");
	$("#BB").click();
}
function editsupplier(node){
	var su_id=$(node).parent().parent().find(".su_id").val();
	
	$("#motai2").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "editSupplier",
		"su_id" : su_id,
	}, function(data) {
		$("#motai2").append(data);
	}, "html");
	
}
function addsupplier(){
	var flag=1;
	var s_id=$("#store").val();
	var s_name=$("#store :selected").text();
	
	var su_name=$("#su_name").val();
	if(su_name=="") {alert("供货商名称不能为空"); return;}

	var su_phone=$("#su_phone").val();
	var su_email=$("#su_email").val();
	var su_contacter=$("#su_contacter").val();
	var s_del=$("#s_del").val();
	var su_ps_return=$("#su_ps_return").val();
	if(su_ps_return=="") su_ps_return=0;
	if(isNaN(su_ps_return)){alert("配送返费点请输入数字"); return;}
	var su_gd_return=$("#su_gd_return").val();
	if(su_gd_return=="") su_gd_return=0;
	if(isNaN(su_gd_return)){alert("固定返利点请输入数字"); return;}
	var su_number=$("#su_number").val();
	if(su_number=="") {alert("供货商编号不能为空"); return;}
	if(isNaN(su_number)){alert("供货商编号请输入数字"); return;}
	var su_empower=$("#su_empower").val();
	var su_address=$("#su_address").val();
	var su_info=$("#su_info").val();
	$("#supplierdiv").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "addsupplier2",
		"s_id" : s_id,
		"s_name" : s_name,
		"su_name" : su_name,
		"su_phone" : su_phone,
		"su_email" : su_email,
		"su_contacter":su_contacter,
		"s_del" : s_del,
		"su_ps_return" : su_ps_return,
		"su_gd_return" : su_gd_return,
		"su_number" : su_number,
		"su_empower" : su_empower,
		"su_address" : su_address,
		"su_info" : su_info,
	}, function(data) {
		if(data!="buhege"){
			$("#supplierdiv").append(data);
			$("#bs").click();
		}else{
			alert("该供货商编号已存在");
			return;
		}
	}, "html");
}



function csh(){
	
	var s_id=$("#store").val();
	var s_name=$("#store :selected").text();

	if(store==null){
		alert("请重新选择店铺！");
		return;
	}
	$("#motai").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "addsupplier",
		"s_id" : s_id,
		"s_name" :s_name  ,
		
	}, function(data) {
		$("#motai").append(data);
	}, "html");
	
}
function search(){
	
		
			var store=$("#store").val();
			var category=$("#category").val();
			var key=$("#key").val();
			if(store==null){
				alert("请重新选择店铺！");
				return;
			}
			$("#supplierdiv").empty();
			$.post("<%=basePath%>huoliu", {
				"m" : "supplierInfo1",
				"s_id" : store,
				"s_del" :category  ,
				"key":key,
			}, function(data) {
				$("#supplierdiv").append(data);
			}, "html");
		
}
</script>

</head>
<script>
function daoru(){
	var s_id=$("#store").val();
	var s_name=$("#store :selected").text();
	$("#motai3").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "daoru",
		"s_id" : s_id,
		"s_name" : s_name,
	}, function(data) {
		$("#motai3").append(data);
	}, "html");
}
function daochu(){
	
	$("#motai4").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "daochu",
		
	}, function(data) {
		$("#motai4").append(data);
	}, "html");
}
</script>
<body>


	

	<!-- 按钮触发模态框 -->
<button class="btn btn-primary " onclick="csh()" data-toggle="modal" 
   data-target="#myModal">
  新增供货商
</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" data-toggle="modal" 
   data-target="#myModal3" onclick="daoru()">导入</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" data-toggle="modal" 
   data-target="#myModal4" onclick="daochu()">导出</button>
	
	<select id="store" class="singleSelector">
		<!-- <option value="-1" selected="selected" disabled="disabled">选择店铺</option> -->

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
		<option value="1" selected="selected">有效单据</option>
		<option value="0">作废单据</option>
	</select>

	<div style="float: right;">
		<input class="input-medium search-query" id="key" type="text" placeholder="名称/编号/电话" float:right /> <input
			type="button" class="btn btn-danger" value="查询" onclick="search()" class="submitBtn" />
		
	</div>
	
	<div data-spy="scroll"
		style="width: 100%; overflow: auto; position: relative;"
		data-offset="10">
    <div id="supplierdiv"> 
<div id="2">
		
		<table class="table table-bordered table-condensed table-hover table-striped ">
		<thead>
			<tr class="info">
				<th>操作</th>
				<th>编号</th>
				<th>名称</th>
				<th>联系人</th>
				<th>电话</th>
				<th>邮箱</th>
				<th>授权状态</th>
			</tr>
		</thead>
		<tbody>
		<%
			List<Object[]> supplier = (List<Object[]>) request.getAttribute("list");

			String s_id = request.getParameter("s_id").toString();
			String yanse[]={"success","danger","active","warning","info"};
			if (supplier != null && supplier.size() > 0) {
				for (int i = 0; i < supplier.size(); i++) {
		%>
		<tr class="<%= yanse[i%5]%>">
			<td> <a  onclick="editsupplier(this)" data-toggle="modal" 
   data-target="#myModal2">编辑</a></td>
					<input type="hidden" class="su_id" value="<%=supplier.get(i)[6]%>">	
					<%
			
				for (int j = 0; j <= 5; j++) {
			%>
			<td><%=supplier.get(i)[j]%></td>
			<%
				}
			%>
		</tr>
		<%
			}
			}
		%>
	</tbody>

	</table>
		</div>
</div>
	</div>


</body>

</html>
<!-- 模态框  新增供货商（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button>
         </div>
            
      <!-- 模态框本质内容 --> 
      <div id="motai">
      
   </div>    
     <!-- 模态框本质内容 -->   
       
       
       
         <div class="modal-footer">
            <button type="button" id="as" onclick="addsupplier()" class="btn btn-primary" 
              >保存
            </button>
           
             <button type="button" id="bs"  class="btn btn-default" 
               data-dismiss="modal">取消
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<!-- 模态框  编辑供货商（Modal） -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button>
         </div>
            
      <!-- 模态框本质内容 --> 
      <div id="motai2">
      
   </div>    
     <!-- 模态框本质内容 -->   
         <div class="modal-footer">
            <button type="button" id="AA" onclick="editsupplier2()" class="btn btn-primary" 
              >保存
            </button>
           
             <button type="button" id="BB"  class="btn btn-default" 
               data-dismiss="modal">取消
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<!-- 模态框  导入（Modal） -->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button>
         </div>
            
      <!-- 模态框本质内容 --> 
      <div id="motai3">
   </div>    
     <!-- 模态框本质内容 -->   
         <div class="modal-footer">
          
           
             <button type="button"   class="btn btn-success" 
               data-dismiss="modal">退出
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<!-- 模态框  导出（Modal） -->
<div class="modal fade" id="myModal4" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button>
         </div>
            
      <!-- 模态框本质内容 --> 
      <div id="motai4">
   </div>    
     <!-- 模态框本质内容 -->   
         <div class="modal-footer">
           
           
             <button type="button"   class="btn btn-default" 
               data-dismiss="modal">退出
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>