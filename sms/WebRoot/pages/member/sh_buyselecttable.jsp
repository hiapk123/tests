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
<script>
//额外加入分页的标签
$(function(){
	//alert("页面加载成功")
	$("#shmembuypaing").children('li').children('a').click(function()
	{
		var which=$(this).text();
	  	//alert(which);//点击的上衣也下一页和尾页
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
		var textfiled=document.sh_buyform.sh_membuyselect.value;
		var time1=$("#sh_selecttime1").val();
		var time2=$("#sh_selecttime2").val();
		
		//alert(which);
		//alert(pageno);
		//直接传递后台进行页面跳转（页面初始化的时候进行分页）
		$.post("<%=basePath%>shbuyselectpaging",{
			"which":which,
			"pageno":pageno,
			"textfiled":textfiled,
			"time1":time1,
			"time2":time2
		
		},function(date){
			
			//alert("分页成功")
			//alert(date);
			$("#sh_sowthetable").empty();
			$("#sh_sowthetable").append(date)
			//$("#shmemtable").empty();		
		},"html");
		
	});
	
});

</script>

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

<div style="text-align: center;">
	<!-- //分页的标签属性 -->
	<input type="hidden" id="page" value="${currentPage}" />
<ul class="pagination" id="shmembuypaing">
	<page:htmlPage pageNo="${currentPage}"
		url="http://www.baidu.com"
		totalSum="${totalPage}" showPage="10" pageSize="10" />
</ul>

</div>