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

<title>搭赠促销</title>
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
<meta http-equiv="description" content="搭赠促销">

<style>
	.trBack{
		font-size:15px;
	}
	.trBack th{
		text-align: center;
	}
	.trBack tr td{
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
<%
	String copy = (String)request.getAttribute("copy");
String pcount=(String)request.getAttribute("pageCount"); 	
%>

<script>
var pageIndex = 1;     //页面索引初始值   
var pageSize = 8;     //每页显示条数初始化，修改显示条数，修改这里即可 
var Count = '<%=pcount%>'
var pageCount =Math.floor(Count/pageSize +1);

function searchGoods(pageIndex,type) {
	var typein = type;
	InitTable(pageIndex,typein);
   $("div.holder").jPages({
      containerID : "dzcx_tb_list",
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
    	
    	 if(ifIntNumOK()){
    		 InitTable(page.current,typein);
   		}else{
   			pageIndex =page;
   			return false;
   		}
    	
    }  	
    
    function InitTable(pageIndex,type1) {  
    	typein =saveGoodsToTable(type1);
    	
    	$("#Tsavetotable").attr('onclick', '');
		$("#dzcx_search_button").attr('onclick','');
		$("#Tsavetotable").attr('onclick', "saveGoodsToTable('"+typein+"');"); 
		$("#dzcx_search_button").attr('onclick', "searchGoods(1,'"+typein+"');");
    	$("#dejdz_all_checkbox").prop("checked",false)
    	
    	var c_id = $("#dejdz_search_select").val();
    	var keywords= $("#dejdz_search_input").val();
    	var str =  dejdz_goods_id_list.join(','); 
    	if(typein!='tianjia'){
			 	if($("#"+typein).children("input[type = 'hidden']").length!=0){
			 		str = $("#"+typein).children("input[type = 'hidden']").eq(0).val();
			 		count = $("#"+typein).children("input[type = 'hidden']").eq(1).val();
			 		}
			 	}
    	 
    	$.ajax({
    		url:'<%=basePath%>DzcxServlet',
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
    		$("#dzcx_tb_list").empty();
			$("#dzcx_tb_list").append(str[0].table);
			var count = str[0].pageCount;
			pageCount =Math.floor(count/pageSize +1);	
			
			$('.cfIntNum').bind('keyup',intnumcolor);
    	},
    	dataType:"json"});
    }
};


$(document).ready(function(){
	if('<%= copy%>'=='yes')
	XGactive("copy",'<%=request.getParameter("name")%>','<%=request.getParameter("start_time")%>');
});
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
	
	 $("#hidden_name").val('');
	 $("#hidden_d_start_time").val('');
	$.post('<%=basePath%>DzcxServlet',
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
	
	$("#hidden_name").val('');
	 $("#hidden_d_start_time").val('');
	// alert(name+"==="+start_time)
	
 	$.post('<%=basePath%>DzcxServlet',
 			{
 				//"type":"dejdz",
 				"subType":"XGactive",
 				"active_type":active_type,
 				"e_name":name,
 				"e_start_date":start_time
 			},
 			function(str){
 				//$(window.parent.document).find("#if_content").html(str);
 					$("#mdejdz").empty();	
 				if(active_type!="shanchu"){
 						
 						$("#mdejdz").html(str);
 					
 							
 						$("#hidden_name").val($("#mbt_dzcx_active_name").val());
 		 				$("#hidden_d_start_time").val($("#date_start").val());
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
		var t =  typeof(type);
		if(t=='object'){
			var tp = type.parent('td').attr('class');
			//alert(tp);
			$("#Tsavetotable").attr('onclick', '');
			$("#dzcx_search_button").attr('onclick','');
			$("#Tsavetotable").attr('onclick', "saveGoodsToTable('"+type.parent().attr('id')+"');"); 
			$("#dzcx_search_button").attr('onclick', "searchGoods(1,'"+type.parent().attr('id')+"');");
		}else if (t=='string'){
			$("#Tsavetotable").attr('onclick', '');
			$("#dzcx_search_button").attr('onclick','');
			$("#Tsavetotable").attr('onclick', "saveGoodsToTable('tianjia');"); 
			$("#dzcx_search_button").attr('onclick', "searchGoods(1,'tianjia');");
		}
		//获得商品分类列表
		$.post('<%=basePath%>HgcxServlet',{"subType":"tanchuceng","tc_type":"getSelect"},function(data){	
			$("#dejdz_search_select").empty();
				$("#dejdz_search_select").append("<option value='0'>--全部分类--</option>");
				for(var i=0;i<data.length;i++){
					$("#dejdz_search_select").append("<option value='"+data[i].c_id+"'>"+data[i].c_name+"</option>");	
				}
				$("#dzcx_tb_list").empty();
				
		},"json");
		$("#dejdz_all_checkbox").prop("checked",false);
	
		
	}
	
 function checkboxClick(){
		 
		 var s = $("#dzcx_all_checkbox").is(':checked');
		 if(s){
			 $(".dzcx_single_checkbox").prop("checked","checked");
			
		 }else{
			  
			 $(".dzcx_single_checkbox").prop("checked",false)
			 
		 }
		 
	 }
 var sgtt = false;
 function saveGoodsToTable(type){
	  if(sgtt){
			  return;
	  	} 
	  if(ifIntNumOK()){
  			
  		}else{
  			alert("请输入整数！");
  			return false;
  		}
  
	  var retype="tianjia";
	  sgtt = true;
	  if(type=='tianjia'){
	  var tr_num =  $("#dzcx_daitianjiashangpinxianshi").children("tr[class='tdbody']");
	  var num = tr_num.length+1;
	  var arrChk=$("input[class='dzcx_single_checkbox']:checked");
	
	  var tb_k="<tr id = \"trlist_"+num+"\" class='tdbody'><td id = 'mairu_"+num+"'>";
	  var g_ids  = new Array();
	  var g_counts = new Array();
	  g_ids.length=0;
	  g_counts.length=0;
	  	 arrChk.each(function(index,ob){
	  		 var tr_k =$("#dzcx_tr_"+ob.value);
	  		 var flag = tr_k.children('td').eq(0).attr('isSelect');
	  		 if(flag!="yes"){
	  		 	var g_name = tr_k.children('td').eq(1).html();
	  		 	var g_count =  tr_k.children('td').eq(3).children('input').val();
	  		 	g_ids.push(ob.value);
	  		 	g_counts.push(g_count);
	  		 	tb_k += "<div>"+g_name + " X " +g_count +"</div>"; 		
	  		 } 
	  	 });
 		tb_k += "<input type='hidden' class='mairuids' id = \"mairuids_"+num+"\" value = \""+g_ids.join(',')+"\" />";
	 	tb_k += "<input type='hidden' class='mairucounts' id = \"mairucounts_"+num+"\" value = \""+g_counts.join(',')+"\" />";
  	   tb_k += "<a onclick=\"addgoods($(this));\" href=\"javascript:void(0)\">添加商品</a></td>";
  	   tb_k += "<td id='maichu_"+num+"'><a onclick=\"addgoods($(this));\" href=\"javascript:void(0)\">添加商品</a></td>";
 
  	   tb_k += "<td id='shanchu_"+num+"'><a onclick=\"delGoodsInList('trlist_"+num+"');\" href=\"javascript:void(0)\">删除</a></td></tr>";
  	   if(arrChk.length!=0){
			 $("#dzcx_daitianjiashangpinxianshi").append(tb_k);
				retype="mairu_"+num;		
  	   		}
  	   } else if(type!="tianjia"){
				 
				  var arrChk=$("input[class='dzcx_single_checkbox']:checked");
				
				  var tb_k="";
				  var g_ids  = new Array();
				  var g_counts = new Array();
			  	   arrChk.each(function(index,ob){
			  		    var tr_k =$("#dzcx_tr_"+ob.value);
				  		 var flag = tr_k.children('td').eq(0).attr('isSelect');
				  		 if(flag!="yes"){
				  		 var g_name = tr_k.children('td').eq(1).html();
				  		 var g_count =  tr_k.children('td').eq(3).children('input').val();
				  		 g_ids.push(ob.value);
				  		 g_counts.push(g_count);
				  		 tb_k += "<div>"+g_name + " X " +g_count +"</div>"; 	
				  		 	
				  		 } 
			  	 	 });
			  		
			  		 var hflag = $("#"+type).children("input[type = 'hidden']").length;
			  		 var num = type.substring(type.length-1,type.length);
			  		 if(hflag==0){
			  			
			  			tb_k += "<input type='hidden' class='maichuids' id = \"maichuids_"+num+"\" value = \""+g_ids.join(',')+"\" />";
			  		 	tb_k += "<input type='hidden' class='maichucounts' id = \"maichucounts_"+num+"\" value = \""+g_counts.join(',')+"\" />";
			  		 	
			  		 }else{
			  			 var str = type.split('_');
			  			var hidids="";
			  			var hidcounts="";
			  			 if($("#"+str[0]+"ids_"+num).val()!=""){
			  				 hidids =$("#"+str[0]+"ids_"+num).val()+","+g_ids.join(',');
			  			 	hidcounts =$("#"+str[0]+"counts_"+num).val()+","+g_counts.join(',');
			  			 }else{
			  				hidids =g_ids.join(',');
			  				hidcounts =g_counts.join(',');
			  			 }
			  			 if(arrChk.size()!=0){
			  				 $("#"+str[0]+"ids_"+num).val(hidids);
			  				 $("#"+str[0]+"counts_"+num).val(hidcounts);
			  			 }
			  			// alert($("#"+str[0]+"ids_"+num).val());
			  		 }
			  		 
					 $("#"+type).prepend(tb_k);
					 retype= type;
	  }
  	 	 sgtt=false;
  	 	return retype;
 }
 
 function delGoodsInList(id){
	 //$("#list_"+id).addClass('content_head');
	 //alert(goods_id_list.join(','));
	 $("#"+id).empty();
	 $("#"+id).attr('id','');
	 
 }
 function saveGoodsToActive(savetype){
	var trs = $("#dzcx_daitianjiashangpinxianshi").children('tr');
	var info = new Array();
	trs.each(function(index,ob){
		 var perInfo = new Array();
		 $(ob).children('td').each(function(nu,td){
			var td_type = $(td).attr('id').split('_');
			
			 if(td_type[0]!='shanchu'){
					var hids = $(td).children("input[type = 'hidden']");
					if(hids.length==0){
						alert("请选择正确的买入和送出商品");
						return false;
					}					
						 hids.each(function(n,hid){
							
							var cls = $(hid).attr('class')	
							if(cls == 'mairuids'){
								perInfo.push('MID'+$(hid).val()+'MID');
						
							}else if(cls=='mairucounts'){
								perInfo.push('MCN'+$(hid).val()+'MCN');
						
							}else if (cls =='maichuids'){
								perInfo.push('DID'+$(hid).val()+'DID');
							
							}else if (cls=='maichucounts'){
								perInfo.push('DCN'+$(hid).val()+'DCN');
							
							}
							
						});
						 
				} 
			
		 }); 
		 
		 if(perInfo.length!=0)
		 info.push(perInfo);
		
	});
	
	
//	alert(discount);
	var type = 0;
	var name = "";
	var start_time = "";
	var stop_time = "";	
	name = $("#mbt_dzcx_active_name").val();
	start_time = $("#date_start").val();
	stop_time = $("#date_stop").val();
   var hname = "";
   var hstart_time="";
	if(savetype=='gengxin'){
		hname = $("#hidden_name").val();
		hstart_time = $("#hidden_d_start_time").val();
		
	}
	
	if(name==""){
		alert("活动名称不能为空！");
		return false;
	}
	if($("#optionsRadios1").is(':checked')){
		type = type +  1;
	}
	if($("#optionsRadios2").is(':checked')){
		type = type +  2;
	}
	if($("#optionsRadios3").is(':checked')){
		type = type +  4;
	}
	if(type == 0){
		alert('请选择适用类型！');
		return false;
	}
	if(info.length==0){
		alert('未添加任何商品!');
		return false;
	}
	
	var tinfo = info.join('_')
	
	 $.post('<%=basePath%>DzcxServlet',{"subType":"tanchuceng","tc_type":"saveGoods","tinfo":tinfo,
		  "name":name,"start_time":start_time,"stop_time":stop_time,"type":type,"hname":hname,"hstart_time":hstart_time},function(str){
			if(str=="success"){
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
					'active':'for_gift',
					'activeSub':'0',
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

		$.post('<%=basePath%>marketing?type=dzcx',
	 		
	 	function(str){
			//alert(str);
	 					$("#mdejdz").empty();									
	 				var  s = str.split("<input style='display: none;' />");
	 				//var sx = s[2].split("</body>");
		 			//alert(s[2]);
	 				$("#mdejdz").html(s[2]);	

	 			});
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
				<h4>搭赠促销</h4>
				
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
				  		<table class= "trBack table  table-bordered table-hover" >
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
												<button id="dzcx_search_button" class="button" style="margin-left: 20px" type="button" onclick="searchGoods(1,'tianjia');" value="搜索" >搜索</button>
											</div>
										  
											<div class="panel-body ">
													<div class="col-sm-12 col-lg-12" style="display: block">
														<table class="trBack table  table-bordered table-hover"   >
															<thead >
																<tr>
																	<th  width="10%"><input id="dzcx_all_checkbox"  onclick="checkboxClick();" type="checkbox" /></th>
																	<th width="20%">商品名称</th>
																	<th width="20%">进货价</th>
									   							<th width="40%">总数</th>
																	
																</tr>
															</thead>
														</table>
													</div>
													<div class="col-sm-12 col-lg-12" style="max-height: 460px;overflow: scroll;" >
														<table class="col-sm-12 col-lg-12 table tbd" id="dzcx_tb_list"  >
										
														</table>
															<div class="holder text-center"></div>
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
														<table class="trBack table  table-bordered table-hover"  >
															<thead >
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
			        //suffix:      ["st", "nd", "rd", "th"],  第二件打折
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
 <%-- function searchGoods(type){
		 var c_id = $("#dejdz_search_select").val();
		 var keywords= $("#dejdz_search_input").val();
		 //alert(c_id+"+"+keywords);
		 var str =  ""; 
		 var count ="";
		 if(type!='tianjia'){
			 	if($("#"+type).children("input[type = 'hidden']").length!=0){
			 		str = $("#"+type).children("input[type = 'hidden']").eq(0).val();
			 		count = $("#"+type).children("input[type = 'hidden']").eq(1).val();
			 		}
			 	}
		 $.post('<%=basePath%>DzcxServlet',
				 {
			 		
			 		"subType":"tanchuceng",
			 		"tc_type":"getSearchGoods",
			 		"c_id":c_id,
			 		"keywords":keywords,
			 		"alreadybarcode":str,
			 		"alreadycounts":count
			 		},function(str){
						$("#dzcx_tb_list").empty();
						$("#dzcx_tb_list").append(str);
		 		 },"text");
	 } --%>
	 
	 