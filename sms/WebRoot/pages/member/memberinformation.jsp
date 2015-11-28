<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta  charset="utf-8">
	<title>会员资料</title>
    <base href="<%=basePath%>">  
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
		cursor:pointer;
	  }	
	a:link,a:visited{
 		text-decoration:none;  /*超链接无下划线*/
					}
	a:hover{
 		//text-decoration:underline;  /*鼠标放上去有下划线*/
			}
</style>
</head>
<script type="text/javascript">
//这里进行的是初始化页面加载时候的分页的处理（页面加载之后进行处理）
window.onload=function(){
	//alert("页面加载成功")
	$("#shaomempaging").children('li').children('a').click(function()
	{
		var which=$(this).text();
	  	//alert(which);//点击的上衣也下一页和尾页
		if(which=="首页")
	  	{
	  	    which="first";	
	  		
	  	}
	  	else if(which=="上一页")
	  	{
	  		
	  		which="prev";	
	  	}
	  	else if(which=="下一页")
	  	{
	  		which="next"
	  		
	  	}
	  	else if (which=="尾页") 
	  	{
	  		which="last"	
	  	}
	  	else{
	  		
	  	
	  	} 	
	  	
		var pageno=$("#page").val();//当前的页数
		
		//直接传递后台进行页面跳转（页面初始化的时候进行分页）
		$.post("<%=basePath%>shmeminforminitpaging",{
			"which":which,
			"pageno":pageno
		
		},function(date){
			
			//alert("分页成功")
			//alert(date);
			$("#shmemtable").empty();
			$("#shmemtable").append(date)
			//$("#shmemtable").empty();		
		},"html");
		
	});
}
</script>
<script type="text/javascript">
function shimportdate(){
	//alert("进入导入数据页面")
	window.open("pages/member/upload.jsp");
}
</script>
<script type="text/javascript">
function shexport()
{ 
	
	//首先要先进行写入excel的操作
	<%-- $.post("<%=basePath%>shwriteintoexcel",
			{
		
			},function(date){
				//alert("生成表格成功");
				//response.sendRedirect("f:\\会员信息.xls");
				//window.location.href="f:\\会员信息.xls"; 
			},"html"); --%>
	//上一步操作成功，成功写入
	
	
	
	//批量进行导出的功能
	//alert("开始打开表格")
	window.open("pages/member/shexport.jsp")
	//alert("打开页面")
	$("#shguanbianniu").click();
	
}

</script>
<script type="text/javascript">
//进行批量导入的操作
function sqclick2()
{
	//alert("实现导入导出功能");
	$("#shimportmodel").click();
} 

function sqclick3(){
	
	//alert("实现导出功能");
	$("#shexportbuttonx").click();
}

</script>
<script type="text/javascript">
$(function(){
	
	//alert("初始化");
	//这里是加载页面的会员数和余额信息
	$.post("<%=basePath%>shlastlist",{},
			function(data){//这里是不让它进行前台的返回
			}		
			,"html");
	
	//
	
		
})

</script>

<script type="text/javascript">
//激发模态框的信息。
function sqclick1()
	{	
	//alert("点击事件");
	$("#shxzhy").click();
	}	
	
</script>
<script type="text/javascript">
//初始化加载页面的表格，页面初始化的时候加载。
$(document).ready(function(){
	//初始化页面加载函数。
	//var shmdm=$("#shmdm").val();
	//var shdjm=$("#shdjm").val();
	//var shqq=$("#shqq").val();
	//提交到后台进行页面处理
	//alert("lalala")
	$.post("<%=basePath%>shmeminit",{},
	function(data){
		//alert("页面初始化成功");
		//alert(data);
		$("#shmemtable").append(data);
		//
		var sklmum1=$("#shmemshouinformtable1").val();
		var sklmum2=$("#shmemshouinformtable2").val();
		var sklmum3=$("#shmemshouinformtable3").val();	
		//alert("页面加载初始化");
		//alert(sklmum1);
		//alert(sklmum2);
		//alert(sklmum3);
		//在这里绑定具体的列表
		$("#sjmountd1").val(sklmum1);
		$("#sjmountd2").val(sklmum2);
		$("#sjmountd3").val(sklmum3);
		
		
	}		
	,"html");
	
	//
	
	
	
});

