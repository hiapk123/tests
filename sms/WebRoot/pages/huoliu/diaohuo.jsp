<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<head>
<style type="text/css">


#Layer1{ display: block;  position: absolute;  top: 0%;  left: 0%;  width: 100%;  height: 100%;  background-color: white;  z-index:1001;  -moz-opacity: 0.7;  opacity:.70;  filter: alpha(opacity=80);}
</style>

<script type="text/javascript">



function tr(node){ 
	var $table = $("#mytable");
	var $trs = $table.find("tbody").find("tr");
	k=$(node).attr("id");
	var $tr = $trs.eq(k);
	var $td = $tr.find("td");
	
	var g_name = $td.eq(1).text();
	var g_barcode = $td.eq(2).text();
	var g_stock_num = $td.eq(3).text();
	var su_name = $td.eq(4).text();
	var g_pur_price = $td.eq(5).text();
	var g_id = $td.eq(6).text();
	var g_unit = $td.eq(7).text();
	var c_name = $td.eq(8).text();
	var s_id_out=$("#s_id_out").val();
	var s_id_in=$("#s_id_in").val();
	$.post("<%=basePath%>huoliu", {
		"m":"add",
		"g_name":g_name,
		"g_barcode":g_barcode,
		"g_stock_num":g_stock_num,
		"su_name":su_name,
		"g_pur_price":g_pur_price,
		"g_id":g_id,
		"g_unit":g_unit,
		"c_name":c_name,
		"s_id_out":s_id_out,
		"s_id_in":s_id_in,
	}, function(data) {
		
		if(data!="buhege")
		{$("#11").append(data);
		$("#x").click();
		}else{
			alert("被调货门店没有该商品，请先在被调货门店先新增此商品");
			
			return;
			
		}
		
		
	}, "html");
	
}





function search(){
	var s_id_out=$("#s_id_out").val();
	var s_id_in=$("#s_id_in").val();
	var shuru=$("#shuru").val();
	
	$("#motai").empty();
	
	$.post("<%=basePath%>huoliu", {
		"m" : "diaohuo1",
		"s_id_out":s_id_out,
		"s_id_in":s_id_out,
		"shuru":shuru,
	}, function(data) {
		$("#motai").append(data);
	}, "html");
	
  }

function Inforukudh()
{   var s_id_out=$("#s_id_out").val();
    var s_id_in=$("#s_id_in").val();
    var s_name_out=$("#s_id_out :selected").text();

	var s_name_in=$("#s_id_in :selected").text();
     var l_info=$("#txt_remarks2").val();
	var list="";
	var listlength=$("#11").find(".jieguo").length;
	
        for(var i=0;i<listlength;i++)   {
        	var  list=list+$("#11").find(".jieguo").eq(i).val()+" ";
        }
        list=list.substring(0,list.length);
     
      
        
        $("#hlgldiv").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "Inforuku1",
		"list":list,
		"s_id_in":s_id_in,
		"s_id_out":s_id_out,
		"s_name_in":s_name_in,
		"s_name_out":s_name_out,
		"l_info":l_info,
	}, function(data) {
		$("#hlgldiv").append(data);
	}, "html");
}
function querenjinhuo()
{    var num=$("#11").find("table").length;
     if(num==0){
    	 alert("你还没有选择货物，请点击搜索然后双击商品来添加要调货的商品！");
    	 return;
     }
     $("#dakai").click();

	
}
 function DR(){
	var s_id=$("#store").val();
	$("#MM").remove();
	$("#11").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "DR",
		"s_id":s_id,
	}, function(data) {
		$("#11").append(data);
	}, "html");
}
function dongtai(){
	alert($("#11").find("input").css('type','text').val());
}
function guanbi(){
$("#motai").empty();
}
function shanchu(node){
	$("td").click(function(){
	var number=$(this).parents("tbody").children("tr").length;
		if(number>1){
		$(this).parent().remove();
		}else if(number=1){
			$(this).parents("table").remove();
		}
		});
} 
function queren(){
	var s_id_out=$("#s_id_out").val();
	
	var s_name_out=$("#s_id_out :selected").text();

	var s_id_in=$("#s_id_in").val();
	var s_name_in=$("#s_id_in :selected").text();
	if(s_id_out==s_id_in){
		alert("调货门店和进货门店不能一样,请重新选择!");
		return;
	}

	$("#1").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "xzmd1",
		"s_name_out":s_name_out,
		"s_name_in":s_name_in,
		"s_id_out":s_id_out,
		"s_id_in":s_id_in,
	}, function(data) {
		$("#1").append(data);
	}, "html");
	$("#Layer1").hide();
  }
