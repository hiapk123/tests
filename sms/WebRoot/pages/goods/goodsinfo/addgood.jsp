<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*,org.uestc.util.*"%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%-- <script>
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
</SCRIPT> --%>
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
<table style="width:3000px; height:30px;  table-layout:fixed;" border="1" ;>
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
		            String s_id = request.getParameter("s_id");
		          //  String g_id = request.getParameter("g_id");
					String s_name = request.getParameter("s_name");
		            String zhuangtai = request.getParameter("state");
		            String g_barcode = request.getParameter("g_barcode");
		            String jinhuojia = request.getParameter("jinhuojia");
		            String productname = request.getParameter("productname");
		            String xiaoshoujia = request.getParameter("xiaoshoujia");
		            String fenlei = request.getParameter("fenlei");
		            String kucun = request.getParameter("kucun");
		            String g_name = request.getParameter("g_name");
		            String sql="select g_id from goods where s_id=? and g_barcode=?";
		    		List<Object[]> list = SqlHelper.find(sql, s_id,g_barcode);
		    		Number num = (Number) list.get(0)[0];  
		    	   int  Num = num.intValue();
		    		String g_id=String.valueOf(Num);
				%>
				
				
		<tr>
			<td class="center"><a href="href="<%=basePath%>goods?m=deleteGood&g_id=<%=g_id%>""> 删除 </a> <br>
			<a href="<%=basePath%>goods?m=editGood&g_barcode=<%=g_barcode%>&g_id=<%=g_id%>&s_name=<%=s_name%>&g_name=<%=g_name%>&s_id=<%=s_id%>">编辑</a>
<br>
			<a href="#"> Delete </a></td>
			
					
			<td><%=s_name%></td>
			<td><%=g_name%></td>
			<td><%=g_barcode%></td>
					
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

