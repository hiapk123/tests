package org.uestc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class XSDJServlet
 */
@WebServlet("/XSDJServlet")
public class XSDJServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String initLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "f:/pages/sales/xsdj.jsp";
	}

}
