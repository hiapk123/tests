package org.uestc.servlet;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/Sellerinformmanager",name="SellerinformmanagerServlet")
public class Sellerinformmanager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//("导购员资料查询的按钮");
		//接受参数
		String shsellerinformstore=request.getParameter("shsellerinformstore");
		int shsellerinformstatus=Integer.parseInt(request.getParameter("shsellerinformstatus").toString()); 
		String shsellerinformtext=request.getParameter("shsellerinformtext");
		
		//查询获取table的数�?
		if("".equals(shsellerinformtext))
		{
			//没有文本框时候的查询
		}
		else {
			//分类判断
			Pattern p= Pattern.compile("[//u4e00-//u9fa5]+");
			Matcher m=p.matcher(shsellerinformtext);
			if(m.matches()==true)
			{
				//说明是汉�?
			}
			else if (shsellerinformtext.length()==11) {
				
				//说明是按照电话号码进行查�?
			}
			else {
				//说明是按照工号来进行查询
			}
		}
		
		//
		
		
	}

}
