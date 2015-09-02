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

	function setTab(m, n) {

		var tli = document.getElementById("menu" + m).getElementsByTagName(
				"button");

		var mli = document.getElementById("main" + m)
				.getElementsByTagName("li");

		for (i = 0; i < tli.length; i++) {

			tli[i].className = i == n ? "btn btn-success" : "";

			mli[i].style.display = i == n ? "block" : "none";

		}

	}
//-->
</script>
<%
					String s_id = request.getParameter("s_id");
					String s_name = request.getParameter("s_name");
					
					String g_barcode = request.getParameter("g_barcode");
					System.out.println("nima"+s_id);
				
				%> 

<style>
.center-block {
	  display: block;
	      /*将页面元素设置为块级元素*/
	 
	margin-right
	:
	auto;
	     /*左右居中显示*/
	 
	margin-left
	:
	auto;
}

.main {
	clear: both;
	padding: 8px;
	text-align: center;
}

#main0 li {
	display: none;
	width: 600px;
	height: 350px;
}

#main0 li.block {
	display: block;
}
</style>
<script>
$("#save").click(function(){
	var s_id=<%=s_id%>;
	var s_name=<%=s_name%>;
	var g_name=$("#g_name").val();
	var g_barcode=$("#g_barcode").val();
	
	var g_flag=$("#g_flag option:selected").val();  //获取选中的项
	//alert(g_flag);   //拿到选中项的值
	
	$("#liuyan").empty();
		
	$.post("<%=basePath%>goods", {
		"m" : "addGood",
		"s_id" :s_id,
		"s_name" :s_name ,
		"g_name" :g_name ,
		"g_barcode" :g_barcode  ,
		"g_flag" :g_flag,
		
	}, function(data) {
		$("#liuyan").append(data);
	}, "html");
	
});
</script>
</head>

<body>
<div id="liuyan">
	<nav class="navbar navbar-default" role="navigation">

	<div>
		<ul class="nav navbar-nav" id="menu0">
			<button type="button" onclick="setTab(0,0)" class="btn btn-success">必填资料</button>
			<button type="button" onclick="setTab(0,1)">扩展资料</button>
			<button type="button" onclick="setTab(0,2)">报表参数</button>
			<button type="button" onclick="setTab(0,3)">商品描述</button>
			<button type="button" onclick="setTab(0,4)">上传图片</button>

		</ul>
	</div>
	</nav>


	<div id="main0">
		<ul >
			<li class="block">
				
				<label>店铺名：<%=s_name%></label> <input type="hidden" value="<%=s_name%>"
				>


				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label
				for="name">商品状态</label> <select  id="g_flag" >
					<option  value="1">启用</option>
					<option value="0">禁用</option>

			</select> </br> <label>商品条码</label> <input type="text" id="g_barcode"
				value="<%=g_barcode%>">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>进货价</label>
				<input type="text" name="jinhuojia"> </br> <label>商品名称</label> <input
				type="text" id="g_name" > <label>销售价</label> <input
				type="text" name="xiaoshoujia"> </br> <label for="name">商品分类</label>
				<select name="fenlei">
					<option>水果</option>
					<option>散装</option>


			</select> <label>库存</label> <input type="text" name="kucun"> </br>
			</li>

			<!--扩展资料-->
			<li>
				<div class="form-group">


					<label>拼音码</label> <input type="text" id="pinyinma"> <label
						for="name">供货商</label> <select>
						<option>刘炎</option>
						<option>赵旭涛</option>
						<option>黄鹏</option>
					</select> </br> <label>库存上限</label> <input type="text" id="kucunshangxian">
					<label>库存下限</label> <input type="text" id="kucunxiaxian"> </br>
					<label>批发价</label> <input type="text" id="pifajia"> <label
						for="name">会员优惠</label>
					<div class="radio">
						<label> <input type="radio" name="optionsRadios"
							id="optionsRadios1" value="option1" checked> 会员折扣
						</label> <label> <input type="radio" name="optionsRadios"
							id="optionsRadios2" value="option2"> 会员价
						</label> <input type="text" id="huiyuanjia">
					</div>

					</br> <label for="maketime">生产日期：</label><input id="maketime"
						type="date" value="2014-01-13" /> <label>保质期（天）</label> <input
						type="text" id="baozhiqi"> </br> <label>自定义1</label> <input
						type="text" id="zidingyi1"> <label>自定义2</label> <input
						type="text" id="zidingyi2"></br> <label>自定义3</label> <input
						type="text" id="zidingyi3"> <label>自定义4</label> <input
						type="text" id="zidingyi4"></br>
					<div class="checkbox">
						<label> <input type="checkbox"> 积分商品
						</label>
					</div>

				</div>
			</li>


			<!-- 报表参数 -->
			<li><label>最小起订量</label> <input type="text"
				id="zuixiaoqidingliang"> <label>畅销量</label> <input
				type="text" id="changxiaoliang"></br> <label>最低陈列量</label> <input
				type="text" id="zuidichenlieliang"> <label>正常销售量</label> <input
				type="text" id="zhengchangxiaoshouliang"></br> <label>库存合理值</label>
				<input type="text" id="kucunhelizhi">
				<div class="checkbox">
					<label> <input type="checkbox"> 是否锁定
					</label>
				</div></li>


			<!-- 商品描述 -->
			<li><input type="text" style="width: 600px; height: 300px;"
				id="shangpinmiaoshu"></li>

			<!-- 上传图片 -->
			<li><input type="file" id="file">
				<button type="button" class="btn btn-success" name="submit">上传</button>

				<label>默认图片</label> <input type="text" id="morentupian"> <label
				for="morentupian">默认图片</label> <select>
					<option>图片1</option>

			</select></li>

			<button type="submit" class="btn btn-success center-block"
				name="submit" id="save">保存</button>

			<button type="button" class="btn btn-default center-block">取消</button>
		</ul>
	</div>
	</div>
	
</body>
</html>
