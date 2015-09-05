package org.uestc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.HuoliuServiceImp;

/**
 * Servlet implementation class HuoLiuServlet
 */
@WebServlet(urlPatterns = { "/huoliu" })
public class HuoLiuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HuoliuServiceImp huoliu = new HuoliuServiceImp();  
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String m = req.getParameter("m");
		if (m.equals("supplierInfo")) {
			this.supplierInfo(req, resp);
			req.getRequestDispatcher("/pages/huoliu/supplierinfo.jsp").forward(req, resp);
		}
	}

	private void supplierInfo(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String s_id=req.getParameter("s_id");
		req.setAttribute("s_id", s_id);
	}
	

}
	
