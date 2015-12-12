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




</head>
<%
String s_id=request.getParameter("s_id");
String s_name=request.getParameter("s_name");
%>
<script type="text/javascript">
$(document).ready(function () {
	if ($("#status").val() != "") {
		alert($("#status").val());
	}
	
});
</script>
<script>
function trimStr(str){return str.replace(/(^\s*)|(\s*$)/g,"");}
function tijiao(){
	
	var s_id=<%=s_id%>;
	var s_name="<%=s_name%>";
    var g_barcode=$("#g_barcode").val();
    if(trimStr(g_barcode)==""){
    	alert("商品条码不可为空!");
    	return;
    }
    var g_name=$("#g_name").val();
    if(trimStr(g_name)==""){
    	alert("商品名称不可为空!");
    	return;
    }
    var c_name=$("#c_name :selected").text();
    var c_id=$("#c_name").val();
    var g_pur_price=$("#g_pur_price").val();
    if(trimStr(g_pur_price)==""){
    	alert("进货价不可为空!");
    	return;
    }
    var g_sale_price=$("#g_sale_price").val();
    if(trimStr(g_sale_price)==""){
    	alert("销售价不可为空!");
    	return;
    }
    var g_stock_num=$("#g_stock_num").val();
    if(trimStr(g_stock_num)==""){
    	alert("库存量不可为空!");
    	return;
    }
	
    
    
	$.post("<%=basePath%>goods", {
		"m" : "kuaisu1",
		"s_id" : s_id,
		"s_name":s_name,
		"g_barcode":g_barcode,
		"g_name" : g_name,
		"c_name" : c_name,
		"c_id" : c_id,
		"g_pur_price" : g_pur_price,
		"g_sale_price" : g_sale_price,
		"g_stock_num" : g_stock_num,
	
		
	}, function(data) {
		if(data!="buhege"){
			$("#hehe").empty();
			$("#hehe").append(data);
			
		}else{
			alert("该商品条码已存在");
			return;
		}
	}, "html");
	
}




</script>
<body>
	<span class="label label-default" style="padding:10px">商品快速录入</span>  &nbsp;&nbsp;
	<a href="<%=basePath %>goods?m=goodsInfo"><input type="button" class="btn btn-success" value="返回"></input></a>
	<div style="float:right">
	<label>录入门店:</label>
	  <input  readonly= "true " value="<%=s_name%>"	 class="btn-success">
</div>
	<table class="table table-bordered table-condensed table-hover table-striped ">
		<thead>
			<tr class="danger">
				<th>行号</th>
				<th>商品条码</th>
				<th>商品名称</th>
				<th>商品分类</th>
				<th>进货价</th>
				<th>销售价</th>
				<th>库存量</th>
			</tr>
		</thead>
		<tbody >
		<input id="status" type="hidden" name="message" value="${message}">
		<tr class="info">
				<td>1</td>
				<td><input type="text" id="g_barcode" style="width:100px"></td>
				<td><input type="text" id="g_name" style="width:100px"></td>
				<td><select id="c_name" class="btn btn-success">
									<%
										List<Object[]> list1 = (List<Object[]>) request.getAttribute("fenlei");
										if (list1 != null && list1.size() != 0) {
											for (Object[] obj : list1) {
									%>
									<option value='<%=obj[0]%>'><%=obj[1]%></option>
									<%
										}
										}
									%>
                </select></td>
				<td><input type="text" id="g_pur_price" style="width:100px"></td>
				<td><input type="text" id="g_sale_price" style="width:100px"></td>
				<td><input type="text" id="g_stock_num" style="width:100px"></td>
			</tr>	
		</tbody>
	</table>
	
	<button type="submit"  class="btn btn-success" onclick="tijiao()" >保存</button>
	<button type="reset" i class="btn btn-success" name="reset" >取消</button>

	
</body>
</html>
