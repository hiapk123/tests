<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">



<script>

	/*第一种形式 第二种形式 更换显示样式*/

	function setTab(m, n) {

		var tli = document.getElementById("menu" + m).getElementsByTagName(
				"button");

		var mli = document.getElementById("main" + m)
				.getElementsByTagName("li");

		for (i = 0; i < tli.length; i++) {

			tli[i].className = i == n ? "btn btn-success" : "";

			mli[i].style.display = i == n ? "block" : "none";

		}

	}

</script>
<%
	String s_id = request.getParameter("s_id");
	String s_name = request.getParameter("s_name");

	String g_barcode = request.getParameter("g_barcode");
	System.out.println("nima" + s_id);
%>

<style>
.center-block {
	  display: block;
	      /*将页面元素设置为块级元素*/
	 
	margin-right
	:
	auto;
	     /*左右居中显示*/
	 
	margin-left
	:
	auto;
}

.main {
	clear: both;
	padding: 8px;
	text-align: center;
}

#main0 li {
	display: none;
	width: 600px;
	height: 350px;
}

#main0 li.block {
	display: block;
}
</style>
<script>
function haha(){
	
	$("#g_vip_price").attr('disabled',true);
	$("#g_vip_price").val("");
	
	
}
function HAHA(){
	
	$("#g_vip_price").attr('disabled',false);
	$("#vip_id").val("1");
}

function save(){
	var s_id=<%=s_id%>;
	var s_name="<%=s_name%>";
	var g_del=$("#g_del option:selected").val();  //获取选中的项
	var g_barcode=$("#g_barcode").val();
	var g_pur_price=$("#g_pur_price").val();
	if(g_pur_price=="") {alert("进货价不能为空"); return;}
	if(isNaN(g_pur_price)){alert("进货价请输入数字"); return;}
	var g_name=$("#g_name").val();
	if(g_name=="") {alert("商品名称不能为空"); return;}
	var g_sale_price=$("#g_sale_price").val();
	if(g_sale_price=="") {alert("销售价不能为空"); return;}
	if(isNaN(g_sale_price)){alert("销售价请输入数字"); return;}
	
	var g_stock_num=$("#g_stock_num").val();
	if(g_stock_num=="") {alert("库存量不能为空"); return;}
	if(isNaN(g_stock_num)){alert("库存量请输入数字"); return;}
	//扩展资料
	var g_pm=$("#g_pm").val();
	var su_name=$("#su_name :selected").text();
	var g_stock_max=$("#g_stock_max").val();
	if(isNaN(g_stock_max)){alert("最大库存量请输入数字"); return;}
	var g_stock_min=$("#g_stock_min").val();
	if(isNaN(g_stock_min)){alert("最小库存量请输入数字"); return;}
	var g_trade_price=$("#g_trade_price").val();
	if(isNaN(g_trade_price)){alert("批发价请输入数字"); return;}
	var g_prod_date=$("#g_prod_date").val();
	var g_giq=$("#g_giq").val();
	if(isNaN(g_trade_price)){alert("保质期请输入数字"); return;}
	var g_vip_price=$("#g_vip_price").val();
	if(isNaN(g_vip_price)){alert("会员价请输入数字"); return;}
	var vip_id=$("#vip_id").val();
	var zdy1=$("#zdy1").val();
	var zdy2=$("#zdy2").val();
	var zdy3=$("#zdy3").val();
	var zdy4=$("#zdy4").val();
	//报表参数
	var g_qd_min=$("#g_qd_min").val();
	var g_cl_min=$("#g_cl_min").val();
	var g_stock_nor=$("#g_stock_nor").val();
	var g_best=$("#g_best").val();
	var g_sale_nor=$("#g_sale_nor").val();
	var g_flag=$("#g_flag input:checkbox:checked").val();
	if(g_flag=="on") {g_flag=1;}
	else{ g_flag=0;}
	var g_integral=$("#g_integral input:checkbox:checked").val();
	if(g_integral=="on") {g_integral=1;}
	else{ g_integral=0;}
	//商品描述，图片路径
	var g_info=$("#g_info").val();
	var g_img_path=$("#g_img_path").val();
	var unit_id=$("#g_unit").val();
	var g_unit=$("#g_unit :selected").text();
    var c_id=$("#c_name0").val();
	
	var c_name=$("#c_name0 :selected").text();
	$("#goodsinfodiv").empty();
	$("#motai").empty();
	$.post("<%=basePath%>goods", {
				"m" : "addGood",
				"s_id" : s_id,
				"s_name" : s_name,
				"g_name" : g_name,
				"g_barcode" : g_barcode,
				"g_del" : g_del,
				"g_pur_price" : g_pur_price,
				"g_sale_price" : g_sale_price,
				"c_name" : c_name,
				"g_stock_num" : g_stock_num,
				//扩展资料
				"g_pm" : g_pm,
				"su_name" : su_name,
				"g_stock_max" : g_stock_max,
				"g_stock_min" : g_stock_min,
				"g_trade_price" : g_trade_price,
				"g_prod_date" : g_prod_date,
				"g_giq" : g_giq,
				"g_vip_price" : g_vip_price,
				"vip_id" : vip_id,
				"zdy1" : zdy1,
				"zdy2" : zdy2,
				"zdy3" : zdy3,
				"zdy4" : zdy4,
				//报表参数
				"g_qd_min" : g_qd_min,
				"g_cl_min" : g_cl_min,
				"g_stock_nor" : g_stock_nor,
				"g_best" : g_best,
				"g_sale_nor" : g_sale_nor,
				"g_flag" : g_flag,
				//商品描述，图片路径
				"g_info" : g_info,
				"g_img_path" : g_img_path,
				//
				"g_integral" : g_integral,
				"c_id":c_id,
				"unit_id":unit_id,
				"g_unit":g_unit,
				
			}, function(data) {
				$("#goodsinfodiv").append(data);
			}, "html");
	$("#tuichu").click();
		
	}
	
