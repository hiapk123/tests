<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加商品</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">


<link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>

<link
	href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>

<link href='css/elfinder.min.css' rel='stylesheet'>

<link href='css/animate.min.css' rel='stylesheet'>

<script src="bower_components/bootstrap/dist/css/bootstrap.min.css"></script>
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="bower_components/jquery/jquery.min.js"></script>
	
  </head>
  <%
			String s_id = request.getParameter("s_id");
			String s_name = request.getParameter("s_name");
			request.setAttribute("s_id", s_id);
			request.setAttribute("s_name", s_name);
		%>
  <body>
  
  
  
	
<form class="form-horizontal" role="form">
   <div class="form-group">
       <label for="firstname" class="col-sm-2 control-label">店铺名:</label>
        <input type="hidden" value="<%=s_id%>" >
      <div class="col-sm-3">
        <input type="text" readonly= "true " value="<%=s_name%>"	 class="form-control">  
      </div>
   </div>
   <div class="form-group">
      <label for="firstname" class="col-sm-2 control-label">商品分类:</label>
      <div class="col-sm-3">
         <select class="form-control">
         <option>散装</option>
         <option>水果</option>
         <option>无</option>
        
      </select>
      </div>
   </div>
  <div class="form-group">
     <div class="col-sm-2 "></div>
      <div class="col-sm-3">
      <form action="<%=basePath%>goods?m=toExcel&s_id=<%=s_id%>" method="post">
   
   <input type="submit"  value="导出数据到Excel" class="btn btn-success">
</form>
      </div>
   </div>
</form>		
		
		
		
      
 


  </body>
</html>
