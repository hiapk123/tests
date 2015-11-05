<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"
	import="java.util.*,org.uestc.util.*"%>
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

<script type="text/javascript" src="<%=basePath%>js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.zh-CN.js"
	charset="utf-8"></script>
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
function ss(){
	alert(119);
}
</script>
</head>
<button onclick="ss()">哈哈</button>
<body>

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

<%
String list=request.getAttribute("list").toString();
String [] Good=list.split(",");
String [] good=new String[Good.length]; 
for(int i=0;i<Good.length;i++){
	good[i]=Good[i].trim();
}
System.out.println("liupeng    "+list+"长度"+good.length);
String sql="select s_id from goods where g_id=?";
List<Object[]> a = SqlHelper.find(sql, good[0]);
Number num = (Number) a.get(0)[0];  
int  Num = num.intValue();
String s_id=String.valueOf(Num);
%>
<script>
$(document).ready(function() {
$("#g_del option[value='<%=good[27]%>']").attr("selected","selected");
$("#c_name option[value='<%=good[8]%>']").attr("selected","selected");
$("#su_name option[value='<%=good[14]%>']").attr("selected","selected");
if(good[15]==1){
$("#g_flag input[type='checkbox']").attr("checked",true); 
}else if(good[15]==0){
	$("#g_flag input[type='checkbox']").attr("checked",false);
}
alert("ss");
if(good[16]==1){
$(".radio input[value='option2']").attr("checked","checked");
}else{
$(".radio input[value='1111']").attr("checked","checked");
}
});

function haha(){
	$("#g_vip_price").attr('disabled',true);
	$("#g_vip_price").val("");
	
}
function HAHA(){
	$("#g_vip_price").attr('disabled',false);
	$("#vip_id").attr('value',1);
}

function save(){
	var g_id=<%=good[0]%>;
	var s_id=<%=s_id%>;

	var s_name="<%=good[2]%>";
	var g_del=$("#g_del option:selected").val();  //获取选中的项
	var g_barcode=$("#g_barcode").val();
	var g_pur_price=$("#g_pur_price").val();
	if(g_pur_price=="") {alert("进货价不能为空"); return;}
	if(isNaN(g_pur_price)){alert("进货价请输入数字"); return;}
	var g_name=$("#g_name").val();
	if(g_name=="") {alert("商品名称不能为空"); return;}
	var g_sale_price=$("#g_sale_price").val();
	if(g_sale_price=="") {alert("销售价不能为空"); return;}
	if(isNaN(g_sale_price)){alert("销售价请输入数字"); return;}
	var c_name=$("#c_name").val();
	var g_stock_num=$("#g_stock_num").val();
	if(g_stock_num=="") {alert("库存量不能为空"); return;}
	if(isNaN(g_stock_num)){alert("库存量请输入数字"); return;}
	//扩展资料
	var g_pm=$("#g_pm").val();
	var su_name=$("#su_name").val();
	var g_stock_max=$("#g_stock_max").val();
	if(isNaN(g_stock_max)){alert("最大库存量请输入数字"); return;}
	var g_stock_min=$("#g_stock_min").val();
	if(isNaN(g_stock_min)){alert("最小库存量请输入数字"); return;}
	var g_trade_price=$("#g_trade_price").val();
	if(isNaN(g_trade_price)){alert("批发价请输入数字"); return;}
	var g_prod_date=$("#g_prod_date").val();
	var g_giq=$("#g_giq").val();
	if(isNaN(g_trade_price)){alert("保质期请输入数字"); return;}
	var g_vip_price=$("#g_vip_price").val();
	if(isNaN(g_vip_price)){alert("会员价请输入数字"); return;}
	var vip_id=$("#vip_id").val();
	var zdy1=$("#zdy1").val();
	var zdy2=$("#zdy2").val();
	var zdy3=$("#zdy3").val();
	var zdy4=$("#zdy4").val();
	//报表参数
	var g_qd_min=$("#g_qd_min").val();
	var g_cl_min=$("#g_cl_min").val();
	var g_stock_nor=$("#g_stock_nor").val();
	var g_best=$("#g_best").val();
	var g_sale_nor=$("#g_sale_nor").val();
	var g_flag=$("input:checkbox:checked").val();
	if(g_flag=="on") {g_flag=1;}
	else{ g_flag=0;}
	//商品描述，图片路径
	var g_info=$("#g_info").val();
	var g_img_path=$("#g_img_path").val();
	$("#goodsinfodiv").empty();
		
	$.post("<%=basePath%>goods", {
		"m" : "editGood2",
		
		"g_id" :g_id,
		 "s_name" :s_name ,
		"g_name" :g_name ,
		"g_barcode" :g_barcode  ,
		"g_del" :g_del,
		"g_pur_price" :g_pur_price ,
		"g_sale_price" :g_sale_price ,
		"c_name" :c_name  ,
		"g_stock_num" :g_stock_num,
		//扩展资料
		"g_pm" :g_pm,
		"su_name" :su_name,
		"g_stock_max" :g_stock_max ,
		"g_stock_min" :g_stock_min  ,
		"g_trade_price" :g_trade_price,
		"g_prod_date" :g_prod_date ,
		"g_giq" :g_giq,
		"g_vip_price" :g_vip_price ,
		"vip_id" :vip_id ,
		"zdy1" :zdy1  ,
		"zdy2" :zdy2,
		"zdy3" :zdy3 ,
		"zdy4" :zdy4 ,
		//报表参数
		"g_qd_min" :g_qd_min ,
		"g_cl_min" :g_cl_min ,
		"g_stock_nor" :g_stock_nor  ,
		"g_best" :g_best,
		"g_sale_nor" :g_sale_nor ,
		"g_flag" :g_flag ,
		//商品描述，图片路径
		"g_info" :g_info ,
		"g_img_path" :g_img_path ,
		 //
		"s_id" :s_id,
		"currentPage":"1",
	
		
		
	}, function(data) {
		$("#goodsinfodiv").append(data);
	}, "html");
	
}

