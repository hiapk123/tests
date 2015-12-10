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
 * Servlet implementation class shempleelogquery
 */
@WebServlet(urlPatterns="/shempleelogquery",name="shempleelogqueryServlet")
public class shempleelogquery extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("交接班查询的按钮");
		String shshopname=request.getParameter("shshopname").toString();
		String time1=request.getParameter("time1").toString();
		String time2=request.getParameter("time2").toString();
		try {
			time1=StrToDate(time1);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			time2=StrToDate(time2);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//查询所属的门店名对应的Id号码
		String sql1="select s_id from store where s_name="+"'"+shshopname+"'";
		List<Object[]> longlist1=new MemInformServiceImp().normalfinad(sql1);
		int memdtoreid=0;
		if(longlist1!=null&&longlist1.size()!=0)
		{
			Object[] ss=longlist1.get(0);
			
			memdtoreid=Integer.parseInt(ss[0].toString());
		}
		
		String longlist2="select start_time,end_time,emp_no,emp_name,total,total_all,total_money,bank_pay from employee a left join jiaojieban b on a.emp_id=b.saler_id where a.store_id="+memdtoreid+" and start_time>="+"'"+time1+"'"+" and end_time<="+"'"+time2+"'";
		List<Object[]> longlist3=new MemInformServiceImp().normalfinad(longlist2);
		request.setAttribute("longlist3", longlist3);
		request.getRequestDispatcher("/pages/member/empleelogtable.jsp").forward(request, response);
		
	//---end---
	}
	
	
	//将时间转化为毫秒级
	private String StrToDate(String str) throws java.text.ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date =format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "" + date.getTime();
	}

}
