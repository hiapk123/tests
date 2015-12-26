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

@WebServlet(urlPatterns="/empleecheckbox",name="empleecheckboxservlet")
public class empleecheckbox extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//("会员查询按钮");
		String type=request.getParameter("type");
		//(type);
		String storeide=request.getParameter("storeide");
		String statee=request.getParameter("statee");
		String shtext=request.getParameter("shtext");
		String sqlb="";
		List<Object[]> shlistd=null;
		if("1".equals(type))
		{
			//没有文本框时候的查询
			sqlb="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id WHERE s_name="+"'"+storeide+"'"+" AND emp_status="+statee;
			shlistd=new MemInformServiceImp().normalfinad(sqlb);
			
		}
		
		else {
			//判断是会员，姓名还是电话查询�?
			//1.判断是否是汉字�??
			Pattern p= Pattern.compile("[//u4e00-//u9fa5]+");
			Matcher m=p.matcher(shtext);
			if(m.matches()==true)
			{
				//说明是汉�?
				sqlb="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id WHERE s_name="+"'"+storeide+"'"+" AND emp_status="+statee+" and emp_name="+"'"+shtext+"'";
				shlistd=new MemInformServiceImp().normalfinad(sqlb);
			}
			else if (shtext.length()==11) {
				//说明是电话号�?
				sqlb="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id WHERE s_name="+"'"+storeide+"'"+" AND emp_status="+statee+" and emp_tel="+"'"+shtext+"'";
				shlistd=new MemInformServiceImp().normalfinad(sqlb);
				
			}
			else {
				//说明查询的是员工卡号
				sqlb="select emp_id,s_name,emp_no,emp_name,emp_tel,emp_status from employee left join store on store_id=s_id WHERE s_name="+"'"+storeide+"'"+" AND emp_status="+statee +" and emp_no="+"'"+shtext+"'";
				shlistd=new MemInformServiceImp().normalfinad(sqlb);
				
			}
			
			
			
		}
		
		//页面进行跳转，返回数据页面进行绑�?
		request.setAttribute("shlistd", shlistd);
		request.getRequestDispatcher("/pages/emplee/empleetable.jsp").forward(request, response);
		
	}

}
