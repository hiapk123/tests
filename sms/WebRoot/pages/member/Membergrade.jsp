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
<title>会员等级</title>
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
function shmemsavepss(id){
	//alert("进行新的保存");
	//alert(id);
	var sskheid=id;
	var sskgracelevel=$("#shmemdjmcss").val();
	var sskgracediscount=$("#shmemyhzkss").val();
	var sskgracetime=0;
	var sskgraceintegra=0;
	if($("#shmemzdsjss").is(":checked"))
		{
		sskgracetime=$("input[name='shmemgdss'][type='radio']:checked").val();
		sskgraceintegra=$("#shmemhyjfss").val();
		
		}
	//alert(sskgracelevel);
	//alert(sskgracediscount);
	//alert(sskgracetime);
	//alert(sskgraceintegra);
	//进行update的绑定(只绑定具体的数值)
	$.post("<%=basePath%>shmemgraceupdate",{
		"sskgracelevel":sskgracelevel,
		"sskgracediscount":sskgracediscount,
		"sskgracetime":sskgracetime,
		"sskgraceintegra":sskgraceintegra,
		"sskheid":sskheid
	},function(date){
		
		//alert("更新成功，随后进行刷新");
		//alert("开始页面的刷新");
		$("#membergrapethebody").empty();
		$("#membergrapethebody").append(date);
	},"html");
	
	//进行页面的刷新
	<%-- $.post("<%=basePath%>shmembergracebath",{},function(date){
		
		alert("开始页面的刷新");
		$("#membergrapethebody").empty();
		$("#membergrapethebody").append(date);
		
	},"html");
	 --%>
	
	
	
}
</script>
<script type="text/javascript">
function shselectsecondchecks()
{
	//alert("开始选");
	if($("#shmemzdsjss").is(":checked"))
		{
		
		document.getElementById("shmemgracetabless").style.display="";
		//alert($("#shhiddenid").val());
		var shhsk=$("#shhiddenid").val();
		if(shhsk=="1")
			{
			//alert("1年");
			$("input[name='shmemgdss']").eq(1).attr("checked","checked")
			}
		else 
		{
			//alert("无期")
			$("input[name='shmemgdss']").eq(0).attr("checked","checked")
			
		}
		
		}
	else
		{
		
		document.getElementById("shmemgracetabless").style.display="none";
		
		}
	
}
</script>
<script type="text/javascript">
function shmemgraceedit(id)
{
	var shrelid=id;
	//alert("编辑");
	//alert(id);
	$.post("<%=basePath%>shmemeditgrace",{
		"shrelid":shrelid
		
	},function(date){
		
		//alert("绑定");
		//alert(date);
		$("#shthesecondbd").empty();
		$("#shthesecondbd").append(date);
		$("#shgraceeditclick").click();	
		//alert("孙浩"+$("input[name='shmemgdss'][type='radio']").value);
		//处理单选框的默认选中按钮
		//alert($("#shhiddenid").val());	
	},"html");
	//编辑之后进行页面的刷新
	
	
}
</script>
<script type="text/javascript">
function shmemsavep()
{
	//alert("保存");
	var shmemdjmc=$("#shmemdjmc").val();
	var shmemyhzk=$("#shmemyhzk").val();
	var shmemhyjf=0;
	var shmemyxq=0;
	if($("#shmemzdsj").is(":checked"))
		{
		//单选按钮
		shmemhyjf=$("#shmemhyjf").val();
		if($("input[name='shmemgd']").is(":checked"))
			{
			shmemyxq=$("input[name='shmemgd'][type='radio']:checked").val();
			//shmemyxq=document.getElementsByName("shmemgd").value;
			
			}
				
		}	
	//alert(shmemdjmc);
	//alert(shmemyhzk);
	//alert(shmemhyjf);
	//alert(shmemyxq);
	$.post("<%=basePath%>shaddmemgrade",{
		
		"shmemdjmc":shmemdjmc,
		"shmemyhzk":shmemyhzk,
		"shmemhyjf":shmemhyjf,
		"shmemyxq":shmemyxq
		
	},function(date){
		
		//alert("插入成功");
	},"html");
	
	//插入成功之后必须进行页面的刷新
	$.post("<%=basePath%>shmemgradeupdate",{},function(date)
			{
		$("#membergrapethebody").empty();
		$("#membergrapethebody").append(date);
		
		
	},"html");
}

