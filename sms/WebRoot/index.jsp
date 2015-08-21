<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
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
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">

<!-- The styles -->
<link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">

<link href="css/charisma-app.css" rel="stylesheet">
<link href='bower_components/fullcalendar/dist/fullcalendar.css'
	rel='stylesheet'>
<link href='bower_components/fullcalendar/dist/fullcalendar.print.css'
	rel='stylesheet' media='print'>
<link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>
<link href='bower_components/colorbox/example3/colorbox.css'
	rel='stylesheet'>
<link href='bower_components/responsive-tables/responsive-tables.css'
	rel='stylesheet'>
<link
	href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>
<link href='css/jquery.noty.css' rel='stylesheet'>
<link href='css/noty_theme_default.css' rel='stylesheet'>
<link href='css/elfinder.min.css' rel='stylesheet'>
<link href='css/elfinder.theme.css' rel='stylesheet'>
<link href='css/jquery.iphone.toggle.css' rel='stylesheet'>
<link href='css/uploadify.css' rel='stylesheet'>
<link href='css/animate.min.css' rel='stylesheet'>
<link href="css/bootstrap-datetimepicker.css" rel="stylesheet">
<link href="css/jquery.dataTables.min.css" rel="stylesheet">

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
<script
	src="bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
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
<script src="js/bootstrap-datetimepicker.min.js"></script>
<script src="js/bootstrap-datetimepicker.zh-CN.js" charset="utf-8"></script>
<script type="text/javascript">
	/* $(function() {
		$("li a")
				.click(
						function() {
							var parent = $(this).attr("data-options");
							var child = $(this).text();
							var title = $(".breadcrumb");
							title.empty();
							title
									.append(" <li><a href=\"#\">收银系统</a></li><li><a href=\"#\">"
											+ parent
											+ "</a></li><li><a href=\"#\">"
											+ child + "</a></li>");

							//显示类容
							var div_id = $(this).attr("id");
							$("div#content").children().css("display", "none");
							$("#" + div_id).css("display", "block");
							if(div_id==""){
							}

						});

		$(".accordion-toggle")
				.click(
						function() {

							var text = $(this).text();
							var title = $(".breadcrumb");
							title.empty();
							title
									.append(" <li><a href=\"#\">收银系统</a></li><li><a href=\"#\">"
											+ text + "</a></li>");

						});
		
						
							
				
	}); */
</script>
<script type="text/javascript">
	$(function(){
		$('#spxxfx').click(function(){
			$('#if_content').attr('src','<%=basePath %>AnalyzeGoods?m=analyzeGoods');
		});
		$('#kcyd').click(function(){
			<%-- $('#if_content').attr('src','<%=basePath %>pages/goods/inventoryWarning.jsp'); --%>
			$('#if_content').attr('src','<%=basePath %>InventoryWarningServlet?method=findByUid');
		});
		$('#zbtj').click(function(){
			$('#if_content').attr('src','<%=basePath %>pages/sales/zbtj.jsp');
			<%-- $('#if_content').attr('src','<%=basePath %>InventoryWarningServlet?method=findByUid'); --%>
		});
<%-- 		$('#kcyd').click(function(){
			$('#if_content').attr('src','<%=basePath %>pages/goods/inventoryWarning.jsp');
			$('#if_content').attr('src','<%=basePath %>InventoryWarningServlet?method=loadBaseMessage');
		}); --%>
		<%-- $('#ckgl').click(function(){
			$('#if_content').attr('src','<%=basePath %>PayPerRideServlet?method=loadBaseMessage');
		}); --%>
	});
	
</script>
<style type="text/css">
.myclass {
	display: none;
}
</style>
</head>

