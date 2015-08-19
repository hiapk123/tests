<%@ page language="java" import="java.util.*,org.uestc.util.PageObject"
	pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<base href="<%=basePath%>">

<title>打折与特价</title>
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
<script src="<%=basePath%>js/bootstrap-datetimepicker.zh-CN.js"charset="utf-8"></script>
<link href="<%=basePath%>css/bootstrap-datetimepicker.css"rel="stylesheet">	
 

<%-- <link href="<%=basePath%>js/datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">

<script type="text/javascript" src="<%=basePath%>js/datetimepicker/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=basePath%>js/datetimepicker/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
 --%>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="营销,活动">
<meta http-equiv="description" content="打折与特价">

<script type="text/javascript" >

 <%-- $(function(){
	 	$.post('<%=basePath%>CategoryGoods',{"type":"initStore"},function(data){
			var list=$('#mbt_dropdown-menu');
			list.empty();
			
			for(var i=0;i<data.length;i++){
				list.append("<li><a  onclick=\"getCate("+ data[i].sid+",'"+data[i].sname+"');\" class= \"mbt_a\" data-value="+ data[i].sid +" ><i class=\"whitespace\"></i>"+ data[i].sname+"</a></li>");
			}
	
		},"json"); 
	});  --%>
	var mainPanel ;
	$(function(){
		backToList();	
	}); 
	function backToList(){
		 mainPanel = $('#mbt_dzytj_panel');
			mainPanel.empty();
			mainPanel.append('<%=request.getAttribute("init_html") %>');
			$("#mbt_dzytj_head").empty();
			$("#mbt_dzytj_head").html("<a style=\"float: right;\" href=\"javascript:void(0)\" onclick='addactive();' class=\"btn btn-primary\">添加</a>");
			$("#mbt_zjytj_content_head").addClass("content_head");
	}
	function addactive(){		
		mainPanel.empty();
	 	$.post('<%=basePath%>DzytjServlet',{"type":"dzytj","subType":"init_add_active"},function(str){
			mainPanel.html(str);
			$("#mbt_dzytj_head").empty();
			$("#mbt_dzytj_head").html("<a style=\"float: right;\" href=\"javascript:void(0)\" onclick='backToList();' class=\"btn btn-primary\">返回列表</a>");
			$("#mbt_zjytj_content_head").removeClass("content_head");
	 	} )		
		}
	//弹出层使用：设置a 标签 link-rel 为待弹出div ID 
    function dbClick() {
    	// isdb=true;	    	 
    	 //var mbt_id = $(this).attr("data-value");
    	// var mbt_name = $(this).attr("name");
    	// var cla = $(this).attr("isFinal");
    	// alert(cla);
    	// $('#mbt_hidid').val(mbt_id);
    	// $('#mbt_hidname').val(mbt_name);
    	// $('#mbt_delflag').val(cla);
    	 //alert(mbt_name);
    	 //alert(mbt_id);
    	// $(".mbt_hid").hide();
    	 var cl = $(this).attr("link-rel");
    	 $("#"+cl).modal('show');
    	};	
	function addgoods(){
		$(".ajax-link").bind('dblclick',dbClick);
		$("#mbt_a_addgoods").trigger("dblclick");
	}

	
</script>

<style>
	.trBack{
			background-color:silver;
			
	}
	.trBack th{
		text-align: center;
	}
	.tbd tr td{
		text-align: center;
	}
	.content_head{
		display: none;
	}
</style>
</head>

<body>
<a id="mbt_a_addgoods" style="display: none" link-rel="myModal" data-value="-1" isfinal="1"  name="根目录" class="ajax-link">添加根目录</a>
	<%	String pageType=(String)request.getAttribute("pageType");%>
	<div class="panel panel-default">
		<div class="panel-body">
			<div style="float: left; width: 15%;">
				<h4>打折与特价</h4>
			</div>
			<div id="mbt_dzytj_head" style="float: right; width: 85%;">			
				
			</div>
		</div>
	</div>
	<!-- div start -->
 <div class="panel panel-default">
 			<div id="mbt_zjytj_content_head" class="panel-heading">
 						 活动名称：<input id="mbt_dzytj_active_name" type="text" />开始时间:<input type="text" value="开始时间"/>
			 			 结束时间：<input type="text" value="结束时间"  /> 可用于： 
			 			 <label class="checkbox-inline">
			      		<input type="radio" name="optionsRadiosinline" id="optionsRadios1" 
			         	value="shitidian" checked> 实体店
			   		</label>
			  			 <label class="checkbox-inline">
			      			<input type="radio" name="optionsRadiosinline" id="optionsRadios2" 
			         		value="wangdian"> 网店
			   		</label>
			   		 <label class="checkbox-inline">
			      			<input type="radio" name="optionsRadiosinline" id="optionsRadios3" 
			         		value="huiyuanzhuanxiang"> 会员专享
			   		</label>
			</div> 
			<div class="panel-body">
				<div id="mbt_dzytj_panel" class="col-sm-12 col-lg-12">
				
			   </div>
			</div> 
			
