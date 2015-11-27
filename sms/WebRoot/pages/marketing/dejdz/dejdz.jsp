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

<title>第二件打折</title>
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

<link rel="stylesheet" href="<%=basePath%>css/jPages.css">
<script src="<%=basePath%>js/jPages.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/animate.css">
<script src="<%=basePath%>js/formcheck.js"></script>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="营销,活动">
<meta http-equiv="description" content="第二件打折">

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
	
		
</style>
<%
	String copy = (String)request.getAttribute("copy");
	String pcount=(String)request.getAttribute("pageCount"); 
%>
<script>
var pageIndex = 1;     //页面索引初始值   
var pageSize = 8;     //每页显示条数初始化，修改显示条数，修改这里即可 
var Count = '<%=pcount%>'
var pageCount =Math.floor(Count/pageSize +1);

$(document).ready(function(){
	if('<%= copy%>'=='yes')
	XGactive("copy",'<%=request.getParameter("name")%>','<%=request.getParameter("start_time")%>');
});
function searchGoods(pageIndex) {
	InitTable(pageIndex);
   $("div.holder").jPages({
      containerID : "dejdz_tb_list",
      previous : "上一页",
      next : "下一页",
      perPage : pageSize,
      pageCount:pageCount,
      animation: 'fadein',
      startPage:pageIndex,
      delay : 200,
      callback:PageCallback,
     
    });  
    function PageCallback(page, item) { 
    	
    	InitTable(page.current);  
    
    }  	
    function InitTable(pageIndex) {  
    	saveGoodsToTable();
    	$("#dejdz_all_checkbox").prop("checked",false)
    	 var c_id = $("#dejdz_search_select").val();
    	 var keywords= $("#dejdz_search_input").val();
    	 var str =  dejdz_goods_id_list.join(','); 
    	$.ajax({
    		url:'<%=basePath%>DejdzServlet',
    		type:"POST",
    		async:false,
    		data:{
    		"subType":"table",
    		"pageSize":pageSize,
    		"c_id":c_id,
	 		"keywords":keywords,
	 		"alreadygid":str,
    		"pageIndex":pageIndex
    	},
    	success :function(str){ 		
    	//	alert(mydata[0].name)
    		$("#dejdz_tb_list").empty();
			$("#dejdz_tb_list").append(str[0].table);
			var count = str[0].pageCount;
			pageCount =Math.floor(count/pageSize +1);	
    	},
    	dataType:"json"});
    }
};

var dejdz_goods_id_list = new Array();
var dejdz_dis_list= new Array();
Array.prototype.remove=function(dx)
{
    if(isNaN(dx)||dx>this.length){return false;}
    for(var i=0,n=0;i<this.length;i++)
    {
        if(this[i]!=this[dx])
        {
            this[n++]=this[i]
        }
    }
    this.length-=1
} 

