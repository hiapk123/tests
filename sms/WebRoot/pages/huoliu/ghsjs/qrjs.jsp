<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="java.util.*,org.uestc.util.DateFormatUtils,java.math.BigDecimal,java.text.DecimalFormat;"

    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<%
String danshu=request.getAttribute("danshu").toString();
String price=request.getAttribute("price").toString();
String su_gd_return=request.getAttribute("su_gd_return").toString();
String numOfGoods=request.getAttribute("numOfGoods").toString();
String su_ps_return=request.getAttribute("su_ps_return").toString();
String ss_pre_price="1";
System.out.println(danshu);
System.out.println(su_gd_return);
DecimalFormat    df   = new DecimalFormat("######0.00");   
%>
<div id="mm">
<div  style=" margin: 80px auto; width: 350px;  height: 300px; border: 5px solid #EE2C2C;">
<center>
                <label id="danshu" value="<%=danshu%>">单数：<%=danshu%></label>
               <label id="numOfGoods"  style=" margin: auto 80px;" value="<%=numOfGoods%>">商品数量：<%=numOfGoods%></label><br>
                <label id="price" value="<%=price%>">总价：<%=price%></label>
                <label id="ss_pre_price"  style=" margin: auto 80px;" value="<%=ss_pre_price%>">预付金额：<%=ss_pre_price%></label><br>
                <label id="su_gd_return" value="<%=Double.parseDouble(su_gd_return)*Double.parseDouble(price)/100%>">固定返利：<%=Double.parseDouble(su_gd_return)*Double.parseDouble(price)/100%></label>
         <label id="su_ps_return" value="<%=Double.parseDouble(su_ps_return)*Double.parseDouble(price)/100%>" style=" margin: auto 80px;">固定返利：<%=Double.parseDouble(su_ps_return)*Double.parseDouble(price)/100%></label>
             <br>   <label >实付：</label>
                <input id="Money" class="pay" value="<%=df.format(Double.parseDouble(price)-Double.parseDouble(ss_pre_price)-Double.parseDouble(su_gd_return)*Double.parseDouble(price)/100-
                		Double.parseDouble(su_ps_return)*Double.parseDouble(price)/100) %>" maxlength="10" type="text">
                <span >元</span>
          
      <p>备注:  </p>      <textarea  id="txt_remarks"></textarea>
<br><br>
<button type="button" class="btn btn-warning" style="width:150px"  name="submit"
  onclick="querenjiesuan1()">确认结算</button>
			
</center>	
 </div>	
 </div>
</body>








</html>