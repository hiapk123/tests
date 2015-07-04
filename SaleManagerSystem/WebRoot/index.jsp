<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!--
        ===
        This comment should NOT be removed.

        Charisma v2.0.0

        Copyright 2012-2014 Muhammad Usman
        Licensed under the Apache License v2.0
        http://www.apache.org/licenses/LICENSE-2.0

        http://usman.it
        http://twitter.com/halalit_usman
        ===
    -->
    <meta charset="utf-8">
    <title>收银系统主页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">

    <!-- The styles -->
    <link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="css/charisma-app.css" rel="stylesheet">
    <link href='bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='css/jquery.noty.css' rel='stylesheet'>
    <link href='css/noty_theme_default.css' rel='stylesheet'>
    <link href='css/elfinder.min.css' rel='stylesheet'>
    <link href='css/elfinder.theme.css' rel='stylesheet'>
    <link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='css/uploadify.css' rel='stylesheet'>
    <link href='css/animate.min.css' rel='stylesheet'>

    <!-- jQuery -->
    <script src="bower_components/jquery/jquery.min.js"></script>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="img/favicon.ico">

	<!-- external javascript -->

<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- library for cookie management -->
<script src="js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='bower_components/moment/min/moment.min.js'></script>
<script src='bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script src="bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script src="bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script src="bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="js/charisma.js"></script>

<script type="text/javascript">
	$(function(){
		$("li a").click(function(){
			var parent=$(this).attr("data-options");
			var child=$(this).text();
			var title=$(".breadcrumb");
		    title.empty();
		    title.append(" <li><a href=\"#\">收银系统</a></li><li><a href=\"#\">"+parent+"</a></li><li><a href=\"#\">"+child+"</a></li>");
		    
		    //显示类容
		    var div_id=$(this).attr("data-div");
		    $("div#content").children().css("display", "none");
		    $("#"+div_id).css("display", "block");
		   
		    
		});
		
		$(".accordion-toggle").click(function(){
			
		    var text=$(this).text();
		    var title=$(".breadcrumb");
		    title.empty();
		    title.append(" <li><a href=\"#\">收银系统</a></li><li><a href=\"#\">"+text+"</a></li>");
			
		});

	});
</script>
<style type="text/css">
	
	.myclass{
		display: none;
	}
</style>
</head>

<body>
    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
            <button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html"> <img alt="Charisma Logo" src="img/logo20.png" class="hidden-xs"/>
                <span>收银系统</span></a>

            <!-- user dropdown starts -->
            <div class="btn-group pull-right">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"> admin</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#">资料</a></li>
                    <li class="divider"></li>
                    <li><a href="login.html">退出</a></li>
                </ul>
            </div>
            <!-- user dropdown ends -->

            <!-- theme selector starts -->
            <div class="btn-group pull-right theme-container animated tada">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-tint"></i><span
                        class="hidden-sm hidden-xs"> 更换主题 / 皮肤</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" id="themes">
                    <!--<li><a data-value="classic" href="#"><i class="whitespace"></i> Classic</a></li>-->
                    <li><a data-value="cerulean" href="#"><i class="whitespace"></i> Cerulean</a></li>
                    <!--<li><a data-value="cyborg" href="#"><i class="whitespace"></i> Cyborg</a></li>-->
                    <!--<li><a data-value="simplex" href="#"><i class="whitespace"></i> Simplex</a></li>-->
                    <!--<li><a data-value="darkly" href="#"><i class="whitespace"></i> Darkly</a></li>
                    <li><a data-value="lumen" href="#"><i class="whitespace"></i> Lumen</a></li>-->
                    <li><a data-value="slate" href="#"><i class="whitespace"></i> Slate</a></li>
                    <!--<li><a data-value="spacelab" href="#"><i class="whitespace"></i> Spacelab</a></li>-->
                   <!--  <li><a data-value="united" href="#"><i class="whitespace"></i> United</a></li> -->
                </ul>
            </div>
            <!-- theme selector ends -->

			<!--
            <ul class="collapse navbar-collapse nav navbar-nav top-menu">
                <li><a href="#"><i class="glyphicon glyphicon-globe"></i> Visit Site</a></li>
                
				<li class="dropdown">
                    <a href="#" data-toggle="dropdown"><i class="glyphicon glyphicon-star"></i> Dropdown <span
                            class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li class="divider"></li>
                        <li><a href="#">Separated link</a></li>
                        <li class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
				
                <li>
                    <form class="navbar-search pull-left">
                        <input placeholder="Search" class="search-query form-control col-md-10" name="query"
                               type="text">
                    </form>
                </li>
            </ul>
			-->

        </div>
    </div>
    <!-- topbar ends -->
