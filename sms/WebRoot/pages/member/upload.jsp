<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="org.uestc.util.shexcelutil"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//request.getRequestDispatcher(action).forward(request, response);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function  ooslx() {
	$.post("<%=basePath%>shiploadexcel",{},function(date){},"html");
	
}
</script>
<body>
<table>
            <tr>
            <td><input id="shthefilepath" type="file" data-filename-placement="inside"></td>
            </tr>
            <tr>
            <td><input type="button" onclick="ooslx();" value="excel导入" /> </td>
            </tr>
</table>

</body>
</html>