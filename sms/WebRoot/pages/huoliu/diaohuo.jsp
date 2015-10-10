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
.fileReport{
width: 342px;
height: 80px;
padding: 5px 8px;
margin: 10px 0px 20px;
background: #FFF none repeat scroll 0% 0%;
border: 1px solid #CCC;
font-size: 12px;
color: #666;
line-height: 18px;
outline: medium none;
resize: none;
border-radius: 2px;

}
.item{
width: 320px;

margin: 10px 0px;
border: 1px solid #CCC;
border-radius: 2px;
font-size: 14px;
text-align: left;
transition: all 0.5s ease 0s;
background: #FFF none repeat scroll 0% 0%;
}

#Layer1{ display: block;  position: absolute;  top: 0%;  left: 0%;  width: 100%;  height: 100%;  background-color: white;  z-index:1001;  -moz-opacity: 0.7;  opacity:.70;  filter: alpha(opacity=80);}
</style>

<script type="text/javascript">


function Inforuku()
{   var s_id_out=$("#s_id_out").val();
    var s_id_in=$("#s_id_in").val();
    alert(s_id_in);
     var l_info=$("#txt_remarks").val();
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
		
		"l_info":l_info,
	}, function(data) {
		$("#hlgldiv").append(data);
	}, "html");
}
function querenjinhuo()
{    
	$("#ruku").css('display','block');
	
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
$("#mm").remove();
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
   $("#queren").click(function(){
	var s_id_out=$("#s_id_out").val();
	var s_id_in=$("#s_id_in").val();
	var s_name=$("#store :selected").text();
	$("#1").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "xzmd1",
		"s_name":s_name,
		"s_id_out":s_id_out,
		"s_id_in":s_id_in,
	}, function(data) {
		$("#1").append(data);
	}, "html");
	$("#Layer1").hide();
  });
$("#search").click(function(){
	var s_id_out=$("#s_id_out").val();
	var s_id_in=$("#s_id_in").val();
	var shuru=$("#shuru").val();
	
	//$("#goodtable").empty();
	
	$.post("<%=basePath%>huoliu", {
		"m" : "diaohuo1",
		"s_id_out":s_id_out,
		"s_id_in":s_id_out,
		"shuru":shuru,
	}, function(data) {
		$("#goodtable").append(data);
	}, "html");
	
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

<div id="goodtable" >
</div>
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
<div id="ruku" style="display:none; margin: 80px auto; width: 360px;  height: 250px; border: 5px solid #458B74;">
<center>

      <p>备注:  </p>      <textarea class="fileReport" id="txt_remarks"></textarea>
<button   onclick="Inforuku()" type="button" class="btn btn-success"  style="width:150px" name="submit"
			>通知收银端入库</button> 

			
	</center>			
</div>
<div id="dr">
</div>	
	<div id="ly" style="position: absolute; bottom: 1400px;">
		<button type="button" id="daoru" class="btn btn-danger btn-lg" name="submit"
			 style="position: relative; bottom: 0px;">导入</button>
		<input style="height: 50px; width: 200px" type="text"  id="shuru" placeholder="请输入拼音码/名称/">
<button type="button" class="btn btn-danger btn-lg" name="submit"
	id="search"  style="position: relative; bottom: 0px;">搜索</button>
	<button type="button" class="btn btn-success btn-lg" name="submit"
			onclick="querenjinhuo()" style="margin: 0 0 0 700px">确认出货</button>	
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
		
		<button type="button" class="btn btn-success btn-block " name="submit" id="queren">确认</button>
	</div>
	</div>
	
</body>
</html>
