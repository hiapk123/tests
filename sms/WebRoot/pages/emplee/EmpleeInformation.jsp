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
<base href="<%=basePath%>">
<title>员工资料</title>
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
/* $(function(){
	//var ssk=document.getElementById("shfirsttable").value;
	//alert("选中的内容"+ssk);
	var ssj=document.getElementById("shfirsttable").value;	
	$("#shfirsttable option[value="+ssj+"]").attr("selected", "selected");
	
}) */

//这里进行初始化页面的加载
$(document).ready(function(){
	var empleestore=$("#shmemstore").val();
	var empleestate=$("#shmemstate").val();
	//alert(empleestate);
	$.post("<%=basePath%>empleeinit",{"empleestore":empleestore,"empleestate":empleestate},function(data){
		//alert("返回数据成功");
		//alert(data);
		$("#shempleetable").append(data);
		},"html");
	
})
</script>
<script type="text/javascript">
//点击查询按钮
function shcheckbutton()
{
	//alert("点击查询");
	var storeide=$("#shmemstore").val();
	var statee=$("#shmemstate").val();
	var shtext=$("#shcheckbox").val();
	if(shtext=="")
		{
		//默认查询,没有文本框的情况
		$.post("<%=basePath%>empleecheckbox",
				{
			"type":"1",
			"storeide":storeide,
			"statee":statee,
			"shtext":shtext
				},function(data){
					//alert("返回数据成功");
					$("#shempleetable").empty();
					$("#shempleetable").append(data);
					
				},"html");
		
		
		}
	else
		{
		//有文本框的情况
		$.post("<%=basePath%>empleecheckbox",
				{
			"type":"0",
			"storeide":storeide,
			"statee":statee,
			"shtext":shtext
				},function(data){
					//alert("返回数据成功");
					$("#shempleetable").empty();
					$("#shempleetable").append(data);
					
				},"html");
		
		
		}
	
}
</script>
<script type="text/javascript">
function empleebjmodel(id) {
	//js前台调试  window.console.log(shbuton);
	var shemptheidd=id;
	//alert(shemptheidd);
	//传递前台的id号吗进行展示。
	$.post("<%=basePath%>shshowedit",{"shemptheidd":shemptheidd},
			function(date)
			{
		//alert(date);
		$("#shgethismodel").empty();
		$("#shgethismodel").append(date);
		//获取编辑的历史数据
		//alert("获取的历史数据是")
		
		var shhistorydate=$("#shempeidtqiy").val();
		if(shhistorydate=="1")
			{
			//说明是禁用的
			}
		else
			{
			//说明是启用的，选中
			//alert("进入到checkbox选中也")
			$("#shhistorydate").attr("checked","true");
			
			
			}
		//alert($("#shempeidtqiy").val())
		//$("body").clear();
		//$("body").append(date);
		//var ssj=document.getElementById("shfirsttable").value;	
		//$("#shfirsttable option[value="+ssj+"]").attr("selected", "selected");
		//document.write(date);
		//shshowmtk
		$("#shshowmtk").click();
		
		//并且将这个值写入到保存的value里面
		$("#shhistroyclgfslbuttom").val(id);
	},"html");
	
	
}
</script>
<script>
//激发模态框
function addempleer(){
	//alert("addemplee");
	$("#shaddmtk").click();	
}
</script>
<body>
	<!-- //会员按钮和资料 -->
	<div class="panel panel-default">
	<div class="panel-footer">
	
	<div class="conditionNav">

		<!-- //左边的button按钮 -->
		<input class="btn btn-primary" onclick="addempleer();" type="button" value="新增收银员">
		<!-- //右边的显示部分  -->
		<div class="conditionNav" style="float: right;">
		<div class="row">
			<div class="col-xs-3" >
			<select style="width: 122px" id="shmemstore" class="form-control">
				<%
					List<Object[]> listf = null;
					List<Object[]> listg = null;
					listf = (List<Object[]>) request.getAttribute("storelist");
					listg = (List<Object[]>) request.getAttribute("shempshowenditinfom");
					if (listf != null && listf.size() != 0) {
						for (Object[] obj : listf) {
				%>
				<option value="<%=obj[0]%>"><%=obj[0]%></option>
				<%
					}
					}
				%>
			</select> 
			</div>
			<div class="col-xs-3" >
			<select class="form-control" id="shmemstate">
				<option value="0" selected="selected">启用</option>
				<option value="1">禁用</option>
			</select> 
			</div>
			<div class="col-xs-4">
			<input class="search-query form-control col-md-10" id="shcheckbox" type="text" placeholder="卡号/姓名/电话" /> 
			</div>	
			<div class="col-xs-1">
			<input class="btn btn-primary" onclick="shcheckbutton();" type="button" value="查询">
			</div>
			</div>
		<!-- end -->
		</div>
	</div>
	
