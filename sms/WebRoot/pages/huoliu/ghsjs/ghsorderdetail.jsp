<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*,org.uestc.util.DateFormatUtils"%>
	
	<%!
	int n=0;
	%>
	<head>
	<style type="text/css">
   #sb1{display: block;  position: absolute;  top: 5%;  left: 22%;  width: 53%;  height: 20%;  padding: 8px;  border: 8px solid #E8E9F7;  background-color: white;  z-index:1002;  overflow: auto;}

 
table.ziti {
font-weight: bold;
font-size:16px;
color:#EE2C2C;
}
#mm th{
background:#FFB5C5;
}

</style>
	
	</head>

<div id="close">
<div id="sb1" >
<table id="mytable" class="ziti" style="width: 600px; height: 50px; font-weight:blod;table-layout: fixed;"border="1";>
				<thead>
					<tr >
					    
						<th>序号</th>
						<th>商品名称</th>
						<th>条码</th>
						<th>供货商</th>
						<th>货流量</th>
						<th>进货价</th>
						<th>小计</th>
					
					</tr>
					<button onclick="guanbi1()" style="float:right" class="btn-danger" id="x">&times;</button>
				</thead>
				<tbody>
					<%
					String order =  request.getAttribute("order").toString();
						String []orderlist=order.trim().split(" ");
					

						if (orderlist != null && orderlist.length > 0) {
							for (int i = 0; i < orderlist.length/5; n++,i++) {
					%>
					<tr id="<%=n%orderlist.length%>">
                         <td><%=n%orderlist.length%></td>
						<%
							for (int j = 0; j <5; j++) {
						%>
						
						
						<td><%=orderlist[5*i+j]%></td>
						
						<%
							}
						%>
					<td><%=(-1)*Double.parseDouble(orderlist[5*i+3])*Double.parseDouble(orderlist[5*i+4])%></td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>

</div>

</div>