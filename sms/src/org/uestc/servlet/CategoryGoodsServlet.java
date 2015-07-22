package org.uestc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.uestc.service.CategoryGoodsService;
import org.uestc.serviceImp.CategoryGoodsServiceImp;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@WebServlet(
		urlPatterns = { "/CategoryGoods" }, 
		name = "CategoryGoodsServlet" 
	)
public class CategoryGoodsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	private CategoryGoodsService  CateService;
	public CategoryGoodsServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("商品分类Servlet!");
		String type = req.getParameter("type");// 获取类型

		CateService = new CategoryGoodsServiceImp();
		// 为空返回
		if (null == type) {
			
			// 失败状态返回为0
			PrintWriter pw = resp.getWriter();
			StringBuffer sb = new StringBuffer();
			sb.append("{\"status\":\"0\"}");
			pw.write(sb.toString());
		} else {
			// 初始化店铺
			if ("initStore".equals(type)) {
				initStore(req, resp);
			} else if ("initCategory".equals(type)) {
				initCategory(req, resp);
			}else if("initPage".equals(type)){
				req.getRequestDispatcher("/pages/goods/category-goods.jsp").forward(req, resp);
			}else if("saveCategory".equals(type)){
				saveCategory(req, resp);
			}
		}
	}
	/**
	 * 保存用户修改
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
    private void  saveCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	String idstr = req.getParameter("id");
		int id  = Integer.parseInt(idstr);
		String storeidstr = req.getParameter("storeID");
		int storeID  = Integer.parseInt(storeidstr);
		String catype = req.getParameter("catype");
		String name = req.getParameter("name");
		if("add".equals(catype)){
			CateService.addCate(id, name, storeID);
			
		}else if("del".equals(catype)){
			
			CateService.deleteCate(storeID);
		}
		String sname = CateService.getStoreNameById(storeID);
		resp.setCharacterEncoding("utf8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.write(sname);
		out.flush();
		out.close();
	    
    }
	/**
	 * 初始化分类树形菜单
	 * @param req
	 * @param resp
	 * @throws IOException 
	 */
	private void initCategory(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		String idstr = req.getParameter("id");
		int storeID  = Integer.parseInt(idstr);
	   String str=	CateService.getCategoryTree(storeID);
	   System.out.println(str);
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf8");
		PrintWriter out = resp.getWriter();
		out.write(str);
		out.flush();
		out.close();
	}
	/**
	 * 初始化门店下拉
	 * @param req
	 * @param resp
	 */
	private void initStore(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		try {
			JSONArray array = new JSONArray();
			HttpSession session = req.getSession();
			int uid = (Integer) session.getAttribute("uid");
			System.out.println(uid);
			List<Object[]> list = CateService.findStoreByUserId(uid);// 查询所有分类，父ID为-1
			for (int i = 0; i < list.size(); i++) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("sid", list.get(i)[1]);
				jsonObject.put("sname", list.get(i)[0]);
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

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
