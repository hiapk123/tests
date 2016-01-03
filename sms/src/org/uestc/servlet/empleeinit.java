package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

/**
 * Servlet implementation class empleeinit
 */
@WebServlet(urlPatterns = "/empleeinit", name = "empleeinitServlet")
public class empleeinit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String empleestore = request.getParameter("empleestore").toString();
		// 智力设定的是0位开启，1为关闭
		String empleestate = request.getParameter("empleestate").toString();

		// System.out.println(empleestore);
		// System.out.println(empleestate);
		int currentPage = 1;
		int current = 10 * (currentPage - 1);
		String sqla = "select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id  where s_name="
				+ "'" + empleestore + "'" + " and emp_status=" + empleestate + " limit " + current + " ,10";

		List<Object[]> shlistd = new MemInformServiceImp().normalfinad(sqla);

		String sspps = "select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id  where s_name ="
				+ "'" + empleestore + "'" + " and emp_status=" + empleestate;
		List<Object[]> shzhongjianlist = new MemInformServiceImp().normalfinad(sspps);
		int totalPage = shzhongjianlist.size();

		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("shlistd", shlistd);
		request.getRequestDispatcher("/pages/emplee/empleetable.jsp").forward(request, response);

	}

}
