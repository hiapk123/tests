package org.uestc.servlet;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

/**
 * Servlet implementation class shempleeinformahpaging
 */
@WebServlet(urlPatterns="/shempleeinformahpaging",name="shempleeinformahpagingServlet")
public class shempleeinformahpaging extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("收银员资料分页处理");
		String storeide=request.getParameter("storeide").toString();
		String statee=request.getParameter("statee").toString();
		String shtext=request.getParameter("shtext").toString();
		String which=request.getParameter("which").toString();
		int pageno=Integer.parseInt(request.getParameter("pageno").toString());
		
		String sqlb="";
		List<Object[]> shlistd=null;
		int totalPage=0;	
		String sqlc="";
		List<Object[]> shlistc=null;
		//进行分页的处理
		
		if("".equals(shtext))
		{
			//没有文本框时候的查询
			sqlb="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id WHERE s_name="+"'"+storeide+"'"+" AND emp_status="+statee;
			//shlistd=new MemInformServiceImp().normalfinad(sqlb);
			
			sqlc="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id WHERE s_name="+"'"+storeide+"'"+" AND emp_status="+statee;
			shlistc=new MemInformServiceImp().normalfinad(sqlc);
			totalPage=shlistc.size();
		}
		
		else {
			//判断是会员，姓名还是电话查询�?
			//1.判断是否是汉字�??
			Pattern p= Pattern.compile("[\\u4e00-\\u9fa5]+");
			Matcher m=p.matcher(shtext);
			if(m.matches()==true)
			{
				//说明是汉�?
				sqlb="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id WHERE s_name="+"'"+storeide+"'"+" AND emp_status="+statee+" and emp_name="+"'"+shtext+"'";
				//shlistd=new MemInformServiceImp().normalfinad(sqlb);
				
				sqlc="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id WHERE s_name="+"'"+storeide+"'"+" AND emp_status="+statee+" and emp_name="+"'"+shtext+"'";
				shlistc=new MemInformServiceImp().normalfinad(sqlc);
				totalPage=shlistc.size();
				
			}
			else if (shtext.length()==11) {
				//说明是电话号�?
				sqlb="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id WHERE s_name="+"'"+storeide+"'"+" AND emp_status="+statee+" and emp_tel="+"'"+shtext+"'";
				//shlistd=new MemInformServiceImp().normalfinad(sqlb);
				
				sqlc="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id WHERE s_name="+"'"+storeide+"'"+" AND emp_status="+statee+" and emp_tel="+"'"+shtext+"'";
				shlistc=new MemInformServiceImp().normalfinad(sqlc);
				totalPage=shlistc.size();
			}
			else {
				//说明查询的是员工卡号
				sqlb="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id WHERE s_name="+"'"+storeide+"'"+" AND emp_status="+statee +" and emp_no="+"'"+shtext+"'";
				//shlistd=new MemInformServiceImp().normalfinad(sqlb);
				
				sqlc="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id WHERE s_name="+"'"+storeide+"'"+" AND emp_status="+statee +" and emp_no="+"'"+shtext+"'";
				shlistc=new MemInformServiceImp().normalfinad(sqlc);
				totalPage=shlistc.size();
				
			}
			
		}
		int yeshu=0;
		if(totalPage%10==0)
		{
			yeshu=totalPage/10;
		}
		else {
			yeshu=totalPage/10 +1;
		}
		int currentPage=1;
		
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
			
			currentPage=yeshu;
		}
		else {
			currentPage=Integer.parseInt(which.toString());
		}
		int current=10*(currentPage-1);
		
		sqlb +=" limit "+current+" ,10";
		shlistd=new MemInformServiceImp().normalfinad(sqlb);
		
		//页面进行跳转，返回数据页面进行绑�?
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("shlistd", shlistd);
		request.getRequestDispatcher("/pages/emplee/empleetable.jsp").forward(request, response);
	}

}
