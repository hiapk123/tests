<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*,org.uestc.util.DateFormatUtils"%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script>
	$(function() {
		$('.pagination').click(function(){
			alert($("#page").val());
		});
	});
</script>
<SCRIPT LANGUAGE="javascript">
<!--
function editgood()
{
	
window.open ("<%=basePath%>goods?m=addGoods&s_id="+storeid+"&s_name="+storename,'newwindow','height=500,width=800,top=100,left=100,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no')
}

-->
</SCRIPT>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link id="bs-css" href="css/bootstrap-cerulean.min.css" rel="stylesheet">


<link href='bower_components/chosen/chosen.min.css' rel='stylesheet'>

<link
	href='bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>

<link href='css/elfinder.min.css' rel='stylesheet'>

<link href='css/animate.min.css' rel='stylesheet'>

<script src="bower_components/bootstrap/dist/css/bootstrap.min.css"></script>
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="bower_components/jquery/jquery.min.js"></script>
<script src="js/bootstrap.file-input.js"></script>
<table class="table table-striped table-bordered">
	<thead>
		<tr>
			<th>操作</th>
			<th>商品名称</th>
			<th>所属门店</th>
			<th>库存量</th>
			<th>进货价</th>
			<th>销售价</th>
			<th>批发价</th>
			<th>会员价</th>
			<th>分类</th>
			<th>条码</th>
			<th>会员折扣</th>
			<th>库存上限</th>
			<th>库存下限</th>
			<th>生产日期</th>
			<th>保质期 ↑ ↓</th>
			<th>拼音码</th>
			<th>供货商</th>
			<th>自定义1</th>
			<th>自定义2</th>
			<th>自定义3</th>
			<th>自定义4</th>
			<th>最小起订量</th>
			<th>最低陈列量</th>
			<th>畅销量</th>
			<th>正常销售量</th>
			<th>库存合理值</th>
			<th>是否锁定</th>
		</tr>
	</thead>
	<tbody>

		<%
		            String storeid = request.getParameter("storeID");
					String storename = request.getParameter("store");
		            String zhuangtai = request.getParameter("state");
		            String tiaoma = request.getParameter("tiaoma");
		            String jinhuojia = request.getParameter("jinhuojia");
		            String productname = request.getParameter("productname");
		            String xiaoshoujia = request.getParameter("xiaoshoujia");
		            String fenlei = request.getParameter("fenlei");
		            String kucun = request.getParameter("kucun");
		            
		            request.setAttribute( "storeid", storeid);
		            request.setAttribute("storename", storename);
		          //  request.setAttribute("zhuangtai", zhuangtai);
		            request.setAttribute("tiaoma", tiaoma);
		            request.setAttribute("jinhuojia", jinhuojia);
		            request.setAttribute("productname", productname);
		            request.setAttribute("xiaoshoujia", xiaoshoujia);
		            request.setAttribute("fenlei", fenlei);
		            request.setAttribute("kucun", kucun); 
				%>
				
				
		<tr>
			<td class="center"><a href="#"> 删除 </a> 
			<button type="button"  id="editgood" class="btn btn-success" name="submit"
		onclick="editgood()">编辑</button>
	&nbsp;&nbsp;
			<a href="#"> Delete </a></td>
			
					
			<td><%=storename%></td>
			<td><%=zhuangtai%></td>
			<td><%=tiaoma%></td>
					
		</tr>
		

	</tbody>

</table>
<input type="hidden" id="page" value="20"/>
<ul class="pagination" id="page">
	<page:htmlPage pageNo="${currentPage }"
		url="/goods?m=findGoodByPage&currentPage=${currentPage }"
		totalSum="${totalPage }" showPage="10" pageSize="10" />
</ul>

<a href="<%=basePath%>goods?m=goodsInfo">返回商品资料页面</a>
