<%@page import="org.apache.el.lang.ELSupport"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
 <%@page import="org.uestc.serviceImp.MemInformServiceImp"%>  
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>   
<script>
$(function(){
	$("#shperformanceofemp").children('li').children('a').click(function(){
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
		//alert(which)
		//alert(pageno)
		//进行页面分页处理
		 var shempperform1=$("#shempperformtstore").val();
	 	 var shempperform2=$("#shempperformemplee").val();
	     var shempperform3=$("#shemppreformfenlei").val();
	     var shempperformtime1=$("#shemperformtime1").val();
	     var shempperformtime2=$("#shemperformtime2").val();
		$.post("<%=basePath%>emperformancepaging",
			{
			"shempperform1":shempperform1,
			"shempperform2":shempperform2,
			"shempperform3":shempperform3,
			"shempperformtime1":shempperformtime1,
			"shempperformtime2":shempperformtime2,
			"which":which,
			"pageno":pageno
		},
		function(date){
			
			 //alert(date);
			 $("#shemperformbodys").empty();
			 $("#shemperformbodys").append(date);
			
		},"html");
	
	})
	
	
})
</script> 
 
<div>
 <table class="table table-bordered" name="numgettable">
	<thred>
			<tr>
				<th>序号</th>
				<th>日期</th>
				<th>收银员</th>
				<th>商品名称</th>
				<th>单价</th>
				<th>数量</th>
				<th>总价</th>
				<th>实收</th>
				<th>利润</th>
				<th>类型</th>
			</tr>
	</thred>  
		<tbody>
<%
List<Object[]> showemperformation=(List<Object[]>)request.getAttribute("shempperformthetable");
if(showemperformation!=null&&showemperformation.size()!=0)
{
	int node=0;
	float sums=0;
	String shshouyinyuanname="";
	String shclxin="";
	for(Object[] obj:showemperformation)
	{
		node++;
		float sx=Float.parseFloat(obj[4].toString());
		float sy=Float.parseFloat(obj[5].toString());
		sums=sx*sy;	
		//将obj【2】的id号码转化成收银员的姓名
		int shkid=Integer.parseInt(obj[2].toString());
		String shswichsql="select emp_name from employee where emp_id="+shkid;
		List<Object[]> empname=(List<Object[]>)new MemInformServiceImp().normalfinad(shswichsql);
		Object[] shobj2=empname.get(0);
		shshouyinyuanname=shobj2[0].toString();//收银员名字
		if("1".equals(obj[8].toString()))
		{
			shclxin="现金支付";
		}
		else if("2".equals(obj[8].toString()))
		{
			shclxin="银联卡支付";
		}
		else if("3".equals(obj[8].toString()))
		{
			shclxin="在线支付";
		}
		else
		{
			shclxin="混合支付";
		}
		
%>
<tr>
<td><%=node %></td>
<td><%=obj[1] %></td>
<td><%=shshouyinyuanname %></td>
<td><%=obj[3] %></td>
<td><%=obj[4] %></td>
<td><%=obj[5] %></td>
<td><%=sums %></td>
<td><%=obj[6] %></td>
<td><%=obj[7] %></td>
<td><%=shclxin %></td>
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
<ul class="pagination" id="shperformanceofemp">
	<page:htmlPage pageNo="${currentPage}"
		url="http://www.baidu.com"
		totalSum="${totalPage}" showPage="10" pageSize="10" />
</ul>

</div>
