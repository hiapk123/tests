package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

@WebServlet(urlPatterns="/shmemeditgrace",name="shmemeditgraceServlet")
public class shmemeditgrace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("编辑会员等级模态框");
		int shrelid=Integer.parseInt(request.getParameter("shrelid").toString());
		String shlsql="select * from vip_type where id="+shrelid;
		List<Object[]> shgetthesecmodel=new MemInformServiceImp().normalfinad(shlsql);
		request.setAttribute("shgetthesecmodel", shgetthesecmodel);
		request.getRequestDispatcher("/pages/member/memgracemodel.jsp").forward(request, response);
	}

}