function CurentTime()
{ 
    var now = new Date();
   
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
   
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
   
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
	dejdz_goods_id_list.length=0;
	dejdz_dis_list.length=0;
	
	$.post('<%=basePath%>DejdzServlet',
 			{
 				"subType":"addactive",
 				"ifsave":"no"
 			},
 			function(str){				
 					$("#mdejdz").empty();	
 					$("#mdejdz").html(str);
 					timeinput();				
 			});
	
}
//详细与更新处理
function XGactive(active_type,name,start_time){		
	dejdz_goods_id_list.length=0;
	dejdz_dis_list.length=0;
	$("#hidden_name").val('');
	 $("#hidden_d_start_time").val('');
 	$.post('<%=basePath%>DejdzServlet',
 			{
 				//"type":"dejdz",
 				"subType":"XGactive",
 				"active_type":active_type,
 				"d_name":name,
 				"d_start_date":start_time
 			},
 			function(str){
 				//$(window.parent.document).find("#if_content").html(str);
 					$("#mdejdz").empty();	
 				if(active_type!="shanchu"){
 						
 						$("#mdejdz").html(str);
 						if(active_type=="gengxin"||active_type=="copy"){
 							var  s = $("#mbt_dejdz_panel").children('table').children('tbody').children('tr');
 							
 							s.each(function(index,ob){
 							var dis= $(ob).children('td').eq(3).children().val();
 							var id= $(ob).attr('value');
 							dejdz_goods_id_list.push(id);
 					   	dejdz_dis_list.push(dis);
 							});
 						
 						}
 						$("#hidden_name").val($("#mbt_dejdz_active_name").val());
 				   	$("#hidden_d_start_time").val($("#date_start").val());
 				   
 				   	$('.cfnum').bind('keyup',numcolor);
 						timeinput();
 					}else{	
 						backlist();
 					}
 			});
	}
	function delgoods(){
		alert("在列表中删除商品");
	}
  function dbClick() {
	    
    	 var cl = $(this).attr("link-rel");
    	 
    	 $("#"+cl).modal('show');
    	};	
	function addgoods(type){
		 $("div.holder").empty();
		$(".ajax-link").bind('dblclick',dbClick);
		$("#mbt_a_addgoods").trigger("dblclick");
		$("#dzytj_all_checkbox").prop("checked",false);
		//获得商品分类列表
		$.post('<%=basePath%>DejdzServlet',{"subType":"tanchuceng","tc_type":"getSelect"},function(data){	
			$("#dejdz_search_select").empty();
				$("#dejdz_search_select").append("<option value='0'>--全部分类--</option>");
				for(var i=0;i<data.length;i++){
					$("#dejdz_search_select").append("<option value='"+data[i].c_id+"'>"+data[i].c_name+"</option>");	
				}
				$("#dejdz_tb_list").empty();
				
		},"json");
		$("#dejdz_all_checkbox").prop("checked",false);
	
		
	}
 function checkboxClick(){
		 
		 var s = $("#dejdz_all_checkbox").is(':checked');
		 if(s){
			 $(".dejdz_single_checkbox").prop("checked","checked");
			
		 }else{
			  
			 $(".dejdz_single_checkbox").prop("checked",false)
			 
		 }
		 
	 }
 var sgtt = false;
 function saveGoodsToTable(type){
	  if(sgtt){
		  return;
	  }
	  sgtt = true;
  	 var arrChk=$("input[class='dejdz_single_checkbox']:checked");
	 var s = ""; 
	 var tb_k="";
	 var discount = $("#dejdz_discount").val();
	 var disprice = $("#dejdz_spice_price").val();
  	 arrChk.each(function(index,ob){
  		 var tr_k =$("#dejdz_tr_"+ob.value);
  		 var flag = tr_k.children('td').eq(0).attr('isSelect');
  		 if(flag!="yes"){
  			 var salprice = tr_k.children('td').eq(3).html();
  		 	tb_k +="<tr id=\"list_"+ob.value+"\">"; 	
  			tb_k += "<td>"+tr_k.children('td').eq(1).html()+"</td>";	  		 
  	    	tb_k += "<td>"+tr_k.children('td').eq(2).html()+"</td>";
  		 	tb_k += "<td>"+tr_k.children('td').eq(3).html()+"</td>"; 
  		 	
  		 	if(disprice!=""){
  		 		//alert(salprice +":"+disprice)
  		 		discount = disprice/salprice*100;
  		 		discount = (Number(discount)).toFixed(4);
  		 	}
  		 	
  			tb_k += "<td><input class=\"disinput cfnum\"  onblur=\"disinput("+ob.value+");\" type=\"text\" value = \""+discount+"\"/></td>";
  		 	tb_k += "<td><a onclick=\"delGoodsInList("+ob.value+");\" href='javascript:void(0)'>删除</a></td>" ;
  		 	tb_k +="</tr>"; 
  		 } 
  		 dejdz_goods_id_list.push(ob.value);
  		 dejdz_dis_list.push(discount);
  		
  	 });
  	//alert(goods_id_list.join(','));
		 var str =  dejdz_goods_id_list.join(',');
  	// alert(str);
		 $("#dejdz_daitianjiashangpinxianshi").append(tb_k);
		 classnumcheck();
		 $('.cfnum').bind('keyup',numcolor);
  	 	 sgtt=false;
 }
 
 function delGoodsInList(id){
	 //$("#list_"+id).addClass('content_head');
	 //alert(goods_id_list.join(','));
	 $("#list_"+id).empty();
	 $("#list_"+id).attr('id','');
	 var idt= id+'';
	 var x =  dejdz_goods_id_list.indexOf(idt);
	 if(typeof(x)=="undefined"){
		 alert("此游览器版本不支持！");
	 }
	 if(x!=-1){
		 dejdz_goods_id_list.remove(x);
		 dejdz_dis_list.remove(x);
	 }else{
		 alert("查无此项！");
	 }
	// alert(dejdz_goods_id_list.join(','));
	// alert(dejdz_dis_list.join(','));
	 
 }
 function saveGoodsToActive(savetype){
	 if(!ifNumOk()){
			alert("请输入正确数字！");
			return false;
		}
	var s =dejdz_goods_id_list.join(',');
	var discount = dejdz_dis_list.join(',');
//	alert(discount);
	var type = 0;
	var name = "";
	var start_time = "";
	var stop_time = "";	
	name = $("#mbt_dejdz_active_name").val();
	start_time = $("#date_start").val();
	stop_time = $("#date_stop").val();
   var hname = "";
   var hstart_time="";
	if(savetype=='gengxin'){
		hname = $("#hidden_name").val();
		hstart_time = $("#hidden_d_start_time").val();
		
	}
	
	//alert(name+start_time+stop_time);
	if(name==""){
		alert("活动名称不能为空！");
		return false;
	}
	if($("#optionsRadios1").is(':checked')){
		type = type +  1;
		
	}
	if($("#optionsRadios2").is(':checked')){
		type = type +  2;
		//type +=2；
		
	}
	if($("#optionsRadios3").is(':checked')){
		type = type +  4;
		//type +=4；
	
	}
	if(type == 0){
		alert('请选择适用类型！');
		return false;
	}
	if(dejdz_goods_id_list.length==0){
		alert('未添加任何商品!');
		return false;
	}
	if(dejdz_dis_list.length!=dejdz_goods_id_list.length){
		alert('参数错误，请返回并重新添加!');
		return false;
	}
	
	$.post('<%=basePath%>DejdzServlet',{"subType":"tanchuceng","tc_type":"saveGoods","g_ids":s,
		  "name":name,"start_time":start_time,"stop_time":stop_time,"type":type,"discount":discount,"hname":hname,"hstart_time":hstart_time},function(str){
			if(str=="success"){
				//alert('保存成功！');	 	
				//parent.location.reload();
				backlist();
			}
	  });  
 }
	function disinput(id){
		var discount= $("#list_"+id).children('td').eq(3).children().val();
		//alert($(this).parent().html());
	 	var idt = id+""
		var x =  dejdz_goods_id_list.indexOf(idt);
	 	
		 if(typeof(x)=="undefined"){
			 alert("此游览器版本不支持！");
		 }
		 if(x!=-1){
			 dejdz_dis_list[x]=discount;
		 }else{
			 alert("查无此项！");
		 } 
		// alert(dejdz_goods_id_list.join(','));
		// alert(dejdz_dis_list.join(','));
	}

	var active_name ;
	var start_time;
	var end_time = "";
	var name = "";
	function kbtrigger(obj){
		var x =$(obj).parent('td').parent('tr');
		 active_name = x.children('td').eq(0).html();
		 start_time = x.children('td').eq(1).html();
		 end_time = x.children('td').eq(2).html();
		 name = $(obj).html(); 
		//alert(name+active_name+start_time+end_time);
		$(".ajax-link").bind('dblclick',dbClick);
		$("#mbt_mytcc").trigger("dblclick");
		
		$.post('<%=basePath%>MarketingSuportServlet',{"type":'getCoupons'},
				function(str){
			$("#mytcc_tb_list").empty();
			$("#mytcc_tb_list").append(str);
		},"text");
	
	}
	function searchCoupons(){
		 var keywords= $("#mytcc_searchinput").val();
		 
		 $.post('<%=basePath%>MarketingSuportServlet',
				 {
			 		"type":"search_coupons",
			 		"keywords":keywords,
			 		},function(str){
			 			
						$("#mytcc_tb_list").empty();
						$("#mytcc_tb_list").append(str);
		 		 },"text");
	 }
	function radioEvent(cid){
		$("input[type=radio][id != radio_"+cid+"]").attr('checked',false)
		$("#radio_+"+cid).attr("checked",true);
		}
	function saveCouponsToActive(delorsave){
		
		var cid = null;
		//alert(name+active_name+start_time+end_time);
		if(delorsave=='save'){
			var id = $("input[type=radio]:checked").attr('id')
			if(id==null){	
				alert("未选择优惠券");
				return false;	
			}
			 cid =id.split('_')[1];
		}
		$.post('<%= basePath%>MarketingSuportServlet',
				{
					'type':'saveToActive',
					'active':'discount',
					'activeSub':'1',
					'activeName':active_name,
					'start_time':start_time,
					'end_time':end_time,
					'cid':cid
				},function(str){
					backlist();
				},"text"); 
		//alert(cid);
	}
	
	function backlist(){
		$("#mdejdz").empty();

		$.post('<%=basePath%>marketing?type=dejdz',
	 		
	 	function(str){
			//alert(str);
	 					$("#mdejdz").empty();									
	 				var  s = str.split("<input style='display: none;' />");
	 				//var sx = s[2].split("</body>");
		 			//alert(s[2]);
	 				$("#mdejdz").html(s[2]);	

	 			});
	}
	function discprice(){
		var disprice = $("#dejdz_spice_price");
		var discount = $("#dejdz_discount");
		if(disprice.val()==""){	
			discount.attr("disabled",false);
			discount.val("100");
			discount.css('background','white');
			disprice.css('background','white');
			discount.addClass('tcfnum');
			$('.tcfnum').bind('keyup',numcolor);
		}else{	
			dispriceCheck();
			discount.attr("disabled","disabled");
			discount.css('background','lightgrey');	
			discount.removeClass('tcfnum');
			discount.val("");
		}
	}
	function dispriceCheck(){
		var disprice = $("#dejdz_spice_price");
		 reg = /^([1-9][\d]{0,7}|0)(\.[\d]{0,4})?$/
			var num = disprice.val();
			num =num.trim();
			if(!reg.test(num)){
				disprice.css("background","red");
			}else{
				disprice.css("background","white");
			}
	}
