package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;
import org.uestc.serviceImp.TableBatchServiceImp;

/**
 * Servlet implementation class empleeinit
 */
@WebServlet(urlPatterns="/empleeinit",
name="empleeinitServlet")
public class empleeinit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("员工资料初始�?");
		String empleestore=request.getParameter("empleestore").toString();
		String empleestate=request.getParameter("empleestate").toString();
		System.out.println(empleestore);
		System.out.println(empleestate);
		String sqla="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id";
		List<Object[]> shlistd=new MemInformServiceImp().normalfinad(sqla);
		request.setAttribute("shlistd", shlistd);
		request.getRequestDispatcher("/pages/emplee/empleetable.jsp").forward(request, response);
		
	}

}