</script>
<div id="rr"  >
	<div id="main0">
		
		<ul >
<li class="block">
	<div class="container">
        
         <div class="row">
 

           <div class="col-md-6" >
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">店铺名</label>
      <div class="col-md-9">
        <input type="text"  class="form-control "  value="<%=good[2] %>" disabled="disabled">
      </div>
   </div>
</form>  

<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">商品状态</label>
      <div class="col-md-9">
       <select  id="g_del" class="form-control">
                    <option value="0">禁用</option>
					<option  value="1">启用</option>
					
			</select> 
      </div>
   </div>
</form>      	
				
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">商品条码</label>
      <div class="col-md-9">
       <input type="text" class="form-control"  id="g_barcode" value="<%=good[3] %>"
         placeholder="请输入名称">
      </div>
   </div>
</form>    
  
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">进货价</label>
      <div class="col-md-9">
       <input type="text" class="form-control"  id="g_pur_price" value="<%=good[5]%>">
      </div>
   </div>
</form>    
</div>


            <div class="col-md-6" >
   
 <form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">商品名称</label>
      <div class="col-md-9">
        <input type="text" class="form-control" id="g_name"  value="<%=good[1]%>"> 
      </div>
   </div>
</form>  

 <form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">销售价</label>
      <div class="col-md-9">
        <input type="text" class="form-control" id="g_sale_price" value="<%=good[6]%>"> 
      </div>
   </div>
</form>   
  
 <form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">商品分类</label>
      <div class="col-md-9">
       <select id="c_name" class="form-control">
					<option value="水果">水果</option>
					<option value="散装">散装</option>
			    </select> 
      </div>
   </div>
</form>              
	
 <form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">库存</label>
      <div class="col-md-9">
       <input type="text" class="form-control"  id="g_stock_num" value="<%=good[4]%>"> 
      </div>
   </div>
</form>    	
   		
			
            </div>
           
         </div>
</div>
				
			 
</li>
		<!--扩展资料-->
<li >
<div class="container">
        
         <div class="row">
           
           <div class="col-md-6" >
 <form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">拼音码</label>
      <div class="col-md-9">
       <input type="text" id="g_pm" class="form-control" value="<%=good[13]%>">
      </div>
   </div>
</form>    	            

     				
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">库存上限</label>
      <div class="col-md-9">
       <input type="text" id="g_stock_max" class="form-control" value="<%=good[10]%>">
      </div>
   </div>
</form> 				

 <form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">批发价</label>
      <div class="col-md-9">
      <input type="text" id="g_trade_price" class="form-control" value="<%=good[7]%>"> 
      </div>
   </div>
</form>    
  
  <form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">生产日期</label>
      <div class="col-md-9">
       <div class="input-group date form_date " data-date="" data-date-format="dd MM yyyy" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
               <input class="form-control" size="16" id="g_prod_date" type="text" value="<%=good[11]%>" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
					<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div> 
      </div>
   </div>