<div class="ch-container">
    <div class="row">
        
        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <!--<div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">

                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header">山大发送</li>
                        <li><a class="ajax-link" href="index.html"><i class="glyphicon glyphicon-home"></i><span> Dashboard</span></a>
                        </li>
                        <li><a class="ajax-link" href="ui.html"><i class="glyphicon glyphicon-eye-open"></i><span> UI Features</span></a>
                        </li>
                        <li><a class="ajax-link" href="#"><i
                                    class="glyphicon glyphicon-edit"></i><span> Forms</span></a></li>
                        <li><a class="ajax-link" href="chart.html"><i class="glyphicon glyphicon-list-alt"></i><span> Charts</span></a>
                        </li>
                        <li><a class="ajax-link" href="typography.html"><i class="glyphicon glyphicon-font"></i><span> Typography</span></a>
                        </li>
                        <li><a class="ajax-link" href="gallery.html"><span> Gallery</span></a>
                        </li>
                        
                    </ul>
                   
                </div>
				-->
				<div class="nav-canvas">
            <div class="panel-group" id="accordion2">
            <div class="panel-heading">
                <strong style="font-size: 30px;">收银系统</strong>
            </div>
                <div class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse"
                        data-parent="#accordion2" href="#collapseOne">
                        <a class="accordion-toggle">销售</a>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse"
                        style="height: 0px;">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#" data-options="销售" data-div="rjbb">日结报表</a></li>
                                <li><a href="#" data-options="销售" data-div="xsdj">销售单据</a></li>
                                <li><a href="#" data-options="销售" data-div="csfx">趋势分析</a></li>
                                <li><a href="#" data-options="销售" data-div="zbtj">占比统计</a></li>
								<li><a href="#" data-options="销售" data-div="spxs">商品销售查询</a></li>
								<li><a href="#" data-options="销售" data-div="fjz">反结账单据</a></li>
								<li><a href="#" data-options="销售" data-div="wdgh">我的供货</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse"
                        data-parent="#accordion2" href="#collapseTwo">
                        <a class="accordion-toggle">商品</a>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse"
                        style="height: 0px;">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#" data-options="商品" data-div="spzl">商品资料</a></li>
								<li><a href="#" data-options="商品" data-div="spfl">商品分类</a></li>
								<li><a href="#" data-options="商品" data-div="plsz">批量设置</a></li>
								<li><a href="#" data-options="商品" data-div="kcyd">库存预警</a></li>
								<li><a href="#" data-options="商品" data-div="spxxfx">商品信息分析</a></li>
								<li><a href="#" data-options="商品" data-div="kcpdjl">库存盘点记录</a></li>
								<li><a href="#" data-options="商品" data-div="kcbdjl">库存变动记录</a></li>
								<li><a href="#" data-options="商品" data-div="zzcf">组装拆分</a></li>
								<li><a href="#" data-options="商品" data-div="ckgl">次卡管理</a></li>
								<li><a href="#" data-options="商品" data-div="yxjggl">优先价格管理</a></li>
								<li><a href="#" data-options="商品" data-div="spbs">商品报损</a></li>
								<li><a href="#" data-options="商品" data-div="bsjl">报损记录</a></li>
								<li><a href="#" data-options="商品" data-div="bstj">报损统计</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse"
                        data-parent="#accordion2" href="#collapseThree">
                        <a class="accordion-toggle">会员</a>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse"
                        style="height: 0px;">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#" data-options="会员" data-div="hyzl">会员资料</a></li>
                                <li><a href="#" data-options="会员" data-div="hydj">会员等级</a></li>
                                <li><a href="#" data-options="会员" data-div="hybz">会员报账</a></li>
								<li><a href="#" data-options="会员" data-div="czmx">充值明细</a></li>
								<li><a href="#" data-options="会员" data-div="hygmjl">会员购买查询</a></li>
								<li><a href="#" data-options="会员" data-div="fsdx">发送短信</a></li>
								<li><a href="#" data-options="会员" data-div="dxjl">短信记录</a></li>
								<li><a href="#" data-options="会员" data-div="jfzd">积分制度</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

				<div class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse"
                        data-parent="#accordion2" href="#collapseFour">
                        <a class="accordion-toggle">营销</a>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse"
                        style="height: 0px;">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#" data-options="营销" data-div="dzytj">打折与特价</a></li>
                                <li><a href="#" data-options="营销" data-div="dzcx">搭赠促销</a></li>
                                <li><a href="#" data-options="营销" data-div="hgcx">换购促销</a></li>
								<li><a href="#" data-options="营销" data-div="dejdz">第二件打折</a></li>
								<li><a href="#" data-options="营销" data-div="tccx">套餐促销</a></li>
								<li><a href="#" data-options="营销" data-div="mefx">满额返现</a></li>
								<li><a href="#" data-options="营销" data-div="yhj">优惠券</a></li>
								<li><a href="#" data-options="营销" data-div="cxspb">促销商品表</a></li>
								<li><a href="#" data-options="营销" data-div="lscxb">历史促销表</a></li>
								<li><a href="#" data-options="营销" data-div="cxhdfkb">促销活动反馈表</a></li>
								<li><a href="#" data-options="营销" data-div="sjlm">商家联盟</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
				
				<div class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse"
                        data-parent="#accordion2" href="#collapseFive">
                        <a class="accordion-toggle">员工</a>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse"
                        style="height: 0px;">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#" data-options="员工" data-div="syyzl">收银员资料</a></li>
                                <li><a href="#" data-options="员工" data-div="syyyj">收银员业绩</a></li>
                                <li><a href="#" data-options="员工" data-div="jjbjl">交接班记录</a></li>
								<li><a href="#" data-options="员工" data-div="dgyzl">导购员资料</a></li>
								<li><a href="#" data-options="员工" data-div="dgyyj">导购员业绩</a></li>
								<li><a href="#" data-options="员工" data-div="dgmx">导购明细</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
				
				<div class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse"
                        data-parent="#accordion2" href="#collapseSix">
                        <a class="accordion-toggle">货流</a>
                    </div>
                    <div id="collapseSix" class="panel-collapse collapse"
                        style="height: 0px;">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#" data-options="货流" data-div="ghszl">供货商资料</a></li>
                                <li><a href="#" data-options="货流" data-div="ghsjs">供货商结算</a></li>
                                <li><a href="#" data-options="货流" data-div="ghsjsjl">供货结算记录</a></li>
								<li><a href="#" data-options="货流" data-div="mddhqq">门店订货请求</a></li>
								<li><a href="#" data-options="货流" data-div="mdjh">门店进货</a></li>
								<li><a href="#" data-options="货流" data-div="mddhsj">门店间调货</a></li>
								<li><a href="#" data-options="货流" data-div="thggys">退货给供应商</a></li>
								<li><a href="#" data-options="货流" data-div="wljl">货流记录</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
			
				
				<div class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse"
                        data-parent="#accordion2" href="#collapseEight">
                        <a class="accordion-toggle">设置</a>
                    </div>
                    <div id="collapseEight" class="panel-collapse collapse"
                        style="height: 0px;">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#" data-options="设置" data-div="zhgl">账户管理</a></li>
                                <li><a href="#" data-options="设置" data-div="xtsz">系统设置</a></li>
                                <li><a href="#" data-options="设置" data-div="xxmm">修改密码</a></li>
								<li><a href="#" data-options="设置" data-div="qksj">清空数据</a></li>
								<li><a href="#" data-options="设置" data-div="zffs">支付方式</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>

            </div>
        </div>
        <!--/span-->
        <!-- left menu ends -->

        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>
        
        <!--要修改的地方  -->
		  <ul class="breadcrumb">
		        <li>
		            <a href="#">收银系统</a>
		        </li>
		        <li>
		            <a href="#">商品</a>
		        </li>
		   </ul>
    
    
        <div id="content" class="col-lg-10 col-sm-10">
         	<!--要添加的地方  -->
         	<!--销售  -->
           	<jsp:include page="pages/xiaoshu.jsp" flush="true"></jsp:include>
           	
           	<!--商品  -->
           	<jsp:include page="pages/goods.jsp" flush="true"></jsp:include>
           	<!--会员  -->
           	<jsp:include page="pages/vip.jsp" flush="true"></jsp:include>
           	<!--等等  -->
            <jsp:include page="pages/emp.jsp" flush="true"></jsp:include>
            
            <jsp:include page="pages/huoliu.jsp" flush="true"></jsp:include>
            
            <jsp:include page="pages/sys.jsp" flush="true"></jsp:include>
            
            <jsp:include page="pages/yingxiao.jsp" flush="true"></jsp:include>
        <div>
  
</div>


    <hr>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">

        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <h3>Settings</h3>
                </div>
                <div class="modal-body">
                    <p>Here settings can be configured...</p>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
                    <a href="#" class="btn btn-primary" data-dismiss="modal">Save changes</a>
                </div>
            </div>
        </div>
    </div>

</div><!--/.fluid-container-->




</body>
</html>
