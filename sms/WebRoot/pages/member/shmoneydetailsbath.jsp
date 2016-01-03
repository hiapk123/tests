<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.util.Date"%>
<%@page import="org.apache.el.lang.ELSupport"%>
<%@page import="org.uestc.serviceImp.MemInformServiceImp"%>
<%@page import="com.uestc.bean.batchgoods"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//request.getRequestDispatcher(action).forward(request, response);
%>

<%@ page import="java.text.SimpleDateFormat" %>
<!-- 分页头部引用 -->
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<script>
//额外加入分页的标签
$(function(){
	//alert("页面加载成功")
	$("#sh_czmxpaging").children('li').children('a').click(function()
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
		//直接传递后台进行页面跳转（页面初始化的时候进行分页）
		
		var shopname =$("#sh_shopname").val();
		var time11=$("#sstartime").val();
		var time22=$("#ssendtime").val();
		var v_type=$("#shzhifufangshi").val();
		//alert(shopname)
		//alert(time11)
		//alert(time22)
		//alert(v_type)
		$.post("<%=basePath%>sh_chongzhi_paging",{
			"which":which,
			"pageno":pageno,
			"shopname":shopname,
			"time11":time11,
			"time22":time22,
			"v_type":v_type
		
		},function(date){
			//alert("成功");
			$("#sh_moneytable").empty();
			$("#sh_moneytable").append(date);
			
		},"html");
		
	});
	
});

</script>
<table class="table table-bordered" >
	<thead>
		<tr>
			<th>充值门店</th>
			<th>会员卡号</th>
			<th>充值类型</th>
			<th>充值金额</th>
			<th>赠送金额</th>
			<th>收银员</th>
			<th>充值时间</th>
			<th>会员余额</th>
		</tr>
	</thead>
	
	<tbody>
	<%
	List<Object[]> deatailist=(List<Object[]>)request.getAttribute("detailslist");
	String therealtiime="";
	String storename="";	
	String chongzhileixing ="";
	double yue=0;
	if(deatailist!=null&&deatailist.size()!=0)
	{
		for(Object[] obj:deatailist)
		{
			
			//将所有需要显示数据全部提炼出来
			int storeid=Integer.parseInt(obj[0].toString());
			String aas="select s_name from store where s_id="+storeid;
			List<Object[]> jjs=new MemInformServiceImp().normalfinad(aas);
			if(jjs!=null&&jjs.size()!=0)
			{
				Object[] mm=jjs.get(0);
				storename=mm[0].toString();
				
			}
			
			//判断充值类型
			int kkk=Integer.parseInt(obj[2].toString());
			if(kkk==1)
			{
				chongzhileixing="现金";
			}
			else if(kkk==2)
			{
				chongzhileixing="银联卡";
			}
			else if(kkk==3)
			{
				chongzhileixing="在线";
			}
			else
			{
				
			}
			//判断余额
			String ppp=obj[1].toString();//获取的是卡号信息
			String sms="select v_balance from vip where v_card_no="+"'"+ppp+"'";
			List<Object[]> ksdm=new MemInformServiceImp().normalfinad(sms);
			if(ksdm!=null&&ksdm.size()!=0)
			{
				Object[] llh=ksdm.get(0);
				yue=Double.parseDouble(llh[0].toString());
			}
			
			//将时间显示成对应的时间格式
			String haomiaotime=obj[6].toString();
			long hmtime=Long.parseLong(haomiaotime);
			Date dat=new Date(hmtime);
			GregorianCalendar gc = new GregorianCalendar();  
			gc.setTime(dat);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			therealtiime=format.format(gc.getTime()); 
			
	%>
		<tr>
			<td><%=storename %></td>	
			<td><%=obj[1] %></td>
			<td><%=chongzhileixing %></td>
			<td><%=obj[3] %></td>
			<td><%=obj[4] %></td>
			<td><%=obj[7] %></td>
			<td><%=therealtiime %></td>
			<td><%=yue %></td>
		</tr>
	<% 
	
	
	//结束
}

}

	%>	
	</tbody>
</table>
<div style="text-align: center;">
	<!-- //分页的标签属性 -->
	<input type="hidden" id="page" value="${currentPage}" />
<ul class="pagination" id="sh_czmxpaging">
	<page:htmlPage pageNo="${currentPage}"
		url="http://www.baidu.com"
		totalSum="${totalPage}" showPage="10" pageSize="10" />
</ul>

</div>

