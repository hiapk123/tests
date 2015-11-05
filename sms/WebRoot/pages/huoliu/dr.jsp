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



<title>导入货单</title>
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
<style type="text/css">
 
span {
font-weight: bold;
font-size:16px;
color:#EE2C2C;
#MM{ display: block;  position: absolute;  top: 0%;  left: 0%;  width: 100%;  height: 100%;  background-color: white;  z-index:1001;  -moz-opacity: 0.7;  opacity:.70;  filter: alpha(opacity=80);}
</style>
</head>
<script type="text/javascript">
function DaoRu()
{
	
window.open ("pages/huoliu/shangchuan.jsp",'newwindow','height=200,width=300,top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
}

</script>

<body>

<div id="MM">

<div   style=" margin: 80px auto; width: 650px;  height: 400px; border: 5px solid #EE2C2C;">
	<%
			String s_id = request.getParameter("s_id");
			String s_name = request.getParameter("s_name");
			request.setAttribute("s_id", s_id);
			request.setAttribute("s_name", s_name);
		%>
		
	<span>
		<label>1.还没创建过导入数据文件</label> <a
			href="pages/huoliu/do_download.jsp">下载</a></br> <label>2.已创建好导入数据文件，直接导入：</label>
		<br> 
		<button type="button" class="btn btn-success" name="submit"
		onclick="DaoRu()">上传文件</button>
		<%-- <form action="<%=basePath%>huoliu?m=shangchuan"
			enctype="multipart/form-data" method="post">
			上传文件：<input type="file" name="file1"><br /> <input
			id="tj"	type="submit" value="提交">
		</form> --%>
		<input id="DR"	 type="submit" value="导入">
	</span>
</div>
</div>
</body>
</html>
<%-- <script type="text/javascript">
$(function(){
  var s_id=<%=s_id%>;
$("#DR").click(function(){
	$("#MM").remove();
	$("#11").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "DR",
		"s_id":s_id,
	}, function(data) {
		$("#11").append(data);
	}, "html");
	
  });    
});

</script> --%>