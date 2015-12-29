package org.uestc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.InventoryWarningService;
import org.uestc.serviceImp.InventoryWarningServiceImp;
import org.uestc.util.PageBean;

import cn.itcast.servlet.BaseServlet;

import com.uestc.bean.Category;
import com.uestc.bean.Goods;
import com.uestc.bean.Store;
import com.uestc.bean.Supplier;
import com.uestc.bean.Users;

@WebServlet("/InventoryWarningServlet")
public class InventoryWarningServlet extends BaseServlet {

	private InventoryWarningService inventoryWarningService = new InventoryWarningServiceImp();

	public String findByCombination(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		Users user = (Users) request.getSession().getAttribute("sessionUser");
		
		List<Store> storeList = null;
		if (user.getUType() == 1) { // 管理员
			storeList = inventoryWarningService.findAllStore();
		} else {
			storeList = inventoryWarningService.findAllStoresByUid(user.getUId());
		}
		request.setAttribute("storeList", storeList);

		List<Category> categoryList = inventoryWarningService.findAllCategory();
		request.setAttribute("categoryList", categoryList);

		List<Supplier> supplierList = inventoryWarningService.findAllSuppliers();
		request.setAttribute("supplierList", supplierList);
		
		
		String storeName = request.getParameter("hp_store");
		String categoryName = request.getParameter("hp_category");
		String supplierName = request.getParameter("hp_supplier");
		String inventoryStatus = request.getParameter("hp_inventoryStatus");
		
		System.out.println("-----------------------------------------");
		System.out.println("storeName:\t"+storeName);
		System.out.println("categoryName:\t"+categoryName);
		System.out.println("supplierName:\t"+supplierName);
		System.out.println("inventoryStatus:\t"+inventoryStatus);
		System.out.println("-----------------------------------------");
		
		request.setAttribute("storeName", storeName);
		request.setAttribute("categoryName", categoryName);
		request.setAttribute("supplierName", supplierName);
		request.setAttribute("inventoryStatus", inventoryStatus);
		
		
		
		int pc = getPc(request);

		String url = getUrl(request);

		PageBean<Goods> pb = null;
		if (user.getUType() == 1) {
			pb = inventoryWarningService.findAllByCombination(storeName, categoryName, supplierName, inventoryStatus, pc);
		} else {
			pb = inventoryWarningService.findByCombination(storeName, categoryName, supplierName, inventoryStatus, user.getUId(), pc);
		}

		pb.setUrl(url);
		request.setAttribute("pb", pb);
		
		return "f:/pages/goods/inventoryWarning.jsp";
		
	}

	private int getPc(HttpServletRequest request) {
		int pc = 1;
		String pcParam = request.getParameter("pc");
		if (pcParam != null) {
			if (!pcParam.trim().isEmpty()) {
				try {
					pc = Integer.parseInt(pcParam);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		return pc;
	}
	
	private String getUrl(HttpServletRequest request) {
		String url = request.getRequestURI() + "?" + request.getQueryString();
		// 如果url中存在pc参数，截取掉，如果不存在就不用截取
		int index = url.lastIndexOf("&pc");
		if (index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}
	
	/**
	 * 通过当前登录用户查询商品信息（左边导航初始化页面函数，包括下拉搜索条件）
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findByUid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 1.加载门店下拉框
		 */
		
		Users user = (Users) request.getSession().getAttribute("sessionUser");
		
		List<Store> storeList = null;
		if (user.getUType() == 1) { // 该用户为管理员
			storeList = inventoryWarningService.findAllStore();
		} else {
			storeList = inventoryWarningService.findAllStoresByUid(user.getUId());
		}
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
		
		
		/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc(request);
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl(request);
		/*
		 * 3.获取查询条件，本方法就是uid，即当前登录用户的id
		 */
		
		/*
		 * 4.使用pc和uid调用service得到pb
		 */
		PageBean<Goods> pb = null;
		if (user.getUType() == 1) { // 该用户为管理员
			pb = inventoryWarningService.findAll(pc);
		} else {
			pb = inventoryWarningService.findByUid(user.getUId(), pc);
		}
		/*
		 * 5.给PageBean设置url，保存pb
		 */
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		
		return "f:/pages/goods/inventoryWarning.jsp";
	}

}
