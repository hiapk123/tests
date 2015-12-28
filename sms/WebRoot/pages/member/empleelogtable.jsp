<%@page import="com.uestc.bean.batchgoods"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//request.getRequestDispatcher(action).forward(request, response);
%>


<!-- 分页头部引用 -->
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<script>
$(function(){
	$("#shligofjiaojieban").children('li').children('a').click(function(){
		var which=$(this).text();
		if(which=="首页")
	  	{
	  	    which="first";	
	  		
	  	}
	  	else if(which=="上一页")
	  	{
	  		
	  		which="prev";	
	  	}
	  	else if(which=="下一页")
	  	{
	  		which="next"
	  		
	  	}
	  	else if (which=="尾页") 
	  	{
	  		which="last"	
	  	}
	  	else{
	  		
	  	
	  	} 	
		var pageno=$("#page").val();//当前的页数
		//提交进行分页的处理
		var shshopname=$("#emplogshopname").val();
		var time1=$("#logfirstname").val();
		var time2=$("#shlogthestime").val();
		
		$.post("<%=basePath%>shlogpaging",{
			"shshopname":shshopname,
			"time1":time1,
			"time2":time2,
			"which":which,
			"pageno":pageno
		}
		,function(date){
			//alert(date);
			$("#shshshoewtabled").empty();
			$("#shshshoewtabled").append(date);
			
			
		},"html")
		
	})
	
	
})

</script>

<table class="table table-bordered" >
<thead>
	<tr>
		<th>开始时间</th>
		<th>结束时间</th>
		<th>员工编号</th>
		<th>员工姓名</th>
		<th>收银单数</th>
		<th>收银总额</th>
		<th>现金数量</th>
		<th>刷卡数量</th>
		
	</tr>
</thead>
	<tbody>
	<% 
	List<Object[]> showloglist=(List<Object[]>)request.getAttribute("longlist3");
	if(showloglist!=null&&showloglist.size()!=0)
	{
		for(Object[] obj:showloglist)
		{
			

	%>
	<tr>
		<td><%=obj[0] %></td>	
		<td><%=obj[1] %></td>	
		<td><%=obj[2] %></td>	
		<td><%=obj[3] %></td>	
		<td><%=obj[4] %></td>	
		<td><%=obj[5] %></td>	
		<td><%=obj[6] %></td>	
		<td><%=obj[7] %></td>	
		
	</tr>
<% 

	}
}

%>
	</tbody>

</table>

<!-- //分页标签的属性 -->
<div style="text-align: center;">
	<!-- //分页的标签属性 -->
	<input type="hidden" id="page" value="${currentPage}" />
<ul class="pagination" id="shligofjiaojieban">
	<page:htmlPage pageNo="${currentPage}"
		url="http://www.baidu.com"
		totalSum="${totalPage}" showPage="10" pageSize="10" />
</ul>

</div>