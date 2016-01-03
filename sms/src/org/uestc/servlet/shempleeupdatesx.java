package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;


@WebServlet(urlPatterns="/shempleeupdatesx",
			name="shempleeupdatesxServlet")
public class shempleeupdatesx extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String shsecondtable=request.getParameter("shsecondtable");
		String shsecondbh=request.getParameter("shsecondbh");
		String shsecondxm=request.getParameter("shsecondxm");
		String shsecondtel=request.getParameter("shsecondtel");
		int shkkis=Integer.parseInt(request.getParameter("shkkis").toString());
		//启动状态
		int shempeidtqiy=Integer.parseInt(request.getParameter("shempeidtqiy"));
		//先根据所属门店名查询门店id
		String kkl="select s_id from store where s_name="+"'"+shsecondtable+"'";
		List<Object[]> lsx1=new MemInformServiceImp().normalfinad(kkl);
		Object[] sd=lsx1.get(0);
		int ssid=Integer.parseInt(sd[0].toString());
 		
		
		String sldql="update employee set emp_status="+shempeidtqiy+","+" store_id="+ssid+" ,emp_no="+"'"+shsecondbh+"'"+", emp_name="+"'"+shsecondxm+"'"+", emp_tel="+"'"+shsecondtel+"'"+"where emp_id="+shkkis;
		new MemInformServiceImp().normalupdate(sldql);
	}

}
