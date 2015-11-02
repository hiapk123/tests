<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<base href="<%=basePath%>">
<title>收银员业绩</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<!-- 需要引入的前端界面的样式 -->
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
<style type="text/css">
a {
	cursor: pointer;
}
</style>
</head>
<script type="text/javascript">
//这里编写初始化的页面加载信息
$(document).ready(function(){
	var sspar1=$("#shempperformtstore").val();
	//var sspar2=$("#").val();
	//var sspar3=$("#").val();
	$.post("<%=basePath%>emperformanceinit",{
		"sspar1":sspar1
	},function(date){
		 $("#shemperformbodys").append(date);
		
	},"html");
	
})
</script>
<script type="text/javascript">
 function shemppreformcheck() 
 {
	 //alert("sd");
	 var shempperform1=$("#shempperformtstore").val();
	 var shempperform2=$("#shempperformemplee").val();
	 var shempperform3=$("#shemppreformfenlei").val();
	 var shempperformtime1=$("#shemperformtime1").val();
	 var shempperformtime2=$("#shemperformtime2").val();
	 //alert(shempperform1);
	 //alert(shempperform2);
	 //alert(shempperform3);
	 //alert(shempperformtime1);
	 //alert(shempperformtime2);
	 $.post("<%=basePath%>emperfomancetable",{
		 "shempperform1":shempperform1,
		 "shempperform2":shempperform2,
		 "shempperform3":shempperform3,
		 "shempperformtime1":shempperformtime1,
		 "shempperformtime2":shempperformtime2 
		 
	 },function(date){
		 alert("返回数据成功");
		 //alert(date);
		  $("#shemperformbodys").empty();
		 $("#shemperformbodys").append(date);
		 
	 },"html");
	 
 }
</script>
<body>
<div>
<div class="conditionNav" style="clear:both; margin-top : 0px">
		<!-- //右边的显示部分  -->
		<div class="conditionNav" style="float: right;">
			<select id="shempperformtstore">
			<%
			List<Object[]> shparameter1=(List<Object[]>)request.getAttribute("shperformstore");
			if(shparameter1.size()!=0&&shparameter1!=null)
			{
				for(Object[] oiu:shparameter1)
				{
					
			
			%>
				<option value="<%=oiu[0]%>"><%=oiu[0] %></option>	
			<%
				}
			}
			
			%>
			</select> 
			
			<select id="shempperformemplee">
				<option value="-1">全部收银员</option>
				<%
				List<Object[]> shparameter2=(List<Object[]>)request.getAttribute("shperformempname");
				if(shparameter2!=null&& shparameter2.size()!=0)
				{
					for(Object[] pld:shparameter2)
					{
		
				%>
				<option value="<%=pld[0]%>"><%=pld[0] %></option>
				<%
				
					}
				}
				
				
				%>
			</select> 
			
			<select id="shemppreformfenlei">
			<option value="-1">全部分类</option>
			<%
			List<Object[]> shparameter3=(List<Object[]>)request.getAttribute("shperformgoodstype");
			if(shparameter3!=null&& shparameter3.size()!=0)
			{
				for(Object[] hyss:shparameter3)
				{
					
		
			%>
				<option value="<%=hyss[0]%>"><%=hyss[0] %></option>
			<%
				}
			}
			%>
			</select> 
			
			<!-- //时间选择列表-->
			<input id="shemperformtime1" size="16" type="text" value="2015-06-15 14:45" readonly class="form_datetime">
			<input id="shemperformtime2" size="16" type="text" value="2015-06-15 14:45" readonly class="form_datetime">
			 <input onclick="shemppreformcheck();" type="button" value="查询">
		</div>
	</div>
		<div>
		<!-- //这里是所列的表格 -->
		<table class="table table-bordered" name="numgettable">
			<thred>
			<tr>
				<td>序号</td>
				<td>日期</td>
				<td>收银员</td>
				<td>商品名称</td>
				<td>单价</td>
				<td>数量</td>
				<td>总价</td>
				<td>实收</td>
				<td>利润</td>
				<td>类型</td>
			</tr>
			</thred>
			<tbody id="shemperformbodys">
			</tbody>
		</table>
	</div>

</div>	
</body>
</html>
<script>
//时间选择列表
 $(".form_datetime").datetimepicker({
	 format: 'yyyy-mm-dd hh:ii',
	 autoclose: true,
	 minuteStep: 10
 });
</script>