</script>
</head>

<body>

	<div id="liuyan">
		<nav class="navbar navbar-default" role="navigation">

			<div>
				<ul class="nav navbar-nav" id="menu0">
					<button type="button" onclick="setTab(0,0)" class="btn btn-success">必填资料</button>
					<button type="button" onclick="setTab(0,1)">扩展资料</button>
					<button type="button" onclick="setTab(0,2)">报表参数</button>
					<button type="button" onclick="setTab(0,3)">商品描述</button>
					<button type="button" onclick="setTab(0,4)">上传图片</button>
				</ul>
			</div>
		</nav>
		<div id="main0">
			<ul>
				<li class="block">

					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">店铺名</label>
							<div class="col-sm-3">
								<input type="text" class="form-control " value="<%=s_name%>"
									disabled="disabled">
							</div>

							<label for="firstname" class="col-sm-2 control-label">商品状态</label>
							<div class="col-sm-3">
								<select id="g_del" class="form-control">
									<option value="1">启用</option>
									<option value="0">禁用</option>
								</select>
							</div>

						</div>
					</form>

					<form class="form-horizontal" role="form">
						<div class="form-group">

							<label for="firstname" class="col-sm-2 control-label">商品条码</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="g_barcode"
									value="<%=g_barcode%>" placeholder="请输入名称">
							</div>
							<div class="form-group">
								<label for="firstname" class="col-sm-2 control-label">进货价</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" id="g_pur_price"
										value="">
								</div>
							</div>
						</div>
					</form>

					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">商品名称</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="g_name">
							</div>
							<label for="firstname" class="col-sm-2 control-label">销售价</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="g_sale_price">
							</div>
						</div>
					</form>

					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">商品分类</label>
							<div class="col-sm-3">
								<select id="c_name0" class="form-control">
							<option value="-1"  >无</option> 
									<%
										List<Object[]> list1 = (List<Object[]>) request.getAttribute("fenlei");
										if (list1 != null && list1.size() != 0) {
											for (Object[] obj : list1) {
									%>
									<option value='<%=obj[0]%>'><%=obj[1]%></option>
									<%
										}
										}
									%>
								</select>
							</div>
							<label for="firstname" class="col-sm-2 control-label">库存</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" value="0"
									id="g_stock_num">
							</div>
						</div>
					</form>
					
					<div id="newdw">
                  <form  class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">单位</label>
							<div class="col-sm-3">
								<select id="g_unit" class="form-control">
								<option value="-1" selected="selected" >无</option>
								<%
										List<Object[]> list0 = (List<Object[]>) request.getAttribute("danwei");
										if (list0 != null && list0.size() != 0) {
											for (Object[] obj : list0) {
									%>
									<option value='<%=obj[0]%>'><%=obj[1]%></option>
									
									<%
										}
										}
									%>
									<option value='0' data-toggle="modal" data-target="#myModal100" >新增单位</option>
								</select>
								
							</div>
							
						</div>
					</form>
					</div>
					<form class="form-horizontal" role="form">
						<div class="form-group">
						<div class="col-sm-10">
							<p><font size="3" face="arial" color="green">说明:单位数量表示一箱，盒等等有多少个商品，例如一箱啤酒有10瓶,
							如果是单一商品，那么单位数量为1.
						</font></p>
						</div>
							
						</div>
					</form>
					
					
					
				</li>
				<!--扩展资料-->
				<li>
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">拼音码</label>
							<div class="col-sm-3">
								<input type="text" id="g_pm" class="form-control">
							</div>

							<label for="firstname" class="col-sm-2 control-label">供货商</label>
							<div class="col-sm-4">

								<select class="form-control" id="su_name">
									<%
										List<Object[]> list = (List<Object[]>) request.getAttribute("suppliers");
										if (list != null && list.size() != 0) {
											for (Object[] obj : list) {
									%>
									<option value='<%=obj[0]%>'><%=obj[1]%></option>
									<%
										}
										}
									%>
								</select>

							</div>
						</div>
					</form>


					<form class="form-horizontal" role="form">
						<div class="form-group">

							<label for="firstname" class="col-sm-2 control-label">库存上限</label>
							<div class="col-sm-3">
								<input type="text" id="g_stock_max" class="form-control">
							</div>
							<label for="firstname" class="col-sm-2 control-label">库存下限</label>
							<div class="col-sm-4">
								<input type="text" id="g_stock_min" class="form-control">
							</div>
						</div>

					</form>

					<div class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">批发价</label>
							<div class="col-sm-3">
								<input type="text" id="g_trade_price" class="form-control">
							</div>
							<label for="firstname" class="col-sm-2 control-label">会员优惠</label>
							<div class="col-sm-4">
								<div class="radio">
									<label> <input type="radio" name="optionsRadios"
										id="vip_id" onclick="haha()" value="0" checked> 会员折扣
									</label> <label> <input type="radio" name="optionsRadios"
										id="optionsRadios2" onclick="HAHA()" value="option2">
										会员价
									</label> <input type="text" class="form-control" id="g_vip_price"
										value="" disabled=true>
								</div>
							</div>
						</div>
					</div>



					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">生产日期</label>
							<div class="col-sm-4">
								<div class="input-group date form_date " data-date=""
									data-date-format="dd MM yyyy" data-link-field="g_prod_date"
									data-link-format="yyyymmdd">
									<input class="form-control" size="16" 
										type="text" value="" readonly> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-remove"></span></span> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-calendar"></span></span>
								</div>
								<input type="hidden" id="g_prod_date" value="" />
							</div>
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
							<label for="firstname" class="col-sm-2 control-label">保质期（天）</label>
							<div class="col-sm-3">
								<input type="text" id="g_giq" class="form-control">
							</div>
						</div>
					</form>

					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">自定义1</label>
							<div class="col-sm-3">
								<input type="text" id="zdy1" class="form-control">
							</div>
							<label for="firstname" class="col-sm-2 control-label">自定义2</label>
							<div class="col-sm-4">
								<input type="text" id="zdy2" class="form-control">
							</div>
						</div>
					</form>

					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">自定义3</label>
							<div class="col-sm-3">
								<input type="text" id="zdy3" class="form-control">
							</div>
							<label for="firstname" class="col-sm-2 control-label">自定义4</label>
							<div class="col-sm-4">
								<input type="text" id="zdy4" class="form-control">
							</div>
						</div>
					</form>
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<div class="col-sm-1"></div>
							<div class="col-sm-2">
								<div class="checkbox" id="g_integral">
									<label> <input type="checkbox"> 积分商品
									</label>
								</div>
							</div>


						</div>
					</form>

				</li>
				<!-- 报表参数 -->
				<li>
					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">最小起订量</label>
							<div class="col-sm-3">
								<input type="text" id="g_qd_min" class="form-control">
							</div>

							<label for="firstname" class="col-sm-2 control-label">畅销量</label>
							<div class="col-sm-3">
								<input type="text" id="g_best" value="       "
									class="form-control">
							</div>

						</div>
					</form>

					<form class="form-horizontal" role="form">
						<div class="form-group">

							<label for="firstname" class="col-sm-2 control-label">最低陈列量</label>
							<div class="col-sm-3">
								<input type="text" id="g_cl_min" class="form-control">
							</div>
							<div class="form-group">
								<label for="firstname" class="col-sm-2 control-label">正常销售量</label>
								<div class="col-sm-3">
									<input type="text" id="g_sale_nor" class="form-control">
								</div>
							</div>
						</div>
					</form>

					<form class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">库存合理值</label>
							<div class="col-sm-3">
								<input type="text" id="g_stock_nor" class="form-control">
							</div>
							<div class="col-sm-1"></div>
							<div class="col-sm-3">
								<div class="checkbox" id="g_flag">
									<label> <input type="checkbox"> 是否锁定
									</label>
								</div>
							</div>
						</div>
					</form>



				</li>


				<!-- 商品描述 -->
				<li>
					<div class="form-horizontal" role="form">
						<div class="form-group">
							<div class="col-sm-9">
								<input type="text" id="g_info" value="" class="form-control"
									style="height: 300px">
							</div>
						</div>
					</div>

				</li>
				<script language="javascript" src="<%=basePath%>js/ajaxfileupload.js" > </script>
