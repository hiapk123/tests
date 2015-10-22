<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
			String s_id1 = request.getParameter("s_id");
			String s_name = request.getParameter("s_name");
			request.setAttribute("s_id1", s_id1);
			request.setAttribute("s_name", s_name);
		%>
<head>



<script src="<%=basePath%>bower_components/jquery/jquery.min.js"></script>


<script>
$(function(){
$("#haha").click(function(){
	
	var s_id1=<%=s_id1%>;
	var s_id2=$("#s_id2").val();
	alert("hello");
	
	
	
	$("#fuzhishangpin").empty();
		
	$.post("<%=basePath%>goods", {
		"m" : "fuzhi",
		"s_id1" :s_id1,
		"s_id2" :s_id2,
		
	}, function(data) {
		$("#fuzhishangpin").append(data);
	}, "html");
	
});
});
</script>
</head>
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
  
   <div id="fuzhishangpin">
   
      <label for="name">复制商品到</label>
     
      </br>
      <label>提示：只复制目标门店没有的商品。</label>
     
      <select id="s_id2" class="singleSelector">
		 <option value="-1" selected="selected" disabled="disabled">选择店铺</option> 

		<%
		  
			List<Object[]> list = (List<Object[]>) request.getAttribute("storeList");
			if (list != null && list.size() != 0) {
				for (Object[] obj : list) {
					if(!s_id1.equals(obj[0].toString())){
		%>
		<option value='<%=obj[0]%>'><%=obj[1]%></option>
		<%
					}	
			}
			}
		%>

	</select>
   <input type="submit" value="保存" id="haha" class="submitBtn" />

			<button type="submit" class="btn btn-default">取消</button>
  
</div>

  </body>
</html>

