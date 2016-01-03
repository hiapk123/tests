package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;


@WebServlet(urlPatterns="/shmembergracebath",name="shmembergracebathServlet")
public class shmembergracebath extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memgracesql="select * from vip_type";
		List<Object[]> shmemgrace=(List<Object[]>)new MemInformServiceImp().normalfinad(memgracesql);
		//进行页面的刷新
		
		// 
		request.setAttribute("shmemgrace", shmemgrace);
		request.getRequestDispatcher("pages/member/membergracetable.jsp").forward(request, response);
	}

}