<script type="text/javascript">
	function sb()
	{
		var s_id=<%=s_id%>;
		var s_name="<%=s_id%>";
       $.ajaxFileUpload({
                 url:'<%=basePath%>goods?m=picture&s_id='+s_id+"&s_name="+s_name,             //需要链接到服务器地址
                 secureuri:false,
                 fileElementId:'uploadFileInput',                         //文件选择框的id属性
                 dataType: 'json',                                     //服务器返回的格式，可以是json
                 success: function (data)             //相当于java中try语句块的用法
                 {   
                	 $('#result').html('上传图片成功!请复制图片地址<br/>');
                     $('#picture').attr('src',"img/"+data.name);
                     $('#g_img_path').val("img/"+data.name);
                 },
                 error: function (data)             //相当于java中catch语句块的用法
                 {
                	 $('#result').html('上传图片失败');
                 }
               }
             );
    }
	
	
	</script>
				<!-- 上传图片 -->
				<li>

					<div class="form-horizontal" role="form">
						<div class="form-group">
							<div class="col-sm-9">
								 <img id="picture"  src="" style="width:300px;height:300px">
								<input type="hidden" id="g_img_path" value="">
							</div>
						</div>
					</div>
					<div class="form-horizontal" role="form">
						<div class="form-group">
							<div class="col-sm-6">
								<input id="uploadFileInput" type="file" size="45"
									name="uploadFileInput"
									class="input btn btn-success form-control" />
							</div>
							<div class="col-sm-3">
								<input type="button" id="buttonUpload" onclick="sb()"
									class="form-control btn btn-primary" value="上   传" />
							</div>
						</div>
					</div>
  
                 <div id="result" style="FONT:12px 宋体">
                   
  
                     </div>
				</li>


			</ul>
		</div>
	</div>



