package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

@WebServlet(urlPatterns = "/shmodelcllick", name = "shmodelcllickServlet")
public class shmodelcllick extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String shvidd = request.getParameter("shvid");
		int shvid = Integer.parseInt(shvidd);
		String nslsql = "select * FROM vip a LEFT JOIN store b ON a.s_id=b.s_id WHERE v_id=" + shvid;
		List<Object[]> nks = new MemInformServiceImp().normalfinad(nslsql);
		// 进行页面会员等级的绑定
		String kklssql = "select DISTINCT v_level FROM vip a LEFT JOIN store b ON a.s_id=b.s_id";
		List<Object[]> plmmcn = new MemInformServiceImp().normalfinad(kklssql);
		request.setAttribute("plmmcn", plmmcn);
		// ***
		request.setAttribute("nks", nks);
		request.getRequestDispatcher("/pages/member/shtablemodel.jsp").forward(request, response);

	}

}
