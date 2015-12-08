<%@page import="com.uestc.bean.batchgoods"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%
request.setCharacterEncoding("utf-8");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- 分页头部引用 -->

<!DOCTYPE>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <title>My JSP 'batch-goods.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 需要引入的前端界面的样式 -->
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
	
  </head>
  
  <script type="text/javascript">
  //定义全局的变量
 var flag=0;
  $(function(){
	  
	  $(".shclass").click(function(){
		  
		  flag=0;
	  });
	  $(".shnewclass").click(function(){
			  
			  flag=1;
			  //alert("1234");
		  });
	  
  })
   
  
  function shselect1()
  {
	  var s =  $("#tcselect").is(':checked');
	  //alert(s);
	  if(s)
	  {
		  //alert("选中");
		  //设置table的内容是选中的
		  document.getElementById("shtable1").style.display="";
		  //alert("hah ");
		  
	  }
	  else
	  {
		  
		  document.getElementById("shtable1").style.display="none";
	  }
	  
  }
  
  function shclecl2()
  {
	  //alert("选中");
	  var s =  $("#jfselect").is(':checked');
	  if(s)
	  {
		  document.getElementById("shtable2").style.display="";
		  //alert("jd");
		  
	  }
	  else
	  {
		  
		  document.getElementById("shtable2").style.display="none";
	  }
  }
  //设置的批量属性提高
   function shplset()
  {
	  //alert('大神');
	  //获取前面选中值得商品的id
	  var j=0;
	  var shs=new Array();
	  var cbox=$("table input[name='shshuliang'][type='checkbox']");
	 // alert(cbox);
	  for(var i=0;i<cbox.length;i++)
	  {
		  if(cbox[i].checked){
			  
			  shs[j]=cbox[i].value;
			  j++;		  
		  }
	  }
	  
	  //shs是一个数组存放选中的商品id值的数组
	  //在前台将它拼接成一个可以后台进行查询的参数。
	  var ids="";
	  //alert("ids");
	  for(var jj=0;jj<shs.length;jj++)
	  {
		  ids+=""+shs[jj]+"";
		  if(jj!=shs.length-1)
			  {
			  ids+=",";
			  }
	  }
	  //alert(ids.length);
	  //alert(ids);//ids是组成的字符串
	 
	  //判断删除商品是否选中。\
	  if(flag==1){
		  //执行的是删除操作，ajax向后台传递删除参数
		  if(window.confirm("确定要进行删除吗？"))
		  {
			//提交删除的url.
			//alert("提交删除url");
			 $.ajax({
				  type: "POST",

		          url: "<%=basePath%>shdelete",

		          data: {"ids":ids},

		          dataType: "html",
		          async:false,
		          success:function(data){
		        	  //alert("删除数据成功");
		        	 
		          }
			  });
			//删除数据成功之后执行的是页面的刷新工作，刷新以后就到了初始化页面
			 var shopname=$("#shselectstore").val();
	 		 var shzhuangtai=$("#shleibie").val();
	 		 var shfenlei=$("#shfenlei").val();
	 		 var shgonghuoshang=$("#shsuname").val();
	  		 var shnameofshangpin;
	  		 if($("shshangpin").text() =="")
	 		 {
		  		shnameofshangpin='';
	 		 }
	 		 else
	 		{
		  		shnameofshangpin=$("shshangpin").val();
	  		}
	  		
			//alert(shopname)
			//alert(shzhuangtai)
			//alert(shfenlei)
			//alert(shgonghuoshang)
			//alert(shnameofshangpin)
			//提交进行刷新操作
			 $.ajax({
				  type: "POST",

		          url: "<%=basePath%>shbatch",

		          data: {'m':'init',"shopname":shopname,"shzhuangtai":shzhuangtai,"shfenlei":shfenlei,"shgonghuoshang":shgonghuoshang,"shnameofshangpin":shnameofshangpin},

		          dataType: "html",
		          async:false,
		          success:function(data){
		        	  $('#shtable').empty();
		        	  //这里还必须将批量设置按钮设置成不可以选择。
		        	   document.getElementById("shsubmitbutton").disabled="true";
		        	  $('#shtable').append(data);
		          }
			  });	
		  }
		  else {
			return;
		}
		
		  //将选中的列表框的值清空
		  $("#shqwer").val('0');
		  
		  
		  
			 //专门进行商品数量的更新（这里相当于一个专门的函数）
			 $.post("<%=basePath%>shshulianggoods",{
				 "shopname":shopname,
				 "shzhuangtai":shzhuangtai,
				 "shfenlei":shfenlei,
				 "shgonghuoshang":shgonghuoshang,
				 "shnameofshangpin":shnameofshangpin
				 
			 },function(date){
				 //alert(date);
				 $("#shkklmmm").empty()
				 $("#shkklmmm").append(date)
				 var islxo=$("#shsdocsas").val();
				 //alert("dsad"+islxo)
				 $("#shpnumofgoods").val(islxo)
				 //成功之后直接绑定文本框
			 },"html")
		  
	  }
	 else {
		//执行的是修改操作.修改操作最好是分开的
		//修改的函数~~~~~~~
		/* //1.首先获取相应的所有的修改数据
		var shbianhao;//用于判断类型
		var tcgz;//提成规则
		var rg=document.getElementsByName("shselects")
		if($("input[name='shselects']").is(":checked"))
			{
			//alert("sss")
			for(var ks=0;ks<rg.length;ks++)
				{
					if(rg[0].checked)
						{
						shbianhao=1;
						tcgz="销售价 x 导购员提成百分比";
					
						}
					else if(rg[1].checked)
						{
						shbianhao=2;
						tcgz="利润 x 导购员提成百分比";
					
						}
					else if(rg[2].checked)
						{
						shbianhao=3;
						//固定金额
						tcgz=$("#hao1").val();
					
						}
					else if(rg[3].checked)
					{
						//销售价的百分之多少
						shbianhao=4;
						tcgz=$("#hao2").val();
						
					}
					else if(rg[4].checked)
					{
						shbianhao=5
						//利润的百分之多少
						tcgz=$("#hao3").val();
					
					}
					else{
						tcgz="-1";
					}
				
				}
			
			
			}
		else{
			tcgz="无提成";
		} */
		//***************************************
		//在这里获取提成规则
		var tcgz;//sunhtcselect
		if($("#tcselect").is(':checked'))
		{
		
			if(document.getElementById("sunhtcselect").value=="-1")
		  {
			 tcgz="-1";
		  }else 
		  {
			tcgz=document.getElementById("sunhtcselect").value;
		  }
			
		}else
			{
			tcgz="6";
			}
	  /////////////这里进行标识}
	  //alert(tcgz);
	  //alert("************");
	 //获取提成成功
	 //获取商品分类下拉框的选项值
	  var ydfl;
	  var ydghs;
	  if(document.getElementById("shzxc1").value=="-1")
		  {
		  
		  	ydfl="-1";
		  
		  }
	  else{
		  ydfl=$("#shzxc1").val();
	  }
	  if(document.getElementById("shzxc2").value=="-1")
	  {
		  ydghs="-1";
	  }
  	  else
  	  {  		  
	  	  ydghs=$("#shzxc2").val();
	  
      }
	  
	  //ydfl=$("#shzxc1").val();
	  //ydghs=$("#shzxc2").val();
	   //alert(ydfl);
	  //alert(ydghs);
	  //判断是否积分,积分有2部分的内容组成，一部分是可以直接设置的，另外的一部分是必须通过计算的
	  //alert("00000000000000000");
	  var shjf;
	  var sshjfen=0;
	  var rg1=document.getElementsByName("shselectjf")
	  if($("input[name='shselectjf']").is(":checked"))
		 {
		  for(var al=0;al<rg1.length;al++)
			  {
			  if(rg1[0].checked)
				  {
				  shjf=$("#shinput1").val();
				  }
			  if(rg1[1].checked)
				  {
				  sshjfen=1;
				  shjf=$("#shinput2").val();
				  }
			  }
		 }
	  else
		  {
		  shjf="不积分";
		  }
	  
	 // alert(shjf);
	  //获取会员优惠信息
	  var hyyh;
	  var sd=document.getElementsByName("shselecthyyh");
	  //alert("nihaoma")
	  //alert($("input[name='shselecthyyh']").is(":checked"));
	  if($("input[name='shselecthyyh']").is(":checked"))
	  {
		  //alert("jsadasd")
		for(var ksx=0;ksx<sd.length;ksx++)
		{
			if(sd[0].checked)
			{
				//alert("进入")
				hyyh="会员优惠";
			}
			if(sd[1].checked)
				{
				hyyh=$("#shshoujia").val()
				}
		}
		  
	  }
	  else
		  {
		  hyyh="-1";
		  }
	  
	  //alert(hyyh);
	  //alert() 
	  //判断启用和禁用的状态
	  var shqyjy;
	  if($("#qyselect").is(":checked"))
	  {
		  shqyjy=0;
	  }
	else{
		shqyjy=1;
	}
	  
	  //alert("****************");
	  //alert(tcgz);
	  //alert(ydfl);
	  //alert(ydghs);
	  //alert(shjf);
	  //alert(hyyh);
	  //alert(shqyjy);
	  //ajax进行表单提交。进行后台的修改操作
	  //1.获取需要操作修改的数据的商品id
	  //alert("hahahhahahahahahahahhahahahahahahahahah");
	  //进行AJAX数据的修改
	 
	  $.ajax({
		  type: "POST",

          url: "<%=basePath%>shupdate",

          data: {"ids":ids,"tcgz":tcgz,"ydfl":ydfl,"ydghs":ydghs,"shjf":shjf,"hyyh":hyyh,"shqyjy":shqyjy,"sshjfen":sshjfen},

          dataType: "html",
          async:false,
          success:function(data){
        	  //alert("修改数据成功");
        	  //alert("hah ");
        	 
          }
	  });
	  
	 
	 //修改完数据以后进行刷新页面的操作
	 //1.获取数据
	 		 var shopname=$("#shselectstore").val();
	 		 var shzhuangtai=$("#shleibie").val();
	 		 var shfenlei=$("#shfenlei").val();
	 		 var shgonghuoshang=$("#shsuname").val();
	  		 var shnameofshangpin;
	  		 if($("shshangpin").text() =="")
	 		 {
		  		shnameofshangpin='';
	 		 }
	 		 else
	 		{
		  		shnameofshangpin=$("shshangpin").val();
	  		}
	  		 
	 //2.进行页面的刷新操作。
	  		 $.ajax({
				  type: "POST",

		          url: "<%=basePath%>shbatch",

		          data: {'m':'init',"shopname":shopname,"shzhuangtai":shzhuangtai,"shfenlei":shfenlei,"shgonghuoshang":shgonghuoshang,"shnameofshangpin":shnameofshangpin},

		          dataType: "html",
		          async:false,
		          success:function(data){
		        	  $('#shtable').empty();
		        	  //这里还必须将批量设置按钮设置成不可以选择。
		        	   document.getElementById("shsubmitbutton").disabled="true"
		        	  $('#shtable').append(data);
		          }
			  });	
	  
	  		  //将选中的列表框的值清空
			  $("#shqwer").val('0');
	 
  }
	  
		 }
  </script>
  
  
  <script type="text/javascript">
  //批量设置
  function volumeset(){
	  
	  //alert("批量设置");
	  //第一步先获取元素中的value的值
	  var cbox=$("table input[name='shshuliang'][type='checkbox']");
	  var j=0;
	  var shs=new Array();
	  for(var i=0;i<cbox.length;i++)
	  {
		  if(cbox[i].checked){
			  
			  shs[j]=cbox[i].value;
			  j++;
			  		  
		  }
	  }
	   
	/*   for(var k=0;k<shs.length;k++)
	  {
		  alert(shs[k]);
	  } */
	  //触发按钮的弹出事件
	  $("#shtrigger").click();	 
	  
	  
	  //
	  
  }
  
  </script>
  
  <script type="text/javascript">
  //查询条件显示页面
  function shsubmit()
  {
	  //首先获取表单元素的值
	  var memtext=document.shmemyanzheng.shmemtext.value;
	  if(memtext.match(/^[0-9]*$/))
		  {
		  
		  }
	  else
		  {
		  //清空文本的内容
		  document.shmemyanzheng.shmemtext.value="";
		  //document.shmemyanzheng.shmemtext.style.background="red";
		  document.shmemyanzheng.shmemtext.focus();
		  alert("条码只能是数字");
		  return false;
		  }
	  //alert(memtext)
	  //alert("查询按钮");
	  var shopname=$("#shselectstore").val();
	  var shzhuangtai=$("#shleibie").val();
	  var shfenlei=$("#shfenlei").val();
	  var shgonghuoshang=$("#shsuname").val();
	  var shnameofshangpin;
	  if($("#shshangpin").val() =="")
	  {
		  shnameofshangpin='';
	  }
	  else
	  {
		  var shtext=$("#shshangpin").val();
		  shnameofshangpin=shtext;
	  }
	  
	  //alert("打印输出"+shnameofshangpin);
	 
	  //这里进行表单提交到新的处理页面
	  //第一个参数是提交的地址url,
		 $.ajax({
			  type: "POST",

	          url: "<%=basePath%>shbatchchaxunselect",

	          data: {"currentpage":"1",'m':'thefinal',"shopname":shopname,"shzhuangtai":shzhuangtai,"shfenlei":shfenlei,"shgonghuoshang":shgonghuoshang,"shnameofshangpin":shnameofshangpin},

	          dataType: "html",
	          async:false,
	          success:function(data){
	        	  //alert(data)
	        	  $('#shtable').empty();
	        	  $('#shtable').append(data);
	          }
		  });
	  //获取所有的值
	 // var shshopname=
		 
		 //查询完之后更新商品的数量
		 //专门进行商品数量的更新（这里相当于一个专门的函数）
		 $.post("<%=basePath%>shshulianggoods",{
			 "shopname":shopname,
			 "shzhuangtai":shzhuangtai,
			 "shfenlei":shfenlei,
			 "shgonghuoshang":shgonghuoshang,
			 "shnameofshangpin":shnameofshangpin
			 
		 },function(date){
			 //alert(date);
			 $("#shkklmmm").empty()
			 $("#shkklmmm").append(date)
			 var islxo=$("#shsdocsas").val();
			 //alert("dsad"+islxo)
			 $("#shpnumofgoods").val(islxo)
			 //成功之后直接绑定文本框
		 },"html")
	  
  }
  </script>
  <script type="text/javascript">
  //计算选中多少的checkbox(数量)。
 function shnum() {
	 //alert("计算数量");
	 var shcount=0;
	 
	 var cbox= $("table input[name='shshuliang'][type='checkbox']");
	 for (var i=0;i<cbox.length;i++){
		 if(cbox[i].checked)
			 {
			// alert("haahah");
			 shcount++;
			 }
		 
	 }
	 //alert(shcount);
	 
	 //这里实现判断有无选中，入没有选中则批量设置按钮不能点击，否则是可以点击的。
	 //*******************************************************
	 if(shcount==0)
	 {
		 document.getElementById("shsubmitbutton").disabled="true"
		 
	 }else {
		 document.getElementById("shsubmitbutton").disabled=""
		 //不设置值就代表不表不变灰色
		
	}
	 
	 //这里讲值赋值给文本框，这样就可以显示选中多少个
	  document.getElementById("shqwer").value = shcount;
	  //var shacccount=cbox.length;
	 // document.getElementById("shpnumofgoods").value = shacccount;
	 /* $("table input[name='shshuliang'][type='checkbox']").each(function(){
		 //判断是否是选中
		// alert("get");
		 console.info("123");
		 console.info("11212133"+ $("table input[name='shshuliang'][type='checkbox']"));
		 if($(this).checked)
			 
			 {
				 shcount++;
				 //alert("成功+1");
			 }
		 else {
			
		}
		 
	 });
	 */
}
  
  </script>

 <script type="text/javascript">
  //全选和反选的实现
  function yemianclick()
  {
	  //alert("选中能成功");
	  //判断是否是成功
	  //alert($("#xuanze").checked);
	  if($("#xuanze").attr("checked"))
	  {
	 	  //alert("hello")
		  $("table input[type='checkbox']").each(function(){
			  
			  $(this).removeAttr("checked");
		  });
	 	  
	 	  //如果选中就更新选中该页面的条目数目
	 	  document.getElementById("shqwer").value=0;
	 	  
	 	  
	 	 document.getElementById("shsubmitbutton").disabled="true";
	 	  
	  }
	  else
		{
		  $("table input[type='checkbox']").each(function(){
			  
			  //alert("world")
			  $(this).attr("checked","true");
		  });
		  
		 //如果取消就将条目数还原
		 var cbox= $("table input[name='shshuliang'][type='checkbox']");
	 	  var sskklxa=cbox.length;
		  document.getElementById("shqwer").value=sskklxa;
		  document.getElementById("shsubmitbutton").disabled=""
		  
		  
		//document.getElementById("shsubmitbutton").disabled="true";
		}
	  
	  
  
  }
  
  </script>
  <script type="text/javascript">
  //初始化的页面加载函数
  $(document).ready(function(){
	  
	  //document.getElementById("shsubmitbutton").disabled='true'
	  //初始化页面的参数
	  var shopname=$("#shselectstore").val();
	  var shzhuangtai=$("#shleibie").val();
	  var shfenlei=$("#shfenlei").val();
	  var shgonghuoshang=$("#shsuname").val();
	  var shnameofshangpin;
	  
	  
	  if($("shshangpin").text() =="")
	  {
		  shnameofshangpin='';
	  }
	  else
	  {
		  shnameofshangpin=$("shshangpin").val();
	  }
	  
	  //alert(shnameofshangpin);
	 
	  //这里进行表单提交到新的处理页面
	  //第一个参数是提交的地址url,
		 $.ajax({
			  type: "POST",

	          url: "<%=basePath%>shbatch",

	          data: {'m':'init',"shopname":shopname,"shzhuangtai":shzhuangtai,"shfenlei":shfenlei,"shgonghuoshang":shgonghuoshang,"shnameofshangpin":shnameofshangpin},

	          dataType: "html",
	          async:false,
	          success:function(data){
	        	  $('#shtable').append(data);
	        	  //初始化的页面
	        	  var lloop=$("#shdsacqqq").val()
	        	  $("#shpnumofgoods").val(lloop)
	          }
		  });	 
	  //初始化所有的checkbox是不选中的状态
		 document.getElementById("tcselect").checked = false;
		 document.getElementById("jfselect").checked = false;
		 document.getElementById("qyselect").checked = false;
		 
	  
		 
		 //专门进行商品数量的更新（这里相当于一个专门的函数）
		 $.post("<%=basePath%>shshulianggoods",{
			 "shopname":shopname,
			 "shzhuangtai":shzhuangtai,
			 "shfenlei":shfenlei,
			 "shgonghuoshang":shgonghuoshang,
			 "shnameofshangpin":shnameofshangpin
			 
		 },function(date){
			 //alert(date);
			 $("#shkklmmm").empty()
			 $("#shkklmmm").append(date)
			 var islxo=$("#shsdocsas").val();
			 //alert("dsad"+islxo)
			 $("#shpnumofgoods").val(islxo)
			 //成功之后直接绑定文本框
		 },"html")
  })
  
  
  //点击门店名字进行查询并且显示列表
  function shstore()
  {
	  //alert("门店选择成功");
	
	  var shopname=$("#shselectstore").val();
	  var shzhuangtai=$("#shleibie").val();
	  var shfenlei=$("#shfenlei").val();
	  var shgonghuoshang=$("#shsuname").val();
	  var shnameofshangpin;
	 
	  if($("shshangpin").text() =="")
	  {
		  shnameofshangpin='';
	  }
	  else
	  {
		  shnameofshangpin=$("shshangpin").val();
	  }
	  
	  
	  //alert(shopname);
	  //alert(shzhuangtai);
	  //alert(shfenlei);
	  //alert(shgonghuoshang);
	  //alert(shnameofshangpin);
	 
	$.ajax({
		 type: "POST",

         url: "<%=basePath%>shbatch",
         async:false,
         data: {'m':'storename',"shopname":shopname,"shzhuangtai":shzhuangtai,"shfenlei":shfenlei,"shgonghuoshang":shgonghuoshang,"shnameofshangpin":shnameofshangpin},

         dataType: "html",
         async:false,
         success:function(data){   	 
          //alert(data);	 
          //将之前的页面信息进行清除
          alert("返回数据成功");
          $('#shtable').empty();
          //alert(data);
       	  $('#shtable').append(data);
         }
	  });
	
  }
  
  </script>
  
