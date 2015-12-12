<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*,org.uestc.util.DateFormatUtils"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%!
int n=0;
%>
<%

//String s_id=request.getParameter("s_id");


%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


	<script type="text/javascript">

	
	  if ($("#status").val() != "") {
		$("#status").show();
	}  
	
	
	<%--  var $table = $("#mytable");
	var $trs = $table.find("tbody").find("tr");
	
	function tr(node){ 
		
		k=$(node).attr("id");
		var $tr = $trs.eq(k);
		var $td = $tr.find("td");
		
		var g_name = $td.eq(1).text();
		var g_barcode = $td.eq(2).text();
		var g_stock_num = $td.eq(3).text();
		var su_name = $td.eq(4).text();
		var g_pur_price = $td.eq(5).text();
		var g_id = $td.eq(6).text();
		
		$.post("<%=basePath%>huoliu", {
			"m":"add",
			"g_name":g_name,
			"g_barcode":g_barcode,
			"g_stock_num":g_stock_num,
			"su_name":su_name,
			"g_pur_price":g_pur_price,
			"g_id":g_id,
			
		}, function(data) {
			$("#11").append(data);
			
		}, "html");
		
		
		
	} --%>

	</script>
	


</head>

<body>

 <div data-spy="scroll"
		style="width:auto;height:300px; overflow: auto; position: relative;"
		data-offset="10">

	
	
			<span font-size:24px >商品搜索:双击选中商品</span>
        
			<table id="mytable"  class="table table-bordered table-condensed table-hover table-striped ziti ">
				<thead>
					<tr >
					    
						<th>序号</th>
						<th>商品名称</th>
						<th>条码</th>
						<th>库存</th>
						<th>供货商</th>
						<th>进货价</th>
					    <th>单位</th>
						<th>分类</th>
					</tr>
					
				</thead>
				<tbody>
					<%
						List<Object[]> goods = (List<Object[]>) request.getAttribute("goodsList");
					

						if (goods != null && goods.size() > 0) {
							for (int i = 0; i < goods.size(); n++,i++) {
					%>
					<tr id="<%=n%goods.size()%>" ondblclick="tr(this)">
                         <td><%=n%goods.size()%></td>
						<%
							for (int j = 0; j <= 4; j++) {
						%>
						
						<td><%=goods.get(i)[j]%></td>
						
						<%
							}
						%>
						<td style="display:none"><%=goods.get(i)[5]%></td>
						<td><%=goods.get(i)[6]%></td>
						<td><%=goods.get(i)[7]%></td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>
			<center>
			 <input id="status" type="text" readOnly="true" style="display:none;width: 300px; height: 50px; font-size:24px;color:#EE2C2C;" name="status" value="${status}"> 
			</center>
           </div>
		

</body>
</html>