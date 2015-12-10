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

<% 
					ArrayList List = (ArrayList) request.getAttribute("List");
					List <Object[]> list0=List;
					String []ly=(String[])List.get(0);
				System.out.println("ssddd"+ly.length);
					String a="";
					if (List.size() > 0) {
						for (int k = 0; k <ly.length/8 ; k++){
					%>
					<div  style="width:2000px;height:auto">
    <table style="width: 2000px; height: 30px; table-layout: fixed;"border="1"; class="table-bordered table-condensed table-hover table-striped">
				<tbody >
					
					
					   <tr class="success">
					   
					   
					  <td><%=n++%></td>
					   <td ><a     onclick="shanchu(this)" >删除</a></td>
                     <% 
                    
                     for(int j=0;j<2;j++)
                     {
                     %>
                     <td ><%=ly[8*k+j]%></td>
					  <%
					 
					// a=a+ly[8*k+j]+" ";
                     }
                  //   a=a+ly[8*k+3];
                     %>
                   <td ><%=ly[8*k+2]%></td>
                   <td ><%=ly[8*k+3]%></td>
                   <input class="jieguo"  type="hidden"   value="<%=ly[8*k+0]%>">
                     <input class="jieguo"  type="hidden"   value="<%=ly[8*k+1]%>">
                       <input class="jieguo"  type="hidden"   value="<%=ly[8*k+3]%>">
                    <td><input ondblclick="dongtai()" class="jieguo" value="<%=ly[8*k+4]%>" placeholder="请输入进货价" type="text" style="height: 40px; width: 100px"></td>
					<%-- <%a=a+arr[4]+" ";%> --%>
					<td ><input   value="<%=ly[8*k+5]%>" class="jieguo" type="text" style="height: 40px; width: 100px"></td>
					<%-- <%a=a+arr[5]+" "; --%>
					  <input class="jieguo"  type="hidden"   value="<%=ly[6]%>">
					    <input class="jieguo"  type="hidden"   value="<%=ly[7]%>">
					<td><%=ly[8*k+6]%></td>
					<td ><%=ly[8*k+7]%></td>    
					<td><%=Double.parseDouble(ly[8*k+5])*Double.parseDouble(ly[8*k+4])%></td>
					
					
		</tr>
					
			</tbody>
			
			</table>
			</div>
			  <%
						}
                     	}
                     	
                     %>
 
	
