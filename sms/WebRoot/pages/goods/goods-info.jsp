<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>

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





  <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
   <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
   <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="<%=basePath%>js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.zh-CN.js"
	charset="utf-8"></script>
<script type="text/javascript">
	$(function(){
	
		$("#search").click(function(){
			
			var store=$("#store").val();
			var g_del=$("#g_del").val();
			var c_id=$("#c_name").val();
			var c_name=$("#c_name :selected").text();
			var currentPage=null;
			var key=$("#key").val();
			if(store==null){
				alert("请重新选择店铺！");
				return;
			}
			$("#goodsinfodiv").empty();
			$.post("<%=basePath%>goods", {
				"m" : "findGoodByPage",
				"s_id" : store,
				"g_del" : g_del,
				"c_name" :c_name  ,
				"c_id" :c_id  ,
				"currentPage":currentPage,
				"key":key,
			}, function(data) {
				$("#goodsinfodiv").append(data);
			}, "html");
			
		});
		
	
		$("#kuaisu").click(function(){
			var store=$("#store").val();
			var s_name=$("#store :selected").text();
			if(store==null){
				alert("请重新选择店铺！");
				return;
			}
			$("#hehe").empty();
			$.post("<%=basePath%>goods", {
				"m" : "kuaisu",
				"store" : store,
				"s_name":s_name,
				"s_id":store,
				
			}, function(data) {
				$("#hehe").append(data);
			}, "html");
			
		});
		
	});
</script>
<script>
function fuzhishangpin(){
	var s_id=$("#store").val();
	var s_name=$("#store :selected").text();
	$("#motai4").empty();
	$.post("<%=basePath%>goods", {
		"m" : "fuzhishangpin",
		"s_id" :s_id,
		"s_name" :s_name  ,
	}, function(data) {
		$("#motai4").append(data);
	}, "html");
	
	
}
function daochu(){
	var s_id=$("#store").val();
	var s_name=$("#store :selected").text();
	$("#motai3").empty();
	$.post("<%=basePath%>goods", {
		"m" : "daochu",
		"s_id" :s_id,
		"s_name" :s_name  ,
	}, function(data) {
		$("#motai3").append(data);
	}, "html");
	
	
}
function DaoRu(){
	
	$("#motai1").empty();
	$.post("<%=basePath%>goods", {
		"m" : "daoru",
	}, function(data) {
		$("#motai1").append(data);
	}, "html");
	
	
}
//新增商品
function xinzengshangpin(){
	var message="";
	var s_id=$("#store").val();
	var s_name=$("#store :selected").text();
	if(store==null){
		alert("请重新选择店铺！");
		return;
	}
	$("#motai").empty();
	$.post("<%=basePath%>goods",{
		"m" : "addGoods",
		"s_id" :s_id,
		"s_name" :s_name  ,
		"message" :message,
	}, function(data) {
		$("#motai").append(data);
	}, "html");
	$("#BAOCUN").hide();
	$("#tuichu").hide();
}

</script>
</head>

<body>
<div id="hehe">
	<span class="label label-success" style="padding: 10px">商品资料</span>
	&nbsp;&nbsp;
	<button type="button" onclick="xinzengshangpin()" class="btn btn-primary"
		data-toggle="modal" data-target="#myModal" >新增商品</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-primary" data-toggle="modal" 
   data-target="#myModal1" onclick="DaoRu()">导入</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-primary" data-toggle="modal" 
   data-target="#myModal3" onclick="daochu()">导出</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-primary" data-toggle="modal" 
   data-target="#myModal4" onclick="fuzhishangpin()">复制商品</button>
	&nbsp;&nbsp;
   <button type="button" id="kuaisu" class="btn btn-primary"
		name="submit" >快速录入</button>
	<select id="store" class="btn btn-success">
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

	<select id="g_del"  class="btn btn-success">
		<option value="1" selected="selected">有效单据</option>
		<option value="0">作废单据</option>
	</select>
<select id="c_name" class="btn btn-success">

									<%
										List<Object[]> list1 = (List<Object[]>) request.getAttribute("fenlei");
										if (list1 != null && list1.size() != 0) {
											for (Object[] obj : list1) {
									%>
									<option value='<%=obj[0]%>'><%=obj[1]%></option>
									<%
										}
										}
									%>
</select>
	<div style="float: right;">
		<input style="height:33px" id="key" class="input" type="text" float:right /> <input
			type="button" value="查询" id="search" class="btn btn-primary" />
		
	</div>
	
	
     <div data-spy="scroll"
		style="width:1200px;height:500px; overflow: auto; position: relative;"
		data-offset="10">

		<div id="goodsinfodiv" >
		
		
		</div>

	</div>
</div>

</body>

</html>

<!-- 模态框  新增（Modal） -->
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
           <button type="button" id="BAOCUN" onclick="save()" style="display:none;"  class="btn btn-primary" 
              >保存
            </button>
           
             <button type="button"  id="tuichu" style="display:none;"  class="btn btn-success" 
               data-dismiss="modal">退出
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>



<!-- 模态框  导入（Modal） -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button>
         </div>
            
      <!-- 模态框本质内容 --> 
      <div id="motai1">
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

<!-- 模态框  编辑（Modal） -->
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
          
            <button type="button" onclick="save1()"  class="btn btn-primary" 
              >保存
            </button>
             <button type="button"  id="tuichu2" class="btn btn-success" 
               data-dismiss="modal">退出
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<!-- 模态框  导出（Modal） -->
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
          
            <!-- <button type="button" onclick="save1()"  class="btn btn-primary" 
              >保存
            </button>
             <button type="button"  id="tuichu2" class="btn btn-success" 
               data-dismiss="modal">退出
            </button> -->
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<!-- 模态框  复制（Modal） -->
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
          
            <!-- <button type="button" onclick="save1()"  class="btn btn-primary" 
              >保存
            </button>
             <button type="button"  id="tuichu2" class="btn btn-success" 
               data-dismiss="modal">退出
            </button> -->
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>