</form>    
   <script type="text/javascript">
  
	$('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	
</script>
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">自定义1</label>
      <div class="col-md-9">
      <input type="text" id="zdy1" class="form-control" value="<%=good[18]%>"> 
      </div>
   </div>
</form> 
   
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">自定义3</label>
      <div class="col-md-9">
      <input type="text" id="zdy3" class="form-control" value="<%=good[20]%>"> 
      </div>
   </div>
</form>    
   
   
   
</div>
 

  <div class="col-md-6" >
 <form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">供货商</label>
      <div class="col-md-9">
      <select class="form-control" id="su_name">
		<option>刘炎</option>
		<option>赵旭涛</option>
		<option>黄鹏</option>
		</select> 
      </div>
   </div>
</form>   
    
 <form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">库存下限</label>
      <div class="col-md-9">
      <input type="text" id="g_stock_min" class="form-control" value="<%=good[9]%>" >
      </div>
   </div>
</form>     
   	
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">会员优惠</label>
      <div class="col-md-9">
      <div class="radio" >
						<label> <input type="radio"  name="optionsRadios"
							id="vip_id" onclick="haha()"  value="1111" > 会员折扣
						</label> 
						<label> <input type="radio" name="optionsRadios"
							id="optionsRadios2" onclick="HAHA()" value="option2" > 会员价
						</label> <input type="text" id="g_vip_price" value="<%=good[17]%>" disabled=true>
                 </div>	
      </div>
   </div>
</form>     
     
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">保质期（天）</label>
      <div class="col-md-9">
     <input  type="text" id="g_giq" class="form-control" value="<%=good[12]%>"> 
      </div>
   </div>
</form>  	
    			
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">自定义2</label>
      <div class="col-md-9">
      <input type="text" id="zdy2" class="form-control" value="<%=good[19]%>">  
      </div>
   </div>
</form>     
   	
  <form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-3 control-label">自定义4</label>
      <div class="col-md-9">
      <input type="text" id="zdy4" class="form-control" value="<%=good[21]%>">  
      </div>
   </div>
</form>    
  
   		
			
            </div>
           
         </div>
</div>
				
			 
</li>
			<!-- 报表参数 -->
			<li >
<div class="container">
        
         <div class="row">
           
           <div class="col-md-5" >
             
<div class="form-group">
    <label>最小起订量</label>
     <input type="text" id="g_qd_min" class="form-control" value="<%=good[22]%>">
   </div>				
				
<div class="form-group">
     <label>最低陈列量</label> <input type="text" id="g_cl_min" value="<%=good[22]%>" class="form-control" >
 </div>
  <div class="form-group">
     <label>库存合理值</label>
	<input type="text" id="g_stock_nor" class="form-control" value="<%=good[23]%>">
   </div>
  
   	<div class="form-group">
     <div class="checkbox" id="g_flag">
					<label> <input type="checkbox" > 是否锁定
					</label>
				</div>
   </div>
   
  
   
  
</div>
 <div class="col-md-2" >
 </div>

  <div class="col-md-5" >
   <div class="form-group">
   <label>畅销量</label>
   <input type="text" id="g_best" class="form-control" value="<%=good[25]%>">
   </div>
   <div class="form-group">
    <label>正常销售量</label>
     <input type="text" id="g_sale_nor" class="form-control" value="<%=good[26]%>">
   </div> 
   
			
            </div>
           
         </div>
</div>
				
			 
</li>


			<!-- 商品描述 -->
			<li>
			<div class="container">
             <div class="row">
             <div class="col-md-12" >
            <input type="text" id="g_info" value="<%=good[28]%>" style=" width:600px; height:280px; margin-left:200px;"> 
			
			
			</div>
             </div>
             </div>
			
		
			
			
				</li>

			<!-- 上传图片 -->
			<li>
			<div style="width:600px; height:280px; overflow-x:scroll; background-color:#ffffff; padding:20px; margin-left:200px;">
                     <img  id="g_img_path" value="<%=good[29]%>" src="img/psb.jpg" style="height:300px;width:300px"></img>
             </div>
			<input type="file" id="file">
				<button type="button" class="btn btn-success" name="submit">上传</button>

				<label>默认图片</label> <input type="text" id="morentupian"> <label
				for="morentupian">默认图片</label> <select>
					<option>图片1</option>

			</select></li>

			<button type="submit" class="btn btn-success center-block"
				 onclick="save()">保存</button>
          
			<button type="button" class="btn btn-default center-block">取消</button>
		</ul>
		
	</div>
	
	<a href="<%=basePath %>goods?m=goodsInfo">跳转商品资料页面</a>
	</div>
</body>
</html>
