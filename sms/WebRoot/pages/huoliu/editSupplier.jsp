<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.util.*"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%


List <Object[]> x=(List <Object[]>)request.getAttribute("list");

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
$("#s_del option[value='<%=x.get(0)[5]%>']").attr("selected","selected");
$("#su_empower option[value='<%=x.get(0)[9]%>']").attr("selected","selected");
</script>

    
</head>
<body>

<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-2 control-label">门店:</label>
      <div class="col-md-3">
      <input type="text" id="s_name" value="<%=x.get(0)[0]%>"	disabled=true  class="form-control">  
      <input id="s_id" type="hidden" value="<%=x.get(0)[13]%>">
      </div>
       <div class="col-md-1">
     
      </div>
       <label for="secondname" class="col-md-2 control-label">供货商名称:</label>
      <div class="col-md-3">
      <input type="text" id="su_name" value="<%=x.get(0)[1]%>"	 class="form-control">  
      </div>
   </div>
  
</form> 
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-2 control-label">电话:</label>
      <div class="col-md-3">
      <input type="text" id="su_phone" value="<%=x.get(0)[2]%>"	 class="form-control">  
      </div>
       <div class="col-md-1">
     
      </div>
       <label for="secondname" class="col-md-2 control-label">邮箱:</label>
      <div class="col-md-3">
      <input type="text" id="su_email" value="<%=x.get(0)[3]%>"	 class="form-control">  
      </div>
   </div>
  
</form> 
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-2 control-label">联系人:</label>
      <div class="col-md-3">
      <input type="text" id="su_contacter" value="<%=x.get(0)[4]%>"	 class="form-control">  
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
      <input type="text" id="su_ps_return" value="<%=x.get(0)[6]%>"	 class="form-control">  
      </div>
       <div class="col-md-1">
     
      </div>
       <label for="secondname" class="col-md-2 control-label">固定返利点:</label>
      <div class="col-md-3">
      <input type="text" id="su_gd_return" value="<%=x.get(0)[7]%>"	 class="form-control">  
      </div>
   </div>
  
</form> 
 
<li class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-2 control-label">供货商编号:</label>
      <div class="col-md-3">
      <input type="text" id="su_number" disabled=true value="<%=x.get(0)[8]%>"	  class="form-control">  
       
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
      <input type="text" id="su_address" value="<%=x.get(0)[10]%>"	 class="form-control">  
      </div>
   </div>
</form> 
<form class="form-horizontal" role="form">
   <div class="form-group">
      <label for="firstname" class="col-md-2 control-label">备注:</label>
      <div class="col-md-9">
      <input id="su_info" class="form-control" value="<%=x.get(0)[11]%>">
      <input id="su_id" type="hidden" value="<%=x.get(0)[12]%>">
      </div>
   </div>
</form> 

</body>
</html>