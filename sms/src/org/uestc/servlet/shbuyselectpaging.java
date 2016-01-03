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

@WebServlet(urlPatterns="/shbuyselectpaging",name="shbuyselectpagingServlet")
public class shbuyselectpaging extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("会员购买查询分页");
		String textfiled=request.getParameter("textfiled").toString();
		String time1=request.getParameter("time1").toString();
		String time2=request.getParameter("time2").toString();
		String which=request.getParameter("which").toString();
		int pageno=Integer.parseInt(request.getParameter("pageno").toString());
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
		//将会员卡号转化为会员id
		String sqluuh="select v_id from vip where v_card_no="+"'"+textfiled+"'";
		List<Object[]> hahalist=new MemInformServiceImp().normalfinad(sqluuh);
		int v_id=0;//定义会员的卡号
		if(hahalist!=null&&hahalist.size()!=0)
		{
			Object[] llk=hahalist.get(0);
			v_id=Integer.parseInt(llk[0].toString());
		}
		int currentPage=1;
		int totalPage=0;		
		
		String slsql="select g_barcode,sa_date,sa_goods_num,sa_goods_price,sa_real_price,sa_discount,s_name from sale a left join store b on a.store_id=b.s_id where a.sa_buyer_id="+v_id+" and sa_date>="+"'"+time1+"'"+" and sa_date<="+"'"+time2+"'";
		List<Object[]> kkldsa=new MemInformServiceImp().normalfinad(slsql);
		totalPage=kkldsa.size();
		//#分页
		int zuyeshu=0;
		if(totalPage%10==0)
		{
			zuyeshu=totalPage/10;
			
		}
		else {
			zuyeshu=totalPage/10+1;
		}
		if("first".equals(which))
		{
			currentPage=1;
		}
		else if ("prev".equals(which)) {
			
			currentPage=pageno-1;
			
		}
		else if ("next".equals(which)) {
			
			currentPage=pageno+1;
		}
		else if ("last".equals(which)) {
			
			currentPage=zuyeshu;
		}
		else {
			
			currentPage=Integer.parseInt(which);
		}
		//#
		//查询所需要的信息
		int current=10*(currentPage-1);
		String gettable="select g_barcode,sa_date,sa_goods_num,sa_goods_price,sa_real_price,sa_discount,s_name from sale a left join store b on a.store_id=b.s_id where a.sa_buyer_id="+v_id+" and sa_date>="+"'"+time1+"'"+" and sa_date<="+"'"+time2+"'"+" limit "+current+",10";
		List<Object[]> cmaqsd=new MemInformServiceImp().normalfinad(gettable);
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("cmaqsd", cmaqsd);
		request.getRequestDispatcher("/pages/member/sh_buyselecttable.jsp").forward(request,response);
		
	}
	
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
