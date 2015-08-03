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



@WebServlet(urlPatterns = { "/goods" })
public class GoodsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6005017459312388968L;
	private int storeid=0;
	private GoodsService good = new GoodsServiceImp();// ������

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String m = req.getParameter("m");
		if (m.equals("goodsinfo")) {
			this.goodsInfo(req, resp);
			req.getRequestDispatcher("/pages/goods/goods-info.jsp").forward(req, resp);
		}else if(m.equals("findGoods")){
			this.findGoods(req,resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/findgood.jsp").forward(req, resp);
		}else if("findGoodByPage".equals(m)){
			this.findGoodByPage(req,resp);
			req.getRequestDispatcher("/pages/goods/goodsinfo/findgood.jsp").forward(req, resp);
		}
	}
	/***
	 * 分页查询
	 * @param req
	 * @param resp
	 */
	private void findGoodByPage(HttpServletRequest req, HttpServletResponse resp) {
		String currentPage=req.getParameter("currentPage");
		if(null!=currentPage){
			try {
				int pageNo=Integer.valueOf(currentPage.trim());
				
				List<Object[]> list=good.goodssearch(storeid,pageNo*10);
				req.setAttribute("goodsList", list);
			} catch (NumberFormatException e) {
				// TODO: handle exception
			}
		}
	}

	/***
	 * 查询商品
	 * @param req
	 * @param resp
	 */
	private void findGoods(HttpServletRequest req, HttpServletResponse resp) {
		RequestHelper reqHelper=new RequestHelper(req);
		List<Object[]> list=good.goodssearch(reqHelper.sid,0);
		int totalPage=0;
		totalPage=getTotalPage();
		storeid=reqHelper.sid;
		req.setAttribute("currentPage", 1);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("goodsList", list);
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
	//实现
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
