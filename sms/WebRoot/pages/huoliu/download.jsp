<%@ page language="java" import="java.util.*,java.io.*"
	pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
	InputStream in = null;
	OutputStream os = null;
	try {
		String path = request.getParameter("path");
		//path=new String(path.getBytes(System.getProperty("file.encoding")),"UTF-8");
		File file = new File(path);

		in = new FileInputStream(file);
		os = response.getOutputStream();

		response.addHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
		response.addHeader("Content-Length", file.length() + "");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/octet-stream");
		int data = 0;
		while ((data = in.read()) != -1) {
			os.write(data);
		}
		//os.close();
	} catch (IOException e) {

	} finally {
		try {
			os.flush();
			os.close();
			in.close();
			os = null;
			response.flushBuffer();
			out.clear();
			out = pageContext.pushBody();
		} catch (IOException e) {

		}
	}
%>