<script type="text/javascript">
//分页标签的使用
//页面加载信息的时候使用$(function({}))
$(function(){
	   //alert($("#tablediv li a"));
	   $("#shpageing li a").click(function(){
	  	var which=$(this).text();
	  	//alert(which);//需要跳转的页数
	  	//alert("哈哈");
	  	if(which=="首页")
	  	{
	  	    which="first";	
	  		
	  	}
	  	else if(which=="上一页")
	  	{
	  		
	  		which="prev";	
	  	}
	  	else if(which=="下一页")
	  	{
	  		which="next"
	  		
	  	}
	  	else if (which=="尾页") 
	  	{
	  		which="last"	
	  	}
	  	else{
	  		
	  	} 	
	  	var pageno=$("#page").val();//当前的页数
	  	//alert("当前页数jjjjj"+pageno);
	  	var shopname=$("#shselectstore").val();
	  	var shzhuangtai=$("#shleibie").val();
	  	var shfenlei=$("#shfenlei").val();
	  	var shsuname=$("#shsuname").val();
	  	var shshangpin=$("#shshangpin").val();
	  	//alert("当前页数1"+shopname);
	  	//alert("当前页数2"+shzhuangtai);
	  	//alert("当前页数3"+shfenlei);
	  	//alert("当前页数4"+shsuname);
	  	//alert("当前页数5"+shshangpin); 	
	  	//AJAX进行表单提交
	  	$.post("<%=basePath%>shfenye",{
	  		"shopname":shopname,
	  		"shzhuangtai":shzhuangtai,
	  		"shfenlei":shfenlei,
	  		"shsuname":shsuname,
	  		"shshangpin":shshangpin,
	  		"which":which,
	  		"pageno":pageno
	  	},function(data){
	  		//alert(data);
	  		$("#shtable").empty();//清空数据
	  		$("#shtable").append(data);
	  		
	  	},"html")
	  	
	  });
	
	
});

  
  </script>
  <body>
  
  
  <!--批量处理的布局  -->
  <div class="conditionNav">
	<div class="panel panel-default">
		<div class="panel-footer">
		<!-- onchange="shstore();" -->
	
