<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
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





  <link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
   <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
   <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="<%=basePath%>js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.zh-CN.js"
	charset="utf-8"></script>
<script type="text/javascript">
	$(function(){
	
		$("#search").click(function(){
			
			var store=$("#store").val();
			var g_del=$("#g_del").val();
			var c_id=$("#c_name").val();
			var c_name=$("#c_name :selected").text();
			var currentPage=null;
			var key=$("#key").val();
			if(store==null){
				alert("请重新选择店铺！");
				return;
			}
			$("#goodsinfodiv").empty();
			$.post("<%=basePath%>goods", {
				"m" : "findByPage",
				"s_id" : store,
				"g_del" : g_del,
				"c_name" :c_name  ,
				"c_id" :c_id  ,
				"currentPage":currentPage,
				"key":key,
			}, function(data) {
				$("#goodsinfodiv").append(data);
			}, "html");
			$("#page").show();
		});
		
	
		$("#kuaisu").click(function(){
			var store=$("#store").val();
			var s_name=$("#store :selected").text();
			if(store==null){
				alert("请重新选择店铺！");
				return;
			}
			$("#hehe").empty();
			$.post("<%=basePath%>goods", {
				"m" : "kuaisu",
				"store" : store,
				"s_name":s_name,
				"s_id":store,
				
			}, function(data) {
				$("#hehe").append(data);
			}, "html");
			
		});
		
	});
</script>
<script>
function fuzhishangpin(){
	var s_id=$("#store").val();
	var s_name=$("#store :selected").text();
	$("#motai4").empty();
	$.post("<%=basePath%>goods", {
		"m" : "fuzhishangpin",
		"s_id" :s_id,
		"s_name" :s_name  ,
	}, function(data) {
		$("#motai4").append(data);
	}, "html");
	
	
}
function daochu(){
	var s_id=$("#store").val();
	var s_name=$("#store :selected").text();
	$("#motai3").empty();
	$.post("<%=basePath%>goods", {
		"m" : "daochu",
		"s_id" :s_id,
		"s_name" :s_name  ,
	}, function(data) {
		$("#motai3").append(data);
	}, "html");
	
	
}
function DaoRu(){
	
	$("#motai1").empty();
	$.post("<%=basePath%>goods", {
		"m" : "daoru",
	}, function(data) {
		$("#motai1").append(data);
	}, "html");
	
	
}
//新增商品
function xinzengshangpin(){
	var message="";
	var s_id=$("#store").val();
	var s_name=$("#store :selected").text();
	if(store==null){
		alert("请重新选择店铺！");
		return;
	}
	$("#motai").empty();
	$.post("<%=basePath%>goods",{
		"m" : "addGoods",
		"s_id" :s_id,
		"s_name" :s_name  ,
		"message" :message,
	}, function(data) {
		$("#motai").append(data);
	}, "html");
	$("#BAOCUN").hide();
	$("#tuichu").hide();
}

</script>
</head>


<script>



