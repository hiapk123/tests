package org.uestc.servlet;

import cn.itcast.servlet.BaseServlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/YYGKServlet")
public class YYGKServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String initLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "f:/pages/sales/yygk.jsp";
	}

}
