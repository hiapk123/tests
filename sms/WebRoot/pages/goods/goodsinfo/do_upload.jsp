<%@ page language="java" import="com.jspsmart.upload.*" 
	pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>文件上传处理页面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		<%
			request.setCharacterEncoding("UTF-8");
			//新建一个SmartUpload对象
			SmartUpload su = new SmartUpload();
			//上传初始化
			su.initialize(pageContext);
			//设定上传限制
			//1.限制每个上传文件的最大长度.
			//su.setMaxFileSize(10000);
			//2.限制总上传数据的长度
			//su.setTotalMaxFileSize(20000);
			//3.设定允许上传的文件(通过扩展名限制)，仅允许doc、txt文件.
			//su.setAllowedFilesList("doc,txt");
			//4.设定禁止上传的文件(通过扩展名限制)，禁止上传带有exe、bat、jsp、htm、html扩展名的文件和没有扩展名的文件
			//su.setDeniedFilesList("ext,bat,jsp,htm,html,,");
			//上传文件
			su.upload();
			//将上传文件全部保存到指定目录
			//int count = su.save("/upload");
			int count = su.save("F:\\");
			
			out.println(count + "个文件上传成功!<br>");
			//利用Request对象获取参数之值
			out.println("test=" + su.getRequest().getParameter("test")
					+ "<br><br>");
			
			//逐一提取上传文件信息，同时可保存文件。
			for (int i = 0; i < su.getFiles().getCount(); i++) {
				SmartFile file = su.getFiles().getFile(i);
				//若文件不存在则继续
				if (file.isMissing())
					continue;
				//显示当前文件信息
				out.println("<table border=1>");
				out.println("<tr><td>表单项名(FieldName)</td><td>"
				+ file.getFieldName() + "</td></tr>");
				out.println("<tr><td>文件长度(Size)</td><td>" + file.getSize()
				+ "</td></tr>");
				out.println("<tr><td>文件名(FileName)</td><td>"
				+ file.getFileName() + "</td></tr>");
				out.println("<tr><td>文件扩展名(FieldExt)</td><td>"
				+ file.getFileExt() + "</td></tr>");
				out.println("<tr><td>文件全名(FieldPathName)</td><td>"
				+ file.getFilePathName() + "</td></tr>");
				out.println("</table><br>");
				//将文件另存
				//file.saveAs("/upload"+file.getFileName());
				//另存到以WEB应用程序的根目录为文件根目录的目录下
				//file.saveAs("/upload"+file.getFileName(),su.SAVE_VIRTUAL);
				//另存到操作系统的跟目录为文件根目录的目录下
				//file.saveAs("c:\\temp\\"+file.getFileName(),su.SAVE_PHYSICAL);
				
				//file.saveAs("F:\\liuyan0\\2\\WebContent\\upload\\"+file.getFileName(),su.SAVE_PHYSICAL);
			}
			
		%>
		<br>
	</body>
</html>