</body>
</html>

<!-- 模态框  新增单位（Modal） -->
<div class="modal fade" id="myModal100" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true" >
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
           
         </div>
            
      <!-- 模态框本质内容 --> 
      <div id="motai100">
      <script>
					
					function QR(){
						var s_id=<%=s_id%>;
						var g_unit=$("#g_unit1").val();
						var g_howmuch=$("#g_howmuch1").val();
						
						
						$.post("<%=basePath%>goods", {
							"m" : "newdw",
							"g_unit":g_unit,
							"g_howmuch":g_howmuch,
							"s_id":s_id,
						}, function(data) {
							//$("#newdw").append(data);
							if(data!="buhege"){
								$("#newdw").empty();
								$("#newdw").append(data);
								
							}else{
								alert("该单位已存在");
								return;
							}
						}, "html");
						//$("#g_unit :selected").text($("#DW").val());
						$("#myModal100").hide();
					}
					</script>
      <div id="dw1" class="form-horizontal" role="form" >
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label" ><font size="3" face="arial" color="red">新增单位:</font></label>
							<div class="col-sm-3">
								<input type="text" id="g_unit1" value="" class="form-control" >
							</div>
							<label for="firstname" class="col-sm-2 control-label" ><font size="3" face="arial" color="red">数量/单位:</font></label>
							<div class="col-sm-3">
								<input type="text" id="g_howmuch1" value="" class="form-control" >
							</div>
							<div class="col-sm-3">
								<button class="btn btn-danger" onclick="QR()">确认</button>
							</div>
						</div>
					</div>
   </div>    
     <!-- 模态框本质内容 -->   
         <div class="modal-footer">
        
         
            
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>