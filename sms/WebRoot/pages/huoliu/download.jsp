<%@ page language="java" import="java.util.*,java.io.*"
	pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
   
	String path=request.getParameter("path");
	path=new String(path.getBytes("gbk"));
	//path=new String(path.getBytes("GBK"),"UTF-8"); 
	File file = new File(path);
	InputStream in = new FileInputStream(file);
	OutputStream os = response.getOutputStream();
	response.addHeader("Content-Disposition", "attachment;filename="
			+ new String(file.getName().getBytes("gbk"),"iso-8859-1"));
	response.addHeader("Content-Length", file.length() + "");
	response.setCharacterEncoding("utf-8");
	response.setContentType("application/octet-stream");
	int data = 0;
	while ((data = in.read()) != -1) {
		os.write(data);
	}
	os.close();
	in.close();
%>
