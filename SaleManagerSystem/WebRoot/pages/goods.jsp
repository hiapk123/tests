<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<div id="spzl" class="myclass">

<jsp:include page="goods/test.jsp"></jsp:include>

</div>
<div id="spfl" class="myclass">商品分类</div>
<div id="plsz" class="myclass">批量设置</div>
<div id="kcyd" class="myclass">库存预警</div>
<div id="spxxfx" class="myclass">
<iframe src="<%=basePath %>AnalyzeGoods?m=initThisPage" width="100%" height="600px"></iframe>
<%-- <c:import url="/AnalyzeGoods?m=initThisPage"></c:import> --%>

<%-- <jsp:include page="goods/analyze-goods.jsp"></jsp:include>  --%>
</div>
<div id="kcpdjl" class="myclass">库存盘点记录</div>
<div id="kcbdjl" class="myclass">库存变动记录</div>
<div id="zzcf" class="myclass">组装拆分</div>
<div id="ckgl" class="myclass">次卡管理</div>
<div id="yxjggl" class="myclass">优先价格管理</div>
<div id="spbs" class="myclass">商品报损</div>
<div id="bsjl" class="myclass">报损记录</div>
<div id="bstj" class="myclass">报损统计</div>
