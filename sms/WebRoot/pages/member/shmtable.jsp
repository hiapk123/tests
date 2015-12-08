<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
   <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   <script src="/scripts/jquery.min.js"></script>
   <script src="/bootstrap/js/bootstrap.min.js"></script>
</head>
<script>
//额外加入分页的标签
$(function(){
	//alert("页面加载成功")
	$("#shaomempaging").children('li').children('a').click(function()
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
		
		//直接传递后台进行页面跳转（页面初始化的时候进行分页）
		$.post("<%=basePath%>shmeminforminitpaging",{
			"which":which,
			"pageno":pageno
		
		},function(date){
			
			//alert("分页成功")
			//alert(date);
			$("#shmemtable").empty();
			$("#shmemtable").append(date)
			//$("#shmemtable").empty();		
		},"html");
		
	});
	
});

</script>
<div class="panel panel-default" id="shgettable" >
<table class="table table-striped table-bordered bootstrap-datatable datatable responsive dataTable" name="numgettable">
<thead>
	<tr>
		<th style="text-align: center;"><font size="3px">会员序号</font></th>
		<th><font style="text-align: center;" size="3px">操作</font></th>
		<th><font style="text-align: center;" size="3px">会员号</font></th>
		<th><font style="text-align: center;" size="3px">姓名</font></th>
		<th><font style="text-align: center;" size="3px">电话号码</font></th>
		<th><font style="text-align: center;" size="3px">会员等级</font></th>
		<th><font style="text-align: center;" size="3px">余额</font></th>
		<th><font style="text-align: center;" size="3px">积分</font></th>
		<th><font style="text-align: center;" size="3px">开卡门店</font></th>
		<th><font style="text-align: center;"  size="3px">开卡时间</font></th>
	</tr>
</thead>
<tbody>
<%
	List<Object[]> shlist=(List<Object[]>)request.getAttribute("nlist");
	int shnumxh=0;
	if(shlist!=null&&shlist.size()!=0)
	{
		for(Object[] al:shlist)
		{
			shnumxh++;
	
%>
		<tr>
		<td style="text-align: center;"><font size="3px"><%=shnumxh %></font></td>
		<td style="text-align: center;">
		<a class="<%=al[0] %>"  onclick="shbianji(<%=al[0] %>);" id="shmedit"><font size="3px">编辑</font></a>
		<!-- //设置一个隐藏按钮的值 -->
		<input id="shycan" type="hidden" value="<%=al[0]%>">
		</td>
		<td style="text-align: center;"><font size="3px"><%=al[1]%></font></td>
		<td style="text-align: center;"><font size="3px"><%=al[2]%></font></td>
		<td style="text-align: center;"><font size="3px"><%=al[3]%></font></td>
		<td style="text-align: center;"><font size="3px"><%=al[4]%></font></td>
		<td style="text-align: center;"><font size="3px"><%=al[5] %></font></td>
		<td style="text-align: center;"><font size="3px"><%=al[6] %></font></td>
		<td style="text-align: center;"><font size="3px"><%=al[7] %></font></td>
		<td style="text-align: center;"><font size="3px"><%=al[8] %></font></td>
		</tr>
<% 
	}
		}
%>	
</tbody>
</table>
</div>
<div style="text-align: center;">
	<!-- //分页的标签属性 -->
	<input type="hidden" id="page" value="${currentPage}" />
<ul class="pagination" id="shaomempaging">
	<page:htmlPage pageNo="${currentPage}"
		url="http://www.baidu.com"
		totalSum="${totalPage}" showPage="10" pageSize="10" />
</ul>

</div>
<%
 int kknum1=(int)request.getAttribute("shshoenum");
int kknum2=(int)request.getAttribute("shshoeyue");
int kknum3=(int)request.getAttribute("shshoejifen");
%>
<input value="<%=kknum1 %>" id="shmemshouinformtable1" type="hidden" />
<input value="<%=kknum2 %>" id="shmemshouinformtable2" type="hidden" />
<input value="<%=kknum3 %>" id="shmemshouinformtable3" type="hidden" />