//页面查询的按钮。
function shmsub() {
	//alert("点击提交");//在后台进行sql的值传递进行判断
	var mshopname=$("#shmdm").val(); 
	var mshopdj=$("#shdjm").val();
	var mshopqy=$("#shqq").val();
	var mshoptext=$("#shminput").val();
	//alert("查询")
	//提交查询。
	$.post("<%=basePath%>shmtijiao",{
		"mshopname":mshopname,
		"mshopdj":mshopdj,
		"mshopqy":mshopqy,
		"mshoptext":mshoptext
	},function(data){
		
		$("#shmemtable").empty();
		$("#shmemtable").append(data);
		//alert("查询结果返回成功");		
		//对下列的文本框进行更新
		var sklmum1=$("#shmemshouinformtable1").val();
		var sklmum2=$("#shmemshouinformtable2").val();
		var sklmum3=$("#shmemshouinformtable3").val();	
		//alert("页面加载初始化");
		//alert("会员数");
		//alert(sklmum1);
		//alert(sklmum2);
		//lert(sklmum3);
		//在这里绑定具体的列表
		$("#sjmountd1").val(sklmum1);
		$("#sjmountd2").val(sklmum2);
		$("#sjmountd3").val(sklmum3);
		
	},"html");
	
	
}
</script>


<body>
	<div class="conditionNav">	
		<div class="panel panel-default">
			<div class="panel-footer">
				<div class="row">
			<div style="clear: both;">
			
		<div class="col-xs-2"><a style="width: 100px" href="#" onclick="sqclick1();" id="shlxzhy">新增会员</a></div>
		<div class="col-xs-2"><a href="#" onclick="sqclick2();" id="shlhydr">批量导入</a></div>
		<div class="col-xs-2"><a href="#" onclick="sqclick3();" id="shlpldc">批量导出</a></div>	
		</div>
		<!-- //嵌套div想让它对齐的作用 -->
		<div class="conditionNav" style="float: left;padding-top: 20px">
		<!-- //门店下拉 -->
		<div class="col-xs-3">
		<select style="width: 150px" id="shmdm" class="form-control">
		
			<option value="-1" >--全部开卡门店</option>
			<%
				List<Object[]> snw1=null;
				snw1=(List<Object[]>)request.getAttribute("shlist1");
				if(snw1!=null&&snw1.size()!=0)
				{
					for(Object[] kk:snw1)
					{
						
			%>
			<option value="<%=kk[0]%>"><%=kk[0]%></option>
			<% 
	}
					
				}
			%>
		</select>
		</div>
		
		<div class="col-xs-3">
		<!-- //会员等级 -->
		<select id="shdjm" class="form-control">
		<option value="-1" >--全部等级--</option>
		<%
		List<Object[]> snw2=null;
		snw2=(List<Object[]>)request.getAttribute("shlist2");
		if(snw2!=null&&snw2.size()!=0)
		{
			for(Object[] mm:snw2)
			{
		
		
		%>
		<option value="<%=mm[0]%>"><%=mm[0]%></option>
		<%
			}
		}
		
		%>
		
		</select>
		</div>
		
		<div class="col-xs-2">
		<!-- //会员卡启用状态 -->
		<select id="shqq" class="form-control">
		<option value="1" selected="selected">启用</option>
		<option value="0">禁用</option>
		</select>
		</div>
		<div class="col-xs-3">
		<input class="search-query form-control col-md-10" id="shminput" type="text" placeholder="卡号/姓名/电话" />
		</div>
	<!-- 	//查询按钮 -->
	<div class="col-xs-1">
		<input class="btn btn-primary" onclick="shmsub();" type="submit" value="查询">
		</div>
		</div>
			</div>
				</div>	
	</div>
	</div>
		
	
	<!-- //展示表格的div -->
	<div class="panel panel-default" style="width: 100%;height: 500px;padding-top: 1px">
	     
		<div id="shmemtable">	
		</div>
	
	</div>
	
	<!-- //下面的显示会员数和总余额 -->
	<div  class="pageList" style="display: block;height: 50%;padding-top: 10px">
	<div class="panel panel-default">
		<div class="panel-footer">

<%
	List<Object[]> shklist=(List<Object[]>)request.getAttribute("nlistq");
	int sn=0;
	int sye=0;
	int sjf=0;
	if(shklist!=null&&shklist.size()!=0)
	{
		sn=shklist.size();
		for(Object[] obj:shklist)
		{
			sye +=Integer.parseInt(obj[5].toString());
			sjf+=Integer.parseInt(obj[6].toString());
			
			System.out.print(sn);
		}
	}
			
%>
	<span>会员数：</span>
	<span><input type="text" id="sjmountd1" style=" background: transparent;border: 0"  /></span>
	<span>总余额：</span>
	<span><input type="text" id="sjmountd2" style=" background: transparent;border: 0" /></span>
	<span>总积分：</span>
	<span><input type="text" id="sjmountd3" style=" background: transparent;border: 0" /></span>

