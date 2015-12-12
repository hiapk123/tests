<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*,org.uestc.util.DateFormatUtils"%>
	
	<%!
	int n=0;
	%>
	



<table id="mytable" class="table table-bordered table-condensed table-hover table-striped ">
				<thead>
					<tr >
					    
						<th>序号</th>
						<th>商品名称</th>
						<th>条码</th>
						<th>供货商</th>
						<th>货流量</th>
						<th>进货价</th>
						
					<th>单位</th>
					<th>分类</th>
					<th>小计</th>
					</tr>
					
				</thead>
				<tbody>
					<%
					String order =  request.getAttribute("order").toString();
						String []orderlist=order.trim().split(" ");
					

						if (orderlist != null && orderlist.length > 0) {
							for (int i = 0; i < orderlist.length/7; n++,i++) {
					%>
					<tr id="<%=n%orderlist.length%>">
                         <td><%=n%orderlist.length%></td>
						<%
							for (int j = 0; j <7; j++) {
						%>
						
						<td><%=orderlist[7*i+j]%></td>
						
						<%
							}
						%>
					<td><%=Double.parseDouble(orderlist[7*i+3])*Double.parseDouble(orderlist[7*i+4])%></td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>





</div>

</div>