function UP(node){
	 var key=$("#key").val();
	 var c_name=$("#fenlei").val();
	 var g_del=$("#g_del").val();
	 var s_id=$("#s_id").val();
	var sorted=$(node).val();
	var currentPage=$("#currentPage").val();
	$("#goodsinfodiv").empty();
	
	$.post("<%=basePath%>goods", {
		"m" : "upsort",
		"which" : "first",
		"s_id" : s_id,
		"sorted" : sorted,
		"key" : key,
		"c_name" : c_name,
		"g_del" : g_del,
		"currentPage" : currentPage,
	}, function(data) {
		$("#goodsinfodiv").append(data);
	}, "html");
}
function DOWN(node){
	 var key=$("#key").val();
	 var c_name=$("#fenlei").val();
	 var g_del=$("#g_del").val();
	var sorted=$(node).val();
	
	var s_id=$("#s_id").val();
	var currentPage=$("#currentPage").val();
	$("#goodsinfodiv").empty();
	
	$.post("<%=basePath%>goods", {
		"m" : "downsort",
		"which" : "first",
		"s_id" : s_id,
		"key" : key,
		"c_name" : c_name,
		"g_del" : g_del,
		"sorted" : sorted,
		"currentPage" : currentPage,
	}, function(data) {
		$("#goodsinfodiv").append(data);
	}, "html");
}
</script>
<script>
function up(node){
	 var key=$("#key").val();
	 var c_name=$("#fenlei").val();
	
	 var g_del=$("#g_del").val();
	var s_id=$("#s_id").val();
	var sorted=$(node).val();
	
	var currentPage=$("#currentPage").val();
	$("#goodsinfodiv").empty();
	$.post("<%=basePath%>goods", {
		"m" : "upsort",
		"which" : "first",
		"s_id" : s_id,
		"key" : key,
		"c_name" : c_name,
		"g_del" : g_del,
		"sorted" : sorted,
		"currentPage" : currentPage,
	}, function(data) {
		$("#goodsinfodiv").append(data);
	}, "html");
}
function down(node){
	 var key=$("#key").val();
	 var c_name=$("#fenlei").val();
	 var g_del=$("#g_del").val();
	var sorted=$(node).val();
	var s_id=$("#s_id").val();
	
	var currentPage=$("#currentPage").val();
	$("#goodsinfodiv").empty();
	$.post("<%=basePath%>goods", {
		"m" : "downsort",
		"which" : "first",
		"s_id" : s_id,
		"key" : key,
		"c_name" : c_name,
		"g_del" : g_del,
		"sorted" : sorted,
		"currentPage" : currentPage,
	}, function(data) {
		$("#goodsinfodiv").append(data);
	}, "html");
}
function detail(node){
	var list=$(node).parent().parent().find(".list").val();
	var s_id=$("#s_id").val();
   // var list=$("#list").val();
  // $("#tableContent").empty();
    $("#motai22").empty();
	$.post("<%=basePath%>goods", {
		"m" : "detail",
		"list" : list,
		"s_id" : s_id,
	}, function(data) {
		$("#motai22").append(data);
	}, "html");
}

function edit(node){
	var list=$(node).parent().parent().find(".list").val();
	var s_id=$("#s_id").val();
   // var list=$("#list").val();
  // $("#tableContent").empty();
    $("#motai2").empty();
	$.post("<%=basePath%>goods", {
		"m" : "editGood",
		"list" : list,
		"s_id" : s_id,
	}, function(data) {
		$("#motai2").append(data);
	}, "html");
}
function del(){
var s_id=$("#s_id").val();
var g_id=$("#g_id").val();
$("#goodsinfodiv").empty();

$.post("<%=basePath%>goods", {
	"m" : "deleteGood",
	"g_id" : g_id,
	"s_id" :s_id,
	"currentPage":"1",
	
	"g_del" :1,
	"c_name" :"无",
	
}, function(data) {
	$("#goodsinfodiv").append(data);
}, "html");
}

