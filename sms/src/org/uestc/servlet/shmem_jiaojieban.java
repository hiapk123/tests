package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

@WebServlet(urlPatterns="/shmem_jiaojieban",
name="shmem_jiaojiebanServlet")
public class shmem_jiaojieban extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsql="select s_name from store";
		List<Object[]> shjiaojielist=new MemInformServiceImp().normalfinad(newsql);
		request.setAttribute("shjiaojielist", shjiaojielist);
		request.getRequestDispatcher("/pages/emplee/empleelog.jsp").forward(request, response);
		
	}

}
