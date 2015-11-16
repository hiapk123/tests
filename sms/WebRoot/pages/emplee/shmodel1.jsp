<%-- <%@page import="org.omg.CORBA.OMGVMCID"%> --%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
List<Object[]> shkfblist = null;
Object[] omnd = new Object[5];
for (int i = 0; i < 5; i++) {
	omnd[i] = 0;
}
shkfblist = (List<Object[]>) request.getAttribute("shempshowenditinfom");
if (shkfblist != null && shkfblist.size() != 0) {
	System.out.print("进入数据");
	omnd = shkfblist.get(0);

	System.out.print("这里是里面包含的数据" + omnd[0] +"/"+ omnd[1] +"/"+ omnd[2] +"/"+ omnd[3] +"/"+ omnd[4]);
}
%>
<!-- //这里是修改的模态框 -->
	<button style="display: none;" id="shshowmtk"
		class="btn btn-primary btn-lg" data-toggle="modal"
		data-target="#showempmodel">加载模态框</button>
	<div class="modal fade" id="showempmodel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">收银员资料</h4>
				</div>
				<div class="modal-body">
					<!-- //模态框的主体部分 -->
					<div class="panel panel-default">
						<div class="panel-body">
							<table>

								<tr>
									<td>是否启用:</td>
									<td></td>
									<td></td>
									<td><input id="shempeidtqiy" value="<%=omnd[3] %>" type="checkbox" /></td>
								</tr>
							</table>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-body">
							<table>
								<tr>
									<td>*所属门店： 
									<select id="shsecondtable">
										<%
									List<Object[]> listf = null;
									listf = (List<Object[]>) request.getAttribute("storelist");
									if (listf != null && listf.size() != 0) {
										for (Object[] obj : listf) {
											
									%>
									
											<option value="<%=omnd[4]%>"><%=obj[0]%></option>
									<%
					}
					}
				%>

									</select>
									</td>
								</tr>
								<tr>
									<td>*编号：<input id="shsecondbh" type="text" value="<%=omnd[1] %>" />
									</td>
								</tr>
								<tr>
									<td>*姓名：<input id="shsecondxm" type="text" value="<%=omnd[0] %>" /></td>
								</tr>
								<tr>
									<td>电话：<input id="shsecondtel" value="<%=omnd[2] %>" type="text" /></td>
								</tr>

							</table>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button id="shhistroyclgfslbuttom" onclick="shempeditd();" type="button"
						class="btn btn-primary" data-dismiss="modal" data-dismiss="modal">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<script>
	//这里如果要操作就得进行遍历
	$(function(){
		//alert("***");
		var ssk=$("#shsecondtable")
		var ssj=$("#shsecondtable").val();
		//alert(ssj);
		for (i=0;i <ssk.length;i++)
		{
		   //alert(ssk.options[i].value);
		   
		}
		//$("#shsecondtable option[value="+ssj+"]").attr("selected",true);
	})
	</script>

	