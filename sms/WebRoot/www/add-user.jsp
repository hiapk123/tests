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
<title>用户管理</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>www/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>www/themes/icon.css">
<link href="<%=basePath%>www/css/bootstrap.min.css" rel="stylesheet">

<script type="text/javascript" src="<%=basePath%>www/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>www/jquery.easyui.min.js"></script>

<script src="<%=basePath%>www/js/easyui-lang-zh_CN.js"></script>

<!-- 注册页面js文件 -->
<script type="text/javascript" src='<%=basePath%>js/regist.js'></script>
</head>
<body>

	<table id="dg" title="用户管理" style="width: auto; height: auto"
		data-options="
				iconCls: 'icon-edit',
				singleSelect: true,
				url: '<%=basePath%>login?m=findUserByPage',
				method:'post'">
		<thead>
			<tr>
				<th data-options="field:'username',sortable:true,width:80">用户名</th>
				<th data-options="field:'phone',editor:'text',width:100">手机号</th>
				<th data-options="field:'email',editor:'text',width:100">邮箱</th>
				<th data-options="field:'type',width:80">用户类型</th>
				<th
					data-options="field:'status',align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">选择</th>
			</tr>
		</thead>
	</table>

	<div id="win" class="easyui-window" title="添加用户"
		style="width: 600px; height: 400px"
		data-options="iconCls:'icon-save',modal:true,closed:true">
		<form id="ff" method="post">
		<input type="hidden" name="m" value="addUser">
			<table class="table">
				<tr>
					<td>请输入姓名</td>
					<td><input class="easyui-validatebox"
					type="text" name="name" data-options="required:true" /></td>
				</tr>
				<tr>
					<td>输入性别</td>
					<td><input class="easyui-validatebox"
					type="text" name="sex" value="男"/></td>
				</tr>
				<tr>
					<td>输入年龄</td>
					<td><input class="easyui-validatebox"
					type="number" name="age" value="20"/></td>
				</tr>
				<tr>
					<td>请输入密码</td>
					<td><input class="easyui-validatebox"
					type="password" name="password" data-options="required:true" /></td>
				</tr>
				<tr>
					<td>再次输入密码</td>
					<td><input class="easyui-validatebox"
					type="password" name="password2" data-options="required:true" /></td>
				</tr>
				<tr>
					<td>输入邮箱</td>
					<td><input class="easyui-validatebox"
					type="email" name="email" data-options="required:true" /></td>
				</tr>
				<tr>
					<td>输入手机号</td>
					<td><input class="easyui-validatebox"
					type="tel" name="tel" data-options="required:true" /></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="button" class="btn btn-primary" value="提交"></td>
				</tr>
			</table>
			
		</form>
	</div>

	<script type="text/javascript">
		$.extend($.fn.datagrid.methods, {
			editCell : function(jq, param) {
				return jq.each(function() {
					var opts = $(this).datagrid('options');
					var fields = $(this).datagrid('getColumnFields', true)
							.concat($(this).datagrid('getColumnFields'));
					for (var i = 0; i < fields.length; i++) {
						var col = $(this)
								.datagrid('getColumnOption', fields[i]);
						col.editor1 = col.editor;
						if (fields[i] != param.field) {
							col.editor = null;
						}
					}
					$(this).datagrid('beginEdit', param.index);
					var ed = $(this).datagrid('getEditor', param);
					if (ed) {
						if ($(ed.target).hasClass('textbox-f')) {
							$(ed.target).textbox('textbox').focus();
						} else {
							$(ed.target).focus();
						}
					}
					for (var i = 0; i < fields.length; i++) {
						var col = $(this)
								.datagrid('getColumnOption', fields[i]);
						col.editor = col.editor1;
					}
				});
			},
			enableCellEditing : function(jq) {
				return jq.each(function() {
					var dg = $(this);
					var opts = dg.datagrid('options');
					opts.oldOnClickCell = opts.onClickCell;
					opts.onClickCell = function(index, field) {
						if (opts.editIndex != undefined) {
							if (dg.datagrid('validateRow', opts.editIndex)) {
								dg.datagrid('endEdit', opts.editIndex);
								opts.editIndex = undefined;
							} else {
								return;
							}
						}
						dg.datagrid('selectRow', index).datagrid('editCell', {
							index : index,
							field : field
						});
						opts.editIndex = index;
						opts.oldOnClickCell.call(this, index, field);
					}
				});
			}
		});

		function showRequest(){
			alert("showRequest");
		}
		
		$(function() {
			$('#dg').datagrid().datagrid('enableCellEditing');
			$('#dg').datagrid({
				pagination : true,
				loadMsg : '请稍后...',
				fitColumns : true,
				pageList : [ 10 ],
				toolbar : [ {
					iconCls : 'icon-add',
					handler : function() {
						$('#win').window('open');  // open a window
					}
				}, '-', {
					iconCls : 'icon-edit',
					handler : function() {
						alert('你没有权限！')
					}
				}, '-', {
					iconCls : 'icon-remove',
					handler : function() {
						var row = $('#dg').datagrid('getSelected');
	                	var username=row.username;
	                	$.post('<%=basePath%>login',{'m':'del','name':username,'type':row.type},function(data){
								if(data.success=='ok'){
									alert('操作完成！');
									$('#dg').datagrid('reload');
								}else if(data.success=='ok2'){
									alert('管理员无法删除！');
								}else{
									alert('操作异常！');
								}
		                },'json');
					}
				} , '-', {
					iconCls : 'icon-save',
					handler : function() {
						alert('你没有权限！')
					}
				} ]
			});

			//设置分页控件 
		    var p = $('#dg').datagrid('getPager'); 
		  	//console.info(p);
		    $(p).pagination({
		        onBeforeRefresh:function(){
		        	$('#dg').datagrid('reload');
		        },onChangePageSize:function(){  
		              
		        }, 
		        onSelectPage:function(pageNumber,pageSize){  

		        	$.post("<%=basePath%>login", {
						m : 'findUserByPage',
						number : pageNumber
					}, function(data) {
						$('#dg').datagrid('loadData', data);
					}, "json");

				}
			});

		    
		    
			$(":button").click(function(){
				var queryString = $('#ff').serialize();
				queryString+="&m=addUser";
				$.post('<%=basePath%>login',queryString,function(data){
					if(data.status==='ok'){
						$('#win').window('close');
					}else{
						alert("输入有误！");
					}
				},"json");
				
				return false;
			});
		})
	</script>
</body>
</html>