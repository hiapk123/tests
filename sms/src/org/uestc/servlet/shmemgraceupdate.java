package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

@WebServlet(urlPatterns="/shmemgraceupdate",name="shmemgraceupdateServlet")
public class shmemgraceupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("UPDATE memgrace");
		String sskgracelevel=request.getParameter("sskgracelevel").toString();
		String sskgracediscount=request.getParameter("sskgracediscount").toString();
		String sskgracetime=request.getParameter("sskgracetime").toString();
		int sskgraceintegra=Integer.parseInt(request.getParameter("sskgraceintegra").toString());
		//id
		int sskheid=Integer.parseInt(request.getParameter("sskheid").toString());
		//sql语句
		String shllmsql="update vip_type set name="+"'"+sskgracelevel+"'"+", discount="+"'"+sskgracediscount+"'"+",time="+"'"+sskgracetime+"'"+", integral="+sskgraceintegra+"  where id="+sskheid;
		new MemInformServiceImp().normalupdate(shllmsql);
		//进行页面的额刷新
		String memgracesql="select * from vip_type";
		List<Object[]> shmemgrace=(List<Object[]>)new MemInformServiceImp().normalfinad(memgracesql);
		request.setAttribute("shmemgrace", shmemgrace);
		request.getRequestDispatcher("pages/member/membergracetable.jsp").forward(request, response);
		
	}

}
