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
      <label for="name">复制商品到</label>
      <select class="form-control">
       
         <option>小六子食品店</option>
         <option>悠食客1店</option>
         <option>悠食客2店</option>
        
      </select>
      </div></br>
      <label>提示：只复制目标门店没有的商品。</label>
     <button type="button" class="btn btn-success" name="submit">保存</button>

			<button type="submit" class="btn btn-default">取消</button>
  
   
</form>
  </body>
</html>
