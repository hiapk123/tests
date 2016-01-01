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
		                	var row = $('#dg').datagrid('getSelected');
		                	var itemid=row.itemid;//获取商品条码
		                	if(null==itemid){
								alert("请选择要编辑的行！");
								return;
			                }
							$('#edit_no').val(row.itemid);
							$('#edit_name').val(row.itemname);
							$('#edit_price1').val(row.itemprice1);
							$('#edit_price2').val(row.itemprice2);
							$('#edit_category').val(row.itemcategory);
							$('#myEditModal').modal({keyboard: false});
			                
		                } 
		            }, '-',{ 
		                text: '删除', 
		                iconCls: 'icon-remove', 
		                handler: function(){ 
		                	//获取所有选中的行
		                	var rows = $('#dg').datagrid('getSelections');
		                	if(null==rows){
								return;
			                }

			                if(rows.length>0){
				                var itemids="";
								for(var i=0;i<rows.length;i++){
									itemids+=rows[i].itemid+",";
								}

								$.post('<%=basePath%>addOrder',{'m':'del','itemids':itemids},function(data){
									if(data.msg=='ok')
										$('#dg').datagrid('reload');
				                },'json');
					        }
		                	
		                } 
		            }], 
				columns:[[
					 {field:'itemid',title:'商品条码',width:150,sortable:true},
					 {field:'itemname',title:'商品名称',width:450,sortable:true},
					 {field:'itemprice1',title:'商品进价',width:150,sortable:true},
					 {field:'itemprice2',title:'商品售价',width:150,sortable:true},
					 {field:'itemcategory',title:'商品分类',width:150,sortable:true}
					 ]
				],
				onClickCell: function(rowIndex, field, value){
					
				}
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
				       {m:'findByPage',
	        	        number:pageNumber
	        	       },
	        	    function(data){
						$('#dg').datagrid('loadData', data);
			        },"json");
			        
		       		
		        }
		    }); 

		    $('#edit_btn').click(function(){
			    //var form=$('#edit_form').serialize();
			    //console.info(form);
			   
		    	//根据条码查询对应的信息
            	 $.ajax({
            		async:true,
            		type:"POST",
            		url:"<%=basePath%>addOrder",
            		//PUT方法不支持这种方式传值
            		data:{"gno":$('#edit_no').val(),"gname":$('#edit_name').val(),gprice1:$('#edit_price1').val(),"gprice2":$('#edit_price2').val(),"gcategory":$('#edit_category').val(),"m":"PUT"},
            		dataType:'json',
            		error:function(data){

                	},
                	success:function(data){
                    	//console.info(data);
						if(data.msg=='ok'){
							$('#myEditModal').modal('hide')
							$('#dg').datagrid('reload');
						}
	                },
	                complete:function(data){

		            },
	                timeout:3000
                }); 
			});

		    $('#add_btn').click(function(){
				$.post('<%=basePath%>addOrder',{"gno":$('#add_no').val(),"gname":$('#add_name').val(),gprice1:$('#add_price1').val(),"gprice2":$('#add_price2').val(),"gcategory":$('#add_category').val(),"m":"addGoods"},function(data){
					if(data.msg=='ok'){
						$('#myModal').modal('hide')
						$('#dg').datagrid('reload');
					}
				},"json");
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
         <input type="text" class="form-control" id="add_no"
            placeholder="请输入商品条码">
      </div>
   </div>
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">商品名称</label>
      <div class="col-sm-10">
         <input type="text" class="form-control"  id="add_name"
            placeholder="商品姓名">
      </div>
   </div>
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">进价</label>
      <div class="col-sm-10">
         <input type="text" class="form-control"  id="add_price1"
            placeholder="请输入进价">
      </div>
   </div>
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">售价</label>
      <div class="col-sm-10">
         <input type="text" class="form-control" id="add_price2"
            placeholder="请输入售价">
      </div>
   </div>
   
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">分类</label>
      <div class="col-sm-10">
         <input type="text" class="form-control" id="add_category"
            placeholder="请输入售价">
      </div>
   </div>
   
</form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" id="add_btn">
               提交更改
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal --></div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myEditModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               编辑商品
            </h4>
         </div>
         <div class="modal-body">
            <form class="form-horizontal" role="form" id='edit_form'>
   <div class="form-group">
      <label for="firstname" class="col-sm-2 control-label">商品条码</label>
      <div class="col-sm-10">
         <input type="text" disabled="disabled" class="form-control" id="edit_no" name="edit_no"
            placeholder="请输入商品条码">
      </div>
   </div>
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">商品名称</label>
      <div class="col-sm-10">
         <input type="text" class="form-control"  id="edit_name" name="edit_name"
            placeholder="商品姓名">
      </div>
   </div>
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">进价</label>
      <div class="col-sm-10">
         <input type="text" class="form-control"  id="edit_price1" name="edit_price1"
            placeholder="请输入进价">
      </div>
   </div>
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">售价</label>
      <div class="col-sm-10">
         <input type="text" class="form-control" id="edit_price2" name="edit_price2"
            placeholder="请输入售价">
      </div>
   </div>
   
   <div class="form-group">
      <label for="lastname" class="col-sm-2 control-label">分类</label>
      <div class="col-sm-10">
         <input type="text" class="form-control" id="edit_category" name="edit_category"
            placeholder="请输入商品分类">
      </div>
   </div>
   
</form>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" class="btn btn-primary" id="edit_btn">
               提交更改
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal --></div>
</body>
</html>