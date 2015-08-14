package org.uestc.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.uestc.service.GoodsService;
import org.uestc.serviceImp.GoodsServiceImp;

import org.uestc.util.DateFormatUtils;
import org.uestc.util.HtmlPage;
import org.uestc.util.Page;
import org.uestc.util.SqlHelper;

import com.uestc.bean.Category;
import com.uestc.bean.CommissionRule;
import com.uestc.bean.Goods;
import com.uestc.bean.IntegralRule;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;
import com.uestc.bean.Vip;

@WebServlet(urlPatterns = { "/goods" })
public class GoodsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6005017459312388968L;
	private int s_id = 0;
	private GoodsService good = new GoodsServiceImp();// ������

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String m = req.getParameter("m");
		if (m.equals("goodsInfo")) {
			this.goodsInfo(req, resp);
			req.getRequestDispatcher("/pages/goods/goods-info.jsp").forward(req, resp);
		} else if (m.equals("findGoods")) {
			this.findGoods(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/findgood.jsp").forward(req, resp);
		} else if ("findGoodByPage".equals(m)) {
			this.findGoodByPage(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/findgood.jsp").forward(req, resp);
		} else if (m.equals("addGood")) {
			this.addGood(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/addgood.jsp").forward(req, resp);
		} else if (m.equals("addGoods")) {
			this.addGoods(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/addproduct.jsp").forward(req, resp);
		}else if (m.equals("editGood")) {
			this.editGood(req, resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/editgood.jsp").forward(req, resp);
		}else if (m.equals("editGood2")) {
			this.editGood2(req, resp);
			//req.getRequestDispatcher("<%=basePath %>goods?m=goodsInfo").forward(req, resp);
			resp.sendRedirect("<%=basePath %>goods?m=goodsInfo");
		}else if (m.equals("deleteGood")) {
			this.deleteGood(req, resp);
			
          String  url = req.getHeader("Referer"); //获得前一页的URL
			resp.sendRedirect(url);
		}
		
	}
	
	

	/***
	 * 删除商品
	 * @param req
	 * @param resp
	 */
	private void deleteGood(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String g_id=req.getParameter("g_id");
		try {

			good.deletegood( Integer.valueOf(g_id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/***
	 * 修改商品2
	 * @param req
	 * @param resp
	 */
	private void editGood2(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

		String g_id=req.getParameter("g_id");
		String s_id = (String) req.getParameter("s_id");
		String s_name = req.getParameter("s_name");
		String g_name = req.getParameter("g_name");
	//	String g_flag = req.getParameter("state");
		String g_stock_num = req.getParameter("kucun");
		String g_sale_price = req.getParameter("xiaoshoujia");
		String g_pur_price = req.getParameter("jinhuojia");
		String c_name = req.getParameter("c_name");
		String g_barcode = req.getParameter("tiaoma");
		//int g_flag0 = Integer.parseInt(g_flag);
		
	System.out.println(g_name);
	System.out.println("nimei"+s_id);
	
		/*
         req.setAttribute(s_name, s_name);
         req.setAttribute(g_name, g_name);
       //  req.setAttribute(g_flag, g_flag);
         req.setAttribute(g_stock_num, g_stock_num);
         req.setAttribute(g_sale_price, g_sale_price);
         req.setAttribute(g_pur_price, g_pur_price);
         req.setAttribute(c_name, c_name);
         req.setAttribute(g_barcode, g_barcode);*/
		try {

			good.editgood(Integer.valueOf(s_id), s_name, g_name, g_stock_num, g_sale_price, g_pur_price, c_name, g_barcode, Integer.valueOf(g_id));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/***
	 * 修改商品
	 * @param req
	 * @param resp
	 */
	
	
	private void editGood(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		String g_id=req.getParameter("g_id");
	String s_id = req.getParameter("s_id");
		String s_name = req.getParameter("s_name");
		String g_name = req.getParameter("g_name");
	//	String g_flag = req.getParameter("state");
	//	String g_stock_num = req.getParameter("kucun");
	//	String g_sale_price = req.getParameter("xiaoshoujia"); 
	//	String g_pur_price = req.getParameter("jinhuojia");
		String c_name = req.getParameter("c_name");
	//	String g_barcode = req.getParameter("tiaoma");
        req.setAttribute("s_name", s_name);
        req.setAttribute("c_name", c_name);     
        req.setAttribute("g_id", g_id);
        req.setAttribute("g_name", g_name);
        req.setAttribute("s_id", s_id);
        System.out.println(s_id);
	}

	/***
	 * 新增商品进入页面
	 * 
	 * @param req
	 * @param resp
	 */

	private void addGoods(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		String s_id = req.getParameter("s_id");
		String s_name = req.getParameter("s_name");
		req.setAttribute(s_id, s_id);
		req.setAttribute(s_name, s_name);
	}

	/***
	 * 新增商品
	 * 
	 * @param req
	 * @param resp
	 */
	private void addGood(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		String s_id = req.getParameter("storeID");
		String s_name = req.getParameter("storeName");
		String g_name = req.getParameter("productname");
		String g_flag = req.getParameter("state");
		String g_stock_num = req.getParameter("kucun");
		String g_sale_price = req.getParameter("xiaoshoujia");
		String g_pur_price = req.getParameter("jinhuojia");
		String c_name = req.getParameter("fenlei");
		String g_barcode = req.getParameter("tiaoma");
		int g_flag0 = Integer.parseInt(g_flag);
		try {
			good.addgood(Integer.valueOf(s_id), s_name, g_name, g_flag0, g_stock_num, g_sale_price, g_pur_price, c_name, g_barcode);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/***
	 * 分页查询
	 * 
	 * @param req
	 * @param resp
	 */
	private void findGoodByPage(HttpServletRequest req, HttpServletResponse resp) {
		String currentPage = req.getParameter("currentPage");
		if (null != currentPage) {
			try {
				int pageNo = Integer.valueOf(currentPage.trim());

				List<Object[]> list = good.goodssearch(s_id, pageNo * 10);
				req.setAttribute("goodsList", list);
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		}
	}

	/***
	 * 查询商品
	 * 
	 * @param req
	 * @param resp
	 */
	private void findGoods(HttpServletRequest req, HttpServletResponse resp) {
		RequestHelper reqHelper = new RequestHelper(req);
		List<Object[]> list = good.goodssearch(reqHelper.sid, 0);
		int totalPage = 0;
		totalPage = getTotalPage();
		s_id = reqHelper.sid;
		req.setAttribute("currentPage", 1);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("goodsList", list);
		req.setAttribute("s_id", s_id);
	
	}

	private void goodsInfo(HttpServletRequest req, HttpServletResponse resp) {
		List<Object[]> storeList = null;
		HttpSession session = req.getSession();

		try {
			storeList = good.findStoreByUserID(Integer.valueOf(session.getAttribute("uid").toString()));

			req.setAttribute("storeList", storeList);

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	// 实现
	private int getTotalPage() {

		return 20;
	}

	private void setAttribute(HttpServletRequest req, String[] args, Object... params) {
		if (args.length == params.length) {
			for (int i = 0; i < params.length; i++) {
				req.setAttribute(args[i], params[i]);
			}
		}

	}

	private class RequestHelper {
		public int sid = -1;//

		public RequestHelper(HttpServletRequest req) {
			String tempId = req.getParameter("store");
			try {
				sid = Integer.valueOf(tempId);

			} catch (NumberFormatException e) {
				sid = -1;
				e.printStackTrace();
			}
		}
	}

}
