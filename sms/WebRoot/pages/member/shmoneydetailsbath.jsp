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