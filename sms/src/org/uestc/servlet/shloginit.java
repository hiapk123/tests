package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

import com.uestc.bean.meminform;

@WebServlet(urlPatterns="/shloginit",name="shloginitServlet")
public class shloginit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("交接班记录初始化的Servlet");
		String lonname=request.getParameter("lonname").toString();
		String timestart=request.getParameter("timestart").toString();
		String timeend=request.getParameter("timeend").toString();
		//查询所属的门店名对应的Id号码
		String sql1="select s_id from store where s_name="+"'"+lonname+"'";
		List<Object[]> longlist1=new MemInformServiceImp().normalfinad(sql1);
		int memdtoreid=0;
		if(longlist1!=null&&longlist1.size()!=0)
		{
			Object[] ss=longlist1.get(0);
			
			memdtoreid=Integer.parseInt(ss[0].toString());
		}
		//连表查询所需要的数据
		String longlist2="select start_time,end_time,emp_no,emp_name,total,total_all,total_money,bank_pay from employee a left join jiaojieban b on a.emp_id=b.saler_id where a.store_id="+memdtoreid+" and start_time>="+"'"+timestart+"'"+" and end_time<="+"'"+timeend+"'";
		List<Object[]> longlist3=new MemInformServiceImp().normalfinad(longlist2);
		request.setAttribute("longlist3", longlist3);
		request.getRequestDispatcher("/pages/member/empleelogtable.jsp").forward(request, response);
		
	}

}
