<%-- <%@page import="org.omg.CORBA.OMGVMCID"%> --%>
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
<title>会员购买查询</title>
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
	
<style type="text/css">
a {
	cursor: pointer;
}
</style>
</head>
<script type="text/javascript">
//页面加载默认绑定时间
$(document).ready(function(){
	
	//将当前时间绑定时间下拉框
	var t=document.getElementsByName("shbuyscendTime");
	var d=new Date(); 
	t.value=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "+d.getHours()+":"+d.getMinutes(); 
	var s=t.value;
	$("#sh_selecttime1").val(s);
	$("#sh_selecttime2").val(s);
});

</script>
<script type="text/javascript">
function sh_buyquery() {
	//alert("查询");
	//1.进行表单验证(a.验证文本框是否为空。b.验证时间下拉框是否符合要求.c.验证文本框是否为数字)
	var textfiled=document.sh_buyform.sh_membuyselect.value;
	if(textfiled=="")
		{
		alert("请输入查询会员编号");
		return false;
		}
	//判断输入是否为数字
	if(isNaN(textfiled))
		{
		alert("会员编号只能是数字序列");
		document.sh_buyform.sh_membuyselect.value="";
		//document.sh_buyform.sh_membuyselect.style.border="red";
		document.sh_buyform.sh_membuyselect.focus();
		return false;
		}
	
	//判断时间是否符合要求
	var time1=$("#sh_selecttime1").val();
	var time2=$("#sh_selecttime2").val();
	//alert(time1);
	//alert(time2);
	var d1 = new Date(time1.replace(/\-/g, "\/"));  
	var d2 = new Date(time2.replace(/\-/g, "\/"));  
	if(d1>d2)
		{
		alert("开始时间不能大于结束时间,重新选择：")
		return false;
		}
	// 表单提交
	$.post("<%=basePath%>",{
		"textfiled":textfiled,
		"time1":time1,
		"time2":time2
	},function(date){
		alert(date);
		
	},"html")
}
</script>
<body>
  <div class="conditionNav">
  <form name="sh_buyform">
	<div class="panel panel-default">
		<div class="panel-footer">
			<div class="row">
				<div class="col-xs-2">
					<input name="sh_memtext" class="search-query form-control col-md-10" type="text" id="sh_membuyselect" placeholder="输入会员号" >
				</div>		
					<!-- 	第一个日期插件 -->
		<div class="col-xs-3">
			<div class="input-group date form_datetime" data-date=""
						data-date-format="yyyy-mm-dd HH:mm:ss"
						data-link-field="dtp_input1">
						<input id="sh_selecttime1" name="shbuyscendTime" class="form-control" size="16" type="text"
							value="" readonly> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-remove"></span></span> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-th"></span></span>
					</div>
					<input type="hidden" id="dtp_input1" value="" />
		</div>	
	<div style="float: left"><font size="5">至</font></div>
		<!-- 显示第二个下拉框 -->
			<div class="col-xs-3">
			<div class="input-group date form_datetime" data-date=""
						data-date-format="yyyy-mm-dd HH:mm:ss"
						data-link-field="dtp_input1">
						<input id="sh_selecttime2" name="shbuyscendTime" class="form-control" size="16" type="text"
							value="" readonly> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-remove"></span></span> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-th"></span></span>
					</div>
				<input type="hidden" id="dtp_input1" value="" />
		</div>
		<!-- 查询按钮 -->
		<div class="col-xs-1">
		<input type="button" value="查询" id="emplogsub" class="btn btn-primary" onclick="sh_buyquery()" />
		</div>	
			</div>
		</div>
	</div>
	</form>
</div>



</body>
<script type="text/javascript">
		$('.form_datetime').datetimepicker({
			format : 'yyyy-mm-dd hh:ii',
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
</html>