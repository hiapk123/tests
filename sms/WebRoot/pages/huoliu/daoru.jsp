<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>批量导入</title>
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
<script src="js/bootstrap.file-input.js"></script>




</head>

<body>
	<%
			String s_id = request.getParameter("s_id");
			String s_name = request.getParameter("s_name");
			request.setAttribute("s_id", s_id);
			request.setAttribute("s_name", s_name);
		%>



	<div>
		<label>1.还没创建过导入数据文件</label> <a
			href="pages/goods/goodsinfo/do_download.jsp">下载</a></br> <label>2.已创建好导入数据文件，直接导入：</label>
		<br> <br> <input type="hidden" value="<%=s_id%>"> <label>店铺名：<%=s_name%></label>
		<input type="hidden" value="<%=s_name%>">

		<form action="<%=basePath%>huoliu?m=Shangchuanwenjian"
			enctype="multipart/form-data" method="post">
			上传文件：<input type="file" name="file1"><br /> <input
				type="submit" value="提交">
		</form>

	</div>







</body>
</html>
