package org.uestc.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.uestc.service.AnalyseGoodsService;
import org.uestc.serviceImp.AnalyseGoodsServiceImp;
import org.uestc.util.DateFormatUtils;
import org.uestc.util.PageObject;

import com.mysql.fabric.xmlrpc.base.Data;

@WebServlet(urlPatterns = { "/AnalyzeGoods" }, name = "analyzeGoodsServlet")
public class AnalyzeGoodsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8327745872922672243L;
	private AnalyseGoodsService goodService = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("商品分析Servlet!");
		String m = req.getParameter("m");// 获取类型

		goodService = new AnalyseGoodsServiceImp();
		// 为空返回
		if (null == m) {
			// 失败状态返回为0
			PrintWriter pw = resp.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("{\"status\":\"0\"}");
			pw.write(sb.toString());
		} else {
			// 初始化店铺
			if ("initStore".equals(m)) {
				initStore(req, resp);
			} else if ("initCategory".equals(m)) {
				initCategory(req, resp);
			} else if ("searchGoods".equals(m)) {
				searchGoods(req, resp);
			} else if ("initPageCount".equals(m)) {
				initPageCount(req, resp);
			}else if("initThisPage".equals(m)){
				req.getRequestDispatcher("/pages/goods/analyze-goods.jsp").forward(req, resp);
			}
		}
	}

	// 初始化页数
	private void initPageCount(HttpServletRequest req, HttpServletResponse resp) {
		int count = 0;
		int store;
		int category;
		String num;
		String startDate;
		String endDate;
		PrintWriter pw = null;
		PageObject pageObject=null;
		try {
			store = Integer.valueOf(req.getParameter("store"));
			category = Integer.valueOf(req.getParameter("category"));
			num = req.getParameter("num");
			// 获取毫秒数
			startDate = getLongTime(req.getParameter("startdate"));
			endDate = getLongTime(req.getParameter("enddate"));
			count = goodService.getCount(store, category, num, startDate,
					endDate);
			pw = resp.getWriter();
			int total=count/pageObject.DEFAULT_PAGE_SIZE+1;//得到总页数
			JSONObject jsonObject = new JSONObject();
			//jsonObject.accumulate("count", count);
			jsonObject.accumulate("total", total);
			pw.println(jsonObject);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}

	}

	private String getLongTime(String parameter) {

		return DateFormatUtils.StrToDate(parameter);
	}

	// 条件搜索商品
	private void searchGoods(HttpServletRequest req, HttpServletResponse resp) {
		PrintWriter out =null; 
		int store;
		int category;
		String num;
		String startDate;
		String endDate;
		try {
			store = Integer.valueOf(req.getParameter("store"));
			category = Integer.valueOf(req.getParameter("category"));
			num = req.getParameter("num");
			// 获取毫秒数
			startDate = getLongTime(req.getParameter("startdate"));
			endDate = getLongTime(req.getParameter("enddate"));
			
			JSONArray array = new JSONArray();
			out=resp.getWriter();
			List<Object[]> list = null;//goodService.findGoodsByCondition(currentPage, pageSize, store, category, num, startDate, endDate, ordering);
			for (int i = 0; i < list.size(); i++) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("cid", list.get(i)[0]);
				jsonObject.put("cname", list.get(i)[1]);
				array.add(jsonObject);
			}
			resp.setContentType("text/json; charset=utf-8");
			resp.setCharacterEncoding("UTF-8");

			out.write(array.toString());

		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

	// 初始化店铺
	private void initStore(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		try {
			JSONArray array = new JSONArray();
			HttpSession session = req.getSession();
			int uid = (Integer) session.getAttribute("uid");
			List<Object[]> list = goodService.findStoreByUserId(uid);// 查询所有分类，父ID为-1
			for (int i = 0; i < list.size(); i++) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("sid", list.get(i)[0]);
				jsonObject.put("sname", list.get(i)[1]);
				array.add(jsonObject);
			}
			resp.setContentType("text/json; charset=utf-8");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();

			out.write(array.toString());
			out.flush();
			out.close();

		} catch (Exception e) {

			e.printStackTrace();
			return;
		}
	}

	// 初始化分类
	private void initCategory(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		try {
			JSONArray array = new JSONArray();

			List<Object[]> list = goodService.findByCategoryId(-1);// 查询所有分类，父ID为-1
			for (int i = 0; i < list.size(); i++) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("cid", list.get(i)[0]);
				jsonObject.put("cname", list.get(i)[1]);
				array.add(jsonObject);
			}
			resp.setContentType("text/json; charset=utf-8");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter out = resp.getWriter();

			out.write(array.toString());
			out.flush();
			out.close();

		} catch (Exception e) {

			e.printStackTrace();
			return;
		}
	}

}