function shselectchecks()
{
	if($("#shmemzdsj").is(":checked"))
	{
	//打开下方列表
	//alert("选中");
	document.getElementById("shmemgracetable").style.display="";
	}
	else 
	{
		
		document.getElementById("shmemgracetable").style.display="none";	
	}
	
}

</script>
<script type="text/javascript">
$(document).ready(function(){
	//alert("页面加载");
$.post("<%=basePath%>shmembergracebath",{},function(date){
	
	//alert("返回数据");
	//alert(date);
	$("#membergrapethebody").append(date);
	
},"html");
	
})

//新增会员等级
function addgrace()
{
	//alert("add");
	$("#shgraceclick").click();
	
}

</script>
<body>
	<div class="conditionNav">
		<div class="panel panel-default">
			<div class="panel-footer">
				<div class="row">
			<div class="col-xs-2">	
		<input class="btn btn-primary" onclick="addgrace();" type="button" value="新增等级">
				</div>
				</div>
		</div>
			</div>
	</div>
		<div>
		<!-- //这里是所列的表格 -->
		<table class="table table-bordered" name="numgettable">
			<thred>
			<tr>
				<td>序号</td>
				<td>操作</td>
				<td>等级名称</td>
				<td>优惠折扣</td>
				<td>自动提升规则</td>
			</tr>
			</thred>
			<tbody id="membergrapethebody">
			</tbody>
		</table>
	</div>
	
<!-- //进行新增等级模态框	 -->
<!-- 按钮触发模态框 -->
<button style="display: none" id="shgraceclick" class="btn btn-primary btn-lg" data-toggle="modal" 
   data-target="#membergracemodel">
   触发模态框
</button>

<!-- 模态框（Modal） -->
<div class="modal fade" id="membergracemodel" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               新增会员等级
            </h4>
         </div>
         <div class="modal-body">
           <!--  //****内容部分  -->
           <div class="panel panel-default">
						<div class="panel-body">
						<table>
						<tr>
						<td>
						*等级名称
						<input id="shmemdjmc" type="text"/>
						</td>
						</tr>
						<tr>
						<td>
						*优惠折扣
						<input id="shmemyhzk" type="text" />
						</td>
						<td>(例如8.5折填写85)</td>
						</tr>
						</table>
						</div>
			</div>
			
			<div class="panel panel-default">
						<div class="panel-body">
						<table>
						<tr>
						<td>
						自动升级
						<input id="shmemzdsj" onclick="shselectchecks();" type="checkbox" />
						</td>
						</tr>
						</table>
						</div>
			</div>
		
		<div id="shmemgracetable" style="display: none" class="panel panel-default">
						<div class="panel-body">
					<table>
					<tr>
					<td>
					1.当会员积分达到
					<input id="shmemhyjf" type="text" style="width: 30px">
					时，自动升级到该等级
					</td>
					</tr>
					
					<tr>
					<td>
					2.该等级的有效期
					<input value="50" type="radio" name="shmemgd" />
					永久
					<input value="1" type="radio" name="shmemgd" />
					一年
					</td>
					</tr>
					
					<tr>
					<td>
					3.自动升级，在积分达到要求后第二天生效
					</td>
					</tr>
					
					</table>
						</div>
		</div>
			
           
           
           
            <!-- //****内容部分 -->
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button onclick="shmemsavep();" type="button" class="btn btn-primary" data-dismiss="modal">
               保存
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<!-- //这里用于编辑模态框的绑定 -->
<div id="shthesecondbd"></div>


</body>
</html>