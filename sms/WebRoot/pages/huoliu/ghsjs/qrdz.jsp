<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>



<%
String danshu=request.getAttribute("danshu").toString();
String numOfGoods=request.getAttribute("numOfGoods").toString();
String price=request.getAttribute("price").toString();



System.out.println(danshu);
%>

<form class="form-horizontal" role="form">
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">单数:</label>
		<div class="col-sm-6">
			<input class=" form-control" value="<%=danshu%>" 
				type="text">
		</div>

	</div>
</form>
<form class="form-horizontal" role="form">
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">商品数量:</label>
		<div class="col-sm-6">
			<input class=" form-control" value="<%=numOfGoods%>" 
				type="text">
		</div>

	</div>
</form>

 <form class="form-horizontal" role="form">
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">总价:</label>
		<div class="col-sm-6">
			<input class=" form-control" value="<%=price%>" 
				type="text">
		</div>

	</div>
</form>             
               
  <form class="form-horizontal" role="form">
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">备注:</label>
		<div class="col-sm-6">
			<input id="txt_remarks" class=" form-control" value="" 
				type="text">
		</div>

	</div>
</form>              
           
    <div class="form-horizontal" role="form">
	<div class="form-group">
		<div class="col-sm-2"></div>
		<div class="col-sm-6">
			<button type="button" class="btn btn-warning" style="width:150px"  name="submit"
  onclick="querenduizhang1()">确认对账</button>
		</div>

	</div>
</div>     

			

 