$(function(){
	$("#queren2").click(function(){
		var $table = $("table");//
		var $trs = $table.find("tbody tr");
		for(var i=0; i<$trs.length; i++){
			var $tr = $trs.eq(i);
			var $td = $tr.find("td");
			alert($td.eq(0).text());
			$td.eq(1).text();
			$td.eq(2).text();
			} 
	});
  

$("#daoru").click(function(){
	var s_id=$("#store").val();
	
	
	
	//$("#goodtable").empty();
	
	$.post("<%=basePath%>huoliu", {
		"m" : "dr",
		"s_id":s_id,
		
	}, function(data) {
		$("#dr").append(data);
	}, "html");
	
  });    
});

</script>
</head>
<body>


<div id="1">
	<label>进货门店:待输入</label>
		<table style="width: 1200px; height: 30px; table-layout: fixed;"
			border="1";>
			<thead>
				<tr>
					<th><input id="checkAll" type="checkbox" /></th>
					<th>序号</th>
					<th>删除</th>
					<th>商品名称</th>
					<th>条码</th>
					<th>供货商</th>
					<th>出货门店</th>
					<th>库存</th>
					<th>进货量</th>
					<th>单位</th>
					<th>进货价(元)</th>
					<th>小计(元)</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
			</table>
			
	</div>	
<div id="11">
</div>

<div id="dr">
</div>	
	<div id="ly" style="position: relative; top: 400px;" class="row">
      <div class="col-xs-5 ">
    <button type="button" id="daoru" class="btn btn-info btn-lg" name="submit"
			 >导入</button>
			  <input  type="text"  id="shuru" style="height:45px;width:200px" placeholder="请输入拼音码/名称/">
			   <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal"
	onclick="search()"  >搜索</button>
      </div>
        <div class="col-xs-5 ">
      </div>
       <div class="col-xs-2 " style="float:right">
	<button type="button" class="btn btn-success btn-lg" 
			onclick="querenjinhuo()" >确认调货</button>	
			<button id="dakai" data-toggle="modal" data-target="#myModaldh"></button>
      </div>
   </div>
  

	
	
	
	
	
		
		

	
	
<div id="Layer1" >
     <div  style="margin:200px auto;">
		<label>请选择调货门店</label> <select id="s_id_out"
			class="singleSelector btn btn-block btn-success">
			<!-- <option value="-1" selected="selected" disabled="disabled">选择店铺</option> -->
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
		
		</select> <br>
	<label>请选择进货门店</label>	<select id="s_id_in"
			class="singleSelector btn btn-block btn-success">
			<!-- <option value="-1" selected="selected" disabled="disabled">选择店铺</option> -->
			<%
			List<Object[]> list1 = (List<Object[]>) request.getAttribute("storeList");
			if (list != null && list.size() != 0) {
				for (Object[] obj : list) {
		%>
			<option value='<%=obj[0]%>'><%=obj[1]%></option>
			<%
			}
			}
		%>
		</select> <br>
		
		<button type="button" class="btn btn-success btn-block " name="submit" onclick="queren()">确认</button>
	</div>
	</div>
	
</body>
</html>
<!-- 模态框  搜索商品（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" id="x"  onclick="guanbi()" data-dismiss="modal" aria-hidden="true">&times; </button>
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