</script>

</head>

<body>
<input type="hidden" id="hidden_name" value="" />
<input type="hidden" id="hidden_d_start_time" value="" />
<a id="mbt_a_addgoods" style="display: none" link-rel="myModal" data-value="-1" isfinal="1"   class="ajax-link"></a>
<a id="mbt_mytcc" style="display: none" link-rel="mytcc" data-value="-1" isfinal="1"   class="ajax-link"></a>
<div id ="mdejdz">
<input style='display: none;' />
<div class="panel panel-default">
		<div class="panel-body">
			<div style="float: left; width: 15%;">
				<h4>第二件打折</h4>
				
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
				  		<table class= "col-sm-12 col-lg-12 table tbd">
				  			<thead class="trBack ">
				  			<tr>
				  			<th >活动名称</th>
				  			<th>开始时间</th>
				  			<th >结束时间</th>
				  			<th >适用于</th>
				  			<th >捆绑优惠券</th>
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
				  						out.println("<td><a onclick=\"kbtrigger(this);\" href=\"javascript:void(0)\" >"+m.getCoupons()+"</a></td>");
				  						out.println("<td>"+"<a onclick=\"XGactive('xiangxi','"+m.getName()+"','"+m.getD_start_date()+"');\" href=\"javascript:void(0)\" >详细</a>");
				  						out.println(" | <a onclick=\"XGactive('gengxin','"+m.getName()+"','"+m.getD_start_date()+"');\" href=\"javascript:void(0)\" >更新</a>");
				  						out.println(" | <a onclick=\"XGactive('shanchu','"+m.getName()+"','"+m.getD_start_date()+"');\" href=\"javascript:void(0)\" >删除</a></td>");
				  					}
				  				
				  				%>
				  			</tbody>
				  		</table>
				  		
			   </div>
			   
			</div> 
