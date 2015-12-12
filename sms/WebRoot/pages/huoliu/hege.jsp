
<%
String flag=request.getAttribute("flag").toString();
System.out.println(flag);


%>


<input type="text" id="flag" value="<%=flag%>">