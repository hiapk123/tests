package org.uestc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.InventoryWarningService;
import org.uestc.serviceImp.InventoryWarningServiceImp;

import cn.itcast.servlet.BaseServlet;

import com.uestc.bean.Category;
import com.uestc.bean.Goods;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;
import com.uestc.bean.Users;

public class InventoryWarningServlet extends BaseServlet {

	private InventoryWarningService inventoryWarningService = new InventoryWarningServiceImp();

	public String findByCriteria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String storeName = request.getParameter("hp_store");
		// System.out.println("hp_store下拉框的值：" + storeName);
		String categoryName = request.getParameter("hp_category");
		// System.out.println("hp_category下拉框的值：" + categoryName);
		String supplierName = request.getParameter("hp_supplier");
		// System.out.println("hp_supplier下拉框的值：" + supplierName);
		String inventoryStatus = request.getParameter("hp_inventoryStatus");
		// System.out.println("hp_inventoryStatus下拉框的值：" + inventoryStatus);
		request.setAttribute("storeName", storeName);
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("supplierName", supplierName);
		request.setAttribute("inventoryStatus", inventoryStatus);

		
		Users user = (Users) request.getSession().getAttribute("sessionUser");
		/*
		 * 1.加载门店下拉框
		 */
		List<Store> storeList = inventoryWarningService.findAllStoresByUid(user.getUId());
		request.setAttribute("storeList", storeList);
		/*
		 * 2.加载分类下拉框
		 */
		List<Category> categoryList = inventoryWarningService.findAllCategory();
		request.setAttribute("categoryList", categoryList);
		/*
		 * 3.加载供货商下拉框
		 */
		List<Supplier> supplierList = inventoryWarningService.findAllSuppliers();
		request.setAttribute("supplierList", supplierList);

		List<Goods> goodsList = inventoryWarningService.findByCriteria(storeName, categoryName, supplierName, inventoryStatus);
		request.setAttribute("goodsList", goodsList);
		return "f:/pages/goods/inventoryWarning.jsp";
	}

	public String loadBaseMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// List<Object[]> storeList = inventoryWarningService.findAllStore();
		// request.setAttribute("storeList", storeList);
		/*
		 * 1.加载门店下拉框
		 */
		
		Users user = (Users) request.getSession().getAttribute("sessionUser");
		
//		System.out.println("该用户名为："+user.getUName()+"\t"+"id为："+user.getUId());
		List<Store> storeList = inventoryWarningService.findAllStoresByUid(user.getUId());
		request.setAttribute("storeList", storeList);
//		List<Store> storeList = inventoryWarningService.findAllStore();
//		request.setAttribute("storeList", storeList);
		/*
		 * 2.加载分类下拉框
		 */
		List<Category> categoryList = inventoryWarningService.findAllCategory();
		request.setAttribute("categoryList", categoryList);
		/*
		 * 3.加载供货商下拉框
		 */
		List<Supplier> supplierList = inventoryWarningService.findAllSuppliers();
		request.setAttribute("supplierList", supplierList);

//		String sName = "王二小食品店";
//		String cName = "散装";
//		String suName = "张飞";
//		String inventoryStatus = "";
		/*
		 * 4.预加载所有的商品信息
		 */
		List<Goods> goodsList = inventoryWarningService.findAllGoods();
		request.setAttribute("goodsList", goodsList);
//		List<Goods> goodsList = inventoryWarningService.findByCriteria(sName, cName, suName, inventoryStatus);
//		request.setAttribute("goodsList", goodsList);

		return "f:/pages/goods/inventoryWarning.jsp";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
