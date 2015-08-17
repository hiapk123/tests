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
		$('.pagination').click(function() {
			alert($("#page").val());
			
			
			
			
			
			//ssss
			
			
		});

		$("li a").click(function() {
			//alert($(this).text());
			var which = $(this).text();
			var storeID=$("#storeID").val();
			alert(storeID);
			if (which === "首页") {
				which="first";
			} else if (which == "上一页") {
				which="prev";
			} else if (which === "下一页") {
				which="next";
			} else if (which === "尾页") {
				which="last";
			} else {

			}
			var pageNo=$("#page").val()
			$.post("<%=basePath%>goods", {
				"which" : which,
				"store" : storeID,
				"m" : "findByPage",
				"currentPage" : pageNo
			}, function(data) {
				$("#tableContent").html(data);
			}, "html");
		});
	});
</script>
<div id="tableContent">
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

				String storeID = request.getAttribute("store").toString();
				System.out.println("liuyan" + storeID);
				if (goods != null && goods.size() > 0) {
					for (int i = 0; i < goods.size(); i++) {%>
			<tr>
				<td><a
					href="<%=basePath%>goods?m=deleteGood&g_id=<%=goods.get(i)[3]%>">
						删除 </a> <a
					href="<%=basePath%>goods?m=editGood&g_id=<%=goods.get(i)[3]%>&s_name=<%=goods.get(i)[1]%>&g_name=<%=goods.get(i)[0]%>&c_name=<%=goods.get(i)[4]%>&s_id=<%=storeID%>">编辑</a>
					<a href="#"> 图片 </a></td>
				<%
					for (int j = 0; j <= 5; j++) {
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
	<input type="hidden" id="page" value="${currentPage }" />
</div>
<input type="hidden" id="storeID" value="<%=storeID%>" />

<ul class="pagination" id="page">
	<page:htmlPage pageNo="${currentPage }"
		url="/goods?m=findGoodByPage&currentPage=${currentPage }"
		totalSum="${totalPage }" showPage="10" pageSize="10" />
</ul>


