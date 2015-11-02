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

/**
 * Servlet implementation class shfenye
 */
@WebServlet(urlPatterns="/shfenye",name="shfenyeservlet"

		)
public class shfenye extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		this.doPost(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("分页函数的实�?");
		//接受前台传过来的参数
		String shopname=request.getParameter("shopname");
		int shzhuangtai=Integer.parseInt(request.getParameter("shzhuangtai"));
		String shfenlei=request.getParameter("shfenlei").toString();
		String shgonghuoshang=request.getParameter("shsuname").toString();
		String shnameofshangpin=request.getParameter("shnameofshangpin");
		//当前页面
		int pageno=Integer.parseInt(request.getParameter("pageno").toString());
		String which=request.getParameter("which").toString();
		System.out.println(shopname);
		System.out.println(shzhuangtai);
		System.out.println(shfenlei);
		System.out.println(shgonghuoshang);
		System.out.println(shnameofshangpin);
		System.out.println(pageno);//当前页面
		System.out.println(which);//点击的�??
		//这里应该重新查询获取记录的条�?
		List<batchgoods> shlist=null;
		int sqlshuliang;
		if(shnameofshangpin==null)
		{
			
			sqlshuliang=new TableBatchServiceImp().mshcount(shopname, shzhuangtai, shfenlei, shgonghuoshang);
			
		}
		else {
		
			
			sqlshuliang=new TableBatchServiceImp().yshcount(shopname, shzhuangtai, shfenlei, shgonghuoshang, shnameofshangpin);
		}
	//获取数量结束
		System.out.println("记录的条�?"+sqlshuliang);
		int thetotal;//定义的是总共的页�?
		if(sqlshuliang%10==0)
		{
			thetotal=sqlshuliang/10;
		}
		else {
			thetotal=sqlshuliang/10+1;
		}
		
		
		//判断应该跳转的页�?
		if("".equals(which))
		{
			which="first";
			pageno=1;
		}else if ("first".equals(which)) {

			which="first";
			pageno=1;
			
		}
		else if ("first".equals(which)) {
			pageno=1;
			
		}
		else if ("prev".equals(which)) {
			pageno--;
			
		}
	else if ("next".equals(which)) {
		pageno++;
		
	}
	else if ("last".equals(which))
	{
	//这里�?要获取�?�共的额页数
		pageno=thetotal;
		
		}
else {
	
	pageno=Integer.parseInt(which.toString());
}		
	
	List<batchgoods> newlist=null;	
	//获取页数后进行查询�??
	if(shnameofshangpin==null)
	{
		//条码为空执行的查�?.
		int current=pageno;
		newlist=new TableBatchServiceImp().yshbathdate(shopname, shzhuangtai, shfenlei, shgonghuoshang,current );
		
	}
	else{
		//条码不空执行的查询�??
		int current1=pageno;
		newlist=new TableBatchServiceImp().mshbathdate(shopname, shzhuangtai, shfenlei, shgonghuoshang, shnameofshangpin, current1);
		
	}
	
	//写入相应的域�?
	request.setAttribute("shlist", newlist);
	request.setAttribute("totalPage", sqlshuliang);
	request.setAttribute("currentPage", pageno);
	
	// 页面的额跳转
	request.getRequestDispatcher("/pages/goods/shtable.jsp").forward(request, response);

	
		
	}

}
