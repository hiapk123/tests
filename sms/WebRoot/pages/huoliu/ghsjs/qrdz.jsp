<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<head>
<style type="text/css">

#mm{ display: block;  position: absolute;  top: 0%;  left: 0%;  width: 100%;  height: 100%;  background-color: white;  z-index:1001;  -moz-opacity: 0.7;  opacity:1;  filter: alpha(opacity=80);}
</style>
</head>
<%
String danshu=request.getAttribute("danshu").toString();
String numOfGoods=request.getAttribute("numOfGoods").toString();
String price=request.getAttribute("price").toString();

System.out.println(danshu);
%>

<div id="mm">
<div  style=" margin: 80px auto; width: 350px;  height: 220px; border: 5px solid #EE2C2C;">
<center>
                <label >单数：<%=danshu%></label>
               <label >商品数量：<%=numOfGoods%></label>
                <label >总价：<%=price%></label>
           
      <p>备注:  </p>      <textarea class="fileReport" id="txt_remarks"></textarea>
<br><br><br>
<button type="button" class="btn btn-warning" style="width:150px"  name="submit"
  onclick="querenduizhang1()">确认对账</button>
			
</center>	
 </div>	

 </div>