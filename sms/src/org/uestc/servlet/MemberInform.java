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
 * Servlet implementation class MemberInform
 */
@WebServlet(urlPatterns="/MemberInform",name="MemberInformservlet")
public class MemberInform extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
      String m=request.getParameter("type");
      if("init".equals(m))
      {
    	  //绑定�?卡门店，会员等级，启用状态，方便前台绑定()
    	  //会员等级函数。shvlevel
    	  //点名函数shvtid
    	  //会员状�?�函数shvstatue
    	  List<Object[]> shlist1=new MemInformServiceImp().shvtid();
    	  List<Object[]> shlist2=new MemInformServiceImp().shvlevel();
   		  List<Object[]> shlist3=new MemInformServiceImp().shvstatue();
    	  request.setAttribute("shlist1", shlist1); 
    	  request.setAttribute("shlist2", shlist2); 
    	  request.setAttribute("shlist3", shlist3); 
    	  //页面转发�?
    	  request.getRequestDispatcher("/pages/member/memberinformation.jsp").forward(request, response);
      }

	}

	//这里可以构�?�私有的方法�?
	
	
	
	
}
