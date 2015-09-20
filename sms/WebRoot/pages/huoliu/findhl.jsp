<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*,org.uestc.util.DateFormatUtils"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

 <script type="text/javascript">

</script> 


<table style="width:1200px; height:30px;  table-layout:fixed;" border="1" ;>
		<thead>
			<tr>
			    <th><input id="checkAll" type="checkbox"/></th>
				<th>序号</th>
				<th>操作</th>
				<th>货流单号</th>
				<th>下单时间</th>
				<th>货单类型</th>
				<th>出货门店</th>
				<th>进货门店</th>
				<th>状态</th>
				<th>货流量</th>
				<th>总价</th>
				<th>预付款</th>
				<th>备注</th>
				<th>入库</th>
			</tr>
			<%
        List <Object[]> list=(List<Object[]>) request.getAttribute("list");
        if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {%>
         	<tr>
				<td><input  type="checkbox"/></td>
				<td>序号</td>
				<td><button class="btn btn-success"  onclick="getdetail(this)"  value="<%=list.get(i)[0]%>">详细</button></td>
				
				  
				<%
					for (int j = 1; j<5; j++) {
				%>
				<td><%=list.get(i)[j]%> </td>
				<%
					}
				%>
				<td>门店</td>
					<%
					for (int j=5; j<10; j++) {
				%>
				<td><%=list.get(i)[j]%> </td>
				<%
					}
				%>
				<td><button class="btn btn-success"  onclick="ruku(this)"  >入库</button></td>
			<input type="hidden" value="<%=list.get(i)[10]%>">
			</tr>
			<%
				}
				}
			%>
			
		</thead>
		<tbody>
		
		
	</tbody>

	</table>
