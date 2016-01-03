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
 * Servlet implementation class moneydetails
 */
@WebServlet(urlPatterns="/moneydetails",name="moneydetailsServlet")
public class moneydetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	

		String sql1="select s_name from store";
		List<Object[]> list1=new MemInformServiceImp().normalfinad(sql1);
		request.setAttribute("shdetails", list1);
		//绑定支付方式
		request.getRequestDispatcher("/pages/member/shchongzhidetails.jsp").forward(request,response);
	}

}
