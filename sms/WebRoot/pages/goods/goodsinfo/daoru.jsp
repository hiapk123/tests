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
<script language="javascript" src="<%=basePath%>js/ajaxfileupload.js" > </script>
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
<script type="text/javascript">
	function sb()
	{
		var s_id=$("#store2").val();
		var s_name=$("#store2 :selected").text();
       $.ajaxFileUpload({
                 url:'<%=basePath%>goods?m=Shangchuanwenjian&s_id='+s_id+"&s_name="+s_name,             //需要链接到服务器地址
                 secureuri:false,
                 fileElementId:'uploadFileInput',                         //文件选择框的id属性
                 dataType: 'json',                                     //服务器返回的格式，可以是json
                 success: function (data)             //相当于java中try语句块的用法
                 {   
                
                	 $('#result').html(data.message.replace(/;/g, "<br/>"));
                 },
                 error: function (data)             //相当于java中catch语句块的用法
                 {
                	 alert(data.message);
                     $('#result').html(data.message);
                 }
               }
             );
    }
	</script>

	<div id="main0">
<li style="display:block">
	
	<div class="form-horizontal" role="form">
   <div class="form-group">
      <div class="col-sm-6">
     
        <input type="text"  value="1.还没创建过导入数据文件"	disabled=true class="form-control"> 
      </div>
     
      <div class="col-sm-3">
      <A HREF="pages/huoliu/download.jsp?path=<%=getServletContext().getRealPath("/upload/商品导入模板.xls") %>" class="form-control btn btn-primary">下载</A>
      
      </div>
   </div>
   </div>


<form class="form-horizontal" role="form">
   <div class="form-group">
     <div class="col-sm-6">
     
        <input type="text"  value="2.已创建好导入数据文件，直接导入"	disabled=true class="form-control"> 
      </div>
   </div>
</form>
   
  <form class="form-horizontal" role="form">
   <div class="form-group">
  
     <div class="col-sm-6">
     
        <input type="text"  value="3.店铺名"	disabled=true class="form-control"> 
      </div>
      
      
     
      <div class="col-sm-3">
        <select id="store2" class="singleSelector form-control">
		

		<%
			List<Object[]> list = (List<Object[]>) request.getAttribute("storeList");
			if (list != null && list.size() != 0) {
				for (Object[] obj : list) {
		%>
		<option value='<%=obj[0]%>'><%=obj[1]%></option>
		<%
			}
			}
		%>

	</select>    
      </div>
   </div>
</form>  

		
  <div class="form-horizontal" role="form">
   <div class="form-group">
     <div class="col-sm-6">
      <input id="uploadFileInput" type="file" size="45" name="uploadFileInput" class="input btn btn-success form-control" />
      </div>
      <div class="col-sm-3">
      <input type="button" id="buttonUpload" onclick="sb()" class="form-control btn btn-primary" value="上   传"/>
      </div>
   </div>
  </div> 
 
  <div id="result" style="FONT:12px 宋体">
 <p>说明：如果导入的商品，系统中没有，进行新增，否则进行更新</p>
  
  </div>
	
	
	
		
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
