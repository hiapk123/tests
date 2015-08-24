package org.uestc.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.uestc.service.InventoryWarningService;
import org.uestc.service.ZBTJService;
import org.uestc.serviceImp.InventoryWarningServiceImp;
import org.uestc.serviceImp.ZBTJServiceImp;
import org.uestc.util.PageBean;

import com.uestc.bean.Goods;
import com.uestc.bean.Sale;
import com.uestc.bean.Store;
import com.uestc.bean.Users;

import cn.itcast.servlet.BaseServlet;

/**
 * Servlet implementation class ZBTJServlet
 */
@WebServlet("/ZBTJServlet")
public class ZBTJServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private InventoryWarningService inventoryWarningService = new InventoryWarningServiceImp();
	private ZBTJService zbtjService = new ZBTJServiceImp();
	
	public String initLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Users user = (Users) request.getSession().getAttribute("sessionUser");
		List<Store> storeList = inventoryWarningService.findAllStoresByUid(user.getUId());
		request.setAttribute("storeList", storeList);
		
		PageBean<Sale> pb = zbtjService.findAllSalesByUid(user.getUId());
		
		
		return "f:/pages/sales/zbtj.jsp";
	}
}
