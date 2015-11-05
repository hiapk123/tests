
<%@ page language="java" import="java.util.*,org.uestc.util.PageObject"
	pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%! 	String show_type ="设置";
	%>
<base href="<%=basePath%>">

<title>商品信息分析</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="商品,信息,分析">
<meta http-equiv="description" content="商品信息分析页面">
<!-- The styles -->
<link id="bs-css" href="<%=basePath%>css/bootstrap-cerulean.min.css"
	rel="stylesheet">

<link href="<%=basePath%>css/charisma-app.css" rel="stylesheet">
<link
	href='<%=basePath%>bower_components/fullcalendar/dist/fullcalendar.css'
	rel='stylesheet'>
<link
	href='<%=basePath%>bower_components/fullcalendar/dist/fullcalendar.print.css'
	rel='stylesheet' media='print'>
<link href='<%=basePath%>bower_components/chosen/chosen.min.css'
	rel='stylesheet'>
<link
	href='<%=basePath%>bower_components/colorbox/example3/colorbox.css'
	rel='stylesheet'>
<link
	href='<%=basePath%>bower_components/responsive-tables/responsive-tables.css'
	rel='stylesheet'>
<link
	href='<%=basePath%>bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>
<link href='<%=basePath%>css/jquery.noty.css' rel='stylesheet'>
<link href='<%=basePath%>css/noty_theme_default.css' rel='stylesheet'>
<link href='<%=basePath%>css/elfinder.min.css' rel='stylesheet'>
<link href='<%=basePath%>css/elfinder.theme.css' rel='stylesheet'>
<link href='<%=basePath%>css/jquery.iphone.toggle.css' rel='stylesheet'>
<link href='<%=basePath%>css/uploadify.css' rel='stylesheet'>
<link href='<%=basePath%>css/animate.min.css' rel='stylesheet'>
<link href="<%=basePath%>css/bootstrap-datetimepicker.css"
	rel="stylesheet">
<link href="<%=basePath%>css/jquery.dataTables.min.css" rel="stylesheet">

<!-- jQuery -->
<script src="<%=basePath%>bower_components/jquery/jquery.min.js"></script>

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- The fav icon -->
<link rel="shortcut icon" href="<%=basePath%>img/favicon.ico">

<!-- external javascript -->

<script
	src="<%=basePath%>bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="<%=basePath%>js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='<%=basePath%>bower_components/moment/min/moment.min.js'></script>
<script
	src='<%=basePath%>bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='<%=basePath%>js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="<%=basePath%>bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script
	src="<%=basePath%>bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="<%=basePath%>js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script
	src="<%=basePath%>bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script
	src="<%=basePath%>bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="<%=basePath%>js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="<%=basePath%>js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="<%=basePath%>js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="<%=basePath%>js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="<%=basePath%>js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="<%=basePath%>js/charisma.js"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.zh-CN.js"
	charset="utf-8"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="商品,分类">
<meta http-equiv="description" content="商品分类">

<script type="text/javascript">
$(function(){
		cate_reset();
		$('#mbt_addroot').hide();
	 	$.post('<%=basePath%>CategoryGoods',{"type":"initStore"},function(data){
			var list=$('#mbt_dropdown-menu');
			list.empty();
			
			for(var i=0;i<data.length;i++){
				list.append("<li><a  onclick=\"getCate("+ data[i].sid+",'"+data[i].sname+"');\" class= \"mbt_a\" data-value="+ data[i].sid +" ><i class=\"whitespace\"></i>"+ data[i].sname+"</a></li>");
			}
	
		},"json"); 
	});

