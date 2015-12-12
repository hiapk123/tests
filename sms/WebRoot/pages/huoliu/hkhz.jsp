<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*,org.uestc.util.DateFormatUtils,java.math.BigDecimal;"%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>


<%
String sum=request.getAttribute("sum").toString();
String sum1=request.getAttribute("sum1").toString();

String currentPage=request.getAttribute("currentPage").toString(); 
String start=request.getAttribute("start").toString();
String end=request.getAttribute("end").toString();
String s_id=request.getAttribute("s_id").toString();
%>

<script>
	
		$("li a").click(function() {
			
			var which = $(this).text();
			var s_id=<%=s_id%>;
			var start=<%=start%>;
			var end=<%=end%>;
			var sum=<%=sum%>;
			if (which === "首页") {
				which="first";
			} else if (which == "上一页") {
				which="prev";
			} else if (which === "下一页") {
				which="next";
			} else if (which === "尾页") {
				which="last";
			} 
			$("#fenye").empty();
			var currentPage=<%=currentPage%>;
		
			$.post("<%=basePath%>huoliu", {
				"which" : which,
				"s_id" : s_id,
				"m" : "findhk",
				"currentPage" : currentPage,
				"start" :start,
				"end" :end,
				"sum" :sum,
			}, function(data) {
				$("#fenye").html(data);
			}, "html");
		});
		

        
</script>




<table class="table table-bordered table-condensed table-hover table-striped ">
		<thead>
			<tr >
				<th>序号</th>
				<th>商品条码</th>
				<th>商品名称</th>
				<th>商品进价</th>
				<th>商品售价</th>
				<th>累计出货</th>
				<th>配送返利</th>
				<th>固定返利</th>
				
				<th>利润</th>
				
			</tr>
		</thead>
		<tbody>
		<%
			List<Object[]> list = (List<Object[]>) request.getAttribute("list");

			
			String yanse[]={"success","danger","active","warning","info"};
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
		%>
		<tr class="<%= yanse[i%5]%>">
			<td><%=i%></td>
						
					<%
			
				for (int j = 0; j <=4; j++) {
			%>
			<td><%=list.get(i)[j]%></td>
			<%
				}
					
			%>
			<td><%=(0.01*Double.parseDouble(String.valueOf(list.get(i)[5]))*Double.parseDouble(String.valueOf(list.get(i)[2])))%></td>	
			<td><%=(0.01*Double.parseDouble(String.valueOf(list.get(i)[6]))*Double.parseDouble(String.valueOf(list.get(i)[2])))%></td>	
		<td>
		<%-- <%=((( Double.parseDouble(String.valueOf(list.get(i)[3])))
				-(Double.parseDouble(String.valueOf(list.get(i)[2])))+
				(0.01*Double.parseDouble(String.valueOf(list.get(i)[5]))*Double.parseDouble(String.valueOf(list.get(i)[2])))+
				(0.01*Double.parseDouble(String.valueOf(list.get(i)[6]))*Double.parseDouble(String.valueOf(list.get(i)[2]))) )*
				(Double.parseDouble(String.valueOf(list.get(i)[4]))))%> --%>
		<%=sum1 %>
		</td>
		
		</tr>
		<%
			}
			}
		%>
		<tr>
						<td colspan="8">总利润</td>
						
						
					    <td><%=sum %></td>
					   
		</tr>
	</tbody>

</table>
	<ul class="pagination" id="page" style="position: absolute; bottom: 0px;">
	<page:htmlPage  pageNo="${currentPage}"
		url=""
		totalSum="${totalSize }" showPage="10" pageSize="10" />
</ul>



