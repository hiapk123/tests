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
	<link rel="stylesheet" type="text/css" href="<%=basePath %>www/themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>www/themes/icon.css">
	<link href="<%=basePath %>www/css/bootstrap.min.css" rel="stylesheet">
 
 
	<script type="text/javascript" src="<%=basePath %>www/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>www/jquery.easyui.min.js"></script>
	<script src="<%=basePath %>www/js/bootstrap.min.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$('#dg').datagrid({
				url:"<%=basePath%>addOrder?m=initGood",
					isField:"itemid",  
					title:"商品管理", 
				 	pagination:true,//显示分页  
		            fit:false,//自动补全  
		            fitColumns:false,  
		            iconCls:"icon-edit",//图标  
		            width: 'auto', 
		            height: 'auto', 
		                    nowrap: false, 
		                    striped: true, 
		                    border: true, 
		                    collapsible:false,//是否可折叠的 
		            loadMsg:'加载中...',

		            singleSelect:false,//是否单选 
		            frozenColumns:[[ 
		                            {field:'itemid',checkbox:true} 
		                        ]],
		             
		            toolbar: [{ 
		                text: '添加', 
		                iconCls: 'icon-add', 
		                handler: function() { 
		                    	$('#myModal').modal({
		                    	keyboard: false
		                    	})
		                } 
		            }, '-', { 
		                text: '修改', 
		                iconCls: 'icon-edit', 
		                handler: function() { 
		                     
		                } 
		            }, '-',{ 
		                text: '删除', 
		                iconCls: 'icon-remove', 
		                handler: function(){ 
		                    delAppInfo(); 
		                } 
		            }], 
				columns:[[
					 {field:'itemid',title:'商品条码',width:150,sortable:true,editor:'text'},
					 {field:'itemname',title:'商品名称',width:450,sortable:true},
					 {field:'itemprice1',title:'商品进价',width:150,sortable:true},
					 {field:'itemprice2',title:'商品售价',width:150,sortable:true},
					 {field:'itemscale',title:'商品规格',width:150,sortable:true}
					 ]
				],
				onClickCell: function(rowIndex, field, value){
					
				}
			});

			//设置分页控件 
		    var p = $('#dg').datagrid('getPager'); 
		  	console.info(p);
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
				       {m:'findByPage',
	        	        number:pageNumber
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
		});
	</script>
	
</head>
<body>
	<span class="label label-primary">商品资料操作面板</span>
	<div style="margin:20px 0;"></div>
	
	<table id="dg" data-options="pagination:true" class="easyui-datagrid"></table>
	
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               添加商品
            </h4>
         </div>
         <div class="modal-body">
            <form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-sm-2 control-label">商品条码</label>
      <div class="col-sm-10">
         <input type="text" class="form-control" 
            placeholder="请输入商品条码">
      </div>
   </div>
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">商品名称</label>
      <div class="col-sm-10">
         <input type="text" class="form-control"  
            placeholder="商品姓名">
      </div>
   </div>
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">进价</label>
      <div class="col-sm-10">
         <input type="text" class="form-control"  
            placeholder="请输入进价">
      </div>
   </div>
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">售价</label>
      <div class="col-sm-10">
         <input type="text" class="form-control" 
            placeholder="请输入售价">
      </div>
   </div>
   
</form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary">
               提交更改
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal --></div>
</body>
</html>