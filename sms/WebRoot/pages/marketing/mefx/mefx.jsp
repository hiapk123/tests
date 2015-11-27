<%@ page language="java" import="java.util.*,org.uestc.util.PageObject,com.uestc.bean.*"
	
	pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<base href="<%=basePath%>">

<title>优惠券</title>
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

<script src="<%=basePath%>bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

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
<script src="<%=basePath%>js/bootstrap-datetimepicker.zh-CN.js"charset="utf-8"></script>
<link href="<%=basePath%>css/bootstrap-datetimepicker.css"rel="stylesheet">	

<script src="<%=basePath%>js/formcheck.js"></script>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="营销,活动">
<meta http-equiv="description" content="满额返现">

<style>
	.trBack{
			background-color:silver;
			
	}
	.trBack th{
		text-align: center;
	}

	.tbd tr td{
		text-align: center;
	}
	.content_head{
		display: none;
	}
	.mytable {
		max-height: 200px;
		overflow: auto;
	}
	.dzytj_left{
		float: left;
		
	}
	.tdbody{
	}
		
</style>
<script>

function CurentTime()
{ 
    var now = new Date();
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var clock = '';
    if(hh < 10)
        clock += "0";      
    clock += hh + ":";
    if (mm < 10) clock += '0'; 
    clock += mm; 
    return(clock); 
}
function Time()
{ 
    var now = new Date();
   
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
   
    var hh ="0";            //时
    var mm = "0";          //分
   
    var clock = year + "-";
   
    if(month < 10)
        clock += "0";
   
    clock += month + "-";
   
    if(day < 10)
        clock += "0";
       
    clock += day + " ";
   
    if(hh < 10)
        clock += "0";
       
    clock += hh + ":";
    if (mm < 10) clock += '0'; 
    clock += mm; 
    return(clock); 
}
//跳转添加活动
function addactive(){
	
	 $("#hc_id").val('');
	 
	$.post('<%=basePath%>MefxServlet',
 			{
 				"subType":"addactive",
 				"ifsave":"no"
 			},
 			function(str){				
 					$("#mmefx").empty();	
 					$("#mmefx").html(str);
 					 $('.cfIntNum').bind('keyup',intnumcolor);
 					timeinput();				
 			});
	
}
//详细与更新处理
function XGactive(active_type,name,start_time,c_id){		
	
	 $("#hc_id").val('');
	 
	// alert(name+"==="+start_time)
	
 	$.post('<%=basePath%>MefxServlet',
 			{
 				"subType":"XGactive",
 				"active_type":active_type,
 				"c_name":name,
 				"c_start_date":start_time
 				,"c_id":c_id
 			},
 			function(str){
 				//$(window.parent.document).find("#if_content").html(str);
 					$("#mmefx").empty();	
 				if(active_type!="shanchu"){
 						
 						$("#mmefx").html(str);
 					
 							
 						$("#hc_id").val($("#mbt_mefx_active_id").val());
 		 				
 		 				timeinput();
 		 				$('.cfnum').bind('keyup',numcolor);
 		 				$('.cfIntNum').bind('keyup',intnumcolor);
 					
 					}else{
 						if(str=="filed")
 						alert("请先取消绑定该优惠券");
 						backlist();
 					}
 			});

 	
	}

 var sgtt = false;
