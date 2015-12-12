

<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



	<button type="button" class="btn btn-success" name="submit"
		onclick="jinhuo()">进货</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" name="submit"
		onclick="diaohuo()">调货</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" name="submit"
		onclick="tuihuo()">退货给供货商</button>
	<div style="float: right;">
	<select id="store" class="singleSelector">
		<option value="" selected="selected" >全部店铺</option>

		<%
			List<Object[]> list = (List<Object[]>) request.getAttribute("storeList");
			if (list != null && list.size() != 0) {
				for (Object[] obj : list) {
		%>
		<option value='<%=obj[0]%>'><%=obj[1]%></option>
		<%
			}
			}
		%>

	</select>

	<select id="category">
	    <option value="全部货单" selected="selected" >全部货单</option>
		<option value="进货单" >门店进货单</option>
		<option value="调拨出货单">调拨出货单</option>
		<option value="调拨进货单">调拨进货单</option>
		<option value="退货单">门店退货单</option>
	</select>

	
		<input class="input-medium search-query" type="text" float:right />
		<button type="button" class="btn btn-success" name="submit"
		onclick="search1()">查询</button>
	</div>
	
	<div data-spy="scroll"
		style="width: 100%; height: 500px; overflow: auto; position: relative;"
		data-offset="10">
 
<div id="findhl">
		
		<table class="table table-bordered table-condensed table-hover table-striped">
		<thead>
			<tr>
			    <th><input id="checkAll" type="checkbox"/></th>
				<th>序号</th>
				<th>操作</th>
				<th>货流单号</th>
				<th>下单时间</th>
				<th>货单类型</th>
				<th>出货门店</th>
				<th>进货门店</th>
				<th>状态</th>
				<th>货流量</th>
				<th>总价</th>
				<th>预付款</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
		
		
	</tbody>

	</table>
		</div>
		
</div>
  <div id="detail">
 
 </div>