$(function(){
$("li a").click(function() {
	
	 var key=$("#key").val();
	 
	 var c_name=$("#fenlei").val();
	 
	 var g_del=$("#g_del").val();
	var m=$("#method").val();
	var sorted=$("#sorted").val();
	var which = $(this).text();
	var s_id=$("#s_id").val();
	if (which === "首页") {
		which="first";
	} else if (which == "上一页") {
		which="prev";
	} else if (which === "下一页") {
		which="next";
	} else if (which === "尾页") {
		which="last";
	} 
	var currentPage=$("#currentPage").val();
	
	$("#goodsinfodiv").empty();
	
	$.post("<%=basePath%>goods", {
		"which" : which,
		"s_id" : s_id,
		"key" : key,
		"c_name" : c_name,
		"g_del" : g_del,
		"m" : m,
		"sorted":sorted,
		"currentPage" : currentPage,
	}, function(data) {
		$("#goodsinfodiv").html(data);
	}, "html");
});
});
</script>
<body>
<div id="hehe">
<div class="panel panel-default">
		<div class="panel-footer">
				<div class="row">
				
				<div class="col-xs-1">
				<span class="badge pull-right" style="margin:10px ">商品资料</span>
				</div>
				<div class="col-xs-1">
				<a onclick="xinzengshangpin()" 
		data-toggle="modal" data-target="#myModal" >新增商品</a>
				</div>
				<div class="col-xs-1">
				<a data-toggle="modal" 
   data-target="#myModal1" onclick="DaoRu()">导入</a>
				</div>
				<div class="col-xs-1">
				 <a data-toggle="modal" 
   data-target="#myModal3" onclick="daochu()">导出</a>
				</div>
				<div class="col-xs-1">
				<a data-toggle="modal" 
   data-target="#myModal4" onclick="fuzhishangpin()">复制商品</a>
				</div>
				<div class="col-xs-1">
				<a id="kuaisu"
		name="submit" >快速录入</a>
				</div>
				
		<div class="conditionNav" style="float: left;padding-top: 20px">
				<div class="col-xs-3">
				<select id="store" class="form-control">
		<!-- <option value="-1" selected="selected" disabled="disabled">选择店铺</option> -->

		<%
			List<Object[]> list2 = (List<Object[]>) request.getAttribute("storeList");
			if (list2 != null && list2.size() != 0) {
				for (Object[] obj : list2) {
		%>
		<option value='<%=obj[0]%>'><%=obj[1]%></option>
		<%
			}
			}
		%>

	</select>
				</div>
				<div class="col-xs-3">
				<select id="g_del"  class="form-control">
		<option value="1" selected="selected">有效单据</option>
		<option value="0">作废单据</option>
	</select>
				</div>
				<div class="col-xs-3">
				<select id="c_name" class="form-control">

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
</select>
				</div>
				
				
			
		<div class="col-xs-2">	
		<input id="key"  type="text"  class="form-control"/> 
			</div>
	
			<div class="col-xs-1">	
			<button type="button" id="search"  class="btn btn-default">查询</button>
			</div>	
	</div>			
				
</div>
	</div>
	</div>
	

	

	
	
	
     <div data-spy="scroll"
		style="width:1230px;height:500px; overflow: auto; position: relative;"
		data-offset="10">
<div id="SF">
<div id="goodsinfodiv" >
<%

String currentPage=request.getAttribute("currentPage").toString();
String key=request.getAttribute("key").toString();
String c_name=request.getAttribute("c_name").toString();

String g_del=request.getAttribute("g_del").toString();
String method=application.getAttribute("method").toString();
  
%>
		<table class="table table-bordered table-condensed table-hover table-striped ">
		<thead>
			<tr class="default">
				<th>操作</th>
				<th>商品名称</th>
				<th>所属门店</th>
				<th>商品条码</th>
				<th>库存量<button onclick="UP(this)" class=" btn btn-success btn-xs" value="g_stock_num">&uarr;</button><button onclick="DOWN(this)" class=" btn btn-success btn-xs" value="g_stock_num">&darr;</button></th>
				<th>进货价<button onclick="up(this)" class=" btn btn-success btn-xs" value="g_pur_price">&uarr;</button><button onclick="down(this)" class=" btn btn-success btn-xs" value="g_pur_price">&darr;</button></th>
				<th>销售价</th>
				<th>批发价</th>
				<th>分类</th>
				<th>详细</th>
				
				
			</tr>
		</thead>
		<tbody>
			<%
			List<Object[]> goods = (List<Object[]>) request.getAttribute("goodsList");
			
				String s_id = request.getParameter("s_id").toString();
				String yanse[]={"success","danger","active","warning","info"};
				if (goods != null && goods.size() > 0) {
					for (int i = 0; i < goods.size(); i++) {
					
						String list="";   //如果此次用逗号会引起按钮不能触发的BUG
		               
		                	for (int j = 0; j <35; j++) {
		                		if(String.valueOf(goods.get(i)[j]).equals(""))
		                		{list=list+" ,";
		                		
		                		}else{
		                		list=list+String.valueOf(goods.get(i)[j])+",";
		                		}
		                }
					
					%>
					
					<input type="hidden" id="g_id" value="<%=goods.get(i)[0]%>">
			<tr class="default">
				<td>
				<a   onclick="del()">删除</a>		
				<a  data-toggle="modal" data-target="#myModal2" onclick="edit(this)">编辑</a>
					<input type="hidden" class="list" value="<%=list%>">
				<%
				
					for (int j = 1; j <9; j++) {
						if(String.valueOf(goods.get(i)[j]).equals("null")) goods.get(i)[j]="";
				%>
				
				<td><%=goods.get(i)[j]%> </td>
				<%
					}
				%>
				<td><a  data-toggle="modal" data-target="#myModal22" onclick="detail(this)">详细</a></td>
				
			</tr>
			<%
				}
				}
			%>
		</tbody>
	</table>
	<input type="hidden" id="s_id" value="<%=s_id%>" />