function addgoods(type){
		
		
  if(sgtt){
	  return;
  }
  sgtt = true;
  var num = $("#mefx_daitianjiashangpinxianshi").children("tr[class = 'tdbody'] ").length+1;
  if(type=='tianjia'){
  	var tb_k="<tr id = \"trlist_"+num+"\" class='tdbody'>";
 	    tb_k+=  "<td class='mane'>	<input class='cfnum' type='text' value = '0' /></td>";
		 tb_k += "<td class = 'back'> <input class='cfnum' type='text' value='0' />   </td>";
 	    tb_k += "<td class ='shanchu'><a onclick=\"delGoodsInList('trlist_"+num+"');\" href=\"javascript:void(0)\">删除</a></td></tr>";
 	  
	 $("#mefx_daitianjiashangpinxianshi").append(tb_k);
 	  
  } else if(type!="tianjia"){ 
	 	var tb_k="<tr id = \"trlist_"+num+"\" class='tdbody'>";
 	    tb_k+="<td class='mane'><input type='text' value = '0' /></td>";
		 tb_k += "<td class = 'back'> <input type='text' value='0' />   </td>";
 	    tb_k += "<td class ='shanchu'><a onclick=\"delGoodsInList('trlist_"+num+"');\" href=\"javascript:void(0)\">删除</a></td></tr>";
 	  
	 $("#mefx_daitianjiashangpinxianshi").append(tb_k);
  }
  $('.cfnum').bind('keyup',numcolor);
 
 	 	 sgtt=false;
}
	
		
	
	
 
 function delGoodsInList(id){

	 $("#"+id).empty();
	 $("#"+id).attr('id','');
	 
 }
 function saveGoodsToActive(savetype){
	 if(!ifNumOk()){
		 alert("请输入正确金额！");
		 return false;
	 }
	 if(!ifIntNumOK()){
		 alert("请输入正确数量！");
		 return false;
	 }
	var trs = $("#mefx_daitianjiashangpinxianshi").children("tr[class='tdbody']");
	
	var info = new Array();
	var miptl = new Array();
	var biptl = new Array();
	var flag= false;
	trs.each(function(index,ob){
		 
		 $(ob).children('td').each(function(nu,td){
			var td_class = $(td).attr('class');
			
			 if(td_class!='shanchu'){
						var nu = $(td).children("input[type='text']").val();
						if(nu==0){
							//alert("请输入正确的金额！")
							flag = true;
						}
						if(td_class=='mane'){
							var mipt = $(td).children("input[type='text']").val()
							miptl.push(mipt);
						}
							
						if(td_class=='back'){
							var mipt = $(td).children("input[type='text']").val()
							biptl.push(mipt);
						} 
				} 
		 }); 
		 
		 
		
	});
	
	info.push("MIPT"+miptl.join(",")+"MIPT");
	info.push("BIPT"+biptl.join(",")+"BIPT");
	/* if(flag){
		alert("请输入正确的金额！");
		return false;
	} */
	
//	alert(discount);
	var type = 0;
	var name = "";
	var start_time = "";
	var stop_time = "";	
	name = $("#mbt_mefx_active_name").val();
	start_time = $("#date_start").val();
	stop_time = $("#date_stop").val();
   var hc_id = "";
  
	if(savetype=='gengxin'){
		hc_id = $("#hc_id").val();
	}
	
	if(name==""){
		alert("名称不能为空！");
		return false;
	}
	if($("#optionsRadios1").is(':checked')){
		type = type +  1;
	}

	if(miptl.length==0 || biptl.length==0){
		alert('未添加任何规则!');
		return false;
	}
	var c_num = $("#mbt_mefx_cnum").val();
	if(c_num==""){
		alert("未指定数量");
	}
	var tinfo = info.join('')
   $.post('<%=basePath%>MefxServlet',{"subType":"tanchuceng","tc_type":"saveGoods","tinfo":tinfo,
		  "name":name,"start_time":start_time,"stop_time":stop_time,"type":type,"hc_id":hc_id,"c_num":c_num},function(str){
			
			if(str=="filed")
					alert("请先取消绑定该优惠券");
			backlist();
	  }); 
 }

	function backlist(){
		$("#mmefx").empty();

		$.post('<%=basePath%>marketing?type=mefx',
	 		
	 	function(str){
			//alert(str);
	 					$("#mmefx").empty();									
	 				var  s = str.split("<body>");
	 				//alert(s[2]);
	 				$("#mmefx").html(s[2]);	

	 			});
	}
</script>

</head>

<body>
<input type="hidden" id="hc_id" value="" />

<a id="mbt_a_addgoods" style="display: none" link-rel="myModal" data-value="-1" isfinal="1"  name="根目录" class="ajax-link">添加根目录</a>
<div id ="mmefx">
<div class="panel panel-default">
		<div class="panel-body">
			<div style="float: left; width: 15%;">
				<h4>优惠券</h4>
				
			</div>
				
			<div id="mbt_dzytj_head" style="float: right; width: 85%;">		
		
				<a class="btn btn-primary" onclick="addactive();" href="javascript:void(0)" style="float: right;">添加</a>
			</div>
		</div>
	</div>
	<!-- div start -->
 <div class="panel panel-default">
 			<div id="mbt_zjytj_content_head" class="panel-heading">
 						
			</div> 
			<div class="panel-body">
				<div id="mbt_dzytj_panel" class="col-sm-12 col-lg-12">
				  		<table class= "col-sm-12 col-lg-12 table tbd" >
				  			<thead class="trBack ">
				  			<tr>
				  			<th >优惠券名称</th>
				  			<th>开始时段</th>
				  			<th >结束时段</th>
				  			<th >适用于</th>
				  			<th>剩余数量</th>
				  			<th>编码</th>
				  			<th >操作</th>
				  			
				  			</tr>
				  			</thead>
				  			<tbody>
				  				<%
				  					List <MarketingInit> mlist = (List<MarketingInit>)request.getAttribute("activelist"); 				  				
				  					for(Iterator<MarketingInit> it = mlist.iterator();it.hasNext();){
				  						MarketingInit m = it.next();
				  						out.println("<tr id = \"trlist_"+m.getActiveId()+"\"><td>"+m.getName()+"</td>");
				  						out.println("<td>"+m.getD_start_date()+"</td>");
				  						out.println("<td>"+m.getD_stop_date()+"</td>");
				  						out.println("<td>"+m.getType()+"</td>");
				  						out.println("<td>"+m.getC_num()+"</td>");
				  						out.println("<td>"+m.getBarcode()+"</td>");
				  						out.println("<td>"+"<a onclick=\"XGactive('xiangxi','"+m.getName()+"','"+m.getD_start_date()+"','"+m.getActiveId()+"');\" href=\"javascript:void(0)\" >详细</a>");
				  						out.println(" | <a onclick=\"XGactive('gengxin','"+m.getName()+"','"+m.getD_start_date()+"','"+m.getActiveId()+"');\" href=\"javascript:void(0)\" >更新</a>");
				  						out.println(" | <a onclick=\"XGactive('shanchu','"+m.getName()+"','"+m.getD_start_date()+"','"+m.getActiveId()+"');\" href=\"javascript:void(0)\" >删除</a></td>");
				  					}
				  				
				  				%>
				  			</tbody>
				  		</table>
				  		
			   </div>
			   
			</div> 
