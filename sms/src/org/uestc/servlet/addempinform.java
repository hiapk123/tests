package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

@WebServlet(urlPatterns="/addempinform",name="addempinformServlet")
public class addempinform extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//鎺ュ彈鍓嶅彴鐨勫弬鏁?
		//System.out.print("新增的处理");
		int shempstatus=Integer.parseInt(request.getParameter("shempstatus").toString());
		String shempstore=request.getParameter("shempstore").toString();
		String shempbh=request.getParameter("shempbh").toString();
		String shempxm=request.getParameter("shempxm").toString();
		String shempdh=request.getParameter("shempdh").toString();
		String empleestore=request.getParameter("empleestore").toString();
		String empleestate=request.getParameter("empleestate").toString();
		int shstidg=0;
		//鍐欏ソsql骞朵笖鎻掑叆閫氱敤绫?
		//1銆佸厛鏍规嵁闂ㄥ簵鍚嶆煡璇㈠嚭闂ㄥ簵鐨刬d
		String sqlid="select s_id from store where s_name="+"'"+shempstore+"'";
		List<Object[]> sholist=null;
		sholist=new MemInformServiceImp().normalfinad(sqlid);
		Object[] ob1= sholist.get(0);
		shstidg=Integer.parseInt(ob1[0].toString());
		//System.out.println("zhendeshi"+shstidg);
		
		//杩涜鎻掑叆鎿嶄綔
		String shsql="insert into employee(emp_name,emp_no,store_id,emp_tel,emp_status) values ("+"'"+shempxm+"'"+","+"'"+shempbh+"'"+","+shstidg+","+"'"+shempdh+"'"+","+shempstatus+")";
		//鎻掑叆鐨勮鍙?
		new MemInformServiceImp().normalupdate(shsql);
		//System.out.println("鎻掑叆鎴愬姛");
		//进行更新的操作
		//System.out.println("更新会员资料页面的servlet");
		String shkksql="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id where s_name="+"'"+empleestore+"'"+" and emp_status="+empleestate+" limit 0,10";
		List<Object[]> shlistd=null;
		shlistd=new MemInformServiceImp().normalfinad(shkksql);
		int currentPage=1;
		int totalPage=0;
		String spdwl="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id where s_name="+"'"+empleestore+"'"+" and emp_status="+empleestate;
		List<Object[]> numlista=new MemInformServiceImp().normalfinad(spdwl);
		totalPage=numlista.size();
		//页面跳转初始化处理
		
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("shlistd", shlistd);
		request.getRequestDispatcher("/pages/emplee/empleetable.jsp").forward(request, response);

		
		
		
		
	}

}
