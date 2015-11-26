<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*,org.uestc.util.DateFormatUtils,java.math.BigDecimal;"%>
	<%@taglib uri="http://www.dky.com/taglibs/page" prefix="page"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
   <script src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
   <script src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="<%=basePath%>js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="<%=basePath%>js/bootstrap-datetimepicker.zh-CN.js"
	charset="utf-8"></script>
</head>
<body>

<script>
function search(){
	
	var s_id=$("#store").val();
	
	var start=$("#start").val();
	var end=$("#end").val();
	var currentPage=null;
	
	if(s_id==null){
		alert("请重新选择店铺！");
		return;
	}
	if(start==""){
		alert("请输入起始时间！");
		return;
	}
	if(end==""){
		alert("请输入结束时间！");
		return;
	}
	$("#fenye").empty();
	$.post("<%=basePath%>huoliu", {
		"m" : "hkhz1",
		"s_id" : s_id,
		"end" :end  ,
		"start" :start  ,
		"currentPage":currentPage,
		
	}, function(data) {
		$("#fenye").append(data);
	}, "html");
	
}
</script>
<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-1 control-label">从</label>
							<div class="col-sm-3">
								<div class="input-group date form_date " data-date=""
									data-date-format="dd MM yyyy" data-link-field="start"
									data-link-format="yyyymmdd">
									<input   class="form-control" size="16" 
										type="text" value="" readonly> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-remove"></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<input type="hidden" id="start" value="" />
							</div>

							<label for="firstname" class="col-sm-1 control-label">到</label>
							<div class="col-sm-3">
								<div class="input-group date form_date " data-date=""
									data-date-format="dd MM yyyy" data-link-field="end"
									data-link-format="yyyymmdd">
									<input class="form-control" size="16" 
										type="text" value="" readonly> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-remove"></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<input type="hidden" id="end" value="" />
							</div>
							<div class="col-sm-4"  style="float: right;">
							<select id="store" class="btn btn-default">
		<!-- <option value="-1" selected="selected" disabled="disabled">选择店铺</option> -->

		<%
			List<Object[]> list0 = (List<Object[]>) request.getAttribute("storeList");
			if (list0 != null && list0.size() != 0) {
				for (Object[] obj : list0) {
		%>
		<option value='<%=obj[0]%>'><%=obj[1]%></option>
		<%
			}
			}
		%>

	</select>
	
		 <input
			type="button" value="查询" onclick="search()" class="btn btn-primary" />
		
	
	</div>
							</div>
							</form>
							
 <script type="text/javascript">
  
	$('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	
</script>
<div data-spy="scroll"
		style="width: 100%; height: 500px; overflow: auto; position: relative;"
		data-offset="10">
<div id="fenye" >

	</div>
	
</div>
</body>
</html>