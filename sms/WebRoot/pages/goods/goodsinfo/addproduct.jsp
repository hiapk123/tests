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
<%
			String s_id = request.getAttribute("s_id").toString();
			String s_name = request.getAttribute("s_name").toString();
			
			String message = request.getAttribute("message").toString();
			
		%>
<script type="text/javascript">
$(document).ready(function () {
	if ($("#status").val() != "") {
		alert($("#status").val());
	}
	
});
</script>

</head>

<body>
<div id="addproduct">
	
</br>
</br>


<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">店铺名：</label>
      <div class="col-md-6">
      <select id="store" class="singleSelector form-control"">
		<option value="<%=s_id%>" selected="selected" disabled="disabled"><%=s_name%></option>

		<%
			List<Object[]> list = (List<Object[]>) request.getAttribute("storeList");
			if (list != null && list.size() != 0) {
				for (Object[] obj : list) {
					if(!s_id.equals(obj[0].toString())){
		%>
		<option value='<%=obj[0]%>'><%=obj[1]%></option>
		<%
			}
			}
			}
		%>

	</select>  
      </div>
   </div>
</form> 
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">商品条码:</label>
      <div class="col-md-6">
      <input type="text" id="g_barcode" value=""	placeholder="请输入商品条码" class="form-control">  
      </div>
   </div>
</form> 
  
		<input type="hidden" value="<%=s_id%>" >
					<input id="status" type="hidden" name="message" value="${message}">
					<center>
				<button type="submit" class="btn btn-success"  onclick="deliver()">确定</button>
              
				<button type="submit" class="btn btn-default" >生成</button>
				 
	                </center>
		

	


</div>
<script>
function deliver(){
	var s_id=<%=s_id%>;
	var s_name='<%=s_name%>';
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
	
}
</script>
</body>
</html>

