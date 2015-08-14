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

<%-- <script type="text/javascript">
	$(function(){
	
		
		
		//进入填资料页面
		$("#deliver").click(function(){
		
			
			
			$("#goodsinfodiv").empty();
		
				
			$.post("<%=basePath%>goods", {
				"m" : "addGood",
				
				
			}, function(data) {
				$("#goodsinfodiv").append(data);
			}, "html");
			
		});
	
	});
</script> --%>
</head>

<body>

	<form role="form" action="pages/goods/goodsinfo/addproduct-info.jsp"
		method="post">


		<%
			String s_id = request.getParameter("s_id");
			String s_name = request.getParameter("s_name");
		%>

		<input type="hidden" value="<%=s_id%>" name="storeID"><label>店铺名：<%=s_name%></label>
		<input type="hidden" value="<%=s_name%>" name="storeName">
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">商品条码</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" name="shangpintiaoma"
					placeholder="请输入商品条码">
				<button type="button" class="btn btn-success" name="submit">确定</button>

				<button type="submit" class="btn btn-default" id="deliver">生成</button>
			</div>
		</div>

	</form>

<div id="goodsinfodiv">

	

</div>


</body>
</html>