</div>
</div>
	</div>
	
	<!-- //模态框的部分 -->
	<!-- 1.新增会员的模态框 -->
	<button id="shxzhy" style="display: none;" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal"></button>
	
	<div  class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">  
  <div class="modal-dialog">  
    <div class="modal-content">  
      <div class="modal-header">  
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>  
        <h4 class="modal-title">新增会员</h4>  
      </div>  
      <div class="modal-body" style="overflow-y: scroll;height: 350px" >  
           		<div class="panel panel-default">
					<div class="panel-body">
					<table >
					<tr>
					<td>是否启用:</td><td></td><td></td>
					<td><input type="checkbox" id="shcheckbox1" /></td>
					</tr>

					</table>
					</div>
				</div>
				<!-- ***** -->
				<div class="panel panel-default">
					<div class="panel-body">
					<table >
					<tr>
					<td>*会员编号：<input id="shhybh" type="text"  /></td>
					</tr>
					<tr>
					<td>*会员姓名：<input id="shhyxm" type="text" /></td>
					</tr>
					<tr>
					<td>
					*会员等级：<select id="sshhydj"><option value="-1">--会员等级--</option>
					<%
					if(snw2!=null&&snw2.size()!=0)
					{
						for(Object[] mm:snw2)
						{
					
					%>
					<option value="<%=mm[0] %>"><%=mm[0] %></option>
					<%
						}
					}
					
					%>
					</select>
					会员折扣：<input id="shhyzk" type="text" />%
					</td>
					</tr>
					<tr>
					<td>
					会员余额：<input id="shhyye" value="0" style="width: 50px" type="text" />元  会员积分：<input id="shhyjf" value="0" style="width: 50px" type="text" />分
					</td>
					</tr>
					
					<tr>
					<td>*联系电话：<input id="shlxdh" type="text" /></td>
					</tr>
					</table>
					</div>
				</div>
				<!-- ********* -->
				<div class="panel panel-default">
					<div class="panel-body">
					<table>
					<tr>
					<td>会员密码：<input id="shhymm" type="password" /></td>
					</tr>
					<tr>
					<td>
					会员生日：
					<!-- //日历插件 -->
					<input id="shhysr" size="16" type="text" readonly="readonly" value=""  class="form_datetime">
					<!-- //生日日期结束 -->
					</td>
					</tr>
					<tr>
					<td>开卡日期：
					
					<!-- //导入当前的年月日 -->
					<!-- //日历插件 -->
					<input id="shkaika" size="16" type="text" readonly="readonly" value=""  class="form_datetime">
					<!-- //生日日期结束 -->
					
					</td>
					</tr>
					
					<tr>
					<td>到期时间：
					<!-- //导入日期的时间; -->
					<!-- //日历插件 -->
					<input id="shdaoqi" size="16" type="text" readonly="readonly" value=""  class="form_datetime">
					<!-- //生日日期结束 -->
					</td>
					</tr>
					<tr>
					<td>允许赊账: <input id="shyxsz" type="checkbox"></td>
					</tr>
					</table>
					
					</div>
				</div>
				
				<!-- //*********** -->
					<div class="panel panel-default">
						<div class="panel-body">
							<table>
							<tr>
							<td>
							QQ号码：<input id="shqqhao" type="text">
							</td>
							</tr>
							<tr>
							<td>
							邮箱地址：<input id="shyouxiang" type="text">
							</td>
							</tr>
							<tr>
							<td>
							地址：<textarea id="shdizhi" name="textfield"></textarea>
							</td>
							</tr>
							<tr>
							<td>
							备注：<textarea id="shbeizhu" name="textfield"></textarea>
							</td>
							</tr>
							
							</table>
						</div>
					</div>
				
				
      </div>  <!-- //主体部分的结束-->
      <div class="modal-footer">  
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>  
        <button onclick="shaddmem();" type="button" class="btn btn-primary" data-dismiss="modal">保存</button>  
      </div>  
    </div><!-- /.modal-content -->  
  </div><!-- /.modal-dialog -->  
</div><!-- /.modal -->  
	   
	<!-- 2.批量导入的模态框 -->
	<button id="shimportmodel" style="display: none" class="btn btn-primary btn-lg" data-toggle="modal" 
   data-target="#myshhModal">
   开始演示模态框