</div>
</div>
				<!-- 弹出层 start -->
								<div class="modal fade" id="myModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">	
									<div class="modal-dialog" >
								   
										<div class="modal-content">
											<div class="modal-header" >
												<!-- <button type="button" class="close" data-dismiss="modal">×</button> -->
												<h3 style="text-align: center;padding-top: 0;padding-bottom: 10px">搜索商品并添加</h3>
												搜索关键字：<input id="mefx_search_input" type="text" value="商品名/条形码" />
												<select id="mefx_search_select" ><option>全部分类</option></select>
												<button id="mefx_search_button" class="button" style="margin-left: 20px" type="button" onclick="searchGoods('tianjia');" value="搜索" >搜索</button>
											</div>
										  
											<div class="panel-body ">
													<div class="col-sm-12 col-lg-12" style="display: block">
														<table class="col-sm-12 col-lg-12 table "    >
															<thead style="background-color: silver;"  class="trBack">
																<tr>
																	<th  width="5%"><input id="mefx_all_checkbox"  onclick="checkboxClick();" type="checkbox" /></th>
																	<th width="20%">商品名称</th>
																	<th width="15%">分类</th>
																	<th width="15%">原价</th>
																	<th width="15%">进货价</th>
									   							<th width="30%">总  数</th>
																	
																</tr>
															</thead>
														</table>
													</div>
													<div class="col-sm-12 col-lg-12" style="max-height: 200px;overflow: scroll;" >
														<table class="col-sm-12 col-lg-12 table tbd" id="mefx_tb_list"  >
																<tr id="mefx_tr_111">
																<td width="5%" isselect="no"><input type="checkbox" value="111" class="mefx_single_checkbox"></td>
																<td width="20%">111</td>
																<td width="15%">adasd</td>
																<td width="15%">111</td>
																<td width="15%">111</td>
																<td width="25%"><input  style="width:80%;float: right;" value = "1" /></td>
														</table>
													
													</div>
											</div>
											<div class="modal-footer">
											
										    	
												<a   href="#" class="btn btn-default"  data-dismiss="modal">取消</a>
												
												<a id="Tsavetotable" href="#" class="btn btn-primary fsub" onclick="saveGoodsToTable('tianjia')"  data-dismiss="modal">添加到列表</a>
											</div>
										</div>
									</div>
								</div>
								<!-- 弹出层  end-->
  
<script type="text/javascript">
			$.fn.datetimepicker.dates['zh'] = {  
			        days:       ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六","星期日"],  
			        daysShort:  ["日", "一", "二", "三", "四", "五", "六","日"],  
			        daysMin:    ["日", "一", "二", "三", "四", "五", "六","日"],  
			        months:     ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月","十二月"],  
			        monthsShort:  ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],  
			        meridiem:    ["上午", "下午"],  
			        //suffix:      ["st", "nd", "rd", "th"],  第二件打折
			        today:       "当前时间"  
			};
			function timeinput(){
			var time = Time();
			//alert(time);
			//$('.form_datetime').attr("value",time);
			    $('.form_datetime').datetimepicker({
			        language:  'zh',
			        format:'hh:ii',
			         weekStart: 1,
			       /// startDate: time,
			        todayBtn:  1,
				   	autoclose: 1,
					  todayHighlight: 1,
					  startView: 1,
					  forceParse: 1,
			        showMeridian: 0,
			        keyboardNavigation:false,
			        maxView:1,
			        startDate:time
			  //      initialDate:1
			        
			    });
				}

	
</script>         
</body>
</html>
 