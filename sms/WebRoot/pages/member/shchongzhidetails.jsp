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
<title>充值明细</title>
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
function sh_memmoneyclick()
{
	//alert("submit");
	var shopname =$("#sh_shopname").val();
	var time11=$("#sstartime").val();
	var time22=$("#ssendtime").val();
	var v_type=$("#shzhifufangshi").val();
	//alert(shopname);
	//alert(time1);
	//alert(time2);
	//alert(v_type);
	
	//1.对时间进行表单验证
	var d11 = new Date(time11.replace(/\-/g, "\/"));  
	var d22 = new Date(time22.replace(/\-/g, "\/"));  
	if(d11>d22)
		{
		alert("开始时间不能大于结束时间");
		return false;
		}
	else{
		//进行表单的查询提交
		$.post("<%=basePath%>shdetailsbath",{
			"type":"init",
			"shopname":shopname,
			"sh_stime":time11,
			"sh_etime":time22,
			"sh_fangshi":v_type	
		},function(date){
			//alert(date);
			//可以直接实现页面的绑定
			$("#sh_moneytable").empty();
			$("#sh_moneytable").append(date);
			
		},"html");
		
		
		//结束
	}
	
}

</script>
<script type="text/javascript">
$(document).ready(function(){
	//alert("初始化");
	//初始化绑定时间列表
	var t=document.getElementsByName("kkendTime");
	var d=new Date(); 
	t.value=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate()+" "+d.getHours()+":"+d.getMinutes(); 
	var s=t.value;
	//alert(s);
	$("#sstartime").val(s);
	$("#ssendtime").val(s);
	//获取数据
	var shopname=$("#sh_shopname").val();
	var sh_stime=$("#sstartime").val();
	var sh_etime=$("#ssendtime").val();
	var sh_fangshi=$("#shzhifufangshi").val();

	//初始化页面提交
	$.post("<%=basePath%>shdetailsbath",{
		"type":"init",
		"shopname":shopname,
		"sh_stime":sh_stime,
		"sh_etime":sh_etime,
		"sh_fangshi":sh_fangshi	
	},function(date){
		
		//可以直接实现页面的绑定
		$("#sh_moneytable").empty();
		$("#sh_moneytable").append(date);
		
	},"html")
	
	//alert($("#shzhifufangshi").val());
});
</script>
<body>
	<!-- 表头部分 -->
  <div class="conditionNav">
	<div class="panel panel-default">
		<div class="panel-footer">
			<div class="row">
			<!--   下拉框开始 -->
					<div class="col-xs-2">
						<select id="sh_shopname" class="form-control" >
						<%
						List<Object[]> dlist=(List<Object[]>)request.getAttribute("shdetails");
						if(dlist!=null&&dlist.size()!=0)
						{
							for(Object[] obj:dlist)
							{
								
						
						%>
							<option value="<%=obj[0]%>"><%=obj[0] %></option>
						<% 
	}
							
						}
						%>	
						</select>
					</div>
				<!-- 	第一个日期插件 -->
		<div class="col-xs-3">
			<div class="input-group date form_datetime" data-date=""
						data-date-format="yyyy-mm-dd HH:mm:ss"
						data-link-field="dtp_input1">
						<input  id="sstartime" name="kkendTime" class="form-control" size="16" type="text"
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
						<input id="ssendtime" name="kkendTime" class="form-control" size="16" type="text"
							value="" readonly> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-remove"></span></span> <span
							class="input-group-addon"><span
							class="glyphicon glyphicon-th"></span></span>
					</div>
				<input type="hidden" id="dtp_input1" value="" />
		</div>
		
		<div class="col-xs-2">
						<select id="shzhifufangshi" class="form-control" >
							<option value="1">现金</option>
							<option value="2">银联卡</option>
							<option value="3">在线</option>
						</select>
					</div>
					
		<div class="col-xs-1">
		<input type="button" value="查询" id="" class="btn btn-primary" onclick="sh_memmoneyclick();" />
		</div>
		<!-- 	下拉框结束 -->
			  </div>
		</div>
		  </div>
		    </div>
		    
<!-- 	显示表格的部分	 -->
	<div class="panel panel-default" id="" >
		<!--start  -->
		<div id="sh_moneytable" style="height: 535px"></div>
		<!-- end -->
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