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
					List<Object[]> goods = (List<Object[]>) request.getAttribute("goodsList");
					String StrPage = request.getParameter("Page");

					if (goods != null && goods.size() > 0) {
						for (int i = 0; i < goods.size(); i++) {
				%>
		<tr>
			<td class="center"><a href="#"> View </a> <a href="#"> Edit
			</a> <a href="#"> Delete </a></td>
			<%
						for (int j = 0; j <= 4; j++) {
					%>
			<td><%=goods.get(i)[j]%></td>
			<%
						}
					%>
		</tr>
		<%
					}
					}
				%>

	</tbody>

</table>
<input type="hidden" id="page" value="20"/>
<ul class="pagination" id="page">
	<page:htmlPage pageNo="${currentPage }"
		url="/goods?m=findGoodByPage&currentPage=${currentPage }"
		totalSum="${totalPage }" showPage="10" pageSize="10" />
</ul>


