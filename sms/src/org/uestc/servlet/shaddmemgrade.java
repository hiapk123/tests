package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.membership.MemberImpl;
import org.uestc.serviceImp.MemInformServiceImp;

@WebServlet(urlPatterns="/shaddmemgrade",name="shaddmemgradeServlet")
public class shaddmemgrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//增加会员等级
		//会员名称
		String shmemdjmc=request.getParameter("shmemdjmc");
		//优惠折扣
		String shmemyhzk=request.getParameter("shmemyhzk");
		//会员积分
		int shmemhyjf=Integer.parseInt(request.getParameter("shmemhyjf"));
		//时间的有效期
		String shmemyxq=request.getParameter("shmemyxq");
		
		String shmgracesql="insert into vip_type(name,discount,time,integral) values ("+"'"+shmemdjmc+"'"+","+"'"+shmemyhzk+"'"+","+"'"+shmemyxq+"'"+","+shmemhyjf+")";
		//进行插入操作
		new MemInformServiceImp().normalupdate(shmgracesql);
		
		
		//进行页面的刷新操作
		String memgracesql="select * from vip_type";
		List<Object[]> shmemgrace=(List<Object[]>)new MemInformServiceImp().normalfinad(memgracesql);
		request.setAttribute("shmemgrace", shmemgrace);
		request.getRequestDispatcher("pages/member/membergracetable.jsp").forward(request, response);
		
		
	
	}

}
