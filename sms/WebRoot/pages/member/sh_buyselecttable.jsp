<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<%@ page import="java.text.SimpleDateFormat" %>

<table class="table table-striped table-bordered bootstrap-datatable datatable responsive dataTable" name="numgettable">
	<thead>
		<tr>
		<th>商品条码</th>
		<th>购买店铺</th>
		<th>购买日期</th>
		<th>购买数量</th>
		<th>商品售价</th>
		<th>折扣价</th>
		<th>打折数</th>
		</tr>
	</thead>
	<tbody>
	<% 
	String sb="";
	List<Object[]> buyselecttable=(List<Object[]>)request.getAttribute("cmaqsd");
	if(buyselecttable!=null&&buyselecttable.size()!=0)
	{
		for(Object[] obj:buyselecttable)
		{
			//直接将毫秒级时间转化
			String timea=obj[1].toString();
			long timeall=Long.parseLong(timea);
			Date dat=new Date(timeall);
			GregorianCalendar gc = new GregorianCalendar();   
			gc.setTime(dat);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    sb=format.format(gc.getTime()); 	
	%>
		<tr>
		<td><%=obj[0] %></td>
		<td><%=obj[6] %></td>
		<td><%=sb %></td>
		<td><%=obj[2] %></td>
		<td><%=obj[3] %></td>
		<td><%=obj[4] %></td>
		<td><%=obj[5] %></td>
		</tr>	
	<% 
		}
	}
	%>
	</tbody>
</table>