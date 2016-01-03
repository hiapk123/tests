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
 * Servlet implementation class shbatch
 */
@WebServlet(urlPatterns = "/shbatch", name = "shbatchservlet"

)
public class shbatch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		this.doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String shtype = request.getParameter("m");

		if (shtype.equals("init")) {
			
			this.inittable(request, response);
			//页面加载商品的数量
			request.getRequestDispatcher("/pages/goods/shtable.jsp").forward(request, response);

		} else if (shtype.equals("storename")) {
			this.storetable(request, response);
			request.getRequestDispatcher("/pages/goods/shtable.jsp").forward(request, response);
		} else if (shtype.equals("thefinal")) {
			this.manytable(request, response);
			request.getRequestDispatcher("/pages/goods/shtable.jsp").forward(request, response);

		}
	}

	// 编写�?些私有的方法�?
	// 初始化显示界�?
	private void inittable(HttpServletRequest req, HttpServletResponse res) {
		//List<batchgoods> shlist = new TableBatchServiceImp().showtablebatchbyinit();
		//接收参数
		String shopname=req.getParameter("shopname");
		int shzhuangtai=Integer.parseInt(req.getParameter("shzhuangtai"));
		String shfenlei=req.getParameter("shfenlei");
		String shgonghuoshang=req.getParameter("shgonghuoshang");
		String shnameofshangpin=req.getParameter("shnameofshangpin");
		
		int currentpage=1;
		List<batchgoods> shlist=null;
		int totalpage=0;
		int sqlshuliang=0;
		int tiaomazhuangtai;
	//判断条码是否为空
		if("".equals(shnameofshangpin))
		{
			tiaomazhuangtai=0;
			shlist=new TableBatchServiceImp().yshbathdate(shopname, shzhuangtai, shfenlei, shgonghuoshang, currentpage);
			//定义实现列表显示
			
			sqlshuliang=new TableBatchServiceImp().mshcount(shopname, shzhuangtai, shfenlei, shgonghuoshang);
			
		}
		else {
			tiaomazhuangtai=1;
			shlist=new TableBatchServiceImp().mshbathdate(shopname, shzhuangtai, shfenlei, shgonghuoshang, shnameofshangpin, currentpage);
			sqlshuliang=new TableBatchServiceImp().yshcount(shopname, shzhuangtai, shfenlei, shgonghuoshang, shnameofshangpin);
		}
		
		if(sqlshuliang%10==0)
		{
			totalpage=sqlshuliang/10;
		}
		else {
			totalpage=sqlshuliang/10+1;
		}
		
		//List<batchgoods> shlist = new TableBatchServiceImp().mshowtablebatchbyinit(currentpage);
		//开始查询商品的数量。
		//###################################
		/*String shssll = "SELECT a.g_id,a.g_name,a.g_barcode,a.c_name,a.g_vip_price,a.su_name,a.g_flag,a.g_integral,a.s_name,b.com_name,a.s_name from goods a LEFT JOIN commission_rule b  ON a.com_id=b.com_id  where a.s_name="+"'"+shopname+"'"+"and a.g_flag="+shzhuangtai+" and a.c_name="+shfenlei+" and a.su_name="+"'"+shgonghuoshang+"'";
		List<Object[]> qqqlist=new MemInformServiceImp().normalfinad(shssll);
		
		int shgoodsnumbers=0;
		if(qqqlist!=null&&qqqlist.size()!=0)
		{
			shgoodsnumbers=qqqlist.size();
		}
		req.setAttribute("shgoodsnumbers", shgoodsnumbers);*/
		//#######################

		
		req.setAttribute("shlist", shlist);
		req.setAttribute("totalPage", sqlshuliang);
		req.setAttribute("currentPage", currentpage);
		req.setAttribute("tiaomazhuangtai", tiaomazhuangtai);
	}

	// 依据商店的名字显示界�?
	private void storetable(HttpServletRequest req, HttpServletResponse res) {

		String shopname=req.getParameter("shopname");
		int shzhuangtai=Integer.parseInt(req.getParameter("shzhuangtai"));
		String shfenlei=req.getParameter("shfenlei");
		String shgonghuoshang=req.getParameter("shgonghuoshang");
		String shnameofshangpin=req.getParameter("shnameofshangpin");
		int currentpage=1;
		List<batchgoods> shlist=null;
		int totalpage=0;
		int sqlshuliang=0;
		
		int tiaomazhuangtai;
	//判断条码是否为空
		if("".equals(shnameofshangpin))
		{
			tiaomazhuangtai=0;
			shlist=new TableBatchServiceImp().yshbathdate(shopname, shzhuangtai, shfenlei, shgonghuoshang, currentpage);
			sqlshuliang=new TableBatchServiceImp().mshcount(shopname, shzhuangtai, shfenlei, shgonghuoshang);
			
		}
		else {
			tiaomazhuangtai=1;
			shlist=new TableBatchServiceImp().mshbathdate(shopname, shzhuangtai, shfenlei, shgonghuoshang, shnameofshangpin, currentpage);
			sqlshuliang=new TableBatchServiceImp().yshcount(shopname, shzhuangtai, shfenlei, shgonghuoshang, shnameofshangpin);
		}
		
		if(sqlshuliang%10==0)
		{
			totalpage=sqlshuliang/10;
		}
		else {
			totalpage=sqlshuliang/10+1;
		}
		
		req.setAttribute("shlist", shlist);
		req.setAttribute("totalPage", sqlshuliang);
		req.setAttribute("currentPage", currentpage);
		req.setAttribute("tiaomazhuangtai", tiaomazhuangtai);
	
		
	}

	// 根据多个条件查询(前台页面进行查询的操作)
	private void manytable(HttpServletRequest req, HttpServletResponse rep) {
		
		String shopname=req.getParameter("shopname");
		int shzhuangtai=Integer.parseInt(req.getParameter("shzhuangtai"));
		String shfenlei=req.getParameter("shfenlei");
		String shgonghuoshang=req.getParameter("shgonghuoshang");
		String shnameofshangpin=req.getParameter("shnameofshangpin");
		int currentpage=1;
		List<batchgoods> shlist=null;
		int totalpage=0;
		int sqlshuliang=0;
		
		int tiaomazhuangtai;
	//判断条码是否为空
		if("".equals(shnameofshangpin))
		{
			tiaomazhuangtai=0;
			shlist=new TableBatchServiceImp().yshbathdate(shopname, shzhuangtai, shfenlei, shgonghuoshang, currentpage);
			sqlshuliang=new TableBatchServiceImp().mshcount(shopname, shzhuangtai, shfenlei, shgonghuoshang);
			
		}
		else {
			tiaomazhuangtai=1;
			shlist=new TableBatchServiceImp().mshbathdate(shopname, shzhuangtai, shfenlei, shgonghuoshang, shnameofshangpin, currentpage);
			sqlshuliang=new TableBatchServiceImp().yshcount(shopname, shzhuangtai, shfenlei, shgonghuoshang, shnameofshangpin);
		}
		
		if(sqlshuliang%10==0)
		{
			totalpage=sqlshuliang/10;
		}
		else {
			totalpage=sqlshuliang/10+1;
		}
		req.setAttribute("shlist", shlist);
		req.setAttribute("totalPage", sqlshuliang);
		req.setAttribute("currentPage", currentpage);
		req.setAttribute("tiaomazhuangtai", tiaomazhuangtai);
		
		
		

	}

}
