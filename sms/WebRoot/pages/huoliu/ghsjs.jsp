<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<base href="<%=basePath%>">
<title></title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content=",keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=basePath%>bower_components/jquery/jquery.min.js"></script>
   <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="<%=basePath%>js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.zh-CN.js"
	charset="utf-8"></script>
</head>
<script type="text/javascript">
function trimStr(str){return str.replace(/(^\s*)|(\s*$)/g,"");}
var flag="";

<%-- function querenjiesuan1(){
    var tr=$("#findjs").find("input:checkbox[class='subBox']:checked").parent().parent();
		var list_ss_id=tr.find(".ss_id");
		var list="";
		for(var i=0;i<list_ss_id.length;i++){
			list=list+list_ss_id.eq(i).val()+" ";
		}
		alert(list);
	var danshu=$("#danshu").val();
	var numOfGoods=$("#numOfGoods").val();
	var price=$("#price").val();
	var ss_pre_price=$("#ss_pre_price").val();
	var su_gd_return=$("#su_gd_return").val();
	var su_ps_return=$("#su_ps_return").val();
	
	$.post("<%=basePath%>huoliu", {
		"m" : "qrjs1",
		"danshu":danshu,
		"numOfGoods":numOfGoods,
		"price":price,
		"list":list,
	}, function(data) {
		$("#qrjs").append(data);
	}, "html");
	
	
} --%>
<%-- function querenjiesuan(node)
{
	var tr=$(node).parent().parent();
	var td = tr.find("td");
   var zhuangtai=td.eq(7).text();
    zhuangtai=trimStr(zhuangtai);
   
  
		var danshu=$("#findjs").find("input:checkbox[class='subBox']:checked").length ;
		var tr=$("#findjs").find("input:checkbox[class='subBox']:checked").parent().parent();
		var td = tr.find("td");
		
		/* var supplier1=$(".ss_detail").val();
		var strs= new Array(); //定义一数组
		strs=supplier1.split(" "); //字符分割 
		var supplier=strs[2]; */
		//alert(supplier);
		for(var i=0,numOfGoods=0;i<td.length/12;i++){
			numOfGoods=numOfGoods+parseInt(td.eq(8+12*i).text());
		}
		for(var i=0,price=0;i<td.length/12;i++){
			price=price+parseInt(td.eq(9+12*i).text());
		}
		
		$("#qrjs").empty();
		$.post("<%=basePath%>huoliu", {
			"m" : "qrjs",
			"danshu":danshu,
			"numOfGoods":numOfGoods,
			"price":price,
			
		}, function(data) {
			$("#qrjs").append(data);
		}, "html");
	
		
	
	
	
} --%>
function querenduizhang1()
{   var tr=$("#findjs").find("input:checkbox[class='subBox']:checked").parent().parent();
	var list_ss_id=tr.find(".ss_id");
	var list="";
	for(var i=0;i<list_ss_id.length;i++){
		list=list+list_ss_id.eq(i).val()+" ";
	}
	
	$("#motai1").empty();
	$("#ghsjs").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "qrdz1",
		"list":list,
	}, function(data) {
		$("#ghsjs").append(data);
	}, "html");
	$("#motaidz").click();
}
function querenduizhang(node)
{    var tr=$(node).parent().parent();
var td = tr.find("td");
var zhuangtai=td.eq(7).text();
 zhuangtai=trimStr(zhuangtai);

 
	var danshu=$("#findjs").find("input:checkbox[class='subBox']:checked").length ;
	if(danshu==0) {
		alert("您还没有选择待结算的货单");
		return;
	}
	$("#xx").click();
	var tr=$("#findjs").find("input:checkbox[class='subBox']:checked").parent().parent();
	var td = tr.find("td");
	
	for(var i=0,numOfGoods=0;i<td.length/12;i++){
		numOfGoods=numOfGoods+parseInt(td.eq(8+12*i).text());
	}
	for(var i=0,price=0;i<td.length/12;i++){
		price=price+parseInt(td.eq(9+12*i).text());
	}
	
	$("#motai1").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "QRDZ",
		"danshu":danshu,
		"numOfGoods":numOfGoods,
		"price":price,
	}, function(data) {
		$("#motai1").append(data);
	}, "html");
}


