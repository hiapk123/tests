<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<div id="MM">


<div   style=" margin: 80px auto; width: 650px;  height: 400px; border: 5px solid #EE2C2C;">
	<%
			String s_id = request.getParameter("s_id");
			String s_name = request.getParameter("s_name");
			request.setAttribute("s_id", s_id);
			request.setAttribute("s_name", s_name);
		%>
		
	<span>
		<label>1.还没创建过导入数据文件</label> <a
			href="pages/huoliu/do_download.jsp">下载</a></br> <label>2.已创建好导入数据文件，直接导入：</label>
		<br> 
		<button type="button" class="btn btn-success" name="submit"
		onclick="DaoRu()">上传文件</button>
		<%-- <form action="<%=basePath%>huoliu?m=shangchuan"
			enctype="multipart/form-data" method="post">
			上传文件：<input type="file" name="file1"><br /> <input
			id="tj"	type="submit" value="提交">
		</form> --%>
		<input id="DR"	 type="submit" value="导入">
	</span>
</div>
</div>
