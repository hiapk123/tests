package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;


@WebServlet(urlPatterns="/EmpleeDateInform",
name="EmpleeDateInform")
public class EmpleeDateInform extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("收银员资料servlet");
		String leixing=request.getParameter("type");
		
		//绑定门店ID
		String storesql="select s_name from store";
		List<Object[]> storelist=(List<Object[]>)new MemInformServiceImp().normalfinad(storesql);
		List<Object[]> shempshowenditinfom=null;
		request.setAttribute("shempshowenditinfom", shempshowenditinfom);
		request.setAttribute("storelist", storelist);
		request.getRequestDispatcher("/pages/emplee/EmpleeInformation.jsp").forward(request, response);	
	}

}