<body>

	<!-- topbar starts -->
	<div class="navbar navbar-default" role="navigation">

		<div class="navbar-inner">
			<button type="button" class="navbar-toggle pull-left animated flip">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html"> <img
				alt="Charisma Logo" src="img/logo20.png" class="hidden-xs" /> <span>收银系统</span></a>

			<!-- user dropdown starts -->
			<div class="btn-group pull-right">
				<button class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					<i class="glyphicon glyphicon-user"></i><span
						class="hidden-sm hidden-xs">${sessionScope.sessionUser.UName }</span> <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<!-- <li><a href="#">资料</a></li>
					<li class="divider"></li> -->
					<li><a href="<c:url value='/UserServlet?method=quit'/>">退出</a></li>
				</ul>
			</div>
			<!-- user dropdown ends -->

			<!-- theme selector starts -->
			<div class="btn-group pull-right theme-container animated tada">
				<button class="btn btn-default dropdown-toggle"
					data-toggle="dropdown">
					<i class="glyphicon glyphicon-tint"></i><span
						class="hidden-sm hidden-xs"> 更换主题 / 皮肤</span> <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" id="themes">
					
					<li><a data-value="cerulean" href="#"><i
							class="whitespace"></i> Cerulean</a></li>
					
					<li><a data-value="slate" href="#"><i class="whitespace"></i>
							Slate</a></li>
					
				</ul>
			</div>
			

		</div>
	</div>
	<!-- topbar ends -->
	<div class="ch-container">
		<div class="row">

			<!-- left menu starts -->
			<div class="col-sm-2 col-lg-2">
				<div class="sidebar-nav">
					
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
											<li><a href="#" data-options="销售" id="rjbb">日结报表</a></li>
											<li><a href="#" data-options="销售" id="xsdj">销售单据</a></li>
											<li><a href="#" data-options="销售" id="csfx">趋势分析</a></li>
											<li><a href="#" data-options="销售" id="zbtj">占比统计</a></li>
											<li><a href="#" data-options="销售" id="spxs">商品销售查询</a></li>
											<li><a href="#" data-options="销售" id="fjz">反结账单据</a></li>
											<li><a href="#" data-options="销售" id="wdgh">我的供货</a></li>
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
											<li><a href="#" data-options="商品" id="spzl">商品资料</a></li>
											<li><a href="#" data-options="商品" id="spfl">商品分类</a></li>
											<li><a href="#" data-options="商品" id="plsz">批量设置</a></li>
											<li><a href="#" data-options="商品" id="kcyd">库存预警</a></li>
											<li><a href="#" data-options="商品" id="spxxfx">商品信息分析</a></li>
											<li><a href="#" data-options="商品" id="kcpdjl">库存盘点记录</a></li>
											<li><a href="#" data-options="商品" id="kcbdjl">库存变动记录</a></li>
											<li><a href="#" data-options="商品" id="zzcf">组装拆分</a></li>
											<li><a href="#" data-options="商品" id="ckgl">次卡管理</a></li>
											<li><a href="#" data-options="商品" id="yxjggl">优先价格管理</a></li>
											<li><a href="#" data-options="商品" id="spbs">商品报损</a></li>
											<li><a href="#" data-options="商品" id="bsjl">报损记录</a></li>
											<li><a href="#" data-options="商品" id="bstj">报损统计</a></li>
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
											<li><a href="#" data-options="会员" id="hyzl">会员资料</a></li>
											<li><a href="#" data-options="会员" id="hydj">会员等级</a></li>
											<li><a href="#" data-options="会员" id="hybz">会员报账</a></li>
											<li><a href="#" data-options="会员" id="czmx">充值明细</a></li>
											<li><a href="#" data-options="会员" id="hygmjl">会员购买查询</a></li>
											<li><a href="#" data-options="会员" id="fsdx">发送短信</a></li>
											<li><a href="#" data-options="会员" id="dxjl">短信记录</a></li>
											<li><a href="#" data-options="会员" id="jfzd">积分制度</a></li>
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
											<li><a href="#" data-options="营销" id="dzytj">打折与特价</a></li>
											<li><a href="#" data-options="营销" id="dzcx">搭赠促销</a></li>
											<li><a href="#" data-options="营销" id="hgcx">换购促销</a></li>
											<li><a href="#" data-options="营销" id="dejdz">第二件打折</a></li>
											<li><a href="#" data-options="营销" id="tccx">套餐促销</a></li>
											<li><a href="#" data-options="营销" id="mefx">满额返现</a></li>
											<li><a href="#" data-options="营销" id="yhj">优惠券</a></li>
											<li><a href="#" data-options="营销" id="cxspb">促销商品表</a></li>
											<li><a href="#" data-options="营销" id="lscxb">历史促销表</a></li>
											<li><a href="#" data-options="营销" id="cxhdfkb">促销活动反馈表</a></li>
											<li><a href="#" data-options="营销" id="sjlm">商家联盟</a></li>
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
											<li><a href="#" data-options="员工" id="syyzl">收银员资料</a></li>
											<li><a href="#" data-options="员工" id="syyyj">收银员业绩</a></li>
											<li><a href="#" data-options="员工" id="jjbjl">交接班记录</a></li>
											<li><a href="#" data-options="员工" id="dgyzl">导购员资料</a></li>
											<li><a href="#" data-options="员工" id="dgyyj">导购员业绩</a></li>
											<li><a href="#" data-options="员工" id="dgmx">导购明细</a></li>
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
											<li><a href="#" data-options="货流" id="ghszl">供货商资料</a></li>
											<li><a href="#" data-options="货流" id="ghsjs">供货商结算</a></li>
											<li><a href="#" data-options="货流" id="ghsjsjl">供货结算记录</a></li>
											<li><a href="#" data-options="货流" id="mddhqq">门店订货请求</a></li>
											<li><a href="#" data-options="货流" id="mdjh">门店进货</a></li>
											<li><a href="#" data-options="货流" id="mddhsj">门店间调货</a></li>
											<li><a href="#" data-options="货流" id="thggys">退货给供应商</a></li>
											<li><a href="#" data-options="货流" id="wljl">货流记录</a></li>
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
											<li><a href="#" data-options="设置" id="zhgl">账户管理</a></li>
											<li><a href="#" data-options="设置" id="xtsz">系统设置</a></li>
											<li><a href="#" data-options="设置" id="xxmm">修改密码</a></li>
											<li><a href="#" data-options="设置" id="qksj">清空数据</a></li>
											<li><a href="#" data-options="设置" id="zffs">支付方式</a></li>
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

					<p>
						You need to have <a href="http://en.wikipedia.org/wiki/JavaScript"
							target="_blank">JavaScript</a> enabled to use this site.
					</p>
				</div>
			</noscript>

			<!--要修改的地方  -->
			<ul class="breadcrumb">
				<li><a href="#">收银系统</a></li>
				<li><a href="#">商品</a></li>
			</ul>


			<div id="content" class="col-lg-10 col-sm-10">
				<iframe src="" width="100%" height="800px" id="if_content" style="border: 0;margin: 0;"></iframe>
			
				<%-- <!--要添加的地方  -->
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

				<jsp:include page="pages/yingxiao.jsp" flush="true"></jsp:include> --%>
				<div>
				
			</div>


				<hr>

				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">

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
								<a href="#" class="btn btn-primary" data-dismiss="modal">Save
									changes</a>
							</div>
						</div>
					</div>
				</div>

			</div>
			<!--/.fluid-container-->
</body>
</html>