<form  name="shmemyanzheng">
	<div class="row">	
	<div class="col-xs-2">
		<select id="shselectstore" class="form-control" >
			<!-- <option value="-1"  disabled="disabled">选择店铺</option> -->

			<%
			List<batchgoods> shselect=null;
			List<batchgoods> sharray=null;
		    shselect=(List<batchgoods>)request.getAttribute("list");
				if (shselect != null && shselect.size() != 0) {
					for (batchgoods obj : shselect) {
					
			%>
			<option value='<%=obj.getS_name() %>'><%=obj.getS_name() %></option>
			<%
				}
				   }
			%>
		</select>
		</div>
		
		<!--类别（是否启用）  -->
	<div class="col-xs-2">
		 <select  id="shleibie" class="form-control" >
			<option value="0" selected="selected">启用</option>
			<option value="1">禁用</option>
		</select>
	</div>
		<div class="col-xs-2">
		<!--分类的下拉框  -->
		<select id="shfenlei" class="form-control" >
		<!-- <option value="-1"  disabled="disabled">全部分类</option> -->
		<%
		List<Object[]> shselect1=null;
		shselect1=(List<Object[]>)request.getAttribute("list1");		
		if(shselect1!=null&&shselect1.size()!=0){
				
				for(Object[] obj:shselect1){
			
		%>
		<!--这里还是要进行绑定  -->
		<option value="<%= obj[0] %>"><%=obj[0] %></option>
		
		<%
				}
			}
		%>
		</select>

		</div>
		<div class="col-xs-2">
		<select id=shsuname class="form-control" >
		<!-- <option value="-1" disabled="disabled">全部供货商</option> -->
		<%
		List<batchgoods> shselect2=null;
		shselect2=(List<batchgoods>)request.getAttribute("list2");
		if(shselect2!=null&&shselect2.size()!=0){
			for(batchgoods obj:shselect2)
			{
		%>
		<option value="<%=obj.getSu_name()%>"><%=obj.getSu_name() %></option>
		
		<%
			}
		}
		%>
		</select>
		</div>
	
	
	<div class="col-xs-2">
	<input name="shmemtext" class="search-query form-control col-md-10" type="text" id="shshangpin" placeholder="输入商品条码" >
	</div>
		<div class="col-xs-1">
		<input type="button" value="查询" id="shsearch" class="btn btn-primary" onclick="shsubmit();" />
		</div>
		</div><!-- //新增 -->
	</form>
	</div>
	</div>
	</div>

	<!--下面是显示表格的页面  -->
	<div class="panel panel-default" id="shgettable" >
		<!--start  -->
		<div id="shtable" style="height: 535px"></div>
		<!-- end -->
	</div>

	
	
 <!--批量设置的布局  -->
	<div style="position: absolute;top:635px;width: 100%">
	<div class="panel panel-default">
		<div class="panel-footer">
	<table>
	<td><input type="checkbox" id="xuanze"  onchange="yemianclick();"></td>
	<td>本页全选</td>
	<td></td>
	<td>|</td>
	<td>已选中:</td>
	<td><input type="text" value="0" id="shqwer" style=" background: transparent;border: 0"/></td>
	<td><input class="btn btn-inverse btn-default btn-lg" type="button" disabled value="批量设置" id="shsubmitbutton"  onclick="volumeset();"></td>
	<td></td>
	<td>|</td>
	<td>共
	<input type="text" value="0" id="shpnumofgoods" style=" width:30px;background: transparent;border: 0"/>
	个商品</td>
	</table>
	</div>	
	</div>
	</div>
	
	
	
	<div id="shkklmmm"></div>
