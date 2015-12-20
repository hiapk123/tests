package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;


@WebServlet(urlPatterns="/EmpleePeformance",name="EmpleePeformanceServlet")
public class EmpleePeformance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("收银员业绩servlet");
		String leixing=request.getParameter("type");
		//绑定门店ID
		String storesql="select s_name from store";
		List<Object[]> shperformstore=(List<Object[]>)new MemInformServiceImp().normalfinad(storesql);
		request.setAttribute("shperformstore", shperformstore);
		//绑定全部的收银员
		String empleesql="SELECT emp_name from employee WHERE emp_status=1";
		List<Object[]> shperformempname=(List<Object[]>)new MemInformServiceImp().normalfinad(empleesql);
		request.setAttribute("shperformempname", shperformempname);
		
		// 绑定全部的分�?(这里指的是商品分�?)
		String empfenlei="SELECT c_name from category";
		List<Object[]> shperformgoodstype=(List<Object[]>)new MemInformServiceImp().normalfinad(empfenlei);
		request.setAttribute("shperformgoodstype", shperformgoodstype);
		
		
		request.getRequestDispatcher("/pages/emplee/empleeperformance .jsp").forward(request, response);	
		
		
	}

}
