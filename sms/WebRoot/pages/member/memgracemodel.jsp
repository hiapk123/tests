
<!-- 编辑会员等级模态框 -->
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!-- 按钮触发模态框 -->
<button style="display: none" id="shgraceeditclick" class="btn btn-primary btn-lg" data-toggle="modal" 
   data-target="#membergracenomalmodel">
   触发模态框
</button>

<!-- 模态框（Modal） -->
<div class="modal fade" id="membergracenomalmodel" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               编辑会员等级
            </h4>
         </div>
         <div class="modal-body">
           <!--  //****内容部分  -->
           <div class="panel panel-default">
						<div class="panel-body">
						<table>
						<%
						List<Object[]> skmodelgl=(List<Object[]>)request.getAttribute("shgetthesecmodel");
						if(skmodelgl!=null&&skmodelgl.size()!=0)
						{
							for(Object[] mzc:skmodelgl)
							{
								
						
						%>
						<tr>
						<td>
						*等级名称
						<input value="<%=mzc[1] %>" id="shmemdjmcss" type="text"/>
						</td>
						</tr>
						<tr>
						<td>
						*优惠折扣
						<input value="<%=mzc[2] %>" id="shmemyhzkss" type="text" />
						</td>
						<td>(例如8.5折填写85)</td>
						</tr>
					
						</table>
						</div>
			</div>
			
			<div class="panel panel-default">
						<div class="panel-body">
						<table>
						<tr>
						<td>
						自动升级
						<input id="shmemzdsjss" onclick="shselectsecondchecks();" type="checkbox" />
						</td>
						</tr>
						</table>
						</div>
			</div>
		
		<div id="shmemgracetabless" style="display: none" class="panel panel-default">
						<div class="panel-body">
					<table>
					<tr>
					<td>
					1.当会员积分达到
					<input value="<%=mzc[4] %>" id="shmemhyjfss" type="text" style="width: 30px">
					时，自动升级到该等级
					</td>
					</tr>
					
					<tr>
					<td>
					2.该等级的有效期
					<input id="shradio1" value="50" type="radio" name="shmemgdss" />
					永久
					<input id="shradio2" value="<%=mzc[3]%>" type="radio" name="shmemgdss" />
					一年
					</td>
					</tr>
					
					<tr>
					<td>
					3.自动升级，在积分达到要求后第二天生效
					</td>
					</tr>
					<!-- //设置隐藏按钮 -->
					<input id="shhiddenid" style="display: none" value="<%=mzc[3] %>" />
					
						
					</table>
						</div>
		</div>
		         
            <!-- //****内容部分 -->
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button id="<%=mzc[0] %>"  onclick="shmemsavepss(<%=mzc[0] %>);" type="button" class="btn btn-primary" data-dismiss="modal">
               保存
            </button>
            <%
							}
						}
						%>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>