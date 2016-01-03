package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

@WebServlet(urlPatterns="/sh_xialakuangbath",name="sh_xialakuangbathServlet")
public class sh_xialakuangbath extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String type=request.getParameter("type").toString();
		if("0".equals(type))
		{
			this.sh_storebath(request, response);
		}
		
	
	}
	//编写方法
	private void sh_storebath(HttpServletRequest rq,HttpServletResponse rs) throws ServletException, IOException
	{
		//System.out.println("哈哈哈哈哈");
		String empleestore=rq.getParameter("empleestore").toString();
		//智力设定的是0位开启，1为关闭
		int empleestate=Integer.parseInt(rq.getParameter("empleestate").toString());
		//System.out.println(empleestore);
		//System.out.println(empleestate);
		String sqla="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id where s_name="+"'"+empleestore+"'"+" and emp_status="+empleestate+" limit 0,10";
		List<Object[]> shlistd=new MemInformServiceImp().normalfinad(sqla);
		int currentPage=1;
		int totalPage=0;
		String sqlc="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id where s_name="+"'"+empleestore+"'"+" and emp_status="+empleestate;
		List<Object[]> shlistc=new MemInformServiceImp().normalfinad(sqlc);
		totalPage=shlistc.size();
		rq.setAttribute("shlistd", shlistd);
		rq.setAttribute("currentPage", currentPage);
		rq.setAttribute("totalPage", totalPage);
		rq.getRequestDispatcher("/pages/emplee/empleetable.jsp").forward(rq, rs);	
	}

}
