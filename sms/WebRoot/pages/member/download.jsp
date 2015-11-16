<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="org.uestc.util.shexcelutil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String patht=request.getRealPath("/")+"/会员信息.xls";
shexcelutil.exportdata(patht);
out.println("<a href='/sms/会员信息.xls'>下载文件</a>");
%>
</body>
</html>