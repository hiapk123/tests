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
 * Servlet implementation class shshulianggoods
 */
@WebServlet(urlPatterns="/shshulianggoods",name="shshulianggoodsServlet")
public class shshulianggoods extends HttpServlet {
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
		
		//System.out.println("商品数量的更新");
		String shopname=request.getParameter("shopname");
		int shzhuangtai=Integer.parseInt(request.getParameter("shzhuangtai"));
		String shfenlei=request.getParameter("shfenlei");
		String shgonghuoshang=request.getParameter("shgonghuoshang");
		String shnameofshangpin=request.getParameter("shnameofshangpin");
		String llosdql="";
		//进行页面的查询
		if("".equals(shnameofshangpin))
		{
			llosdql="SELECT a.g_id,a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b  ON a.com_id=b.com_id  where a.s_name="+"'"+shopname+"'"+"and a.g_flag="+shzhuangtai+" and a.c_name="+"'"+shfenlei+"'"+" and a.su_name="+"'"+shgonghuoshang+"'";
		}
		else {
			llosdql="SELECT a.g_id,a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id  where a.s_name="+"'"+ shopname +"'"+ " and a.g_flag=" + shzhuangtai + " and a.c_name= " +"'"+ shfenlei +"'"+ " and a.su_name="+ "'"+shgonghuoshang +"'"+ " and a.g_barcode= " +"'"+ shnameofshangpin+"'";
		}
		List<Object[]>  pplsox=new MemInformServiceImp().normalfinad(llosdql);
		
		
		int shgoodsnums=0;
		if(pplsox!=null&&pplsox.size()!=0)
		{
			shgoodsnums=pplsox.size();
		}
		
		request.setAttribute("shgoodsnums", shgoodsnums);
		request.getRequestDispatcher("/pages/goods/shgoodsnumyy.jsp").forward(request, response);
		
	}

}
