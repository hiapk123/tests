<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<%
			String s_id = request.getParameter("s_id");
			String s_name = request.getParameter("s_name");
		%>

</head>

<body>
<div id="addproduct">
	
</br>
</br>
		
  <center>
		<input type="hidden" value="<%=s_id%>" >
		<label   >店铺名：<%=s_name%></label>
		<input type="hidden" value="<%=s_name%>" >
		
			</br>
			<label   >商品条码:</label> <input type="text" id="g_barcode"
				placeholder="请输入商品条码">
				</br>
					</br>
				<button type="submit" class="btn btn-success"  id="deliver">确定</button>
              
				<button type="submit" class="btn btn-default" >生成</button>
				 
</center>	
		

	


</div>
<script>
$("#deliver").click(function(){
	
	var s_id=<%=s_id%>;
	var s_name=<%=s_name%>;
	var g_barcode=$("#g_barcode").val();
	if(store==null){
		alert("请重新选择店铺！");
		return;
	}
	
	
	$("#addproduct").empty();
		
	$.post("<%=basePath%>goods", {
		"m" : "addGood1",
		"s_id" :s_id,
		"s_name" :s_name  ,
		"g_barcode" :g_barcode  ,
	}, function(data) {
		$("#addproduct").append(data);
	}, "html");
	
});
</script>
</body>
</html>
