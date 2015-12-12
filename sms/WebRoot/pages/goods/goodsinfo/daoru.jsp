<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script language="javascript" src="<%=basePath%>js/ajaxfileupload.js" > </script>

<body>
	
<script type="text/javascript">
	function sb()
	{
		var s_id=$("#store2").val();
		var s_name=$("#store2 :selected").text();
       $.ajaxFileUpload({
                 url:'<%=basePath%>goods?m=Shangchuanwenjian&s_id='+s_id+"&s_name="+s_name,             //需要链接到服务器地址
                 secureuri:false,
                 fileElementId:'uploadFileInput',                         //文件选择框的id属性
                 dataType: 'json',                                     //服务器返回的格式，可以是json
                 success: function (data)             //相当于java中try语句块的用法
                 {   
                
                	 $('#result').html(data.message.replace(/;/g, "<br/>"));
                 },
                 error: function (data)             //相当于java中catch语句块的用法
                 {
                	 alert(data.message);
                     $('#result').html(data.message);
                 }
               }
             );
    }
	
	
	</script>



	
	<div class="form-horizontal" role="form">
   <div class="form-group">
      <div class="col-sm-6">
     
        <input type="text"  value="1.还没创建过导入数据文件"	disabled=true class="form-control"> 
      </div>
     
      <div class="col-sm-3">
      <A HREF="pages/huoliu/download.jsp?path=<%=getServletContext().getRealPath("/upload/商品导入模板.xls") %>" class="form-control btn btn-primary">下载</A>
      
      </div>
   </div>
   </div>


<form class="form-horizontal" role="form">
   <div class="form-group">
     <div class="col-sm-6">
     
        <input type="text"  value="2.已创建好导入数据文件，直接导入"	disabled=true class="form-control"> 
      </div>
   </div>
</form>
   
  <form class="form-horizontal" role="form">
   <div class="form-group">
  
     <div class="col-sm-6">
     
        <input type="text"  value="3.店铺名"	disabled=true class="form-control"> 
      </div>
      
      
     
      <div class="col-sm-3">
        <select id="store2" class="singleSelector form-control">
		

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
      </div>
   </div>
</form>  

		
  <div class="form-horizontal" role="form">
   <div class="form-group">
     <div class="col-sm-6">
      <input id="uploadFileInput" type="file" size="45" name="uploadFileInput" class="input btn btn-success form-control" />
      </div>
      <div class="col-sm-3">
      <input type="button" id="buttonUpload" onclick="sb()" class="form-control btn btn-primary" value="上   传"/>
      </div>
   </div>
  </div> 
 
 
  <div id="result" style="FONT:12px 宋体">
 <p>说明：如果导入的商品，系统中没有，进行新增，否则进行更新</p>
  
  </div>
	
	
	
		







</body>
</html>
