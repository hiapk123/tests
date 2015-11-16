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
 * Servlet implementation class shshowedit
 */
@WebServlet(urlPatterns="/shshowedit",name="shshoweditServlet")
public class shshowedit extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doPost(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("历史编辑显示页面");
		int shemptheidd=Integer.parseInt(request.getParameter("shemptheidd").toString());
		List<Object[]> shempshowenditinfom=null;
		String sqlsh="SELECT emp_name,emp_no,emp_tel,emp_status,s_name FROM employee LEFT JOIN store on s_id=store_id WHERE emp_id="+shemptheidd;
		shempshowenditinfom=new MemInformServiceImp().normalfinad(sqlsh);
		//将获取到的列表写入域，这样前台就可以显示�?
		request.setAttribute("shempshowenditinfom", shempshowenditinfom);
		
		//绑定门店名的下拉�?
		String storesql="select s_name from store";
		List<Object[]> storelist=(List<Object[]>)new MemInformServiceImp().normalfinad(storesql);
		request.setAttribute("storelist", storelist);
		
		request.getRequestDispatcher("/pages/emplee/shmodel1.jsp").forward(request, response);	
	}

}
