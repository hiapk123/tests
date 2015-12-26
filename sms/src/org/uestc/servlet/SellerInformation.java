package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

@WebServlet(urlPatterns="/SellerInformation",name="SellerInformationServlet")
public class SellerInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//("导购员资料Servlet");
		//绑定商品的id的名�?
		//绑定门店ID
		String storesql="select s_name from store";
		List<Object[]> shpsellerinform=(List<Object[]>)new MemInformServiceImp().normalfinad(storesql);
		request.setAttribute("shpsellerinform", shpsellerinform);
		request.getRequestDispatcher("pages/emplee/sellerinformation.jsp").forward(request, response);	
		
		
		
		
	}

}
