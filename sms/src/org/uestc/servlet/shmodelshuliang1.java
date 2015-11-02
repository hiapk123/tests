package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;


@WebServlet(urlPatterns="/shmodelshuliang1",
			name="shmodelshuliang1Servlet")
public class shmodelshuliang1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//***
		String shopname=request.getParameter("shopname");
		int shzhuangtai=Integer.parseInt(request.getParameter("shzhuangtai"));
		String shfenlei=request.getParameter("shfenlei");
		String shgonghuoshang=request.getParameter("shgonghuoshang");
		String nrewql="SELECT a.g_id,a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b  ON a.com_id=b.com_id  where a.s_name="+"'"+shopname+"'"+"and a.g_flag="+shzhuangtai+" and a.c_name="+shfenlei+" and a.su_name="+"'"+shgonghuoshang+"'";
		List<Object[]> ssbbs=new MemInformServiceImp().normalfinad(nrewql);
		int skshnum=ssbbs.size();
		request.setAttribute("skshnum", skshnum);
		request.getRequestDispatcher("/pages/goods/batchsetting-goods.jsp").forward(request, response);
	}

}
