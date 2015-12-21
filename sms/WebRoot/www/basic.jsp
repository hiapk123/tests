<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<HTML>
<HEAD>
	<TITLE> ZTREE DEMO - beforeClick / onClick</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="www/css/demo.css" type="text/css">
	<link rel="stylesheet" href="www/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript" src="www/js/jquery.ztree.core-3.5.js"></script>
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet" href="www/css/bootstrap.min.css">
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="www/js/bootstrap.min.js"></script>
	
	<style type="text/css">
		.mytable{
			width:800px;
			height:350px;
			font-family: verdana,arial,sans-serif;
			font-size:11px;
			color:#333333;
			border-width: 3px;
			border-color: #666666;
			border-collapse: collapse;
		}
		.mytable.td{
			border-width: 1px;
			padding: 8px;
			border-style: solid;
			border-color: #999999;
		}
	</style>
	
	<SCRIPT type="text/javascript">
		var setting = {
			data: {
				key: {
					title:"t"
				},
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};

		var CID=0;
		
		function beforeClick(treeId, treeNode, clickFlag) {
			/* className = (className === "dark" ? "":"dark");
			showLog("[ "+getTime()+" beforeClick ]&nbsp;&nbsp;" + treeNode.name ); */
			return (treeNode.click != false);
		}
		function onClick(event, treeId, treeNode, clickFlag) {
			var id=(treeNode.id);
			CID=id;
			var sid=$('#store').val();
		
			$.post("<%=basePath%>initTree?m=initG",{"id":id,"sid":sid,"pageNo":1},
			function(goods){
				setContent(goods);
			},"json");
		
		}		

		function checkAll(){
			var checkAll=document.getElementById("checkAll");

			var status=checkAll.checked;
			var checkbox=document.getElementsByName("mycheckbox");

			if(status){
				for(var i=0;i<checkbox.length;i++){
					checkbox[i].checked=true;
				}
			}else{
				for(var i=0;i<checkbox.length;i++){
					checkbox[i].checked=false;
				}
			}
		}

		function uncheck(){
			document.getElementById("checkAll").checked=false;
		}
		//添加商品按钮，成功之后返回商品条形码的数组。
		idsList=new Array();
		numsList=new Array();
		function addGoods(){
			var checkbox=document.getElementsByName("mycheckbox");
			var barcodes=new Array();
			var nums=new Array();
			var index=0;
			for(var i=0;i<checkbox.length;i++){
				if(checkbox[i].checked){
					var id="#text"+i;
					barcodes[index]=checkbox[i].value;
					nums[index]=$(id).val();
					index++;
				}
			}
			//添加到后台
			ajaxSubmit(barcodes,nums);
			idsList=idsList.concat(barcodes);
			numsList=numsList.concat(nums);
			console.info(idsList);
			console.info(numsList);
		}
		
		//ajax提交按钮
		function ajaxSubmit(array1,array2){
			//实现你的方法
			//$('#content').empty();
			//alert("实现你的提交函数！");
			for(var i=0;i<array1.length;i++){
				$('#content').append("<span>("+array1[i]+"&nbsp,&nbsp;"+array2[i]+")</span>");
			}
		}

		function sub(s){
			
			switch(s){
			case 0:
				var cur=$('#text0').val();
				cur--;
				if(cur<1){
					cur=1;
				}
				$('#text0').val(cur);
				break;
			case 1:
				var cur=$('#text1').val();
				cur--;
				if(cur<1){
					cur=1;
				}
				$('#text1').val(cur);
				break;
			case 2:
				var cur=$('#text2').val();
				cur--;
				if(cur<1){
					cur=1;
				}
				$('#text2').val(cur);
				break;
			case 3:
				var cur=$('#text3').val();
				cur--;
				if(cur<1){
					cur=1;
				}
				$('#text3').val(cur);
				break;
			case 4:
				var cur=$('#text4').val();
				cur--;
				if(cur<1){
					cur=1;
				}
				$('#text4').val(cur);
				break;
			}
		}

		function add(s){
			switch(s){
			case 0:
				var cur=$('#text0').val();
				cur++;
				if(cur<1){
					cur=1;
				}
				$('#text0').val(cur);
				break;
			case 1:
				var cur=$('#text1').val();
				cur++;
				if(cur<1){
					cur=1;
				}
				$('#text1').val(cur);
				break;
			case 2:
				var cur=$('#text2').val();
				cur++;
				if(cur<1){
					cur=1;
				}
				$('#text2').val(cur);
				break;
			case 3:
				var cur=$('#text3').val();
				cur++;
				if(cur<1){
					cur=1;
				}
				$('#text3').val(cur);
				break;
			case 4:
				var cur=$('#text4').val();
				cur++;
				if(cur<1){
					cur=1;
				}
				$('#text4').val(cur);
				break;
			}
		}
		
		function setContent(goods){
			
			$('#footer').show();
			try{
				if(goods.flag==0){
					$('#div1').html("<p>没有要显示的商品列表。</p>");
					$('#footer').hide();
					return;
				}
			}catch (e) {
				
			}
			var tableStr=" <table class=\"mytable\">";
			tableStr+="<thead><tr><th><input type=\"checkbox\" id=\"checkAll\" onclick='checkAll();'></th>"+
					
				     "<th>名称</th>"+
				     "<th>条码</th>"+
				     "<th>售价</th>"+
				     "<th>日期</th>"+
				     "<th>数量</th>"+
				     "</tr>"+
				   "</thead>";
			tableStr+="<tbody>";
		
			for(var i=0;i<goods.length-1;i++){
				var id="text"+i;
				tableStr+="<tr>";
				tableStr+="<td><input type=\"checkbox\" value=\""+goods[i].barcode+"\" name='mycheckbox' onclick='uncheck();' ></td>";
				tableStr+="<td>"+goods[i].name+"</td>";
				tableStr+="<td>"+goods[i].barcode+"</td>";
				tableStr+="<td>"+goods[i].price+"</td>";
				tableStr+="<td>"+goods[i].date+"</td>";
				tableStr+="<td>"+
				"<a onclick=\"sub("+i+");\" >"+
		        "<span class=\"glyphicon glyphicon-minus\"></span>"+
		        "</a>"+
				"<input id=\""+id+"\" "+
				"type=\"text\" class=\"input-small\" placeholder='number' size='3' value='1' style='margin:2px;' />"+
				"<a onclick=\"add("+i+");\">"+
		        "<span class=\"glyphicon glyphicon-plus\"></span>"+
		        "</a>"+
				"</td>";
				tableStr+="</tr>";
			}

			tableStr+="</tbody></table>";
			
			$('#div1').html(tableStr);

			//设置总页数和当前页数
			var pageObject=goods[goods.length-1];
			var totalSum=pageObject.totalSum;
			var pageNo=pageObject.pageNo;

			$('#info').val("第"+pageNo+"页/共"+totalSum+"页");			
			
		
		}
		$(document).ready(function(){	
			//初始化门店
			$('#footer').hide();
			$.get('<%=basePath %>initTree?m=initS',null,function(stores){
					for(var i=0;i<stores.length;i++){
						$('#store').empty();
						$('#store').append("<option value=0 selected>请选择店铺</option>");
						$('#store').append("<option value=\'"+stores[i].id+"\'>"+stores[i].name+"</option>");
					}
			},"json");

			$('#store').change(function(){
				var id=$(this).val();
				if(id!=0){
					$.post("<%=basePath%>initTree?m=initT",{"id":id},function(zNodes2){
						$.fn.zTree.init($("#treeDemo"), setting, zNodes2);
					},"json");
					 
			    }
			});

			$('#first').click(function(){
				var sid=$('#store').val();
				$.post("<%=basePath%>initTree?m=findByPage",{"sid":sid,"which":"first","id":CID},
				function(goods){
					setContent(goods);
				},"json");
			});

			$('#next').click(function(){
				var sid=$('#store').val();
				$.post("<%=basePath%>initTree?m=findByPage",{"sid":sid,"which":"next","id":CID},
				function(goods){
					setContent(goods);
				},"json");
			});

			$('#prev').click(function(){
				var sid=$('#store').val();
				$.post("<%=basePath%>initTree?m=findByPage",{"sid":sid,"which":"prev","id":CID},
				function(goods){
					setContent(goods);
				},"json");
			});

			$('#last').click(function(){
				var sid=$('#store').val();
				$.post("<%=basePath%>initTree?m=findByPage",{"sid":sid,"which":"last","id":CID},
				function(goods){
					setContent(goods);
				},"json");
			});

			$('#submit').click(function(){
			
				if(idsList.length==0||numsList.length==0||(idsList.length!=numsList.length)){
					return;
				}
			
				$.post("<%=basePath%>addOrder?m=add",{"ids":idsList.toString(),"nums":numsList.toString(),'store':$('#store').val()},function(result){
						if(result.flag==='ok'){
							alert("已提交！");
							
						}else{
							alert("选择错误！");
							
						}
						idsList=new Array();
						numsList=new Array();
						$('#content').empty();
				},"json");
			});
		});

	</SCRIPT>
</HEAD>

<BODY>
<div class="panel-default">
   <div class="panel-heading">
      <input type="button" class="btn btn-primary" value="提交订单" id='submit'/>
   </div>
   <div class="panel-body">

	<div class="content_wrap">
		<div class="zTreeDemoBackground left">
			<select id="store"></select>
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div class="right">
			<div class="panel" id="div1">
				<p>没有要显示的商品列表。</p>
			</div>
			
			<div class="panel" style="width:800px;" id="footer">
				<input type="button" class="btn" value="添加" onclick="addGoods();">
				<input type="button" class="btn btn-link" value="首页" id="first">
				<input type="button" class="btn btn-link" value="上一页" id="prev">
				<input type="button" class="btn btn-link" value="下一页" id="next">
				<input type="button" class="btn btn-link" value="尾页" id="last">
				
				<input type="button" class="btn btn-link" value="第1页/共10页" onclick="javascript:void(0);" id="info">
			
				<!-- <input type="text" class="input-mini" placeholder="Number" size="3" id="goto_text">
				<input type="button" class="btn" value="跳转" id="goto_btn"> -->
			</div>
		</div>
	
	</div>
   	
   </div>
   		
	
</div>
</BODY>
</HTML>