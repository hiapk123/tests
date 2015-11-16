package org.uestc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.TableBatchServiceImp;

/**
 * Servlet implementation class shdelete
 */
@WebServlet(urlPatterns="/shdelete",name="shdeleteservlet")
public class shdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	
		System.out.println("删除的servlet页面");
	    String ids=request.getParameter("ids");
	    System.out.println(ids);
	    new TableBatchServiceImp().datedelete(ids);
	    //数据库判断进行的是删除成功�??
	}

}
