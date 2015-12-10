<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   
  <%
			String s_id = request.getParameter("s_id");
			String s_name = request.getParameter("s_name");
			request.setAttribute("s_id", s_id);
			request.setAttribute("s_name", s_name);
		%>
  <body>
  
  
  
	
<div class="form-horizontal" role="form">
   <div class="form-group">
       <label for="firstname" class="col-sm-2 control-label">店铺名:</label>
        <input type="hidden" value="<%=s_id%>" >
      <div class="col-sm-3">
        <input type="text" readonly= "true " value="<%=s_name%>"	 class="form-control">  
      </div>
   </div>
 <form action="<%=basePath%>goods?m=toExcel&s_id=<%=s_id%>" method="post">
   <div class="form-group">
      <label for="firstname" class="col-sm-2 control-label">商品分类:</label>
      <div class="col-sm-3">
        <select name="c_name" class="form-control">

									<%
										List<Object[]> list1 = (List<Object[]>) request.getAttribute("fenlei");
										if (list1 != null && list1.size() != 0) {
											for (Object[] obj : list1) {
									%>
									<option value='<%=obj[0]%>'><%=obj[1]%></option>
									<%
										}
										}
	
										
										%>
</select>
      </div>
   </div>
  <div class="form-group">
    
      <div class="col-sm-2"></div>
      
     
    <div class="col-sm-3">
   <input type="submit" onclick="" value="导出数据到Excel" class="btn btn-success">
   </div>
</form>
      
   </div>
</div>		


		
		
		
      
 


  </body>
</html>
