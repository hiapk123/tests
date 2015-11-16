<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<!-- 4.用户编辑的额模态框 -->
	<button id="shyhbj" style="display: none;" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#shhybjmyModal"></button>
		<!-- 模态框（Modal） -->
<div class="modal fade" id="shhybjmyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            	<h4 class="modal-title" id="myModalLabel">
             	  编辑会员信息
           	 </h4>
         	</div>
         <div class="modal-body" style="overflow-y: scroll;height: 350px" >  
           		<div class="panel panel-default">
					<div class="panel-body">
					<table >
					<tr>
					<td>是否启用:</td><td></td><td></td>
					<td><input type="checkbox" id="shcheckbox1f" /></td>
					</tr>

					</table>
					</div>
				</div>
				<%
				List<Object[]> kks=(List<Object[]>)request.getAttribute("nks");
				if(kks!=null&&kks.size()!=0)
				{
					for(Object[] obj:kks)
					{
						
				
				%>
				<!-- ***** -->
				<div class="panel panel-default">
					<div class="panel-body">
					<table >
					<tr>
					<td>*会员编号：<input value="<%=obj[4] %>" id="shhybhf" type="text"  /></td>
					</tr>
					<tr>
					<td>*会员姓名：<input value="<%=obj[15] %>" id="shhyxmf" type="text" /></td>
					</tr>
					<tr>
					<td>
					*会员等级：<select id="sshhydjf"><option value="">--会员等级--</option>
					<%
						List<Object[]> plnnb=(List<Object[]>)request.getAttribute("plmmcn");
						List<Object[]> qqwerp=null;
 						if(plnnb!=null&&plnnb.size()!=0)
						{
							//这里面有一个去重的问题
							for(Object[] mxlp:plnnb)
							{
								
						
								
						
					%>
					<option value="<%=mxlp[0]%>"><%=mxlp[0] %></option>
					<%
							}
						}
						
					%>
					
					</select>
					会员折扣：<input value="<%=obj[9] %>" id="shhyzkf" type="text" />%
					</td>
					</tr>
					<tr>
					<td>
					会员余额：<input id="shhyyef" value="<%=obj[5] %>" style="width: 50px" type="text" />元  会员积分：<input id="shhyjff" value="<%=obj[2] %>" style="width: 50px" type="text" />分
					</td>
					</tr>
					
					<tr>
					<td>*联系电话：<input value="<%=obj[10] %>" id="shlxdhf" type="text" /></td>
					</tr>
					</table>
					</div>
				</div>
				<!-- ********* -->
				<div class="panel panel-default">
					<div class="panel-body">
					<table>
					<tr>
					<td>会员密码：<input value="<%=obj[14]%>" id="shhymmffs" type="password" /></td>
					</tr>
					<tr>
					<td>
					会员生日：
					<!-- //日历插件 -->
					<input id="shhysrf" size="16" type="text" readonly="readonly" value="<%=obj[17] %>"  class="form_datetime">
					<!-- //生日日期结束 -->
					</td>
					</tr>
					<tr>
					<td>开卡日期：
					
					<!-- //导入当前的年月日 -->
					<!-- //日历插件 -->
					<input id="shkaikaf" size="16" type="text" readonly="readonly" value="<%=obj[7] %>"  class="form_datetime">
					<!-- //生日日期结束 -->
					
					</td>
					</tr>
					
					<tr>
					<td>到期时间：
					<!-- //导入日期的时间; -->
					<!-- //日历插件 -->
					<input id="shdaoqif" size="16" type="text" readonly="readonly" value="<%=obj[8] %>"  class="form_datetime">
					<!-- //生日日期结束 -->
					</td>
					</tr>
					<tr>
					<td>允许赊账: <input id="shyxszf" type="checkbox"></td>
					</tr>
					</table>
					
					</div>
				</div>
				
				<!-- //*********** -->
					<div class="panel panel-default">
						<div class="panel-body">
							<table>
							<tr>
							<td>
							QQ号码：<input value="<%=obj[18] %>" id="shqqhaof" type="text">
							</td>
							</tr>
							<tr>
							<td>
							邮箱地址：<input value="<%=obj[19] %>" id="shyouxiangf" type="text">
							</td>
							</tr>
							<tr>
							<td>
							地址：<textarea value="<%=obj[13] %>" id="shdizhif" name="textfield"></textarea>
							</td>
							</tr>
							<tr>
							<td>
							备注：<textarea value="<%=obj[20] %>" id="shbeizhuf" name="textfield"></textarea>
							</td>
							</tr>
							
							</table>
						</div>
					</div>	
				
      </div>  <!-- //主体部分的结束-->
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button value="<%=obj[0] %>" onclick="updatememupdateinfo(<%=obj[0] %>);"  data-dismiss="modal" type="button" class="btn btn-primary">
               保存
            </button>
         </div>
         	<%
					}
				}
					
					%>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
	
	
<!-- //放上去的主要div	 -->
</div>

<script type="text/javascript">
$(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii',autoclose: true,todayBtn: true,  minuteStep: 10});
</script>