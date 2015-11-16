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


<!-- 分页头部引用 -->
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page" %>


<script>
//分页标签的点击事件的属性
//分页标签的使用
//页面加载信息的时候使用$(function({}))
$(function(){
	   //alert($("#tablediv li a"));
	   $("#shpageing li a").click(function(){
	  	var which=$(this).text();
	  	//alert(which);//需要跳转的页数
	  	//alert("哈哈");
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
	  	//alert("当前页数jjjjj"+pageno);
	  	var shopname=$("#shselectstore").val();
	  	var shzhuangtai=$("#shleibie").val();
	  	var shfenlei=$("#shfenlei").val();
	  	var shsuname=$("#shsuname").val();
	  	var shshangpin=$("#shshangpin").val();
	  	//alert("当前页数1"+shopname);
	  	//alert("当前页数2"+shzhuangtai);
	  	//alert("当前页数3"+shfenlei);
	  	//alert("当前页数4"+shsuname);
	  	//alert("当前页数5"+shshangpin); 	
	  	//AJAX进行表单提交
	  	$.post("<%=basePath%>shfenye",{
	  		"shopname":shopname,
	  		"shzhuangtai":shzhuangtai,
	  		"shfenlei":shfenlei,
	  		"shsuname":shsuname,
	  		"shshangpin":shshangpin,
	  		"which":which,
	  		"pageno":pageno
	  	},function(data){
	  		//alert(data);
	  		$("#shtable").empty();//清空数据
	  		$("#shtable").append(data);
	  		
	  	},"html")
	  	
	  });
	
	
});
</script>
<%
	//int tiaomazhuangtai=Integer.parseInt(request.getAttribute("tiaomazhuangtai").toString()) ;
	//System.out.print("条码状态"+tiaomazhuangtai);
%>
<div id="tablediv">
<%--  <%
	int splnum=(int)request.getAttribute("shgoodsnumbers");
%> --%>
<%-- <input value="<%=splnum %>" type="hidden" id="shdsacqqq" /> --%>

<table class="table table-bordered" id="pagetable" name="numgettable" >
<thead>
<tr>
	<th><input type="checkbox" onchange="yemianclick();"></th>
	<th>序号</th>
	<th>商品名称</th>
	<th>条码</th>
	<th>商品分类</th>
	<th>会员优惠</th>
	<th>供货商</th>
	<th>状态</th>
	<th>积分</th>
	<th>提成规则</th>
</tr>
</thead>
<tbody>
<%
	List<batchgoods> showlist=(List<batchgoods>)request.getAttribute("shlist");
	int shhhhs=0;
	if(showlist!=null&&showlist.size()!=0){
	for(batchgoods obj:showlist){
		shhhhs++;
		String shthezhuangtai;
		//设置的是0是用来启用的。
		if(obj.getG_flag()==0){
			shthezhuangtai="启用";
		}
		else{
			shthezhuangtai="禁用";
		}
	
%>
<tr>
<!-- 获取选中条目的商品id -->
	<td><input type="checkbox" name="shshuliang" id="sh button1" onclick="shnum();" value="<%=obj.getG_id() %>"></td>
	<td><%=shhhhs %></td>
	<td><%=obj.getG_name() %></td>
	<td><%=obj.getG_barcode() %></td>
	<td><%=obj.getC_name() %></td>
	<td><%=obj.getG_pur_price() %></td>
	<td><%=obj.getSu_name() %></td>
	<td><%=shthezhuangtai %></td>
	<td><%=obj.getG_integral() %></td>
	<td><%=obj.getCome_name() %></td>
</tr>	
	<%
		}
	}
	%>	

</tbody>
</table>	
	<!-- //分页的标签属性totalPage  currentPage ${currentPage} -->
	<input type="hidden" id="page" value="${currentPage}" />
<ul class="pagination" id="shpageing">
	<page:htmlPage pageNo="${currentPage}"
		url="http://www.baidu.com"
		totalSum="${totalPage}" showPage="10" pageSize="10" />
</ul>
</div>



