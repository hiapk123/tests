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
	
	<SCRIPT LANGUAGE="javascript">
<!--
function ShowNewProductDiv1()
{
window.open ('pages/goods/addproduct-info.jsp','newwindow','height=309,width=700,top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
}
-->
</SCRIPT>
  </head>
  
  <body>
   <form role="form">
   <div class="form-group">
      <label for="name">所属门店</label>
      <select class="form-control">
         <option>小六子食品店</option>
         <option>悠食客1店</option>
         <option>悠食客2店</option>
        
      </select>
   </div>
  <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">商品条码</label>
      <div class="col-sm-10">
         <input type="text" class="form-control" id="lastname" 
            placeholder="请输入商品条码">
            <button type="button" class="btn btn-success" name="submit" onclick="ShowNewProductDiv1()">确定</button>
            
         <button type="submit" class="btn btn-default">生成</button>
      </div>
   </div>
  
   
</form>
  </body>
</html>
