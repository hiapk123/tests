<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
function music(randomno)
{

$("#su_number").val(randomno);
}
</script>

    
</head>
<body>
<%
String s_id=request.getAttribute("s_id").toString();
String s_name=request.getAttribute("s_name").toString();
%>
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-2 control-label">门店:</label>
      <div class="col-md-3">
      <input type="text" id="s_name" value="<%=s_name %>"	disabled=true  class="form-control">  
      </div>
       <div class="col-md-1">
     
      </div>
       <label for="secondname" class="col-md-2 control-label">供货商名称:</label>
      <div class="col-md-3">
      <input type="text" id="su_name" value=""	 class="form-control">  
      </div>
   </div>
  
</form> 
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-2 control-label">电话:</label>
      <div class="col-md-3">
      <input type="text" id="su_phone" value=""	 class="form-control">  
      </div>
       <div class="col-md-1">
     
      </div>
       <label for="secondname" class="col-md-2 control-label">邮箱:</label>
      <div class="col-md-3">
      <input type="text" id="su_email" value=""	 class="form-control">  
      </div>
   </div>
  
</form> 
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-2 control-label">联系人:</label>
      <div class="col-md-3">
      <input type="text" id="su_contacter" value=""	 class="form-control">  
      </div>
       <div class="col-md-1">
     
      </div>
       <label for="secondname" class="col-md-2 control-label">是否启用:</label>
      <div class="col-md-3">
    
  <select id="s_del" class="form-control">
					<option value="1">是</option>
					<option value="0">否</option>
			    </select> 
      </div>
   </div>
   
</form> 
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-2 control-label">配送费返点:</label>
      <div class="col-md-3">
      <input type="text" id="su_ps_return" value=""	 class="form-control">  
      </div>
       <div class="col-md-1">
     
      </div>
       <label for="secondname" class="col-md-2 control-label">固定返利点:</label>
      <div class="col-md-3">
      <input type="text" id="su_gd_return" value=""	 class="form-control">  
      </div>
   </div>
  
</form> 
 
<li class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-2 control-label">供货商编号:</label>
      <div class="col-md-3">
      <input type="text" id="su_number" value=""	  class="form-control">  
       
      </div>
       <div class="col-md-1">
    <button onclick="music(Math.floor(Math.random()*1000+1))" class="btn btn-primary ">随机生成</button>
      </div>
       <div class="col-md-1">
    
    
      </div>
       <label for="secondname" class="col-md-2 control-label">是否授权:</label>
      <div class="col-md-2">
    
  <select id="su_empower" class="form-control">
					<option value="1">是</option>
					<option value="0">否</option>
			    </select> 
      </div>
   </div>
  
</li> 
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-2 control-label">地址:</label>
      <div class="col-md-9">
      <input type="text" id="su_address" value=""	 class="form-control">  
      </div>
   </div>
</form> 
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-2 control-label">备注:</label>
      <div class="col-md-9">
      <textarea id="su_info" class="form-control"></textarea>
      </div>
   </div>
</form> 

</body>
</html>