<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
 <form  class="form-horizontal" role="form">
						<div class="form-group">
							<label for="firstname" class="col-sm-2 control-label">单位</label>
							<div class="col-sm-3">
								<select id="g_unit" class="form-control">
								<option value="0"  >无</option>
								<%
										List<Object[]> list00 = (List<Object[]>) request.getAttribute("danwei");
										if (list00 != null && list00.size() != 0) {
											for (Object[] obj : list00) {
									%>
									<option value='<%=obj[0]%>'><%=obj[1]%></option>
									<%
										}
										}
									%>
									<option value='-1' data-toggle="modal" data-target="#myModal100" >新增单位</option>
								</select>
								
							</div>
							<label for="firstname" class="col-sm-2 control-label">数量/单位</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="g_howmuch">
							</div>
						</div>
					</form>