</button>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myshhModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               批量导入数据
            </h4>
         </div>
         <div class="modal-body">
             <hr>  
    <p style="color:red;">  
        请先下载任务信息的<a href="/xlsmodel/会员信息.xls">Excel模版</a>，填写完毕之后再导入！  
    </p>  
            
          
            
         </div>
         <div class="modal-footer">
         <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button onclick="shimportdate();" type="button" class="btn btn-primary" data-dismiss="modal">
             开始导入
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>	
	<!-- 3.批量导出的模态框 -->
	<!-- //********************** -->
	<button style="display: none" id="shexportbuttonx" class="btn btn-primary btn-lg" data-toggle="modal" 
   data-target="#shglxmyModal">
   开始演示模态框
</button>

<!-- 模态框（Modal） -->
<div class="modal fade" id="shglxmyModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               批量导出数据
            </h4>
         </div>
         <div class="modal-body">
            <div class="panel panel-default">
					<div class="panel-body">
					<table >
					<tr>
					<td>
					<!-- <input type="submit" /> -->
					<a  onclick="shexport();" >导出成excel表格</a>
					</td>
					</tr>
					</table>
					</div>
				</div>
            
            
         </div>
         <div class="modal-footer">
        <button id="shguanbianniu" style="display: none" type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
         <!--    <button type="button" class="btn btn-primary">
               提交更改
            </button> -->
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<!-- 	//************************** -->
<div id="ldfucl"></div>	
<script type="text/javascript">
//编辑的模态框的触发
function shbianji(id){
	//alert("编辑");
	var shvid=id;
	//alert(shvid);
	//开始进行新的页面的查询并绑定列表框
	$.post("<%=basePath%>shmodelcllick",{
		"shvid":shvid,
		
	},function(date){
		//alert(date)
		//先进行绑定
		$("#ldfucl").append(date);
		$("#shyhbj").click();
		
	},"html");
	

}

</script>
<script type="text/javascript">
$(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii',autoclose: true,todayBtn: true,  minuteStep: 10});
</script>
<script type="text/javascript">
//进行确定点击合法性的判断。
function shaddmem()
{
	//alert("新增会员的保存");
	//获取参数。
	var sh_v_level;//会员卡启用状态
	//var v_card_no;//会员卡号
	var v_card_no;//会员卡卡号也就是会员编号
	var vip_name;//会员姓名
	var sshhydj;//会员等级
	var shhyzk;//会员折扣
	var shhyye;
	var shhyjf;//会员积分
	var shlxdh;//会员电话
	var shhymm;//会员密码
	var shhysr;//会员生日
	var shkaika;//开卡日期
	var shdaoqi;//到期时间
	var shyxsz;//是否允许赊账
	var shqqhao;//会员QQ号码
	var shyouxiang;//邮箱地址
	var shdizhi;
	var shbeizhu;//地址和备注
	if($("#shcheckbox1").is(":checked"))
		{
		//选中
		sh_v_level=1;
		}
	else{
		sh_v_level=0;
	}
	v_card_no=$("#shhybh").val();
	vip_name=$("#shhyxm").val();
	sshhydj=$("#sshhydj").val();
	shhyzk=$("#shhyzk").val();
	shhyye=$("#shhyye").val();
	shhyjf=$("#shhyjf").val();
	shlxdh=$("#shlxdh").val();
	shhymm=$("#shhymm").val();
	shhysr=$("#shhysr").val();
	shkaika=$("#shkaika").val();
	shdaoqi=$("#shdaoqi").val();
	
	if($("#shyxsz").is(":checked"))
		{
		shyxsz=1;
		}
	else {
		shyxsz=0;
	}
	shqqhao=$("#shqqhao").val();
	shyouxiang=$("#shyouxiang").val();
	shdizhi=$("#shdizhi").val();
	shbeizhu=$("#shbeizhu").val();
	//alert(shbeizhu);
	//alert(shdizhi);
	
	//进行判断
	if(v_card_no=="")
		{
		alert("请填写会员编号");
		}
	else if (vip_name=="") {
		alert("填写会员姓名");	
	}
	else if (sshhydj=="-1") {
		alert("请选择会员等级");
	}
	else if (shlxdh=="") {
		alert("请填写会员电话");
	}
	else
		{
		
		//进行页面的提交进行添加操作
		$.post("<%=basePath%>mshaddmemer",{"sh_v_level":sh_v_level,"v_card_no":v_card_no,"vip_name":vip_name,"sshhydj":sshhydj,
			"shhyzk":shhyzk,"shhyye":shhyye,"shhyjf":shhyjf,"shlxdh":shlxdh,"shhymm":shhymm,
			"shhysr":shhysr,"shkaika":shkaika,"shdaoqi":shdaoqi,"shyxsz":shyxsz,"shqqhao":shqqhao,
			"shyouxiang":shyouxiang,"shdizhi":shdizhi,"shbeizhu":shbeizhu},
				function(data)
				{
			//alert("页面添加成功");
			//alert(data)
			$("#shmemtable").empty();
			$("#shmemtable").append(data);
			//开始进行各种页面的更新操作
			var sklmum1=$("#shmemshouinformtable1").val();
			var sklmum2=$("#shmemshouinformtable2").val();
			var sklmum3=$("#shmemshouinformtable3").val();	
			//alert("页面加载初始化");
			//alert(sklmum1);
			//alert(sklmum2);
			//alert(sklmum3);
			//在这里绑定具体的列表
			$("#sjmountd1").val(sklmum1);
			$("#sjmountd2").val(sklmum2);
			$("#sjmountd3").val(sklmum3);
			
				},"html");
		//提交完成后对页面进行刷新
		//这里进行的是刷新操作。
	<%-- 	$.post("<%=basePath%>shmeminit",{},
				function(data){
					alert("页面初始化成功");
					alert(data);
					$("#shmemtable").empty();
					$("#shmemtable").append(data);
				}		
				,"html");
			
		} --%>//else的结束
	
}
		
	
}

