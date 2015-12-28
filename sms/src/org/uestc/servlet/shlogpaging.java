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
 * Servlet implementation class shlogpaging
 */
@WebServlet(urlPatterns="/shlogpaging",name="shlogpagingServlet")
public class shlogpaging extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("交接班分页处理");
		String lonname=request.getParameter("shshopname").toString();
		String timestart=request.getParameter("time1").toString();
		String timeend=request.getParameter("time2").toString();
		String which=request.getParameter("which").toString();
		int pageno=Integer.parseInt(request.getParameter("pageno").toString());
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
				//进行分页的当前页数的判断
			String longlist2="select start_time,end_time,emp_no,emp_name,total,total_all,total_money,bank_pay from employee a left join jiaojieban b on a.emp_id=b.saler_id where a.store_id="+memdtoreid+" and start_time>="+"'"+timestart+"'"+" and end_time<="+"'"+timeend+"'";	
			List<Object[]> longlist5=new MemInformServiceImp().normalfinad(longlist2);
			int totalPage=longlist5.size();
			int zongyehsu=0;
			if(totalPage%10==0)
			{
				zongyehsu=totalPage/10;
				
			}
			else {
				zongyehsu=totalPage/10 +1;
				
			}
			int currentPage=1;
			if("first".equals(which))
			{
				currentPage=1;
				
			}
			else if ("next".equals(which)) {
				
				currentPage=pageno+1;
			}
			else if ("prev".equals(which)) {
				
				currentPage=pageno-1;
			}
			else if ("last".equals(which)) {
				
				currentPage=zongyehsu;
			}
			else {
				currentPage=Integer.parseInt(which.toString());
			}
			
			int current=10*(currentPage-1);
			//连表查询所需要的数据
			String longlist9="select start_time,end_time,emp_no,emp_name,total,total_all,total_money,bank_pay from employee a left join jiaojieban b on a.emp_id=b.saler_id where a.store_id="+memdtoreid+" and start_time>="+"'"+timestart+"'"+" and end_time<="+"'"+timeend+"'"+"limit "+current+",10";
			List<Object[]> longlist3=new MemInformServiceImp().normalfinad(longlist9);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPage", totalPage);
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
