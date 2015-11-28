package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.serviceImp.MemInformServiceImp;
import org.uestc.serviceImp.TableBatchServiceImp;

import com.uestc.bean.batchgoods;

@WebServlet(urlPatterns="/shbatchchaxunselect",
			name="shbatchchaxunselectServlet")
public class shbatchchaxunselect extends HttpServlet {
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
	
		String shopname=request.getParameter("shopname");
		int shzhuangtai=Integer.parseInt(request.getParameter("shzhuangtai"));
		String shfenlei=request.getParameter("shfenlei");
		String shgonghuoshang=request.getParameter("shgonghuoshang");
		String shnameofshangpin=request.getParameter("shnameofshangpin");
		int currentpage=Integer.parseInt(request.getParameter("currentpage"));
		String shbathsql="";
		String shnumsql="";
		List<batchgoods> shlist=null;
		if("".equals(shnameofshangpin))
		{
		 shlist=new TableBatchServiceImp().yshbathdate(shopname, shzhuangtai, shfenlei, shgonghuoshang, currentpage);
		 
		 if("null".equals(shgonghuoshang))
		 {
			 shnumsql="SELECT a.g_id,a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b ON a.c_id=b.com_id where a.s_name="+"'"+ shopname +"'"+ " and a.g_flag=" + shzhuangtai + " and a.c_name= " + "'"+shfenlei +"'"+ " and a.su_name is null"; 
		 
		 }
		 else 
		 {
			 shnumsql="SELECT a.g_id,a.g_name,a.g_barcode,a	.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b ON a.c_id=b.com_id where a.s_name="+"'"+ shopname +"'"+ " and a.g_flag=" + shzhuangtai + " and a.c_name= " + "'"+shfenlei +"'"+ " and a.su_name="+ "'"+shgonghuoshang +"'";
		 }
		 
		 }
		else {
		shlist=shlist=new TableBatchServiceImp().mshbathdate(shopname, shzhuangtai, shfenlei, shgonghuoshang, shnameofshangpin, currentpage);	
		
		 if("null".equals(shgonghuoshang))
		 {
			 shnumsql="SELECT a.g_id,a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b ON a.c_id=b.com_id where a.s_name="+"'"+ shopname +"'"+ " and a.g_flag=" + shzhuangtai + " and a.c_name= " + "'"+shfenlei +"'"+ " and a.su_name is null"; 
		 
		 }
		 else {
			
		shnumsql="SELECT a.g_id,a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b  ON a.c_id=b.com_id  where a.s_name="+"'"+ shopname +"'"+ " and a.g_flag=" + shzhuangtai + " and a.c_name= " +"'"+ shfenlei +"'" + " and a.su_name="+ "'"+shgonghuoshang +"'"+ " and a.g_barcode= " +"'"+ shnameofshangpin+"'";	
		 }
		 }
		//#####################计算数量
		List<Object[]> ppazlccm=new MemInformServiceImp().normalfinad(shnumsql);
		int totalnum=0;
		if(ppazlccm!=null&&ppazlccm.size()!=0)
		{
			totalnum=ppazlccm.size();
		}
		int totalPage=0;
		if(totalnum%10==0)
		{
			totalPage=totalnum/10;
		}
		else {
			totalPage=totalnum/10+1;
		}
		
		
		request.setAttribute("totalPage", totalnum);
		request.setAttribute("currentPage", currentpage);
		//#####################
		request.setAttribute("shlist", shlist);
		request.getRequestDispatcher("/pages/goods/shtable.jsp").forward(request, response);
		
	}

}
