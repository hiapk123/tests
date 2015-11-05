package org.uestc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name="AuditOrderServlet",urlPatterns="/AuditOrderServlet")
public class AuditOrderServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5303344441324211748L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String m=req.getParameter("m");
		if("auditOrder".equals(m)){
			req.getRequestDispatcher("pages/admin/admin-audit-order.jsp").forward(req, resp);
		}
	}
}
