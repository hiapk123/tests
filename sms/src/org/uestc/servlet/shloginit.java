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
		String lonname=request.getParameter("lonname").toString();
		String timestart=request.getParameter("timestart").toString();
		String timeend=request.getParameter("timeend").toString();
		//将时间进行处理
		try {
			timestart=StrToDate(timestart);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			timeend=StrToDate(timeend);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//查询所属的门店名对应的Id号码
		String sql1="select s_id from store where s_name="+"'"+lonname+"'";
		List<Object[]> longlist1=new MemInformServiceImp().normalfinad(sql1);
			
		int memdtoreid=0;
		if(longlist1!=null&&longlist1.size()!=0)
		{
			Object[] ss=longlist1.get(0);
			
			memdtoreid=Integer.parseInt(ss[0].toString());
		}
		int currentPage=1;
		int current=10*(currentPage-1);
		//连表查询所需要的数据
		String longlist2="select start_time,end_time,emp_no,emp_name,total,total_all,total_money,bank_pay from employee a left join jiaojieban b on a.emp_id=b.saler_id where a.store_id="+memdtoreid+" and start_time>="+"'"+timestart+"'"+" and end_time<="+"'"+timeend+"'"+"limit "+current+",10";
		List<Object[]> longlist3=new MemInformServiceImp().normalfinad(longlist2);
		
		String longlist7="select start_time,end_time,emp_no,emp_name,total,total_all,total_money,bank_pay from employee a left join jiaojieban b on a.emp_id=b.saler_id where a.store_id="+memdtoreid+" and start_time>="+"'"+timestart+"'"+" and end_time<="+"'"+timeend+"'";
		List<Object[]> longlist8=new MemInformServiceImp().normalfinad(longlist7);
		//#########################################################################
		//分页部分
		int totalPage=longlist8.size();
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPage", totalPage);
		//#########################################################################
		request.setAttribute("longlist3", longlist3);
		request.getRequestDispatcher("/pages/member/empleelogtable.jsp").forward(request, response);
		
	}
	
	//编写字符串的转化类
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