<!-- 批量设置的 弹出层 -->
<!-- 按钮触发模态框 -->
<button  id="shtrigger" style="display: none" class="btn btn-primary btn-lg" data-toggle="modal" 
   data-target="#myModal">
   按钮触发模态框
</button>

<!-- 模态框（Modal） -->
<div class="modal fade " id="myModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true"  >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h3 class="modal-title" id="myModalLabel">
               批量设置
            </h3>
         </div>
         
         <div class="modal-body">
          <!--  选项卡的内容 -->
          <ul id="myTab" class="nav nav-tabs">
  
   <li class="active">
      <a href="#home" data-toggle="tab" class="shclass">
         .商品提成
      </a>
   </li>
   
   <li>
   <a href="#ios" data-toggle="tab" class="shclass">.商品分类</a>
   </li>
   
   <li>
    <a href="#jmeter" data-toggle="tab" class="shclass">.供货商</a>
   </li>
   
    <li>
    <a href="#shjf" data-toggle="tab" class="shclass">.积分</a>
   </li>
    <li>
    <a href="#shhyyh" data-toggle="tab" class="shclass">.会员优惠</a>
   </li>
    <li>
    <a href="#shqj" data-toggle="tab" class="shclass">.启用/禁用</a>
   </li>
    <li id="shselectbox">
    <a href="#shscsp" data-toggle="tab" class="shnewclass" id="shselect">.删除商品</a>
   </li>