function getdetail(node){
	
	
	var list=$(node).val();
	$("#motai").empty();
	$.post("<%=basePath%>huoliu", {
			"m" : "ghsdetail",
			"list":list,
		}, function(data) {
			$("#motai").append(data);
		}, "html");
	
} 


function search(){
	var s_id=$("#store").val();
	var status=$("#category").val();
	var supplier=$("#supplier").val();
    flag=trimStr(status);
    if(flag=="待结算货单"){
    	$("#1").attr('disabled',false);
    	
    }else{
    	$("#1").attr('disabled',true);
    	
    }
	var currentPage=null;
	if(store==null){
		alert("请重新选择店铺！");
		return;
	}
	$("#findjs").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "findjs",
		"s_id" : s_id,
		"status" :status,
		"supplier" :supplier,
		"currentPage":currentPage,
	}, function(data) {
		$("#findjs").append(data);
	}, "html");
	
}


</script>
<body>
<div id="ghsjsdiv">

	<label >供货商结算</label>
	
	<div style="float: right;">
	<select id="store" class="singleSelector">
		<option value="" selected="selected" >全部门店</option>

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
<select id="supplier" class="singleSelector">
		<option value="" selected="selected" >全部供货商</option>

		<%
			List<Object[]> supplierList = (List<Object[]>) request.getAttribute("supplierList");
			if (supplierList != null && supplierList.size() != 0) {
				for (Object[] obj : supplierList) {
		%>
		<option value='<%=obj[1]%>'><%=obj[1]%></option>
		<%
			}
			}
		%>

	</select>
	<select id="category">
	   <option value="全部状态" selected="selected" >全部状态</option> 
		<option value="未成功货单" >未成功货单</option>
		
		<option value="待结算货单">待结算货单</option>
		<option value="已结算货单">已结算货单</option>
	</select>

	
		<input class="input-medium search-query" type="text" float:right /> <input
			type="button"  class="btn btn-success" value="  查     询     " onclick="search()" class="submitBtn" />
		
	</div>
	
	<div data-spy="scroll"
		style="width: 100%; overflow: auto; position: relative;"
		data-offset="10">
 
<div id="findjs">
		
		<table style="width:1200px; height:30px;  table-layout:fixed;" border="1" ;
		class="table-bordered table-condensed table-hover table-striped">
		
		<thead>
			<tr>
			    <th><input id="checkAll" type="checkbox"/></th>
				<th>序号</th>
				<th>操作</th>
				<th>供货商</th>
				<th>门店</th>
				<th>货单号</th>
				<th>下单时间</th>
				<th>货单类型</th>
				
				<th>货流量</th>
				<th>总价</th>
				<th>预付款</th>
				<th>状态</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		
		
	</tbody>

	</table>
		</div>
		
</div>
  <div id="detail">
 
 </div>
	

   <div id="ly" style="position: fixed; top: 480px;">
	
	<button type="button" class="btn btn-success btn-lg" name="submit"
	id="1"		onclick="querenduizhang(this)"  style="margin: 0 0 0 1100px" disabled="disabled" >确认结算</button>
	
	<!-- <button type="button" class="btn btn-success btn-lg" name="submit"
	id="2"		onclick="querenjiesuan(this)" style="margin: 0 0 0 50px" disabled="disabled">确认结算</button> -->	
	</div> 
 <button  data-toggle="modal" style="display:none"
		   data-target="#myModal1" id="xx"></button>

<div id="qrjs"  >
	
</div>
</div>
</body>

</html>
<!-- 模态框  详细（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button>
         </div>
            
      <!-- 模态框本质内容 --> 
      <div id="motai">
   </div>    
     <!-- 模态框本质内容 -->   
         <div class="modal-footer">
           
           
             
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<!-- 模态框  对账（Modal） -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" id="motaidz" class="close" data-dismiss="modal" aria-hidden="true">&times; </button>
         </div>
            
      <!-- 模态框本质内容 --> 
      <div id="motai1">
   </div>    
     <!-- 模态框本质内容 -->   
         <div class="modal-footer">
           
           
             
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>