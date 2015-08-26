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

<title>导入</title>
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

<script>

<!--

/*第一种形式 第二种形式 更换显示样式*/

function setTab(m,n){

 var tli=document.getElementById("menu"+m).getElementsByTagName("button");

 var mli=document.getElementById("main"+m).getElementsByTagName("li");

 for(i=0;i<tli.length;i++){

  tli[i].className=i==n?"btn btn-success":"";

  mli[i].style.display=i==n?"block":"none";

 }

}

//-->

</script>

<style>
.main {
	clear: both;
	padding: 8px;
	text-align: center;
}

#main0 li {
	display: none;
}

#main0 li.block {
	display: block;
}
</style>
</head>

<body>
	<nav class="navbar navbar-default" role="navigation">

	<div>
		<ul class="nav navbar-nav" id="menu0">
			<button type="button" onclick="setTab(0,0)" class="btn btn-success">导入商品信息</button>
			<button type="button" onclick="setTab(0,1)">导入商品图片</button>


		</ul>
	</div>
	</nav>


	<div id="main0">

		<li class="block"><label>1.还没创建过导入数据文件</label> <a
			href="pages/goods/goodsinfo/do_download.jsp">下载</a></br> <label>2.已创建好导入数据文件，直接导入：</label>
			<label>所属门店</label> <select>
				<option>小六子食品店</option>
				<option>悠食客1店</option>
				<option>悠食客2店</option>

		</select>

			<form
				action="<%=basePath%>goods?m=Shangchuanwenjian"
				enctype="multipart/form-data" method="post">
				 上传文件：<input
					type="file" name="file1" ><br /> 
					 <input type="submit" value="提交">
			</form>

			<%-- <form action="<%=basePath%>goods?m=excToMqsql" method="post">

				<input type="submit" value="将excel数据添加到mysql中">

			</form> --%>
			</li>


		<li role="form">
			<div class="form-group">


				<label>说明：上传的图片以条码作为文件名，图片格式为（jpg,jpeg），最佳大小：900*1200，每张不超过3M，一次不超过50张.</label>
				<label>所属门店</label> <select>
					<option>小六子食品店</option>
					<option>悠食客1店</option>
					<option>悠食客2店</option>

				</select>

				<!--  <input type="file" id="file" >
              <button type="button" class="btn btn-success" name="submit" >shangchuan</button> -->





			</div>
		</li>



	</div>
</body>
</html>
