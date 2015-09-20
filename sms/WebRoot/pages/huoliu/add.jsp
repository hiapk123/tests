<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*,org.uestc.util.DateFormatUtils,java.math.BigDecimal;"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%!
int n=0;
%> 
    <table style="width: 1200px; height: 30px; table-layout: fixed;"border="1";>
				<tbody >
					<% 
					ArrayList List = (ArrayList) request.getAttribute("List");
					List <Object[]> list0=List;
					String a="";
					if (List.size() > 0) {
					   for (int i = 0; i < List.size(); i++,n++) {%>
					   <tr style="width: 100px; height: 50px; "border="1";>
					   
					   
					  <td><%=n%></td>
					   <td ><a     onclick="shanchu(this)" >删除</a></td>
                     <% 
                     String arr[]=(String[])List.get(i);
                     for(int j=0;j<2;j++)
                     {
                     %>
                     <td ><%=arr[j]%></td>
					  <%
					  System.out.println(arr[j]);
					 a=a+arr[j]+" ";
                     }
                     a=a+arr[3];
                     %>
                   <td ><%=arr[2]%></td>
                   <td ><%=arr[3]%></td>
                   <input class="jieguo"  type="hidden"   value="<%=a%>">
                    <td><input ondblclick="dongtai()" class="jieguo" value="<%=arr[4]%>" placeholder="请输入进货价" type="text" style="height: 40px; width: 100px"></td>
					<%-- <%a=a+arr[4]+" ";%> --%>
					<td ><input   value="<%=arr[5]%>" class="jieguo" type="text" style="height: 40px; width: 100px"></td>
					<%-- <%a=a+arr[5]+" "; --%>
					
					<td></td>
					      
					<td><%=Double.parseDouble(arr[5])*Double.parseDouble(arr[4])%></td>
					
					
		</tr>
                     <%
                     	}
                     	}
                     %>
					
			</tbody>
			
			</table>
			
 
	
