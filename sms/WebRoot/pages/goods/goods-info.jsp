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



<SCRIPT LANGUAGE="javascript">
<!--

function fuzhishangpin()
{
	var id=$("#store").val();
	var name=$("#store :selected").text();
window.open ("<%=basePath%>goods?m=fuzhishangpin&s_id="+id+"&s_name="+name,'newwindow','height=500,width=800,top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
}
-->
</SCRIPT>


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
<link href="<%=basePath%>css/bootstrap-datetimepicker.css"
	rel="stylesheet">
<link href="<%=basePath%>css/jquery.dataTables.min.css" rel="stylesheet">

<!-- jQuery -->
<script src="<%=basePath%>bower_components/jquery/jquery.min.js"></script>

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico">

<!-- external javascript -->

<script
	src="<%=basePath%>bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="<%=basePath%>js/jquery.cookie.js"></script>
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
<script src="<%=basePath%>js/charisma.js"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.zh-CN.js"
	charset="utf-8"></script>
<script type="text/javascript">
	$(function(){
	
		$("#search").click(function(){
			var store=$("#store").val();
			var number=$("#category").val();
			var currentPage=null;
			if(store==null){
				alert("请重新选择店铺！");
				return;
			}
			$("#goodsinfodiv").empty();
			$.post("<%=basePath%>goods", {
				"m" : "findGoodByPage",
				"s_id" : store,
				"catagory" :number  ,
				"currentPage":currentPage,
			}, function(data) {
				$("#goodsinfodiv").append(data);
			}, "html");
			
		});
		
		//新增商品
		$("#xinzengshangpin").click(function(){
			var message="";
			var s_id=$("#store").val();
			var s_name=$("#store :selected").text();
			if(store==null){
				alert("请重新选择店铺！");
				return;
			}
			$("#goodsinfodiv").empty();
			$.post("<%=basePath%>goods",{
				"m" : "addGoods",
				"s_id" :s_id,
				"s_name" :s_name  ,
				"message" :message,
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
function DaoRu(){
	
	$("#motai1").empty();
	$.post("<%=basePath%>goods", {
		"m" : "daoru",
	}, function(data) {
		$("#motai1").append(data);
	}, "html");
	
	
}


</script>
</head>

<body>
<div id="hehe">
	<span class="label label-default" style="padding: 10px">商品资料</span>
	&nbsp;&nbsp;
	<button type="button" id="xinzengshangpin" class="btn btn-success"
		name="submit" >新增商品</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" data-toggle="modal" 
   data-target="#myModal1" onclick="DaoRu()">导入</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" name="submit"
		onclick="daochu()">导出</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" name="submit"
		onclick="fuzhishangpin()">复制商品</button>
	&nbsp;&nbsp;
   <button type="button" id="kuaisu" class="btn btn-success"
		name="submit" >快速录入</button>
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
		<option value="0" selected="selected">有效单据</option>
		<option value="1">作废单据</option>
	</select>

	<div style="float: right;">
		<input class="input-medium search-query" type="text" float:right /> <input
			type="button" value="查询" id="search" class="submitBtn" />
		<button type="button" class="btn btn-success" float:right>按分类</button>
	</div>
	
	<!-- <div data-spy="scroll"
		style="height: 100%; width:100%; overflow: auto; position: relative;"
		data-offset="10"> -->
     <div data-spy="scroll"
		style="width:1200px;height:auto; overflow: auto; position: relative;"
		data-offset="10">

		<div id="goodsinfodiv" >
		
		
		</div>

	</div>
</div>

</body>

</html>
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