</div>
								<div class="modal fade" id="myModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					
									<div class="modal-dialog">
								   
										<div class="modal-content">
											<div class="modal-header">
												<!-- <button type="button" class="close" data-dismiss="modal">×</button> -->
												<h3 style="text-align: center;padding-top: 0;padding-bottom: 10px">搜索商品并添加</h3>
												搜索关键字：<input type="text" value="商品名/条形码" />
												<select><option>全部分类</option></select>
												<button class="button" style="margin-left: 20px" type="button" value="搜索" >搜索</button>
											</div>
										
											<div class="panel-body " >
											<table class="col-sm-12 col-lg-12 table">
												<thead style="background-color: silver;">
													<tr>
														<th><input type="checkbox" /></th>
														<th>商品名称</th>
														<th>分类</th>
														<th>原价</th>
														<th>进货价</th>
													</tr>
												</thead>
												<tbody >
												
												</tbody>
											</table>
											</div>
											<div class="modal-footer">
												<a href="#" class="btn btn-default"  data-dismiss="modal">取消</a>
												<a href="#" class="btn btn-primary"  data-dismiss="modal">保存</a>
											</div>
										</div>
									</div>
								</div>
		
</body>
</html>

 <%-- 	<input type="hidden" id="page" value="20"/>
							<ul class="pagination">
								<page:htmlPage pageNo="20" url="index.jsp" totalSum="980" showPage="10" pageSize="10"/>
							</ul> --%>
 
 
 		<!-- <table class="col-sm-12 col-lg-12 table">
						<thead >
							<tr class="trBack">
								<th>活动名称</th>
								<th>开始时间</th>
								<th>结束时间</th>
								<th>适用于</th>
								<th>捆绑优惠券</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody class="tbd"> 
						<tr>
						<td>开业大放送</td>
						<td>2015.07.11</td>
						<td>2015.07.21</td>
						<td>实体店 网店</td>
						<td><a href="javascript:void(0)">立即捆绑</a></td>
						<td><a href="javascript:void(0)">详细</a> | <a href="javascript:void(0)">修改</a> | <a href="javascript:void(0)">删除</a></td>
						</tr>
						
							
						</tbody>
					</table>
						 -->
						 
			<!--  
			
 			
 			 活动名称：<input id="mbt_dzytj_active_name" type="text" />开始时间:<input type="text" value="开始时间"/>
 			 结束时间：<input type="text" value="结束时间"  /> 可用于： 
 			 <label class="checkbox-inline">
      		<input type="radio" name="optionsRadiosinline" id="optionsRadios1" 
         	value="shitidian" checked> 实体店
   		</label>
  			 <label class="checkbox-inline">
      			<input type="radio" name="optionsRadiosinline" id="optionsRadios2" 
         		value="wangdian"> 网店
   		</label>
   		 <label class="checkbox-inline">
      			<input type="radio" name="optionsRadiosinline" id="optionsRadios3" 
         		value="huiyuanzhuanxiang"> 会员专享
   		</label>
										   
	
			<table class="col-sm-12 col-lg-12 table">
						<thead >
							<tr class="trBack">
								<th>商品名</th>
								<th>分类</th>
								<th>原价</th>
								<th>折扣</th>
								<th>删除</th>
								
							</tr>
						</thead>
						<tbody class="tbd"> 
					 	<tr>
							<td>伊犁牛奶</td>
							<td>定价</td>
							<td>5.5</td>
							<td><input type="text" value=100 /></td>
							<td><a href="javascript:void(0)">删除</a></td>
						</tr> 
						<tr ><td style="text-align: left;"><a href="javascript:void(0)">点此打开商品搜索</a></td></tr>
						
							
						</tbody>
					</table> -->