
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

	 function getCate(data,name){
		  $.post('<%= basePath%>CategoryGoods', {"type" : "initCategory","id":data}, function(data) {
				$("#mbtCateList").empty();
				$("#mbtCateList").html("");
				$("#mbtCateList").html(data);
				//alert(data);
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
	    	 
	    	 $('#mbt_hidid').val(mbt_id);
	    	 $('#mbt_hidname').val(mbt_name);
	    	 
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
	    			$("#mbt_showspan").append("新增子分类：");
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
	    		}
	    	} 	
	    	 
	    	 function cate_reset(){
	    		 $('#mbt_showtext').val("");
	    		 $('#mbt_hidid').val("");
		    	 $('#mbt_hidname').val("");
		    	 $('#mbt_hidtype').val("");
	    		 $('#mbt_setting').show();
		    	 $('#mbt_add').hide();
		    	 
	    	 }
	    	 
	    	 function saveChange(){
	    		 var id = $('#mbt_hidid').val();		    	 
		    	 var catype = $('#mbt_hidtype').val();
		    	 var name = $('#mbt_showtext').val();
		    	 var storeID = $('#mbt_caname').attr("data-value");
		    	 
		    	 if(name!=""){
		   	 		$.post('<%= basePath%>CategoryGoods', {"type" : "saveCategory","id":id,"catype":catype,"name":name,"storeID":storeID}, function(storeName) {		 					
		   	 			
		   	 			getCate(storeID,storeName);
		   	 		//	afterReload();
		 				}, "text");	 
		   	 	
		    	 	}
		    	 
		    	 cate_reset();
	    		
	    	 }
</script>

</head>

<body>
	<input type="hidden" id= "mbt_hidid" />
	<input type ="hidden" id = "mbt_hidname" />
	<input type ="hidden" id = "mbt_hidtype" />
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
								<li class="nav-header 	"><span id="mbt_caname"data-value=1 calss="input-group-addon"><i  class="glyphicon
 red">门店一</i></span></li><li class="accordion"><a href="javascript:void(0);" name=水果 data-value=1 link-rel
="myModal" ><i	class="glyphicon glyphicon-plus"></i><span>水果</span></a><ul class="nav nav-pills nav-stacked"
><li class="accordion"><a href="javascript:void(0);" name=苹果 data-value=2 link-rel="myModal" ><i	class
="glyphicon glyphicon-plus"></i><span>苹果</span></a><ul class="nav nav-pills nav-stacked"><li><a link-rel
="myModal" name=烟台红富士 data-value=4 class="ajax-link" href="javascript:void(0);"><span> 烟台红富士</span><
/a></li></ul></li><li class="accordion"><a href="javascript:void(0);" name=香蕉 data-value=3 link-rel="myModal"
 ><i	class="glyphicon glyphicon-plus"></i><span>香蕉</span></a><ul class="nav nav-pills nav-stacked"><li
><a link-rel="myModal" name=南海 data-value=12 class="ajax-link" href="javascript:void(0);"><span> 南海<
/span></a></li></ul></li></ul></li><li class="accordion"><a href="javascript:void(0);" name=酒 data-value
=5 link-rel="myModal" ><i	class="glyphicon glyphicon-plus"></i><span>酒</span></a><ul class="nav nav-pills
 nav-stacked"><li class="accordion"><a href="javascript:void(0);" name=啤酒 data-value=10 link-rel="myModal"
 ><i	class="glyphicon glyphicon-plus"></i><span>啤酒</span></a><ul class="nav nav-pills nav-stacked"><li
><a link-rel="myModal" name=哈啤 data-value=13 class="ajax-link" href="javascript:void(0);"><span> 哈啤<
/span></a></li><li><a link-rel="myModal" name=哈啤 data-value=14 class="ajax-link" href="javascript:void
(0);"><span> 哈啤</span></a></li></ul></li><li class="accordion"><a href="javascript:void(0);" name=白酒
 data-value=11 link-rel="myModal" ><i	class="glyphicon glyphicon-plus"></i><span>白酒</span></a><ul class
="nav nav-pills nav-stacked"><li><a link-rel="myModal" name=北京二锅头 data-value=15 class="ajax-link" href
="javascript:void(0);"><span> 北京二锅头</span></a></li><li><a link-rel="myModal" name=牛栏山 data-value=16 class
="ajax-link" href="javascript:void(0);"><span> 牛栏山</span></a></li><li><a link-rel="myModal" name=飞天 data-value
=17 class="ajax-link" href="javascript:void(0);"><span> 飞天</span></a></li><li><a link-rel="myModal" name
=浏阳河  data-value=18 class="ajax-link" href="javascript:void(0);"><span> 浏阳河 </span></a></li></ul></li
><li class="accordion"><a href="javascript:void(0);" name=葡萄酒 data-value=19 link-rel="myModal" ><i	class
="glyphicon glyphicon-plus"></i><span>葡萄酒</span></a><ul class="nav nav-pills nav-stacked"><li><a link-rel
="myModal" name=国产 data-value=20 class="ajax-link" href="javascript:void(0);"><span> 国产</span></a></li
><li class="accordion"><a href="javascript:void(0);" name=法国葡萄酒 data-value=21 link-rel="myModal" ><i
	class="glyphicon glyphicon-plus"></i><span>法国葡萄酒</span></a><ul class="nav nav-pills nav-stacked"><li
><a link-rel="myModal" name=1982 data-value=22 class="ajax-link" href="javascript:void(0);"><span> 1982
</span></a></li></ul></li></ul></li></ul></li>
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
												 <a class=" btn btn-danger col-sm-3 col-lg-3"style="margin-left:10%;margin-bottom: 5%;" onclick="mbt_show('del')">
													<i class="glyphicon geditlyphicon-trash icon-white"></i> 删除 
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
</body>
</html>

 
 