<input type="hidden" id="key" value="<%=key%>" />
<input type="hidden" id="fenlei" value="<%=c_name%>" />
<input type="hidden" id="g_del" value="<%=g_del%>" />
<input type="hidden" id="method" value="${method }" />
<input type="hidden" id="sorted" value="${sorted }" />
<input type="hidden" id="currentPage" value="<%=currentPage%>" />
<ul class="pagination" id="page" style="position: absolute; bottom: 70px;">
	<page:htmlPage  pageNo="${currentPage}"
		url=""
		totalSum="${totalSize}" showPage="10" pageSize="10" />
</ul>
		</div>

</div>
	</div>
</div>

</body>

</html>




<!-- 模态框  新增（Modal） -->
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
           <button type="button" id="BAOCUN" onclick="save()" style="display:none;"  class="btn btn-primary" 
              >保存
            </button>
           
             <button type="button"  id="tuichu" style="display:none;"  class="btn btn-success" 
               data-dismiss="modal">退出
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>



<!-- 模态框  导入（Modal） -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button>
         </div>
            
      <!-- 模态框本质内容 --> 
      <div id="motai1">
   </div>    
     <!-- 模态框本质内容 -->   
         <div class="modal-footer">
          
           
             <button type="button"   class="btn btn-success" 
               data-dismiss="modal">退出
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<!-- 模态框  编辑（Modal） -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button>
         </div>
            
      <!-- 模态框本质内容 --> 
      <div id="motai2">
   </div>    
     <!-- 模态框本质内容 -->   
         <div class="modal-footer">
          
            <button type="button" onclick="save1()"  class="btn btn-primary" 
              >保存
            </button>
             <button type="button"  id="tuichu2" class="btn btn-success" 
               data-dismiss="modal">退出
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<!-- 模态框  详细（Modal） -->
<div class="modal fade" id="myModal22" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button>
         </div>
            
      <!-- 模态框本质内容 --> 
      <div id="motai22">
   </div>    
     <!-- 模态框本质内容 -->   
         <div class="modal-footer">
          
           <!--  <button type="button" onclick="save1()"  class="btn btn-primary" 
              >保存
            </button>
             <button type="button"  id="tuichu2" class="btn btn-success" 
               data-dismiss="modal">退出
            </button> -->
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<!-- 模态框  导出（Modal） -->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button>
         </div>
            
      <!-- 模态框本质内容 --> 
      <div id="motai3">
   </div>    
     <!-- 模态框本质内容 -->   
         <div class="modal-footer">
          
            <!-- <button type="button" onclick="save1()"  class="btn btn-primary" 
              >保存
            </button>
             <button type="button"  id="tuichu2" class="btn btn-success" 
               data-dismiss="modal">退出
            </button> -->
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

<!-- 模态框  复制（Modal） -->
<div class="modal fade" id="myModal4" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times; </button>
         </div>
            
      <!-- 模态框本质内容 --> 
      <div id="motai4">
   </div>    
     <!-- 模态框本质内容 -->   
         <div class="modal-footer">
          
            <!-- <button type="button" onclick="save1()"  class="btn btn-primary" 
              >保存
            </button>
             <button type="button"  id="tuichu2" class="btn btn-success" 
               data-dismiss="modal">退出
            </button> -->
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>