</ul>

<!-- 选项卡的内容 -->
<div id="myTabContent" class="tab-content">
   <div class="tab-pane fade in active" id="home">
    <!--   商品提成内容 -->
    	<div class="panel panel-default">
		<div class="panel-body">
			<table >
			<tr>
				<td>是否提成</td>
				<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td>
				<td>
				<!-- 选项开关 (使用checkbox来替代)-->
				<input type="checkbox" id="tcselect" onclick="shselect1();">
				<!-- 结束 -->
				</td>
			</tr>
			</table>
				
					
		</div>
	</div>
	<!-- <div class="panel panel-default" >
		<div class="panel-body">
			设置第一个选项卡的单选按钮
	<table class='table' style="display:none" id="shtable1">
	<tbody>
		<tr>
			<td><input type="radio" name='shselects' value="销售价 x 导购员提成百分比"></td>
			<td></td>
			<td>销售价 x 导购员提成百分比 </td>		
		</tr>
		<tr>
			<td><input type="radio" name='shselects' value="利润 x 导购员提成百分比"></td>
			<td></td>
			<td>利润 x 导购员提成百分比</td>
		</tr>
		<tr>
			<td><input type="radio" name='shselects' id="shnew1" ></td>
			<td></td>
			<td>固定金额:<input id="hao1" type="text" value="0.00"/>元</td>
		</tr>
		<tr>
			<td><input type="radio" name='shselects' id="shnew2" ></td>
			<td></td>
			<td>销售价*<input id="hao2" type="text" value="0.00"/>%</td>
		</tr>
		<tr>
			<td><input type="radio" name='shselects' id="shnew3" ></td>
			<td></td>
			<td>利润*<input id="hao3" type="text" value="0.00"/>%</td>
		</tr>
		</tbody>
	</table>
		</div>
	</div> -->
	
	<!-- //设定下拉框的内容 (直接绑定)后台数据表的内容-->
	
	<div class="panel panel-default" >
		<div class="panel-body">
			<table class='table' style="display:none" id="shtable1">
				<tbody>
					<tr>
					<td>商品提成规则：</td>
					<td>
					<!-- //选择提成规则的下拉框 -->
					<select id="sunhtcselect">
					<option value="-1">请选择提成规则类型</option>
					<%
					List<Object[]> shhaha=null;
					shhaha=(List<Object[]>)request.getAttribute("list3");
					if(shhaha !=null&&shhaha.size()!=0)
					{
						for(Object[] ksa:shhaha)
						{
							
					%>
					<option value="<%=ksa[0] %>"><%=ksa[1] %></option>
					<%
					}
					
				}
					
					%>
					
					</select>
					</td>
					</tr>		
				</tbody>
			</table>
		
		</div>
	</div>
	
	
	
    <!-- 商品提成内容结束   -->
   </div>
   <div class="tab-pane fade" id="ios">
     <table>
     <tr>
     	<td>把选中的商品移动至：</td>
     </tr>
     <tr>
     <td>商品分类：</td>
     <td>
  
     <select id="shzxc1">
     
     <option value="-1" >-请选择商品的分类</option>
        <%
 	if(shselect1!=null&&shselect1.size()!=0){
		
		for(Object[] obj:shselect1){
     %>
     <option value="<%=obj[0]%>"> <%=obj[0] %></option>
  <%
		}
 	}
     %>   
     </select>
   
     </td>
     </tr>
     
     </table> 
   </div>
   
   <!-- 设置供货商的选项 -->
   <div class="tab-pane fade" id="jmeter">
   	 <table>
     <tr>
     	<td>为选中的商品更改供应商：</td>
     </tr>
     <tr>
     <td>供应商：</td>
     <td>
     <select id="shzxc2">
     <option value="-1" >-请选择商品的供应商</option>
          <%
    if(shselect2!=null&&shselect2.size()!=0){
		for(batchgoods obj:shselect2)
		{
    %> 
     <option value="<%=obj.getSu_name() %>"><%=obj.getSu_name() %></option>
      <%
		}
    }
     %>
     </select>
  
     </td>
     </tr>
     </table> 
      
   </div>
   
   
 <!--   设置积分选项 -->
 <div class="tab-pane fade" id="shjf">
 
 	<div class="panel panel-default">
		<div class="panel-body">
		<table>
		<tr>
		<td>
		是否积分：
		</td>
		<td>
		<!-- 设置开关选项 -->
					<!-- 选项开关 -->
		<input type="checkbox" id="jfselect" onclick="shclecl2();"> 
		<!-- 结束 -->
		</td>
		</tr>
		</table>
		</div>
	</div>
	
	
	<!-- 设置相关的单选按钮 -->
	<div class="panel panel-default">
		<div class="panel-body">
			<!-- 设置第一个选项卡的单选按钮 -->
	<table class='table' id="shtable2" style="display: none">
	<tbody>
	
		<tr>
			<td><input type="radio" name='shselectjf'></td>
			<td></td>
			<td>固定积分:<input id="shinput1" type="text" value="0.00"/>分</td>
		</tr>
		<tr>
			<td><input type="radio" name='shselectjf'></td>
			<td></td>
			<td>销售价:<input id="shinput2" type="text" value="0.00"/>%</td>
		</tr>
		
		</tbody>
	</table>
		</div>
	</div>
    <!--  相关的单选按钮结束 -->
 </div>
 
   <!-- 会员优惠选项 -->
    <div class="tab-pane fade" id="shhyyh">
    <div class="panel panel-default">
		<div class="panel-body">
			<table class="table">
				<tr>
				<td><input type="radio" name='shselecthyyh'></td>
				<td>会员优惠</td>
				</tr>
				
				<tr>
				<td><input type="radio" name='shselecthyyh'></td>
				<td>会员价： （售价*<input id="shshoujia" type="text" value="0.00"> %</td> 
				</tr>
			</table>
		
		</div>
	</div>
    
    
    </div>
    
<!-- 设置启用/禁用的选项 -->
	<div class="tab-pane fade" id="shqj">
		<table class='table'>
			<tr>
				<td>将商品的状态设置为：启用/禁用状态</td>
				
			</tr>
			<tr>
			<td>
			<!-- 设置开关选项 -->
			<!-- 选项开关 -->
 			<input type="checkbox" id="qyselect">

		    <!-- 结束 -->
			
			</td>
			</tr>
		</table>

	</div>


	<div class="tab-pane fade" id="shscsp">
		<table class='table'>
			<tr>
			<td>确定删除所选的商品.<font color="red">(不可恢复，请谨慎操作)</font></td>
			
			</tr>
		</table>
	</div>

<!-- //********************************************* -->
</div>


		<!-- 	选项卡结束 -->
         </div>
         
         
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">
               	取消
            </button>
            <button type="button" class="btn btn-primary" onclick="shplset();"  data-dismiss="modal">
               	确定
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
  </body>
</html>