function afterReload(){
	 $(".accordion > a").bind('click',oneClick);
	 $(".accordion > a").bind('dblclick',dbClick);
	 $(".ajax-link").bind('click',oneClick);
	 $(".ajax-link").bind('dblclick',dbClick);
}

	 function getCate(storeID,name){
		  $.post('<%= basePath%>CategoryGoods', {"type" : "initCategory","id":storeID}, function(data) {
				$("#mbtCateList").empty();
				$("#mbtCateList").html("");
				$("#mbtCateList").html(data);
				//alert(data);
				$('#mbt_hidstoreID').val(storeID);
				$('#mbt_addroot').show();
				$('#mbt_caname').empty();
			   $('#mbt_caname').html("<i class=\"glyphicon red\">"+name+"</i>");
			    afterReload();
			}, "text");	 		
		}	
	
		 var isdb;
	    
	    function oneClick () {	        
	        isdb=false;
	        	var cur = $(this)
	        function sigle(){
	             if(isdb!=false)	return;
	             var $ul = cur.siblings('ul');
	             var $li = cur.parent();
	             
	              /* if ($ul.is(':visible')) $li.removeClass('active');
	             else                    $li.addClass('active');   */
	             $ul.slideToggle();
	       	  }
		     setTimeout(sigle,400);             
	    };
	    	
	       	//弹出层使用：设置a 标签 link-rel 为待弹出div ID 
	    function dbClick() {
	    	 isdb=true;	    	 
	    	 var mbt_id = $(this).attr("data-value");
	    	 var mbt_name = $(this).attr("name");
	    	 var cla = $(this).attr("isFinal");
	    	// alert(cla);
	    	 $('#mbt_hidid').val(mbt_id);
	    	 $('#mbt_hidname').val(mbt_name);
	    	 $('#mbt_delflag').val(cla);
	    	 //alert(mbt_name);
	    	 //alert(mbt_id);
	    	 $(".mbt_hid").hide();
	    	 var cl = $(this).attr("link-rel");
	    	 $("#"+cl).modal('show');
	    	};
	    	
	    	//处理弹出层点击事件
	     function mbt_show(type){
	    		$('#mbt_setting').hide();
	    		$('#mbt_add').show();
	    		
	    		
	    		if(type=="add"){
	    			//alert("add");
	    			
	    			$("#mbt_showspan").empty();
	    			$("#mbt_showspan").append("新增分类：");
	    			$('#mbt_hidtype').val("add");
	    		}
	    		
	    		if(type == "edit"){
	    			//alert("edit");
	    			$("#mbt_showspan").empty();
	    			$("#mbt_showspan").append("修改分类名称：");
	    			$('#mbt_hidtype').val("edit");
	    			var name = $('#mbt_hidname').val();
	    			$('#mbt_showtext').val(name);
	    		
	    		}
	    		if(type=="del"){
	    			$('#mbt_hidtype').val("del");
	    			$('#mbt_showtext').val("分类");
	    			var delflag=$('#mbt_delflag').val();
	    			if(delflag==1)
	    			saveChange();
	    			else
	    				{
		    				function cancel(){
		    					$("#mbt_btnwarn").trigger("click")
		    					//alert("请先删除子分类!");
		    					cate_reset();
		    				}
	    					setTimeout(cancel,200);
	    				
	    				}
	    				
	    		}
	    	} 	
	    	
	    	 function cate_reset(){
	    		 $('#mbt_showtext').val("");
	    		 $('#mbt_hidid').val("");
		    	 $('#mbt_hidname').val("");
		    	 $('#mbt_hidtype').val("");
		    	 $('#mbt_delflag').val("");
	    		 $('#mbt_setting').show();
		    	 $('#mbt_add').hide();
		    	 
	    	 }
	    	 
	    	 function saveChange(){
	    		 var id = $('#mbt_hidid').val();		    	 
		    	 var catype = $('#mbt_hidtype').val();
		    	 var name = $('#mbt_showtext').val();
		    	 var storeID = $('#mbt_caname').attr("data-value");
		    	 //alert(name);
		    	 if(name!=""){
		   	 		$.post('<%= basePath%>CategoryGoods', {"type" : "saveCategory","id":id,"catype":catype,"name":name,"storeID":storeID}, function(storeName) {		 					
		   	 			
		   	 			getCate(storeID,storeName);
		   	 		//	afterReload();
		   	 			$("#mbt_btnsucc").trigger("click")
		 				}, "text");	 
		   	 	
		    	 	}
		    	 
		    	 cate_reset();
	    		
	    	 }
	    	 function addroot(){
	    		 //alert("addroot");
	    		 $("#mbt_a_addroot").trigger("dblclick");
	    		 mbt_show("add");
	    	 }