</div>
</div>

	<div style="overflow-y: scoll;height:50px" >
		<!-- //这里是所列的表格 -->
		<table class="table table-bordered" name="numgettable" style="height: 70px;overflow-y: scroll;">
			<thred>
			<tr>
				<td>序号</td>
				<td>操作</td>
				<td>所属门店</td>
				<td>工号</td>
				<td>姓名</td>
				<td>电话</td>
				<td>状态</td>
			</tr>
			</thred>
			<tbody id="shempleetable" style="overflow: scroll;">
			</tbody>
		</table>
	</div>

	<!-- //这里是激发模态框的按钮 -->
	<button style="display: none;" id="shaddmtk"
		class="btn btn-primary btn-lg" data-toggle="modal"
		data-target="#addempmodel">加载模态框</button>
	<div class="modal fade" id="addempmodel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">收银员资料</h4>
				</div>
				<div class="modal-body">
					<!-- //模态框的主体部分 -->
					<div class="panel panel-default">
						<div class="panel-body">
							<table>
								<tr>
									<td>是否启用:</td>
									<td></td>
									<td></td>
									<td><input type="checkbox" id="shempcheck" /></td>
								</tr>
							</table>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-body">
							<table>
								<tr>
									<td>*所属门店： <select id="shempshore">
											<%
												if (listf != null && listf.size() != 0) {
													for (Object[] obj : listf) {
											%>
											<option value="<%=obj[0]%>"><%=obj[0]%></option>
											<%
												}
												}
											%>
									</select>
									</td>
								</tr>
								<tr>
									<td>*编号：<input id="shempbh" type="text" />
									</td>
								</tr>
								<tr>
									<td>*姓名：<input id="shempxm" type="text" /></td>
								</tr>
								<tr>
									<td>电话：<input id="shempdh" type="text" /></td>
								</tr>
							</table>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button onclick="shempsave();" type="button"
						class="btn btn-primary" data-dismiss="modal">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

<div id="shgethismodel">

</div>
</body>
<script type="text/javascript">
//触发修改的模态框的内容
function shempeditd(){
	//触发修改模态框
	//alert("xasdas");
	//获取其中的5个参数
	var shempeidtqiy;
	if($("#shempeidtqiy").is(":checked"))
		{
		shempeidtqiy=0;
		
		}
	else {
		shempeidtqiy=1;
		
	}
	//alert("选择状态是："+shempeidtqiy);
	var shkkis=$("#shhistroyclgfslbuttom").val();
	var shsecondtable=$("#shsecondtable").val();
	var shsecondbh=$("#shsecondbh").val();
	var shsecondxm=$("#shsecondxm").val();
	var shsecondtel=$("#shsecondtel").val();
	//在这里出发的是修改的模态框
	//alert("判断信息");
	//alert(shkkis);
	//alert(shsecondbh);
	//alert(shsecondxm);
	//alert(shsecondtel);
	//alert("save")
	//这里进行页面的额修改
	$.post("<%=basePath%>shempleeupdatesx",{
		"shempeidtqiy":shempeidtqiy,
		"shsecondtable":shsecondtable,
		"shsecondbh":shsecondbh,
		"shsecondxm":shsecondxm,
		"shsecondtel":shsecondtel,
		"shkkis":shkkis
		
	},function(date){
		
		
	},"html");
	
	//修改成功，进行页面的刷新
	$.post("<%=basePath%>shshuaxinempleeinforma",
	{
		
		
	},function(data){
		
		//alert("刷新成功");
		$("#shempleetable").empty();
		$("#shempleetable").append(data);
	},"html");
	
	
}

</script>
<script type="text/javascript">
function shempsave()
{
	//alert("save");
	var shempstore=$("#shempshore").val();
	var shempbh=$("#shempbh").val();
	var shempxm=$("#shempxm").val();
	var shempdh=$("#shempdh").val();
	var shempstatus;
	//如果选中设置的值
	if($("#shempcheck").is(":checked"))
		{
		shempstatus=0;
		}
	else{
		shempstatus=1;
	}
	if(shempbh=="")
		{
		alert("请输入收银员编号");
		}
	else
		{
		if(shempxm==""){alert("请输入姓名");}
		else {
			//进行表单的提交
			$.post("<%=basePath%>addempinform", {
					"shempstatus" : shempstatus,
					"shempstore" : shempstore,
					"shempbh" : shempbh,
					"shempxm" : shempxm,
					"shempdh" : shempdh
				}, function(data) {
					//alert(插入成功);
					$("#shempleetable").empty();
					$("#shempleetable").append(data);

				}, "html");
			//插入成功后进行刷新列表的操作
			//提交进行update的更新操作
			//alert("开始更新");
			<%-- $.post("<%=basePath%>empinformupdatetable",{},
					function(date){
				//alert("开始");
			//alert(date);
				$("#shempleetable").empty();
				$("#shempleetable").append(date);
				
			},"html"); --%>
			

			}

		}

	}
</script>
</html>