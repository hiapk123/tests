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
 * Servlet implementation class emperformanceinit
 */
@WebServlet(urlPatterns="/emperformanceinit",
			name="emperformanceinitServlet")
public class emperformanceinit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String sspar1=request.getParameter("sspar1").toString();
		System.out.println("页面加载");
		String ssknsql="SELECT  t1.sa_id,t1.sa_date,sa_saler_id,t2.g_name,t1.sa_goods_price,t1.sa_goods_num,t1.sa_shishou,t1.sa_profit,t1.sa_type,t3.s_name,t2.c_id from sale t1,goods t2 ,store t3 WHERE t3.s_name="+"'"+ sspar1 +"'"+"AND t1.sa_date<='2015-06-15 14:45' AND t1.sa_date>='2015-06-15 14:45'  ORDER  BY t1.sa_id ";
		List<Object[]> shempperformthetable=(List<Object[]>)new MemInformServiceImp().normalfinad(ssknsql);
		request.setAttribute("shempperformthetable", shempperformthetable);
		
		request.getRequestDispatcher("/pages/emplee/shmodel2.jsp").forward(request, response);
		
	}

}
