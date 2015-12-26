package org.uestc.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

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
		//("页面加载");
		String times1=request.getParameter("times1").toString();
		String times2=request.getParameter("times2").toString();
		try {
			times1=StrToDate(times1);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		try {
			times2=StrToDate(times2);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String ssknsql="SELECT  t1.sa_id,t1.sa_date,sa_saler_id,t2.g_name,t1.sa_goods_price,t1.sa_goods_num,t1.sa_shishou,t1.sa_profit,t1.sa_type,t3.s_name,t2.c_id from sale t1,goods t2 ,store t3 WHERE t3.s_name="+"'"+ sspar1 +"'"+"AND t1.sa_date<="+"'"+times2+"'"+ "AND t1.sa_date>="+"'"+times1+"'"+"ORDER  BY t1.sa_id ";
		List<Object[]> shempperformthetable=(List<Object[]>)new MemInformServiceImp().normalfinad(ssknsql);
		request.setAttribute("shempperformthetable", shempperformthetable);
		
		request.getRequestDispatcher("/pages/emplee/shmodel2.jsp").forward(request, response);
		
	}
	
	//字符串的转化类
	private String StrToDate(String str) throws java.text.ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "" + date.getTime();
	}


}
