<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<table class="table table-bordered" name="numgettable">
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
<thead>
	<tr>
		<th>会员序号</th>
		<th>操作</th>
		<th>会员号</th>
		<th>姓名</th>
		<th>电话</th>
		<th>会员等级</th>
		<th>余额</th>
		<th>积分</th>
		<th>开卡门店</th>
		<th>开卡时间</th>
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
		<td><%=shnumxh %></td>
		<td>
		<a class="<%=al[0] %>" onclick="shbianji(<%=al[0] %>);" id="shmedit">编辑</a>
		<!-- //设置一个隐藏按钮的值 -->
		<input id="shycan" type="hidden" value="<%=al[0]%>">
		</td>
		<td><%=al[1]%></td>
		<td><%=al[2]%></td>
		<td><%=al[3]%></td>
		<td><%=al[4]%></td>
		<td><%=al[5] %></td>
		<td><%=al[6] %></td>
		<td><%=al[7] %></td>
		<td><%=al[8] %></td>
		</tr>
<% 
	}
		}
%>	
</tbody>
</table>
	<!-- //分页的标签属性 -->
	<input type="hidden" id="page" value="${currentPage}" />
<ul class="pagination" id="shaomempaging">
	<page:htmlPage pageNo="${currentPage}"
		url="http://www.baidu.com"
		totalSum="${totalPage}" showPage="10" pageSize="10" />
</ul>
<%
 int kknum1=(int)request.getAttribute("shshoenum");
int kknum2=(int)request.getAttribute("shshoeyue");
int kknum3=(int)request.getAttribute("shshoejifen");
%>
<input value="<%=kknum1 %>" id="shmemshouinformtable1" type="hidden" />
<input value="<%=kknum2 %>" id="shmemshouinformtable2" type="hidden" />
<input value="<%=kknum3 %>" id="shmemshouinformtable3" type="hidden" />



