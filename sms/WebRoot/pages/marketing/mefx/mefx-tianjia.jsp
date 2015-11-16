<%@ page language="java" import="java.util.*,org.uestc.util.PageObject,com.uestc.bean.*"
	
	pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<base href="<%=basePath%>">

<title>优惠券</title>



<script>


$(function(){
	$(".form_datetime").attr("value",CurentTime());
	
})

</script>

</head>

<body>

<div class="panel panel-default">
		<div class="panel-body">
			<div style="float: left; width: 15%;">
				<h4>优惠券</h4>
				
			</div>
				
			<div id="mbt_mefx_head" style="float: right; width: 85%;">			
				<a class="btn btn-primary" onclick="backlist();" href="javascript:void(0)" style="float: right;">返回列表</a>
			</div>
		</div>
	</div>
	<!-- div start -->
	
 <div class="panel panel-default">
 			<div id="mbt_mefx_content_head" class="panel-heading">
 						<div class="panel-heading" id="mbt_mefx_content_head">
 						<input type = 'hidden' id = 'mbt_mefx_active_id' value ='' />
 						优惠券名称：<input type="text"  value='' id="mbt_mefx_active_name">
 						优惠券数量：<input type="text"  value='' id="mbt_mefx_cnum">
 						开始时段:<input  type="text" readonly="readonly"  value=''  id="date_start" class="form_datetime"/>
 						结束时段：<input type="text" readonly="readonly"  value=''   id="date_stop"class="form_datetime"/ >
 						 类型： 
 						  <label class="checkbox-inline">
 						  		<input type="checkbox" value="huiyuanzhuanxiang"  id="optionsRadios1" name="optionsRadiosinline"> 会员专享
 						  </label>
 						 </div>
			</div> 
			<div class="panel-body">
				<div id="mbt_mefx_panel" class="col-sm-12 col-lg-12">
				  		<table class= "col-sm-12 col-lg-12 table tbd" border="1">
				  			<thead class="trBack ">
				  			<tr>
				  			<th style="width: 40%">订单总额</th>
				  			<th style="width: 40%">优惠金额</th>
				  			
				  			<th style="width: 20%">删除</th>
				  			</tr>
				  			</thead>
				  			<tbody id="mefx_daitianjiashangpinxianshi">
				  				
				  			</tbody>
				  		
				  		</table>
				  		<a href="javascript:void(0)" onclick="addgoods('tianjia');">新增明细</a>
				  		<div class="span7 text-center"><a class="text-center	 btn btn-info btn-sm" onclick="saveGoodsToActive(1);" href="javascript:void(0)" style="float:center;">保存返回列表</a></div>
			   </div>

			</div> 
		
</div>
 
</body>
</html>
 