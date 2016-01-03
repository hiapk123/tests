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
 * Servlet implementation class shmemgradeupdate
 */
@WebServlet(urlPatterns="/shmemgradeupdate",
			name="shmemgradeupdateServlet")
public class shmemgradeupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("添加之后进行刷新");
		String memgracesql="select * from vip_type";
		List<Object[]> shmemgrace=(List<Object[]>)new MemInformServiceImp().normalfinad(memgracesql);
		request.setAttribute("shmemgrace", shmemgrace);
		request.getRequestDispatcher("pages/member/membergracetable.jsp").forward(request, response);
		
	}

}
