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
	
	
  </head>
  <%
			String s_id = request.getParameter("s_id");
			String s_name = request.getParameter("s_name");
			request.setAttribute("s_id", s_id);
			request.setAttribute("s_name", s_name);
		%>
  <body>
   <form role="form">
   <div class="form-group">
     <input type="hidden" value="<%=s_id%>" >
		<label   >店铺名：<%=s_name%></label>
		<input type="hidden" value="<%=s_name%>" >
       <label for="name">商品分类</label>
      <select class="form-control">
         <option>散装</option>
         <option>水果</option>
         <option>无</option>
        
      </select>
   </div>
  
  
   
</form>
<form action="<%=basePath%>goods?m=toExcel&s_id=<%=s_id%>" method="post">
   
   <input type=submit value=导出数据到Excel>
</form>
  </body>
</html>

