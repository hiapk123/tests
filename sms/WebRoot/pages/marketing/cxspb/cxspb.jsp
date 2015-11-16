<%@ page language="java" import="java.util.*,org.uestc.util.PageObject,com.uestc.bean.*"
	
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

<title>套餐促销</title>
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

<script src="<%=basePath%>bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

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


<link rel="stylesheet" href="<%=basePath%>css/jPages.css">
<script src="<%=basePath%>js/jPages.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/animate.css">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="营销,活动">
<meta http-equiv="description" content="套餐促销">
<%
	String pcount=(String)request.getAttribute("pageCount"); 
	
%>

<script>
var pageIndex = 1;     //页面索引初始值   
var pageSize = 8;     //每页显示条数初始化，修改显示条数，修改这里即可 
var Count = '<%=pcount%>'
var pageCount =Math.floor(Count/pageSize +1);

$(document).ready(function() {
	//InitTable(0);
     $("div.holder").jPages({
      containerID : "mytbd",
      previous : "上一页",
      next : "下一页",
      perPage : pageSize,
      pageCount:pageCount,
      animation: 'fadein',
      startPage:pageIndex,
      delay : 200,
      callback:PageCallback,
     
    });
  //翻页调用   
    function PageCallback(page, item) { 
        InitTable(page.current);  
      // alert(page.count)//=2;	
    }  
    //请求数据   	
    function InitTable(pageIndex) {   
    	$.post('<%=basePath%>CxspbServlet',{
    		"subType":"table",
    		"pageSize":pageSize,
    		"pageIndex":pageIndex
    	},function(mydata){
    		//alert(data)
    		//var mydata = eval('('+data+')');
    	//	alert(mydata[0].name)
    		var str = "";
    		$.each(mydata,function(index,i){
    			 str +="<tr><td>"+i.name+"</td>";
    			 str +="<td>"+i.barcode+"</td>";
    			 str +="<td>"+i.activeName+"</td>";
    			 str +="<td>"+i.activeType+"</td>";
    		    str +="<td>"+i.startTime+"</td>";
    		    str +="<td>"+i.endTime+"</td>";
    		    str +="<td>"+i.price+"</td>";
    		    str +="<td>"+i.discPirce+"</td>";
    		    
    		    var type = i.type;
    	    	 var tt = "";
    	    	if(type=="1"||type=="3"||type=="5"||type=="7"){
    	    		tt +="实体店 ";
    	    	}
    	    	if(type=="2"||type=="3"||type=="6"||type=="7"){
    	    		tt +="网店 ";
    	    	}
    	    	if(type=="4"||type=="5"||type=="6"||type=="7"){
    	    		tt +="会员专享";
    	    	}
    	    	str +="<td>"+tt+"</td>";
    	    	
    	    	str += "</tr>";
    		});
    		//alert(str);
    		$("#mytbd").empty();
    		$("#mytbd").html(str);		
    	},
    	"json");
    }
   // InterlacesColor(); //隔行换色(这个不是分页里面的js方法)
}); 
//详细与更新处理
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
	.mytable {
		max-height: 200px;
		overflow: auto;
	}
	.dzytj_left{
		float: left;
		
	}
	.tdbody{
	}
		
</style>
</head>

<body>
<div id ="mtccx">
<div class="panel panel-default">
		<div class="panel-body">
			<div style="float: left; width: 15%;">
				<h4>促销商品表</h4>
				
			</div>
				
			<div id="mbt_dzytj_head" style="float: right; width: 85%;">		
			
			</div>
		</div>
	</div>
	<!-- div start -->
 <div class="panel panel-default">
 			<div id="mbt_zjytj_content_head" class="panel-heading">
 						
			</div> 
			<div class="panel-body">
				<div id="mbt_dzytj_panel" class="col-sm-12 col-lg-12">
				  		<table class= "col-sm-12 col-lg-12 table tbd" >
				  			<thead class="trBack ">
				  			<tr>
				  			<th >商品名称</th>
				  			<th>条码</th>
				  			<th >活动名称</th>
				  			<th >类型活动</th>
				  			<th >促销开始时间</th>
				  			<th >促销结束时间</th>
				  			<th >原价</th>
				  			<th>促销价</th>
				  			<th>适用于</th>
				  			</tr>
				  			</thead>
				  			<tbody id="mytbd">

				  			</tbody>
				  		</table>
				  		  <div class="holder text-center"></div>
				  		
			   </div>
			   
			</div> 
</div>
</div>         
</body>
</html>
 