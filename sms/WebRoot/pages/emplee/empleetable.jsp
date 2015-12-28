<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
	<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script>
//额外加入分页的标签
$(function(){
	//alert("页面加载成功")
	$("#shempinformpaging").children('li').children('a').click(function()
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
		//alert(which);
		//alert(pageno);
		var storeide=$("#shmemstore").val();
		var statee=$("#shmemstate").val();
		var shtext=$("#shcheckbox").val();
		//alert(storeide)
		//alert(statee)	
		//alert(shtext)		
		//直接传递后台进行页面跳转（页面初始化的时候进行分页）
		$.post("<%=basePath%>shempleeinformahpaging",{
			"storeide":storeide,
			"statee":statee,
			"shtext":shtext,
			"which":which,
			"pageno":pageno
		
		},function(date){
			//alert(date);
			$("#shempleetable").empty();
			$("#shempleetable").append(date);
		},"html");
		
	});
	
});

</script>
<div>
	<table style="height: 100%" class="table table-bordered" name="numgettable">
			<thred>
			<tr>
				<th><font size="3" >序号</font></th>
				<th><font size="3" >操作</font></th>
				<th><font size="3" >所属门店</font></th>
				<th><font size="3" >工号</font></th>
				<th><font size="3" >姓名</font></th>
				<th><font size="3" >电话</font></th>
				<th><font size="3" >状态</font></th>
			</tr>
			</thred>
			<tbody>
<%
List<Object[]> shtables=(List<Object[]>)request.getAttribute("shlistd");
String shtatue="";
int shmun=0;
if(shtables!=null&&shtables.size()!=0)
{
	for(Object[] all:shtables)
	{
		shmun++;
		System.out.print("dasdasdasd"+all[0]);
		if(Integer.parseInt(all[5].toString())==0)
		{
			shtatue="启用";
		}
		else{
			shtatue="禁用";
			
		}
%>
<tr id="<%=all[0] %>">
<td ><%=shmun %></td>
<td><a id="<%=all[0] %>" class="shclassofname"  onclick="empleebjmodel(<%=all[0]%>);">编辑</a></td>
<td><%=all[1] %></td>
<td><%=all[2] %></td>
<td><%=all[3] %></td>
<td><%=all[4] %></td>
<td><%=shtatue %></td>
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
<ul class="pagination" id="shempinformpaging">
	<page:htmlPage pageNo="${currentPage}"
		url="http://www.baidu.com"
		totalSum="${totalPage}" showPage="10" pageSize="10" />
</ul>

</div>
