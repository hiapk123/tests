package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;


@WebServlet(urlPatterns="/emperfomancetable",name="emperfomancetableServlet")
public class emperfomancetable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("进入收银员处理表格servlet");
		//接受前台的参�?
		String shempperform1=request.getParameter("shempperform1");
		String shempperform2=request.getParameter("shempperform2");
		String shempperform3=request.getParameter("shempperform3");
		String shempperformtime1=request.getParameter("shempperformtime1");
		String shempperformtime2=request.getParameter("shempperformtime2");	
		//进行表格的处理操�?
		int shfenleimid=0;
		int shshouyinyuanid=0;
		//1.用收银员名字查找收银员id.***************		
		if("-1".equals(shempperform2))
		{
			
		}
		else {
		
		String shsql1="SELECT emp_id FROM employee WHERE  emp_name="+"'"+shempperform2+"'";
		List<Object[]> empid=(List<Object[]>)new MemInformServiceImp().normalfinad(shsql1);
		Object[] shobj1=empid.get(0);
		shshouyinyuanid=Integer.parseInt(shobj1[0].toString());//收银员id
		}
		//2.通过分类名字查找分类的id.
		if("-1".equals(shempperform3))
		{
			
		}
		else {
			
		
		String shsql2="SELECT c_id FROM category WHERE  c_name="+"'"+shempperform3+"'";
		List<Object[]> shfenleiid=(List<Object[]>)new MemInformServiceImp().normalfinad(shsql2);
		Object[] shobj2=shfenleiid.get(0);
		shfenleimid=Integer.parseInt(shobj2[0].toString());//分类名id
		
		}
		//查询返回表格的操�?
		//1.这里进行返回表格的查�?
		
		String shobj3="";
		if("-1".equals(shempperform2))
		{
			if("-1".equals(shempperform3))
			{
				shobj3="SELECT  t1.sa_id,t1.sa_date,sa_saler_id,t2.g_name,t1.sa_goods_price,t1.sa_goods_num,t1.sa_shishou,t1.sa_profit,t1.sa_type,t3.s_name,t2.c_id from sale t1,goods t2 ,store t3 WHERE t3.s_name="+"'"+shempperform1+"'"+" AND t1.sa_date<="+"'"+shempperformtime2+"'"+" AND t1.sa_date>="+"'"+shempperformtime1+"'"+"  ORDER  BY t1.sa_id ";
			}
			else
			{
				shobj3="SELECT  t1.sa_id,t1.sa_date,sa_saler_id,t2.g_name,t1.sa_goods_price,t1.sa_goods_num,t1.sa_shishou,t1.sa_profit,t1.sa_type,t3.s_name,t2.c_id from sale t1,goods t2 ,store t3 WHERE t3.s_name="+"'"+shempperform1+"'"+" AND t2.c_id="+shfenleimid+" AND t1.sa_date<="+"'"+shempperformtime2+"'"+" AND t1.sa_date>="+"'"+shempperformtime1+"'"+"  ORDER  BY t1.sa_id ";
			}
			
		}
		else 
		{
			if("-1".equals(shempperform3))
			{
				shobj3="SELECT  t1.sa_id,t1.sa_date,sa_saler_id,t2.g_name,t1.sa_goods_price,t1.sa_goods_num,t1.sa_shishou,t1.sa_profit,t1.sa_type,t3.s_name,t2.c_id from sale t1,goods t2 ,store t3 WHERE t3.s_name="+"'"+shempperform1+"'"+" and t1.sa_saler_id="+shshouyinyuanid+" AND t1.sa_date<="+"'"+shempperformtime2+"'"+" AND t1.sa_date>="+"'"+shempperformtime1+"'"+"  ORDER  BY t1.sa_id ";
			}
			else
			{
				shobj3="SELECT  t1.sa_id,t1.sa_date,sa_saler_id,t2.g_name,t1.sa_goods_price,t1.sa_goods_num,t1.sa_shishou,t1.sa_profit,t1.sa_type,t3.s_name,t2.c_id from sale t1,goods t2 ,store t3 WHERE t3.s_name="+"'"+shempperform1+"'"+" AND t2.c_id="+shfenleimid+" and t1.sa_saler_id="+shshouyinyuanid+" AND t1.sa_date<="+"'"+shempperformtime2+"'"+" AND t1.sa_date>="+"'"+shempperformtime1+"'"+"  ORDER  BY t1.sa_id ";
				
			}
				
		}
		
		List<Object[]> shempperformthetable=(List<Object[]>)new MemInformServiceImp().normalfinad(shobj3);
		request.setAttribute("shempperformthetable", shempperformthetable);
		
		request.getRequestDispatcher("/pages/emplee/shmodel2.jsp").forward(request, response);
		
	}

}
