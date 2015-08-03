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
  
  <body>
   <form role="form">
   <div class="form-group">
      <label for="name">选择门店</label>
      <select class="form-control">
         <option>小六子食品店</option>
         <option>悠食客1店</option>
         <option>悠食客2店</option>
        
      </select>
       <label for="name">商品分类</label>
      <select class="form-control">
         <option>散装</option>
         <option>水果</option>
         <option>无</option>
        
      </select>
   </div>
  <button type="button" class="btn btn-success" name="submit" >导出</button>
  
   
</form>
  </body>
</html>
