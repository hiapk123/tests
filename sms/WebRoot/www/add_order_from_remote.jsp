<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>www/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>www/themes/icon.css">
<link href="<%=basePath%>www/css/bootstrap.min.css" rel="stylesheet">


<script type="text/javascript" src="<%=basePath%>www/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>www/jquery.easyui.min.js"></script>
<script src="<%=basePath%>www/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>www/js/datagrid-cellediting.js"></script>
<script type="text/javascript">
	var ids=new Array();
	var nums=new Array();
	var sum=0;
	$(function(){

		$.get('<%=basePath %>initTree?m=initS',null,function(stores){
			for(var i=0;i<stores.length;i++){
				$('#store').empty();
				$('#store').append("<option value=0 selected>请选择店铺</option>");
				$('#store').append("<option value=\'"+stores[i].id+"\'>"+stores[i].name+"</option>");
			}
		},"json");
		
		$('#tt').tree({
		    url:'<%=basePath%>initTree?m=getTreeData',
		    onClick: function(node){
	 			//alert(node.text);
	    		$('#dg').datagrid({
	    		    url:'<%=basePath%>addOrder',
	    		    queryParams: {
	    		    	m:'getGoodsByCategory',
	        	        number:1,
	        	        c:node.text
	    			},
	    		    method:"POST",
	    			isField:"itemid",  
					title:"选择商品", 
				 	pagination:true,//显示分页  
		            fit:false,//自动补全  
		            fitColumns:true,  
		            iconCls:"icon-edit",//图标  
		            width: 'auto', 
		            height: 'auto', 
		            nowrap: false, 
		            striped: true, 
		            border: true, 
		            collapsible:false,//是否可折叠的 
		            loadMsg:'加载中...',
		            singleSelect:false,//是否单选 
	    			columns:[[
	    						 {field:'itemid',title:'商品条码',width:150,sortable:true,checkbox:true},
	    						 {field:'itemname',title:'商品名称',width:350},
	    						 {field:'itemprice1',title:'商品进价',width:100,sortable:true},
	    						 {field:'itemprice2',title:'商品售价',width:100,sortable:true},
	    						 {field:'itemcategory',title:'商品分类',width:100},
	    						 {field:'itemnum',title:'数量',width:100,editor: { type: 'combobox', options: { 
		    						 required: true,
	    							 valueField:'numId',
	                                 textField:'numValue',
	                                 url:'<%=basePath%>www/number.json',
		    						 } }}
	    						 ]
	    					],toolbar: [{ 
	    		                text: '添加到订单', 
	    		                iconCls: 'icon-add', 
	    		                handler: function() { 
		    		                var rows=$('#dg').datagrid('getChecked');
		    		                if(null==rows||rows.length==0){
										alert("您未选中任何行！");
										return;
			    		            }

									var orderTable=$('#order');
									var table="<table class=\"table\">"+
									"<tr>"+
									"<th>商品编号</th>"+
									"<th>商品名称</th>"+
									"<th>商品进价</th>"+
									"<th>商品售价</th>"+
									"<th>商品分类</th>"+
									"<th>商品数量</th>"+
									"</tr>"
			    		            for(var i=0;i<rows.length;i++){
				    		            var id=new Array();
				    		            var num=new Array();
				    		            
			    		            	var row =rows[i];
		    		                	var gno=row.itemid;
		    		                	var gname=row.itemname;
		    		                	var gprice1=row.itemprice1;
		    		                	var gprice2=row.itemprice2;
		    		                	var gcategory=row.itemcategory;
		    		                	var gnum=row.itemnum;

		    		                	sum+=(gprice1*gnum);
			    		                
		    		                	table+=("<tr>");
		    		                	table+=("<td>"+gno+"</td>");
		    		                	table+=("<td>"+gname+"</td>");
		    		                	table+=("<td>"+gprice1+"</td>");
		    		                	table+=("<td>"+gprice2+"</td>");
		    		                	table+=("<td>"+gcategory+"</td>");
		    		                	table+=("<td>"+gnum+"</td>");
		    		                	table+=("</tr>");

		    		                	id.push(gno);
		    		                	num.push(gnum);
										//所有选中的编号和数量
		    		               		ids=ids.concat(id);
		    		               		nums=nums.concat(num);

		    		               		$('#info').html('总计：'+sum.toFixed(2)+'元');
		    		      
				    		        }

									table+="</table>"

									orderTable.append(table);
	    		                } 
	    		            },{'text':'添加完成',
	    		            	iconCls: 'icon-save', 
	    		                handler: function(){
									//处理ids和nums对应的列表
									if(0==$('#store').val()){
										alert("请选择要订货的店铺！");
										return;
									}

									if(null==ids||ids.length==0){
										return;
									}
									$.post('<%=basePath%>addOrder',{'m':'add','ids':ids.toString(),'nums':nums.toString(),'store':$('#store').val()},
									function(data)
									{
										if(data.flag=='ok'){
											alert('提交完成！');
										}
										$('#order').empty();
										ids=new Array();
										nums=new Array();
										$('#dg').datagrid('reload');
										$('#info').html('总计：0.00元');
									},'json');
		    		            }
		    		        }],
	    		           
	    		});
				//设置分页控件 
			    var p = $('#dg').datagrid('getPager'); 
			  	//console.info(p);
			    $(p).pagination({ 
			        pageSize: 10,//每页显示的记录条数，默认为10 
			        pageList:[10],
			        beforePageText: '第',//页数文本框前显示的汉字 
			        afterPageText: '页    共 {pages} 页', 
			        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
			        onBeforeRefresh:function(){
			        	$('#dg').datagrid('reload');
			        },onChangePageSize:function(){  
			              
			        }, 
			        onSelectPage:function(pageNumber,pageSize){  

			        	$.post("<%=basePath%>addOrder",
					       {m:'getGoodsByCategory',
		        	        number:pageNumber,
		        	        c:$('#tt').tree('getSelected').text
		        	       },
		        	    function(data){
							$('#dg').datagrid('loadData', data);
				        },"json");
				        
			       		<%-- 
			        	$('#dg').datagrid({
			        	    url:'<%=basePath%>addOrder',
			        	    queryParams:{
			        	        m:'findByPage',
			        	        number:pageNumber
			        	    },
			        		
			        	}); --%>
			        }
			    }); 

			    $('#dg').datagrid('enableCellEditing').datagrid('editCell', {
	                index: 5,
	                field: 'itemnum'
	            });
	    	}
		});

		
	});
</script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',title:'订单列表',split:true" style="height:200px;">
		<h3 id='info'>总计：0元</h3>
		<div id="order"></div>
	</div>
	<div data-options="region:'west',title:'分类列表',split:true"
		style="width: 200px;">
		<ul id="tt" >
			
		</ul>
	</div>
	<div data-options="region:'center',title:'详细'"
		style="padding: 5px; background: #eee;">
		<select id="store"></select>
		<table id="dg"></table>	
	</div>
</body>
</html>