<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <SCRIPT LANGUAGE="javascript">
<!--
function ShowNewProductDiv()
{
window.open ('pages/goods/addproduct.jsp','newwindow','height=500,width=800,top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
}
function DaoRu()
{
window.open ('pages/goods/daoru.jsp','newwindow','height=500,width=800,top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
}
-->
</SCRIPT>
<SCRIPT LANGUAGE="javascript">
<!--

function DaoRu()
{
window.open ('pages/goods/daoru.jsp','newwindow','height=600,width=800,top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
}
-->
</SCRIPT>
<SCRIPT LANGUAGE="javascript">
<!--

function daochu()
{
window.open ('pages/goods/daochu.jsp','newwindow','height=500,width=800,top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
}
-->
</SCRIPT>
<SCRIPT LANGUAGE="javascript">
<!--

function fuzhishangpin()
{
window.open ('pages/goods/fuzhishangpin.jsp','newwindow','height=500,width=800,top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
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

  </head>
  
   <body   >
 
 <span class="label label-default" style="padding:10px">商品资料</span>  &nbsp;&nbsp;
<button type="button" class="btn btn-success" name="submit" onclick="ShowNewProductDiv()">新增商品</button>    &nbsp;&nbsp;
<button type="button" class="btn btn-success" name="submit" onclick="DaoRu()">导入</button>       &nbsp;&nbsp;
<button type="button" class="btn btn-success" name="submit" onclick="daochu()">导出</button>      &nbsp;&nbsp;
<button type="button" class="btn btn-success" name="submit" onclick="fuzhishangpin()">复制商品</button>  &nbsp;&nbsp;
 
<a href="pages/goods/kuaisuluru.jsp"><input type="button" class="btn btn-success" value="快速录入"></input></a> &nbsp;&nbsp;
    <div class="btn-group">
   <button type="button" class="btn btn-default dropdown-toggle" 
      data-toggle="dropdown">
      小六子食品店 <span class="caret"></span>
   </button>
   <ul class="dropdown-menu" role="menu">
      <li><a href="#">悠食客1店</a></li>
      <li><a href="#">悠食客2店</a></li>
   </ul>
</div>
&nbsp;&nbsp;&nbsp;
<div class="btn-group">
   <button type="button" class="btn btn-primary dropdown-toggle" 
      data-toggle="dropdown">
      启用 <span class="caret"></span>
   </button>
   <ul class="dropdown-menu" role="menu">
      <li><a href="#">禁用</a></li>
      
   </ul>
</div>



 


<div style = "float:right;">
<input class="input-medium search-query" type="text" float:right/> <button type="submit" class="btn">查找</button>
<button type="button" class="btn btn-success" float:right>按分类</button>	
</div>
  
  <div data-spy="scroll" style="width:1600px;  overflow:auto; position: relative;" data-offset="10" 
   >
   <div data-spy="scroll" style="height:350px;  overflow:auto; position: relative;" data-offset="10" 
   > 
  


<table class="table table-striped table-bordered bootstrap-datatable datatable responsive">
    <thead>
    <tr>
          <th >操作</th>
         <th >商品名称</th>
         <th>所属门店</th>
         <th>库存量</th>
         <th>进货价</th>
         <th>销售价</th>
         <th>批发价</th>
         <th>会员价</th>
         <th>分类</th>
         <th>条码</th>
         <th>会员折扣</th>
         <th>库存上限</th>
         <th>库存下限</th>
         <th>生产日期</th>
         <th>保质期 ↑ ↓</th>
         <th>拼音码</th>
         <th>供货商</th>
         <th>自定义1</th>
         <th>自定义2</th>
         <th>自定义3</th>
         <th>自定义4</th>
         <th>最小起订量</th>
         <th>最低陈列量</th>
         <th>畅销量</th>
         <th>正常销售量</th>
         <th>库存合理值</th>
         <th>是否锁定</th>
    </tr>
    </thead>
    <tbody>
    <tr>
     <td class="center">
            <a href="#">
               
                View
            </a>
            <a href="#">
               
                Edit
            </a>
            <a  href="#">
                
                Delete
            </a>
        </td>
        <td>David R</td>
        <td class="center">2012/01/01</td>
        <td class="center">Member</td>
        <td class="center">
            <span class="label-success label label-default">Active</span>
        </td>
       
         <td>David R</td>
        <td class="center">2012/01/01</td>
        <td class="center">Member</td>
         <td>David R</td>
        <td class="center">2012/01/01</td>
        <td class="center">Member</td>
         <td>David R</td>
        <td class="center">2012/01/01</td>
        <td class="center">Member</td>
         <td>David R</td>
        <td class="center">2012/01/01</td>
        <td class="center">Member</td>
         <td>David R</td>
        <td class="center">2012/01/01</td>
        <td class="center">Member</td>
         <td>David R</td>
        <td class="center">2012/01/01</td>
        <td class="center">Member</td>
         <td>David R</td>
        <td class="center">2012/01/01</td>
        <td class="center">Member</td>
         <td class="center">Member</td>
       
    </tr>
   
    
    </tbody>
    </table>



</div> 
</div>


  </body>
</html>
