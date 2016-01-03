package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.print.attribute.standard.PrinterLocation;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;

/**
 * Servlet implementation class shmeminit
 */
@WebServlet(urlPatterns= "/shmeminit",
			name="shmeminitservlet")
public class shmeminit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//初始化查询所有的页面数据，页面函数meminfoinit();
		List<Object[]> nlist=null;
		nlist=new MemInformServiceImp().meminfoinit();
		//********
		List<Object[]> shshowinform=null;
		String pldsh="select a.v_id,a.v_card_no,a.vip_name,a.vip_tel,a.v_level,a.v_balance,a.v_commodity_integral,b.s_name,a.vip_startdate, a.v_status FROM vip a LEFT JOIN store b ON a.s_id=b.s_id";
		shshowinform=new MemInformServiceImp().normalfinad(pldsh);
		//*****
		//1.会员数量
		int shshoenum=0;
		//2,总的余额（5）
		int shshoeyue=0;
		//3.总的积分
		int shshoejifen=0;
		if(shshowinform!=null&&shshowinform.size()!=0)
		{
		shshoenum=shshowinform.size();
			
		for(Object[] obj:shshowinform)
		{
			shshoeyue=shshoeyue+Integer.parseInt(obj[5].toString());
			shshoejifen=shshoejifen+Integer.parseInt(obj[6].toString());
		}
		}
		int totalPage=0;
		int currentpage=1;
		if(shshoenum%10==0)
		{
			totalPage=shshoenum/10;
			
			
		}
		else {
			
			totalPage=shshoenum/10+1;
		}
		
		request.setAttribute("totalPage", shshoenum);
		request.setAttribute("currentPage", currentpage);
		request.setAttribute("nlist", nlist);
		request.setAttribute("shshoenum", shshoenum);
		request.setAttribute("shshoeyue", shshoeyue);
		request.setAttribute("shshoejifen", shshoejifen);
		request.getRequestDispatcher("/pages/member/shmtable.jsp").forward(request, response);
		
		
	}

}