</div>
<input style='display: none;' />
</div>
				<!-- 弹出层 start -->
								<div class="modal fade" id="myModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">	
									<div class="modal-dialog" >
								   
										<div class="modal-content">
											<div class="modal-header" >
												<!-- <button type="button" class="close" data-dismiss="modal">×</button> -->
												<h3 style="text-align: center;padding-top: 0;padding-bottom: 10px">搜索商品并添加</h3>
												搜索关键字：<input id="dejdz_search_input" type="text" value="商品名/条形码" />
												<select id="dejdz_search_select" ><option>全部分类</option></select>
												<button class="button" style="margin-left: 20px" type="button" onclick="searchGoods(1);" value="搜索" >搜索</button>
											</div>
										  
											<div class="panel-body ">
													<div class="col-sm-12 col-lg-12" style="display: block">
														<table class="col-sm-12 col-lg-12 table "   >
															<thead style="background-color: silver;" class="trBack">
																<tr>
																	<th  width="10%"><input id="dejdz_all_checkbox"  onclick="checkboxClick();" type="checkbox" /></th>
																	<th width="30%">商品名称</th>
																	<th width="20%">分类</th>
									   							<th width="20%">原价</th>
																	<th width="20%">进货价</th>
																</tr>
															</thead>
														</table>
													</div>
													<div class="col-sm-12 col-lg-12" style="max-height: 400px;overflow: scroll;" >
														<table class="col-sm-12 col-lg-12 table tbd" id="dejdz_tb_list"  >
														
														</table>
													<div class="holder text-center"></div>
													</div>
											</div>
											<div class="modal-footer">
											
										    	<span class="dejdz_left">折扣：</span><input class="dejdz_left tcfnum" id="dejdz_discount" style=" width: 10%;" value="100" type="text" /><span style="padding-right: 20px;" class="dejdz_left">%</span>
												<span class="dejdz_left">特价：</span><input class="dejdz_left" onkeyup="discprice()" id="dejdz_spice_price" style=" width: 10%"  value=""/>
												
												<a   href="#" class="btn btn-default"  data-dismiss="modal">取消</a>
												
												<a href="#" class="btn btn-primary" onclick="saveGoodsToTable()"  data-dismiss="modal">添加到列表</a>
											</div>
										</div>
									</div>
								</div>
								<!-- 弹出层  end-->
  <!-- 弹出层 start -->
								<div class="modal fade" id="mytcc"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">	
									<div class="modal-dialog" >
								   
										<div class="modal-content">
											<div class="modal-header" >
												<!-- <button type="button" class="close" data-dismiss="modal">×</button> -->
												<h3 style="text-align: center;padding-top: 0;padding-bottom: 10px">绑定优惠券</h3>
												<hr>
												搜索关键字：<input style="margin-left: 20px" id="mytcc_searchinput" type="text" value="名称/条码" />
												
												<button class="button" style="margin-right: 20px;float: right;" type="button" onclick="searchCoupons();" value="搜索" >搜 索</button>
											</div>
										  
											<div class="panel-body ">
													<div class="col-sm-12 col-lg-12" style="display: block">
														<table class="col-sm-12 col-lg-12 table " border="1"  >
															<thead style="background-color: silver;" class="trBack">
																<tr>
																	<th  width="10%"></th>
																	<th width="30%">优惠券名称</th>
																	<th width="20%">剩余数量</th>
									   							<th width="20%">开始时段</th>
																	<th width="20%">结束时段</th>
																</tr>
															</thead>
														</table>
													</div>
													<div class="col-sm-12 col-lg-12" style="max-height: 200px;overflow: scroll;" >
														<table class="col-sm-12 col-lg-12 table tbd" id="mytcc_tb_list"  >
															<tr><td  width="10%"><div class="radio">
																<label>
																	<input type="radio"  value="option1" class="optionsRadios" name="optionsRadios">
																</label>
																</div></td><td  width="30%">111</td><td  width="20%">111</td><td  width="20%">111</td><td  width="20%">111</td>
														</table>
													
													</div>
											</div>
											<div class="modal-footer">	
												<a   href="#" class="btn btn-default"  data-dismiss="modal">取消</a>
												<a href="#" class="btn btn-primary" onclick="saveCouponsToActive('del')"  data-dismiss="modal">取消绑定</a>
												<a href="#" class="btn btn-primary" onclick="saveCouponsToActive('save')"  data-dismiss="modal">绑定优惠券</a>
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
			        //suffix:      ["st", "nd", "rd", "th"],  
			        today:       "今天"  
			};
			function timeinput(){
			//var time = CurentTime();
			//alert(time);
			//$('.form_datetime').attr("value",time);
			    $('.form_datetime').datetimepicker({
			        language:  'zh',
			         weekStart: 1,
			        // startDate: time,
			        todayBtn:  1,
				   	autoclose: 1,
					  todayHighlight: 1,
					  startView: 2,
					  forceParse: 0,
			        showMeridian: 1
			    });
				}

	