</script>

</head>

<body>
	<input type="hidden" id= "mbt_hidid" />
	<input type ="hidden" id = "mbt_hidname" />
	<input type ="hidden" id = "mbt_hidtype" />
	<input type="hidden" id= "mbt_delflag" />
	<input type="hidden" id="mbt_hidstoreID">
	<a id="mbt_a_addroot" style="display: none" link-rel="myModal" data-value="-1" isfinal="1"  name="根目录" class="ajax-link">添加根目录</a>
	<div class="panel panel-default">
		<div class="panel-body">
			<div style="float: left; width: 15%;">
				<h4>商品分类</h4>
			</div>
			<div style="float: right; width: 85%;">

				<div class="btn-group">
					<button id="select_store" type="button"
						class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						选择店铺<span class="caret"></span>

					</button>
					<ul class="dropdown-menu" id="mbt_dropdown-menu">

						<li><a class="mbt_a" data-value="cerulean" href="#"><i
								class="whitespace"></i> 无可用门店</a></li>
					</ul>
					
				</div>
				<a id="mbt_addroot" style="float: right;" href="#" onclick="addroot();" class="btn btn-primary"> 添加根目录</a>
			</div>
		</div>
	</div>
		<!-- div start -->
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="col-sm-12 col-lg-12">
					<div class="">
						<div class="nav-canvas">
							<ul id="mbtCateList" class="nav nav-pills nav-stacked main-menu">
	
							</ul>

						</div>
						
					</div>
				</div>
			</div>
										
			
								<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
									aria-labelledby="myModalLabel" aria-hidden="true">
					
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">×</button>
												<h3><%=show_type %></h3>
											</div>
											<div class="panel-body" id="mbt_setting">
											  <a class=" btn btn-success col-sm-3 col-lg-3" onclick="mbt_show('add')">
											   	<i class="glyphicon glypgion-edit icon-white"></i> 新增
										   	</a> 
										   	<a	class=" btn btn-info col-sm-3 col-lg-3"style="margin-left: 10%;" onclick="mbt_show('edit')"> 
										   		<i class="glyphicon glypgion-edit icon-white"></i> 编辑
												</a>
												 <a class=" btn btn-danger col-sm-3 col-lg-3" data-dismiss="modal"  style="margin-left:10%;margin-bottom: 5%;" onclick="mbt_show('del')">
													<i class="glyphicon geditlyphicon-trash icon-white" ></i> 删除 
												</a>
											</div>
											<div class="panel-body mbt_hid" id="mbt_add">
											 <form action="">
											 	<span id="mbt_showspan">请输入分类名称:</span><input type="text" id="mbt_showtext" />
											 </form>
											</div>
											<div class="modal-footer">
												<a href="#" class="btn btn-default" onclick="cate_reset()" data-dismiss="modal">取消</a>
												<a href="#" class="btn btn-primary" onclick="saveChange()" data-dismiss="modal">保存</a>
											</div>
										</div>
									</div>
								</div>
							

			<!--div end  -->
		</div>
			<button id="mbt_btnwarn" style="display: none;" data-noty-options="{&quot;text&quot;:&quot;请先删除子分类！&quot;,&quot;layout&quot;:&quot;center&quot;,&quot;type&quot;:&quot;error&quot;,&quot;timeout&quot;:&quot;500&quot;}" class="btn btn-primary noty">
			<i class="glyphicon glyphicon-bell icon-white"></i> Center
			</button>


			<button id="mbt_btnsucc" style="display: none" data-noty-options="{&quot;text&quot;:&quot;操作已保存！&quot;,&quot;layout&quot;:&quot;center&quot;,&quot;type&quot;:&quot;alert&quot;,&quot;animateOpen&quot;: {&quot;opacity&quot;: &quot;show&quot;}}" class="btn btn-primary noty">
			<i class="glyphicon glyphicon-bell icon-white"></i> Center (fade)
			</button>
</body>
</html>

 
 