package org.uestc.servlet;

import cn.itcast.servlet.BaseServlet;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.InventoryWarningService;
import org.uestc.service.YYGKService;
import org.uestc.serviceImp.InventoryWarningServiceImp;
import org.uestc.serviceImp.YYGKServiceImp;

import com.uestc.bean.SalesSummaryBean;
import com.uestc.bean.Store;
import com.uestc.bean.Users;

@WebServlet("/YYGKServlet")
public class YYGKServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private InventoryWarningService inventoryWarningService = new InventoryWarningServiceImp();
	private YYGKService yygkService = new YYGKServiceImp();
	
	public String initLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Users user = (Users) request.getSession().getAttribute("sessionUser");
		List<Store> storeList = inventoryWarningService.findAllStoresByUid(user.getUId());
		request.setAttribute("storeList", storeList);
		
		SalesSummaryBean ssb = yygkService.getSalesSummary(user.getUId());
		request.setAttribute("ssb", ssb);
		
		return "f:/pages/sales/yygk.jsp";
	}
	public String findByCombination(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Users user = (Users) request.getSession().getAttribute("sessionUser");
		List<Store> storeList = inventoryWarningService.findAllStoresByUid(user.getUId());
		request.setAttribute("storeList", storeList);
		
		String storeName = request.getParameter("hp_store");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		String condition = request.getParameter("hp_condition");
		System.out.println(condition);
		
		request.setAttribute("storeName", storeName);
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("condition", condition);
		
		
		if (condition.equals("销售汇总分析")) {
			SalesSummaryBean ssb = yygkService.getSalesSummaryByCombination(storeName, beginTime, endTime, user.getUId());
			request.setAttribute("ssb", ssb);
		}
		
		return "f:/pages/sales/yygk.jsp";
	}

}