</script>
<script type="text/javascript">
function updatememupdateinfo(id){
	//alert(id);
	//alert("修改操作")
	//参数操作
	var sskkid=id;
	var sh_zhuangtai;//1.启用状态
	if($("#shcheckbox1f").is(":checked"))
		{
		sh_zhuangtai=1;
		}
	else{
		sh_zhuangtai=0;
	}
	var sh_bianhao=$("#shhybhf").val();
	var sh_xingming=$("#shhyxmf").val();
	var sh_dengji=$("#sshhydjf").val();
	var sh_zhekou=$("#shhyzkf").val();
	var sh_yue=$("#shhyyef").val();
	var sh_jifen=$("#shhyjff").val();
	var sh_tel=$("#shlxdhf").val();
	var sh_mima=$("#shhymmffs").val()
	
	var sh_shengri=$("#shhysrf").val();
	var sh_kaikariqi =$("#shkaikaf").val();
	var sh_daoqishijian =$("#shdaoqif").val()
	
	var sh_qq=$("#shqqhaof").val();
	var sh_youxiang=$("#shyouxiangf").val();
	var sh_dizhi=$("#shdizhif").val();	
	var sh_beizhu=$("#shbeizhuf").val();
	//测试数据获取的对不对
	//alert(sh_xingming)
	$.post("<%=basePath%>shhuiyuanbianjichuli",
	{
		
		"sh_zhuangtai":sh_zhuangtai,
		"sh_bianhao":sh_bianhao,
		"sh_xingming":sh_xingming,
		"sh_dengji":sh_dengji,
		"sh_zhekou":sh_zhekou,
		"sh_yue":sh_yue,
		"sh_jifen":sh_jifen,
		"sh_tel":sh_tel,
		"sh_mima":sh_mima,
		"sh_shengri":sh_shengri,
		"sh_kaikariqi":sh_kaikariqi,
		"sh_daoqishijian":sh_daoqishijian,
		"sh_qq":sh_qq,
		"sh_youxiang":sh_youxiang,
		"sh_dizhi":sh_dizhi,
		"sh_beizhu":sh_beizhu,
		"sskkid":sskkid
	},function(date){
		
		//alert("会员修改成功")
		
		
	},"html");
	
	//进行操作的更新的操作。。。
	
	$.post("<%=basePath%>shnewmeminit",{},function(date){
		
		//*****
		//alert(date)
		$("#shmemtable").empty();
		$("#shmemtable").append(date);
		//
		var sklmum1=$("#shmemshouinformtable1").val();
		var sklmum2=$("#shmemshouinformtable2").val();
		var sklmum3=$("#shmemshouinformtable3").val();	
		//alert("页面加载初始化");
		//alert(sklmum1);
		//alert(sklmum2);
		//alert(sklmum3);
		//在这里绑定具体的列表
		$("#sjmountd1").val(sklmum1);
		$("#sjmountd2").val(sklmum2);
		$("#sjmountd3").val(sklmum3);
		
		
	},"html");
	
	
	//每次编辑完成之后要进行页面的会员数，余额，积分和分页标签的绑定
	
	
}
</script>
</body>
</html>