</script>         
</body>
</html>
 <%-- 
 	var dtable;//定义datatable的全局变量  


    //其中 fnInitComplete和sSrollY属性控制datatable刷新时不整个页面刷的效果；  function doSearch() {  
        if(dtable!=null){  
            dtable.fnClearTable(0);  
            dtable.fnDraw(); //重新加载数据  
        }else{  
        dtable = $("#datatable").dataTable({  
            "oLanguage" : { // 汉化  
            	"sLengthMenu": "每页显示 _MENU_ 条记录",   
                "sZeroRecords": "没有检索到数据",   
                "sInfo": "当前数据为从第 _START_ 到第 _END_ 条数据；总共有 _TOTAL_ 条记录",   
                "sInfoEmtpy": "没有数据",   
                "sProcessing": "正在加载数据...",   
                "oPaginate": {   
                    "sFirst": "首页",   
                    "sPrevious": "前页",   
                    "sNext": "后页",   
                    "sLast": "尾页"  
               	 }  
                },  
                "bStateSave" : true,  
                "bJQueryUI" : true,  
                "bPaginate" : true,// 分页按钮  
                "bFilter" : false,// 搜索栏  
                "bLengthChange" : true,// 每行显示记录数  
                "iDisplayLength" : 10,// 每页显示行数  
                "bSort" : false,// 排序  
                "bInfo" : true,// Showing 1 to 10 of 23 entries 总记录数没也显示多少等信息  
                "bWidth" : true,  
                "bScrollCollapse" : true,  
                "sPaginationType" : "full_numbers", // 分页，一共两种样式 另一种为two_button // 是datatables默认  
                "bProcessing" : true,  
                "bServerSide" : true,  
                "bDestroy" : true,  
                "bSortCellsTop" : true,  
                "sAjaxSource" : "<%=basePath%>/jsp/stat.do?action=statRevampList",  
                "sScrollY": "100%",  
                "fnInitComplete": function() {  
                    this.fnAdjustColumnSizing(true);  
                 },  
                "fnServerParams" : function(aoData) {  
                    aoData.push({  
                        "name" : "statId",  
                        "value" : encodeURI($("#s_statId").val())  
                    });  
                },  
                "aoColumns" : [   
                                {"mData" : "statCleanRevampId"},   
                                {"mData" : "statCleanId"},   
                                {"mData" : "statId"},   
                                {"mData" : "statName"},   
                                {"mData" : "problemDes"},   
                                {"mData" : "revampStatus"} ],  
                "aoColumnDefs":[  
                                {  
                                    "sClass":"center",  
                                    "aTargets":[6],  
                                    "mData":"statCleanRevampId",  
                                    "mRender":function(a,b,c,d){//a表示statCleanRevampId对应的值，c表示当前记录行对象  
                                        return '<a href=\"javascript:void(0);\" onclick=\"del('+a+')\">删除</a>'  
                                    + '<a href=\"javascript:void(0);\" onclick=\"editForm('+c.statCleanRevampId+','+c.statCleanId+','  
                                    +c.revampOrgan+',\''+c.statName+'\',\''+c.problemDes+'\')\">编辑</a>'  
                                        + ' <a href=\"javascript:void(0);\" onclick=\"check('+a+',1)\">审核通过</a>'  
                                        + ' <a href=\"javascript:void(0);\" onclick=\"check('+a+',2)\">审核不通过</a>';  
                                    }  
                                },//隐藏列Index=0，1，2的三列  
                                {  
                                    "aTargets":[0],  
                                    "visible":false  
                                },  
                                {  
                                    "aTargets":[1],  
                                    "visible":false  
                                },  
                                {  
                                    "aTargets":[2],  
                                    "visible":false  
                                }  
                                ],  
                "fnRowCallback" : function(nRow, aData, iDisplayIndex) {//相当于对字段格式化  
                    if (aData["revampStatus"] == 0) {  
                        $('td:eq(5)', nRow).html("结束");  
                    } else if (aData["revampStatus"] == 1) {  
                        $('td:eq(5)', nRow).html("进行中");  
                    } else if (aData["revampStatus"] == 2) {  
                        $('td:eq(5)', nRow).html("完成");  
                    } else if (aData["revampStatus"] == 3) {  
                        $('td:eq(5)', nRow).html("驳回");  
                    }  
                },  
                "fnServerData" : function(sSource, aoData, fnCallback) {  
                    $.ajax({  
                        "type" : 'get',  
                        "url" : sSource,  
                        "dataType" : "json",  
                        "data" : {  
                            aoData : JSON.stringify(aoData)  
                        },  
                        "success" : function(resp) {  
                            fnCallback(resp);  
                        }  
                    });  
                }  
            });  
        }}   --%>
 <!-- <table id="datatable">  
               <thead>  
                    <tr>  
                        <th>ID</th>  
                        <th>巡查单ID</th>  
                        <th>站点ID</th>  
                        <th>站点名称</th>  
                        <th>问题描述</th>  
    										<span style="white-space:pre"></span><th>状态</th>  
                        <th>操作</th>  
                    </tr>  
                </thead>  
                <tbody></tbody>  
            </table>    -->

